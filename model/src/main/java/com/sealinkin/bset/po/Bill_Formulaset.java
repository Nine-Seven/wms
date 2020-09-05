package com.sealinkin.bset.po;
// default package

import java.math.BigDecimal;
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
 * BillFormulaset entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BILL_FORMULASET")
public class Bill_Formulaset implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bill_FormulasetId id;
	private String projectName;
	private String billingCycle;
	private String billingFlag;
	private String billingUnit;
	private Double unitPrice;
	private BigDecimal fixedValue;
	private String valueFlag;
	private String appendCondition;
	private Double appendValue1;
	private Double appendValue2;
	private String remark;
	private String status;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private Date endDate;
	private String balanceDay;

	// Constructors

	/** default constructor */
	public Bill_Formulaset() {
	}

	/** minimal constructor */
	public Bill_Formulaset(Bill_FormulasetId id, String projectName,
			String billingCycle, String billingFlag, String billingUnit,
			Double unitPrice, String rgstName, Date rgstDate) {
		this.id = id;
		this.projectName = projectName;
		this.billingCycle = billingCycle;
		this.billingFlag = billingFlag;
		this.billingUnit = billingUnit;
		this.unitPrice = unitPrice;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Bill_Formulaset(Bill_FormulasetId id, String projectName,
			String billingCycle, String billingFlag, String billingUnit,
			Double unitPrice, BigDecimal fixedValue, String valueFlag,
			String appendCondition, Double appendValue1, Double appendValue2,
			String remark, String status, String rgstName, Date rgstDate,
			String updtName, Date updtDate, Date endDate, String balanceDay) {
		this.id = id;
		this.projectName = projectName;
		this.billingCycle = billingCycle;
		this.billingFlag = billingFlag;
		this.billingUnit = billingUnit;
		this.unitPrice = unitPrice;
		this.fixedValue = fixedValue;
		this.valueFlag = valueFlag;
		this.appendCondition = appendCondition;
		this.appendValue1 = appendValue1;
		this.appendValue2 = appendValue2;
		this.remark = remark;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.endDate = endDate;
		this.balanceDay = balanceDay;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "billingProject", column = @Column(name = "BILLING_PROJECT", nullable = false, length = 8)),
			@AttributeOverride(name = "billingType", column = @Column(name = "BILLING_TYPE", nullable = false, length = 3)) })
	public Bill_FormulasetId getId() {
		return this.id;
	}

	public void setId(Bill_FormulasetId id) {
		this.id = id;
	}

	@Column(name = "PROJECT_NAME", nullable = false, length = 64)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "BILLING_CYCLE", nullable = false, length = 3)
	public String getBillingCycle() {
		return this.billingCycle;
	}

	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}

	@Column(name = "BILLING_FLAG", nullable = false, length = 3)
	public String getBillingFlag() {
		return this.billingFlag;
	}

	public void setBillingFlag(String billingFlag) {
		this.billingFlag = billingFlag;
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

	@Column(name = "FIXED_VALUE", precision = 22, scale = 0)
	public BigDecimal getFixedValue() {
		return this.fixedValue;
	}

	public void setFixedValue(BigDecimal fixedValue) {
		this.fixedValue = fixedValue;
	}

	@Column(name = "VALUE_FLAG", length = 1)
	public String getValueFlag() {
		return this.valueFlag;
	}

	public void setValueFlag(String valueFlag) {
		this.valueFlag = valueFlag;
	}

	@Column(name = "APPEND_CONDITION", length = 1)
	public String getAppendCondition() {
		return this.appendCondition;
	}

	public void setAppendCondition(String appendCondition) {
		this.appendCondition = appendCondition;
	}

	@Column(name = "APPEND_VALUE1", precision = 12, scale = 3)
	public Double getAppendValue1() {
		return this.appendValue1;
	}

	public void setAppendValue1(Double appendValue1) {
		this.appendValue1 = appendValue1;
	}

	@Column(name = "APPEND_VALUE2", precision = 12, scale = 3)
	public Double getAppendValue2() {
		return this.appendValue2;
	}

	public void setAppendValue2(Double appendValue2) {
		this.appendValue2 = appendValue2;
	}

	@Column(name = "REMARK", length = 225)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", length = 7)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "BALANCE_DAY", length = 20)
	public String getBalanceDay() {
		return this.balanceDay;
	}

	public void setBalanceDay(String balanceDay) {
		this.balanceDay = balanceDay;
	}
}