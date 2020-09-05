package com.sealinkin.odata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OdataOutstockMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Odata_OutstockMId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String outstockNo;

	// Constructors

	/** default constructor */
	public Odata_OutstockMId() {
	}

	/** full constructor */
	public Odata_OutstockMId(String warehouseNo, String outstockNo) {
		this.warehouseNo = warehouseNo;
		this.outstockNo = outstockNo;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "OUTSTOCK_NO", nullable = false, length = 20)
	public String getOutstockNo() {
		return this.outstockNo;
	}

	public void setOutstockNo(String outstockNo) {
		this.outstockNo = outstockNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Odata_OutstockMId))
			return false;
		Odata_OutstockMId castOther = (Odata_OutstockMId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOutstockNo() == castOther.getOutstockNo()) || (this
						.getOutstockNo() != null
						&& castOther.getOutstockNo() != null && this
						.getOutstockNo().equals(castOther.getOutstockNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37
				* result
				+ (getOutstockNo() == null ? 0 : this.getOutstockNo()
						.hashCode());
		return result;
	}

}