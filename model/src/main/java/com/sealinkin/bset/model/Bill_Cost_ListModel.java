package com.sealinkin.bset.model;

import java.util.Date;

import com.sealinkin.bset.po.Bill_Cost_List;

public class Bill_Cost_ListModel extends Bill_Cost_List {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4083952436781605844L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String billingNo;
	private String billingProject;
	private Date billingDate;
	private Double amount;
	private String flag;
	private Integer serialNo;
	private String appendCondition;
	private Double appendValue1;
	private Double appendValue2;
	private String billingUnit;
	private Double unitPrice;
	private Double qty;
	private Double value;
	private String status;
	private String statusText;
	
	
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
	
	public String getBillingNo() {
		return billingNo;
	}
	public void setBillingNo(String billingNo) {
		this.billingNo = billingNo;
	}
	public String getBillingProject() {
		return billingProject;
	}
	public void setBillingProject(String billingProject) {
		this.billingProject = billingProject;
	}
	public Date getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Integer getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	public String getAppendCondition() {
		return appendCondition;
	}
	public void setAppendCondition(String appendCondition) {
		this.appendCondition = appendCondition;
	}
	public Double getAppendValue1() {
		return appendValue1;
	}
	public void setAppendValue1(Double appendValue1) {
		this.appendValue1 = appendValue1;
	}
	public Double getAppendValue2() {
		return appendValue2;
	}
	public void setAppendValue2(Double appendValue2) {
		this.appendValue2 = appendValue2;
	}
	public String getBillingUnit() {
		return billingUnit;
	}
	public void setBillingUnit(String billingUnit) {
		this.billingUnit = billingUnit;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
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
	
}
