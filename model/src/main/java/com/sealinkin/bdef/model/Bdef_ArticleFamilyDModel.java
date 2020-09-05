package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_ArticleFamilyD;

public class Bdef_ArticleFamilyDModel extends Bdef_ArticleFamilyD{

	private static final long serialVersionUID = 1L;
	

	private String enterpriseNo;
	private String ownerNo;
	private String familyNo;
	private String articleNo;
	private String articleName;
	private String ownerArticleNo;

	
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
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}
	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}
	
}
