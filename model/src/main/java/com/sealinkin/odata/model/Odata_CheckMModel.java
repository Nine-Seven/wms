package com.sealinkin.odata.model;

import com.sealinkin.odata.po.Odata_CheckM;

public class Odata_CheckMModel extends Odata_CheckM {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String checkNo;
	private String boxsQty;
	
	
	public String getBoxsQty() {
		return boxsQty;
	}
	public void setBoxsQty(String boxsQty) {
		this.boxsQty = boxsQty;
	}
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
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
}
