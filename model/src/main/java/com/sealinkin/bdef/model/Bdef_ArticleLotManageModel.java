package com.sealinkin.bdef.model;

import java.util.Date;

import com.sealinkin.bdef.po.Bdef_ArticleLotManage;

public class Bdef_ArticleLotManageModel extends Bdef_ArticleLotManage{

	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String articleNo;
	private String lotNo;
	private Date produceDate;
	private Date expireDate;
	private Integer expiryDays;
	private String rgstName;
	private Date rgstDate;
	private double price;
	
	
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public Date getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public Integer getExpiryDays() {
		return expiryDays;
	}
	public void setExpiryDays(Integer expiryDays) {
		this.expiryDays = expiryDays;
	}
	public String getRgstName() {
		return rgstName;
	}
	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}
	public Date getRgstDate() {
		return rgstDate;
	}
	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
