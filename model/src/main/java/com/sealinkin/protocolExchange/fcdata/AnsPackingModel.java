package com.sealinkin.protocolExchange.fcdata;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AnsPackingModel implements Serializable{
	private String articleName;
	private String packunit;
	private Double packQty;
	private Double palBaseBox;
	private Double palHeightBox;
	private Double expiryDays;
	private String articleNo;
	private Double packingLength;
	private Double packingWidth;
	private Double packingHeight;
	private Double packingWeight;
	private String barcode;
	private String article_identifier;
	
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getArticle_identifier() {
		return article_identifier;
	}
	public void setArticle_identifier(String article_identifier) {
		this.article_identifier = article_identifier;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getPackunit() {
		return packunit;
	}
	public void setPackunit(String packunit) {
		this.packunit = packunit;
	}
	public Double getPackQty() {
		return packQty;
	}
	public void setPackQty(Double packQty) {
		this.packQty = packQty;
	}
	public Double getExpiryDays() {
		return expiryDays;
	}
	public void setExpiryDays(Double expiryDays) {
		this.expiryDays = expiryDays;
	}
	public Double getPalBaseBox() {
		return palBaseBox;
	}
	public void setPalBaseBox(Double palBaseBox) {
		this.palBaseBox = palBaseBox;
	}
	public Double getPalHeightBox() {
		return palHeightBox;
	}
	public void setPalHeightBox(Double palHeightBox) {
		this.palHeightBox = palHeightBox;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	

	public Double getPackingLength() {
		return packingLength;
	}
	public void setPackingLength(Double packingLength) {
		this.packingLength = packingLength;
	}
	public Double getPackingWidth() {
		return packingWidth;
	}
	public void setPackingWidth(Double packingWidth) {
		this.packingWidth = packingWidth;
	}
	public Double getPackingHeight() {
		return packingHeight;
	}
	public void setPackingHeight(Double packingHeight) {
		this.packingHeight = packingHeight;
	}
	public Double getPackingWeight() {
		return packingWeight;
	}
	public void setPackingWeight(Double packingWeight) {
		this.packingWeight = packingWeight;
	}
	
	
}
