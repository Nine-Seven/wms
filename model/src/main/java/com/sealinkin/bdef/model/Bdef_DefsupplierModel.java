package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_Defsupplier;

public class Bdef_DefsupplierModel extends Bdef_Defsupplier{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String ownerNo;
	private String supplierNo;
	private String unloadflagText;
	private String statusText;
	private String createFlagText;
	private String unload_flag_desc;
	private String ownerName;
	
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	public String getUnloadflagText() {
		return unloadflagText;
	}
	public void setUnloadflagText(String unloadflagText) {
		this.unloadflagText = unloadflagText;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getCreateFlagText() {
		return createFlagText;
	}
	public void setCreateFlagText(String createFlagText) {
		this.createFlagText = createFlagText;
	}
	public String getUnload_flag_desc() {
		return unload_flag_desc;
	}
	public void setUnload_flag_desc(String unload_flag_desc) {
		this.unload_flag_desc = unload_flag_desc;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

}
