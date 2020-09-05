package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_ArticleFamilyM;

public class Bdef_ArticleFamilyMModel extends Bdef_ArticleFamilyM{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String ownerNo;
	private String familyNo;
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
	public String getFamilyNo() {
		return familyNo;
	}
	public void setFamilyNo(String familyNo) {
		this.familyNo = familyNo;
	}


	


}
