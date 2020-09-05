package com.sealinkin.stock.model;


import com.sealinkin.stock.po.Stock_ConfirmM;

public class Stock_ConfirmMModel extends Stock_ConfirmM{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String confirmNo;
	private String poNo;
	private String planTypeText;
	private String statusText;
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
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getConfirmNo() {
		return confirmNo;
	}
	public void setConfirmNo(String confirmNo) {
		this.confirmNo = confirmNo;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getPlanTypeText() {
		return planTypeText;
	}
	public void setPlanTypeText(String planTypeText) {
		this.planTypeText = planTypeText;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	
}
