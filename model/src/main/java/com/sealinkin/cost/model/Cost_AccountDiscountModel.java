package com.sealinkin.cost.model;

import com.sealinkin.cost.po.Cost_AccountDiscount;

public class Cost_AccountDiscountModel extends Cost_AccountDiscount {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String accountNo;
	private String accountLadder;
	private String discountFlag;
	private String accountLadderText;
	private String accountTypeText;
	private String discountFlagText;
	private String billingProjectText;
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
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountLadder() {
		return accountLadder;
	}
	public String getDiscountFlag() {
		return discountFlag;
	}
	public void setDiscountFlag(String discountFlag) {
		this.discountFlag = discountFlag;
	}
	public void setAccountLadder(String accountLadder) {
		this.accountLadder = accountLadder;
	}
	public String getAccountTypeText() {
		return accountTypeText;
	}
	public void setAccountTypeText(String accountTypeText) {
		this.accountTypeText = accountTypeText;
	}
	public String getDiscountFlagText() {
		return discountFlagText;
	}
	public void setDiscountFlagText(String discountFlagText) {
		this.discountFlagText = discountFlagText;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getAccountLadderText() {
		return accountLadderText;
	}
	public void setAccountLadderText(String accountLadderText) {
		this.accountLadderText = accountLadderText;
	}
	public String getBillingProjectText() {
		return billingProjectText;
	}
	public void setBillingProjectText(String billingProjectText) {
		this.billingProjectText = billingProjectText;
	}
	
}
