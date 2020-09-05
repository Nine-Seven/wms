package com.sealinkin.stock.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * StockAdjDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class StockAdjDId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String adjNo;
	private Short rowId;
	private String warehouseNo;

	// Constructors

	/** default constructor */
	public StockAdjDId() {
	}

	/** full constructor */
	public StockAdjDId(String enterpriseNo, String adjNo, Short rowId,
			String warehouseNo) {
		this.enterpriseNo = enterpriseNo;
		this.adjNo = adjNo;
		this.rowId = rowId;
		this.warehouseNo = warehouseNo;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "ADJ_NO", nullable = false, length = 20)
	public String getAdjNo() {
		return this.adjNo;
	}

	public void setAdjNo(String adjNo) {
		this.adjNo = adjNo;
	}

	@Column(name = "ROW_ID", nullable = false, precision = 3, scale = 0)
	public Short getRowId() {
		return this.rowId;
	}

	public void setRowId(Short rowId) {
		this.rowId = rowId;
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
		if (!(other instanceof StockAdjDId))
			return false;
		StockAdjDId castOther = (StockAdjDId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getAdjNo() == castOther.getAdjNo()) || (this
						.getAdjNo() != null && castOther.getAdjNo() != null && this
						.getAdjNo().equals(castOther.getAdjNo())))
				&& ((this.getRowId() == castOther.getRowId()) || (this
						.getRowId() != null && castOther.getRowId() != null && this
						.getRowId().equals(castOther.getRowId())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getAdjNo() == null ? 0 : this.getAdjNo().hashCode());
		result = 37 * result
				+ (getRowId() == null ? 0 : this.getRowId().hashCode());
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		return result;
	}

}