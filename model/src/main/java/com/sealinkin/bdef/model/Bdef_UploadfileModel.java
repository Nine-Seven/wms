package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_Uploadfile;

public class Bdef_UploadfileModel extends Bdef_Uploadfile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String fileName;
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
