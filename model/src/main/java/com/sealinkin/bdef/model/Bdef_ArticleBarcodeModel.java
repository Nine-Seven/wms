package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_ArticleBarcode;

public class Bdef_ArticleBarcodeModel extends Bdef_ArticleBarcode{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String barcode;
	private String ownerNo;
	private String articleNo;
	private double packingQty;
	
	private String ownerArticleNo;
	private String articleName;
	private String primaryflagText;
	
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public double getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(double packingQty) {
		this.packingQty = packingQty;
	}
	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}
	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getPrimaryflagText() {
		return primaryflagText;
	}
	public void setPrimaryflagText(String primaryflagText) {
		this.primaryflagText = primaryflagText;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}	

}
