package com.sealinkin.wms.model;

import com.sealinkin.wms.po.Wms_OwnerOutorder;


public class Wms_OwnerOutorderModel extends Wms_OwnerOutorder{

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String ownerNo;
	private String expType;

	private String ownerName;
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getExpType() {
		return expType;
	}
	public void setExpType(String expType) {
		this.expType = expType;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
}
