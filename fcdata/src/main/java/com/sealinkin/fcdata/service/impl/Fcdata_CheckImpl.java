package com.sealinkin.fcdata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.fcdata.model.Fcdata_CheckDModel;
import com.sealinkin.fcdata.model.Fcdata_CheckDirectModel;
import com.sealinkin.fcdata.model.Fcdata_CheckMModel;
import com.sealinkin.fcdata.service.IFcdata_CheckService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.TipUtil;

/*
 * 初盘发单实现类（初盘、复盘、三盘）
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class Fcdata_CheckImpl implements IFcdata_CheckService{

	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
    /////////////////////////////////初盘发单/////////////////////////////////////////////////////
	//获得盘点类别
	public List<ComboxBo> getFcdataTypeCombo(String enterpriseNo, String warehouseNo,
			String strOwner, String strFlag, int i,int j) {
		String strSql="";
		//1:成单条件》计划单号 2:作业预览》计划单号 status=11已切单 
		if(strFlag!=null && strFlag.equals("1"))
		{
			strSql="select distinct a.fcdata_type value,f_get_fieldtext('N', 'FCDATA_TYPE',a.fcdata_type) text, " +
					" '['|| ltrim(a.fcdata_type)||']'||f_get_fieldtext('N', 'FCDATA_TYPE',a.fcdata_type) dropValue " +
					" from fcdata_request_m a where 1=1 " ;
		}else if(strFlag!=null && strFlag.equals("2"))
		{
			strSql="select distinct a.fcdata_type value,f_get_fieldtext('N', 'FCDATA_TYPE',a.fcdata_type) text, " +
					" '['|| ltrim(a.fcdata_type)||']'||f_get_fieldtext('N', 'FCDATA_TYPE',a.fcdata_type) dropValue " +
					" from fcdata_check_m a where 1=1 and a.status=11 "  ;
		}
		
		if(enterpriseNo!=null && !enterpriseNo.equals("")){
			strSql=strSql+" and a.enterprise_no='"+enterpriseNo+"' ";
		}
		if(warehouseNo!=null && !warehouseNo.equals(""))
		{
			strSql=strSql+" and a.warehouse_no='"+warehouseNo+"' ";
		}
		if(strOwner!=null && !strOwner.equals(""))
		{
			strSql=strSql+" and a.owner_no in("+strOwner + ",'N') ";
		}else{
			strSql=strSql+" and 1=2";
		}
		List list=genDao.getListByNativeSql(strSql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	
	//获得计划单号
	public List<ComboxBo> getPlanNoCombo(String enterpriseNo, String warehouseNo, String strOwner, 
			String strFlag,  String strFcdataType,  int intstart, int intpagesize) 
	{
		String strSql="";
		//1:成单条件》计划单号 2:作业预览》计划单号 status=11已切单 3:作业预览》盘点次数
		if(strFlag!=null && strFlag.equals("1"))
		{
			strSql="select distinct a.plan_no value,a.plan_no text,a.plan_no dropValue " +
					"from fcdata_request_m a " +
					"where a.status='10' ";
			if(strFcdataType!=null && !strFcdataType.equals(""))
			{
				String ft=CommUtil.covtCollectionToWhereSql(strFcdataType);
				strSql=strSql+ft;
			}
		}else if(strFlag!=null && strFlag.equals("2"))
		{
			strSql="select distinct a.plan_no value,a.plan_no text,a.plan_no dropValue " +
					"from fcdata_check_m a " +
					"where a.status='11' " ;
			if(strFcdataType!=null && !strFcdataType.equals(""))
			{
				String ft=CommUtil.covtCollectionToWhereSql(strFcdataType);
				strSql=strSql+ft;
			}
		}else if(strFlag!=null && strFlag.equals("3")){
			strSql="select distinct a.CHECK_TYPE value,a.CHECK_TYPE text," +
					"f_get_fieldtext('N','CHECK_TYPE',a.CHECK_TYPE) dropValue " +
					"from fcdata_check_m a " +
					"where 1=1 and a.plan_no='"+strFlag+"'" ;
		}
		
		if(enterpriseNo!=null && !enterpriseNo.equals("")){
			strSql=strSql+" and a.enterprise_no='"+enterpriseNo+"' ";
		}
		if(warehouseNo!=null && !warehouseNo.equals(""))
		{
			strSql=strSql+" and a.warehouse_No='"+warehouseNo+"' ";
		}
		if(strOwner!=null && !strOwner.equals(""))
		{
			strSql=strSql+" and a.owner_no in("+strOwner+",'N') ";
		}else{
			strSql=strSql+" and 1=2";
		}
		List list=genDao.getListByNativeSql(strSql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	
	// 获得盘点定位指示。1获取成单条件》盘点需求切单；2派单；3获取成单条件》盘点需求切单（商品盘只取需求单号）
	public ExtListDataBo<Fcdata_CheckDirectModel> getCheckDirect(	
			String enterpriseNo,
			String strWarehouseNo,
			String strOwnerNo,
			String strWheresql, 
			String strFlag,
			PageBo pageBo) throws Exception {
		String strSql = "";
		String totsql = "";
		if(strFlag.equals("1"))
		{
			 strSql="select distinct cdc.ware_no,cdc.area_no,cdw.ware_name,  "+ 
					" cda.area_name,cdc.enterprise_no,cdc.warehouse_no,frd.request_no,   "+ 
					" frd.owner_no,fcd.rgst_name   "+ 
					" from cdef_defcell cdc,cdef_defware cdw,cdef_defarea cda,  "+ 
					" fcdata_request_m fcd,fcdata_request_d frd where  "+ 
			        " fcd.enterprise_no=frd.enterprise_no " +
			        " and fcd.warehouse_no=frd.warehouse_no "+ 
			        " and fcd.request_no=frd.request_no "+ 
			        " and cdc.cell_no =case when frd.cell_no = 'ALL' then cdc.cell_no else frd.cell_no end  "+ 
		            " and cdw.ware_no=case when frd.ware_no = 'ALL' then cdw.ware_no else frd.ware_no end "+ 
		            " and cda.area_no=case when frd.area_no = 'ALL' then cda.area_no else frd.area_no end  "+ 
					" and frd.enterprise_no=cdc.enterprise_no  "+ 
			        " and frd.warehouse_no=cdc.warehouse_no and cdc.ware_no=cda.ware_no  "+  
			        " and cdc.ware_no=cdw.ware_no and cdc.area_no=cda.area_no   "+ 
			        " and cdc.enterprise_no=cdw.enterprise_no  "+ 
			        " and cdc.warehouse_no=cdw.warehouse_no   "+ 
			        " and cdc.enterprise_no=cda.enterprise_no  "+ 
			        " and cdc.warehouse_no=cda.warehouse_no  and fcd.status='10'  "+ 
			        " and frd.warehouse_no='"+strWarehouseNo+"' "+ 
			        " and frd.enterprise_no='"+enterpriseNo+"' ";
					
				   totsql = "select count(*) " +
						   " from cdef_defcell cdc,cdef_defware cdw,cdef_defarea cda,  "+ 
							" fcdata_request_m fcd,fcdata_request_d frd where  "+ 
					        " fcd.enterprise_no=frd.enterprise_no " +
					        " and fcd.warehouse_no=frd.warehouse_no "+ 
					        " and fcd.request_no=frd.request_no "+ 
					        " and cdc.cell_no =case when frd.cell_no = 'all' then cdc.cell_no else frd.cell_no end  "+ 
				            " and cdw.ware_no=case when frd.ware_no = 'all' then cdw.ware_no else frd.ware_no end "+ 
				            " and cda.area_no=case when frd.area_no = 'all' then cda.area_no else frd.area_no end  "+ 
							" and frd.enterprise_no=cdc.enterprise_no  "+ 
					        " and frd.warehouse_no=cdc.warehouse_no and cdc.ware_no=cda.ware_no  "+  
					        " and cdc.ware_no=cdw.ware_no and cdc.area_no=cda.area_no   "+ 
					        " and cdc.enterprise_no=cdw.enterprise_no  "+ 
					        " and cdc.warehouse_no=cdw.warehouse_no   "+ 
					        " and cdc.enterprise_no=cda.enterprise_no  "+ 
					        " and cdc.warehouse_no=cda.warehouse_no  and fcd.status='10'  "+ 
					        " and frd.warehouse_no='"+strWarehouseNo+"' "+ 
					        " and frd.enterprise_no='"+enterpriseNo+"' ";
		}else if (strFlag.equals("2"))
		{			
			 strSql="select distinct cdc.ware_no,cdc.area_no,cdc.stock_no," +
						" cda.area_name,cdc.enterprise_no,cdc.warehouse_no,fcm.check_no,fcd.fcdata_type, " +
						" fcd.owner_no,fcd.rgst_name " +
						" from fcdata_check_d fcm,cdef_defcell cdc,cdef_defarea cda," +
						" fcdata_check_m fcd where cdc.cell_no =fcm.cell_no " +
						" and fcm.enterprise_no=cdc.enterprise_no "+
						" and fcm.warehouse_no=cdc.warehouse_no " +
						" and cdc.enterprise_no=cda.enterprise_no "+
						" and cdc.warehouse_no=cda.warehouse_no " +
						" and fcm.enterprise_no=fcd.enterprise_no "+
						" and fcm.warehouse_no=fcd.warehouse_no " +
						" and cdc.ware_no=cda.ware_no  and cdc.area_no=cda.area_no " +
						" and fcd.check_type=1  and fcd.status='11' " +
						" and fcm.check_no=fcd.check_no " +
						" and fcm.enterprise_no='"+enterpriseNo+"' "+
						" and fcm.warehouse_no='"+strWarehouseNo+"' " ;
				
				   totsql = "select count(*) " +
						" from fcdata_check_d fcm,cdef_defcell cdc,cdef_defarea cda," +
						" fcdata_check_m fcd where cdc.cell_no =fcm.cell_no " +
						" and fcm.enterprise_no = cdc.enterprise_no "+
						" and fcm.warehouse_no=cdc.warehouse_no " +
						" and cdc.enterprise_no=cda.enterprise_no "+
						" and cdc.warehouse_no=cda.warehouse_no " +
						" and fcm.enterprise_no=fcd.enterprise_no "+
						" and fcm.warehouse_no=fcd.warehouse_no " +
						" and cdc.ware_no=cda.ware_no  and cdc.area_no=cda.area_no " +
						" and fcd.check_type=1  and fcd.status='11' " +
						" and fcm.check_no=fcd.check_no " +
						" and fcm.enterprise_no='"+enterpriseNo+"' "+
						" and fcm.warehouse_no='"+strWarehouseNo+"' " ;
		}else if (strFlag.equals("3"))
		{
			 strSql="select distinct fcd.request_no, fcd.enterprise_no, " +
						" fcd.owner_no,fcd.warehouse_no " +
						" from fcdata_request_m fcd where fcd.status=10 " +
						" and fcd.enterprise_no='"+enterpriseNo+"' "+
						" and fcd.warehouse_no='"+strWarehouseNo+"'";
				   totsql = "select count(*) from fcdata_request_m fcd where fcd.status=10 " +
						" and fcd.enterprise_no='"+enterpriseNo+"' "+
						" and fcd.warehouse_no='"+strWarehouseNo+"'";
		}
		
		
		 
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strWheresql);
			strSql=strSql+ws;
			totsql=totsql+ws;
		}
		if(strFlag.equals("2"))
		{
			strSql = strSql + "order by fcm.check_no desc";
		}
		//totsql = totsql+")";
		List<Fcdata_CheckDirectModel> list = genDao.getListByNativeSql(strSql,Fcdata_CheckDirectModel.class,0,1000);
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Fcdata_CheckDirectModel> extListBo= new ExtListDataBo<Fcdata_CheckDirectModel>(list, count);
        return extListBo;
	}
	
	// 切单 
	public MsgRes tscCut8201(String strCheckDirect, String strOwnerNo) throws Exception 
	{
		List<Fcdata_CheckDirectModel> fcd=JSON.parseArray(strCheckDirect, Fcdata_CheckDirectModel.class);
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		for(int i=0;i<fcd.size();i++)
		{
			List inList=new ArrayList();
			inList.add(fcd.get(i).getEnterpriseNo());
			inList.add(fcd.get(i).getWarehouseNo());
			inList.add(fcd.get(i).getOwnerNo());
			inList.add(fcd.get(i).getRgstName());
			inList.add(fcd.get(i).getRequestNo());
			inList.add(fcd.get(i).getBeginDate());//dtBEGIN_DATE
			inList.add(fcd.get(i).getEndDate());//dtEND_DATE
			inList.add(fcd.get(i).getCutFlag());
			
			resultList=genDao.execProc(inList, outList, "PKLG_FCDATA.P_LocateDirectAndCutTask");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
		}
		
		return new MsgRes(true,TipUtil.getTipsByKey("E30211"),null);//切单成功！
	}
	
	//获得盘点类别
	public List<String> CheckType(String enterpriseNo,String warehouseNo, String strOwnerNo,
			String strPlanNo, int i, int j) {
		String strSql = "";
		if(strPlanNo!=null && !strPlanNo.equals(""))
		{
			strSql = "select a.plan_type from FCDATA_REQUEST_M a " +
					"where a.warehouse_no='" +warehouseNo+
					"' and a.enterprise_no='"+enterpriseNo+
					"' and a.plan_no='"+strPlanNo+"'";
		}
		List list=genDao.getListByNativeSql(strSql);
		return  (List<String>)list;
	}
	
	// 初盘发单
	public MsgRes send(String strCheckD,String dockNo) throws Exception {
		List<Fcdata_CheckDModel> fcd=JSON.parseArray(strCheckD, Fcdata_CheckDModel.class);
		
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		outList.add("S");
		for(int i=0;i<fcd.size();i++){
			List inList=new ArrayList();
			inList.add(fcd.get(i).getEnterpriseNo());
			inList.add(fcd.get(i).getWarehouseNo());
			inList.add(fcd.get(i).getOwnerNo());
			inList.add(fcd.get(i).getRgstName());//strUserId
			inList.add(fcd.get(i).getCheckNo());//strCheckNo
			inList.add(fcd.get(i).getCheckType());//strCheckType
			inList.add(dockNo);//strDockNo码头
			inList.add(fcd.get(i).getFcdataType());
			inList.add(fcd.get(i).getPrintType());
			resultList=genDao.execProc(inList, outList, "PKLG_FCDATA.P_SendTask");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
		}
		return new MsgRes(true,TipUtil.getTipsByKey("E30210"),null);//发单成功！
	}
    //////////////////////////////////////////////////////////////////////////////////////////
	
	
	/////////////////////////////////////复盘、三盘发单///////////////////////////////////////////
	
	// 获得盘点头档单 
	@Override
	public ExtListDataBo<Fcdata_CheckMModel> getCheckM(
			String enterpriseNo,String warehouseNo,
			String workerOwner, String strCheckType, String strDifferentFlag,
			PageBo pageBo) throws Exception {
		String strSql = "select distinct a.check_no,a.fcdata_type,a.owner_no  from" +
				" fcdata_check_m a ,fcdata_plan_m c  " +
				" where a.enterprise_no = c.enterprise_no " +        
				" and a.warehouse_no = c.warehouse_no " +
				" and a.plan_no = c.plan_no " +        
				" and a.fcdata_type=1" +
                " and a.WAREHOUSE_NO='"+warehouseNo+"' "+
				" and a.enterprise_no='"+enterpriseNo+"' "+
				" and a.OWNER_NO in("+workerOwner+",'N')" + 
				" and c.status not in('10','13')";

		if(strCheckType.equals("2")){//二盘
			if(strDifferentFlag.equals("0")){//无差异
				
			   strSql+=" and ((a.check_type='1' and a.status in ('13','11')) or (a.check_type='2' and a.status='11')) "+
	                   "and not exists    "+
	                   "(select 'x' from fcdata_check_d fcd " +
	                   "where a.enterprise_no=fcd.enterprise_no   "+ 
	                   "and a.warehouse_no=fcd.warehouse_no and a.check_no=fcd.check_no    "+
	                   "and fcd.different_flag='1') ";
			}else{//有差异
			   strSql+=" and a.check_type='2' "+
		            " and exists " +  
		            " (select 'x' from fcdata_check_d fcd where a.enterprise_no=fcd.enterprise_no " +  
		            " and a.warehouse_no=fcd.warehouse_no and a.check_no=fcd.check_no " +  
		            " and fcd.different_flag='1') " +   
		            " and a.status ='11' " ;
			}
		}else if(strCheckType.equals("3")){//三盘
			strSql+= " and a.status=11" +
					" and a.check_type='3' " ;
		}
		 
        String totsql = "select count(*) from " +"("+strSql+")";			
		
		List<Fcdata_CheckMModel> list = genDao.getListByNativeSql(strSql,Fcdata_CheckMModel.class,pageBo.getStart(), 10000/*pageBo.getPagesize()*/);
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Fcdata_CheckMModel> extListBo= new ExtListDataBo<Fcdata_CheckMModel>(list, count);
        return extListBo;
	}
	
	// 获得盘点明细
	@Override
	public ExtListDataBo<Fcdata_CheckDModel> getCheckD(	
			String enterpriseNo,
			String warehouseNo,
			String workerOwner,
			String strWheresql,
			String strCheckType,
			PageBo pageBo)throws Exception {
		String strSql = "";
		if(strCheckType.equals("3"))
		{
			strSql= "select distinct a.cell_no, b.ware_no, b.area_no," +
					" b.stock_no, a.warehouse_no, a.check_no, c.fcdata_type," +
					" c.rgst_name, c.check_type, case when a.cell_no in (" +
					" select distinct a.cell_no from fcdata_check_d a " +
					" where a.check_no='"+strWheresql+"'" +
					" and a.enterprise_no='" +enterpriseNo+"' "+
					" and a.warehouse_no='"+warehouseNo+"' "+
					" and a.different_flag='1'   ) then 'true' else 'false' end  as difFlag," +
					" case when  a.cell_no in ( select distinct a.cell_no from fcdata_check_d a " +
					" where a.check_no='"+strWheresql+"'" +
					" and a.enterprise_no='" +enterpriseNo+"' "+
					" and a.warehouse_no='"+warehouseNo+"' "+
					" and a.different_flag='1'   ) then '有差异' else '无差异' end differentFlagText" +
					" from fcdata_check_d a, cdef_defcell b, fcdata_check_m c " +
					" where a.enterprise_no=c.enterprise_no "  +
					" and a.warehouse_no=c.warehouse_no " +
					" and a.check_no=c.check_no " +
					" and a.enterprise_no=b.enterprise_no "+
					" and a.warehouse_no=b.warehouse_no "+
					" and a.cell_no=b.cell_no " +
					" and a.cell_no in( select distinct a.cell_no from" +
					" fcdata_check_d a  where a.check_no='"+strWheresql+"' " +
					" and a.enterprise_no='" +enterpriseNo+"' "+
					" and a.warehouse_no='"+warehouseNo+"' "+
					" and a.different_flag='1'   )" +
					" and c.status='11' " +
					" and a.enterprise_no='" +enterpriseNo+"' "+
					" and a.WAREHOUSE_NO='"+warehouseNo+"' "+
					" and a.OWNER_NO in("+workerOwner+",'N')" +
					" and a.check_no='"+strWheresql+"'";	
		}else
		{			
			strSql= "select distinct a.cell_no, b.ware_no, b.area_no," +
					" b.stock_no, a.enterprise_no, a.warehouse_no, a.check_no, c.fcdata_type," +
					" c.rgst_name, c.check_type, case when a.cell_no in (" +
					" select distinct a.cell_no from fcdata_check_d a" +
					" where a.check_no='"+strWheresql+"' " +
					" and a.enterprise_no='" +enterpriseNo+"' "+
					" and a.warehouse_no='"+warehouseNo+"' "+
					" and a.different_flag='1'   ) then 'true' else 'false' end  as difFlag," +
					" case when  a.cell_no in ( select distinct a.cell_no from fcdata_check_d a " +
					" where a.check_no='"+strWheresql+"'" +
					" and a.enterprise_no='" +enterpriseNo+"' "+
					" and a.warehouse_no='"+warehouseNo+"' "+
					" and a.different_flag='1'   ) then '有差异' else '无差异' end differentFlagText" +
					" from fcdata_check_d a, cdef_defcell b, fcdata_check_m c " +
					" where a.enterprise_no=c.enterprise_no " +
					" and a.warehouse_no=c.warehouse_no" +
					" and a.check_no=c.check_no " +
					" and a.enterprise_no=b.enterprise_no "+
					" and a.warehouse_no=b.warehouse_no "+
					" and a.cell_no=b.cell_no  " +
					" and a.WAREHOUSE_NO='"+warehouseNo+"' " +
					" and a.enterprise_no='" +enterpriseNo+"' "+
					" and a.OWNER_NO in("+workerOwner+",'N')" +
					" and a.check_no='"+strWheresql+"'";	
		}
		strSql += "group by a.cell_no, b.ware_no, b.area_no," +
				  "b.stock_no, a.enterprise_no, a.warehouse_no, " +
				  "a.check_no, c.fcdata_type,c.rgst_name,c.check_type ";
		List<Fcdata_CheckDModel> list = genDao.getListByNativeSql(strSql,Fcdata_CheckDModel.class,pageBo.getStart(), 10000);
		Integer count = genDao.getCountByNativeSql("select count(*) from ("+strSql+")");
		ExtListDataBo<Fcdata_CheckDModel> extListBo= new ExtListDataBo<Fcdata_CheckDModel>(list, count);
        return extListBo;
	}
	
	
	// 复盘/三盘发单
	public MsgRes sendAgain(String strCheckD,String strCheckType,String dockNo) throws Exception {
		List<Fcdata_CheckDModel> fcd=JSON.parseArray(strCheckD, Fcdata_CheckDModel.class);
		
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		outList.add("S");
		if(strCheckType.equals("2")){
			for(int i=0;i<fcd.size();i++){
				List inList=new ArrayList();
				inList.add(fcd.get(i).getEnterpriseNo());
				inList.add(fcd.get(i).getWarehouseNo());
				inList.add(fcd.get(i).getOwnerNo());
				inList.add(fcd.get(i).getRgstName());//strUserId
				inList.add(fcd.get(i).getCheckNo());//strCheckNo
				inList.add(fcd.get(i).getCheckType());
				inList.add(dockNo);//strDockNo码头
				inList.add(i);
				inList.add(fcd.get(i).getPrintType());
				inList.add(fcd.get(i).getCellNo());
				resultList=genDao.execProc(inList, outList, "PKLG_FCDATA.P_SendTaskAgain");
				if(resultList.get(0).toString().indexOf("N|")!=-1){
					throw new Exception(resultList.get(0).toString());
				}
			}
		}else if(strCheckType.equals("3")){
			List inList=new ArrayList();
			inList.add(fcd.get(0).getEnterpriseNo());
			inList.add(fcd.get(0).getWarehouseNo());
			inList.add(fcd.get(0).getOwnerNo());
			inList.add(fcd.get(0).getRgstName());//strUserId
			inList.add(fcd.get(0).getCheckNo());//strCheckNo
			inList.add(fcd.get(0).getCheckType());
			inList.add(dockNo);//strDockNo码头
			inList.add(0);
			inList.add(fcd.get(0).getPrintType());
			inList.add("N");
			resultList=genDao.execProc(inList, outList, "PKLG_FCDATA.P_SendTaskAgain");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
		}
		
		return new MsgRes(true,TipUtil.getTipsByKey("E30210"),null);//发单成功！
	}
	

	
	// 结束盘点
	public MsgRes sendEndFcdata(String enterpriseNo,String warehouseNo,String strCheckNo, String rgstName) throws Exception {
		String sql="update fcdata_check_m " +
					"set status=13," +
					"UPDT_NAME='"+rgstName+"'," +
					"UPDT_DATE=sysdate " +
					"where status=11 " +
					"and check_no in('"+strCheckNo+"') " +
					"and enterprise_no='"+enterpriseNo+"' " +
					"and warehouse_no='"+warehouseNo+"' ";
		genDao.updateBySql(sql);
		
		sql="update fcdata_check_d " +
		"set status=13," +
		"RECHECK_WORKER='"+rgstName+"'," +
		"RECHECK_DATE=sysdate " +
		"where status=10 " +
		"and check_no in('"+strCheckNo+"') "+
		"and enterprise_no='"+enterpriseNo+"' " +
		"and warehouse_no='"+warehouseNo+"' ";;
		genDao.updateBySql(sql);
		return new MsgRes(true,TipUtil.getTipsByKey("E30213"),null);//发单成功！
	}
		
}
