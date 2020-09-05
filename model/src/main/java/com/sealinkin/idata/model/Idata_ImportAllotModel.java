package com.sealinkin.idata.model;

import com.sealinkin.idata.po.Idata_ImportAllot;

public class Idata_ImportAllotModel extends Idata_ImportAllot{

	private static final long serialVersionUID = 1L;
	
	private String warehouseNo;
	private String importNo;
	private String ownerNo;
	private String articleNo;
	private double packingQty;
	private String subCustNo;
	private String custNo;
	private String poNo;
	
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getImportNo() {
		return importNo;
	}
	public void setImportNo(String importNo) {
		this.importNo = importNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public double getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(double packingQty) {
		this.packingQty = packingQty;
	}
	public String getSubCustNo() {
		return subCustNo;
	}
	public void setSubCustNo(String subCustNo) {
		this.subCustNo = subCustNo;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	
	

}
