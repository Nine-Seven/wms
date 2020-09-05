package com.sealinkin.protocolExchange.print;

import java.io.Serializable;

/**
 * 更新打印任务请求  StuReqUpdatePrintTask
 * @author lich
 *
 */
public class ReqUpdatePrintTaskModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sessionID;      //对话ID 
	private String warehouseNo;    //仓库代码
	private String taskNo;    //组群信息
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
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}		
}
