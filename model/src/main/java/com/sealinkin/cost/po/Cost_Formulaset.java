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
 * CostFormulaset entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COST_FORMULASET")
public class Cost_Formulaset implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Cost_FormulasetId id;
	private String projectName;
	private String standardFlag;
	private Date endDate;
	private String status;
	private String billingFlag;
	private String billingCycle;
	private String balanceDay;
	private String billingUnit;
	private String valueFlag;
	private Double unitPrice;
	private Double fixedValue;
	private Double otherCost1;
	private Double otherCost2;
	private Double otherCost3;
	private Double otherCost4;
	private Double otherCost5;
	private String remark;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String costFlag;
	private Date beginDate;

	// Constructors

	/** default constructor */
	public Cost_Formulaset() {
	}

	/** minimal constructor */
	public Cost_Formulaset(Cost_FormulasetId id, String projectName,
			String standardFlag, String billingFlag, String billingCycle,
			Double unitPrice, String rgstName, Date rgstDate, String costFlag) {
		this.id = id;
		this.projectName = projectName;
		this.standardFlag = standardFlag;
		this.billingFlag = billingFlag;
		this.billingCycle = billingCycle;
		this.unitPrice = unitPrice;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.costFlag = costFlag;
	}

	/** full constructor */
	public Cost_Formulaset(Cost_FormulasetId id, String projectName,
			String standardFlag, Date endDate, String status,
			String billingFlag, String billingCycle, String balanceDay,
			String billingUnit, String valueFlag, Double unitPrice,
			Double fixedValue, Double otherCost1,
			Double otherCost2, Double otherCost3,
			Double otherCost4, Double otherCost5, String remark,
			String rgstName, Date rgstDate, String updtName, Date updtDate, String costFlag, Date beginDate) {
		this.id = id;
		this.projectName = projectName;
		this.standardFlag = standardFlag;
		this.endDate = endDate;
		this.status = status;
		this.billingFlag = billingFlag;
		this.billingCycle = billingCycle;
		this.balanceDay = balanceDay;
		this.billingUnit = billingUnit;
		this.valueFlag = valueFlag;
		this.unitPrice = unitPrice;
		this.fixedValue = fixedValue;
		this.otherCost1 = otherCost1;
		this.otherCost2 = otherCost2;
		this.otherCost3 = otherCost3;
		this.otherCost4 = otherCost4;
		this.otherCost5 = otherCost5;
		this.remark = remark;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.costFlag = costFlag;
		this.beginDate = beginDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "billingProject", column = @Column(name = "BILLING_PROJECT", nullable = false, length = 8)),
			@AttributeOverride(name = "billingType", column = @Column(name = "BILLING_TYPE", nullable = false, length = 3)) })
	public Cost_FormulasetId getId() {
		return this.id;
	}

	public void setId(Cost_FormulasetId id) {
		this.id = id;
	}

	@Column(name = "PROJECT_NAME", nullable = false, length = 64)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "STANDARD_FLAG", nullable = false, length = 1)
	public String getStandardFlag() {
		return this.standardFlag;
	}

	public void setStandardFlag(String standardFlag) {
		this.standardFlag = standardFlag;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", length = 7)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "BILLING_FLAG", nullable = false, length = 3)
	public String getBillingFlag() {
		return this.billingFlag;
	}

	public void setBillingFlag(String billingFlag) {
		this.billingFlag = billingFlag;
	}

	@Column(name = "BILLING_CYCLE", nullable = false, length = 3)
	public String getBillingCycle() {
		return this.billingCycle;
	}

	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}

	@Column(name = "BALANCE_DAY", length = 20)
	public String getBalanceDay() {
		return this.balanceDay;
	}

	public void setBalanceDay(String balanceDay) {
		this.balanceDay = balanceDay;
	}

	@Column(name = "BILLING_UNIT", length = 3)
	public String getBillingUnit() {
		return this.billingUnit;
	}

	public void setBillingUnit(String billingUnit) {
		this.billingUnit = billingUnit;
	}

	@Column(name = "VALUE_FLAG", length = 1)
	public String getValueFlag() {
		return this.valueFlag;
	}

	public void setValueFlag(String valueFlag) {
		this.valueFlag = valueFlag;
	}

	@Column(name = "UNIT_PRICE", nullable = false, precision = 12, scale = 3)
	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "FIXED_VALUE", precision = 22, scale = 0)
	public Double getFixedValue() {
		return this.fixedValue;
	}

	public void setFixedValue(Double fixedValue) {
		this.fixedValue = fixedValue;
	}

	@Column(name = "OTHER_COST1", precision = 22, scale = 0)
	public Double getOtherCost1() {
		return this.otherCost1;
	}

	public void setOtherCost1(Double otherCost1) {
		this.otherCost1 = otherCost1;
	}

	@Column(name = "OTHER_COST2", precision = 22, scale = 0)
	public Double getOtherCost2() {
		return this.otherCost2;
	}

	public void setOtherCost2(Double otherCost2) {
		this.otherCost2 = otherCost2;
	}

	@Column(name = "OTHER_COST3", precision = 22, scale = 0)
	public Double getOtherCost3() {
		return this.otherCost3;
	}

	public void setOtherCost3(Double otherCost3) {
		this.otherCost3 = otherCost3;
	}

	@Column(name = "OTHER_COST4", precision = 22, scale = 0)
	public Double getOtherCost4() {
		return this.otherCost4;
	}

	public void setOtherCost4(Double otherCost4) {
		this.otherCost4 = otherCost4;
	}

	@Column(name = "OTHER_COST5", precision = 22, scale = 0)
	public Double getOtherCost5() {
		return this.otherCost5;
	}

	public void setOtherCost5(Double otherCost5) {
		this.otherCost5 = otherCost5;
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
	@Column(name = "COST_FLAG", nullable = false, length = 1)
	public String getCostFlag() {
		return this.costFlag;
	}

	public void setCostFlag(String costFlag) {
		this.costFlag = costFlag;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "BEGIN_DATE", length = 7)
	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
}