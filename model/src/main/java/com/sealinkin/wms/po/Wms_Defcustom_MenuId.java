package com.sealinkin.wms.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsDefcustomMenuId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_Defcustom_MenuId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String moduleId;
	private String customId;
	private String enterpriseNo;

	// Constructors

	/** default constructor */
	public Wms_Defcustom_MenuId() {
	}

	/** full constructor */
	public Wms_Defcustom_MenuId(String moduleId, String customId,
			String enterpriseNo) {
		this.moduleId = moduleId;
		this.customId = customId;
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

	@Column(name = "CUSTOM_ID", nullable = false, length = 30)
	public String getCustomId() {
		return this.customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
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
		if (!(other instanceof Wms_Defcustom_MenuId))
			return false;
		Wms_Defcustom_MenuId castOther = (Wms_Defcustom_MenuId) other;

		return ((this.getModuleId() == castOther.getModuleId()) || (this
				.getModuleId() != null && castOther.getModuleId() != null && this
				.getModuleId().equals(castOther.getModuleId())))
				&& ((this.getCustomId() == castOther.getCustomId()) || (this
						.getCustomId() != null
						&& castOther.getCustomId() != null && this
						.getCustomId().equals(castOther.getCustomId())))
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
				+ (getCustomId() == null ? 0 : this.getCustomId().hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		return result;
	}

}