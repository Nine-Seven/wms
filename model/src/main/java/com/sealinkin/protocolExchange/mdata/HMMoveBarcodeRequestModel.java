package com.sealinkin.protocolExchange.mdata;

/**
 * 移库扫描条码请求StuHMMoveBarcodeRequest
 * @author lich
 *
 */
public class HMMoveBarcodeRequestModel {
	private String EnterpriseNo;
	private String warehouseNo;
    private String cellNo;
    private String barcode;
    
	public String getEnterpriseNo() {
		return EnterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		EnterpriseNo = enterpriseNo;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
}
