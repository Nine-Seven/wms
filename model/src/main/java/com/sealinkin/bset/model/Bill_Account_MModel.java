package com.sealinkin.bset.model;

import com.sealinkin.bset.po.Bill_Account_M;

public class Bill_Account_MModel extends Bill_Account_M {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String accountNo;
	private String accountTypeText;
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
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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
	
}
