package com.sealinkin.odata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OdataDivideMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Odata_DivideMId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String divideNo;
	private String warehouseNo;

	// Constructors

	/** default constructor */
	public Odata_DivideMId() {
	}

	/** full constructor */
	public Odata_DivideMId(String divideNo, String warehouseNo) {
		this.divideNo = divideNo;
		this.warehouseNo = warehouseNo;
	}

	// Property accessors

	@Column(name = "DIVIDE_NO", nullable = false, length = 20)
	public String getDivideNo() {
		return this.divideNo;
	}

	public void setDivideNo(String divideNo) {
		this.divideNo = divideNo;
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
		if (!(other instanceof Odata_DivideMId))
			return false;
		Odata_DivideMId castOther = (Odata_DivideMId) other;

		return ((this.getDivideNo() == castOther.getDivideNo()) || (this
				.getDivideNo() != null && castOther.getDivideNo() != null && this
				.getDivideNo().equals(castOther.getDivideNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getDivideNo() == null ? 0 : this.getDivideNo().hashCode());
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		return result;
	}

}