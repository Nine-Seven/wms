package com.sealinkin.cset.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CsetAreaBackupMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Cset_AreaBackupMId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private Integer lineId;

	// Constructors

	/** default constructor */
	public Cset_AreaBackupMId() {
	}

	/** full constructor */
	public Cset_AreaBackupMId(String enterpriseNo, String warehouseNo,
			Integer lineId) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.lineId = lineId;
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

	@Column(name = "LINE_ID", nullable = false, precision = 2, scale = 0)
	public Integer getLineId() {
		return this.lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Cset_AreaBackupMId))
			return false;
		Cset_AreaBackupMId castOther = (Cset_AreaBackupMId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getLineId() == castOther.getLineId()) || (this
						.getLineId() != null && castOther.getLineId() != null && this
						.getLineId().equals(castOther.getLineId())));
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
				+ (getLineId() == null ? 0 : this.getLineId().hashCode());
		return result;
	}

}