package com.sealinkin.cost.po;


import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CostBillingType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COST_BILLING_TYPE")
public class Cost_BillingType implements java.io.Serializable {

	// Fields

	private Cost_BillingTypeId id;
	private String billingTypeName;
	private String status;
	private String memo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Cost_BillingType() {
	}

	/** minimal constructor */
	public Cost_BillingType(Cost_BillingTypeId id, String rgstName, Date rgstDate) {
		this.id = id;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Cost_BillingType(Cost_BillingTypeId id, String billingTypeName,
			String status, String memo, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.billingTypeName = billingTypeName;
		this.status = status;
		this.memo = memo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "billingType", column = @Column(name = "BILLING_TYPE", nullable = false, length = 3)) })
	public Cost_BillingTypeId getId() {
		return this.id;
	}

	public void setId(Cost_BillingTypeId id) {
		this.id = id;
	}

	@Column(name = "BILLING_TYPE_NAME", length = 20)
	public String getBillingTypeName() {
		return this.billingTypeName;
	}

	public void setBillingTypeName(String billingTypeName) {
		this.billingTypeName = billingTypeName;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "MEMO", length = 100)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.TIMESTAMP)
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}
