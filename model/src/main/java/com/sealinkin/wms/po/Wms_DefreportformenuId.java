package com.sealinkin.wms.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsDefreportformenuId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_DefreportformenuId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String moduleId;
	private String pgmId;
	private String enterpriseNo;

	// Constructors

	/** default constructor */
	public Wms_DefreportformenuId() {
	}

	/** full constructor */
	public Wms_DefreportformenuId(String moduleId, String pgmId,
			String enterpriseNo) {
		this.moduleId = moduleId;
		this.pgmId = pgmId;
		this.enterpriseNo = enterpriseNo;
	}

	// Property accessors

	@Column(name = "MODULE_ID", nullable = false, length = 20)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name = "PGM_ID", nullable = false, length = 20)
	public String getPgmId() {
		return this.pgmId;
	}

	public void setPgmId(String pgmId) {
		this.pgmId = pgmId;
	}

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Wms_DefreportformenuId))
			return false;
		Wms_DefreportformenuId castOther = (Wms_DefreportformenuId) other;

		return ((this.getModuleId() == castOther.getModuleId()) || (this
				.getModuleId() != null && castOther.getModuleId() != null && this
				.getModuleId().equals(castOther.getModuleId())))
				&& ((this.getPgmId() == castOther.getPgmId()) || (this
						.getPgmId() != null && castOther.getPgmId() != null && this
						.getPgmId().equals(castOther.getPgmId())))
				&& ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
						.getEnterpriseNo() != null
						&& castOther.getEnterpriseNo() != null && this
						.getEnterpriseNo().equals(castOther.getEnterpriseNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getModuleId() == null ? 0 : this.getModuleId().hashCode());
		result = 37 * result
				+ (getPgmId() == null ? 0 : this.getPgmId().hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		return result;
	}

}