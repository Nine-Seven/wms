package com.sealinkin.odata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OdataDivideDirectId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Odata_DivideDirectId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sourceNo;
	private String warehouseNo;
	private String ownerNo;
	private Long divideId;

	// Constructors

	/** default constructor */
	public Odata_DivideDirectId() {
	}

	/** full constructor */
	public Odata_DivideDirectId(String sourceNo, String warehouseNo,
			String ownerNo, Long divideId) {
		this.sourceNo = sourceNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.divideId = divideId;
	}

	// Property accessors

	@Column(name = "SOURCE_NO", nullable = false, length = 20)
	public String getSourceNo() {
		return this.sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "DIVIDE_ID", nullable = false, precision = 10, scale = 0)
	public Long getDivideId() {
		return this.divideId;
	}

	public void setDivideId(Long divideId) {
		this.divideId = divideId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Odata_DivideDirectId))
			return false;
		Odata_DivideDirectId castOther = (Odata_DivideDirectId) other;

		return ((this.getSourceNo() == castOther.getSourceNo()) || (this
				.getSourceNo() != null && castOther.getSourceNo() != null && this
				.getSourceNo().equals(castOther.getSourceNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getDivideId() == castOther.getDivideId()) || (this
						.getDivideId() != null
						&& castOther.getDivideId() != null && this
						.getDivideId().equals(castOther.getDivideId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSourceNo() == null ? 0 : this.getSourceNo().hashCode());
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getDivideId() == null ? 0 : this.getDivideId().hashCode());
		return result;
	}

}