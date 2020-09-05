package com.sealinkin.wms.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsDefmodulequerycolumnId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_DefmodulequerycolumnId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String moduleid;
	private String columnid;
	private String enterpriseNo;

	// Constructors

	/** default constructor */
	public Wms_DefmodulequerycolumnId() {
	}

	/** full constructor */
	public Wms_DefmodulequerycolumnId(String moduleid, String columnid,
			String enterpriseNo) {
		this.moduleid = moduleid;
		this.columnid = columnid;
		this.enterpriseNo = enterpriseNo;
	}

	// Property accessors

	@Column(name = "MODULEID", nullable = false, length = 16)
	public String getModuleid() {
		return this.moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	@Column(name = "COLUMNID", nullable = false, length = 64)
	public String getColumnid() {
		return this.columnid;
	}

	public void setColumnid(String columnid) {
		this.columnid = columnid;
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
		if (!(other instanceof Wms_DefmodulequerycolumnId))
			return false;
		Wms_DefmodulequerycolumnId castOther = (Wms_DefmodulequerycolumnId) other;

		return ((this.getModuleid() == castOther.getModuleid()) || (this
				.getModuleid() != null && castOther.getModuleid() != null && this
				.getModuleid().equals(castOther.getModuleid())))
				&& ((this.getColumnid() == castOther.getColumnid()) || (this
						.getColumnid() != null
						&& castOther.getColumnid() != null && this
						.getColumnid().equals(castOther.getColumnid())))
				&& ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
						.getEnterpriseNo() != null
						&& castOther.getEnterpriseNo() != null && this
						.getEnterpriseNo().equals(castOther.getEnterpriseNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getModuleid() == null ? 0 : this.getModuleid().hashCode());
		result = 37 * result
				+ (getColumnid() == null ? 0 : this.getColumnid().hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		return result;
	}

}