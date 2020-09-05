package com.sealinkin.print.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSON;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sealinkin.bdef.model.Bdef_DefarticleModel;
import com.sealinkin.comm.model.DocumentTypeModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.print.service.IPrinterTagService;
import com.sealinkin.protocolExchange.print.AnsFieldDescModel;
import com.sealinkin.protocolExchange.print.AnsPrintTaskModel;
import com.sealinkin.protocolExchange.print.AnsReportInfoModel;
import com.sealinkin.protocolExchange.print.CProgramDescModel;
import com.sealinkin.protocolExchange.print.PrintTaskInfoModel;
import com.sealinkin.protocolExchange.print.ReqPrintTaskModel;
import com.sealinkin.protocolExchange.print.ReqReportInfoModel;
import com.sealinkin.protocolExchange.print.ReqUpdatePrintTaskModel;
import com.sealinkin.util.CommUtil;
import com.sealinkin.wms.model.Wms_DefreportModel;

@SuppressWarnings({"rawtypes","unchecked"})
public class PrinterTagImpl implements IPrinterTagService {
private IGenericManager genDao;
    private JdbcTemplate jdbcTemplate;
    
	public IGenericManager getGenDao()
    {
        return genDao;
    }
	public void setGenDao(IGenericManager genDao)
    {
        this.genDao = genDao;
    }
	
	@Override
	public ExtListDataBo<Bdef_DefarticleModel> getDefarticle(
			String workerOwner, String strQuery, PageBo poPagebo) {
		
		String strSql="select a.* from bdef_defarticle a " +
				" where 1=1 ";
		
		String strTotsql="select count(1) from bdef_defarticle a where 1=1";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
			strTotsql=strTotsql+strWs;
		}
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			strSql=strSql+" and a.owner_no in("+workerOwner+") ";
			strTotsql=strTotsql+" and a.owner_no in("+workerOwner+")";
		}else
		{
			strSql=strSql+" and 1=2";
			strTotsql=strTotsql+" and 1=2";
		}
		
		List<Bdef_DefarticleModel> list = genDao.getListByNativeSql(strSql,Bdef_DefarticleModel.class,poPagebo.getStart(),poPagebo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		
		ExtListDataBo<Bdef_DefarticleModel> extListBo= new ExtListDataBo<Bdef_DefarticleModel>(list, intCount);
        return extListBo;
	
	}
	@Override
	public MsgRes Get_PrintTask(String strRecvData) throws Exception {
		ReqPrintTaskModel reqMod=JSON.parseObject(strRecvData, ReqPrintTaskModel.class);
		String strSql="select " +
						" distinct  m.task_no," +
						" m.task_type,m.report_id, " +
						" e.printer_name as PrintName," +
						" m.reprint_flag " +
					" from " +
						" job_printtask_m  m ," +
						" PNTDEF_REPORT c ," +
						" PNTSET_PRINTER_GROUP d ," +
						" pntset_grpgather_printer_group a," +
						" pntset_paper_type b, " +
						" PNTDEF_DEFPRINTER e      "+
					" where " +
						" m.report_id = c.report_id " +
						" and d.printer_group_no = m.printer_group_no " +
						" and d.warehouse_no = m.warehouse_no " +
						" and d.enterprise_no = m.enterprise_no " +
						" and d.printer_no = e.printer_no " +
						" and d.warehouse_no  = e.warehouse_no " +
						" and d.enterprise_no = e.enterprise_no " +						
						" and m.task_type = b.task_type " +
						" and b.warehouse_no = m.warehouse_no " +
						" and b.enterprise_no = m.enterprise_no " +
						" and c.paper_type_no = b.paper_type_no" +		
						" and e.paper_type_no = b.paper_type_no" +						
						" and d.warehouse_no = a.warehouse_no " +
						" and d.enterprise_no = a.enterprise_no " +
						" and d.PRINTER_GROUP_NO = a.PRINTER_GROUP_NO " +
						" and c.sub_report_id = 0 " +
						" and m.back_flag = 0 " +
//						" and m.reprint_flag = 0 " +
						" and e.status = 1 " +
						" and d.warehouse_no = '"+reqMod.getWarehouseNo()+"' " +
						" and d.enterprise_no= '" +reqMod.getEnterpriseNo()+"' " +
						" and a.GRPGATHER_NO= '"+reqMod.getPrintGroups()+"' " +
					" order by m.task_no ";
		List<PrintTaskInfoModel> list = genDao.getListByNativeSql(strSql,PrintTaskInfoModel.class);
		if(list.size()>0)
		{
			AnsPrintTaskModel ansMod=new AnsPrintTaskModel();
			ansMod.setLstPrintTask(list);
			return new MsgRes(true,"",JSON.toJSON(ansMod));
		}
		else
		{
			return new MsgRes(false,"",null);
		}
	}
	@Override
	public MsgRes Update_PrintTask(String strRecvData) throws Exception {
		ReqUpdatePrintTaskModel reqMod=JSON.parseObject(strRecvData, ReqUpdatePrintTaskModel.class);
		
		String strSql  = "";
		//插入历史主表
		strSql = "insert into job_printtask_mhty(" +
					"warehouse_no, " +
					"task_no, " +
					"source_no, " +
					"back_flag, " +
					"task_type, " +
					"report_id, " +
					"printer_group_no, " +
					"operate_date, " +
					"reprint_flag, " +
					"rgst_name, " +
					"rgst_date, " +
					"updt_name, " +
					"updt_date, " +
					"hty_flag," +
					"enterprise_no)" +
				"select " +
					"m.WAREHOUSE_NO, " +
					"m.TASK_NO, " +
					"m.SOURCE_NO, " +
					"m.BACK_FLAG, " +
					"m.TASK_TYPE," +
					"m.REPORT_ID, " +
					"m.PRINTER_GROUP_NO, " +
					"m.OPERATE_DATE, " +
					"m.REPRINT_FLAG , " +
					"m.RGST_NAME, " +
					"m.RGST_DATE, " +
					"m.RGST_NAME," +
					"sysdate," +
					"hty_flag," +
					"m.enterprise_no " +
				"from " +
					"JOB_PRINTTASK_M m  " +
				"where " +
					" m.task_no = '"+reqMod.getTaskNo()+"'" +
					" and m.warehouse_no ='"+reqMod.getWarehouseNo()+"' " +
					" and m.enterprise_no='" +reqMod.getEnterpriseNo()+"' ";
		genDao.updateBySql(strSql);
		//查询历史从表
		strSql = "insert into JOB_PRINTTASK_DHTY " +
				"select * " +
				"from " +
					"job_printtask_d d " +
				"where " +
					" d.task_no = '"+reqMod.getTaskNo()+"' " +
					" and d.warehouse_no ='"+reqMod.getWarehouseNo()+"' " +
					" and d.enterprise_no='" +reqMod.getEnterpriseNo()+"' ";
		genDao.updateBySql(strSql);
		
		//删除任务主表
		strSql = "delete from JOB_PRINTTASK_M " +
				" where task_no = '"+reqMod.getTaskNo()+"' " +
				" and warehouse_no ='"+reqMod.getWarehouseNo()+"' " +
				" and enterprise_no='" +reqMod.getEnterpriseNo()+"' ";
		genDao.updateBySql(strSql);
		//删除任务从表
		strSql = "delete from JOB_PRINTTASK_D " +
				" where task_no = '"+reqMod.getTaskNo()+"' " +
				" and warehouse_no ='"+reqMod.getWarehouseNo()+"' " +
				" and enterprise_no='" +reqMod.getEnterpriseNo()+"' ";
		genDao.updateBySql(strSql);
		return new MsgRes(true,"","更新成功！");
	}
	@Override
	public MsgRes Get_ReportInfo(String strRecvData) throws Exception {
		ReqReportInfoModel reqMod=JSON.parseObject(strRecvData, ReqReportInfoModel.class);
		//取报表相关信息
		String strSql="select * from PNTDEF_REPORT a " +
				"where a.report_id='"+reqMod.getReportID()+"' order by a.SUB_REPORT_ID,a.SQL_ORDER ";
		List<Wms_DefreportModel> list=genDao.getListByNativeSql(strSql, Wms_DefreportModel.class);
		
		//取打印任务类型0：工作标签或报表 1：历史标签或报表 
		strSql="select " +
					"a.hty_flag " +
				"from " +
					"job_printtask_m a " +
				"where " +
					"a.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
					"and a.enterprise_no='" +reqMod.getEnterpriseNo()+"' "+
					"and a.task_no='"+reqMod.getTaskNo()+"'";
		
		String strHtyFlag=genDao.getListByNativeSql(strSql).get(0).toString();
		//拼接取报表数据sql
		strSql="";
		
		for(Wms_DefreportModel w:list)
		{
			if("1".equals(strHtyFlag)){
				strSql+=w.getSqlTextHty();
			}else{
				strSql+=w.getSqlText();
			}
		}
		strSql=strSql.replace("%s0", reqMod.getEnterpriseNo());
		strSql=strSql.replace("%s1", reqMod.getWarehouseNo());
		strSql=strSql.replace("%s2", reqMod.getTaskNo());
		
		List listReportInfo =null;
		listReportInfo=jdbcTemplate.queryForList(strSql);
		
		//获取打印份数		
		AnsReportInfoModel ansMod=new AnsReportInfoModel();
		
		ansMod.setStrReportType(list.get(0).getReportType());
		
		List<String> lstReportName=new ArrayList<String>();
		List<Object> lstDtReport=new ArrayList<Object>();
		
		lstReportName.add(list.get(0).getReportFilename());
		ansMod.setLstReportName(lstReportName);
		
		//获取不到打印份数，则打印份数置为1
		ansMod.setReportCopies(list.get(0).getReportCopies());
		
		lstDtReport.add(listReportInfo);
		ansMod.setLstDtReport(lstDtReport);

		return new MsgRes(true,"",JSON.toJSON(ansMod));
	}
	@Override
	public MsgRes Get_FieldInfo(String strRecvData) throws Exception {
		String strSql="select distinct upper(a.field_id) as field_id,a.field_name,a.tool_tips,a.s_field_name,a.s_tool_tips,a.desc_flag from wms_deffielddesc a  where rownum<10011 ";
		List<CProgramDescModel> list=genDao.getListByNativeSql(strSql, CProgramDescModel.class);
		
		Map<String, CProgramDescModel> map=new HashMap<String, CProgramDescModel>();
		for(CProgramDescModel c:list)
		{
			map.put(c.getFieldId(), c);
		}
		AnsFieldDescModel ansMod=new AnsFieldDescModel();
		ansMod.setArrStuField(map);
		return new MsgRes(true,"",JSON.toJSON(ansMod));
	}
	public JdbcTemplate getJdbcTemplate()
	{
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public MsgRes sendTask(String printNumber, String barcode, String enterpriseNo,
			String warehouseNo, String workStationNo, String rgstName,String articleNo)
			throws Exception {
			
		String sql=" select count(*) from PNTSET_PRINTER_WORKSTATION " +
				    "where WAREHOUSE_NO='"+warehouseNo+ "' " +
				    "and enterprise_no='" +enterpriseNo+"' "+
					"and WORKSTATION_NO='"+workStationNo+"'";   
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
	
		sql=" select PRINTER_GROUP_NO from PNTSET_PRINTER_WORKSTATION " +
			" where WAREHOUSE_NO='"+warehouseNo+"' " +
			" and enterprise_no='" +enterpriseNo+"' "+
			" and WORKSTATION_NO='"+workStationNo+"'";
		
		List<String> list = genDao.getListByNativeSql(sql);
		
		String printerGroupNo=list.get(0);
		
		sql="INSERT INTO JOB_PRINTTASK_M(enterprise_no,warehouse_No,TASK_NO,SOURCE_NO,BACK_FLAG,TASK_TYPE,REPRINT_FLAG, REPORT_ID,PRINTER_GROUP_NO,OPERATE_DATE,RGST_NAME,RGST_DATE)"+
            " values ('"+
				enterpriseNo+"','"+
				warehouseNo+"','"+
				taskNo+"','"+
				articleNo+"',"+
				"'0','B','0','AR1994CM','"+
				printerGroupNo+"',sysdate,'"+
				rgstName+"',sysdate)";
		
		genDao.exceuteSql(sql);
		
		
		int number = Integer.valueOf(printNumber).intValue();
		sql=" INSERT INTO JOB_PRINTTASK_D(enterprise_no,Warehouse_No,TASK_NO,CONTAINER_NO,PRINT_SN,PRINT_QTY)"+
            " values ('"+
                enterpriseNo+"','"+
				warehouseNo+"','"+
				taskNo+"','"+
				barcode+"',"+
				number+","+
				number+")";
		
		System.out.println(sql);
		
		genDao.exceuteSql(sql);
		
		return new MsgRes(true,"打印指令发送成功","");	
	}
}
