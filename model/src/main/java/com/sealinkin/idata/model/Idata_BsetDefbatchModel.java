package com.sealinkin.idata.model;

import java.math.BigDecimal;
import java.util.Date;

import com.sealinkin.idata.po.Idata_BsetDefbatch;

public class Idata_BsetDefbatchModel extends Idata_BsetDefbatch{

	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String batchNo;
	private Date operateDate;
	private String startTime;
	private String endTime;
	private String rgstName;
	private Date rgstDate;
	private String status;
	private BigDecimal currRecord;
	private String batchType;
	private String warehouseText;
	private String statusText;
	private String operateDateText;

	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public BigDecimal getCurrRecord() {
		return currRecord;
	}
	public void setCurrRecord(BigDecimal currRecord) {
		this.currRecord = currRecord;
	}
	public String getBatchType() {
		return batchType;
	}
	public void setBatchType(String batchType) {
		this.batchType = batchType;
	}
	public String getWarehouseText() {
		return warehouseText;
	}
	public void setWarehouseText(String warehouseText) {
		this.warehouseText = warehouseText;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getOperateDateText() {
		return operateDateText;
	}
	public void setOperateDateText(String operateDateText) {
		this.operateDateText = operateDateText;
	}
	
}
