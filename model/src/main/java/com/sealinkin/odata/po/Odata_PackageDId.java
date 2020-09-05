package com.sealinkin.odata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OdataPackageDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Odata_PackageDId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String warehouseNo;
	private String poNo;
	private String sourceexpNo;
	private String shipperDeliverNo;

	// Constructors

	/** default constructor */
	public Odata_PackageDId() {
	}

	/** full constructor */
	public Odata_PackageDId(String enterpriseNo, String warehouseNo,
			String poNo, String sourceexpNo, String shipperDeliverNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.poNo = poNo;
		this.sourceexpNo = sourceexpNo;
		this.shipperDeliverNo = shipperDeliverNo;
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

	@Column(name = "SOURCEEXP_NO", nullable = false, length = 30)
	public String getSourceexpNo() {
		return this.sourceexpNo;
	}

	public void setSourceexpNo(String sourceexpNo) {
		this.sourceexpNo = sourceexpNo;
	}

	@Column(name = "SHIPPER_DELIVER_NO", nullable = false, length = 30)
	public String getShipperDeliverNo() {
		return this.shipperDeliverNo;
	}

	public void setShipperDeliverNo(String shipperDeliverNo) {
		this.shipperDeliverNo = shipperDeliverNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Odata_PackageDId))
			return false;
		Odata_PackageDId castOther = (Odata_PackageDId) other;

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
						.equals(castOther.getPoNo())))
				&& ((this.getSourceexpNo() == castOther.getSourceexpNo()) || (this
						.getSourceexpNo() != null
						&& castOther.getSourceexpNo() != null && this
						.getSourceexpNo().equals(castOther.getSourceexpNo())))
				&& ((this.getShipperDeliverNo() == castOther
						.getShipperDeliverNo()) || (this.getShipperDeliverNo() != null
						&& castOther.getShipperDeliverNo() != null && this
						.getShipperDeliverNo().equals(
								castOther.getShipperDeliverNo())));
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
		result = 37
				* result
				+ (getSourceexpNo() == null ? 0 : this.getSourceexpNo()
						.hashCode());
		result = 37
				* result
				+ (getShipperDeliverNo() == null ? 0 : this
						.getShipperDeliverNo().hashCode());
		return result;
	}

}
