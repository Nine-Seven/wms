package com.sealinkin.odata.model;

import com.sealinkin.odata.po.Odata_LocateD;

public class Odata_LocateDModel extends Odata_LocateD{

	private static final long serialVersionUID = 1L;

	private String waveNo;
	private String warehouseNo;
	private long rowId;
	private String expNo;
	public String getWaveNo() {
		return waveNo;
	}
	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public long getRowId() {
		return rowId;
	}
	public void setRowId(long rowId) {
		this.rowId = rowId;
	}
	public String getExpNo() {
		return expNo;
	}
	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}
	
	
}
