package com.sealinkin.sodata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SodataWasteDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Sodata_WasteDId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private String enterpriseNo;
	private String warehouseNo;
	private String wasteNo;
	private Short poId;

	// Constructors

	/** default constructor */
	public Sodata_WasteDId() {
	}

	/** full constructor */
	public Sodata_WasteDId(String enterpriseNo, String warehouseNo,
			String wasteNo, Short poId) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.wasteNo = wasteNo;
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
		if (!(other instanceof Sodata_WasteDId))
			return false;
		Sodata_WasteDId castOther = (Sodata_WasteDId) other;

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
						.getWasteNo().equals(castOther.getWasteNo())))
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
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getWasteNo() == null ? 0 : this.getWasteNo().hashCode());
		result = 37 * result
				+ (getPoId() == null ? 0 : this.getPoId().hashCode());
		return result;
	}

}