package com.sealinkin.bdef.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BdefDefsupplierId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bdef_DefsupplierId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String ownerNo;
	private String supplierNo;

	// Constructors

	/** default constructor */
	public Bdef_DefsupplierId() {
	}

	/** full constructor */
	public Bdef_DefsupplierId(String enterpriseNo, String ownerNo,
			String supplierNo) {
		this.enterpriseNo = enterpriseNo;
		this.ownerNo = ownerNo;
		this.supplierNo = supplierNo;
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

	@Column(name = "SUPPLIER_NO", nullable = false, length = 10)
	public String getSupplierNo() {
		return this.supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bdef_DefsupplierId))
			return false;
		Bdef_DefsupplierId castOther = (Bdef_DefsupplierId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getSupplierNo() == castOther.getSupplierNo()) || (this
						.getSupplierNo() != null
						&& castOther.getSupplierNo() != null && this
						.getSupplierNo().equals(castOther.getSupplierNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37
				* result
				+ (getSupplierNo() == null ? 0 : this.getSupplierNo()
						.hashCode());
		return result;
	}

}