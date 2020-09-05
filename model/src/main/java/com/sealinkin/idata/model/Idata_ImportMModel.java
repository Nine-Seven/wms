package com.sealinkin.idata.model;

import com.sealinkin.idata.po.Idata_ImportM;

public class Idata_ImportMModel extends Idata_ImportM{

	private static final long serialVersionUID = 1L;

	private String importNo;
	private String warehouseNo;
	private String ownerNo;
	private String ownerName;
	private String potypeText;
	private String supplierName;
	private String statusText;
	private String SImportNo;
	private String qty;
	private String articleItems;
	private double pkQty;
	private String unloadTypeText;
	private String overQtyFlag;//是否超量验收0:不超量；1：超量；2：超品 huangb 20160726
	
	public String getImportNo() {
		return importNo;
	}
	public void setImportNo(String importNo) {
		this.importNo = importNo;
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
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getPotypeText() {
		return potypeText;
	}
	public void setPotypeText(String potypeText) {
		this.potypeText = potypeText;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getSImportNo() {
		return SImportNo;
	}
	public void setSImportNo(String sImportNo) {
		SImportNo = sImportNo;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getArticleItems() {
		return articleItems;
	}
	public void setArticleItems(String articleItems) {
		this.articleItems = articleItems;
	}
	public double getPkQty() {
		return pkQty;
	}
	public void setPkQty(double pkQty) {
		this.pkQty = pkQty;
	}
	public String getUnloadTypeText() {
		return unloadTypeText;
	}
	public void setUnloadTypeText(String unloadTypeText) {
		this.unloadTypeText = unloadTypeText;
	}
	public String getOverQtyFlag() {
		return overQtyFlag;
	}
	public void setOverQtyFlag(String overQtyFlag) {
		this.overQtyFlag = overQtyFlag;
	}
	
}
