package com.sealinkin.wms.po;
 

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Wms_OutorderId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_OutorderId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String expType;

	// Constructors

	/** default constructor */
	public Wms_OutorderId() {
	}

	/** full constructor */
	public Wms_OutorderId(String enterpriseNo, String expType) {
		this.enterpriseNo = enterpriseNo;
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
		if (!(other instanceof Wms_OutorderId))
			return false;
		Wms_OutorderId castOther = (Wms_OutorderId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
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
				+ (getExpType() == null ? 0 : this.getExpType().hashCode());
		return result;
	}

}