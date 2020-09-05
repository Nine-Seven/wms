package com.sealinkin.wms.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsOutwaveplanDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_OutwaveplanDId implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private Integer batchStrategyId;
	private String batchRuleId;

	// Constructors

	/** default constructor */
	public Wms_OutwaveplanDId() {
	}

	/** full constructor */
	public Wms_OutwaveplanDId(String enterpriseNo, Integer batchStrategyId,
			String batchRuleId) {
		this.enterpriseNo = enterpriseNo;
		this.batchStrategyId = batchStrategyId;
		this.batchRuleId = batchRuleId;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "BATCH_STRATEGY_ID", nullable = false, precision = 5, scale = 0)
	public Integer getBatchStrategyId() {
		return this.batchStrategyId;
	}

	public void setBatchStrategyId(Integer batchStrategyId) {
		this.batchStrategyId = batchStrategyId;
	}

	@Column(name = "BATCH_RULE_ID", nullable = false, length = 5)
	public String getBatchRuleId() {
		return this.batchRuleId;
	}

	public void setBatchRuleId(String batchRuleId) {
		this.batchRuleId = batchRuleId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Wms_OutwaveplanDId))
			return false;
		Wms_OutwaveplanDId castOther = (Wms_OutwaveplanDId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getBatchStrategyId() == castOther
						.getBatchStrategyId()) || (this.getBatchStrategyId() != null
						&& castOther.getBatchStrategyId() != null && this
						.getBatchStrategyId().equals(
								castOther.getBatchStrategyId())))
				&& ((this.getBatchRuleId() == castOther.getBatchRuleId()) || (this
						.getBatchRuleId() != null
						&& castOther.getBatchRuleId() != null && this
						.getBatchRuleId().equals(castOther.getBatchRuleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37
				* result
				+ (getBatchStrategyId() == null ? 0 : this.getBatchStrategyId()
						.hashCode());
		result = 37
				* result
				+ (getBatchRuleId() == null ? 0 : this.getBatchRuleId()
						.hashCode());
		return result;
	}

}