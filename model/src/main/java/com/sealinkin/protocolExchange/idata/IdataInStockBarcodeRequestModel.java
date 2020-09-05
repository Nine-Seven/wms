package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;
/**
 * 上架扫描条码 对应客户端StuIMInStockBarcodeRequest
 * @author lich
 *
 */
public class IdataInStockBarcodeRequestModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String enterpriseNo;//企业
	private String WarehouseNo;//仓别
	private String OwnerNo; //货主
	private String serialNo;//流水号
	private String barcode;//条码
	private String poNo;//源单号
	public String getWarehouseNo() {
		return WarehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		WarehouseNo = warehouseNo;
	}
	public String getOwnerNo() {
		return OwnerNo;
	}
	public void setOwnerNo(String ownerNo) {
		OwnerNo = ownerNo;
	}	
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	
	
}
