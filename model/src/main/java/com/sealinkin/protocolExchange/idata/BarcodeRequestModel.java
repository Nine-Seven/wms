package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;
/**
 * 扫描商品条码 对应客户端StuIMGetArticleByBarcodeRequest
 * @author lich
 *
 */
public class BarcodeRequestModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String WarehouseNo;
	private String barcode;
    private String ownerNo;
    private String SImportNo;
    private String ImportType;
    
    
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
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
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getSImportNo() {
		return SImportNo;
	}
	public void setSImportNo(String sImportNo) {
		SImportNo = sImportNo;
	}
	public String getImportType() {
		return ImportType;
	}
	public void setImportType(String importType) {
		ImportType = importType;
	}
}
