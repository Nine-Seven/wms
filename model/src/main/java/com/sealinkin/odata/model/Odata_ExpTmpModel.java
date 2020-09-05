package com.sealinkin.odata.model;

import java.util.Date;

import com.sealinkin.odata.po.Odata_ExpTmp;

public class Odata_ExpTmpModel extends Odata_ExpTmp{

	private static final long serialVersionUID = 1L;
	
	private String ownerNo;
	private String expType;
	private String sourceexpNo;
	private String custNo;
	private String contactorName;
	private String custPhone;
	private String custMail;
	private String custAddress;
	private String shipperNo;
	private short priority;
	private String fastFlag;
	private String articleNo;
	private double packingQty;
	private double articleQty;
	private Date produceValue1;
	private String expRemark;
	
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getExpType() {
		return expType;
	}
	public void setExpType(String expType) {
		this.expType = expType;
	}
	public String getSourceexpNo() {
		return sourceexpNo;
	}
	public void setSourceexpNo(String sourceexpNo) {
		this.sourceexpNo = sourceexpNo;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getContactorName() {
		return contactorName;
	}
	public void setContactorName(String contactorName) {
		this.contactorName = contactorName;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustMail() {
		return custMail;
	}
	public void setCustMail(String custMail) {
		this.custMail = custMail;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getShipperNo() {
		return shipperNo;
	}
	public void setShipperNo(String shipperNo) {
		this.shipperNo = shipperNo;
	}
	public short getPriority() {
		return priority;
	}
	public void setPriority(short priority) {
		this.priority = priority;
	}
	public String getFastFlag() {
		return fastFlag;
	}
	public void setFastFlag(String fastFlag) {
		this.fastFlag = fastFlag;
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
	public double getArticleQty() {
		return articleQty;
	}
	public void setArticleQty(double articleQty) {
		this.articleQty = articleQty;
	}
	public Date getProduceValue1() {
		return produceValue1;
	}
	public void setProduceValue1(Date produceValue1) {
		this.produceValue1 = produceValue1;
	}
	public String getExpRemark() {
		return expRemark;
	}
	public void setExpRemark(String expRemark) {
		this.expRemark = expRemark;
	}
	
	

}
