package com.sealinkin.oset.model;

import com.sealinkin.oset.po.Oset_TaskAllotM;

public class Oset_TaskAllotMModel extends Oset_TaskAllotM {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private short taskId;
	private String defaultFlagText;
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public short getTaskId() {
		return taskId;
	}
	public void setTaskId(short taskId) {
		this.taskId = taskId;
	}
	public String getDefaultFlagText() {
		return defaultFlagText;
	}
	public void setDefaultFlagText(String defaultFlagText) {
		this.defaultFlagText = defaultFlagText;
	}	
}
