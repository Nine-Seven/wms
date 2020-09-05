package com.sealinkin.idata.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.DocumentTypeModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.IGetSystemParameterService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.idata.model.Idata_ImportDModel;
import com.sealinkin.idata.model.Idata_ImportMModel;
import com.sealinkin.idata.po.Idata_ImportAllot;
import com.sealinkin.idata.po.Idata_ImportD;
import com.sealinkin.idata.po.Idata_ImportM;
import com.sealinkin.idata.po.Idata_ImportTmp;
import com.sealinkin.idata.po.Idata_ImportTmpId;
import com.sealinkin.idata.service.Iidata_ImPort;
import com.sealinkin.report.conf.ReportDefine;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.ContextUtil;
import com.sealinkin.util.FileUtilSys;
import com.sealinkin.wms.model.Wms_IDataTypeModel;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Idata_ImPortImpl implements Iidata_ImPort {
	
    
	private IGenericManager genDao;
	private IGetSystemParameterService getSystemParameterImpl;
     
	public IGenericManager getGenDao()
    {
        return genDao;
    }
	public void setGenDao(IGenericManager genDao)
    {
        this.genDao = genDao;
    }

	
    public IGetSystemParameterService getGetSystemParameterImpl() {
		return getSystemParameterImpl;
	}
	public void setGetSystemParameterImpl(
			IGetSystemParameterService getSystemParameterImpl) {
		this.getSystemParameterImpl = getSystemParameterImpl;
	}
	/**
     * 进货通知单列
     */
	@Override
	public ExtListDataBo<Idata_ImportMModel> getImPort_MList(
			String strEnterpriseNo,
			String warehouseNo,String workerOwner,
			String queryStr,PageBo pageBo)throws Exception {
		String sql="select distinct  a.import_no,s.s_import_no," +
				"a.owner_no,b.owner_name,a.org_no,a.take_Type," +
				"a.supplier_no,c.supplier_name," +
				//"a.po_no," +
				//huangb 20160721
				"nvl(a.rsv_varod2,a.po_no) po_no," +
				"a.order_date,a.request_date," +
				"a.status,a.order_end_date,a.po_type,a.class_type," +
				"a.create_flag,a.end_date ,a.import_type,a.rsv_varod1, " +
				"f_get_fieldtext('N','STATUS',a.status)statusText," +
				"f_get_fieldtext('IDATA_IMPORT_M','PO_TYPE',a.po_type)potypeText," +
				"a.rgst_name,a.rgst_date,a.updt_name,a.updt_date,a.import_remark," +
				"(select sum(iid.po_qty) from idata_import_d iid " +
				"where iid.enterprise_no=a.enterprise_no " +
				"and iid.warehouse_no=a.warehouse_no " +
				"and iid.import_no=a.import_no " +
				"and iid.owner_no=a.owner_no) qty " +
				"from idata_import_m a,bdef_defowner b," +
				"bdef_defsupplier c,idata_import_sm s " +
				"where a.owner_no=b.owner_no " +
				"and a.warehouse_no=s.warehouse_no(+) " +
				"and a.import_no=s.import_no(+) " +
				"and a.enterprise_no=c.enterprise_no " +
				"and a.owner_no=c.owner_no " +
				"and a.supplier_no=c.supplier_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.enterprise_no=c.enterprise_no " +
				"and a.enterprise_no=s.enterprise_no(+) " +
				"and a.warehouse_no='"+warehouseNo+"' " +
				"and a.enterprise_no='"+strEnterpriseNo+"'" ;		
//        String totsql = "select count(1) " +
//        		"from idata_import_m a,bdef_defowner b," +
//				"bdef_defsupplier c,idata_import_sm s " +
//				"where a.owner_no=b.owner_no " +
//				"and a.warehouse_no=s.warehouse_no(+) " +
//				"and a.import_no=s.import_no(+) " +
//				"and a.supplier_no=c.supplier_no " +
//				"and a.enterprise_no=b.enterprise_no " +
//				"and a.enterprise_no=c.enterprise_no " +
//				"and a.enterprise_no=s.enterprise_no(+) " +
//				"and a.warehouse_no='"+warehouseNo+"' " +
//				"and a.enterprise_no='"+strEnterpriseNo+"'" ;	
        if(workerOwner!=null && !workerOwner.equals(""))
		{
			sql=sql+" and a.owner_no in("+workerOwner+") ";
//			totsql=totsql+"and a.owner_no in("+workerOwner+")";
		}else{
			sql=sql+" and 1=2";
//			totsql=totsql+" and 1=2";
		}
        
        if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
//			totsql=totsql+ws;
			if(ws.indexOf("a.status")==-1){
				sql+=" and a.status<>13 and a.status<>16 ";
			}
		}else{
			sql+=" and a.status<>13 and a.status<>16 ";
//			totsql+=" and a.status<>13 and a.status<>16";
		}
        sql=sql+" order by a.owner_no,a.import_type,a.import_no desc ";
		String totsql = "select count(*) from (" + sql + ") " ;	
		List<Idata_ImportMModel> list = genDao.getListByNativeSql(sql,Idata_ImportMModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Idata_ImportMModel> extListBo= new ExtListDataBo<Idata_ImportMModel>(list, count);
        return extListBo;
	}
	
	/** 
	 * 进货通知单明细
	 */
	@Override
	public ExtListDataBo<Idata_ImportDModel> getImPort_DList(String strEnterpriseNo,
			String wheresql,PageBo pageBo)throws Exception {
		String sql="";
		Integer count=0;
		String str[]=wheresql.split(",");
		if(str[1].trim().equals("IS")){
			sql="select a.import_no,a.owner_no,a.article_no,c.owner_article_no,c.article_name,c.barcode," +
			"a.packing_qty,c.unit_packing,c.qmin_operate_packing" +
			//"nvl(e.packing_unit, decode(a.packing_qty,c.qmin_operate_packing,c.qmin_operate_packing_unit,c.unit)) packing_unit,"+
			//"nvl(e.spec, '1*' || a.packing_qty || c.unit) spec," +
			"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"+
			"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,c.qmin_operate_packing) packingUnitQmin,"+
			"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,c.unit_packing) Unit,"+
			"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"+
			"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,c.qmin_operate_packing) packingSpecQmin,"+
			"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,c.unit_packing) spec,"+
			"a.status,nvl(b.rsv_varod2,b.po_no) po_no," +
			"trunc(a.po_qty/a.packing_qty) as planBox,"+
			"trunc(mod(a.po_qty,a.packing_qty)/c.QMIN_OPERATE_PACKING) as planQmin,"+
			"(a.po_qty - trunc(a.po_qty/a.packing_qty) * a.packing_qty - trunc(mod(a.po_qty,a.packing_qty)/c.QMIN_OPERATE_PACKING) * c.QMIN_OPERATE_PACKING) as planDis," +
			//新增是否允许差异确认标识(0-不允许;1-允许) huangb 20160721
			"(case when b.send_flag = '13' and b.po_no = b.rsv_varod2 then '1' else '0' end) isDiffConfirmFlag,"+
			"a.unit_cost " +
			"from idata_import_d a,idata_import_m b,bdef_defarticle c," +
			"bdef_article_packing e  " +
			"where a.import_no=b.import_no and a.article_no=c.article_no " +
			"and a.article_no=e.article_no(+) " +
			"and a.packing_qty=e.packing_qty(+) " +
			"and a.enterprise_no=b.enterprise_no and a.enterprise_no=c.enterprise_no " +
			"and a.enterprise_no=e.enterprise_no(+) " +
			"and a.enterprise_no='"+strEnterpriseNo+"' " +
			"and a.import_no='"+str[0].trim()+"' ";
		}else if(str[1].trim().equals("ID")){
			sql="select a.article_no,b.article_name, b.owner_article_no,b.barcode,a.cust_no," +
			"d.cust_name,a.packing_qty,b.unit_packing,b.qmin_operate_packing" +
			//"nvl(e.packing_unit, decode(a.packing_qty,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit)) packing_unit,	"+	
			//"nvl(e.spec, '1*' || a.packing_qty || b.unit) spec," +
			"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"+
			"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingUnitQmin,"+
			"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) Unit,"+
			"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"+
			"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingSpecQmin,"+
			"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) spec,"+
			"nvl(m.rsv_varod2,m.po_no) po_no," +
			"trunc(a.po_qty/a.packing_qty) as planBox,"+
			"trunc(mod(a.po_qty,a.packing_qty)/b.QMIN_OPERATE_PACKING) as planQmin,"+
			"(a.po_qty - trunc(a.po_qty/a.packing_qty) * a.packing_qty - trunc(mod(a.po_qty,a.packing_qty)/b.QMIN_OPERATE_PACKING) * b.QMIN_OPERATE_PACKING) as planDis," +
			//新增是否允许差异确认标识(0-不允许;1-允许) huangb 20160721
			"(case when m.send_flag = '13' and m.po_no = m.rsv_varod2 then '1' else '0' end) isDiffConfirmFlag,"+
			"id.unit_cost " +
			"from idata_import_allot a,idata_import_d id,bdef_defarticle b ," +
			"bdef_defcust d," +
			"bdef_article_packing e,idata_import_d m  " +
			"where m.enterprise_no = id.enterprise_no and m.warehouse_no = id.warehouse_no and m.owner_no = id.owner_no and m.import_no = id.import_no" +
			"and a.cust_no=d.cust_no " +
			"and a.article_no=e.article_no(+) and  a.packing_qty=e.packing_qty(+) " +
			"and a.enterprise_no=b.enterprise_no and a.article_no=b.article_no " +
			"and a.enterprise_no=d.enterprise_no and a.enterprise_no=e.enterprise_no(+) " +
			"and a.enterprise_no=id.enterprise_no and a.article_no=id.article_no " +
			"and a.import_no=id.import_no and a.packing_qty=id.packing_qty " +
			"and a.enterprise_no='"+strEnterpriseNo+"' " +
			"and a.import_no='"+str[0].trim()+"' ";
		}
		List<Idata_ImportDModel> list = genDao.getListByNativeSql(sql,Idata_ImportDModel.class,pageBo.getStart(), 1000);
		ExtListDataBo<Idata_ImportDModel> extListBo= new ExtListDataBo<Idata_ImportDModel>(list, count);
        return extListBo;
	}
	
	
	/**
	 * 保存存储
	 */
	@Override
	public MsgRes saveImPort(String jsonMaster, String jsonDetail)throws Exception {
		Idata_ImportM importM=(Idata_ImportM)JSON.parseObject(jsonMaster,Idata_ImportM.class);
		List<Idata_ImportD> imPortD=JSON.parseArray(jsonDetail,Idata_ImportD.class);
		
		String importNo="";
		String sql="select * from idata_import_m m where 1=1 and " +
						"m.warehouse_no='"+importM.getId().getWarehouseNo()+"' " +
						"and m.enterprise_no='"+importM.getId().getEnterpriseNo()+"' " +
						"and m.import_no='"+importM.getId().getImportNo()+"'";
		List<Idata_ImportM> list = genDao.getListByNativeSql(sql,Idata_ImportM.class,0, 2000);
		if(list.size()==0){
			importM.setUpdtDate(null);
			importM.setUpdtName(null);
		}else{
			importM.setRgstDate(list.get(0).getRgstDate());
			importM.setRgstName(list.get(0).getRgstName());
		}
		if(importM.getId().getImportNo().equals("保存时自动生成")){
			List<Comparable> inList2=new ArrayList<Comparable>();
			List<String> outList2=new ArrayList<String>();
			List resultList2=new ArrayList();
			
			inList2.add(imPortD.get(0).getId().getEnterpriseNo());
			inList2.add(imPortD.get(0).getId().getWarehouseNo());
			inList2.add(DocumentTypeModel.IDATAIMPORTIS);
			outList2.add("S");
			outList2.add("S");
			resultList2=genDao.execProc(inList2, outList2, "PKLG_WMS_BASE.p_getsheetno");
			if(resultList2.get(0).toString().indexOf("N|")!=-1){
				throw new Exception();
			}
			importNo=resultList2.get(0).toString();
			importM.getId().setImportNo(importNo);
			for(Idata_ImportD d:imPortD){
				d.getId().setImportNo(importNo);
			}
			this.genDao.saveOrUpdateObj(importM);
			this.genDao.saveList(imPortD);
			this.genDao.flush();
			//集单
			tscidataOrderTime(importNo,jsonMaster);
		}else{
			String sqlBackUpOne=" insert into IDATA_IMPORT_M_LOG (warehouse_no,owner_no,import_type,import_no,po_type,po_no,"+
		              "supplier_no,order_date,request_date, status,create_flag,import_remark,end_date,receive_type,"+
		              "pay_type_name,order_type_name,lay_type,class_type,error_status,order_end_date,with_code_flag,"+
		              "stock_type,stock_value,quality,sample_rate,dept_no,send_flag,rgst_name,rgst_date,updt_name,updt_date,enterprise_no)"+
		              "select warehouse_no,owner_no,import_type,import_no,po_type,po_no,"+
		              "supplier_no,order_date,request_date, status,create_flag,import_remark,end_date,receive_type,"+
		              "pay_type_name,order_type_name,lay_type,class_type,error_status,order_end_date,with_code_flag,"+
		              "stock_type,stock_value,quality,sample_rate,dept_no,send_flag,rgst_name,rgst_date,updt_name,updt_date,enterprise_no "+
		              "from IDATA_IMPORT_M m where 1=1 and m.import_no='"+importM.getId().getImportNo()+"' and m.enterprise_no='"+importM.getId().getEnterpriseNo()+"' ";
			for (Idata_ImportD idata_ImportD : imPortD) {
				String sqlBackUpTwo="insert into IDATA_IMPORT_D_LOG(warehouse_no,owner_no,import_no,article_no," +
						"packing_qty,po_qty,import_qty,po_id,unit_cost,check_name,status,check_date,out_stock_flag," +
						"error_status,item_type,plan_across_qty,check_across_qty,qc_status,qc_flag,enterprise_no)" +
						"select warehouse_no,owner_no,import_no,article_no,packing_qty,po_qty,import_qty," +
						"po_id,unit_cost,check_name,status,check_date,out_stock_flag,error_status,item_type," +
						"plan_across_qty,check_across_qty,qc_status,qc_flag,enterprise_no from IDATA_IMPORT_D d " +
						"where 1=1 and d.import_no='"+idata_ImportD.getId().getImportNo()+"'" +
						" and d.article_no='"+idata_ImportD.getArticleNo()+"' " +
						"and d.warehouse_no='"+idata_ImportD.getId().getWarehouseNo()+"'" +
						"and d.owner_no='"+idata_ImportD.getId().getOwnerNo()+"' " +
						"and d.enterprise_no='"+idata_ImportD.getId().getEnterpriseNo()+"' ";
				this.genDao.updateBySql(sqlBackUpTwo);
			}
			this.genDao.updateBySql(sqlBackUpOne);
			importNo=importM.getId().getImportNo();
			this.genDao.saveOrUpdateObj(importM);
			String deleteSql="delete from idata_import_d d " +
					"where 1=1 and d.import_no='"+importM.getId().getImportNo()+"'" +
					"and d.warehouse_no='"+importM.getId().getWarehouseNo()+"'" +
					"and d.owner_no='"+importM.getId().getOwnerNo()+"' " +
					"and d.enterprise_no='"+importM.getId().getEnterpriseNo()+"' ";
			this.genDao.updateBySql(deleteSql);
			this.genDao.saveList(imPortD);
		}
		return new MsgRes(true, "数据保存成功！", importNo);
	}
	
	//保存直通（进货手建单）
	@Override
	public MsgRes saveImPort2(String jsonMaster, String jsonDetail,String jsonDetail2)
			throws Exception {
		Idata_ImportM importM=(Idata_ImportM)JSON.parseObject(jsonMaster, Idata_ImportM.class);
		List<Idata_ImportD> imPortD=JSON.parseArray(jsonDetail, Idata_ImportD.class);
		List<Idata_ImportAllot> imPortD2=JSON.parseArray(jsonDetail2, Idata_ImportAllot.class);
		
		String importNo="";
		String poNo = "";
		HashMap<String, String> map=new HashMap<String, String>();
		String sql="select * from idata_import_m m where 1=1 and " +
				"m.warehouse_no='"+importM.getId().getWarehouseNo()+"' " +
				"and m.import_no='"+importM.getId().getImportNo()+"' " +
				"and m.enterprise_no='"+importM.getId().getEnterpriseNo()+"'";
		List<Idata_ImportM> list = genDao.getListByNativeSql(sql,Idata_ImportM.class,0, 2000);
		
		if(list.size()==0){
			importM.setUpdtDate(null);
			importM.setUpdtName(null);
		}else{
			importM.setRgstDate(list.get(0).getRgstDate());
			importM.setRgstName(list.get(0).getRgstName());
		}
		if(importM.getId().getImportNo().equals("保存时自动生成")){
			List<Comparable> inList2=new ArrayList<Comparable>();
			List<String> outList2=new ArrayList<String>();
			List resultList2=new ArrayList();
			
			inList2.add(imPortD.get(0).getId().getEnterpriseNo());
			inList2.add(imPortD.get(0).getId().getWarehouseNo());
			inList2.add(DocumentTypeModel.IDATAIMPORTID);
			outList2.add("S");
			outList2.add("S");
			resultList2=genDao.execProc(inList2, outList2, "PKLG_WMS_BASE.p_getsheetno");
			if(resultList2.get(0).toString().indexOf("N|")!=-1){
				throw new Exception();
			}
			importNo=resultList2.get(0).toString();
			importM.getId().setImportNo(importNo);
			for(Idata_ImportD d:imPortD){
				d.getId().setImportNo(importNo);
			}
			for(Idata_ImportAllot a:imPortD2){
				a.getId().setImportNo(importNo);
				
				//获取出货单号
				String strCutNo=a.getId().getCustNo();
				if(map.get(strCutNo) == null){
					
					List<Comparable> inList3=new ArrayList<Comparable>();
					List<String> outList3=new ArrayList<String>();
					List resultList3=new ArrayList();
					
					inList3.add(imPortD.get(0).getId().getEnterpriseNo());
					inList3.add(imPortD.get(0).getId().getWarehouseNo());
					inList3.add(DocumentTypeModel.ODATAOE);
					outList3.add("S");
					outList3.add("S");
					resultList3=genDao.execProc(inList3, outList3, "PKLG_WMS_BASE.p_getsheetno");
					if(resultList3.get(0).toString().indexOf("N|")!=-1){
						throw new Exception();
					}
					poNo=resultList3.get(0).toString();
					map.put(a.getId().getCustNo(), poNo);
				}else
				{
					poNo=map.get(strCutNo);
				}
			}
		}else{
			importNo = importM.getId().getImportNo();
		    importM.getId().setImportNo(importNo);
		    for(Idata_ImportAllot a:imPortD2){
				a.getId().setImportNo(importNo);
				//获取出货单号
				String strCutNo=a.getId().getCustNo();
				if(map.get(strCutNo) == null){
					
					List<Comparable> inList3=new ArrayList<Comparable>();
					List<String> outList3=new ArrayList<String>();
					List resultList3=new ArrayList();
					
					inList3.add(imPortD.get(0).getId().getEnterpriseNo());
					inList3.add(imPortD.get(0).getId().getWarehouseNo());
					inList3.add(DocumentTypeModel.ODATAOE);
					outList3.add("S");
					outList3.add("S");
					resultList3=genDao.execProc(inList3, outList3, "PKLG_WMS_BASE.p_getsheetno");
					if(resultList3.get(0).toString().indexOf("N|")!=-1){
						throw new Exception();
					}
					poNo=resultList3.get(0).toString();
					map.put(a.getId().getCustNo(), poNo);
				}else
				{
					poNo=map.get(strCutNo);
				}
				a.getId().setPoNo(poNo);
			}	    
		    
		}
			
			this.genDao.saveOrUpdateObj(importM);
			this.genDao.saveList(imPortD);
			this.genDao.saveList(imPortD2);
		
		return new MsgRes(true, "数据保存成功！", importNo);
	}
	//天天惠直通（直通进货手建单）
	@Override
	public MsgRes saveImPortTth2(String jsonMaster, String jsonDetail,
			String jsonDetail2,String DockNo) throws Exception {
		Idata_ImportM importM=(Idata_ImportM)JSON.parseObject(jsonMaster, Idata_ImportM.class);
		List<Idata_ImportD> imPortD=JSON.parseArray(jsonDetail, Idata_ImportD.class);
		List<Idata_ImportAllot> imPortD2=JSON.parseArray(jsonDetail2, Idata_ImportAllot.class);
		String importNo="";
		//String poNo = "";
		//HashMap<String, String> map=new HashMap<String, String>();
		String sql="select * from idata_import_m m where 1=1 and " +
				"m.warehouse_no='"+importM.getId().getWarehouseNo()+"' " +
				"and m.import_no='"+importM.getId().getImportNo()+"' " +
				"and m.enterprise_no='"+importM.getId().getEnterpriseNo()+"'";
		List<Idata_ImportM> list = genDao.getListByNativeSql(sql,Idata_ImportM.class);
		
		if(list.size()==0){
			importM.setUpdtDate(null);
			importM.setUpdtName(null);
		}else{
			importM.setRgstDate(list.get(0).getRgstDate());
			importM.setRgstName(list.get(0).getRgstName());
		}
		if(importM.getId().getImportNo().equals("保存时自动生成")){
			List<Comparable> inList2=new ArrayList<Comparable>();
			List<String> outList2=new ArrayList<String>();
			List resultList2=new ArrayList();
			
			inList2.add(imPortD.get(0).getId().getEnterpriseNo());
			inList2.add(imPortD.get(0).getId().getWarehouseNo());
			inList2.add(DocumentTypeModel.IDATAIMPORTID);
			outList2.add("S");
			outList2.add("S");
			resultList2=genDao.execProc(inList2, outList2, "PKLG_WMS_BASE.p_getsheetno");
			if(resultList2.get(0).toString().indexOf("N|")!=-1){
				throw new Exception();
			}
			importNo=resultList2.get(0).toString();
			importM.getId().setImportNo(importNo);
			for(Idata_ImportD d:imPortD){
				d.getId().setImportNo(importNo);
			}
		 
			for(Idata_ImportAllot a:imPortD2){
				a.getId().setImportNo(importNo);
				//获取出货单号(现在由界面传入，所以屏蔽)
				/*String strCutNo=a.getId().getCustNo();
				if(map.get(strCutNo) == null){
					
					List inList3=new ArrayList();
					List outList3=new ArrayList();
					List resultList3=new ArrayList();
					
					inList3.add(imPortD.get(0).getId().getEnterpriseNo());
					inList3.add(imPortD.get(0).getId().getWarehouseNo());
					inList3.add(DocumentTypeModel.ODATAOE);
					outList3.add("S");
					outList3.add("S");
					resultList3=genDao.execProc(inList3, outList3, "PKLG_WMS_BASE.p_getsheetno");
					if(resultList3.get(0).toString().indexOf("N|")!=-1){
						throw new Exception();
					}
					poNo=resultList3.get(0).toString();
					map.put(a.getId().getCustNo(), poNo);
				}else
				{
					poNo=map.get(strCutNo);
				}
				a.getId().setPoNo(poNo);*/
			}
		
		}else{
			importNo = importM.getId().getImportNo();
		    importM.getId().setImportNo(importNo);
	 
		}
			
			this.genDao.saveOrUpdateObj(importM);
			this.genDao.saveList(imPortD);
			this.genDao.saveList(imPortD2);
			this.genDao.flush();
			//集单
			tscidataOrderTime(importNo,jsonMaster);
			return new MsgRes(true, "数据保存成功！", importNo);
	}
	//保存天天惠分播明细
	@Override
	public MsgRes saveImPortAllotTth(String importNo, String jsonDetail2)
			throws Exception {
		List<Idata_ImportAllot> imPortD2=JSON.parseArray(jsonDetail2, Idata_ImportAllot.class);
		//String poNo = "";
		//HashMap<String, String> map=new HashMap<String, String>();
		for(Idata_ImportAllot a:imPortD2){
				a.getId().setImportNo(importNo);
				/*//获取出货单号
				String strCutNo=a.getId().getCustNo();
				if(map.get(strCutNo) == null){
					
					List inList3=new ArrayList();
					List outList3=new ArrayList();
					List resultList3=new ArrayList();
					
					inList3.add(imPortD2.get(0).getId().getEnterpriseNo());
					inList3.add(imPortD2.get(0).getId().getWarehouseNo());
					inList3.add(DocumentTypeModel.ODATAOE);
					outList3.add("S");
					outList3.add("S");
					resultList3=genDao.execProc(inList3, outList3, "PKLG_WMS_BASE.p_getsheetno");
					if(resultList3.get(0).toString().indexOf("N|")!=-1){
						throw new Exception();
					}
					poNo=resultList3.get(0).toString();
					map.put(a.getId().getCustNo(), poNo);
				}else
				{
					poNo=map.get(strCutNo);
				}
				a.getId().setPoNo(poNo);*/
			}
		this.genDao.saveList(imPortD2);
		return new MsgRes(true, "数据保存成功！", importNo);
	}
	//集单
	@Override
	public MsgRes tscidataOrderTime(String importNo, String jsonMaster)
			throws Exception {
		Idata_ImportM importM=(Idata_ImportM)JSON.parseObject(jsonMaster, Idata_ImportM.class);
		
		//获取参数配置（AutoCollectOrder产生进货单时是否自动集单，0:不自动集单；1:自动集单）
		String strLoadCarLevel="";
	//	String sql="select * from wms_defbase a where a.colname='AutoCollectOrder'";
		//List<Wms_DefbaseModel> listA=genDao.getListByNativeSql(sql, Wms_DefbaseModel.class);
		List<Wms_IDataTypeModel> listA = getSystemParameterImpl.getIdataTypeStrategy(importM.getId().getEnterpriseNo(),
				importM.getId().getWarehouseNo(),importM.getId().getOwnerNo(),importM.getImportType(),"AUTOCOLLECTORDER");
		if(listA.size()!=0){
        	strLoadCarLevel=listA.get(0).getColumnValue();
        	if(strLoadCarLevel.equals("1")){
        		
        		List<Comparable> inList4=new ArrayList<Comparable>();
        		List<String> outList4=new ArrayList<String>();
        		List resultList4=new ArrayList();
        	
        		inList4.add(importM.getId().getEnterpriseNo());
        		inList4.add(importM.getId().getWarehouseNo());
        		inList4.add(importNo);
        		inList4.add("N");
        		inList4.add(importM.getRgstName());
        		
        		outList4.add("S");
        		outList4.add("S");
        		System.out.println(inList4);
        		
        		resultList4=genDao.execProc(inList4, outList4, "PKLG_IDATA.p_single_set");
        		if(resultList4.get(1).toString().indexOf("N|")!=-1){
        			throw new Exception();
        		}
        	
        	}
        }
	
		return new MsgRes(true, "", "");
	}
	
	/**
	 * 删除
	 */
	@Override
	public void deleteImPort(String strEnterpriseNo,String importNo)throws Exception {
	
	}
	
	@Override
	public List<Idata_ImportDModel> getImportArticle(String strEnterpriseNo,String articleNo)throws Exception {
		String sql="select a.article_no,a.article_name,a.unit_packing,a.qmin_operate_packing," +
				"a.owner_article_no,a.barcode from " +
				"bdef_defarticle a " +
				"where a.article_no='"+articleNo+"' and a.enterprise_no='"+strEnterpriseNo+"'";
		List<Idata_ImportDModel> list=genDao.getListByNativeSql(sql,Idata_ImportDModel.class,0,1000);
		return (List<Idata_ImportDModel>)list;
	}
	
	@Override
	public String checkPoNo(String strEnterpriseNo,String warehouseNo,String poNo) throws Exception {
		String sql="select a.po_no from idata_import_m a where a.po_no='"+poNo+"' " +
				" and a.warehouse_no='"+warehouseNo+"' and a.enterprise_no='"+strEnterpriseNo+"'";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
	@Override
	public void updatePrinter(
			String strEnterpriseNo,String importNo, String importType,String workerNo)
			throws Exception {
		String sql="update idata_import_m set status='15' " +
				"where import_no='"+importNo+"' and enterprise_no='"+strEnterpriseNo+"'";
		genDao.updateBySql(sql);
		if(importType.equals("IS")){
			sql="update idata_import_mm set status='15'," +
				"printer_name='"+workerNo+"',printer_date=sysdate " +
				"where enterprise_no='"+strEnterpriseNo+"' and s_import_no=REPLACE('"+importNo+"', 'IS', 'SS')";
			genDao.updateBySql(sql);
		}else if(importType.equals("ID")){
			sql="update idata_import_mm set status='15'," +
				"printer_name='"+workerNo+"',printer_date=sysdate " +
				"where enterprise_no='"+strEnterpriseNo+"' and s_import_no=REPLACE('"+importNo+"', 'ID', 'SD')";
			genDao.updateBySql(sql);
		}
	}
	
	/**
	 * 上传Excel导入数据库
	 */
	@Override
	public MsgRes upLoad(String importType,File file,String strWarehouseNo,
			String strEnterpriseNo,String workNo) throws Exception {
		
		FileUtilSys.writeFile(file, "IdataTmp.xlsx", ContextUtil.getWebRootPath()+"uploadFiles\\temp\\");
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		List<Idata_ImportTmp> list;
		
		if(importType.equals("IS")){//存储
			list = changeexcelBeanIS(ContextUtil.getWebRootPath()+"uploadFiles\\temp\\"+"IdataTmp.xlsx",strWarehouseNo,strEnterpriseNo);
			
			genDao.saveList(list);
			genDao.flush();
			
			outList.add("S");
			inList.add(strEnterpriseNo);
			inList.add(strWarehouseNo);
			inList.add(workNo);
			resultList=genDao.execProc(inList, outList, "pkobj_create_base.P_idata_import");

			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
			
		}else if(importType.equals("ID")){//直通
			list  = changeexcelBeanID(ContextUtil.getWebRootPath()+"uploadFiles\\temp\\"+"IdataTmp.xlsx",strWarehouseNo,strEnterpriseNo);

			genDao.saveList(list);
			genDao.flush();
			
			outList.add("S");
			inList.add(strEnterpriseNo);
			inList.add(strWarehouseNo);
			inList.add(workNo);
			resultList=genDao.execProc(inList, outList, "pkobj_create_base.P_idata_import_id");

			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
			
		}
		
		return new MsgRes(true,resultList.get(0).toString(),"");
	}
	
	/**
	 * Excel数据转List(存储)
	 */
	@Override
	public List<Idata_ImportTmp> changeexcelBeanIS(String fileName,String strWarehouseNo,String strEnterpriseNo)throws Exception {
		List<Idata_ImportTmp> iitList = new ArrayList<Idata_ImportTmp>();
		
		//导入前删除临时表数据
		String delsql=" delete from idata_import_tmp a " +
				      "  where a.enterprise_no='"+strEnterpriseNo+"' " +
				      "    and a.warehouse_no='"+strWarehouseNo+"' ";
		genDao.updateBySql(delsql);
		
		List<List> excelList = this.impExcelIS(fileName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for (int i = 0; i < excelList.size(); i++) {
			if(excelList.get(i).get(0).toString()!=null && !excelList.get(i).get(0).toString().equals("")){
				Idata_ImportTmp po = new Idata_ImportTmp();
				Idata_ImportTmpId id = new Idata_ImportTmpId();
							
				id.setEnterpriseNo(strEnterpriseNo);
				id.setWarehouseNo(strWarehouseNo);
				
				id.setRowId(Double.parseDouble(excelList.get(i).get(0).toString()));
				id.setPoNo(excelList.get(i).get(1).toString());
				id.setOwnerNo(excelList.get(i).get(2).toString());
				id.setSupplierNo(excelList.get(i).get(3).toString());
				id.setPoType(excelList.get(i).get(4).toString());
				id.setTakeType(excelList.get(i).get(5).toString());
				id.setOrderDate(sdf.parse(excelList.get(i).get(6).toString()));
				id.setRequestDate(sdf.parse(excelList.get(i).get(7).toString()));
				id.setEndDate((short) Integer.parseInt(excelList.get(i).get(8).toString()));
				id.setOwnerArticleNo(excelList.get(i).get(9).toString());
				id.setPackingQty(Double.parseDouble(excelList.get(i).get(10).toString().equals("")?"1":excelList.get(i).get(10).toString()));
				id.setPoQty(Double.parseDouble(excelList.get(i).get(11).toString().equals("")?"0":excelList.get(i).get(11).toString())*Double.parseDouble(excelList.get(i).get(10).toString().equals("")?"1":excelList.get(i).get(10).toString())+Double.parseDouble(excelList.get(i).get(12).toString().equals("")?"0":excelList.get(i).get(12).toString()));
				id.setUnitCost(Double.parseDouble(excelList.get(i).get(13).toString().equals("")?"0":excelList.get(i).get(13).toString()));
				id.setImportRemark(excelList.get(i).get(14).toString());
				
				id.setRsvVarod1(excelList.get(i).get(15).toString().equals("")?"":excelList.get(i).get(15).toString());
				id.setRsvVarod2(excelList.get(i).get(16).toString().equals("")?excelList.get(i).get(1).toString():excelList.get(i).get(16).toString());
				id.setRsvVarod3(excelList.get(i).get(17).toString().equals("")?"":excelList.get(i).get(17).toString());
				id.setRsvVarod4(excelList.get(i).get(18).toString().equals("")?"":excelList.get(i).get(18).toString());
				id.setRsvVarod5(excelList.get(i).get(19).toString().equals("")?"":excelList.get(i).get(19).toString());
				id.setRsvVarod6(excelList.get(i).get(20).toString().equals("")?"":excelList.get(i).get(20).toString());
				id.setRsvVarod7(excelList.get(i).get(21).toString().equals("")?"":excelList.get(i).get(21).toString());
				id.setRsvVarod8(excelList.get(i).get(22).toString().equals("")?"":excelList.get(i).get(22).toString());
				id.setRsvNum1(Double.parseDouble(excelList.get(i).get(23).toString().equals("")?"0":excelList.get(i).get(23).toString()));
				id.setRsvNum2(Double.parseDouble(excelList.get(i).get(24).toString().equals("")?"0":excelList.get(i).get(24).toString()));
				id.setRsvNum3(Double.parseDouble(excelList.get(i).get(25).toString().equals("")?"0":excelList.get(i).get(25).toString()));
				id.setRsvDate1(sdf.parse(excelList.get(i).get(26).toString().equals("")?"1900-01-01":excelList.get(i).get(26).toString()));
				id.setRsvDate2(sdf.parse(excelList.get(i).get(27).toString().equals("")?"1900-01-01":excelList.get(i).get(27).toString()));
				id.setRsvDate3(sdf.parse(excelList.get(i).get(28).toString().equals("")?"1900-01-01":excelList.get(i).get(28).toString()));

			
				id.setStatus("10");
				//System.out.println(Double.parseDouble(excelList.get(i).get(11).toString()));
				po.setId(id);
				iitList.add(po);
			}
		}
		return iitList;
	}
	/**
	 * Excel数据转List(直通)
	 */
	@Override
	public List<Idata_ImportTmp> changeexcelBeanID(String fileName,String strWarehouseNo,String strEnterpriseNo)throws Exception {
		List<Idata_ImportTmp> iitList = new ArrayList<Idata_ImportTmp>();
		
		//导入前删除临时表数据
		String delsql=" delete from idata_import_tmp a " +
				      "  where a.enterprise_no='"+strEnterpriseNo+"' " +
				      "    and a.warehouse_no='"+strWarehouseNo+"' ";
		genDao.updateBySql(delsql);
		
		List<List> excelList = this.impExcelID(fileName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for (int i = 0; i < excelList.size(); i++) {
			if(excelList.get(i).get(0).toString()!=null && !excelList.get(i).get(0).toString().equals("")){
				Idata_ImportTmp po = new Idata_ImportTmp();
				Idata_ImportTmpId id = new Idata_ImportTmpId();
							
				id.setEnterpriseNo(strEnterpriseNo);
				id.setWarehouseNo(strWarehouseNo);
				
				id.setRowId(Double.parseDouble(excelList.get(i).get(0).toString()));
				id.setPoNo(excelList.get(i).get(1).toString());
				id.setOwnerNo(excelList.get(i).get(2).toString());
				id.setSupplierNo(excelList.get(i).get(3).toString());
				id.setPoType(excelList.get(i).get(4).toString());
				id.setTakeType(excelList.get(i).get(5).toString());
				id.setOrderDate(sdf.parse(excelList.get(i).get(6).toString()));
				id.setRequestDate(sdf.parse(excelList.get(i).get(7).toString()));
				id.setEndDate((short) Integer.parseInt(excelList.get(i).get(8).toString()));
				
				id.setAllotTakeType(excelList.get(i).get(9).toString());
				id.setOdataexpNo(excelList.get(i).get(10).toString());
				id.setCustNo(excelList.get(i).get(11).toString());
				
				id.setOwnerArticleNo(excelList.get(i).get(12).toString());
				id.setPackingQty(Double.parseDouble(excelList.get(i).get(13).toString().equals("")?"1":excelList.get(i).get(13).toString()));
				id.setPoQty(Double.parseDouble(excelList.get(i).get(14).toString().equals("")?"0":excelList.get(i).get(14).toString())*Double.parseDouble(excelList.get(i).get(13).toString().equals("")?"1":excelList.get(i).get(13).toString())+Double.parseDouble(excelList.get(i).get(15).toString().equals("")?"0":excelList.get(i).get(15).toString()));
				
				id.setAllotQty(Double.parseDouble(excelList.get(i).get(16).toString().equals("")?"0":excelList.get(i).get(16).toString())*Double.parseDouble(excelList.get(i).get(13).toString().equals("")?"1":excelList.get(i).get(13).toString())+Double.parseDouble(excelList.get(i).get(17).toString().equals("")?"0":excelList.get(i).get(17).toString()));

				
				id.setUnitCost(Double.parseDouble(excelList.get(i).get(18).toString().equals("")?"0":excelList.get(i).get(18).toString()));
				id.setImportRemark(excelList.get(i).get(19).toString());
				
				id.setRsvVarod1(excelList.get(i).get(20).toString().equals("")?"":excelList.get(i).get(20).toString());
				id.setRsvVarod2(excelList.get(i).get(21).toString().equals("")?"":excelList.get(i).get(21).toString());
				id.setRsvVarod3(excelList.get(i).get(22).toString().equals("")?"":excelList.get(i).get(22).toString());
				id.setRsvVarod4(excelList.get(i).get(23).toString().equals("")?"":excelList.get(i).get(23).toString());
				id.setRsvVarod5(excelList.get(i).get(24).toString().equals("")?"":excelList.get(i).get(24).toString());
				id.setRsvVarod6(excelList.get(i).get(25).toString().equals("")?"":excelList.get(i).get(25).toString());
				id.setRsvVarod7(excelList.get(i).get(26).toString().equals("")?"":excelList.get(i).get(26).toString());
				id.setRsvVarod8(excelList.get(i).get(27).toString().equals("")?"":excelList.get(i).get(27).toString());
				id.setRsvNum1(Double.parseDouble(excelList.get(i).get(28).toString().equals("")?"0":excelList.get(i).get(28).toString()));
				id.setRsvNum2(Double.parseDouble(excelList.get(i).get(29).toString().equals("")?"0":excelList.get(i).get(29).toString()));
				id.setRsvNum3(Double.parseDouble(excelList.get(i).get(30).toString().equals("")?"0":excelList.get(i).get(30).toString()));
				id.setRsvDate1(sdf.parse(excelList.get(i).get(31).toString().equals("")?"1900-01-01":excelList.get(i).get(31).toString()));
				id.setRsvDate2(sdf.parse(excelList.get(i).get(32).toString().equals("")?"1900-01-01":excelList.get(i).get(32).toString()));
				id.setRsvDate3(sdf.parse(excelList.get(i).get(33).toString().equals("")?"1900-01-01":excelList.get(i).get(33).toString()));

			
				id.setStatus("10");
				po.setId(id);
				iitList.add(po);
			}
		}
		return iitList;
	}
	/**
	 * 解析Excel（存储）
	 */
	@Override
	public List<List> impExcelIS(String execelFile)throws Exception{
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
				List<String> object = new ArrayList<String>();
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
						System.out.println(1111);
						if(j==10 || j==11 || j==12 || j==13){	
							System.out.println("1111——1");
							cell = this.getValue((HSSFCell) row.getCell(j),2);		
						}else{
							System.out.println("1111——2");
							cell = this.getValue((HSSFCell) row.getCell(j),0);
						}									
					}catch (Exception e) 
					{   System.out.println("1111——3");
						cell = row.getCell(j).toString();
					}
					object.add(cell.trim());
					System.out.print(cell + "\n");
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
	
	/**
	 * 解析Excel（直通）
	 */
	@Override
	public List<List> impExcelID(String execelFile)throws Exception{
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
				List<String> object = new ArrayList<String>();
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
						System.out.println(1111);
						if(j==13 || j==14 || j==15 || j==16 || j==17 || j==18){	
							System.out.println("1111——1");
							cell = this.getValue((HSSFCell) row.getCell(j),2);		
						}else{
							System.out.println("1111——2");
							cell = this.getValue((HSSFCell) row.getCell(j),0);
						}									
					}catch (Exception e) 
					{   System.out.println("1111——3");
						cell = row.getCell(j).toString();
					}
					object.add(cell.trim());
					System.out.print(cell + "\n");
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
			System.out.println("<><><><><><><><><><><>");
			System.out.println(style);
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
	
	//获取进货直通配量（天天惠）
	@Override
	public ExtListDataBo<Idata_ImportDModel> getImPortAllot(
			String strEnterpriseNo,String wheresql,String articleNo,
			PageBo pageBo) throws Exception {
	 String sql="select a.article_no,b.article_name, b.owner_article_no,b.barcode,a.cust_no," +
			"d.cust_name,a.packing_qty,b.unit_packing,b.qmin_operate_packing," +
			//"nvl(e.packing_unit, decode(a.packing_qty,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit)) packing_unit,"+
			//" nvl(e.spec, '1*' || a.packing_qty || b.unit) spec,a.po_no,a.take_type," +
			"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit," +
			"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingUnitQmin," +
			"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) Unit," +
			"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec," +
			"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingSpecQmin," +
			"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) spec," +
			"trunc(a.po_qty/a.packing_qty) as planBox,"+
			"trunc(mod(a.po_qty,a.packing_qty)/b.QMIN_OPERATE_PACKING) as planQmin,"+
			//新增是否允许差异确认标识(0-不允许;1-允许) huangb 20160721
			"(case when m.send_flag = '13' and m.po_no = m.rsv_varod2 then '1' else '0' end) isDiffConfirmFlag,"+
			"(a.po_qty - trunc(a.po_qty/a.packing_qty) * a.packing_qty - trunc(mod(a.po_qty,a.packing_qty)/b.QMIN_OPERATE_PACKING) * b.QMIN_OPERATE_PACKING) as planDis," +
			"a.take_type,nvl(m.rsv_varod2,m.po_no) po_no "+
			"from idata_import_allot a,bdef_defarticle b ," +
			"bdef_defcust d," +
			"bdef_article_packing e,idata_import_m m  " +
			"where m.enterprise_no = a.enterprise_no and m.warehouse_no = a.warehouse_no and m.owner_no = a.owner_no and m.import_no = a.import_no " +
			"and a.article_no=b.article_no " +
			"and a.cust_no=d.cust_no " +
			"and a.article_no=e.article_no(+) and  a.packing_qty=e.packing_qty(+) " +
			"and a.enterprise_no=b.enterprise_no " +
			"and a.enterprise_no=d.enterprise_no and a.enterprise_no=e.enterprise_no(+) " +
			"and a.enterprise_no='"+strEnterpriseNo+"' " +
			"and a.import_no='"+wheresql+"' ";
			
	 if(articleNo!=null && !articleNo.equals(""))
		{
			sql=sql+" and a.article_no = '"+articleNo+"' ";
		}
		List<Idata_ImportDModel> list = genDao.getListByNativeSql(sql,Idata_ImportDModel.class,pageBo.getStart(), 1000);
		ExtListDataBo<Idata_ImportDModel> extListBo= new ExtListDataBo<Idata_ImportDModel>(list, 0);
        return extListBo;
	  }
	
	//获取进货明细（天天惠）
	@Override
	public ExtListDataBo<Idata_ImportDModel> getImPortTth_DList(
			String strEnterpriseNo,
			String wheresql, PageBo pageBo) throws Exception {
		
	 String sql="select b.warehouse_no,b.import_type,a.import_no,a.owner_no,a.article_no,c.owner_article_no,c.article_name,c.barcode," +
			"a.packing_qty,c.unit_packing,c.qmin_operate_packing," +
			//"nvl(e.packing_unit, decode(a.packing_qty,c.qmin_operate_packing,c.qmin_operate_packing_unit,c.unit)) packing_unit,"+
			//"nvl(e.spec, '1*' || a.packing_qty || c.unit) spec,a.status," +
			"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit," +
			"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,c.qmin_operate_packing) packingUnitQmin," +
			"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,c.unit_packing) Unit," +
			"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec," +
			"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,c.qmin_operate_packing) packingSpecQmin," +
			"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,c.unit_packing) spec," +
			"trunc(a.po_qty/a.packing_qty) as planBox,"+
			"trunc(mod(a.po_qty,a.packing_qty)/c.QMIN_OPERATE_PACKING) as planQmin,"+
			"(a.po_qty - trunc(a.po_qty/a.packing_qty) * a.packing_qty - trunc(mod(a.po_qty,a.packing_qty)/c.QMIN_OPERATE_PACKING) * c.QMIN_OPERATE_PACKING) as planDis,"+
			//新增是否允许差异确认标识(0-不允许;1-允许) huangb 20160721
			"(case when b.send_flag = '13' and b.po_no = b.rsv_varod2 then '1' else '0' end) isDiffConfirmFlag," +
			//获取头档状态 huangb 20160721
			"b.status,"+
			"a.unit_cost,nvl(b.rsv_varod2,b.po_no) po_no " +
			"from idata_import_d a,idata_import_m b,bdef_defarticle c," +
			"bdef_article_packing e  " +
			"where a.import_no=b.import_no and a.article_no=c.article_no " +
			"and a.article_no=e.article_no(+) " +
			"and a.packing_qty=e.packing_qty(+) " +
			"and a.enterprise_no=b.enterprise_no and a.enterprise_no=c.enterprise_no " +
			"and a.enterprise_no=e.enterprise_no(+) " +
			"and a.enterprise_no='"+strEnterpriseNo+"' " +
			"and a.import_no='"+wheresql+"' " +
			"order by a.article_no ";
		
		List<Idata_ImportDModel> list = genDao.getListByNativeSql(sql,Idata_ImportDModel.class,pageBo.getStart(), 1000);
		//获取是否可修改允许原单，跨境单重新下单后对原单做调整，0：不允许；1：允许 haungb 20160726
		List<Wms_IDataTypeModel> listModifyFlag = getSystemParameterImpl.getIdataTypeStrategy
				(strEnterpriseNo, list.get(0).getWarehouseNo(), list.get(0).getOwnerNo(), list.get(0).getImportType(), "modify_flag");
		
		for(int i = 0;i < list.size(); i++)
		{
			list.get(i).setModifyFlag(listModifyFlag.get(0).getColumnValue());
		}

		ExtListDataBo<Idata_ImportDModel> extListBo= new ExtListDataBo<Idata_ImportDModel>(list, 0);
        return extListBo;
	}
	
	//取消订单
	@Override
	public MsgRes closeOrder(String strEnterpriseNo,String warehouseNo, String workerNo,
			String importNo, String ownerNo) throws Exception {
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
			
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(warehouseNo);
		inList.add(ownerNo);
		inList.add(importNo);
		inList.add(workerNo);
		resultList=genDao.execProc(inList, outList, "PKLG_IDATA.P_import_cancel");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}

		return new MsgRes(true,"操作成功","");
	}
	//集单打印
	@Override
	public MsgRes tscSetAndPrint(String currEnterpriseNo, String warehouseNo,
			String workerNo, String workSpaceNo, String importNo,
			String printFlag) throws Exception {
		
		List<String>  outList=new ArrayList<String>();
		List  resultList=new ArrayList();
		List<Comparable>  inList=new ArrayList<Comparable>();
	
		outList.add("S");
		outList.add("S");
		inList.add(currEnterpriseNo);
		inList.add(warehouseNo);
		inList.add(importNo);
		inList.add("N");	
		inList.add(workerNo);
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_IDATA.p_single_set");				
		if(resultList.get(1).toString().indexOf("Y")==-1){
			throw new Exception(resultList.get(1).toString());
		}	
		
		if(printFlag.equals("2")){
			resultList=tscPrint(currEnterpriseNo,warehouseNo,resultList.get(0).toString(),workerNo,
					ReportDefine.L_IS_READY_CHECK,workSpaceNo);
			if(resultList.get(1).toString().indexOf("N|")!=-1)
			{
				throw new Exception(resultList.get(1).toString());
			}
		}	
		return new MsgRes(true,"集单成功","");
	}
	
	//发单打印
		public List tscPrint(String strEnterpriseNo, String strWareHouseNo,
							 String strSourceNo, String strWorkerNo,
							 String strReportId, String strDockNo) throws Exception{
			List<Comparable> inList=new ArrayList<Comparable>();
			List<String> outList=new ArrayList<String>();
			List resultList=new ArrayList();
			
			outList.add("S");
			outList.add("S");
			inList.add(strEnterpriseNo);
			inList.add(strWareHouseNo);
			inList.add(strSourceNo);
			inList.add("0");
			inList.add(strReportId);
			inList.add(strDockNo);
			inList.add("0");
			inList.add(strWorkerNo);
			
			resultList=genDao.execProc(inList, outList, "PKOBJ_PRINTTASK.p_insert_taskmaster");
			if(resultList.get(1).toString().indexOf("N|")!=-1)
			{
				throw new Exception(resultList.get(1).toString());
			}
			return resultList;
		}
		
	/*	public String changeType(String var) throws Exception {
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
		}*/
		
		/**差异确认
		 * huangb 20160721
		 */
		@Override
		public MsgRes tscDiffConfirm(String strEnterpriseNo, String strWarehouseNo,
				String strOwnerNo, String strImportNo, String strNewPoNo,
				String strUserId) throws Exception {
			
			List<String>  outList=new ArrayList<String>();
			List  resultList=new ArrayList();
			List<Comparable>  inList=new ArrayList<Comparable>();
		
			outList.add("S");
			inList.add(strEnterpriseNo);
			inList.add(strWarehouseNo);
			inList.add(strOwnerNo);
			inList.add(strImportNo);	
			inList.add(strNewPoNo);
			inList.add(strUserId);
			System.out.println(inList);
			
			resultList=genDao.execProc(inList, outList, "PKLG_IDATA.p_Check_DiffConfirm");				
			if(resultList.get(0).toString().indexOf("Y")==-1){
				throw new Exception(resultList.get(0).toString());
			}	
			return new MsgRes(true,"差异确认成功","");
		}
		
		//根据进货单号获得验收量   8-17
		@Override
		public MsgRes getCheckNumber(String strEnterpriseNo, String strWareNo,
				String importNo) throws Exception {
			String sql="select sum(m.import_qty) inportQty from idata_import_d m where 1=1 and " +
					"m.import_no='"+importNo+"' " +
					"and m.enterprise_no='"+strEnterpriseNo+"' " +
					"and m.warehouse_no='"+strWareNo+"'";
			List<Double> list = genDao.getListByNativeSql(sql);
			System.out.println("验收量: " + list.get(0));
			
			return new MsgRes(true,"",list.get(0));
		}
}

















