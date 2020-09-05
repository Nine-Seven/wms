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
 * BillBaseAmount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BILL_BASE_AMOUNT")
public class Bill_BaseAmount implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bill_BaseAmountId id;
	private Date amountDate;
	private Double value;
	private String flag;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Bill_BaseAmount() {
	}

	/** minimal constructor */
	public Bill_BaseAmount(Bill_BaseAmountId id, Date amountDate, Double value,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.amountDate = amountDate;
		this.value = value;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Bill_BaseAmount(Bill_BaseAmountId id, Date amountDate, Double value,
			String flag, String rgstName, Date rgstDate, String updtName,
			Date updtDate) {
		this.id = id;
		this.amountDate = amountDate;
		this.value = value;
		this.flag = flag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "serialNo", column = @Column(name = "SERIAL_NO", nullable = false, length = 6)),
			@AttributeOverride(name = "billingProject", column = @Column(name = "BILLING_PROJECT", nullable = false, length = 8)) })
	public Bill_BaseAmountId getId() {
		return this.id;
	}

	public void setId(Bill_BaseAmountId id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AMOUNT_DATE", nullable = false, length = 7)
	public Date getAmountDate() {
		return this.amountDate;
	}

	public void setAmountDate(Date amountDate) {
		this.amountDate = amountDate;
	}

	@Column(name = "VALUE", nullable = false, precision = 12, scale = 3)
	public Double getValue() {
		return this.value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Column(name = "FLAG", length = 1)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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