package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_Defarticle;

public class Bdef_DefarticleModel extends Bdef_Defarticle{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String articleNo;
	private String groupName;
	private String barcode;
	private String articleIdentifier;
	private String statusText;
	private String ownerName;
	private String qminUnit;
	private String basePacking;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getArticleIdentifier() {
		return articleIdentifier;
	}
	public void setArticleIdentifier(String articleIdentifier) {
		this.articleIdentifier = articleIdentifier;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getQminUnit() {
		return qminUnit;
	}
	public void setQminUnit(String qminUnit) {
		this.qminUnit = qminUnit;
	}
	public String getBasePacking() {
		return basePacking;
	}
	public void setBasePacking(String basePacking) {
		this.basePacking = basePacking;
	}
	
}
