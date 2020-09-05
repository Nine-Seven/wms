package com.sealinkin.cost.model;

import java.util.Date;

import com.sealinkin.cost.po.Cost_Financial;

public class Cost_FinancialModel extends Cost_Financial {

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String accountNo;
	private String checkNo;
	private Double amount;
	private Double discountAmount;
	private Double realAmount;
	private Date beginDate;
	private Date endDate;
	private String flag;
	private Date buildDate;
	private String status;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String createFlag;
	private String costFlag;
	private String accountGroupNo;
	private Double otherCost1;
	private Double otherCost2;
	private Double otherCost3;
	private Double otherCost4;
	private Double otherCost5;
	private String ownerNoText;
	private String accountNoText;
	private String statusText;
	private String flagText;
	private String buildDateText;
	private String beginDateText;
	private String endDateText;
	private Double total;
	private Double spareAmount;
	private Double planAmount;
	private String discountFlag;
	private String costFlagText;
	
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
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Double getRealAmount() {
		return realAmount;
	}
	public void setRealAmount(Double realAmount) {
		this.realAmount = realAmount;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Date getBuildDate() {
		return buildDate;
	}
	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getCreateFlag() {
		return createFlag;
	}
	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}
	public String getCostFlag() {
		return costFlag;
	}
	public void setCostFlag(String costFlag) {
		this.costFlag = costFlag;
	}
	public String getAccountGroupNo() {
		return accountGroupNo;
	}
	public void setAccountGroupNo(String accountGroupNo) {
		this.accountGroupNo = accountGroupNo;
	}
	public Double getOtherCost1() {
		return otherCost1;
	}
	public void setOtherCost1(Double otherCost1) {
		this.otherCost1 = otherCost1;
	}
	public Double getOtherCost2() {
		return otherCost2;
	}
	public void setOtherCost2(Double otherCost2) {
		this.otherCost2 = otherCost2;
	}
	public Double getOtherCost3() {
		return otherCost3;
	}
	public void setOtherCost3(Double otherCost3) {
		this.otherCost3 = otherCost3;
	}
	public Double getOtherCost4() {
		return otherCost4;
	}
	public void setOtherCost4(Double otherCost4) {
		this.otherCost4 = otherCost4;
	}
	public Double getOtherCost5() {
		return otherCost5;
	}
	public void setOtherCost5(Double otherCost5) {
		this.otherCost5 = otherCost5;
	}
	public String getOwnerNoText() {
		return ownerNoText;
	}
	public void setOwnerNoText(String ownerNoText) {
		this.ownerNoText = ownerNoText;
	}
	public String getAccountNoText() {
		return accountNoText;
	}
	public void setAccountNoText(String accountNoText) {
		this.accountNoText = accountNoText;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getFlagText() {
		return flagText;
	}
	public void setFlagText(String flagText) {
		this.flagText = flagText;
	}
	public String getBuildDateText() {
		return buildDateText;
	}
	public void setBuildDateText(String buildDateText) {
		this.buildDateText = buildDateText;
	}
	public String getBeginDateText() {
		return beginDateText;
	}
	public void setBeginDateText(String beginDateText) {
		this.beginDateText = beginDateText;
	}
	public String getEndDateText() {
		return endDateText;
	}
	public void setEndDateText(String endDateText) {
		this.endDateText = endDateText;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getSpareAmount() {
		return spareAmount;
	}
	public void setSpareAmount(Double spareAmount) {
		this.spareAmount = spareAmount;
	}
	public Double getPlanAmount() {
		return planAmount;
	}
	public void setPlanAmount(Double planAmount) {
		this.planAmount = planAmount;
	}
	public String getDiscountFlag() {
		return discountFlag;
	}
	public void setDiscountFlag(String discountFlag) {
		this.discountFlag = discountFlag;
	}
	public String getCostFlagText() {
		return costFlagText;
	}
	public void setCostFlagText(String costFlagText) {
		this.costFlagText = costFlagText;
	}
}