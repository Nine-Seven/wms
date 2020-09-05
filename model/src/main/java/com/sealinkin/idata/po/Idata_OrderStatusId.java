package com.sealinkin.idata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Idata_OrderStatusId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Idata_OrderStatusId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String warehouseNo;
	private String orderSerial;
	private String startTime;

	// Constructors

	/** default constructor */
	public Idata_OrderStatusId() {
	}

	/** full constructor */
	public Idata_OrderStatusId(String warehouseNo, String orderSerial,
			String startTime) {
		this.warehouseNo = warehouseNo;
		this.orderSerial = orderSerial;
		this.startTime = startTime;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "ORDER_SERIAL", nullable = false, length = 20)
	public String getOrderSerial() {
		return this.orderSerial;
	}

	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}

	@Column(name = "START_TIME", nullable = false, length = 5)
	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Idata_OrderStatusId))
			return false;
		Idata_OrderStatusId castOther = (Idata_OrderStatusId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOrderSerial() == castOther.getOrderSerial()) || (this
						.getOrderSerial() != null
						&& castOther.getOrderSerial() != null && this
						.getOrderSerial().equals(castOther.getOrderSerial())))
				&& ((this.getStartTime() == castOther.getStartTime()) || (this
						.getStartTime() != null
						&& castOther.getStartTime() != null && this
						.getStartTime().equals(castOther.getStartTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37
				* result
				+ (getOrderSerial() == null ? 0 : this.getOrderSerial()
						.hashCode());
		result = 37 * result
				+ (getStartTime() == null ? 0 : this.getStartTime().hashCode());
		return result;
	}

}