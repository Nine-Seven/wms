package com.sealinkin.acdata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AcdataInstockMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Acdata_InstockMId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String instockNo;

	// Constructors

	/** default constructor */
	public Acdata_InstockMId() {
	}

	/** full constructor */
	public Acdata_InstockMId(String warehouseNo, String instockNo) {
		this.warehouseNo = warehouseNo;
		this.instockNo = instockNo;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "INSTOCK_NO", nullable = false, length = 20)
	public String getInstockNo() {
		return this.instockNo;
	}

	public void setInstockNo(String instockNo) {
		this.instockNo = instockNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Acdata_InstockMId))
			return false;
		Acdata_InstockMId castOther = (Acdata_InstockMId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getInstockNo() == castOther.getInstockNo()) || (this
						.getInstockNo() != null
						&& castOther.getInstockNo() != null && this
						.getInstockNo().equals(castOther.getInstockNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getInstockNo() == null ? 0 : this.getInstockNo().hashCode());
		return result;
	}

}