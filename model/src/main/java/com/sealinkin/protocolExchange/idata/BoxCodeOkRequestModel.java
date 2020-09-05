package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;
/**
 * 箱码采集 对应stuBoxCodeOkRequest
 * @author lich
 *
 */
public class BoxCodeOkRequestModel implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String articleNo;
	private String barCode;
	private String cellNo;
	private String packingQty;
	private String userNo;
	
	private String enterpriseNo;
	private String wareHouseNo;
	private String serialNo;
	private String boxNo;
	
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(String packingQty) {
		this.packingQty = packingQty;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getWareHouseNo() {
		return wareHouseNo;
	}
	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getBoxNo() {
		return boxNo;
	}
	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}
	
}
