package com.sealinkin.odata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Odata_LocateDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Odata_LocateDId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String waveNo;
	private String warehouseNo;
	private long rowId;
	private String expNo;

	// Constructors

	/** default constructor */
	public Odata_LocateDId() {
	}

	/** full constructor */
	public Odata_LocateDId(String waveNo, String warehouseNo, long rowId,
			String expNo) {
		this.waveNo = waveNo;
		this.warehouseNo = warehouseNo;
		this.rowId = rowId;
		this.expNo = expNo;
	}

	// Property accessors

	@Column(name = "WAVE_NO", nullable = false, length = 20)
	public String getWaveNo() {
		return this.waveNo;
	}

	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
	}

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "ROW_ID", nullable = false, precision = 10, scale = 0)
	public long getRowId() {
		return this.rowId;
	}

	public void setRowId(long rowId) {
		this.rowId = rowId;
	}

	@Column(name = "EXP_NO", nullable = false, length = 20)
	public String getExpNo() {
		return this.expNo;
	}

	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Odata_LocateDId))
			return false;
		Odata_LocateDId castOther = (Odata_LocateDId) other;

		return ((this.getWaveNo() == castOther.getWaveNo()) || (this
				.getWaveNo() != null && castOther.getWaveNo() != null && this
				.getWaveNo().equals(castOther.getWaveNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& (this.getRowId() == castOther.getRowId())
				&& ((this.getExpNo() == castOther.getExpNo()) || (this
						.getExpNo() != null && castOther.getExpNo() != null && this
						.getExpNo().equals(castOther.getExpNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getWaveNo() == null ? 0 : this.getWaveNo().hashCode());
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result + (int) this.getRowId();
		result = 37 * result
				+ (getExpNo() == null ? 0 : this.getExpNo().hashCode());
		return result;
	}

}