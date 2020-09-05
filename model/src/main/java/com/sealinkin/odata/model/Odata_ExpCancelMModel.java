package com.sealinkin.odata.model;


import com.sealinkin.odata.po.Odata_ExpCancelM;

public class Odata_ExpCancelMModel extends Odata_ExpCancelM{
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String cancelNo;
	
	private String sourceexpNo;
	private String handleFlagText;
	private String statusText;
	private String suorceTypeText;
	
	public String getHandleFlagText() {
		return handleFlagText;
	}
	public void setHandleFlagText(String handleFlagText) {
		this.handleFlagText = handleFlagText;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getCancelNo() {
		return cancelNo;
	}
	public void setCancelNo(String cancelNo) {
		this.cancelNo = cancelNo;
	}
	
	public String getSourceexpNo() {
		return sourceexpNo;
	}
	public void setSourceexpNo(String sourceexpNo) {
		this.sourceexpNo = sourceexpNo;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getSuorceTypeText() {
		return suorceTypeText;
	}
	public void setSuorceTypeText(String suorceTypeText) {
		this.suorceTypeText = suorceTypeText;
	}
	
}
