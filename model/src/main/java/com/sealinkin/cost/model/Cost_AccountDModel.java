package com.sealinkin.cost.model;

import com.sealinkin.cost.po.Cost_AccountD;

public class Cost_AccountDModel extends Cost_AccountD {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String accountGroupNo;
	private String accountNo;
	private String accountTypeText;
	
	private String accountCycle;
	private String balanceDay;
	private String status;
	private String remark_m;
	private String accountCycleText;
	private String balanceDayText;
	private String statusText;
	private String ownerNoText;
	private String accountName;

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
	public String getAccountGroupNo() {
		return accountGroupNo;
	}
	public void setAccountGroupNo(String accountGroupNo) {
		this.accountGroupNo = accountGroupNo;
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
	public String getAccountCycle() {
		return accountCycle;
	}
	public void setAccountCycle(String accountCycle) {
		this.accountCycle = accountCycle;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getRemark_m() {
		return remark_m;
	}
	public void setRemark_m(String remark_m) {
		this.remark_m = remark_m;
	}
	public String getBalanceDay() {
		return balanceDay;
	}
	public void setBalanceDay(String balanceDay) {
		this.balanceDay = balanceDay;
	}
	public String getAccountCycleText() {
		return accountCycleText;
	}
	public void setAccountCycleText(String accountCycleText) {
		this.accountCycleText = accountCycleText;
	}
	public String getBalanceDayText() {
		return balanceDayText;
	}
	public void setBalanceDayText(String balanceDayText) {
		this.balanceDayText = balanceDayText;
	}
	public String getOwnerNoText() {
		return ownerNoText;
	}
	public void setOwnerNoText(String ownerNoText) {
		this.ownerNoText = ownerNoText;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
