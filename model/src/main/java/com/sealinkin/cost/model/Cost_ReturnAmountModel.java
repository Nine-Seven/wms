package com.sealinkin.cost.model;

import java.util.Date;

import com.sealinkin.cost.po.Cost_ReturnAmount;

public class Cost_ReturnAmountModel extends Cost_ReturnAmount {

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String checkNo;
	private Short rowId;
	private String rgstName;
	private Date rgstDate;
	private String rgstDateText;
	private String rgstNameText;
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
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public Short getRowId() {
		return rowId;
	}
	public void setRowId(Short rowId) {
		this.rowId = rowId;
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
	public String getRgstDateText() {
		return rgstDateText;
	}
	public void setRgstDateText(String rgstDateText) {
		this.rgstDateText = rgstDateText;
	}
	public String getRgstNameText() {
		return rgstNameText;
	}
	public void setRgstNameText(String rgstNameText) {
		this.rgstNameText = rgstNameText;
	}
	public String getCostFlagText() {
		return costFlagText;
	}
	public void setCostFlagText(String costFlagText) {
		this.costFlagText = costFlagText;
	}
}