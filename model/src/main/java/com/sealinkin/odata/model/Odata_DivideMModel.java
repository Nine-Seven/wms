package com.sealinkin.odata.model;

import com.sealinkin.odata.po.Odata_DivideM;

public class Odata_DivideMModel extends Odata_DivideM{
	private static final long serialVersionUID = 1L;
	private String divideNo;
	private String warehouseNo;
	
	private String waveNo;
	private String statusText;
	public String getDivideNo() {
		return divideNo;
	}
	public void setDivideNo(String divideNo) {
		this.divideNo = divideNo;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getWaveNo() {
		return waveNo;
	}
	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	
}
