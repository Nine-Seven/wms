package com.sealinkin.idata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * IdataGuardRgisteId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Idata_GuardRgisteId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String orderSerial;
	private String supplierNo;

	// Constructors

	/** default constructor */
	public Idata_GuardRgisteId() {
	}

	/** full constructor */
	public Idata_GuardRgisteId(String enterpriseNo, String warehouseNo,
			String ownerNo, String orderSerial, String supplierNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.orderSerial = orderSerial;
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

	@Column(name = "ORDER_SERIAL", nullable = false, length = 20)
	public String getOrderSerial() {
		return this.orderSerial;
	}

	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
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
		if (!(other instanceof Idata_GuardRgisteId))
			return false;
		Idata_GuardRgisteId castOther = (Idata_GuardRgisteId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getOrderSerial() == castOther.getOrderSerial()) || (this
						.getOrderSerial() != null
						&& castOther.getOrderSerial() != null && this
						.getOrderSerial().equals(castOther.getOrderSerial())))
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
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37
				* result
				+ (getOrderSerial() == null ? 0 : this.getOrderSerial()
						.hashCode());
		result = 37
				* result
				+ (getSupplierNo() == null ? 0 : this.getSupplierNo()
						.hashCode());
		return result;
	}

}