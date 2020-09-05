package com.sealinkin.cset.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CsetAreaBackupDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Cset_AreaBackupDId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private Byte lineId;
	private Integer ALevel;
	private String wareNo;
	private String areaNo;
	private String stockNo;

	// Constructors

	/** default constructor */
	public Cset_AreaBackupDId() {
	}

	/** full constructor */
	public Cset_AreaBackupDId(String enterpriseNo, String warehouseNo,
			Byte lineId, Integer ALevel, String wareNo, String areaNo,
			String stockNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.lineId = lineId;
		this.ALevel = ALevel;
		this.wareNo = wareNo;
		this.areaNo = areaNo;
		this.stockNo = stockNo;
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
	public Byte getLineId() {
		return this.lineId;
	}

	public void setLineId(Byte lineId) {
		this.lineId = lineId;
	}

	@Column(name = "A_LEVEL", nullable = false, precision = 1, scale = 0)
	public Integer getALevel() {
		return this.ALevel;
	}

	public void setALevel(int ALevel) {
		this.ALevel = ALevel;
	}

	@Column(name = "WARE_NO", nullable = false, length = 5)
	public String getWareNo() {
		return this.wareNo;
	}

	public void setWareNo(String wareNo) {
		this.wareNo = wareNo;
	}

	@Column(name = "AREA_NO", nullable = false, length = 5)
	public String getAreaNo() {
		return this.areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	@Column(name = "STOCK_NO", nullable = false, length = 5)
	public String getStockNo() {
		return this.stockNo;
	}

	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Cset_AreaBackupDId))
			return false;
		Cset_AreaBackupDId castOther = (Cset_AreaBackupDId) other;

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
						.getLineId().equals(castOther.getLineId())))
				&& ((this.getALevel() == castOther.getALevel()) || (this
						.getALevel() != null && castOther.getALevel() != null && this
						.getALevel().equals(castOther.getALevel())))
				&& ((this.getWareNo() == castOther.getWareNo()) || (this
						.getWareNo() != null && castOther.getWareNo() != null && this
						.getWareNo().equals(castOther.getWareNo())))
				&& ((this.getAreaNo() == castOther.getAreaNo()) || (this
						.getAreaNo() != null && castOther.getAreaNo() != null && this
						.getAreaNo().equals(castOther.getAreaNo())))
				&& ((this.getStockNo() == castOther.getStockNo()) || (this
						.getStockNo() != null && castOther.getStockNo() != null && this
						.getStockNo().equals(castOther.getStockNo())));
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
		result = 37 * result
				+ (getALevel() == null ? 0 : this.getALevel().hashCode());
		result = 37 * result
				+ (getWareNo() == null ? 0 : this.getWareNo().hashCode());
		result = 37 * result
				+ (getAreaNo() == null ? 0 : this.getAreaNo().hashCode());
		result = 37 * result
				+ (getStockNo() == null ? 0 : this.getStockNo().hashCode());
		return result;
	}

}