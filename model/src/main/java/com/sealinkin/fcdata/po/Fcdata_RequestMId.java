package com.sealinkin.fcdata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Fcdata_RequestMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Fcdata_RequestMId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String requestNo;

	// Constructors

	/** default constructor */
	public Fcdata_RequestMId() {
	}

	/** full constructor */
	public Fcdata_RequestMId(String warehouseNo, String requestNo) {
		this.warehouseNo = warehouseNo;
		this.requestNo = requestNo;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "REQUEST_NO", nullable = false, length = 20)
	public String getRequestNo() {
		return this.requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Fcdata_RequestMId))
			return false;
		Fcdata_RequestMId castOther = (Fcdata_RequestMId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getRequestNo() == castOther.getRequestNo()) || (this
						.getRequestNo() != null
						&& castOther.getRequestNo() != null && this
						.getRequestNo().equals(castOther.getRequestNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getRequestNo() == null ? 0 : this.getRequestNo().hashCode());
		return result;
	}

}