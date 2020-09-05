package com.sealinkin.protocolExchange.fcdata;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ReqFCScanBarcodeForArticleModel implements Serializable{
	private String barcode;
	private String warehouseNo;
	private String ownerNo;
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
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
	
}
