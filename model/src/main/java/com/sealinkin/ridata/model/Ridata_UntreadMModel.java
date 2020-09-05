package com.sealinkin.ridata.model;

import com.sealinkin.ridata.po.Ridata_UntreadM;

public class Ridata_UntreadMModel extends Ridata_UntreadM{

	private static final long serialVersionUID = 1L;
    private String enterpriseNo;
	private String untreadNo;
	private String warehouseNo;
	private String custName;
	private String statusText;
	private String SUntreadNo;
	private String serialNo;
	private String qualityText;//质量类型
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getUntreadNo() {
		return untreadNo;
	}
	public void setUntreadNo(String untreadNo) {
		this.untreadNo = untreadNo;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getSUntreadNo() {
		return SUntreadNo;
	}
	public void setSUntreadNo(String sUntreadNo) {
		SUntreadNo = sUntreadNo;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getQualityText() {
		return qualityText;
	}
	public void setQualityText(String qualityText) {
		this.qualityText = qualityText;
	}
	
}
