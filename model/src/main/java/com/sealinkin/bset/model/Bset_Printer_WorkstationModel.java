package com.sealinkin.bset.model;

import com.sealinkin.bset.po.Bset_Printer_Workstation;

public class Bset_Printer_WorkstationModel extends Bset_Printer_Workstation{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String warehouseNo;
	private String workstationNo;
	private String printerGroupName;
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getWorkstationNo() {
		return workstationNo;
	}
	public void setWorkstationNo(String workstationNo) {
		this.workstationNo = workstationNo;
	}
	public String getPrinterGroupName() {
		return printerGroupName;
	}
	public void setPrinterGroupName(String printerGroupName) {
		this.printerGroupName = printerGroupName;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	
}
