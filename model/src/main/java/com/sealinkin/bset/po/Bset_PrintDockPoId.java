package com.sealinkin.bset.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Bset_PrintDockPoId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bset_PrintDockPoId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String dockNo;

	// Constructors

	/** default constructor */
	public Bset_PrintDockPoId() {
	}

	/** full constructor */
	public Bset_PrintDockPoId(String warehouseNo, String dockNo) {
		this.warehouseNo = warehouseNo;
		this.dockNo = dockNo;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "DOCK_NO", nullable = false, length = 3)
	public String getDockNo() {
		return this.dockNo;
	}

	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bset_PrintDockPoId))
			return false;
		Bset_PrintDockPoId castOther = (Bset_PrintDockPoId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getDockNo() == castOther.getDockNo()) || (this
						.getDockNo() != null && castOther.getDockNo() != null && this
						.getDockNo().equals(castOther.getDockNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getDockNo() == null ? 0 : this.getDockNo().hashCode());
		return result;
	}

}