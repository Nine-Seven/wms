package com.sealinkin.acdata.model;

import com.sealinkin.acdata.po.Acdata_InstockD;

public class Acdata_InstockDModel extends Acdata_InstockD {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String instockNo;
	private String articleName;
	private String barcodeNo;
	private Long inQty;
	private Double inWt;
	private Double inVl;
	private Long istockQty;
	private Double istockWt;
	private Double istockVl;
	private String remark;
	
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getInstockNo() {
		return instockNo;
	}
	public void setInstockNo(String instockNo) {
		this.instockNo = instockNo;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
