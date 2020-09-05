package com.sealinkin.bdef.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsLogiboxRuleId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_LogiboxRuleId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String ruleId;

	// Constructors

	/** default constructor */
	public Wms_LogiboxRuleId() {
	}

	/** full constructor */
	public Wms_LogiboxRuleId(String enterpriseNo, String ruleId) {
		this.enterpriseNo = enterpriseNo;
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

	@Column(name = "RULE_ID", nullable = false, length = 5)
	public String getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Wms_LogiboxRuleId))
			return false;
		Wms_LogiboxRuleId castOther = (Wms_LogiboxRuleId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
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
		result = 37 * result
				+ (getRuleId() == null ? 0 : this.getRuleId().hashCode());
		return result;
	}

}