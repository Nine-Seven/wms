package com.sealinkin.stock.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Stock_ContentId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Stock_ContentId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String warehouseNo;
	private String cellNo;
	private long cellId;

	// Constructors

	/** default constructor */
	public Stock_ContentId() {
	}

	/** full constructor */
	public Stock_ContentId(String warehouseNo, String cellNo, long cellId) {
		this.warehouseNo = warehouseNo;
		this.cellNo = cellNo;
		this.cellId = cellId;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "CELL_NO", nullable = false, length = 24)
	public String getCellNo() {
		return this.cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	@Column(name = "CELL_ID", nullable = false, precision = 15, scale = 0)
	public long getCellId() {
		return this.cellId;
	}

	public void setCellId(long cellId) {
		this.cellId = cellId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Stock_ContentId))
			return false;
		Stock_ContentId castOther = (Stock_ContentId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getCellNo() == castOther.getCellNo()) || (this
						.getCellNo() != null && castOther.getCellNo() != null && this
						.getCellNo().equals(castOther.getCellNo())))
				&& (this.getCellId() == castOther.getCellId());
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getCellNo() == null ? 0 : this.getCellNo().hashCode());
		result = 37 * result + (int) this.getCellId();
		return result;
	}

}