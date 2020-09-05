package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;
/**
 * 分播回单请求（StuReqSaveDivideSupp）
 * @author lich
 *
 */
public class ReqSaveDivideSuppModel implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  enterpriseNo;
	private String  wareHouseNo;
     private String  instockNo;
     private String  labelNo;
     private String  articleNo;
     private String  barcode;
     private float packingQty;
     private double realQty;
     private String  userId;
     private String  paperUserId;
     private Integer  operateTools;
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getWareHouseNo() {
		return wareHouseNo;
	}
	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}
	public String getInstockNo() {
		return instockNo;
	}
	public void setInstockNo(String instockNo) {
		this.instockNo = instockNo;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public float getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(float packingQty) {
		this.packingQty = packingQty;
	}
	public double getRealQty() {
		return realQty;
	}
	public void setRealQty(double realQty) {
		this.realQty = realQty;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPaperUserId() {
		return paperUserId;
	}
	public void setPaperUserId(String paperUserId) {
		this.paperUserId = paperUserId;
	}
	public Integer getOperateTools() {
		return operateTools;
	}
	public void setOperateTools(Integer operateTools) {
		this.operateTools = operateTools;
	}
}
