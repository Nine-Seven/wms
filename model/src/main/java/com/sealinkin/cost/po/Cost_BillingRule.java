package com.sealinkin.cost.po;


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
 * CostBillingRule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COST_BILLING_RULE")
public class Cost_BillingRule implements java.io.Serializable {

	// Fields

	private Cost_BillingRuleId id;
	private String strategyName;
	private String useType;
	private String standardSql;
	private String nonstandardSql;
	private BigDecimal sqlOrder;
	private String memo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Cost_BillingRule() {
	}

	/** minimal constructor */
	public Cost_BillingRule(Cost_BillingRuleId id, String strategyName,
			BigDecimal sqlOrder, String rgstName, Date rgstDate) {
		this.id = id;
		this.strategyName = strategyName;
		this.sqlOrder = sqlOrder;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Cost_BillingRule(Cost_BillingRuleId id, String strategyName,
			String useType, String standardSql, String nonstandardSql,
			BigDecimal sqlOrder, String memo, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.strategyName = strategyName;
		this.useType = useType;
		this.standardSql = standardSql;
		this.nonstandardSql = nonstandardSql;
		this.sqlOrder = sqlOrder;
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
			@AttributeOverride(name = "billingType", column = @Column(name = "BILLING_TYPE", nullable = false, length = 3)),
			@AttributeOverride(name = "billingUnit", column = @Column(name = "BILLING_UNIT", nullable = false, length = 3)),
			@AttributeOverride(name = "ruleId", column = @Column(name = "RULE_ID", nullable = false, precision = 3, scale = 0)) })
	public Cost_BillingRuleId getId() {
		return this.id;
	}

	public void setId(Cost_BillingRuleId id) {
		this.id = id;
	}

	@Column(name = "STRATEGY_NAME", nullable = false, length = 100)
	public String getStrategyName() {
		return this.strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	@Column(name = "USE_TYPE", length = 1)
	public String getUseType() {
		return this.useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	@Column(name = "STANDARD_SQL", length = 4000)
	public String getStandardSql() {
		return this.standardSql;
	}

	public void setStandardSql(String standardSql) {
		this.standardSql = standardSql;
	}

	@Column(name = "NONSTANDARD_SQL", length = 4000)
	public String getNonstandardSql() {
		return this.nonstandardSql;
	}

	public void setNonstandardSql(String nonstandardSql) {
		this.nonstandardSql = nonstandardSql;
	}

	@Column(name = "SQL_ORDER", nullable = false, precision = 22, scale = 0)
	public BigDecimal getSqlOrder() {
		return this.sqlOrder;
	}

	public void setSqlOrder(BigDecimal sqlOrder) {
		this.sqlOrder = sqlOrder;
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
