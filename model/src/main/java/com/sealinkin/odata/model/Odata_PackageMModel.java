package com.sealinkin.odata.model;

import com.sealinkin.odata.po.Odata_PackageM;

public class Odata_PackageMModel extends Odata_PackageM{

	private static final long serialVersionUID = 1L;
	private String statusText;
	private String enterpriseNo;
	private String warehouseNo;
	private String poNo;

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

	public String getWarehouseNo() {
		return warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	
	
}
