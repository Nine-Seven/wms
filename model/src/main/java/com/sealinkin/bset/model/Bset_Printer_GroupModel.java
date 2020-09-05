package com.sealinkin.bset.model;

import com.sealinkin.bset.po.Bset_Printer_Group;

public class Bset_Printer_GroupModel extends Bset_Printer_Group{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String warehouseNo;
	private String printerGroupNo;
	private String printerNo;
	private String printerName;
	
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
	public String getPrinterNo() {
		return printerNo;
	}
	public void setPrinterNo(String printerNo) {
		this.printerNo = printerNo;
	}
	public String getPrinterName() {
		return printerName;
	}
	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	
	

}
