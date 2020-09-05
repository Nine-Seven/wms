package com.sealinkin.odata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsCheckStrategyId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Odata_WmsCheckStrategyId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String checkType;
	private Integer checkStrategyId;

	// Constructors

	/** default constructor */
	public Odata_WmsCheckStrategyId() {
	}

	/** full constructor */
	public Odata_WmsCheckStrategyId(String enterpriseNo, String checkType,
			Integer checkStrategyId) {
		this.enterpriseNo = enterpriseNo;
		this.checkType = checkType;
		this.checkStrategyId = checkStrategyId;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "CHECK_TYPE", nullable = false, length = 1)
	public String getCheckType() {
		return this.checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	@Column(name = "CHECK_STRATEGY_ID", nullable = false, precision = 5, scale = 0)
	public Integer getCheckStrategyId() {
		return this.checkStrategyId;
	}

	public void setCheckStrategyId(Integer checkStrategyId) {
		this.checkStrategyId = checkStrategyId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Odata_WmsCheckStrategyId))
			return false;
		Odata_WmsCheckStrategyId castOther = (Odata_WmsCheckStrategyId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getCheckType() == castOther.getCheckType()) || (this
						.getCheckType() != null
						&& castOther.getCheckType() != null && this
						.getCheckType().equals(castOther.getCheckType())))
				&& ((this.getCheckStrategyId() == castOther
						.getCheckStrategyId()) || (this.getCheckStrategyId() != null
						&& castOther.getCheckStrategyId() != null && this
						.getCheckStrategyId().equals(
								castOther.getCheckStrategyId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getCheckType() == null ? 0 : this.getCheckType().hashCode());
		result = 37
				* result
				+ (getCheckStrategyId() == null ? 0 : this.getCheckStrategyId()
						.hashCode());
		return result;
	}

}