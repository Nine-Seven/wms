package com.sealinkin.idata.model;

import com.sealinkin.idata.po.Idata_OrderSheet;

public class Idata_OrderSheetModel extends Idata_OrderSheet{

	private static final long serialVersionUID = 1L;
	
	private String warehouseNo;
	private String orderSerial;
	private String importNo;
	private String poType;
	private String cancelStatus;
	private String statusText;
	private String dockNo;
	private String dockText;
	
	
	private String poNo;
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getOrderSerial() {
		return orderSerial;
	}
	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}
	public String getImportNo() {
		return importNo;
	}
	public void setImportNo(String importNo) {
		this.importNo = importNo;
	}
	public String getPoType() {
		return poType;
	}
	public void setPoType(String poType) {
		this.poType = poType;
	}
	public String getCancelStatus() {
		return cancelStatus;
	}
	public void setCancelStatus(String cancelStatus) {
		this.cancelStatus = cancelStatus;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getDockNo() {
		return dockNo;
	}
	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}
	public String getDockText() {
		return dockText;
	}
	public void setDockText(String dockText) {
		this.dockText = dockText;
	}
	
	

}
