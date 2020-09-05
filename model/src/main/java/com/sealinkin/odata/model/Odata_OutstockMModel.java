package com.sealinkin.odata.model;

import com.sealinkin.odata.po.Odata_OutstockM;


public class Odata_OutstockMModel extends Odata_OutstockM{
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String warehouseName;
	private String outstockNo;
	private String outstockTypeText;
	private String statusText;
	private String custNo;
	private String custName;
	private String waveNo;
	private String workerName;
	
	
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getOutstockNo() {
		return outstockNo;
	}
	public void setOutstockNo(String outstockNo) {
		this.outstockNo = outstockNo;
	}
	public String getOutstockTypeText() {
		return outstockTypeText;
	}
	public void setOutstockTypeText(String outstockTypeText) {
		this.outstockTypeText = outstockTypeText;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getWaveNo() {
		return waveNo;
	}
	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
	}
	
}
