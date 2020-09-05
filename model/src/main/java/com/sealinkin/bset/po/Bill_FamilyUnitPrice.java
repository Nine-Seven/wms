package com.sealinkin.bset.po;

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
 * BillFamilyUnitPrice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BILL_FAMILY_UNIT_PRICE")
public class Bill_FamilyUnitPrice implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bill_FamilyUnitPriceId id;
	private String billingUnit;
	private Double unitPrice;
	private String appendCondition;
	private Double appendValue1;
	private Double appendValue2;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Bill_FamilyUnitPrice() {
	}

	/** minimal constructor */
	public Bill_FamilyUnitPrice(Bill_FamilyUnitPriceId id, String billingUnit,
			Double unitPrice, Double appendValue1, Double appendValue2,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.billingUnit = billingUnit;
		this.unitPrice = unitPrice;
		this.appendValue1 = appendValue1;
		this.appendValue2 = appendValue2;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Bill_FamilyUnitPrice(Bill_FamilyUnitPriceId id, String billingUnit,
			Double unitPrice, String appendCondition, Double appendValue1,
			Double appendValue2, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.billingUnit = billingUnit;
		this.unitPrice = unitPrice;
		this.appendCondition = appendCondition;
		this.appendValue1 = appendValue1;
		this.appendValue2 = appendValue2;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "familyNo", column = @Column(name = "FAMILY_NO", nullable = false, length = 10)),
			@AttributeOverride(name = "billingProject", column = @Column(name = "BILLING_PROJECT", nullable = false, length = 8)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)) })
	public Bill_FamilyUnitPriceId getId() {
		return this.id;
	}

	public void setId(Bill_FamilyUnitPriceId id) {
		this.id = id;
	}

	@Column(name = "BILLING_UNIT", nullable = false, length = 3)
	public String getBillingUnit() {
		return this.billingUnit;
	}

	public void setBillingUnit(String billingUnit) {
		this.billingUnit = billingUnit;
	}

	@Column(name = "UNIT_PRICE", nullable = false, precision = 12, scale = 3)
	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "APPEND_CONDITION", length = 1)
	public String getAppendCondition() {
		return this.appendCondition;
	}

	public void setAppendCondition(String appendCondition) {
		this.appendCondition = appendCondition;
	}

	@Column(name = "APPEND_VALUE1", nullable = false, precision = 12, scale = 3)
	public Double getAppendValue1() {
		return this.appendValue1;
	}

	public void setAppendValue1(Double appendValue1) {
		this.appendValue1 = appendValue1;
	}

	@Column(name = "APPEND_VALUE2", nullable = false, precision = 12, scale = 3)
	public Double getAppendValue2() {
		return this.appendValue2;
	}

	public void setAppendValue2(Double appendValue2) {
		this.appendValue2 = appendValue2;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
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

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}