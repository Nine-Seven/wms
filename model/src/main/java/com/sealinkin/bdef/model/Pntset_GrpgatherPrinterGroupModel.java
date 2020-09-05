package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Pntset_GrpgatherPrinterGroup;

public class Pntset_GrpgatherPrinterGroupModel extends Pntset_GrpgatherPrinterGroup{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String warehouseNo;
	private String grpgatherNo;
	private String printerGroupNo;
	private String printerGroupName;
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getPrinterGroupNo() {
		return printerGroupNo;
	}
	public void setPrinterGroupNo(String printerGroupNo) {
		this.printerGroupNo = printerGroupNo;
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
	public String getPrinterGroupName() {
		return printerGroupName;
	}
	public void setPrinterGroupName(String printerGroupName) {
		this.printerGroupName = printerGroupName;
	}
}
