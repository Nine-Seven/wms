package com.sealinkin.rodata.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.rodata.model.Rodata_RecedeMModel;
import com.sealinkin.rodata.po.Rodata_RecedeM;
import com.sealinkin.rodata.po.Rodata_RecedeTmp;
import com.sealinkin.rodata.po.Rodata_RecedeTmpId;
import com.sealinkin.rodata.service.IRodata_RecedeMTTHService;
import com.sealinkin.stock.model.Stock_ContentModel;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.ContextUtil;
import com.sealinkin.util.FileUtilSys;
import com.sealinkin.util.TipUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Rodata_RecedeMTTHImpl implements IRodata_RecedeMTTHService {
	private IGenericManager genDao;
	private static final Logger logger = Logger.getLogger(Rodata_RecedeMImpl.class);
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	@Override
	public List<ComboxBo> getSupplierNo(String enterpriseNo,String workerOwner,
			String warehouseNo,String strQuery)
			throws Exception {
		String strSql="select distinct m.supplier_no value,bds.supplier_name text,"+
			"'['|| ltrim(m.supplier_no)||']'||bds.supplier_name dropValue,m.po_type "+
			" from rodata_recede_m m,bdef_defsupplier bds "+
			" where m.owner_no=bds.owner_no and m.supplier_no=bds.supplier_no " +
			" and m.enterprise_no=bds.enterprise_no "+
//			" and m.class_type = '0' "+
			" and m.enterprise_no ='"+enterpriseNo+
			"' and m.warehouse_no ='"+warehouseNo+
			"' and m.STATUS NOT IN ('13', '16','14') ";

		if(workerOwner!=null && !workerOwner.equals(""))
		{
			strSql=strSql+" and m.owner_no in("+workerOwner+") ";
		}
		if(strQuery!=null && !strQuery.equals(""))
		{

			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+ws;
		}
		
		strSql=strSql+"  order by m.supplier_no ";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	
	}
	//获取退货单类型
	@Override
	public List<ComboxBo> getRecede_type(String enterpriseNo,
			String workerOwner, String warehouseNo)
			throws Exception {
		String strSql="select distinct m.recede_type value,f.text text,"+
				"'['|| ltrim(m.recede_type)||']'||f.text dropValue "+
		        " from rodata_recede_m m,wms_deffieldval f  "+
		        " where m.recede_type=f.value " +
		        " and f.table_name='RODATA_RECEDE_M' and f.colname='RECEDE_TYPE' "+
				" and m.enterprise_no ='"+enterpriseNo+"' " +
				" and m.warehouse_no ='"+warehouseNo+"' " +
				" and m.STATUS <'13'  ";

			if(workerOwner!=null && !workerOwner.equals(""))
			{
				strSql=strSql+" and m.owner_no in("+workerOwner+") ";
			}
			
			List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
			return (List<ComboxBo>) list;
	}
	@Override
	public ExtListDataBo<Stock_ContentModel> getRodateRecedeList(String enterpriseNo,String warehouseNo,String str)
			throws Exception {
		
		List<Stock_ContentModel> list=new ArrayList<Stock_ContentModel>();
		int count=0;
		
		
		if(str!=null && str!=null){
			String str1[]=str.split(",");
			for(int i=0; i<str1.length; i++){
				
				String sql="select '"+str1[i].trim()+"' as labelNo, rrd.article_no,rrd.packing_qty,bd.owner_article_no,bd.article_name," +
						"(rrd.recede_qty-rrd.locate_qty) recede_qty,rrd.warehouse_no,rrd.owner_no,"+
						"f_get_packingunit(rrd.enterprise_no,rrd.owner_no,rrd.article_no,rrd.packing_qty) packingUnit,"+
						"f_get_spec(rrd.enterprise_no,rrd.owner_no,rrd.article_no,rrd.packing_qty) packingSpec,"+
						
						"trunc((rrd.recede_qty-rrd.locate_qty)/rrd.packing_qty) as planBox,"+
						"trunc(mod((rrd.recede_qty-rrd.locate_qty),rrd.packing_qty)/bd.QMIN_OPERATE_PACKING) as planQmin,"+
						"((rrd.recede_qty-rrd.locate_qty) - trunc((rrd.recede_qty-rrd.locate_qty)/rrd.packing_qty) * rrd.packing_qty - trunc(mod((rrd.recede_qty-rrd.locate_qty),rrd.packing_qty)/bd.QMIN_OPERATE_PACKING) * bd.QMIN_OPERATE_PACKING) as planDis,"+
											
						"trunc((nvl(a.qty, 0))/rrd.packing_qty) as realBox,"+
						"trunc(mod((nvl(a.qty, 0)),rrd.packing_qty)/bd.QMIN_OPERATE_PACKING) as realQmin,"+
						"((nvl(a.qty, 0)) - trunc((nvl(a.qty, 0))/rrd.packing_qty) * rrd.packing_qty - trunc(mod((nvl(a.qty, 0)),rrd.packing_qty)/bd.QMIN_OPERATE_PACKING) * bd.QMIN_OPERATE_PACKING) as realDis,"+
						
				"(nvl(a.qty, 0)) as available_qty,rrd.packing_qty,"+
				"case when nvl(a.qty,0)-(rrd.recede_qty - rrd.locate_qty)<0 then ((rrd.recede_qty - rrd.locate_qty)-nvl(a.qty,0))/rrd.packing_qty  else 0 end as no_enough_qty,sum(rrd.budget_qty)/rrd.packing_qty as budegtQty" +
				" from  "+
				"(select sc.enterprise_no,sc.warehouse_no, sc.packing_qty,sc.article_no,sum(sc.qty-sc.outstock_qty) qty " +
				" from stock_content sc,cdef_defcell cd,cdef_defarea t,cdef_defware cdw,rodata_recede_m rrm "+
				" where sc.enterprise_no=cd.enterprise_no " +
				" and sc.warehouse_no=cd.warehouse_no " +
				" and sc.cell_no=cd.cell_no "+
				" and cd.enterprise_no=t.enterprise_no "+
				" and cd.warehouse_no=t.warehouse_no " +
				" and t.enterprise_no=cdw.enterprise_no " +
				" and t.warehouse_no=cdw.warehouse_no " +
				" and t.ware_no=cdw.ware_no " +
				" and cdw.enterprise_no=rrm.enterprise_no " +
				" and cdw.warehouse_no=rrm.warehouse_no " +
				" and cdw.org_no=rrm.org_no  and t.area_attribute<>'1'  " +
				" and rrm.recede_no='"+str1[i].trim()+"' "  +
				" and cd.enterprise_no='" +enterpriseNo+"' "+
				" and cd.warehouse_no='"+warehouseNo+"' "+
				" and cd.ware_no||cd.area_no=t.ware_no||t.area_no "+
				" and cd.cell_status<>'1' and sc.qty-sc.outstock_qty>0 "+
				" and ((sc.stock_type='1' and sc.stock_value='N') or "+
				" (sc.stock_type='3' and sc.stock_value='"+str1[i].trim()+"'))"+
				" group by sc.enterprise_no, sc.warehouse_no,sc.article_no,sc.packing_qty) a,rodata_recede_d rrd,bdef_defarticle bd "+
				" where a.enterprise_no(+)=rrd.enterprise_no  and rrd.enterprise_no = bd.enterprise_no " +
				" and a.warehouse_no(+)=rrd.warehouse_no and a.article_no(+)=rrd.article_no and rrd.packing_qty=a.packing_qty(+) "+
				" and rrd.article_no=bd.article_no "+
				" and rrd.recede_no='"+str1[i].trim()+"' " +
				" and rrd.enterprise_no='"+enterpriseNo+"' " +
				" group by rrd.article_no,bd.QMIN_OPERATE_PACKING,bd.owner_article_no,bd.article_name,rrd.packing_qty,(rrd.recede_qty-rrd.locate_qty)," +
				"          rrd.warehouse_no,rrd.enterprise_no,rrd.owner_no, nvl(a.qty,0), " +
				"          case  when nvl(a.qty,0)-(rrd.recede_qty - rrd.locate_qty)<0 then (rrd.recede_qty - rrd.locate_qty)-nvl(a.qty, 0) else 0 end ";				
				List<Stock_ContentModel> list1=genDao.getListByNativeSql(sql, Stock_ContentModel.class);	
			 
				for(int j=0; j<list1.size();j++){	
					list.add(count++, list1.get(j));
				} 
			}	
			
		  	 ExtListDataBo<Stock_ContentModel> extListBo = new ExtListDataBo<Stock_ContentModel>(list, 0);
			 return extListBo;
		}else{
			return null;
		}		
	}
	@Override
	public MsgRes send(String enterpriseNo,String workerNo, String warehouseNo,
			String workSpaceNo,String str,String cellNo)
			throws Exception {
		List<Rodata_RecedeMModel> str1=JSON.parseArray(str, Rodata_RecedeMModel.class);
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		
		for (int i=0; i<str1.size();i++) {
			List  inList=new ArrayList();
			
			inList.add(enterpriseNo);
			inList.add(warehouseNo);
			inList.add(str1.get(i).getOwnerNo());//strOwnerNo
			inList.add(str1.get(i).getRecedeNo());//strRecedeNo
			inList.add(str1.get(i).getSupplierNo());//strSupplierNo
			inList.add(str1.get(i).getClassType());//strClassType
			inList.add(workerNo);
			inList.add(workSpaceNo);
			inList.add(cellNo);
			
			System.out.println(inList);
			resultList=genDao.execProc(inList, outList, "PKLG_ROLOCATE.P_Special_LOCATEAndTask");
			if(resultList.get(1).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(1).toString());
			}
		}
		return new MsgRes(true,TipUtil.getTipsByKey("E25001"),null);//瀹氫綅鎴愬姛锛?
	}
	@Override
	public ExtListDataBo<Rodata_RecedeMModel> getRodata_RecedeM(
			String enterpriseNo,String warehouseNo, String workerOwner,
			String strQuery,String str) throws Exception {
		String sql= "select distinct m.wave_no waveNo ,m.*,"
		+"                 o.owner_name," 
		+"                 '['|| ltrim(m.supplier_no)||']'||bds.supplier_name as supplier_name,"
		+"                 fv.text as status_desc,"
		+" f_get_fieldtext('RODATA_RECEDE_M','STATUS',m.status) as statusText,"
		+"                 dv.text as class_TYPE_desc,"
		+"                 df.text as PO_TYPE_desc,"
		+"                 dfv.text as RECEDE_TYPE_desc"
		+"   from rodata_recede_m     m,"
		+"        bdef_defowner     o,"
		+"        WMS_DEFFIELDVAL fv,"
		+"        WMS_DEFFIELDVAL dv,"
		+"        WMS_DEFFIELDVAL df,"
		+"        WMS_DEFFIELDVAL dfv," 
		+"        bdef_defsupplier bds"
		+"  where m.owner_no = o.owner_no"
		+"    and trim(nvl(m.status, '')) = fv.value(+)"
		+"    and upper(fv.table_name(+)) = 'RODATA_RECEDE_M'"
		+"    and upper(fv.colname(+)) = 'STATUS'"
		+"    and trim(nvl(m.class_type, '')) = dv.value(+)"
		+"    and upper(dv.table_name(+)) = 'RODATA_RECEDE_M'"
		+"    and upper(dv.colname(+)) = 'CLASS'"
		+"    and trim(nvl(m.PO_TYPE, '')) = df.value(+)"
		+"    and upper(df.table_name(+)) = 'RODATA_RECEDE_M'"
		+"    and upper(df.colname(+)) = 'PO_TYPE'"
		+"    and trim(nvl(m.RECEDE_TYPE, '')) = dfv.value(+)"
		+"    and upper(dfv.table_name(+)) = 'RODATA_RECEDE_M'"
		+"    and upper(dfv.colname(+)) = 'RECEDE_TYPE'" 
		+"    and m.owner_no=bds.owner_no " 
		+"    and m.supplier_no=bds.supplier_no"
		+"    and m.enterprise_no=bds.enterprise_no" +
		"     and m.enterprise_no = o.enterprise_no "
		+"    and m.enterprise_no = '"+enterpriseNo+"'"
		+"    and m.warehouse_no = '"+warehouseNo+"'"
//		+"    and m.class_type = '0'"
		+"    and m.owner_no in("+workerOwner+")"
		+"    and m.STATUS NOT IN ('14','13', '16')";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
		}
		if(str!=null && !str.equals("")){
			sql=sql+" and to_char(m.recede_date,'yyyy-mm-dd') = '"+str+"'   ";
		}
		sql=sql+"  order by m.supplier_no,m.RGST_DATE desc";
  	   List<Rodata_RecedeMModel> list=genDao.getListByNativeSql(sql, Rodata_RecedeMModel.class);
  	   ExtListDataBo<Rodata_RecedeMModel> extListBo = new ExtListDataBo<Rodata_RecedeMModel>(list, 0);
  	   logger.info("leval queryRodata_Recede_MModel");
  	   return extListBo;
	}

	//获取定位次数
	@Override
	public List<ComboxBo> getLocateTime(String enterpriseNo,
			String workerOwner, String warehouseNo, String strQuery) {

		String strSql="select distinct m.locate_times value,m.locate_times text,"+
			          "       m.locate_times dropValue  "+
			          "  from rodata_recede_m m "+
			          " where m.enterprise_no ='"+enterpriseNo+"' "+
			          "   and m.warehouse_no ='"+warehouseNo+"' "+
			          "   and m.STATUS NOT IN ('13', '16','14') ";

		System.out.println(strSql);
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			strSql=strSql+" and m.owner_no in("+workerOwner+") ";
		}
		if(strQuery!=null && !strQuery.equals(""))
		{

			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+ws;
		}
		
		strSql=strSql+"  order by m.locate_times ";

		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	
	
	}
	
	//退单审核
	@Override
	public MsgRes tscReviewComfir(String workerNo, String workSpaceNo,
			String jsonMaster) throws Exception {
		Rodata_RecedeM recedeM=(Rodata_RecedeM)JSON.parseObject(jsonMaster,Rodata_RecedeM.class);
		
		List  inList=new ArrayList();
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(recedeM.getId().getEnterpriseNo());
		inList.add(recedeM.getId().getWarehouseNo());
		inList.add(recedeM.getId().getOwnerNo());
		inList.add(recedeM.getId().getRecedeNo());
		inList.add(recedeM.getSupplierNo());
		inList.add(recedeM.getClassType());
		inList.add(workerNo);
		inList.add(workSpaceNo);
			
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_ROLOCATE.P_reviewRecedeNo");
		if(resultList.get(1).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(1).toString());
		}
		
		return new MsgRes(true,"审核成功",null);//审核成功

	}
	@Override
	public List<ComboxBo> getCdef_DefCellCombo(String enterpriseNo,
			String warehouseNo, String strJson, String str, int i, int j)
			throws Exception {
		String sql = "select a.cell_no value,a.cell_no text,a.cell_no dropValue  "
				+ "from Cdef_Defcell a ,Cdef_Defarea b "
				+ " where a.cell_status<>1 "
				+ " and a.ware_no = b.ware_no "
				+ " and a.area_no=b.area_no "
				+ " and a.warehouse_No='" + warehouseNo + "' "
				+ " and a.warehouse_no=b.warehouse_no "
				+ " and a.enterprise_no='" + enterpriseNo + "' "
				+ " and a.enterprise_no=b.enterprise_no "
				+ " and b.area_attribute=0 "
				+ " and b.area_usetype in ('1','5','6') ";
				
		if (strJson != null && !strJson.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strJson);
			sql = sql + ws;
		}
		if (str != null && !str.equals("")) {
			sql = sql + "and a.cell_no like '%" + str + "%'";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	@Override
	public MsgRes checkCell(String enterpriseNo, String warehouseNo,
			String ownerNo, String recedeNo, String cellNo) throws Exception {
		List  inList=new ArrayList();
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(enterpriseNo);
		inList.add(warehouseNo);
		inList.add(ownerNo);
		inList.add(recedeNo);
		inList.add(cellNo);
			
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "pklg_rodata.P_CheckLocateCell");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		
		return new MsgRes(true,"审核成功",null);//审核成功
	}
		

	/**
	 * 上传Excel导入数据库
	 */
	@Override
	public MsgRes upLoad(File file,String strWarehouseNo,String strEnterpriseNo,String workNo) throws Exception {
		
		FileUtilSys.writeFile(file, "RodataTmp.xlsx", ContextUtil.getWebRootPath()+"uploadFiles\\temp\\");
	
		List<Rodata_RecedeTmp> list  = changeexcelBean(ContextUtil.getWebRootPath()+"uploadFiles\\temp\\"+"RodataTmp.xlsx",strWarehouseNo,strEnterpriseNo);
			
		genDao.saveList(list);
		genDao.flush();
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(workNo);
		resultList=genDao.execProc(inList, outList, "pkobj_Create_base.P_rodata_recede");

		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		
		return new MsgRes(true,resultList.get(0).toString(),"");
	}
	
	/**
	 * Excel数据转List
	 */
	@Override
	public List<Rodata_RecedeTmp> changeexcelBean(String fileName,String strWarehouseNo,String strEnterpriseNo)throws Exception {
		List<Rodata_RecedeTmp> iitList = new ArrayList<Rodata_RecedeTmp>();
		
		//导入前删除临时表数据
		String delsql=" delete from rodata_recede_tmp a " +
				      "  where a.enterprise_no='"+strEnterpriseNo+"' " +
				      "    and a.warehouse_no='"+strWarehouseNo+"' ";
		genDao.updateBySql(delsql);
		
		List<List> excelList = this.impExcel(fileName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for (int i = 0; i < excelList.size(); i++) {
			if(excelList.get(i).get(0).toString()!=null && !excelList.get(i).get(0).toString().equals("")){
				Rodata_RecedeTmp po = new Rodata_RecedeTmp();
				Rodata_RecedeTmpId id = new Rodata_RecedeTmpId();
							
				id.setEnterpriseNo(strEnterpriseNo);
				id.setWarehouseNo(strWarehouseNo);
				
				id.setRowId(Double.parseDouble(excelList.get(i).get(0).toString()));
				id.setPoNo(changeType(excelList.get(i).get(1).toString()));
				id.setOwnerNo(changeType(excelList.get(i).get(2).toString()));
				id.setSupplierNo(changeType(excelList.get(i).get(3).toString()));
				id.setPoType(excelList.get(i).get(4).toString());
				id.setRecedeDate(sdf.parse(excelList.get(i).get(5).toString()));
                id.setRequestDate(sdf.parse(excelList.get(i).get(6).toString()));
                id.setOrgNo(changeType(excelList.get(i).get(7).toString()));
                
                id.setTakeType(changeType(excelList.get(i).get(8).toString()).equals("")?"0":excelList.get(i).get(8).toString());

				id.setClassType(changeType(excelList.get(i).get(9).toString()));
				id.setOwnerArticleNo(changeType(excelList.get(i).get(10).toString()));
				id.setPackingQty(Double.parseDouble(excelList.get(i).get(11).toString().equals("")?"1":excelList.get(i).get(11).toString()));
				id.setPoQty(Double.parseDouble(excelList.get(i).get(12).toString().equals("")?"0":excelList.get(i).get(12).toString())*Double.parseDouble(excelList.get(i).get(11).toString().equals("")?"1":excelList.get(i).get(11).toString())+Double.parseDouble(excelList.get(i).get(13).toString().equals("")?"0":excelList.get(i).get(13).toString()));
				id.setUnitCost(Double.parseDouble(excelList.get(i).get(14).toString().equals("")?"0":excelList.get(i).get(14).toString()));
				id.setRecedeRemark(changeType(excelList.get(i).get(15).toString()));
				id.setRsvVarod1(changeType(excelList.get(i).get(16).toString().equals("")?"":excelList.get(i).get(16).toString()));
				id.setRsvVarod2(changeType(excelList.get(i).get(17).toString().equals("")?"":excelList.get(i).get(17).toString()));
				id.setRsvVarod3(changeType(excelList.get(i).get(18).toString().equals("")?"":excelList.get(i).get(18).toString()));
				id.setRsvVarod4(changeType(excelList.get(i).get(19).toString().equals("")?"":excelList.get(i).get(19).toString()));
				id.setRsvVarod5(changeType(excelList.get(i).get(20).toString().equals("")?"":excelList.get(i).get(20).toString()));
				id.setRsvVarod6(changeType(excelList.get(i).get(21).toString().equals("")?"":excelList.get(i).get(21).toString()));
				id.setRsvVarod7(changeType(excelList.get(i).get(22).toString().equals("")?"":excelList.get(i).get(22).toString()));
				id.setRsvVarod8(changeType(excelList.get(i).get(23).toString().equals("")?"":excelList.get(i).get(23).toString()));
				id.setRsvNum1(Double.parseDouble(excelList.get(i).get(24).toString().equals("")?"0":excelList.get(i).get(24).toString()));
				id.setRsvNum2(Double.parseDouble(excelList.get(i).get(25).toString().equals("")?"0":excelList.get(i).get(25).toString()));
				id.setRsvNum3(Double.parseDouble(excelList.get(i).get(26).toString().equals("")?"0":excelList.get(i).get(26).toString()));
				id.setRsvDate1(sdf.parse(excelList.get(i).get(27).toString().equals("")?"1900-01-01":excelList.get(i).get(27).toString()));
				id.setRsvDate2(sdf.parse(excelList.get(i).get(28).toString().equals("")?"1900-01-01":excelList.get(i).get(28).toString()));
				id.setRsvDate3(sdf.parse(excelList.get(i).get(29).toString().equals("")?"1900-01-01":excelList.get(i).get(29).toString()));
			
				id.setStockType("1");
				id.setStockValue("N");
				id.setDeptNo("N");
				
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
						if(j==14 || j==11 || j==12 || j==13){	
							
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
	
	
	//取消订单     7-14
		@Override
		public MsgRes cancelOrder(String enterpriseNo, String warehouseNo,
				String workerNo, String recedeNo) throws Exception {
			//锁单
			String sql1="update rodata_recede_m a set a.status =a.status " +
					"where a.recede_no='"+recedeNo+
					"' and a.warehouse_no='"+warehouseNo+
					"' and a.enterprise_no='"+enterpriseNo+"' ";
			genDao.updateBySql(sql1);
			
			String sql="update rodata_recede_m a set a.status =16, " +
					"a.updt_name='"+workerNo+"', " +
					"a.updt_date=sysdate " +
					"where a.recede_no='"+recedeNo+
					"' and a.warehouse_no='"+warehouseNo+
					"' and a.enterprise_no='"+enterpriseNo+"'" +
					"  and a.status=10";
			genDao.updateBySql(sql);
			
			return new MsgRes(true,TipUtil.getTipsByKey("E30003"),recedeNo);//操作成功！;
		}
}













