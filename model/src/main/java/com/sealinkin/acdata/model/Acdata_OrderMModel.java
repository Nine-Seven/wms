package com.sealinkin.acdata.model;

import java.sql.Timestamp;
import com.sealinkin.acdata.po.Acdata_OrderM;

public class Acdata_OrderMModel extends Acdata_OrderM {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7594512673991978004L;
	private String orderNo;
	private String sourceNo;
	private Timestamp sdate;
	private String ownerAlias;
	private String ownerProvince;
	private String ownerCity;
	private String ownerZone;
	private String ownerAddr;
	private String ownerLinkman;
	private String ownerPhone;
	private String custAlias;
	private String custProvince;
	private String custCity;
	private String custZone;
	private String custAddr;
	private String custLinkman;
	private String custPhone;
	private String status;
	private String remark;
	private String rgstName;
	private Timestamp rgstDate;
	private String updtName;
	private Timestamp updtDate;
	private String statusText;
	private String sdateText;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getSourceNo() {
		return sourceNo;
	}
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}
	public Timestamp getSdate() {
		return sdate;
	}
	public void setSdate(Timestamp sdate) {
		this.sdate = sdate;
	}
	public String getOwnerAlias() {
		return ownerAlias;
	}
	public void setOwnerAlias(String ownerAlias) {
		this.ownerAlias = ownerAlias;
	}
	public String getOwnerProvince() {
		return ownerProvince;
	}
	public void setOwnerProvince(String ownerProvince) {
		this.ownerProvince = ownerProvince;
	}
	public String getOwnerCity() {
		return ownerCity;
	}
	public void setOwnerCity(String ownerCity) {
		this.ownerCity = ownerCity;
	}
	public String getOwnerZone() {
		return ownerZone;
	}
	public void setOwnerZone(String ownerZone) {
		this.ownerZone = ownerZone;
	}
	public String getOwnerAddr() {
		return ownerAddr;
	}
	public void setOwnerAddr(String ownerAddr) {
		this.ownerAddr = ownerAddr;
	}
	public String getOwnerLinkman() {
		return ownerLinkman;
	}
	public void setOwnerLinkman(String ownerLinkman) {
		this.ownerLinkman = ownerLinkman;
	}
	public String getOwnerPhone() {
		return ownerPhone;
	}
	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}
	public String getCustAlias() {
		return custAlias;
	}
	public void setCustAlias(String custAlias) {
		this.custAlias = custAlias;
	}
	public String getCustProvince() {
		return custProvince;
	}
	public void setCustProvince(String custProvince) {
		this.custProvince = custProvince;
	}
	public String getCustCity() {
		return custCity;
	}
	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}
	public String getCustZone() {
		return custZone;
	}
	public void setCustZone(String custZone) {
		this.custZone = custZone;
	}
	public String getCustAddr() {
		return custAddr;
	}
	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}
	public String getCustLinkman() {
		return custLinkman;
	}
	public void setCustLinkman(String custLinkman) {
		this.custLinkman = custLinkman;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRgstName() {
		return rgstName;
	}
	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}
	public Timestamp getRgstDate() {
		return rgstDate;
	}
	public void setRgstDate(Timestamp rgstDate) {
		this.rgstDate = rgstDate;
	}
	public String getUpdtName() {
		return updtName;
	}
	public void setUpdtName(String updtName) {
		this.updtName = updtName;
	}
	public Timestamp getUpdtDate() {
		return updtDate;
	}
	public void setUpdtDate(Timestamp updtDate) {
		this.updtDate = updtDate;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getSdateText() {
		return sdateText;
	}
	public void setSdateText(String sdateText) {
		this.sdateText = sdateText;
	}
	

}
