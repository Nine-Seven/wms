package com.sealinkin.sodata.model;

import com.sealinkin.sodata.po.Sodata_WasteM;

public class Sodata_WasteMModel extends Sodata_WasteM{

	/**
	 * 报损手建单
	 */
	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String warehouseNo;
	private String wasteNo;
	private String orgNoText;
	private String statusText;
	
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
	public String getWasteNo() {
		return wasteNo;
	}
	public void setWasteNo(String wasteNo) {
		this.wasteNo = wasteNo;
	}

	public String getOrgNoText() {
		return orgNoText;
	}
	public void setOrgNoText(String orgNoText) {
		this.orgNoText = orgNoText;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	
	
}
