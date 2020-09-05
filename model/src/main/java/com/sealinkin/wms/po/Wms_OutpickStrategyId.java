package com.sealinkin.wms.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsOutpickStrategyId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_OutpickStrategyId implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private Integer pickStrategyId;

	// Constructors

	/** default constructor */
	public Wms_OutpickStrategyId() {
	}

	/** full constructor */
	public Wms_OutpickStrategyId(String enterpriseNo, Integer pickStrategyId) {
		this.enterpriseNo = enterpriseNo;
		this.pickStrategyId = pickStrategyId;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "PICK_STRATEGY_ID", nullable = false, precision = 5, scale = 0)
	public Integer getPickStrategyId() {
		return this.pickStrategyId;
	}

	public void setPickStrategyId(Integer pickStrategyId) {
		this.pickStrategyId = pickStrategyId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Wms_OutpickStrategyId))
			return false;
		Wms_OutpickStrategyId castOther = (Wms_OutpickStrategyId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getPickStrategyId() == castOther.getPickStrategyId()) || (this
						.getPickStrategyId() != null
						&& castOther.getPickStrategyId() != null && this
						.getPickStrategyId().equals(
								castOther.getPickStrategyId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37
				* result
				+ (getPickStrategyId() == null ? 0 : this.getPickStrategyId()
						.hashCode());
		return result;
	}

}