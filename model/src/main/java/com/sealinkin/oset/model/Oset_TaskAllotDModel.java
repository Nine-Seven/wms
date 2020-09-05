package com.sealinkin.oset.model;

import com.sealinkin.oset.po.Oset_TaskAllotD;

public class Oset_TaskAllotDModel extends Oset_TaskAllotD {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private short taskId;
	private String outstockType;
	private String operateType;
	private String sourceType;
	
	private String allotRuleText;
	private String boxFalgText;
	private String operateTypeText;
	private String outstockTypeText;
	private String taskTypeText;
	private String sourceTypeText;
	
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
	public String getOutstockType() {
		return outstockType;
	}
	public void setOutstockType(String outstockType) {
		this.outstockType = outstockType;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getAllotRuleText() {
		return allotRuleText;
	}
	public void setAllotRuleText(String allotRuleText) {
		this.allotRuleText = allotRuleText;
	}
	public String getBoxFalgText() {
		return boxFalgText;
	}
	public void setBoxFalgText(String boxFalgText) {
		this.boxFalgText = boxFalgText;
	}
	public String getOperateTypeText() {
		return operateTypeText;
	}
	public void setOperateTypeText(String operateTypeText) {
		this.operateTypeText = operateTypeText;
	}
	public String getOutstockTypeText() {
		return outstockTypeText;
	}
	public void setOutstockTypeText(String outstockTypeText) {
		this.outstockTypeText = outstockTypeText;
	}
	public String getTaskTypeText() {
		return taskTypeText;
	}
	public void setTaskTypeText(String taskTypeText) {
		this.taskTypeText = taskTypeText;
	}
	public String getSourceTypeText() {
		return sourceTypeText;
	}
	public void setSourceTypeText(String sourceTypeText) {
		this.sourceTypeText = sourceTypeText;
	}
}
