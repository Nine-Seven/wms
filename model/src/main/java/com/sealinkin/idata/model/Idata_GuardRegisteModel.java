package com.sealinkin.idata.model;

import java.util.Date;

import com.sealinkin.idata.po.Idata_GuardRgiste;

public class Idata_GuardRegisteModel extends Idata_GuardRgiste{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String orderSerial;
	private String supplierNo;
	private String dockText;
	private String registeDateText;
	private String beginUnloadDateText;
	private String endUnloadDateText;
	private String leaveDateText;
	
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
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getOrderSerial() {
		return orderSerial;
	}
	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}
	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	public String getDockText() {
		return dockText;
	}
	public void setDockText(String dockText) {
		this.dockText = dockText;
	}
	public String getRegisteDateText() {
		return registeDateText;
	}
	public void setRegisteDateText(String registeDateText) {
		this.registeDateText = registeDateText;
	}
	public String getBeginUnloadDateText() {
		return beginUnloadDateText;
	}
	public void setBeginUnloadDateText(String beginUnloadDateText) {
		this.beginUnloadDateText = beginUnloadDateText;
	}
	public String getEndUnloadDateText() {
		return endUnloadDateText;
	}
	public void setEndUnloadDateText(String endUnloadDateText) {
		this.endUnloadDateText = endUnloadDateText;
	}
	public String getLeaveDateText() {
		return leaveDateText;
	}
	public void setLeaveDateText(String leaveDateText) {
		this.leaveDateText = leaveDateText;
	}

}
