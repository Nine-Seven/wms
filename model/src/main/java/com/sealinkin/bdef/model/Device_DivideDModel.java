package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Device_DivideD;

public class Device_DivideDModel extends Device_DivideD {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String deviceNo;
	private String deviceCellNo;
	private String statusText;
	private String mixSupplierText;
	private String mixFlagText;
	private String deviceName;
	private String countBayX;
	private String countStockY;
	
	
	
	
	public String getCountBayX() {
		return countBayX;
	}
	public void setCountBayX(String countBayX) {
		this.countBayX = countBayX;
	}
	public String getCountStockY() {
		return countStockY;
	}
	public void setCountStockY(String countStockY) {
		this.countStockY = countStockY;
	}
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
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getDeviceCellNo() {
		return deviceCellNo;
	}
	public void setDeviceCellNo(String deviceCellNo) {
		this.deviceCellNo = deviceCellNo;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getMixSupplierText() {
		return mixSupplierText;
	}
	public void setMixSupplierText(String mixSupplierText) {
		this.mixSupplierText = mixSupplierText;
	}
	public String getMixFlagText() {
		return mixFlagText;
	}
	public void setMixFlagText(String mixFlagText) {
		this.mixFlagText = mixFlagText;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
}
