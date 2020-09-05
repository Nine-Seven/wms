package com.sealinkin.protocolExchange.ridata;

import java.io.Serializable;

public class RIGetSaveInfoModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String untreadNo;
	private String ownerNo;
	private String quality;
	private String classType;
	private String articleNo;
	private String packingQty;
	private String supplierNo;
	private String articleName;
	private String style;
	private String waveNo;
	private String checkqty;
	private String qty;
	private String Barcode;
	private String untreadType;
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(String packingQty) {
		this.packingQty = packingQty;
	}
	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getWaveNo() {
		return waveNo;
	}
	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
	}
	public String getUntreadNo() {
		return untreadNo;
	}
	public void setUntreadNo(String untreadNo) {
		this.untreadNo = untreadNo;
	}
	public String getCheckQty() {
		return checkqty;
	}
	public void setCheckQty(String checkqty) {
		this.checkqty = checkqty;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}	
	public String getBarcode() {
		return Barcode;
	}
	public void setBarcode(String Barcode) {
		this.Barcode = Barcode;
	}
	public String getCheckqty() {
		return checkqty;
	}
	public void setCheckqty(String checkqty) {
		this.checkqty = checkqty;
	}
	public String getUntreadType() {
		return untreadType;
	}
	public void setUntreadType(String untreadType) {
		this.untreadType = untreadType;
	}
	
	
}
