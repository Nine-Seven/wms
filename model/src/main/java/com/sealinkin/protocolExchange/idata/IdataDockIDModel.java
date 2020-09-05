package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;

public class IdataDockIDModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String EnterpriseNo;
	private String WarehouseNo;
	private String Dock;
	private String WorkerNo;
	private String poNo;
	private String barcode;
	private String articleNo;
	private String subLabelNo;
	public String getEnterpriseNo() {
		return EnterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		EnterpriseNo = enterpriseNo;
	}
	public String getWarehouseNo() {
		return WarehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		WarehouseNo = warehouseNo;
	}
	public String getDock() {
		return Dock;
	}
	public void setDock(String dock) {
		Dock = dock;
	}
	public String getWorkerNo() {
		return WorkerNo;
	}
	public void setWorkerNo(String workerNo) {
		WorkerNo = workerNo;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getSubLabelNo() {
		return subLabelNo;
	}
	public void setSubLabelNo(String subLabelNo) {
		this.subLabelNo = subLabelNo;
	}
		
}
