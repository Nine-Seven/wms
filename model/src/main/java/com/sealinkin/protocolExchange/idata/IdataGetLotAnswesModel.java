package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;
/**
 * 扫描商品条码 对应客户端 StuIMGetLotRequestAnswer
 * @author lich
 *
 */
public class IdataGetLotAnswesModel implements Serializable {	
       /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String articleNo;
       private String lotNo;
       private String produceDate;
       private String expireDate;
       private String expiryDays;
       private String price;       
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
	public String getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getExpiryDays() {
		return expiryDays;
	}
	public void setExpiryDays(String expiryDays) {
		this.expiryDays = expiryDays;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}       
}
