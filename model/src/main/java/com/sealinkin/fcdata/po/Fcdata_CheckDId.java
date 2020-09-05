package com.sealinkin.fcdata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FcdataCheckDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Fcdata_CheckDId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String checkNo;
	private Integer rowId;

	// Constructors

	/** default constructor */
	public Fcdata_CheckDId() {
	}

	/** full constructor */
	public Fcdata_CheckDId(String enterpriseNo, String warehouseNo,
			String checkNo, Integer rowId) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.checkNo = checkNo;
		this.rowId = rowId;
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

	@Column(name = "CHECK_NO", nullable = false, length = 20)
	public String getCheckNo() {
		return this.checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	@Column(name = "ROW_ID", nullable = false, precision = 6, scale = 0)
	public Integer getRowId() {
		return this.rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Fcdata_CheckDId))
			return false;
		Fcdata_CheckDId castOther = (Fcdata_CheckDId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getCheckNo() == castOther.getCheckNo()) || (this
						.getCheckNo() != null && castOther.getCheckNo() != null && this
						.getCheckNo().equals(castOther.getCheckNo())))
				&& ((this.getRowId() == castOther.getRowId()) || (this
						.getRowId() != null && castOther.getRowId() != null && this
						.getRowId().equals(castOther.getRowId())));
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
				+ (getCheckNo() == null ? 0 : this.getCheckNo().hashCode());
		result = 37 * result
				+ (getRowId() == null ? 0 : this.getRowId().hashCode());
		return result;
	}

}