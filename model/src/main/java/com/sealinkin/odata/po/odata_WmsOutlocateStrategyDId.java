package com.sealinkin.odata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsOutlocateStrategyDId entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Embeddable
public class odata_WmsOutlocateStrategyDId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private Integer locateStrategyId;
	private String locateRuleId;

	// Constructors

	/** default constructor */
	public odata_WmsOutlocateStrategyDId() {
	}

	/** full constructor */
	public odata_WmsOutlocateStrategyDId(String enterpriseNo,
			Integer locateStrategyId, String locateRuleId) {
		this.enterpriseNo = enterpriseNo;
		this.locateStrategyId = locateStrategyId;
		this.locateRuleId = locateRuleId;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "LOCATE_STRATEGY_ID", nullable = false, precision = 5, scale = 0)
	public Integer getLocateStrategyId() {
		return this.locateStrategyId;
	}

	public void setLocateStrategyId(Integer locateStrategyId) {
		this.locateStrategyId = locateStrategyId;
	}

	@Column(name = "LOCATE_RULE_ID", nullable = false, length = 5)
	public String getLocateRuleId() {
		return this.locateRuleId;
	}

	public void setLocateRuleId(String locateRuleId2) {
		this.locateRuleId = locateRuleId2;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof odata_WmsOutlocateStrategyDId))
			return false;
		odata_WmsOutlocateStrategyDId castOther = (odata_WmsOutlocateStrategyDId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getLocateStrategyId() == castOther
						.getLocateStrategyId()) || (this.getLocateStrategyId() != null
						&& castOther.getLocateStrategyId() != null && this
						.getLocateStrategyId().equals(
								castOther.getLocateStrategyId())))
				&& ((this.getLocateRuleId() == castOther.getLocateRuleId()) || (this
						.getLocateRuleId() != null
						&& castOther.getLocateRuleId() != null && this
						.getLocateRuleId().equals(castOther.getLocateRuleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37
				* result
				+ (getLocateStrategyId() == null ? 0 : this
						.getLocateStrategyId().hashCode());
		result = 37
				* result
				+ (getLocateRuleId() == null ? 0 : this.getLocateRuleId()
						.hashCode());
		return result;
	}

}