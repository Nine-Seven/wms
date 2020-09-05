package com.sealinkin.ridata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RidataUntreadMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Ridata_UntreadMId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String untreadNo;
	private String warehouseNo;

	// Constructors

	/** default constructor */
	public Ridata_UntreadMId() {
	}

	/** full constructor */
	public Ridata_UntreadMId(String enterpriseNo, String untreadNo,
			String warehouseNo) {
		this.enterpriseNo = enterpriseNo;
		this.untreadNo = untreadNo;
		this.warehouseNo = warehouseNo;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "UNTREAD_NO", nullable = false, length = 20)
	public String getUntreadNo() {
		return this.untreadNo;
	}

	public void setUntreadNo(String untreadNo) {
		this.untreadNo = untreadNo;
	}

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Ridata_UntreadMId))
			return false;
		Ridata_UntreadMId castOther = (Ridata_UntreadMId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getUntreadNo() == castOther.getUntreadNo()) || (this
						.getUntreadNo() != null
						&& castOther.getUntreadNo() != null && this
						.getUntreadNo().equals(castOther.getUntreadNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getUntreadNo() == null ? 0 : this.getUntreadNo().hashCode());
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		return result;
	}

}