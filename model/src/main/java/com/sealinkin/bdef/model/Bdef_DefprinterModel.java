package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_Defprinter;

public class Bdef_DefprinterModel extends Bdef_Defprinter{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String warehouseNo;
	private String printerNo;
	private String printertypeText;
	private String statusText;
	
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getPrinterNo() {
		return printerNo;
	}
	public void setPrinterNo(String printerNo) {
		this.printerNo = printerNo;
	}
	public String getPrintertypeText() {
		return printertypeText;
	}
	public void setPrintertypeText(String printertypeText) {
		this.printertypeText = printertypeText;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

}
