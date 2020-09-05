package com.sealinkin.bdef.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DeviceDivideDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Device_DivideDId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String deviceNo;
	private String deviceCellNo;

	// Constructors

	/** default constructor */
	public Device_DivideDId() {
	}

	/** full constructor */
	public Device_DivideDId(String enterpriseNo, String warehouseNo,
			String deviceNo, String deviceCellNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.deviceNo = deviceNo;
		this.deviceCellNo = deviceCellNo;
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

	@Column(name = "DEVICE_NO", nullable = false, length = 20)
	public String getDeviceNo() {
		return this.deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	@Column(name = "DEVICE_CELL_NO", nullable = false, length = 24)
	public String getDeviceCellNo() {
		return this.deviceCellNo;
	}

	public void setDeviceCellNo(String deviceCellNo) {
		this.deviceCellNo = deviceCellNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Device_DivideDId))
			return false;
		Device_DivideDId castOther = (Device_DivideDId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getDeviceNo() == castOther.getDeviceNo()) || (this
						.getDeviceNo() != null
						&& castOther.getDeviceNo() != null && this
						.getDeviceNo().equals(castOther.getDeviceNo())))
				&& ((this.getDeviceCellNo() == castOther.getDeviceCellNo()) || (this
						.getDeviceCellNo() != null
						&& castOther.getDeviceCellNo() != null && this
						.getDeviceCellNo().equals(castOther.getDeviceCellNo())));
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
				+ (getDeviceNo() == null ? 0 : this.getDeviceNo().hashCode());
		result = 37
				* result
				+ (getDeviceCellNo() == null ? 0 : this.getDeviceCellNo()
						.hashCode());
		return result;
	}

}