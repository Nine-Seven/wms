package com.sealinkin.rodata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RodataOutstockDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Rodata_OutstockDId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String outstockNo;
	private String warehouseNo;
	private String ownerNo;
	private String sourceNo;
	private Integer divideId;
	private String SCellNo;
	private Long SCellId;
	private String outstockCellNo;

	// Constructors

	/** default constructor */
	public Rodata_OutstockDId() {
	}

	/** full constructor */
	public Rodata_OutstockDId(String enterpriseNo, String outstockNo,
			String warehouseNo, String ownerNo, String sourceNo,
			Integer divideId, String SCellNo, Long SCellId,
			String outstockCellNo) {
		this.enterpriseNo = enterpriseNo;
		this.outstockNo = outstockNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.sourceNo = sourceNo;
		this.divideId = divideId;
		this.SCellNo = SCellNo;
		this.SCellId = SCellId;
		this.outstockCellNo = outstockCellNo;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "OUTSTOCK_NO", nullable = false, length = 20)
	public String getOutstockNo() {
		return this.outstockNo;
	}

	public void setOutstockNo(String outstockNo) {
		this.outstockNo = outstockNo;
	}

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "SOURCE_NO", nullable = false, length = 20)
	public String getSourceNo() {
		return this.sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	@Column(name = "DIVIDE_ID", nullable = false, precision = 8, scale = 0)
	public Integer getDivideId() {
		return this.divideId;
	}

	public void setDivideId(Integer divideId) {
		this.divideId = divideId;
	}

	@Column(name = "S_CELL_NO", nullable = false, length = 24)
	public String getSCellNo() {
		return this.SCellNo;
	}

	public void setSCellNo(String SCellNo) {
		this.SCellNo = SCellNo;
	}

	@Column(name = "S_CELL_ID", nullable = false, precision = 10, scale = 0)
	public Long getSCellId() {
		return this.SCellId;
	}

	public void setSCellId(Long SCellId) {
		this.SCellId = SCellId;
	}

	@Column(name = "OUTSTOCK_CELL_NO", nullable = false, length = 24)
	public String getOutstockCellNo() {
		return this.outstockCellNo;
	}

	public void setOutstockCellNo(String outstockCellNo) {
		this.outstockCellNo = outstockCellNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Rodata_OutstockDId))
			return false;
		Rodata_OutstockDId castOther = (Rodata_OutstockDId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getOutstockNo() == castOther.getOutstockNo()) || (this
						.getOutstockNo() != null
						&& castOther.getOutstockNo() != null && this
						.getOutstockNo().equals(castOther.getOutstockNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getSourceNo() == castOther.getSourceNo()) || (this
						.getSourceNo() != null
						&& castOther.getSourceNo() != null && this
						.getSourceNo().equals(castOther.getSourceNo())))
				&& ((this.getDivideId() == castOther.getDivideId()) || (this
						.getDivideId() != null
						&& castOther.getDivideId() != null && this
						.getDivideId().equals(castOther.getDivideId())))
				&& ((this.getSCellNo() == castOther.getSCellNo()) || (this
						.getSCellNo() != null && castOther.getSCellNo() != null && this
						.getSCellNo().equals(castOther.getSCellNo())))
				&& ((this.getSCellId() == castOther.getSCellId()) || (this
						.getSCellId() != null && castOther.getSCellId() != null && this
						.getSCellId().equals(castOther.getSCellId())))
				&& ((this.getOutstockCellNo() == castOther.getOutstockCellNo()) || (this
						.getOutstockCellNo() != null
						&& castOther.getOutstockCellNo() != null && this
						.getOutstockCellNo().equals(
								castOther.getOutstockCellNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37
				* result
				+ (getOutstockNo() == null ? 0 : this.getOutstockNo()
						.hashCode());
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getSourceNo() == null ? 0 : this.getSourceNo().hashCode());
		result = 37 * result
				+ (getDivideId() == null ? 0 : this.getDivideId().hashCode());
		result = 37 * result
				+ (getSCellNo() == null ? 0 : this.getSCellNo().hashCode());
		result = 37 * result
				+ (getSCellId() == null ? 0 : this.getSCellId().hashCode());
		result = 37
				* result
				+ (getOutstockCellNo() == null ? 0 : this.getOutstockCellNo()
						.hashCode());
		return result;
	}

}