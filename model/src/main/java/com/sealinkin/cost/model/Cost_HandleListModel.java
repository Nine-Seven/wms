package com.sealinkin.cost.model;

import com.sealinkin.cost.po.Cost_HandleList;

public class Cost_HandleListModel extends Cost_HandleList {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String serialNo;
	private String amountDateText;
	private String costFlagText;
	private String projectText;
	private String statusText;
	private Double unitPrice;
	private String billingType;
	private String billingTypeText;
	private String accountNoText;
	private String ownerNoText;
	
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
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getAmountDateText() {
		return amountDateText;
	}
	public void setAmountDateText(String amountDateText) {
		this.amountDateText = amountDateText;
	}
	public String getCostFlagText() {
		return costFlagText;
	}
	public void setCostFlagText(String costFlagText) {
		this.costFlagText = costFlagText;
	}
	public String getProjectText() {
		return projectText;
	}
	public void setProjectText(String projectText) {
		this.projectText = projectText;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getBillingType() {
		return billingType;
	}
	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}
	public String getBillingTypeText() {
		return billingTypeText;
	}
	public void setBillingTypeText(String billingTypeText) {
		this.billingTypeText = billingTypeText;
	}
	public String getAccountNoText() {
		return accountNoText;
	}
	public void setAccountNoText(String accountNoText) {
		this.accountNoText = accountNoText;
	}
	public String getOwnerNoText() {
		return ownerNoText;
	}
	public void setOwnerNoText(String ownerNoText) {
		this.ownerNoText = ownerNoText;
	}
}
