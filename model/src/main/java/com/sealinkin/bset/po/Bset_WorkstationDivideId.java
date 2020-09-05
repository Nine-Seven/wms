package com.sealinkin.bset.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BsetWorkstationDivideId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bset_WorkstationDivideId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String workstationNo;
	private String deviceGroupNo;

	// Constructors

	/** default constructor */
	public Bset_WorkstationDivideId() {
	}

	/** full constructor */
	public Bset_WorkstationDivideId(String enterpriseNo, String warehouseNo,
			String workstationNo, String deviceGroupNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.workstationNo = workstationNo;
		this.deviceGroupNo = deviceGroupNo;
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

	@Column(name = "WORKSTATION_NO", nullable = false, length = 20)
	public String getWorkstationNo() {
		return this.workstationNo;
	}

	public void setWorkstationNo(String workstationNo) {
		this.workstationNo = workstationNo;
	}

	@Column(name = "DEVICE_GROUP_NO", nullable = false, length = 5)
	public String getDeviceGroupNo() {
		return this.deviceGroupNo;
	}

	public void setDeviceGroupNo(String deviceGroupNo) {
		this.deviceGroupNo = deviceGroupNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bset_WorkstationDivideId))
			return false;
		Bset_WorkstationDivideId castOther = (Bset_WorkstationDivideId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getWorkstationNo() == castOther.getWorkstationNo()) || (this
						.getWorkstationNo() != null
						&& castOther.getWorkstationNo() != null && this
						.getWorkstationNo()
						.equals(castOther.getWorkstationNo())))
				&& ((this.getDeviceGroupNo() == castOther.getDeviceGroupNo()) || (this
						.getDeviceGroupNo() != null
						&& castOther.getDeviceGroupNo() != null && this
						.getDeviceGroupNo()
						.equals(castOther.getDeviceGroupNo())));
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
				+ (getWorkstationNo() == null ? 0 : this.getWorkstationNo()
						.hashCode());
		result = 37
				* result
				+ (getDeviceGroupNo() == null ? 0 : this.getDeviceGroupNo()
						.hashCode());
		return result;
	}

}