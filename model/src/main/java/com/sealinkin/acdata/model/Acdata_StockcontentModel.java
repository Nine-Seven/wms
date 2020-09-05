package com.sealinkin.acdata.model;

import com.sealinkin.acdata.po.Acdata_Stockcontent;

public class Acdata_StockcontentModel extends Acdata_Stockcontent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6032464874501359138L;
	private String warehouseNo;
	private String orderNo;
	private String sourceNo;
	private String articleName;
	private String alreadyQty;
	private String alreadyWt;
	private String alreadyVl;
	private String ostockQty;
	private String ostockWt;
	private String ostockVl;
	
	public String getAlreadyQty() {
		return alreadyQty;
	}
	public void setAlreadyQty(String alreadyQty) {
		this.alreadyQty = alreadyQty;
	}
	public String getAlreadyWt() {
		return alreadyWt;
	}
	public void setAlreadyWt(String alreadyWt) {
		this.alreadyWt = alreadyWt;
	}
	public String getAlreadyVl() {
		return alreadyVl;
	}
	public void setAlreadyVl(String alreadyVl) {
		this.alreadyVl = alreadyVl;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getSourceNo() {
		return sourceNo;
	}
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getOstockQty() {
		return ostockQty;
	}
	public void setOstockQty(String ostockQty) {
		this.ostockQty = ostockQty;
	}
	public String getOstockWt() {
		return ostockWt;
	}
	public void setOstockWt(String ostockWt) {
		this.ostockWt = ostockWt;
	}
	public String getOstockVl() {
		return ostockVl;
	}
	public void setOstockVl(String ostockVl) {
		this.ostockVl = ostockVl;
	}
	
}
