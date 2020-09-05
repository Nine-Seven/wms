package com.sealinkin.odata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OdataPackageMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Odata_PackageMId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String warehouseNo;
	private String poNo;

	// Constructors

	/** default constructor */
	public Odata_PackageMId() {
	}

	/** full constructor */
	public Odata_PackageMId(String enterpriseNo, String warehouseNo, String poNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.poNo = poNo;
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

	@Column(name = "PO_NO", nullable = false, length = 20)
	public String getPoNo() {
		return this.poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Odata_PackageMId))
			return false;
		Odata_PackageMId castOther = (Odata_PackageMId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getPoNo() == castOther.getPoNo()) || (this.getPoNo() != null
						&& castOther.getPoNo() != null && this.getPoNo()
						.equals(castOther.getPoNo())));
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
				+ (getPoNo() == null ? 0 : this.getPoNo().hashCode());
		return result;
	}

}
