package com.sealinkin.wms.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PntsetModuleReportQueryId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_PntsetmodulereportqueryId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String moduleId;
	private String columnid;

	// Constructors

	/** default constructor */
	public Wms_PntsetmodulereportqueryId() {
	}

	/** full constructor */
	public Wms_PntsetmodulereportqueryId(String enterpriseNo, String moduleId,
			String columnid) {
		this.enterpriseNo = enterpriseNo;
		this.moduleId = moduleId;
		this.columnid = columnid;
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

	@Column(name = "COLUMNID", nullable = false, length = 64)
	public String getColumnid() {
		return this.columnid;
	}

	public void setColumnid(String columnid) {
		this.columnid = columnid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Wms_PntsetmodulereportqueryId))
			return false;
		Wms_PntsetmodulereportqueryId castOther = (Wms_PntsetmodulereportqueryId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getModuleId() == castOther.getModuleId()) || (this
						.getModuleId() != null
						&& castOther.getModuleId() != null && this
						.getModuleId().equals(castOther.getModuleId())))
				&& ((this.getColumnid() == castOther.getColumnid()) || (this
						.getColumnid() != null
						&& castOther.getColumnid() != null && this
						.getColumnid().equals(castOther.getColumnid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getModuleId() == null ? 0 : this.getModuleId().hashCode());
		result = 37 * result
				+ (getColumnid() == null ? 0 : this.getColumnid().hashCode());
		return result;
	}

}