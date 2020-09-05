package com.sealinkin.wms.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsOwnerRiordertypeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_OwnerRiordertypeId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String ownerNo;
	private String untreadType;
	private String classType;
	private String qualityFlag;

	// Constructors

	/** default constructor */
	public Wms_OwnerRiordertypeId() {
	}

	/** full constructor */
	public Wms_OwnerRiordertypeId(String enterpriseNo, String ownerNo,
			String untreadType, String classType, String qualityFlag) {
		this.enterpriseNo = enterpriseNo;
		this.ownerNo = ownerNo;
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

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
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
		if (!(other instanceof Wms_OwnerRiordertypeId))
			return false;
		Wms_OwnerRiordertypeId castOther = (Wms_OwnerRiordertypeId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
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
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
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