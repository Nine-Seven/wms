package com.sealinkin.jk.model;

import java.util.Date;

import com.sealinkin.jk.po.JkBymSheetwarehouse;

public class JkBymSheetwarehouseModel extends JkBymSheetwarehouse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sheetNo;
	private String warehouseNo;
	private String rgstName;
	private Date rgstDate;
	private String corpkey;
	private String sheetType;
	private String dateText;
	
	
	public String getSheetNo() {
		return sheetNo;
	}
	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
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
	public String getCorpkey() {
		return corpkey;
	}
	public void setCorpkey(String corpkey) {
		this.corpkey = corpkey;
	}
	public String getDateText() {
		return dateText;
	}
	public void setDateText(String dateText) {
		this.dateText = dateText;
	}
	public String getSheetType() {
		return sheetType;
	}
	public void setSheetType(String sheetType) {
		this.sheetType = sheetType;
	}
	
	
	
}
