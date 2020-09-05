package com.sealinkin.bdef.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsDefbaseId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bdef_WmsDefbaseId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String colname;

	// Constructors

	/** default constructor */
	public Bdef_WmsDefbaseId() {
	}

	/** full constructor */
	public Bdef_WmsDefbaseId(String enterpriseNo, String colname) {
		this.enterpriseNo = enterpriseNo;
		this.colname = colname;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "COLNAME", nullable = false, length = 50)
	public String getColname() {
		return this.colname;
	}

	public void setColname(String colname) {
		this.colname = colname;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bdef_WmsDefbaseId))
			return false;
		Bdef_WmsDefbaseId castOther = (Bdef_WmsDefbaseId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getColname() == castOther.getColname()) || (this
						.getColname() != null && castOther.getColname() != null && this
						.getColname().equals(castOther.getColname())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getColname() == null ? 0 : this.getColname().hashCode());
		return result;
	}

}