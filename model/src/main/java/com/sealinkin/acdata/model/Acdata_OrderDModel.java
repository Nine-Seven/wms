package com.sealinkin.acdata.model;

import com.sealinkin.acdata.po.Acdata_OrderD;

public class Acdata_OrderDModel extends Acdata_OrderD {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderNo;
	private String articleName;
	private String barcodeNo;
	private Long orderQty;
	private Double orderWt;
	private Double orderVl;
	private Long inQty;
	private Double inWt;
	private Double inVl;
	private Long signQty;
	private Double signWt;
	private Double signVl;
	private String remark;
	private Long istockQty;
	private Double istockWt;
	private Double istockVl;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getBarcodeNo() {
		return barcodeNo;
	}
	public void setBarcodeNo(String barcodeNo) {
		this.barcodeNo = barcodeNo;
	}
	public Long getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(Long orderQty) {
		this.orderQty = orderQty;
	}
	public Double getOrderWt() {
		return orderWt;
	}
	public void setOrderWt(Double orderWt) {
		this.orderWt = orderWt;
	}
	public Double getOrderVl() {
		return orderVl;
	}
	public void setOrderVl(Double orderVl) {
		this.orderVl = orderVl;
	}
	public Long getInQty() {
		return inQty;
	}
	public void setInQty(Long inQty) {
		this.inQty = inQty;
	}
	public Double getInWt() {
		return inWt;
	}
	public void setInWt(Double inWt) {
		this.inWt = inWt;
	}
	public Double getInVl() {
		return inVl;
	}
	public void setInVl(Double inVl) {
		this.inVl = inVl;
	}
	public Long getSignQty() {
		return signQty;
	}
	public void setSignQty(Long signQty) {
		this.signQty = signQty;
	}
	public Double getSignWt() {
		return signWt;
	}
	public void setSignWt(Double signWt) {
		this.signWt = signWt;
	}
	public Double getSignVl() {
		return signVl;
	}
	public void setSignVl(Double signVl) {
		this.signVl = signVl;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getIstockQty() {
		return istockQty;
	}
	public void setIstockQty(Long istockQty) {
		this.istockQty = istockQty;
	}
	public Double getIstockWt() {
		return istockWt;
	}
	public void setIstockWt(Double istockWt) {
		this.istockWt = istockWt;
	}
	public Double getIstockVl() {
		return istockVl;
	}
	public void setIstockVl(Double istockVl) {
		this.istockVl = istockVl;
	}
	
}
