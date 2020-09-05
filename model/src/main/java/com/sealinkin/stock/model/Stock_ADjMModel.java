package com.sealinkin.stock.model;

import java.math.BigDecimal;
import java.util.Date;

import com.sealinkin.stock.po.Stock_LabelM;

public class Stock_ADjMModel extends Stock_LabelM{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String adjNo;
	private String warehouseNo;
	private String ownerNo;
	
	private String adjType;
	private String poNo;
	private Date adjDate;
	private String status;
	private String createFlag;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	private BigDecimal sendFlag;
	
	public String getAdjNo() {
		return adjNo;
	}
	public void setAdjNo(String adjNo) {
		this.adjNo = adjNo;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getAdjType() {
		return adjType;
	}
	public void setAdjType(String adjType) {
		this.adjType = adjType;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public Date getAdjDate() {
		return adjDate;
	}
	public void setAdjDate(Date adjDate) {
		this.adjDate = adjDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateFlag() {
		return createFlag;
	}
	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
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
	public BigDecimal getSendFlag() {
		return sendFlag;
	}
	public void setSendFlag(BigDecimal sendFlag) {
		this.sendFlag = sendFlag;
	}
	
}
