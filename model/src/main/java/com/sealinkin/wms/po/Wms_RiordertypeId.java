package com.sealinkin.wms.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Wms_RiordertypeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_RiordertypeId implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String untreadType;
	private String classType;
	private String qualityFlag;

	// Constructors

	/** default constructor */
	public Wms_RiordertypeId() {
	}

	/** full constructor */
	public Wms_RiordertypeId(String enterpriseNo, String untreadType,
			String classType, String qualityFlag) {
		this.enterpriseNo = enterpriseNo;
		this.untreadType = untreadType;
		this.classType = classType;
		this.qualityFlag = qualityFlag;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "UNTREAD_TYPE", nullable = false, length = 5)
	public String getUntreadType() {
		return this.untreadType;
	}

	public void setUntreadType(String untreadType) {
		this.untreadType = untreadType;
	}

	@Column(name = "CLASS_TYPE", nullable = false, length = 1)
	public String getClassType() {
		return this.classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	@Column(name = "QUALITY_FLAG", nullable = false, length = 1)
	public String getQualityFlag() {
		return this.qualityFlag;
	}

	public void setQualityFlag(String qualityFlag) {
		this.qualityFlag = qualityFlag;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Wms_RiordertypeId))
			return false;
		Wms_RiordertypeId castOther = (Wms_RiordertypeId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getUntreadType() == castOther.getUntreadType()) || (this
						.getUntreadType() != null
						&& castOther.getUntreadType() != null && this
						.getUntreadType().equals(castOther.getUntreadType())))
				&& ((this.getClassType() == castOther.getClassType()) || (this
						.getClassType() != null
						&& castOther.getClassType() != null && this
						.getClassType().equals(castOther.getClassType())))
				&& ((this.getQualityFlag() == castOther.getQualityFlag()) || (this
						.getQualityFlag() != null
						&& castOther.getQualityFlag() != null && this
						.getQualityFlag().equals(castOther.getQualityFlag())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37
				* result
				+ (getUntreadType() == null ? 0 : this.getUntreadType()
						.hashCode());
		result = 37 * result
				+ (getClassType() == null ? 0 : this.getClassType().hashCode());
		result = 37
				* result
				+ (getQualityFlag() == null ? 0 : this.getQualityFlag()
						.hashCode());
		return result;
	}

}