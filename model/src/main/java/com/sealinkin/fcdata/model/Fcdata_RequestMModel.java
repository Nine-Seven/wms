package com.sealinkin.fcdata.model;

import com.sealinkin.fcdata.po.Fcdata_RequestM;

public class Fcdata_RequestMModel extends Fcdata_RequestM{

	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String requestNo;
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	
	

}
