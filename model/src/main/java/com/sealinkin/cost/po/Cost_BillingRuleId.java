package com.sealinkin.cost.po;


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CostBillingRuleId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Cost_BillingRuleId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String billingType;
	private String billingUnit;
	private Short ruleId;

	// Constructors

	/** default constructor */
	public Cost_BillingRuleId() {
	}

	/** full constructor */
	public Cost_BillingRuleId(String enterpriseNo, String billingType,
			String billingUnit, Short ruleId) {
		this.enterpriseNo = enterpriseNo;
		this.billingType = billingType;
		this.billingUnit = billingUnit;
		this.ruleId = ruleId;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "BILLING_TYPE", nullable = false, length = 3)
	public String getBillingType() {
		return this.billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	@Column(name = "BILLING_UNIT", nullable = false, length = 3)
	public String getBillingUnit() {
		return this.billingUnit;
	}

	public void setBillingUnit(String billingUnit) {
		this.billingUnit = billingUnit;
	}

	@Column(name = "RULE_ID", nullable = false, precision = 3, scale = 0)
	public Short getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(Short ruleId) {
		this.ruleId = ruleId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Cost_BillingRuleId))
			return false;
		Cost_BillingRuleId castOther = (Cost_BillingRuleId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getBillingType() == castOther.getBillingType()) || (this
						.getBillingType() != null
						&& castOther.getBillingType() != null && this
						.getBillingType().equals(castOther.getBillingType())))
				&& ((this.getBillingUnit() == castOther.getBillingUnit()) || (this
						.getBillingUnit() != null
						&& castOther.getBillingUnit() != null && this
						.getBillingUnit().equals(castOther.getBillingUnit())))
				&& ((this.getRuleId() == castOther.getRuleId()) || (this
						.getRuleId() != null && castOther.getRuleId() != null && this
						.getRuleId().equals(castOther.getRuleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37
				* result
				+ (getBillingType() == null ? 0 : this.getBillingType()
						.hashCode());
		result = 37
				* result
				+ (getBillingUnit() == null ? 0 : this.getBillingUnit()
						.hashCode());
		result = 37 * result
				+ (getRuleId() == null ? 0 : this.getRuleId().hashCode());
		return result;
	}

}
