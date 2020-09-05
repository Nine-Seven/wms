package com.sealinkin.idata.model;

import com.sealinkin.idata.po.Idata_InstockM;


public class Idata_InstockMModel extends Idata_InstockM {

	private static final long serialVersionUID = -2512853780536574052L;
	
	private String enterpriseNo;
	private String instockNo;
	private String warehouseNo;
	private String ownerNo;
	private String statusText;
	
	public String getInstockNo() {
		return instockNo;
	}
	public void setInstockNo(String instockNo) {
		this.instockNo = instockNo;
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
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
}
