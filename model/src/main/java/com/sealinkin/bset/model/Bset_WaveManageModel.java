package com.sealinkin.bset.model;

import com.sealinkin.bset.po.Bset_WaveManage;

public class Bset_WaveManageModel extends Bset_WaveManage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String waveNo;
	private String statusText;
	private String waveTypeText;
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
	public String getWaveTypeText() {
		return waveTypeText;
	}
	public void setWaveTypeText(String waveTypeText) {
		this.waveTypeText = waveTypeText;
	}
	
	
	
}
