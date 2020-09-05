package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Device_DivideM;

public class Device_DivideMModel extends Device_DivideM {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String deviceNo;
	private String deviceTypeText;
	private String statusText;
	private String operateTypeText;
	private String produceCapacityTypeText;
	private String refRateFlagText;
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
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getDeviceTypeText() {
		return deviceTypeText;
	}
	public void setDeviceTypeText(String deviceTypeText) {
		this.deviceTypeText = deviceTypeText;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getOperateTypeText() {
		return operateTypeText;
	}
	public void setOperateTypeText(String operateTypeText) {
		this.operateTypeText = operateTypeText;
	}
	public String getProduceCapacityTypeText() {
		return produceCapacityTypeText;
	}
	public void setProduceCapacityTypeText(String produceCapacityTypeText) {
		this.produceCapacityTypeText = produceCapacityTypeText;
	}
	public String getRefRateFlagText() {
		return refRateFlagText;
	}
	public void setRefRateFlagText(String refRateFlagText) {
		this.refRateFlagText = refRateFlagText;
	}
	
	
}
