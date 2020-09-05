package com.sealinkin.bdef.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DeviceDivideGroupId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Device_Divide_GroupId implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String deviceGroupNo;
	private String useType;

	// Constructors

	/** default constructor */
	public Device_Divide_GroupId() {
	}

	/** full constructor */
	public Device_Divide_GroupId(String enterpriseNo, String warehouseNo,
			String deviceGroupNo, String useType) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.deviceGroupNo = deviceGroupNo;
		this.useType = useType;
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

	@Column(name = "DEVICE_GROUP_NO", nullable = false, length = 5)
	public String getDeviceGroupNo() {
		return this.deviceGroupNo;
	}

	public void setDeviceGroupNo(String deviceGroupNo) {
		this.deviceGroupNo = deviceGroupNo;
	}

	@Column(name = "USE_TYPE", nullable = false, length = 1)
	public String getUseType() {
		return this.useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Device_Divide_GroupId))
			return false;
		Device_Divide_GroupId castOther = (Device_Divide_GroupId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getDeviceGroupNo() == castOther.getDeviceGroupNo()) || (this
						.getDeviceGroupNo() != null
						&& castOther.getDeviceGroupNo() != null && this
						.getDeviceGroupNo()
						.equals(castOther.getDeviceGroupNo())))
				&& ((this.getUseType() == castOther.getUseType()) || (this
						.getUseType() != null && castOther.getUseType() != null && this
						.getUseType().equals(castOther.getUseType())));
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
		result = 37
				* result
				+ (getDeviceGroupNo() == null ? 0 : this.getDeviceGroupNo()
						.hashCode());
		result = 37 * result
				+ (getUseType() == null ? 0 : this.getUseType().hashCode());
		return result;
	}

}