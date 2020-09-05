package com.sealinkin.acdata.model;

import java.util.Date;

import com.sealinkin.acdata.po.Acdata_InstockM;

public class Acdata_InstockMModel extends Acdata_InstockM {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4472064730768101506L;
	private String warehouseNo;
	private String instockNo;
	private String orderNo;
	private String sourceNo;
	private String ownerAlias;
	private String custAlias;
	private String status;
	private String remark;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String statusText;
	
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
	public String getOwnerAlias() {
		return ownerAlias;
	}
	public void setOwnerAlias(String ownerAlias) {
		this.ownerAlias = ownerAlias;
	}
	public String getCustAlias() {
		return custAlias;
	}
	public void setCustAlias(String custAlias) {
		this.custAlias = custAlias;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRgstName() {
		return rgstName;
	}
	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}
	public Date getRgstDate() {
		return rgstDate;
	}
	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}
	public String getUpdtName() {
		return updtName;
	}
	public void setUpdtName(String updtName) {
		this.updtName = updtName;
	}
	public Date getUpdtDate() {
		return updtDate;
	}
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	
}
