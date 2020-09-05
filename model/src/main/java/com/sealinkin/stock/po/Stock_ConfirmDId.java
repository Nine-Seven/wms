package com.sealinkin.stock.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * StockConfirmDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Stock_ConfirmDId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String confirmNo;
	private Long rowId;

	// Constructors

	/** default constructor */
	public Stock_ConfirmDId() {
	}

	/** full constructor */
	public Stock_ConfirmDId(String enterpriseNo, String warehouseNo,
			String ownerNo, String confirmNo, Long rowId) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.confirmNo = confirmNo;
		this.rowId = rowId;
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

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "CONFIRM_NO", nullable = false, length = 30)
	public String getConfirmNo() {
		return this.confirmNo;
	}

	public void setConfirmNo(String confirmNo) {
		this.confirmNo = confirmNo;
	}

	@Column(name = "ROW_ID", nullable = false, precision = 10, scale = 0)
	public Long getRowId() {
		return this.rowId;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Stock_ConfirmDId))
			return false;
		Stock_ConfirmDId castOther = (Stock_ConfirmDId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getConfirmNo() == castOther.getConfirmNo()) || (this
						.getConfirmNo() != null
						&& castOther.getConfirmNo() != null && this
						.getConfirmNo().equals(castOther.getConfirmNo())))
				&& ((this.getRowId() == castOther.getRowId()) || (this
						.getRowId() != null && castOther.getRowId() != null && this
						.getRowId().equals(castOther.getRowId())));
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
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getConfirmNo() == null ? 0 : this.getConfirmNo().hashCode());
		result = 37 * result
				+ (getRowId() == null ? 0 : this.getRowId().hashCode());
		return result;
	}

}