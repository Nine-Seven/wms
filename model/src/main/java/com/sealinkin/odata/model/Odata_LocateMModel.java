package com.sealinkin.odata.model;

import com.sealinkin.odata.po.Odata_LocateM;

public class Odata_LocateMModel extends Odata_LocateM{

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String waveNo;
	private String sendbufCompute;
	private String orderType;
    private String taskAllotRuleID;
    
    private String strdivideComputeFlag;
    private String strcheckComputeFlag;
    
    private String batchRuleI;
    
    
    
	public String getBatchRuleI() {
		return batchRuleI;
	}
	public void setBatchRuleI(String batchRuleI) {
		this.batchRuleI = batchRuleI;
	}
	public String getStrdivideComputeFlag() {
		return strdivideComputeFlag;
	}
	public void setStrdivideComputeFlag(String strdivideComputeFlag) {
		this.strdivideComputeFlag = strdivideComputeFlag;
	}
	public String getStrcheckComputeFlag() {
		return strcheckComputeFlag;
	}
	public void setStrcheckComputeFlag(String strcheckComputeFlag) {
		this.strcheckComputeFlag = strcheckComputeFlag;
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
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getSendbufCompute() {
		return sendbufCompute;
	}
	public void setSendbufCompute(String sendbufCompute) {
		this.sendbufCompute = sendbufCompute;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getTaskAllotRuleID() {
		return taskAllotRuleID;
	}
	public void setTaskAllotRuleID(String taskAllotRuleID) {
		this.taskAllotRuleID = taskAllotRuleID;
	}
	
}
