package com.sealinkin.wms.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PntsetModuleReportSetId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_PntsetmodulereportsetId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String moduleId;

	// Constructors

	/** default constructor */
	public Wms_PntsetmodulereportsetId() {
	}

	/** full constructor */
	public Wms_PntsetmodulereportsetId(String enterpriseNo, String moduleId) {
		this.enterpriseNo = enterpriseNo;
		this.moduleId = moduleId;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "MODULE_ID", nullable = false, length = 24)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Wms_PntsetmodulereportsetId))
			return false;
		Wms_PntsetmodulereportsetId castOther = (Wms_PntsetmodulereportsetId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getModuleId() == castOther.getModuleId()) || (this
						.getModuleId() != null
						&& castOther.getModuleId() != null && this
						.getModuleId().equals(castOther.getModuleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getModuleId() == null ? 0 : this.getModuleId().hashCode());
		return result;
	}

}