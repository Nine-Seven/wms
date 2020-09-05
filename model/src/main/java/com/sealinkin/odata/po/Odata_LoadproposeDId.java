package com.sealinkin.odata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Odata_LoadproposeDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Odata_LoadproposeDId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String loadproposeNo;
	private String warehouseNo;
	private String containerNo;

	// Constructors

	/** default constructor */
	public Odata_LoadproposeDId() {
	}

	/** full constructor */
	public Odata_LoadproposeDId(String loadproposeNo, String warehouseNo,
			String containerNo) {
		this.loadproposeNo = loadproposeNo;
		this.warehouseNo = warehouseNo;
		this.containerNo = containerNo;
	}

	// Property accessors

	@Column(name = "LOADPROPOSE_NO", nullable = false, length = 20)
	public String getLoadproposeNo() {
		return this.loadproposeNo;
	}

	public void setLoadproposeNo(String loadproposeNo) {
		this.loadproposeNo = loadproposeNo;
	}

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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Odata_LoadproposeDId))
			return false;
		Odata_LoadproposeDId castOther = (Odata_LoadproposeDId) other;

		return ((this.getLoadproposeNo() == castOther.getLoadproposeNo()) || (this
				.getLoadproposeNo() != null
				&& castOther.getLoadproposeNo() != null && this
				.getLoadproposeNo().equals(castOther.getLoadproposeNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getContainerNo() == castOther.getContainerNo()) || (this
						.getContainerNo() != null
						&& castOther.getContainerNo() != null && this
						.getContainerNo().equals(castOther.getContainerNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getLoadproposeNo() == null ? 0 : this.getLoadproposeNo()
						.hashCode());
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37
				* result
				+ (getContainerNo() == null ? 0 : this.getContainerNo()
						.hashCode());
		return result;
	}

}