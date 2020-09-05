package com.sealinkin.rodata.model;

import com.sealinkin.rodata.po.Rodata_OutstockM;


public class Rodata_OutstockMModel extends Rodata_OutstockM{
	private static final long serialVersionUID = 1L;
	private String outstockNo;
	private String warehouseNo;
	private String ownerNo;
	
	private String  supplierNo;
	private String  supplierName;
	
	private String statusText;
	private String poNo;
	private String recedeNo;
	private String classType;
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getOutstockNo() {
		return outstockNo;
	}
	public void setOutstockNo(String outstockNo) {
		this.outstockNo = outstockNo;
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
	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getRecedeNo() {
		return recedeNo;
	}
	public void setRecedeNo(String recedeNo) {
		this.recedeNo = recedeNo;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	
	
}
