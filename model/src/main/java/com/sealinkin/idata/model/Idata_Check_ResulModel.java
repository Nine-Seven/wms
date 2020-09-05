package com.sealinkin.idata.model;

/** 验收结果model
 * haungb 20160716
 */
public class Idata_Check_ResulModel{
	
	private String enterpriseNo;
	private String warehouseNo;
	private String owner;
	private String poNo;
	private String articleIdentifier;
	private String barcode;
	private String articleNo;
	private String articleName;
	private Double packingQty;
	private Double poQty;
	private Double importQty;
	
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
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getArticleIdentifier() {
		return articleIdentifier;
	}
	public void setArticleIdentifier(String articleIdentifier) {
		this.articleIdentifier = articleIdentifier;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public Double getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}
	public Double getPoQty() {
		return poQty;
	}
	public void setPoQty(Double poQty) {
		this.poQty = poQty;
	}
	public Double getImportQty() {
		return importQty;
	}
	public void setImportQty(Double importQty) {
		this.importQty = importQty;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
}
