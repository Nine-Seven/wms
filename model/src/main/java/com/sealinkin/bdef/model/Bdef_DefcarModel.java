package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_Defcar;

public class Bdef_DefcarModel extends Bdef_Defcar{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String warehouseNo;
	private String carNo;
	private String cartypeName;
	private String careDateText;
	private String carClassText;
	private String workerName;
	private String driverWorkerText;
	
	private String strStatus;
	
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
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getCartypeName() {
		return cartypeName;
	}
	public void setCartypeName(String cartypeName) {
		this.cartypeName = cartypeName;
	}
	public String getCareDateText() {
		return careDateText;
	}
	public void setCareDateText(String careDateText) {
		this.careDateText = careDateText;
	}
	public String getCarClassText() {
		return carClassText;
	}
	public void setCarClassText(String carClassText) {
		this.carClassText = carClassText;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getDriverWorkerText() {
		return driverWorkerText;
	}
	public void setDriverWorkerText(String driverWorkerText) {
		this.driverWorkerText = driverWorkerText;
	}
	public String getStrStatus() {
		return strStatus;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	
}
