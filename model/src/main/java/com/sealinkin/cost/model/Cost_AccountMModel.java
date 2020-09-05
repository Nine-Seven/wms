package com.sealinkin.cost.model;

import java.util.Date;

import com.sealinkin.cost.po.Cost_AccountM;

public class Cost_AccountMModel extends Cost_AccountM {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String accountGroupNo;
	private String accountCycle;
	private String balanceDay;
	private String status;
	private String remark;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String accountCycleText;
	private String balanceDayText;
	private String statusText;
	private String rgstDateText;
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
	public String getAccountCycle() {
		return accountCycle;
	}
	public void setAccountCycle(String accountCycle) {
		this.accountCycle = accountCycle;
	}
	public String getBalanceDay() {
		return balanceDay;
	}
	public void setBalanceDay(String balanceDay) {
		this.balanceDay = balanceDay;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRgstName() {
		return rgstName;
	}
	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}
	public Date getRgstDate() {
		return rgstDate;
	}
	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}
	public String getUpdtName() {
		return updtName;
	}
	public void setUpdtName(String updtName) {
		this.updtName = updtName;
	}
	public Date getUpdtDate() {
		return updtDate;
	}
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}
	public String getBalanceDayText() {
		return balanceDayText;
	}
	public void setBalanceDayText(String balanceDayText) {
		this.balanceDayText = balanceDayText;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getAccountCycleText() {
		return accountCycleText;
	}
	public void setAccountCycleText(String accountCycleText) {
		this.accountCycleText = accountCycleText;
	}
	public String getRgstDateText() {
		return rgstDateText;
	}
	public void setRgstDateText(String rgstDateText) {
		this.rgstDateText = rgstDateText;
	}
}
