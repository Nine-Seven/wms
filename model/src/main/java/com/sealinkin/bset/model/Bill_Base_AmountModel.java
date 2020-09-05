package com.sealinkin.bset.model;

import com.sealinkin.bset.po.Bill_BaseAmount;

public class Bill_Base_AmountModel extends Bill_BaseAmount {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String serialNo;
	private String billingProject;
	private String amountDateText;
	private String flagText;
	private String projectText;
	private String fixedValue;
	private String billingCycle;
	private String billingCycleText;
	private Double unitPrice;
	
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
	public String getBillingProject() {
		return billingProject;
	}
	public void setBillingProject(String billingProject) {
		this.billingProject = billingProject;
	}
	public String getAmountDateText() {
		return amountDateText;
	}
	public void setAmountDateText(String amountDateText) {
		this.amountDateText = amountDateText;
	}
	public String getFlagText() {
		return flagText;
	}
	public void setFlagText(String flagText) {
		this.flagText = flagText;
	}
	public String getProjectText() {
		return projectText;
	}
	public void setProjectText(String projectText) {
		this.projectText = projectText;
	}
	public String getFixedValue() {
		return fixedValue;
	}
	public void setFixedValue(String fixedValue) {
		this.fixedValue = fixedValue;
	}
	public String getBillingCycle() {
		return billingCycle;
	}
	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}
	public String getBillingCycleText() {
		return billingCycleText;
	}
	public void setBillingCycleText(String billingCycleText) {
		this.billingCycleText = billingCycleText;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
}
