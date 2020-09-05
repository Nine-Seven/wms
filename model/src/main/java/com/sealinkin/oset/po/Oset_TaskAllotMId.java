package com.sealinkin.oset.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OsetTaskAllotMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Oset_TaskAllotMId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private short taskId;

	// Constructors

	/** default constructor */
	public Oset_TaskAllotMId() {
	}

	/** full constructor */
	public Oset_TaskAllotMId(String warehouseNo, short taskId) {
		this.warehouseNo = warehouseNo;
		this.taskId = taskId;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "TASK_ID", nullable = false, precision = 3, scale = 0)
	public short getTaskId() {
		return this.taskId;
	}

	public void setTaskId(short taskId) {
		this.taskId = taskId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Oset_TaskAllotMId))
			return false;
		Oset_TaskAllotMId castOther = (Oset_TaskAllotMId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& (this.getTaskId() == castOther.getTaskId());
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result + this.getTaskId();
		return result;
	}

}