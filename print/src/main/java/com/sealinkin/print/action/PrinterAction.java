package com.sealinkin.print.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.print.service.IPrinterService;
import com.sealinkin.bset.model.Bset_User_ListModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.model.PrintArgsBo;
import com.sealinkin.comm.model.RepairReportInfoBo;
import com.sealinkin.comm.service.HttpService;
import com.sealinkin.util.StringUtil;

public class PrinterAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private IPrinterService printerImpl;
	private String reportId;
	private String sourceNo;
	private MsgRes msgRes;
	private String subReportId;
	private String reportType;
	private String date;
	private String printStr;
	private String htyFlag;
	private String labelNoList;
	private String printNum;
	private String hty;
	private String labelNo;
	
	/**
	 * 自动打印服务
	 */
	public void Auto(){
		Bset_User_ListModel us=(Bset_User_ListModel)HttpService.getRequest().getSession().getAttribute("loginUser");
		String warehouseNo = us.getWarehouseNo();
		String workStationNo = us.getWorkSpaceNo();
		String rgstName = us.getWorkerNo();
		try {
			if(StringUtil.isEmptyAll(new String[]{warehouseNo,workStationNo,sourceNo,reportId,rgstName}))
			{msgRes =  new MsgRes(false,"参数不完整!","");}
			else{
				PrintArgsBo pageArgs = new PrintArgsBo();
				pageArgs.setReportId(reportId);
				pageArgs.setRgstName(rgstName);
				pageArgs.setSourceNo(sourceNo);
				pageArgs.setWarehouseNo(warehouseNo);
				pageArgs.setWorkStationNo(workStationNo);
				pageArgs.setEnterpriseNo(super.getMdBdef_DefWorker().getCurrEnterpriseNo());
				
				System.out.println(super.getMdBdef_DefWorker().getCurrEnterpriseNo());
				
				List<PrintArgsBo> list  = new ArrayList<PrintArgsBo>();
				list.add(pageArgs);
				msgRes = printerImpl.sendPrintService(list);
			}
		} catch (Exception e) {
			msgRes = new MsgRes(false,e.getMessage(),"");
			e.printStackTrace();
		}
		super.writeObj(msgRes);
	}
	
	@SuppressWarnings("unchecked")
	public void Batch(){
		try {
			String warehouseNo = super.getMdBdef_DefWorker().getWarehouseNo();
			String workStationNo = super.getMdBdef_DefWorker().getWorkSpaceNo();
			String rgstName = super.getMdBdef_DefWorker().getWorkerNo();
			
			if(StringUtil.isEmptyAll(new String[]{warehouseNo,workStationNo,rgstName}))
			{msgRes =  new MsgRes(false,"参数不完整!","");}
			
			
			Collection<PrintArgsBo> collection = JSONArray.toCollection(JSONArray.fromObject(printStr),PrintArgsBo.class);
			List<PrintArgsBo> listRepairReportInfoBo = (List<PrintArgsBo>)collection;
			
			List<PrintArgsBo> list  = new ArrayList<PrintArgsBo>();
			
			for (PrintArgsBo repairReportInfoBo : listRepairReportInfoBo) {
				PrintArgsBo bo = new PrintArgsBo();
				bo.setReportId(repairReportInfoBo.getReportId());
				bo.setRgstName(rgstName);
				bo.setSourceNo(repairReportInfoBo.getSourceNo());
				bo.setWarehouseNo(warehouseNo);
				bo.setWorkStationNo(workStationNo);
				bo.setHtyFlag(StringUtil.isEmpty(getHtyFlag())?"0":getHtyFlag());
				bo.setEnterpriseNo(super.getMdBdef_DefWorker().getCurrEnterpriseNo());
				bo.setLabelNoList(labelNoList);
				bo.setPrintNum(this.getPrintNum());				
				list.add(bo);
			}
			msgRes = printerImpl.sendPrintService(list);
		} catch (Exception e) {
			msgRes = new MsgRes(false,e.getMessage(),"");
			e.printStackTrace();
		}
		super.writeObj(msgRes);
	}
	
	@SuppressWarnings("rawtypes")
	public void AueryReportList(){
		List list = null;
		try {
			list = printerImpl.queryReportList(getReportType());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void QueryRepairSheetInfoBoList(){
		ExtListDataBo<RepairReportInfoBo> list = null;
		try {
			PageBo pageBo = new PageBo(getStart(), getLimit());
			list = printerImpl.queryRepairSheetInfoBoList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					reportId , reportType ,sourceNo, date,hty, pageBo);
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void QueryLabelList(){
		ExtListDataBo<RepairReportInfoBo> list = null;
		try {
			list = printerImpl.queryLabelList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					reportId , 
					sourceNo,
					hty);
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printLabel(){
		try {
			msgRes = printerImpl.printLabel(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					this.getLabelNo() );
			
		} catch (Exception e) {
			msgRes = new MsgRes(false,e.getMessage(),"");
			e.printStackTrace();
		}
		super.writeObj(msgRes);
	}
	
	
	public void setPrinterImpl(IPrinterService printerImpl) {
		this.printerImpl = printerImpl;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getPrintStr() {
		return printStr;
	}
	public void setPrintStr(String printStr) {
		this.printStr = printStr;
	}
	public String getSubReportId() {
		return subReportId;
	}
	public void setSubReportId(String subReportId) {
		this.subReportId = subReportId;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public MsgRes getMsgRes() {
		return msgRes;
	}
	public void setMsgRes(MsgRes msgRes) {
		this.msgRes = msgRes;
	}
	
	public String getSourceNo() {
		return sourceNo;
	}
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	public String getHtyFlag() {
		return htyFlag;
	}

	public void setHtyFlag(String htyFlag) {
		this.htyFlag = htyFlag;
	}

	public String getLabelNoList() {
		return labelNoList;
	}

	public void setLabelNoList(String labelNoList) {
		this.labelNoList = labelNoList;
	}

	public String getPrintNum() {
		return printNum;
	}

	public void setPrintNum(String printNum) {
		this.printNum = printNum;
	}

	public String getHty() {
		return hty;
	}

	public void setHty(String hty) {
		this.hty = hty;
	}

	public String getLabelNo() {
		return labelNo;
	}

	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
		
}
