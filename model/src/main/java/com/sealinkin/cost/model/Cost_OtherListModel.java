package com.sealinkin.cost.model;

import java.util.Date;

import com.sealinkin.cost.po.Cost_OtherList;

public class Cost_OtherListModel extends Cost_OtherList {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String costNo;
	private Date costDate;
	private String sourceNo;
	
	private String ownerNoText;
	private String costNoText;
	private String costDateText;
	private String costFlagText;
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
	public String getCostNo() {
		return costNo;
	}
	public void setCostNo(String costNo) {
		this.costNo = costNo;
	}
	public Date getCostDate() {
		return costDate;
	}
	public void setCostDate(Date costDate) {
		this.costDate = costDate;
	}
	public String getSourceNo() {
		return sourceNo;
	}
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}
	public String getOwnerNoText() {
		return ownerNoText;
	}
	public void setOwnerNoText(String ownerNoText) {
		this.ownerNoText = ownerNoText;
	}
	public String getCostNoText() {
		return costNoText;
	}
	public void setCostNoText(String costNoText) {
		this.costNoText = costNoText;
	}
	public String getCostDateText() {
		return costDateText;
	}
	public void setCostDateText(String costDateText) {
		this.costDateText = costDateText;
	}
	public String getCostFlagText() {
		return costFlagText;
	}
	public void setCostFlagText(String costFlagText) {
		this.costFlagText = costFlagText;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	
}
