package com.sealinkin.bdef.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BdefDefdockId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bdef_DefdockId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String dockNo;

	// Constructors

	/** default constructor */
	public Bdef_DefdockId() {
	}

	/** full constructor */
	public Bdef_DefdockId(String enterpriseNo, String warehouseNo, String dockNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.dockNo = dockNo;
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
		if (!(other instanceof Bdef_DefdockId))
			return false;
		Bdef_DefdockId castOther = (Bdef_DefdockId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getDockNo() == castOther.getDockNo()) || (this
						.getDockNo() != null && castOther.getDockNo() != null && this
						.getDockNo().equals(castOther.getDockNo())));
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
				+ (getDockNo() == null ? 0 : this.getDockNo().hashCode());
		return result;
	}

}