package com.sealinkin.mdata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MdataPlanMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Mdata_PlanMId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String planNo;

	// Constructors

	/** default constructor */
	public Mdata_PlanMId() {
	}

	/** full constructor */
	public Mdata_PlanMId(String warehouseNo, String planNo) {
		this.warehouseNo = warehouseNo;
		this.planNo = planNo;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "PLAN_NO", nullable = false, length = 20)
	public String getPlanNo() {
		return this.planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Mdata_PlanMId))
			return false;
		Mdata_PlanMId castOther = (Mdata_PlanMId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getPlanNo() == castOther.getPlanNo()) || (this
						.getPlanNo() != null && castOther.getPlanNo() != null && this
						.getPlanNo().equals(castOther.getPlanNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getPlanNo() == null ? 0 : this.getPlanNo().hashCode());
		return result;
	}

}