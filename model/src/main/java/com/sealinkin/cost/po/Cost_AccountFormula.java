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
 * CostAccountFormula entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COST_ACCOUNT_FORMULA")
public class Cost_AccountFormula implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Cost_AccountFormulaId id;
	private String billingType;
	private String remark;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Cost_AccountFormula() {
	}

	/** minimal constructor */
	public Cost_AccountFormula(Cost_AccountFormulaId id, String billingType,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.billingType = billingType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Cost_AccountFormula(Cost_AccountFormulaId id, String billingType,
			String remark, String rgstName, Date rgstDate, String updtName,
			Date updtDate) {
		this.id = id;
		this.billingType = billingType;
		this.remark = remark;
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
			@AttributeOverride(name = "accountNo", column = @Column(name = "ACCOUNT_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "billingProject", column = @Column(name = "BILLING_PROJECT", nullable = false, length = 8)) })
	public Cost_AccountFormulaId getId() {
		return this.id;
	}

	public void setId(Cost_AccountFormulaId id) {
		this.id = id;
	}

	@Column(name = "BILLING_TYPE", nullable = false, length = 3)
	public String getBillingType() {
		return this.billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	@Column(name = "REMARK", length = 225)
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