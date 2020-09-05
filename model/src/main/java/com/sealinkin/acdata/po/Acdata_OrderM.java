package com.sealinkin.acdata.po;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AcdataOrderM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACDATA_ORDER_M")
public class Acdata_OrderM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	// Constructors

	/** default constructor */
	public Acdata_OrderM() {
	}

	/** minimal constructor */
	public Acdata_OrderM(String orderNo, String sourceNo, Timestamp sdate,
			String ownerAlias, String ownerProvince, String ownerCity,
			String ownerZone, String custProvince, String custCity,
			String custZone, String status, String rgstName, Timestamp rgstDate) {
		this.orderNo = orderNo;
		this.sourceNo = sourceNo;
		this.sdate = sdate;
		this.ownerAlias = ownerAlias;
		this.ownerProvince = ownerProvince;
		this.ownerCity = ownerCity;
		this.ownerZone = ownerZone;
		this.custProvince = custProvince;
		this.custCity = custCity;
		this.custZone = custZone;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Acdata_OrderM(String orderNo, String sourceNo, Timestamp sdate,
			String ownerAlias, String ownerProvince, String ownerCity,
			String ownerZone, String ownerAddr, String ownerLinkman,
			String ownerPhone, String custAlias, String custProvince,
			String custCity, String custZone, String custAddr,
			String custLinkman, String custPhone, String status, String remark,
			String rgstName, Timestamp rgstDate, String updtName,
			Timestamp updtDate) {
		this.orderNo = orderNo;
		this.sourceNo = sourceNo;
		this.sdate = sdate;
		this.ownerAlias = ownerAlias;
		this.ownerProvince = ownerProvince;
		this.ownerCity = ownerCity;
		this.ownerZone = ownerZone;
		this.ownerAddr = ownerAddr;
		this.ownerLinkman = ownerLinkman;
		this.ownerPhone = ownerPhone;
		this.custAlias = custAlias;
		this.custProvince = custProvince;
		this.custCity = custCity;
		this.custZone = custZone;
		this.custAddr = custAddr;
		this.custLinkman = custLinkman;
		this.custPhone = custPhone;
		this.status = status;
		this.remark = remark;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@Id
	@Column(name = "ORDER_NO", unique = true, nullable = false, length = 20)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "SOURCE_NO", nullable = false, length = 50)
	public String getSourceNo() {
		return this.sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	@Column(name = "SDATE", nullable = false, length = 7)
	public Timestamp getSdate() {
		return this.sdate;
	}

	public void setSdate(Timestamp sdate) {
		this.sdate = sdate;
	}

	@Column(name = "OWNER_ALIAS", nullable = false, length = 100)
	public String getOwnerAlias() {
		return this.ownerAlias;
	}

	public void setOwnerAlias(String ownerAlias) {
		this.ownerAlias = ownerAlias;
	}

	@Column(name = "OWNER_PROVINCE", nullable = false, length = 20)
	public String getOwnerProvince() {
		return this.ownerProvince;
	}

	public void setOwnerProvince(String ownerProvince) {
		this.ownerProvince = ownerProvince;
	}

	@Column(name = "OWNER_CITY", nullable = false, length = 20)
	public String getOwnerCity() {
		return this.ownerCity;
	}

	public void setOwnerCity(String ownerCity) {
		this.ownerCity = ownerCity;
	}

	@Column(name = "OWNER_ZONE", nullable = false, length = 20)
	public String getOwnerZone() {
		return this.ownerZone;
	}

	public void setOwnerZone(String ownerZone) {
		this.ownerZone = ownerZone;
	}

	@Column(name = "OWNER_ADDR", length = 128)
	public String getOwnerAddr() {
		return this.ownerAddr;
	}

	public void setOwnerAddr(String ownerAddr) {
		this.ownerAddr = ownerAddr;
	}

	@Column(name = "OWNER_LINKMAN", length = 20)
	public String getOwnerLinkman() {
		return this.ownerLinkman;
	}

	public void setOwnerLinkman(String ownerLinkman) {
		this.ownerLinkman = ownerLinkman;
	}

	@Column(name = "OWNER_PHONE", length = 50)
	public String getOwnerPhone() {
		return this.ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	@Column(name = "CUST_ALIAS", length = 100)
	public String getCustAlias() {
		return this.custAlias;
	}

	public void setCustAlias(String custAlias) {
		this.custAlias = custAlias;
	}

	@Column(name = "CUST_PROVINCE", nullable = false, length = 20)
	public String getCustProvince() {
		return this.custProvince;
	}

	public void setCustProvince(String custProvince) {
		this.custProvince = custProvince;
	}

	@Column(name = "CUST_CITY", nullable = false, length = 20)
	public String getCustCity() {
		return this.custCity;
	}

	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}

	@Column(name = "CUST_ZONE", nullable = false, length = 20)
	public String getCustZone() {
		return this.custZone;
	}

	public void setCustZone(String custZone) {
		this.custZone = custZone;
	}

	@Column(name = "CUST_ADDR", length = 128)
	public String getCustAddr() {
		return this.custAddr;
	}

	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}

	@Column(name = "CUST_LINKMAN", length = 20)
	public String getCustLinkman() {
		return this.custLinkman;
	}

	public void setCustLinkman(String custLinkman) {
		this.custLinkman = custLinkman;
	}

	@Column(name = "CUST_PHONE", length = 30)
	public String getCustPhone() {
		return this.custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "REMARK", length = 128)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Column(name = "RGST_DATE", nullable = false, length = 7)
	public Timestamp getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Timestamp rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "UPDT_NAME", length = 20)
	public String getUpdtName() {
		return this.updtName;
	}

	public void setUpdtName(String updtName) {
		this.updtName = updtName;
	}

	@Column(name = "UPDT_DATE", length = 7)
	public Timestamp getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Timestamp updtDate) {
		this.updtDate = updtDate;
	}

}