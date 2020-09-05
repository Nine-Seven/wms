package com.sealinkin.mdata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MdataPlanDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Mdata_PlanDId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String planNo;
	private String warehouseNo;
	private String ownerNo;
	private Long rowId;

	// Constructors

	/** default constructor */
	public Mdata_PlanDId() {
	}

	/** full constructor */
	public Mdata_PlanDId(String planNo, String warehouseNo, String ownerNo,
			Long rowId) {
		this.planNo = planNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.rowId = rowId;
	}

	// Property accessors

	@Column(name = "PLAN_NO", nullable = false, length = 20)
	public String getPlanNo() {
		return this.planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
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
		if (!(other instanceof Mdata_PlanDId))
			return false;
		Mdata_PlanDId castOther = (Mdata_PlanDId) other;

		return ((this.getPlanNo() == castOther.getPlanNo()) || (this
				.getPlanNo() != null && castOther.getPlanNo() != null && this
				.getPlanNo().equals(castOther.getPlanNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getRowId() == castOther.getRowId()) || (this
						.getRowId() != null && castOther.getRowId() != null && this
						.getRowId().equals(castOther.getRowId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPlanNo() == null ? 0 : this.getPlanNo().hashCode());
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getRowId() == null ? 0 : this.getRowId().hashCode());
		return result;
	}

}