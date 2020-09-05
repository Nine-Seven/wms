package com.sealinkin.cost.model;

import com.sealinkin.cost.po.Cost_FormulaDiscount;

public class Cost_FormulaDiscountModel extends Cost_FormulaDiscount {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String ownerNo;
	private String billingProject;
	private String ladder;
	private String enterpriseNo;
	private String discountFlagText;
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
	public String getLadder() {
		return ladder;
	}
	public void setLadder(String ladder) {
		this.ladder = ladder;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getDiscountFlagText() {
		return discountFlagText;
	}
	public void setDiscountFlagText(String discountFlagText) {
		this.discountFlagText = discountFlagText;
	}
	
	
}
