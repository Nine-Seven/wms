package com.sealinkin.wms.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsDefcustomDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_DefcustomDId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customId;
	private String paramName;
	private String enterpriseNo;

	// Constructors

	/** default constructor */
	public Wms_DefcustomDId() {
	}

	/** full constructor */
	public Wms_DefcustomDId(String customId, String paramName,
			String enterpriseNo) {
		this.customId = customId;
		this.paramName = paramName;
		this.enterpriseNo = enterpriseNo;
	}

	// Property accessors

	@Column(name = "CUSTOM_ID", nullable = false, length = 30)
	public String getCustomId() {
		return this.customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	@Column(name = "PARAM_NAME", nullable = false, length = 50)
	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
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
		if (!(other instanceof Wms_DefcustomDId))
			return false;
		Wms_DefcustomDId castOther = (Wms_DefcustomDId) other;

		return ((this.getCustomId() == castOther.getCustomId()) || (this
				.getCustomId() != null && castOther.getCustomId() != null && this
				.getCustomId().equals(castOther.getCustomId())))
				&& ((this.getParamName() == castOther.getParamName()) || (this
						.getParamName() != null
						&& castOther.getParamName() != null && this
						.getParamName().equals(castOther.getParamName())))
				&& ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
						.getEnterpriseNo() != null
						&& castOther.getEnterpriseNo() != null && this
						.getEnterpriseNo().equals(castOther.getEnterpriseNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCustomId() == null ? 0 : this.getCustomId().hashCode());
		result = 37 * result
				+ (getParamName() == null ? 0 : this.getParamName().hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		return result;
	}

}