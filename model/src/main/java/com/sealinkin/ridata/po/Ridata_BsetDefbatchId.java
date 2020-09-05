package com.sealinkin.ridata.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BsetDefbatchId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Ridata_BsetDefbatchId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String batchNo;
	private Date operateDate;

	// Constructors

	/** default constructor */
	public Ridata_BsetDefbatchId() {
	}

	/** full constructor */
	public Ridata_BsetDefbatchId(String warehouseNo, String batchNo,
			Date operateDate) {
		this.warehouseNo = warehouseNo;
		this.batchNo = batchNo;
		this.operateDate = operateDate;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "BATCH_NO", nullable = false, length = 2)
	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	@Column(name = "OPERATE_DATE", nullable = false, length = 7)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Ridata_BsetDefbatchId))
			return false;
		Ridata_BsetDefbatchId castOther = (Ridata_BsetDefbatchId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getBatchNo() == castOther.getBatchNo()) || (this
						.getBatchNo() != null && castOther.getBatchNo() != null && this
						.getBatchNo().equals(castOther.getBatchNo())))
				&& ((this.getOperateDate() == castOther.getOperateDate()) || (this
						.getOperateDate() != null
						&& castOther.getOperateDate() != null && this
						.getOperateDate().equals(castOther.getOperateDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getBatchNo() == null ? 0 : this.getBatchNo().hashCode());
		result = 37
				* result
				+ (getOperateDate() == null ? 0 : this.getOperateDate()
						.hashCode());
		return result;
	}

}