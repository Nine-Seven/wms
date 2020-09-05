package com.sealinkin.stock.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Stock_LabelDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Stock_LabelDId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String warehouseNo;
	private String containerNo;
	private long rowId;

	// Constructors

	/** default constructor */
	public Stock_LabelDId() {
	}

	/** full constructor */
	public Stock_LabelDId(String warehouseNo, String containerNo, long rowId) {
		this.warehouseNo = warehouseNo;
		this.containerNo = containerNo;
		this.rowId = rowId;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "CONTAINER_NO", nullable = false, length = 24)
	public String getContainerNo() {
		return this.containerNo;
	}

	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}

	@Column(name = "ROW_ID", nullable = false, precision = 10, scale = 0)
	public long getRowId() {
		return this.rowId;
	}

	public void setRowId(long rowId) {
		this.rowId = rowId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Stock_LabelDId))
			return false;
		Stock_LabelDId castOther = (Stock_LabelDId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getContainerNo() == castOther.getContainerNo()) || (this
						.getContainerNo() != null
						&& castOther.getContainerNo() != null && this
						.getContainerNo().equals(castOther.getContainerNo())))
				&& (this.getRowId() == castOther.getRowId());
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37
				* result
				+ (getContainerNo() == null ? 0 : this.getContainerNo()
						.hashCode());
		result = 37 * result + (int) this.getRowId();
		return result;
	}

}