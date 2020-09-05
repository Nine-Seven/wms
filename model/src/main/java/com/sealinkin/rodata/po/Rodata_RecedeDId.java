package com.sealinkin.rodata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RodataRecedeDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Rodata_RecedeDId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String recedeNo;
	private String warehouseNo;
	private String ownerNo;
	private Short poId;

	// Constructors

	/** default constructor */
	public Rodata_RecedeDId() {
	}

	/** full constructor */
	public Rodata_RecedeDId(String enterpriseNo, String recedeNo,
			String warehouseNo, String ownerNo, Short poId) {
		this.enterpriseNo = enterpriseNo;
		this.recedeNo = recedeNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.poId = poId;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "RECEDE_NO", nullable = false, length = 20)
	public String getRecedeNo() {
		return this.recedeNo;
	}

	public void setRecedeNo(String recedeNo) {
		this.recedeNo = recedeNo;
	}

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "PO_ID", nullable = false, precision = 4, scale = 0)
	public Short getPoId() {
		return this.poId;
	}

	public void setPoId(Short poId) {
		this.poId = poId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Rodata_RecedeDId))
			return false;
		Rodata_RecedeDId castOther = (Rodata_RecedeDId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getRecedeNo() == castOther.getRecedeNo()) || (this
						.getRecedeNo() != null
						&& castOther.getRecedeNo() != null && this
						.getRecedeNo().equals(castOther.getRecedeNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getPoId() == castOther.getPoId()) || (this.getPoId() != null
						&& castOther.getPoId() != null && this.getPoId()
						.equals(castOther.getPoId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getRecedeNo() == null ? 0 : this.getRecedeNo().hashCode());
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getPoId() == null ? 0 : this.getPoId().hashCode());
		return result;
	}

}