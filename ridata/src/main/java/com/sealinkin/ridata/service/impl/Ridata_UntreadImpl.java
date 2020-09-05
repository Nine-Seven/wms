package com.sealinkin.ridata.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.DocumentTypeModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;

import com.sealinkin.ridata.model.Ridata_UntreadDModel;
import com.sealinkin.ridata.model.Ridata_UntreadMModel;
import com.sealinkin.ridata.po.Ridata_UntreadD;
import com.sealinkin.ridata.po.Ridata_UntreadM;
import com.sealinkin.ridata.po.Ridata_UntreadTmp;
import com.sealinkin.ridata.po.Ridata_UntreadTmpId;
import com.sealinkin.ridata.service.IRidata_UntreadService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.ContextUtil;
import com.sealinkin.util.FileUtilSys;

/**
 * 返配手建单Service
 * @author zhouhuan
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class Ridata_UntreadImpl implements IRidata_UntreadService{
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	//取消订单		7-12
	@Override
	public MsgRes cancelOrder(String enterpriseNo, String warehouseNo,
		String WorkerNo,String untreadNo) throws Exception {
		 
		String sql="update RIDATA_UNTREAD_M a set a.status =16, " +
				"a.updt_name='"+WorkerNo+"', " +
				"a.updt_date=sysdate " +
				"where a.UNTREAD_NO='"+untreadNo+
				"' and a.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"'" +
				"  and a.status=10";
		genDao.updateBySql(sql);
		 
		return new MsgRes(true,"操作成功","");
	}

	/**
	 * 返配手建单列
	 */
	@Override
	public ExtListDataBo<Ridata_UntreadMModel> getRidata_UntreadMList(
			String enterpriseNo,
			String warehouseNo,String workerOwner,
			String queryStr, PageBo pageBo)throws Exception {
		//8-16添加了     a.create_flag
		String sql="select a.create_flag,mm.serial_no, mm.s_untread_no, a.untread_no,a.po_type,a.po_no,a.take_type," +
				"f_get_fieldtext('RIDATA_UNTREAD_M','QUALITY',a.quality)qualityText,a.quality,a.cust_no,b.cust_name," +
				"a.status,a.untread_date,a.request_date,a.untread_flag,a.untread_remark," +
				"a.untread_type,a.owner_no,a.class_type,a.org_no," +
				"f_get_fieldtext('N','STATUS',a.status)statusText,a.car_plan_no, " +
				"a.rgst_name,a.rgst_date,a.updt_name,a.updt_date " +
				"from ridata_untread_m a,bdef_defcust b,ridata_untread_mm mm " +
				"where a.cust_no=b.cust_no " +
				"  and a.enterprise_no=b.enterprise_no " +
				
				"  and a.enterprise_no=mm.enterprise_no(+) " +
				"  and a.warehouse_no=mm.warehouse_no(+) " +
				"  and a.owner_no = mm.owner_no(+) " +
				"  and a.po_no=mm.s_po_no(+)  " +
				
			
				"  and a.warehouse_no='"+warehouseNo+"' " +
				"  and a.enterprise_no='"+enterpriseNo+"' ";
		String totsql="select count(1) "+
		"from ridata_untread_m a,bdef_defcust b " +
		"where a.cust_no=b.cust_no and a.warehouse_no='"+warehouseNo+"' " +
		"and a.enterprise_no=b.enterprise_no and a.enterprise_no='"+enterpriseNo+"'";
		
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
			totsql=totsql+ws;
			if(ws.indexOf("a.status")==-1){
				sql+=" and a.status<>13 ";
			}
		}else{
			sql+=" and a.status<>13 ";
			totsql+=" and a.status<>13 ";
		}
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			sql=sql+" and a.owner_no in("+workerOwner+")";
			totsql=totsql+" and a.owner_no in("+workerOwner+")";
		}else{
			sql=sql+" and 1=2";
			totsql=totsql+" and 1=2";
		}
		sql=sql+" order by mm.serial_no desc,a.untread_type,a.untread_no desc ";
		List<Ridata_UntreadMModel> list = genDao.getListByNativeSql(sql,Ridata_UntreadMModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Ridata_UntreadMModel> extListBo= new ExtListDataBo<Ridata_UntreadMModel>(list, count);
		return extListBo;
	}
	
	/**
	 * 返配手建单明细
	 */
	@Override
	public ExtListDataBo<Ridata_UntreadDModel> getRidata_UntreadDList(
			String enterpriseNo,
			String wheresql, PageBo pageBo)throws Exception {
		String sql="select a.article_no,a.supplier_no,b.article_name,b.owner_article_no,a.packing_qty," +
				//"nvl(c.packing_unit, decode(a.packing_qty,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit)) packing_unit," +
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"+
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingUnitQmin,"+
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) Unit,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingSpecQmin,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) spec,"+
				"b.unit_packing,b.qmin_operate_packing," +
				//"b.spec as packingSpec,"+
				"trunc(a.untread_qty/a.packing_qty) as planBox,"+
				"trunc(mod(a.untread_qty,a.packing_qty)/b.QMIN_OPERATE_PACKING) as planQmin,"+
				"(a.untread_qty - trunc(a.untread_qty/a.packing_qty) * a.packing_qty - trunc(mod(a.untread_qty,a.packing_qty)/b.QMIN_OPERATE_PACKING) * b.QMIN_OPERATE_PACKING) as planDis,"+
				"b.barcode,a.unit_cost,a.status,a.check_qty " +
				//"(a.untread_qty/a.packing_qty) as pobox " +
				"from ridata_untread_d a,bdef_defarticle b,bdef_article_packing c " +
				"where a.article_no=b.article_no and a.article_no=c.article_no(+) " +
				"and a.packing_qty=c.packing_qty(+) " +
				"and a.untread_no='"+wheresql+"' " +
				"and a.enterprise_no=b.enterprise_no and a.enterprise_no=c.enterprise_no(+) " +
				"and a.enterprise_no='"+enterpriseNo+"'";
		List<Ridata_UntreadDModel> list = genDao.getListByNativeSql(sql,Ridata_UntreadDModel.class,pageBo.getStart(), 1000);
		ExtListDataBo<Ridata_UntreadDModel> extListBo= new ExtListDataBo<Ridata_UntreadDModel>(list, 0);
		return extListBo;
	}
	
	@Override
	public MsgRes saveRidata_Untread(String jsonMaster,String jsonDetail,
			String warehouseNo,String enterpriseNo)throws Exception {
		Ridata_UntreadM untreadM=(Ridata_UntreadM) JSON.parseObject(jsonMaster, Ridata_UntreadM.class);
		List<Ridata_UntreadD> listd =  JSON.parseArray(jsonDetail, Ridata_UntreadD.class);
		String untreadNo="";
		if(untreadM.getId().getUntreadNo().equals("保存时自动生成")){
			List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();
			
			inList.add(enterpriseNo);
			inList.add(warehouseNo);
			inList.add(DocumentTypeModel.RIDATAUM);
			outList.add("S");
			outList.add("S");
			
			resultList=genDao.execProc(inList, outList, "PKLG_WMS_BASE.p_getsheetno");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception();
			}
			untreadNo=resultList.get(0).toString();
			untreadM.getId().setUntreadNo(untreadNo);
			for(Ridata_UntreadD d:listd){
				d.getId().setUntreadNo(untreadNo);
				d.getId().setWarehouseNo(warehouseNo);
				d.getId().setEnterpriseNo(enterpriseNo);
			}
			untreadM.getId().setEnterpriseNo(enterpriseNo);
			untreadM.getId().setWarehouseNo(warehouseNo);
			this.genDao.saveOrUpdateObj(untreadM);
			this.genDao.saveList(listd);
			this.genDao.flush();
		}else{
			untreadNo=untreadM.getId().getUntreadNo();
			deleteUntread(untreadM.getId().getUntreadNo(),enterpriseNo);
			untreadM.getId().setEnterpriseNo(enterpriseNo);
			untreadM.getId().setWarehouseNo(warehouseNo);
			for(Ridata_UntreadD d:listd){
				d.getId().setWarehouseNo(warehouseNo);
				d.getId().setEnterpriseNo(enterpriseNo);
			}
			this.genDao.saveOrUpdateObj(untreadM);
			this.genDao.saveList(listd);
		}
		return new MsgRes(true, "数据保存成功！", untreadNo);
	}
	
	@Override
	public List<Ridata_UntreadDModel> getUntreadArticle(String articleNo,String enterpriseNo)throws Exception {
		String sql="select a.article_no,a.supplier_no,a.article_name,a.barcode,a.owner_article_no,a.qmin_operate_packing,a.unit_packing from " +
		"bdef_defarticle a " +
		"where  a.article_no='"+articleNo+"' " +
		"and a.enterprise_no='"+enterpriseNo+"'";
		List<Ridata_UntreadDModel> list=genDao.getListByNativeSql(sql,Ridata_UntreadDModel.class,0,100);
		return (List<Ridata_UntreadDModel>)list;
	}
	
	@Override
	public void deleteUntread(String untreadNo,String enterpriseNo)throws Exception {
		String delsql="delete ridata_untread_m a where a.untread_no='"+untreadNo+"' and a.enterprise_no='"+enterpriseNo+"'";
		genDao.updateBySql(delsql);
		delsql="delete ridata_untread_d a where a.untread_no='"+untreadNo+"' and a.enterprise_no='"+enterpriseNo+"'";
		genDao.updateBySql(delsql);
		delsql="delete ridata_untread_mm a where a.s_untread_no='"+untreadNo+"' and a.enterprise_no='"+enterpriseNo+"'";
		genDao.updateBySql(delsql);
		delsql="delete ridata_untread_sm a where a.s_untread_no='"+untreadNo+"' and a.enterprise_no='"+enterpriseNo+"'";
		genDao.updateBySql(delsql);
	}
	

	//获得返配单号（返配扫描调用）
	@Override
	public List<ComboxBo> getUntreadNoList(
			String strOwnerNo,String warehouseNo,
			String strPageSql,String enterpriseNo,String poType) throws Exception {
		
		String sql="select a.untread_no as value," +
				"a.po_no as text," +
				"a.po_no dropValue " +
				"from ridata_untread_m a where a.warehouse_no='"+warehouseNo+"' " +
				"and a.status not in('13','16') and a.enterprise_no='"+enterpriseNo+"'";
		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			sql=sql+" and a.owner_no in("+strOwnerNo+")";
		}else{
			sql=sql+" and 1=2 ";
		}
		if(strPageSql!=null && strPageSql!=""){
		    sql+=" and a.po_no like '%"+strPageSql+"%'";
		}
		//如果是次品扫描验收
		if(poType!="" && poType!=null && poType.equals("B")){
			sql+=" and a.QUALITY = 'B'";
		}else{
			sql+=" and a.QUALITY <> 'B'";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
	
	//打印
	@Override
	public MsgRes print(String enterpriseNo, String warehouseNo, 
			           String workStationNo,
			           String rgstName, String poNo,String untreadNo
			           ) throws Exception {
		String sql=" select count(*) from PNTSET_PRINTER_WORKSTATION where WAREHOUSE_NO='"+warehouseNo+ 
				"' and WORKSTATION_NO='"+workStationNo+"' " +
				"and enterprise_no='"+enterpriseNo+"' ";
	Integer intCount = genDao.getCountByNativeSql(sql);
	if(intCount==0){
		return new MsgRes(false,"工作站与打印机组对应关系没有维护","");
	}
	List outList=new ArrayList();
	List resultList=new ArrayList();
	List inList=new ArrayList();
	outList.add("S");
	outList.add("S");
	inList.add(enterpriseNo);
	inList.add(warehouseNo);
	inList.add(DocumentTypeModel.PRINTPT);
	resultList=genDao.execProc(inList, outList, "PKLG_WMS_BASE.p_getsheetno");
	if(resultList.get(1).toString().indexOf("N|")!=-1){
		throw new Exception(resultList.get(1).toString());
	}
	String taskNo=resultList.get(0).toString();
	sql=" select PRINTER_GROUP_NO from PNTSET_PRINTER_WORKSTATION where WAREHOUSE_NO='"+warehouseNo+ 
				"' and WORKSTATION_NO='"+workStationNo+"' and enterprise_no='"+enterpriseNo+"'";
	List<String> list = genDao.getListByNativeSql(sql);
	String printerGroupNo=list.get(0);
	sql="INSERT INTO JOB_PRINTTASK_M(enterprise_no,Warehouse_No,TASK_NO,SOURCE_NO,BACK_FLAG,TASK_TYPE,REPRINT_FLAG, REPORT_ID,PRINTER_GROUP_NO,OPERATE_DATE,RGST_NAME,RGST_DATE)"+
        " values ('"+enterpriseNo+"','"+
			warehouseNo+"','"+
			taskNo+"','"+
			poNo+"',"+
			"'0','B','0','UM8500RM','"+
			printerGroupNo+"',trunc(sysdate),'"+
			rgstName+"',sysdate)";
	genDao.exceuteSql(sql);
	sql="update ridata_untread_m set status='15' where untread_no='"+untreadNo+"' and enterprise_no='"+enterpriseNo+"' ";
	genDao.updateBySql(sql);
	sql="update ridata_untread_mm set status='15',printer_name='"+rgstName+"',printer_date=sysdate where s_po_no='"+poNo+"' and warehouse_no='"+warehouseNo+"' " +
			"and enterprise_no='"+enterpriseNo+"'";
	genDao.updateBySql(sql);
	return new MsgRes(true,"打印指令发送成功","");	
	}
	
	
	@Override
	public String checkUntreadNo(String enterpriseNo, String warehouseNo, String poNo)
			throws Exception {
		String sql="select a.po_no from ridata_untread_m a where a.po_no='"+poNo+"' " +
				" and a.warehouse_no='"+warehouseNo+"' and a.enterprise_no='"+enterpriseNo+"'";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
	//集单打印
	@Override
	public MsgRes printCheckPlan(String enterpriseNo, String warehouseNo,
			String strSourceNo, String ownerNo,String rgstName, String strDockNo)
			throws Exception {
		
	    	String  s_untread_no="";
			String sql="select rum.s_untread_no from ridata_untread_mm rum " +
					"where rum.s_po_no='"+strSourceNo+"' ";		
			
			List<String> list=genDao.getListByNativeSql(sql);			
			
			
			if(list.size()==0){
				sql="select a.untread_no from ridata_untread_m a where a.po_no='" +strSourceNo+"'";
				List<String> untreadNo=genDao.getListByNativeSql(sql);	
				
				List inlist2=new ArrayList();
				List outList2=new ArrayList();
				List resultList2=new ArrayList();
				
				inlist2.add(enterpriseNo);
				inlist2.add(warehouseNo);
				inlist2.add(untreadNo.get(0));
				inlist2.add("N");
				outList2.add("S");
				outList2.add("S");
				resultList2=genDao.execProc(inlist2, outList2, "PKLG_RIDATA.p_single_set");
				System.out.println(resultList2);	
				if(resultList2.get(1).toString().indexOf("N|")!=-1){
					throw new Exception(resultList2.get(1).toString());
				}else{
					s_untread_no=resultList2.get(0).toString();
				}
			}else{
				s_untread_no=list.get(0);
			}
					
			
			List outList1=new ArrayList();
			List resultList1=new ArrayList();
			List inList1=new ArrayList();
			outList1.add("S");
			outList1.add("S");
			inList1.add(enterpriseNo);//strEnterpriseNo
			inList1.add(warehouseNo);//strWarehouseNo
			inList1.add(s_untread_no);//strSourceNo
			inList1.add("0");//strBackFlag
			inList1.add("UM8500R");//strReportID
			inList1.add(strDockNo);//strDockNo
			inList1.add("0");//strReprintFlag
			inList1.add(rgstName);//strUserNo
			System.out.println(inList1);
			resultList1=genDao.execProc(inList1, outList1, "PKOBJ_PRINTTASK.p_insert_taskmaster");
			if(resultList1.get(1).toString().indexOf("N|")!=-1){
				throw new Exception(resultList1.get(1).toString());
			}
		
		return new MsgRes(true,"打印任务已发送","");	
	}
	/**
	 * 上传Excel导入数据库
	 */
	@Override
	public MsgRes upLoad(File file,String strWarehouseNo,String strEnterpriseNo,String workNo,String strWorkSpaceNo) throws Exception {
		
		FileUtilSys.writeFile(file, "RidataTmp.xlsx", ContextUtil.getWebRootPath()+"uploadFiles\\temp\\");
	
		List<Ridata_UntreadTmp> list  = changeexcelBean(ContextUtil.getWebRootPath()+"uploadFiles\\temp\\"+"RidataTmp.xlsx",strWarehouseNo,strEnterpriseNo);
			
		genDao.saveList(list);
		genDao.flush();
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strWorkSpaceNo);
		inList.add(workNo);
		resultList=genDao.execProc(inList, outList, "pkobj_create_base.p_ridata_untread");

		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,resultList.get(0).toString(),"");
	}
	
	/**
	 * Excel数据转List
	 */
	@Override
	public List<Ridata_UntreadTmp> changeexcelBean(String fileName,String strWarehouseNo,String strEnterpriseNo)throws Exception {
		List<Ridata_UntreadTmp> iitList = new ArrayList<Ridata_UntreadTmp>();
		
		//导入前删除临时表数据
		String delsql=" delete from ridata_untread_tmp a " +
				      "  where a.enterprise_no='"+strEnterpriseNo+"' " +
				      "    and a.warehouse_no='"+strWarehouseNo+"' ";
		genDao.updateBySql(delsql);
		
		List<List> excelList = this.impExcel(fileName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for (int i = 0; i < excelList.size(); i++) {
			if(excelList.get(i).get(0).toString()!=null && !excelList.get(i).get(0).toString().equals("")){
				Ridata_UntreadTmp po = new Ridata_UntreadTmp();
				Ridata_UntreadTmpId id = new Ridata_UntreadTmpId();
							
				id.setEnterpriseNo(strEnterpriseNo);
				id.setWarehouseNo(strWarehouseNo);
				
				id.setRowId(Double.parseDouble(excelList.get(i).get(0).toString()));
				id.setPoNo(changeType(excelList.get(i).get(1).toString()));
				id.setOwnerNo(changeType(excelList.get(i).get(2).toString()));
				id.setCustNo(changeType(excelList.get(i).get(3).toString()));
				id.setUntreadFlag(changeType(excelList.get(i).get(4).toString()));
				id.setPoType(excelList.get(i).get(5).toString());
				id.setClassType(changeType(excelList.get(i).get(6).toString()));
				id.setQuality(changeType(excelList.get(i).get(7).toString()));
				id.setUntreadDate(sdf.parse(excelList.get(i).get(8).toString()));
				id.setRequestDate(sdf.parse(excelList.get(i).get(9).toString()));
				id.setOrgNo(changeType(excelList.get(i).get(10).toString()));
				id.setTakeType(changeType(excelList.get(i).get(11).toString()));
				id.setOwnerArticleNo(changeType(excelList.get(i).get(12).toString()));
				id.setPackingQty(Double.parseDouble(excelList.get(i).get(13).toString().equals("")?"1":excelList.get(i).get(13).toString()));
				id.setPoQty(Double.parseDouble(excelList.get(i).get(14).toString().equals("")?"0":excelList.get(i).get(14).toString())*Double.parseDouble(excelList.get(i).get(13).toString().equals("")?"1":excelList.get(i).get(13).toString())+Double.parseDouble(excelList.get(i).get(15).toString().equals("")?"0":excelList.get(i).get(15).toString()));
				id.setUnitCost(Double.parseDouble(excelList.get(i).get(16).toString().equals("")?"0":excelList.get(i).get(16).toString()));
				id.setCarPlanNo(changeType(excelList.get(i).get(17).toString()));
				id.setUntreadRemark(changeType(excelList.get(i).get(18).toString()));
				id.setRsvVarod1(changeType(excelList.get(i).get(18).toString().equals("")?"":excelList.get(i).get(19).toString()));
				id.setRsvVarod2(changeType(excelList.get(i).get(19).toString().equals("")?"":excelList.get(i).get(20).toString()));
				id.setRsvVarod3(changeType(excelList.get(i).get(20).toString().equals("")?"":excelList.get(i).get(21).toString()));
				id.setRsvVarod4(changeType(excelList.get(i).get(21).toString().equals("")?"":excelList.get(i).get(22).toString()));
				id.setRsvVarod5(changeType(excelList.get(i).get(22).toString().equals("")?"":excelList.get(i).get(23).toString()));
				id.setRsvVarod6(changeType(excelList.get(i).get(23).toString().equals("")?"":excelList.get(i).get(24).toString()));
				id.setRsvVarod7(changeType(excelList.get(i).get(24).toString().equals("")?"":excelList.get(i).get(25).toString()));
				id.setRsvVarod8(changeType(excelList.get(i).get(25).toString().equals("")?"":excelList.get(i).get(26).toString()));
				id.setRsvNum1(Double.parseDouble(excelList.get(i).get(26).toString().equals("")?"0":excelList.get(i).get(27).toString()));
				id.setRsvNum2(Double.parseDouble(excelList.get(i).get(27).toString().equals("")?"0":excelList.get(i).get(28).toString()));
				id.setRsvNum3(Double.parseDouble(excelList.get(i).get(28).toString().equals("")?"0":excelList.get(i).get(29).toString()));
				id.setRsvDate1(sdf.parse(excelList.get(i).get(29).toString().equals("")?"1900-01-01":excelList.get(i).get(30).toString()));
				id.setRsvDate2(sdf.parse(excelList.get(i).get(30).toString().equals("")?"1900-01-01":excelList.get(i).get(31).toString()));
				id.setRsvDate3(sdf.parse(excelList.get(i).get(31).toString().equals("")?"1900-01-01":excelList.get(i).get(32).toString()));
			
				id.setStockType("1");
				id.setStockValue("N");
				id.setExpNo("0");
				id.setStatus("10");
				po.setId(id);
				iitList.add(po);
			}
		}
		return iitList;
	}
	
	/**
	 * 解析Excel
	 */
	@Override
	public List<List> impExcel(String execelFile)throws Exception{
		List<List> inputlist = new ArrayList<List>();
		try {
			// 构造 Workbook 对象，execelFile 是传入文件路径(获得Excel工作区)
			Workbook book = null;
			try {
				// Excel 2007获取方法
				book = new XSSFWorkbook(new FileInputStream(execelFile));
			} catch (Exception ex) {
				// Excel 2003获取方法
				book = new HSSFWorkbook(new FileInputStream(execelFile));
			}
			// 读取表格的第一个sheet页
			Sheet sheet = book.getSheetAt(0);
			// 定义 row、cell
			Row row;
			String cell;
			// 总共有多少行,从0开始
			int totalRows = sheet.getLastRowNum();
			// 总共有多少列
			int totalCells = sheet.getRow(0).getLastCellNum();
			
			// 循环输出表格中的内容,首先循环取出行,再根据行循环取出列
			for (int i = 1; i <= totalRows; i++) {
				row = sheet.getRow(i);
				List object = new ArrayList();
				// 处理空行
				if (row == null) {
					// object.add("");
					continue;
				}
				// 总共有多少列,从0开始
				for (int j = row.getFirstCellNum(); j < totalCells; j++) {
					// 处理空列
					if (row.getCell(j) == null) {
						object.add("");
						continue;
					}
					//获取单元格内容
					try 
					{
						if(j==13 || j==14 || j==15 || j==16){	
							
							cell = this.getValue((HSSFCell) row.getCell(j),2);		
						}else{
							
							cell = this.getValue((HSSFCell) row.getCell(j),0);
						}									
					}catch (Exception e) 
					{   
						cell = row.getCell(j).toString();
					}
					object.add(cell.trim());
					
				}
				inputlist.add(object);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return inputlist;
	}
	
	//Excel格式转换
	@SuppressWarnings("static-access")
	private String getValue(HSSFCell xssfCell, int length) {
		String style = "0";
		for(int i=0; i<length;i++){
			if(i==0){
				style=style+".";
			}
			style=style+"0";
		}
		if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
			DecimalFormat df = new DecimalFormat(style);
			String whatYourWant = df.format(xssfCell.getNumericCellValue());
			return whatYourWant;
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_STRING) {
			return String.valueOf(xssfCell.getStringCellValue());
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else {
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}
	
	
	public String changeType(String var) throws Exception {
		String result="";
		try{	
			Float temp =Float.parseFloat(var);
			int tempInt =temp.intValue();
			if(var.charAt(0)=='0'){
				result=var;
			}else{
				if(var.length()!=String.valueOf(temp).length()){
				
					result=var;
				}else{
					result=String.valueOf(tempInt);	
				}	
			}		
		}catch(Exception e){
			result=var;
		}		
		return result;
	}
	//审空单
	@Override
	public MsgRes tscEmptyList(String strEnterpriseNo, String strWarehouseNo,
				String wheresql) throws Exception {
		List<Ridata_UntreadMModel> str=JSON.parseArray(wheresql, Ridata_UntreadMModel.class);
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
			
		outList.add("S");

		List  inList=new ArrayList();
			
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(str.get(0).getOwnerNo());//strOwnerNo
		inList.add(str.get(0).getSUntreadNo());//strSUntreadNo
		inList.add(str.get(0).getUntreadNo());//strUntreadNo
		inList.add(str.get(0).getUpdtName());//strUserId 
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_RIDATA.P_ComfireEmptyList");
		System.out.println(resultList.get(0).toString());

		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		
		return new MsgRes(true,"审核成功！","");
	}
	
	//根据返配单号获得验收量   8-17
	@Override
	public MsgRes getCheckNumber(String strEnterpriseNo, String strWareNo,
			String untreadNo) throws Exception {
		String sql="select sum(m.check_qty) inportQty from ridata_untread_d m where 1=1 and " +
				"m.untread_no='"+untreadNo+"' " +
				"and m.enterprise_no='"+strEnterpriseNo+"' " +
				"and m.warehouse_no='"+strWareNo+"'";
		List<Double> list = genDao.getListByNativeSql(sql);
		System.out.println("验收量: " + list.get(0));
		
		return new MsgRes(true,"",list.get(0));
	}
}
















