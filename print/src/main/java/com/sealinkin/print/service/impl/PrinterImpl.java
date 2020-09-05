package com.sealinkin.print.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.model.DefReportBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.model.PrintArgsBo;
import com.sealinkin.comm.model.RepairReportInfoBo;
import com.sealinkin.comm.model.WmsDefPrintCenterBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.print.service.IPrinterService;
import com.sealinkin.util.StringUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class PrinterImpl implements IPrinterService{
	
private IGenericManager genDao;
    
	public IGenericManager getGenDao()
    {
        return genDao;
    }
	public void setGenDao(IGenericManager genDao)
    {
        this.genDao = genDao;
    }

	@Override
	public MsgRes sendPrintService(List<PrintArgsBo> list)  throws Exception{
		String resultStr = "";
		for (PrintArgsBo printArgsBo : list) {
			
			List inputList = new ArrayList();
			inputList.add(printArgsBo.getEnterpriseNo());
			inputList.add(printArgsBo.getWarehouseNo());
			inputList.add(printArgsBo.getSourceNo());
			inputList.add("0");
			inputList.add(printArgsBo.getReportId());
			inputList.add(printArgsBo.getLabelNoList());
			inputList.add(printArgsBo.getWorkStationNo());
			inputList.add(StringUtil.isEmpty(printArgsBo.getHtyFlag())?"0":printArgsBo.getHtyFlag());
			inputList.add(printArgsBo.getPrintNum());
			inputList.add(printArgsBo.getRgstName());
			
			List outputList = new ArrayList();
			outputList.add("S");
			outputList.add("S");
			System.out.println(inputList);
			List resultList = genDao.execProc(inputList, outputList, "pkobj_printtask.p_insert_printCenter");
			
			if(resultList.size()>0){
				resultStr = resultList.get(1).toString();
				if(!resultStr.equals("Y")){
					throw new Exception(resultStr);
				}
			}
		}
		return new MsgRes(true,"打印指令发送成功","");
	}
	
	
	@Override
	public List<DefReportBo> queryReportList(String reportType) {
		String strSql="select REPORT_ID,REPORT_NAME,SQL_TEXT " +
				"from wms_defprint_center " +
				"where 1=1 ";
		if("B".equals(reportType))
		{
			strSql+=" and report_type='B' ";
		}else{
			strSql+=" and report_type='L' ";
		}
		strSql+="order by REPORT_NAME ";
		List<DefReportBo> list=genDao.getListByNativeSql(strSql,DefReportBo.class);
		return list;
	}
	
	@Override
	public ExtListDataBo<RepairReportInfoBo> queryRepairSheetInfoBoList(String enterpriseNo,String wareHouseNo, String reportId ,String reportType ,
			String sheetId, String date,String hty, PageBo page) {
		if(StringUtil.isEmptyAll(new String[]{reportId,reportType})){return null;}
		
		List sqlList = new ArrayList();
		if(hty.equals("0")){
			sqlList = genDao.getListByNativeSql(
				"select sql_text from wms_defprint_center where REPORT_ID = '"+reportId+"'  ",
				WmsDefPrintCenterBo.class);
		}else if(hty.equals("1")){
			sqlList = genDao.getListByNativeSql(
				"select sql_text_thy from wms_defprint_center where REPORT_ID = '"+reportId+"'  ",
				WmsDefPrintCenterBo.class);
		}
		String sql = "",buildSql="";
		if(sqlList.size()==0){
			return null;
		}else{
			if(hty.equals("0")){
			   sql = ((WmsDefPrintCenterBo)sqlList.get(0)).getSqlText();
			}else if(hty.equals("1")){
				 sql = ((WmsDefPrintCenterBo)sqlList.get(0)).getSqlTextThy();
			}
			
			if(StringUtil.isEmpty(sql)){return null;}
			
			if(StringUtil.isEmpty(sheetId)&&!StringUtil.isEmpty(date)){
				sheetId="";
				date = date.substring(0, date.indexOf("T"));
				date = date.replaceAll("-", "/");
			}else if(!StringUtil.isEmpty(sheetId)&&StringUtil.isEmpty(date)){
				date = "1900/01/01";
			}
			buildSql = sql.replaceAll("(?i):s0", enterpriseNo).replaceAll("(?i):s1", wareHouseNo).replaceAll("(?i):s2", date).replaceAll("(?i):s3", sheetId);
			
		}
		String buildSql1="select * from ("+buildSql+")";
		buildSql1 += " order by SOURCE_NO desc";
		List rootList = genDao.getListByNativeSql(buildSql1, RepairReportInfoBo.class, page.getStart(), page.getPagesize());
		Integer count = genDao.getCountByNativeSql("select count(*) from ("+buildSql1+")");
		return new ExtListDataBo<RepairReportInfoBo>(rootList, count);
	}
	@Override
	public ExtListDataBo<RepairReportInfoBo> queryLabelList(String enterpriseNo,String wareHouseNo,
			String reportId, String sheetId,String hty) {
		String sql = "",buildSql="";
		if(hty.equals("0")){
			sql="select sql_text2 " +
					"from wms_defprint_center " +
					"where REPORT_ID = '"+reportId+"'  ";
		}else if(hty.equals("1")){
			sql="select sql_tex_hty2 " +
					"from wms_defprint_center " +
					"where REPORT_ID = '"+reportId+"'  ";
			}
		List sqlList = genDao.getListByNativeSql(sql,WmsDefPrintCenterBo.class);
	
		if(sqlList.size()==0){
			return null;
		}else{
			if(hty.equals("0")){
				sql = ((WmsDefPrintCenterBo)sqlList.get(0)).getSqlText2();
			}else if(hty.equals("1")){
				sql = ((WmsDefPrintCenterBo)sqlList.get(0)).getSqlTexHty2();
				}
			if(StringUtil.isEmpty(sql)){
				return null;
			}			
			buildSql = sql.replaceAll("(?i):s0", enterpriseNo).replaceAll("(?i):s1", wareHouseNo).replaceAll("(?i):s2", sheetId);
		}
		String buildSql1="select * from ("+buildSql+")";
		buildSql1 += " order by SOURCE_NO desc";
		List<RepairReportInfoBo> rootList = genDao.getListByNativeSql(buildSql1, RepairReportInfoBo.class);
		Integer count = genDao.getCountByNativeSql("select count(*) from ("+buildSql1+")");
		return new ExtListDataBo<RepairReportInfoBo>(rootList, count);
	}
	
	//根据标签打印
	@Override
	public MsgRes printLabel(String currEnterpriseNo, String warehouseNo,
			String dockNo, String workerNo,String labelNo) throws Exception {
		
		List outList=new ArrayList();
		List resultList=new ArrayList();
		List inList=new ArrayList();
		
		outList.add("S");
		inList.add(currEnterpriseNo);
		inList.add(warehouseNo);
		inList.add(labelNo);
		inList.add(dockNo);
		inList.add(workerNo);
		
		System.out.println(inList);
		
		resultList=genDao.execProc(inList, outList, "PKLG_WMS_BASE.P_PrintLabel");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"打印指令发送成功","");
	}
}
