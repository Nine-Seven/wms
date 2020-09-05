package com.sealinkin.bdef.model;

import java.util.Date;

import com.sealinkin.bdef.po.Bdef_ArticleBarcode;

public class Bdef_CsetCustDpscellModel extends Bdef_ArticleBarcode{
	private static final long serialVersionUID = 1L;
	
	private String warehouseNo;
	private String ownerNo;
	private String dpsCellNo;
	private String custNo;
	private String rgstName;
	private Date rgstDate;
	private String status;
	private String deviceGroupNo;
	private String deviceNo;
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
	public String getDpsCellNo() {
		return dpsCellNo;
	}
	public void setDpsCellNo(String dpsCellNo) {
		this.dpsCellNo = dpsCellNo;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeviceGroupNo() {
		return deviceGroupNo;
	}
	public void setDeviceGroupNo(String deviceGroupNo) {
		this.deviceGroupNo = deviceGroupNo;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
}
