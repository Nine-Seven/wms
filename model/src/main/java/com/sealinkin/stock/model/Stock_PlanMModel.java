package com.sealinkin.stock.model;

import com.sealinkin.stock.po.Stock_PlanM;

public class Stock_PlanMModel extends Stock_PlanM{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String planNo;
	private String planTypeText;
	private String statusText;
	private String orgNoText;
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
	public String getPlanNo() {
		return planNo;
	}
	public void setPlanNo(String planNo) {
		this.planNo = planNo;
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
	public String getOrgNoText() {
		return orgNoText;
	}
	public void setOrgNoText(String orgNoText) {
		this.orgNoText = orgNoText;
	}
	
}
