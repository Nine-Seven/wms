package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Pntdef_PrinterGrpgather;

public class Pntdef_PrinterGrpgatherModel extends Pntdef_PrinterGrpgather{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String warehouseNo;
	private String grpgatherNo;
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getGrpgatherNo() {
		return grpgatherNo;
	}
	public void setGrpgatherNo(String grpgatherNo) {
		this.grpgatherNo = grpgatherNo;
	}
}
