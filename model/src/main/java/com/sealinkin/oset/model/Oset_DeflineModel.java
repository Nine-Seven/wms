package com.sealinkin.oset.model;

import com.sealinkin.oset.po.Oset_Defline;

public class Oset_DeflineModel extends Oset_Defline{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String warehouseNo;
	private String lineNo;
	private String delivertypeText;
	private String transporttypeText;
	
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getDelivertypeText() {
		return delivertypeText;
	}
	public void setDelivertypeText(String delivertypeText) {
		this.delivertypeText = delivertypeText;
	}
	public String getTransporttypeText() {
		return transporttypeText;
	}
	public void setTransporttypeText(String transporttypeText) {
		this.transporttypeText = transporttypeText;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
}
