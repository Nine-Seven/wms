package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;

public class IdataGetBarcodeRequestModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String EnterpriseNo;
	private String WarehouseNo;
	private String Barcode;
	private String ArticleNo;
	private String PoNo;
	private String DockNo;
	private String PrinterGroupNo;
	public String getEnterpriseNo() {
		return EnterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		EnterpriseNo = enterpriseNo;
	}
	public String getWarehouseNo() {
		return WarehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		WarehouseNo = warehouseNo;
	}
	public String getBarcode() {
		return Barcode;
	}
	public void setBarcode(String barcode) {
		Barcode = barcode;
	}
	public String getArticleNo() {
		return ArticleNo;
	}
	public void setArticleNo(String articleNo) {
		ArticleNo = articleNo;
	}
	public String getPoNo() {
		return PoNo;
	}
	public void setPoNo(String poNo) {
		PoNo = poNo;
	}
	public String getDockNo() {
		return DockNo;
	}
	public void setDockNo(String dockNo) {
		DockNo = dockNo;
	}
	public String getPrinterGroupNo() {
		return PrinterGroupNo;
	}
	public void setPrinterGroupNo(String printerGroupNo) {
		PrinterGroupNo = printerGroupNo;
	}
	
}
