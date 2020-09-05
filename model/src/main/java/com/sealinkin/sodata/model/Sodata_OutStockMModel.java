package com.sealinkin.sodata.model;

import com.sealinkin.sodata.po.Sodata_OutstockM;

public class Sodata_OutStockMModel extends Sodata_OutstockM{

	/**
	 * 报损回单
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String outstockNo;
	private String warehouseNo;
	private String ownerNo;
	private String statusText;
	private String poNo;
	private String orgNoText;
	
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getOutstockNo() {
		return outstockNo;
	}
	public void setOutstockNo(String outstockNo) {
		this.outstockNo = outstockNo;
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
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getOrgNoText() {
		return orgNoText;
	}
	public void setOrgNoText(String orgNoText) {
		this.orgNoText = orgNoText;
	}
	

	
}
