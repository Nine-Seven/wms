package com.sealinkin.sodata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SodataOutstockMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Sodata_OutstockMId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String outstockNo;
	private String warehouseNo;
	private String ownerNo;

	// Constructors

	/** default constructor */
	public Sodata_OutstockMId() {
	}

	/** full constructor */
	public Sodata_OutstockMId(String enterpriseNo, String outstockNo,
			String warehouseNo, String ownerNo) {
		this.enterpriseNo = enterpriseNo;
		this.outstockNo = outstockNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "OUTSTOCK_NO", nullable = false, length = 20)
	public String getOutstockNo() {
		return this.outstockNo;
	}

	public void setOutstockNo(String outstockNo) {
		this.outstockNo = outstockNo;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Sodata_OutstockMId))
			return false;
		Sodata_OutstockMId castOther = (Sodata_OutstockMId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getOutstockNo() == castOther.getOutstockNo()) || (this
						.getOutstockNo() != null
						&& castOther.getOutstockNo() != null && this
						.getOutstockNo().equals(castOther.getOutstockNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37
				* result
				+ (getOutstockNo() == null ? 0 : this.getOutstockNo()
						.hashCode());
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		return result;
	}

}