package com.sealinkin.fcdata.model;

import com.sealinkin.fcdata.po.Fcdata_CheckM;

public class Fcdata_CheckMModel extends Fcdata_CheckM{

	private static final long serialVersionUID = 1L;

	private String enterpriseNo;
	private String warehouseNo;
	private String checkNo;
	
	private String statusText;
	private String checktypeText;
	private String fcdatatypeText;
	private String ware;
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getChecktypeText() {
		return checktypeText;
	}
	public void setChecktypeText(String checktypeText) {
		this.checktypeText = checktypeText;
	}
	public String getFcdatatypeText() {
		return fcdatatypeText;
	}
	public void setFcdatatypeText(String fcdatatypeText) {
		this.fcdatatypeText = fcdatatypeText;
	}
	public String getWare() {
		return ware;
	}
	public void setWare(String ware) {
		this.ware = ware;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}	
}
