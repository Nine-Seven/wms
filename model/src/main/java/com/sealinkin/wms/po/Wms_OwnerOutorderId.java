package com.sealinkin.wms.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsOwnerOutorderId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_OwnerOutorderId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String ownerNo;
	private String expType;

	// Constructors

	/** default constructor */
	public Wms_OwnerOutorderId() {
	}

	/** full constructor */
	public Wms_OwnerOutorderId(String enterpriseNo, String ownerNo,
			String expType) {
		this.enterpriseNo = enterpriseNo;
		this.ownerNo = ownerNo;
		this.expType = expType;
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

	@Column(name = "EXP_TYPE", nullable = false, length = 5)
	public String getExpType() {
		return this.expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Wms_OwnerOutorderId))
			return false;
		Wms_OwnerOutorderId castOther = (Wms_OwnerOutorderId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getExpType() == castOther.getExpType()) || (this
						.getExpType() != null && castOther.getExpType() != null && this
						.getExpType().equals(castOther.getExpType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getExpType() == null ? 0 : this.getExpType().hashCode());
		return result;
	}

}