package com.sealinkin.wms.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsOutwaveplanMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_OutwaveplanMId implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private Integer batchStrategyId;

	// Constructors

	/** default constructor */
	public Wms_OutwaveplanMId() {
	}

	/** full constructor */
	public Wms_OutwaveplanMId(String enterpriseNo, Integer batchStrategyId) {
		this.enterpriseNo = enterpriseNo;
		this.batchStrategyId = batchStrategyId;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Wms_OutwaveplanMId))
			return false;
		Wms_OutwaveplanMId castOther = (Wms_OutwaveplanMId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getBatchStrategyId() == castOther
						.getBatchStrategyId()) || (this.getBatchStrategyId() != null
						&& castOther.getBatchStrategyId() != null && this
						.getBatchStrategyId().equals(
								castOther.getBatchStrategyId())));
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
		return result;
	}

}