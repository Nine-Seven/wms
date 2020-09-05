package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;
/**
 * 箱码采集条码扫描 stuBoxBarcodeRequest
 * @author lich
 *
 */
public class BoxBarcodeRequestModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String WarehouseNo;
	private String barcode;
    private String serialNo;
	public String getWarehouseNo() {
		return WarehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		WarehouseNo = warehouseNo;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}   
}
