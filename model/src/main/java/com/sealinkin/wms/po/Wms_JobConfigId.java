package com.sealinkin.wms.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsJobConfigId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Wms_JobConfigId implements java.io.Serializable {

	// Fields

	private String warehouseNo;
	private String enterpriseNo;
	private String procName;

	// Constructors

	/** default constructor */
	public Wms_JobConfigId() {
	}

	/** full constructor */
	public Wms_JobConfigId(String warehouseNo, String enterpriseNo,
			String procName) {
		this.warehouseNo = warehouseNo;
		this.enterpriseNo = enterpriseNo;
		this.procName = procName;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 20)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "PROC_NAME", nullable = false, length = 30)
	public String getProcName() {
		return this.procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Wms_JobConfigId))
			return false;
		Wms_JobConfigId castOther = (Wms_JobConfigId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
						.getEnterpriseNo() != null
						&& castOther.getEnterpriseNo() != null && this
						.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getProcName() == castOther.getProcName()) || (this
						.getProcName() != null
						&& castOther.getProcName() != null && this
						.getProcName().equals(castOther.getProcName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getProcName() == null ? 0 : this.getProcName().hashCode());
		return result;
	}

}