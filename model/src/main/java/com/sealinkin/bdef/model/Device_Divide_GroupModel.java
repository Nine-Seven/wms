package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Device_Divide_Group;

public class Device_Divide_GroupModel extends Device_Divide_Group {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String deviceGroupNo;
	private String useType;
	private String useTypeText;
	private String statusText;
	private String computeText;
	private String defaultFlagText;
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
	public String getDeviceGroupNo() {
		return deviceGroupNo;
	}
	public void setDeviceGroupNo(String deviceGroupNo) {
		this.deviceGroupNo = deviceGroupNo;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public String getUseTypeText() {
		return useTypeText;
	}
	public void setUseTypeText(String useTypeText) {
		this.useTypeText = useTypeText;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getComputeText() {
		return computeText;
	}
	public void setComputeText(String computeText) {
		this.computeText = computeText;
	}
	public String getDefaultFlagText() {
		return defaultFlagText;
	}
	public void setDefaultFlagText(String defaultFlagText) {
		this.defaultFlagText = defaultFlagText;
	}
	
}
