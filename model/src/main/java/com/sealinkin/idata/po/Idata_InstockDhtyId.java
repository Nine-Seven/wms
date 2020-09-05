package com.sealinkin.idata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * IdataInstockDhtyId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Idata_InstockDhtyId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String instockNo;
	private String warehouseNo;
	private String ownerNo;
	private String instockId;

	// Constructors

	/** default constructor */
	public Idata_InstockDhtyId() {
	}

	/** full constructor */
	public Idata_InstockDhtyId(String instockNo, String warehouseNo,
			String ownerNo, String instockId) {
		this.instockNo = instockNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.instockId = instockId;
	}

	// Property accessors

	@Column(name = "INSTOCK_NO", nullable = false, length = 20)
	public String getInstockNo() {
		return this.instockNo;
	}

	public void setInstockNo(String instockNo) {
		this.instockNo = instockNo;
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

	@Column(name = "INSTOCK_ID", nullable = false, length = 20)
	public String getInstockId() {
		return this.instockId;
	}

	public void setInstockId(String instockId) {
		this.instockId = instockId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Idata_InstockDhtyId))
			return false;
		Idata_InstockDhtyId castOther = (Idata_InstockDhtyId) other;

		return ((this.getInstockNo() == castOther.getInstockNo()) || (this
				.getInstockNo() != null && castOther.getInstockNo() != null && this
				.getInstockNo().equals(castOther.getInstockNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getInstockId() == castOther.getInstockId()) || (this
						.getInstockId() != null
						&& castOther.getInstockId() != null && this
						.getInstockId().equals(castOther.getInstockId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getInstockNo() == null ? 0 : this.getInstockNo().hashCode());
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getInstockId() == null ? 0 : this.getInstockId().hashCode());
		return result;
	}

}