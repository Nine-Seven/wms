package com.sealinkin.rodata.model;

import com.sealinkin.rodata.po.Rodata_RecedeM;


public class Rodata_RecedeMModel extends Rodata_RecedeM{
	private static final long serialVersionUID = 1L;
	private String recedeNo;
	private String warehouseNo;
	private String ownerNo;
	private String supplierName;
	private String statusText;
	private String locateTimes;
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getRecedeNo() {
		return recedeNo;
	}
	public void setRecedeNo(String recedeNo) {
		this.recedeNo = recedeNo;
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
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getLocateTimes() {
		return locateTimes;
	}
	public void setLocateTimes(String locateTimes) {
		this.locateTimes = locateTimes;
	}
	
}
