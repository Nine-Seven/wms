package com.sealinkin.ridata.model;

import com.sealinkin.ridata.po.Ridata_InstockM;

public class Ridata_InstockMModel extends Ridata_InstockM{

	private static final long serialVersionUID = 1L;

	private String instockNo;
	private String warehouseNo;
	private String ownerNo;
	private String statusText;
	private String waveNo;//波次号
	
	public String getInstockNo() {
		return instockNo;
	}
	public void setInstockNo(String instockNo) {
		this.instockNo = instockNo;
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
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getWaveNo() {
		return waveNo;
	}
	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
	}
	
	
}
