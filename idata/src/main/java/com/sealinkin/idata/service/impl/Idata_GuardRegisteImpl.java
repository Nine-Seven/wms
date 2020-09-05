package com.sealinkin.idata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.idata.model.Idata_GuardRegisteModel;
import com.sealinkin.idata.model.Idata_OrderSheetModel;
import com.sealinkin.idata.model.Idata_OrderStatusModel;
import com.sealinkin.idata.po.Idata_GuardRgiste;
import com.sealinkin.idata.po.Idata_OrderSheet;
import com.sealinkin.idata.service.Iidata_GuardRegisteService;
import com.sealinkin.report.conf.ReportDefine;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Idata_GuardRegisteImpl implements Iidata_GuardRegisteService{
	
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	//获取货主下拉
	@Override
	public List<ComboxBo> queryOwnerCombo(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String strAppointDate,String str)
			throws Exception {
		String strSql="select distinct t1.owner_no value,t1.owner_name text," +
				"'['|| ltrim(t1.owner_no)||']'||t1.owner_name dropValue " +
				"from bdef_defowner t1 ,idata_order_status a " + 
				"where t1.enterprise_no=a.enterprise_no " +
				"and t1.owner_no=a.owner_no "+
				"and a.warehouse_no='"+strWarehouseNo+"' "+
				"and a.owner_no in ("+strWorkerOwner+") "+
				"and a.enterprise_no='"+strEnterpriseNo+"'  " +
				"and to_date('"+strAppointDate+"','yyyy/mm/dd') = a.request_date";
		 
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	//获取供应商下拉
	@Override
	public List<ComboxBo> querySupplierNoCombo(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner,
			String strAppointDate, String str) throws Exception {
		String strSql="select distinct t1.supplier_no value,t1.supplier_name text," +
				"'['|| ltrim(t1.supplier_no)||']'||t1.supplier_name dropValue " +
				"from bdef_defsupplier t1 ,idata_order_status a " + 
				"where t1.enterprise_no=a.enterprise_no " +
				"and t1.owner_no=a.owner_no " +
				"and t1.supplier_no=a.supplier_no "+
				"and a.warehouse_no='"+strWarehouseNo+"' "+
				"and a.owner_no in ("+strWorkerOwner+") "+
				"and a.enterprise_no='"+strEnterpriseNo+"'  " +
				"and to_date('"+strAppointDate+"','yyyy/mm/dd') = a.request_date";
		
		if(str != null && !str.equals(""))
		   {
			   String ws  =  CommUtil.covtCollectionToWhereSql(str);
			   strSql  =  strSql + ws;
		   }
		 
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	//获取状态下拉
	@Override
	public List<ComboxBo> queryStatusCombo(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner,
			String strAppointDate, String str) throws Exception {
		String strSql="select distinct a.status value,a.status text," +
				"'['|| ltrim(a.status)||']'||f_get_fieldtext('IDATA_ORDER_STATUS','STATUS',a.status) dropValue " +
				"from idata_order_status a " + 
				"where a.warehouse_no='"+strWarehouseNo+"' "+
				"and a.owner_no in ("+strWorkerOwner+") "+
				"and a.enterprise_no='"+strEnterpriseNo+"'  " +
				"and to_date('"+strAppointDate+"','yyyy/mm/dd') = a.request_date";
		
		if(str != null && !str.equals(""))
		   {
			   String ws  =  CommUtil.covtCollectionToWhereSql(str);
			   strSql  =  strSql + ws;
		   }
		 
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	//获取预约状况信息列表
	@Override
	public ExtListDataBo<Idata_OrderStatusModel> getIdata_Order_StatusList(
			String strEnterpriseNo, String strWarehouseNo,
			String strWorkerOwner, String strAppointDate, String str,
			PageBo pageBo) throws Exception {
		String strSql="select a.*, " +
				"to_char(a.request_date,'yyyy/mm/dd') requestDateText, "+
				"'['|| ltrim(t1.supplier_no)||']'||t1.supplier_name suppliersText, " +
				"f_get_fieldtext('IDATA_ORDER_STATUS','STATUS',a.status) statusText," +
				"'['|| ltrim(a.dock_no)||']'||nvl(c.dock_name,'N') dockText "+
				"from idata_order_status a,bdef_defsupplier t1,bdef_defDock c " + 
				"where t1.enterprise_no=a.enterprise_no " +
				"and t1.owner_no=a.owner_no " +
				"and t1.supplier_no=a.supplier_no "+
				"and a.enterprise_no=c.enterprise_no(+) " +
				"and a.warehouse_no=c.warehouse_no(+) " +
				"and a.dock_no=c.dock_no(+) " +
				"and a.warehouse_no='"+strWarehouseNo+"' "+
				"and a.owner_no in ("+strWorkerOwner+") "+
				"and a.enterprise_no='"+strEnterpriseNo+"'  ";
		if(strAppointDate!=null && !strAppointDate.equals(""))
		{
			strSql +=" and to_date('"+strAppointDate+"','yyyy-mm-dd') = a.request_date ";
		}
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}
		strSql=strSql+" order by a.dock_no,a.start_time ";
        String totsql = "select count(*) from (" + strSql + ") " ;	
		List<Idata_OrderStatusModel> list = genDao.getListByNativeSql(strSql,Idata_OrderStatusModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Idata_OrderStatusModel> extListBo= new ExtListDataBo<Idata_OrderStatusModel>(list,count);
        return extListBo;
	}
	//获取报到信息列表
	@Override
	public ExtListDataBo<Idata_GuardRegisteModel> getGuardRegisteList(
			String strEnterpriseNo, String strWarehouseNo,
			String strWorkerOwner,  String str,String strWheresql,String status,
			PageBo pageBo) throws Exception {
		List<Idata_GuardRegisteModel> list=new ArrayList<Idata_GuardRegisteModel>();
		int count=0;
		
		if(str!=null && str!=null){
			String str1[]=str.split(",");
			for(int i=0; i<str1.length; i++){
				String strSql="select a.*, " +
						"to_char(a.registe_date,'yyyy-mm-dd HH24:MI:SS') registeDateText, "+
						"to_char(a.begin_unload_date,'yyyy-mm-dd HH24:MI:SS') beginUnloadDateText, "+
						"to_char(a.end_unload_date,'yyyy-mm-dd HH24:MI:SS') endUnloadDateText,"+
						"to_char(a.leave_date,'yyyy-mm-dd HH24:MI:SS') leaveDateText,"+
						"'['|| ltrim(a.dock_no)||']'||nvl(c.dock_name,'N') dockText "+
						"from idata_guard_rgiste a,bdef_defDock c " + 
						"where a.enterprise_no=c.enterprise_no(+) " +
						"and a.warehouse_no=c.warehouse_no(+) " +
						"and a.dock_no=c.dock_no(+) " +
						"and a.order_serial='"+str1[i].trim()+"'  " +
						"and a.warehouse_no='"+strWarehouseNo+"' "+
						"and a.owner_no in ("+strWorkerOwner+") "+
						"and a.enterprise_no='"+strEnterpriseNo+"' ";	

				if(strWheresql!=null && !strWheresql.equals(""))
				{
					String ws=CommUtil.covtCollectionToWhereSql(strWheresql);
					strSql=strSql+ws;
				}
				List<Idata_GuardRegisteModel> list1=genDao.getListByNativeSql(strSql, Idata_GuardRegisteModel.class);	
			 
				for(int j=0; j<list1.size();j++){	
					list.add(count++, list1.get(j));
				} 
			}
		  	 ExtListDataBo<Idata_GuardRegisteModel> extListBo = new ExtListDataBo<Idata_GuardRegisteModel>(list, 0);
			 return extListBo;
		}else{
			return null;
		}		
	   
	}
	//获取单据信息列表
	@Override
	public ExtListDataBo<Idata_OrderSheetModel> getIdata_Order_SheetList(
			String strEnterpriseNo, String strWarehouseNo, String str, PageBo pageBo) throws Exception {
		List<Idata_OrderSheetModel> list=new ArrayList<Idata_OrderSheetModel>();

		int count=0;
		
		if(str!=null && str!=null){
			String str1[]=str.split(",");
			for(int i=0; i<str1.length; i++){
				String strSql="select a.*,b.po_no,c.dock_no, " +
			    "'['|| ltrim(c.dock_no)||']'||nvl(d.dock_name,'N') dockText, "+
				"f_get_fieldtext('IDATA_ORDER_SHEET','STATUS',a.status) statusText " +
				"from idata_order_sheet a,idata_import_m b,idata_order_status c ,bdef_defdock d " +
				"where a.import_no=b.import_no " +
				"and a.warehouse_no=b.warehouse_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.enterprise_no=c.enterprise_no " +
				"and a.warehouse_no=c.warehouse_no " +
				"and a.order_serial=c.order_serial " +
				"and c.enterprise_no=d.enterprise_no(+) " +
				"and c.warehouse_no=d.warehouse_no(+) " +
				"and c.dock_no=d.dock_no(+) " +
				"and a.warehouse_no='"+strWarehouseNo+"' " +
				"and a.enterprise_no='"+strEnterpriseNo+"'  " +
				"and a.order_serial='"+str1[i].trim()+"'   ";	

				List<Idata_OrderSheetModel> list1=genDao.getListByNativeSql(strSql, Idata_OrderSheetModel.class);	
			 
				for(int j=0; j<list1.size();j++){	
					list.add(count++, list1.get(j));
				} 
			}
		  	 ExtListDataBo<Idata_OrderSheetModel> extListBo = new ExtListDataBo<Idata_OrderSheetModel>(list, 0);
			 return extListBo;
		}else{
			return null;
		}		
//		String strSql="select a.*,b.po_no, " +
//				"f_get_fieldtext('IDATA_ORDER_SHEET','STATUS',a.status) statusText " +
//				"from idata_order_sheet a,idata_import_m b " +
//				"where a.import_no=b.import_no " +
//				"and a.enterprise_no=b.enterprise_no " +
//				"and a.warehouse_no='"+strWarehouseNo+"' " +
//				"and a.enterprise_no='"+strEnterpriseNo+"' " ;			
//       	
//        
//        if(str!=null && !str.equals(""))
//		{
//			String ws=CommUtil.covtCollectionToWhereSql(str);
//			strSql=strSql+ws;
//		}
//        strSql=strSql+" order by a.import_no ";
//        String totsql = "select count(*) from (" + strSql + ") " ;	
//
//		List<Idata_OrderSheetModel> list = genDao.getListByNativeSql(strSql,Idata_OrderSheetModel.class,pageBo.getStart(), pageBo.getPagesize());
//		Integer count = genDao.getCountByNativeSql(totsql);
//		ExtListDataBo<Idata_OrderSheetModel> extListBo= new ExtListDataBo<Idata_OrderSheetModel>(list,count);
//        return extListBo;
	}
	@Override
	public MsgRes save(String jsonMaster, String jsonDetail,String strSaveType,String strWorkSpaceNo) throws Exception {
		Idata_GuardRgiste guardRgiste=JSON.parseObject(jsonMaster,Idata_GuardRgiste.class);
		List<Idata_OrderSheet> list=JSON.parseArray(jsonDetail,Idata_OrderSheet.class);
		
		this.genDao.saveOrUpdateObj(guardRgiste);
		
		String status=guardRgiste.getStatus();
		if (status.equals("10")){
			status="11";
		}else{
			status=guardRgiste.getStatus();
		}
		String sql1="update idata_order_status ios set ios.status='"+status+"' " +
				   " where ios.enterprise_no='"+guardRgiste.getId().getEnterpriseNo()+"' "+
                   "and ios.warehouse_no='"+guardRgiste.getId().getWarehouseNo()+"' " +
                   "and ios.order_serial='"+guardRgiste.getId().getOrderSerial()+"' " +
                   "and ios.supplier_no='"+guardRgiste.getId().getSupplierNo()+"' ";
		this.genDao.updateBySql(sql1);
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				String sql2="update idata_order_sheet ios set ios.status='11' " +
						   " where ios.enterprise_no='"+guardRgiste.getId().getEnterpriseNo()+"' "+
	                          "and ios.warehouse_no='"+guardRgiste.getId().getWarehouseNo()+"' " +
	                          "and ios.order_serial='"+list.get(i).getId().getOrderSerial()+"' " +
	                          "and ios.import_no='"+list.get(i).getId().getImportNo()+"' ";
				this.genDao.updateBySql(sql2);
			}
		}
		
		if(strSaveType.equals("0")){
			List<String> outList1=new ArrayList<String>();
			List resultList1=new ArrayList();
			List<Comparable> inList1=new ArrayList<Comparable>();
			outList1.add("S");
			outList1.add("S");
			inList1.add(guardRgiste.getId().getEnterpriseNo());//strEnterpriseNo
			inList1.add(guardRgiste.getId().getWarehouseNo());//strWarehouseNo
			inList1.add(guardRgiste.getId().getOrderSerial());//strSourceNo
			inList1.add("0");//strBackFlag
			inList1.add(ReportDefine.B_DOOR_RECODE);//strReportID
			inList1.add(strWorkSpaceNo);//strDockNo
			inList1.add("0");//strReprintFlag
			inList1.add(guardRgiste.getRgstName());//strUserNo
			resultList1=genDao.execProc(inList1, outList1, "PKOBJ_PRINTTASK.p_insert_taskmaster");
			if(resultList1.get(1).toString().indexOf("N|")!=-1){
				throw new Exception(resultList1.get(1).toString());
			}
		}
		return new MsgRes(true, "", "");
	}
	//获取预约流水号下拉
	@Override
	public List<ComboxBo> getOrderSerialList(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner,
			String strAppointDate, String strWheresql, String str)
			throws Exception {
		String sql = "select a.order_serial value,a.order_serial text,a.order_serial dropValue  "
				+ "from idata_order_status a "
				+ " where a.status not in ('13','16') "
				+ " and a.enterprise_no='" + strEnterpriseNo + "' "
				+ " and a.warehouse_No='" + strWarehouseNo + "' "
				+ " and a.owner_no in(" + strWorkerOwner + ") ";
		if(strAppointDate!=null && !strAppointDate.equals(""))
		{
			sql +=" and to_date('"+strAppointDate+"','yyyy-mm-dd') = a.request_date ";
		}
				
		if (str != null && !str.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql + ws;
		}
		if (strWheresql != null && !strWheresql.equals("")) {
			sql = sql + "and a.order_serial like '%" + strWheresql + "%' ";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	
	
}
