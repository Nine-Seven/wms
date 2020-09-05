package com.sealinkin.odata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OdataLocateMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Odata_LocateMId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String waveNo;

	// Constructors

	/** default constructor */
	public Odata_LocateMId() {
	}

	/** full constructor */
	public Odata_LocateMId(String enterpriseNo, String warehouseNo, String waveNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.waveNo = waveNo;
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

	@Column(name = "WAVE_NO", nullable = false, length = 20)
	public String getWaveNo() {
		return this.waveNo;
	}

	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Odata_LocateMId))
			return false;
		Odata_LocateMId castOther = (Odata_LocateMId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getWaveNo() == castOther.getWaveNo()) || (this
						.getWaveNo() != null && castOther.getWaveNo() != null && this
						.getWaveNo().equals(castOther.getWaveNo())));
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
				+ (getWaveNo() == null ? 0 : this.getWaveNo().hashCode());
		return result;
	}

}