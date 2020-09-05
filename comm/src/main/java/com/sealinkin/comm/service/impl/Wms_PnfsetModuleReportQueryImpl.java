package com.sealinkin.comm.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.IWms_PnfsetModuleReportQuery;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;
import com.sealinkin.wms.model.Wms_PntsetModuleReportQueryModel;
import com.sealinkin.wms.model.Wms_PntsetModuleReportSetModel;
import com.sealinkin.wms.model.Wms_PntsetmoduleReportModel;

@SuppressWarnings({"unchecked","rawtypes"})
public class Wms_PnfsetModuleReportQueryImpl implements IWms_PnfsetModuleReportQuery{

	private IGenericManager genDao;
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	@Override
	public List<Wms_PntsetModuleReportQueryModel> getModuleReportQuery(String enterpriseNo,String moduleId) {
		// TODO Auto-generated method stub
		String sql="select * " +
				   " from PNTSET_MODULE_REPORT_QUERY " +
				   " where 1=1 and module_id='"+moduleId+"' " +
				   " and enterprise_no='" +enterpriseNo+"' "+
				   " order by orderNo";
		List<Wms_PntsetModuleReportQueryModel> list=genDao.getListByNativeSql(sql, Wms_PntsetModuleReportQueryModel.class, 0, 100);				
		return list;
	}
	//获取来源单号
	@Override
	public ExtListDataBo<Wms_PntsetmoduleReportModel> getSourceNoList(String enterpriseNo,String warehouseNo,String ownerNo, String moduleId, String strQuery,
			PageBo pageBo) {
		// TODO Auto-generated method stub
		String sql="";
		String preparedSql="select * from pntset_module_report_set where module_id='"+moduleId+"'";
		List<Wms_PntsetModuleReportSetModel> listPntsetModuleReportSet=genDao.getListByNativeSql(preparedSql, Wms_PntsetModuleReportSetModel.class);
		if(listPntsetModuleReportSet.isEmpty()){
			return null;
		}
		sql=listPntsetModuleReportSet.get(0).getSqlText();
		sql=sql.replace("%s0", enterpriseNo);
	    sql=sql.replace("%s1", warehouseNo);
	    sql=sql.replace("%s2", ownerNo);
		List<Wms_PntsetmoduleReportModel> list =null; 
		Integer count = 0; 		
		ExtListDataBo<Wms_PntsetmoduleReportModel> extListBo=null;
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
		}
		list = genDao.getListByNativeSql(sql,Wms_PntsetmoduleReportModel.class, pageBo.getStart(), pageBo.getPagesize());	
		count = genDao.getCountByNativeSql("select count(*) from ("+sql+")");
		extListBo= new ExtListDataBo<Wms_PntsetmoduleReportModel>(list, count);
		return extListBo;
	}
	@Override
	public ExtListDataBo<Wms_PntsetmoduleReportModel> getLabelNoList(
			String enterpriseNo,String warehouseNo,String ownerNo, String moduleId,String sourceNo,String containerNo, PageBo pageBo) {
		// TODO Auto-generated method stub
		String sql="";
		String preparedSql="select * from pntset_module_report_set where module_id='"+moduleId+"'";
		List<Wms_PntsetModuleReportSetModel> listPntsetModuleReportSet=genDao.getListByNativeSql(preparedSql, Wms_PntsetModuleReportSetModel.class);
		if(listPntsetModuleReportSet.isEmpty()){
			return null;
		}
		sql=listPntsetModuleReportSet.get(0).getSqlText2();
		//替换查询参数	
	    sql=sql.replace("%s0", enterpriseNo);
	    sql=sql.replace("%s1", warehouseNo);
	    sql=sql.replace("%s2", ownerNo);
	    sql=sql.replace("%s3", sourceNo);
	    if(!containerNo.isEmpty()){
	    	sql=sql.replace("%s4", containerNo);
	    }
		List<Wms_PntsetmoduleReportModel> list =
				genDao.getListByNativeSql(sql,Wms_PntsetmoduleReportModel.class, pageBo.getStart(), pageBo.getPagesize()); 
		Integer count = 0; 			
		count = genDao.getCountByNativeSql("select count(*) from ("+sql+")");
		ExtListDataBo<Wms_PntsetmoduleReportModel> extListBo= 
				new ExtListDataBo<Wms_PntsetmoduleReportModel>(list, count);
		return extListBo;
	}
	
	/**
	 * 补印-通过配置表获取报表ID的Sql语句 供前台界面调用 huangb 20160617
	 * @param strRecvData
	 * @throws Exception
	 */
	@Override
	public MsgRes getReportSqlBySet(Wms_PntsetmoduleReportModel reqMod) throws Exception{
		MsgRes msgRes=new MsgRes();
		List<Wms_PntsetmoduleReportModel> List_StuMod = new ArrayList<Wms_PntsetmoduleReportModel>();
				
		String[] s=reqMod.getId().getReportType().split(",");
		for(int i=0;i<s.length;i++){
			//保存商品扫描数据
			List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();
			outList.add("S");
			outList.add("S");
			inList.add(reqMod.getEnterpriseNo());//strEnterpriseNo 企业
			inList.add(reqMod.getWarehouseNo());//strWarehouseNo 仓别号
			inList.add(reqMod.getId().getPaperType());//strPaperType 单据类型 例如'HO','IP','SS'等wms内部产生的单据类型，可为标签		
			inList.add(s[i]);//strReportType 报表类型 L:表单；M:标签头档；D:标签明细
			inList.add(reqMod.getSourceNo());//strSourceNo 来源单号
			
			System.out.println(inList);
			
			resultList = genDao.execProc(inList, outList, "PKLG_WMS_Public.p_GetReportIdUI");
		
			if(resultList.get(1).toString().indexOf("N|")!=-1)
			{
				msgRes.setIsSucc(false); 
				msgRes.setMsg(resultList.get(1).toString());
				return msgRes; 
			}
			
			//获取报表信息
			String Sql = resultList.get(0).toString();
			List<Wms_PntsetmoduleReportModel> stuMod = genDao.getListByNativeSql(Sql,Wms_PntsetmoduleReportModel.class);
			if(!stuMod.isEmpty()){
				List_StuMod.addAll(stuMod);
			}
			
			
		}
		
		if(List_StuMod.size() <= 0){
			msgRes.setIsSucc(false); 
			msgRes.setMsg("获取报表信息失败");
			return msgRes;
		}
		
		msgRes.setIsSucc(true); 
//		msgRes.setObj(JSONArray.fromObject(stuMod));
		msgRes.setObj(List_StuMod);
		msgRes.setMsg("获取报表信息成功");
		return msgRes;
	}
	@Override
	public ExtListDataBo<Wms_PntsetmoduleReportModel> getReportData(
			String enterpriseNo, String warehouseNo,String ownerNo, String moduleId,
			String sourceNo,String containerNo, PageBo pageBo) {
		// TODO Auto-generated method stub
		String sql="";
		String preparedSql="select * from pntset_module_report_set where module_id='"+moduleId+"'";
		List<Wms_PntsetModuleReportSetModel> listPntsetModuleReportSet=genDao.getListByNativeSql(preparedSql, Wms_PntsetModuleReportSetModel.class);
		if(listPntsetModuleReportSet.isEmpty()){
			return null;
		}
		sql=listPntsetModuleReportSet.get(0).getSqlText3();
		//替换查询参数	
	    sql=sql.replace("%s0", enterpriseNo);
	    sql=sql.replace("%s1", warehouseNo);
	    sql=sql.replace("%s2", ownerNo);
	    sql=sql.replace("%s3", sourceNo);
	    if(!containerNo.isEmpty()){
	    	sql=sql.replace("%s4", containerNo);
	    }
		List<Wms_PntsetmoduleReportModel> list =
				genDao.getListByNativeSql(sql,Wms_PntsetmoduleReportModel.class, pageBo.getStart(), pageBo.getPagesize()); 
		Integer count = 0; 			
		count = genDao.getCountByNativeSql("select count(*) from ("+sql+")");
		ExtListDataBo<Wms_PntsetmoduleReportModel> extListBo= 
				new ExtListDataBo<Wms_PntsetmoduleReportModel>(list, count);
		return extListBo;
	}
	
	/**
	 * 写打印任务
	 * czh
	 * 2016.6.27
	 */
	@Override
	public MsgRes printReportOrLabel(Wms_PntsetmoduleReportModel reqMod,String workerNo,String workSpaceNo) throws Exception{
		MsgRes msgRes=new MsgRes();
		//List<Wms_PntsetmoduleReportModel> List_StuMod = new ArrayList<Wms_PntsetmoduleReportModel>();

			//保存报表打印数据
			List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();
			outList.add("S");
			outList.add("S");
			if(reqMod.getTaskType()=="B"){
				inList.add(reqMod.getEnterpriseNo());//strEnterpriseNo 企业
				inList.add(reqMod.getWarehouseNo());//strWarehouseNo 仓别号
				inList.add(reqMod.getSourceNo());//strSourceNo 来源单号
				inList.add(reqMod.getReportId());//strReportId 打印报表ID
				inList.add("0");//strBackFlag 后台打印标识
				inList.add(workSpaceNo);//strDockNo 码头或者工作站号
				inList.add("1");//strReprintFlag 补印标识
				inList.add(workerNo);//strUserNo 操作人员
				inList.add("1");//strAddDetailFlag 是否新增打印任务明细 0-不新增;1-新增
				inList.add(reqMod.getContainerNo());//strContainerNo 需要打印的系统内部容器号
				inList.add(Float.parseFloat("1"));//nSerialNo 
				inList.add(Float.parseFloat("1"));//NPrintQty
			}else{
				inList.add(reqMod.getEnterpriseNo());//strEnterpriseNo 企业
				inList.add(reqMod.getWarehouseNo());//strWarehouseNo 仓别号
				inList.add(reqMod.getSourceNo());//strSourceNo 来源单号
				inList.add(reqMod.getReportId());//strReportId 打印报表ID
				inList.add("0");//strBackFlag 后台打印标识
				inList.add(workSpaceNo);//strDockNo 码头或者工作站号
				inList.add("1");//strReprintFlag 补印标识
				inList.add(workerNo);//strUserNo 操作人员
				inList.add("0");//strAddDetailFlag 是否新增打印任务明细 0-不新增;1-新增
				inList.add("N");//strContainerNo 需要打印的系统内部容器号
				inList.add(Float.parseFloat("1"));//nSerialNo 
				inList.add(Float.parseFloat("1"));//NPrintQty
			}
			System.out.println(inList);
			resultList = genDao.execProc(inList, outList, "PKOBJ_PRINTTASK.p_Insert_TaskInfo");
		
			if(resultList.get(1).toString().indexOf("N|")!=-1)
			{
				msgRes.setIsSucc(false); 
				msgRes.setMsg(resultList.get(1).toString());
				return msgRes; 
			}
		
		msgRes.setIsSucc(true); 
		msgRes.setObj(resultList.get(0).toString());
		msgRes.setMsg("打印成功");
		return msgRes;
	}

}
