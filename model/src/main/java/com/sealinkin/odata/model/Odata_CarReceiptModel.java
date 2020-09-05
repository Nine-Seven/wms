package com.sealinkin.odata.model;

import com.sealinkin.odata.po.Odata_CarReceipt;

public class Odata_CarReceiptModel extends Odata_CarReceipt {

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String waveNo;
	private String deliverNo;
	private String statusText;
	private String ownerName;
	private String custNoText;
	private String custName;
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
	public String getWaveNo() {
		return waveNo;
	}
	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
	}
	public String getDeliverNo() {
		return deliverNo;
	}
	public void setDeliverNo(String deliverNo) {
		this.deliverNo = deliverNo;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getCustNoText() {
		return custNoText;
	}
	public void setCustNoText(String custNoText) {
		this.custNoText = custNoText;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
}
