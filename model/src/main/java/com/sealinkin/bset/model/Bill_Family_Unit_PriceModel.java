package com.sealinkin.bset.model;

import com.sealinkin.bset.po.Bill_FamilyUnitPrice;

public class Bill_Family_Unit_PriceModel extends Bill_FamilyUnitPrice {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String billingProject;
	private String billingType;
	private String billingCycleText;
	private String billingFlagText;
	private String billingUnitText;
	private String appendConditionText;
	private String projectText;
	private String billingTypeText;
	private String statusText;
	
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
	public String getBillingProject() {
		return billingProject;
	}
	public void setBillingProject(String billingProject) {
		this.billingProject = billingProject;
	}
	public String getBillingCycleText() {
		return billingCycleText;
	}
	public void setBillingCycleText(String billingCycleText) {
		this.billingCycleText = billingCycleText;
	}
	public String getBillingFlagText() {
		return billingFlagText;
	}
	public void setBillingFlagText(String billingFlagText) {
		this.billingFlagText = billingFlagText;
	}
	public String getBillingUnitText() {
		return billingUnitText;
	}
	public void setBillingUnitText(String billingUnitText) {
		this.billingUnitText = billingUnitText;
	}
	public String getAppendConditionText() {
		return appendConditionText;
	}
	public void setAppendConditionText(String appendConditionText) {
		this.appendConditionText = appendConditionText;
	}
	public String getProjectText() {
		return projectText;
	}
	public void setProjectText(String projectText) {
		this.projectText = projectText;
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
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}	
}
