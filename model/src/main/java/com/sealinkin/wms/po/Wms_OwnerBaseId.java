package com.sealinkin.wms.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsOwnerBaseId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_OwnerBaseId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String ownerNo;
	private String colname;

	// Constructors

	/** default constructor */
	public Wms_OwnerBaseId() {
	}

	/** full constructor */
	public Wms_OwnerBaseId(String enterpriseNo, String ownerNo, String colname) {
		this.enterpriseNo = enterpriseNo;
		this.ownerNo = ownerNo;
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

	@Column(name = "OWNER_NO", nullable = false, length = 5)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
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
		if (!(other instanceof Wms_OwnerBaseId))
			return false;
		Wms_OwnerBaseId castOther = (Wms_OwnerBaseId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
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
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getColname() == null ? 0 : this.getColname().hashCode());
		return result;
	}

}