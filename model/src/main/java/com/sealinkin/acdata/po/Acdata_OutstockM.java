package com.sealinkin.acdata.po;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AcdataOutstockM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACDATA_OUTSTOCK_M")
public class Acdata_OutstockM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Acdata_OutstockMId id;
	private String orderNo;
	private String sourceNo;
	private String ownerAlias;
	private String custAlias;
	private String status;
	private String remark;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Acdata_OutstockM() {
	}

	/** minimal constructor */
	public Acdata_OutstockM(Acdata_OutstockMId id, String orderNo,
			String ownerAlias, String status, String rgstName,
			Date rgstDate) {
		this.id = id;
		this.orderNo = orderNo;
		this.ownerAlias = ownerAlias;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Acdata_OutstockM(Acdata_OutstockMId id, String orderNo,
			String sourceNo, String ownerAlias, String custAlias,
			String status, String remark, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.orderNo = orderNo;
		this.sourceNo = sourceNo;
		this.ownerAlias = ownerAlias;
		this.custAlias = custAlias;
		this.status = status;
		this.remark = remark;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "outstockNo", column = @Column(name = "OUTSTOCK_NO", nullable = false, length = 20)) })
	public Acdata_OutstockMId getId() {
		return this.id;
	}

	public void setId(Acdata_OutstockMId id) {
		this.id = id;
	}

	@Column(name = "ORDER_NO", nullable = false, length = 20)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "SOURCE_NO", length = 50)
	public String getSourceNo() {
		return this.sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	@Column(name = "OWNER_ALIAS", nullable = false, length = 100)
	public String getOwnerAlias() {
		return this.ownerAlias;
	}

	public void setOwnerAlias(String ownerAlias) {
		this.ownerAlias = ownerAlias;
	}

	@Column(name = "CUST_ALIAS", length = 100)
	public String getCustAlias() {
		return this.custAlias;
	}

	public void setCustAlias(String custAlias) {
		this.custAlias = custAlias;
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
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
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
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}