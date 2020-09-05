package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_ArticlePacking;

public class Bdef_ArticlePackingModel extends Bdef_ArticlePacking{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String articleNo;
	private double packingQty;
	
	private String articleName;
	private String sorterflagText;
	private String ruleflagText;
	
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
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getSorterflagText() {
		return sorterflagText;
	}
	public void setSorterflagText(String sorterflagText) {
		this.sorterflagText = sorterflagText;
	}
	public String getRuleflagText() {
		return ruleflagText;
	}
	public void setRuleflagText(String ruleflagText) {
		this.ruleflagText = ruleflagText;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}	
}	
