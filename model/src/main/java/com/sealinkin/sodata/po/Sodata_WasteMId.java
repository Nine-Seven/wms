package com.sealinkin.sodata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SodataWasteMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Sodata_WasteMId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String wasteNo;

	// Constructors

	/** default constructor */
	public Sodata_WasteMId() {
	}

	/** full constructor */
	public Sodata_WasteMId(String enterpriseNo, String warehouseNo,
			String wasteNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.wasteNo = wasteNo;
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

	@Column(name = "WASTE_NO", nullable = false, length = 20)
	public String getWasteNo() {
		return this.wasteNo;
	}

	public void setWasteNo(String wasteNo) {
		this.wasteNo = wasteNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Sodata_WasteMId))
			return false;
		Sodata_WasteMId castOther = (Sodata_WasteMId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getWasteNo() == castOther.getWasteNo()) || (this
						.getWasteNo() != null && castOther.getWasteNo() != null && this
						.getWasteNo().equals(castOther.getWasteNo())));
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
				+ (getWasteNo() == null ? 0 : this.getWasteNo().hashCode());
		return result;
	}

}