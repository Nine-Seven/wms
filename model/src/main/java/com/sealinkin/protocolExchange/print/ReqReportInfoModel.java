package com.sealinkin.protocolExchange.print;

import java.io.Serializable;

/**
 * 获取报表详细信息请求  StuReqReportInfo
 * @author lich
 *
 */
public class ReqReportInfoModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sessionID;      //对话ID 
	private String warehouseNo;    //仓库代码
	private String reportID ;
	private String taskNo;
	private String containerNo;
	private String enterpriseNo;//企业	
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getReportID() {
		return reportID;
	}
	public void setReportID(String reportID) {
		this.reportID = reportID;
	}
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	public String getContainerNo() {
		return containerNo;
	}
	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}	
}
