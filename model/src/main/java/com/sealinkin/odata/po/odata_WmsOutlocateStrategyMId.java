package com.sealinkin.odata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsOutlocateStrategyMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class odata_WmsOutlocateStrategyMId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private Integer locateStrategyId;

	// Constructors

	/** default constructor */
	public odata_WmsOutlocateStrategyMId() {
	}

	/** full constructor */
	public odata_WmsOutlocateStrategyMId(String enterpriseNo, Integer locateStrategyId) {
		this.enterpriseNo = enterpriseNo;
		this.locateStrategyId = locateStrategyId;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof odata_WmsOutlocateStrategyMId))
			return false;
		odata_WmsOutlocateStrategyMId castOther = (odata_WmsOutlocateStrategyMId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getLocateStrategyId() == castOther
						.getLocateStrategyId()) || (this.getLocateStrategyId() != null
						&& castOther.getLocateStrategyId() != null && this
						.getLocateStrategyId().equals(
								castOther.getLocateStrategyId())));
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
		return result;
	}

}