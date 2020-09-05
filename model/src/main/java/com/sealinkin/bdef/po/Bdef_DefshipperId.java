package com.sealinkin.bdef.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BdefDefshipperId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bdef_DefshipperId implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String shipperNo;

	// Constructors

	/** default constructor */
	public Bdef_DefshipperId() {
	}

	/** full constructor */
	public Bdef_DefshipperId(String enterpriseNo, String warehouseNo,
			String shipperNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.shipperNo = shipperNo;
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

	@Column(name = "SHIPPER_NO", nullable = false, length = 15)
	public String getShipperNo() {
		return this.shipperNo;
	}

	public void setShipperNo(String shipperNo) {
		this.shipperNo = shipperNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bdef_DefshipperId))
			return false;
		Bdef_DefshipperId castOther = (Bdef_DefshipperId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getShipperNo() == castOther.getShipperNo()) || (this
						.getShipperNo() != null
						&& castOther.getShipperNo() != null && this
						.getShipperNo().equals(castOther.getShipperNo())));
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
				+ (getShipperNo() == null ? 0 : this.getShipperNo().hashCode());
		return result;
	}

}