package com.sealinkin.oset.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OsetTaskAllotDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Oset_TaskAllotDId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private short taskId;
	private String outstockType;
	private String operateType;
	private String sourceType;

	// Constructors

	/** default constructor */
	public Oset_TaskAllotDId() {
	}

	/** full constructor */
	public Oset_TaskAllotDId(String warehouseNo, short taskId,
			String outstockType, String operateType, String sourceType) {
		this.warehouseNo = warehouseNo;
		this.taskId = taskId;
		this.outstockType = outstockType;
		this.operateType = operateType;
		this.sourceType = sourceType;
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

	@Column(name = "OUTSTOCK_TYPE", nullable = false, length = 1)
	public String getOutstockType() {
		return this.outstockType;
	}

	public void setOutstockType(String outstockType) {
		this.outstockType = outstockType;
	}

	@Column(name = "OPERATE_TYPE", nullable = false, length = 1)
	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Column(name = "SOURCE_TYPE", nullable = false, length = 2)
	public String getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Oset_TaskAllotDId))
			return false;
		Oset_TaskAllotDId castOther = (Oset_TaskAllotDId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& (this.getTaskId() == castOther.getTaskId())
				&& ((this.getOutstockType() == castOther.getOutstockType()) || (this
						.getOutstockType() != null
						&& castOther.getOutstockType() != null && this
						.getOutstockType().equals(castOther.getOutstockType())))
				&& ((this.getOperateType() == castOther.getOperateType()) || (this
						.getOperateType() != null
						&& castOther.getOperateType() != null && this
						.getOperateType().equals(castOther.getOperateType())))
				&& ((this.getSourceType() == castOther.getSourceType()) || (this
						.getSourceType() != null
						&& castOther.getSourceType() != null && this
						.getSourceType().equals(castOther.getSourceType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result + this.getTaskId();
		result = 37
				* result
				+ (getOutstockType() == null ? 0 : this.getOutstockType()
						.hashCode());
		result = 37
				* result
				+ (getOperateType() == null ? 0 : this.getOperateType()
						.hashCode());
		result = 37
				* result
				+ (getSourceType() == null ? 0 : this.getSourceType()
						.hashCode());
		return result;
	}

}