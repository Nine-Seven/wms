package com.sealinkin.idata.model;

import com.sealinkin.idata.po.Idata_OrderStatus;

public class Idata_OrderStatusModel extends Idata_OrderStatus{

	private static final long serialVersionUID = 1L;
	
	private String warehouseNo;
	private String orderSerial;
	private String startTime;
	private String endTime;
	private String dockText;
	private String statusText;
	private String suppliersText;
	private String requestDateText;
	private String supplierName;
	private String articleItems;
	private String volumn;
	private String weight;
	private String pkQty;
	private String ownerNoText;
	
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getOrderSerial() {
		return orderSerial;
	}
	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getDockText() {
		return dockText;
	}
	public void setDockText(String dockText) {
		this.dockText = dockText;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getSuppliersText() {
		return suppliersText;
	}
	public void setSuppliersText(String suppliersText) {
		this.suppliersText = suppliersText;
	}
	public String getRequestDateText() {
		return requestDateText;
	}
	public void setRequestDateText(String requestDateText) {
		this.requestDateText = requestDateText;
	}
	public String getArticleItems() {
		return articleItems;
	}
	public void setArticleItems(String articleItems) {
		this.articleItems = articleItems;
	}
	public String getVolumn() {
		return volumn;
	}
	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getPkQty() {
		return pkQty;
	}
	public void setPkQty(String pkQty) {
		this.pkQty = pkQty;
	}
	public String getOwnerNoText() {
		return ownerNoText;
	}
	public void setOwnerNoText(String ownerNoText) {
		this.ownerNoText = ownerNoText;
	}
	
}
