package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;
/**
 * stuArrangePrintDLabelReq
 * @author wyf
 *
 */
@SuppressWarnings("serial")
public class ReqArrangePrintDLabel implements Serializable{
	private String EnterpriseNo;
	private String WareHouseNo;
	private String DockNo;//码头
	private String DLabelNo;//目的容器
    private String UserID;//操作人员
	public String getEnterpriseNo() {
		return EnterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		EnterpriseNo = enterpriseNo;
	}
	public String getWareHouseNo() {
		return WareHouseNo;
	}
	public void setWareHouseNo(String wareHouseNo) {
		WareHouseNo = wareHouseNo;
	}
	public String getDockNo() {
		return DockNo;
	}
	public void setDockNo(String dockNo) {
		DockNo = dockNo;
	}
	public String getDLabelNo() {
		return DLabelNo;
	}
	public void setDLabelNo(String dLabelNo) {
		DLabelNo = dLabelNo;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
}
