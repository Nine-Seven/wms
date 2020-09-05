package com.sealinkin.rodata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Rodata_BoxDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Rodata_BoxDId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String labelNo;
	private String rowId;
	private String recedeNo;

	// Constructors

	/** default constructor */
	public Rodata_BoxDId() {
	}

	/** full constructor */
	public Rodata_BoxDId(String enterpriseNo, String warehouseNo,
			String labelNo, String rowId, String recedeNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.labelNo = labelNo;
		this.rowId = rowId;
		this.recedeNo = recedeNo;
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

	@Column(name = "LABEL_NO", nullable = false, length = 24)
	public String getLabelNo() {
		return this.labelNo;
	}

	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}

	@Column(name = "ROW_ID", nullable = false, length = 10)
	public String getRowId() {
		return this.rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	@Column(name = "RECEDE_NO", nullable = false, length = 20)
	public String getRecedeNo() {
		return this.recedeNo;
	}

	public void setRecedeNo(String recedeNo) {
		this.recedeNo = recedeNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Rodata_BoxDId))
			return false;
		Rodata_BoxDId castOther = (Rodata_BoxDId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getLabelNo() == castOther.getLabelNo()) || (this
						.getLabelNo() != null && castOther.getLabelNo() != null && this
						.getLabelNo().equals(castOther.getLabelNo())))
				&& ((this.getRowId() == castOther.getRowId()) || (this
						.getRowId() != null && castOther.getRowId() != null && this
						.getRowId().equals(castOther.getRowId())))
				&& ((this.getRecedeNo() == castOther.getRecedeNo()) || (this
						.getRecedeNo() != null
						&& castOther.getRecedeNo() != null && this
						.getRecedeNo().equals(castOther.getRecedeNo())));
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
				+ (getLabelNo() == null ? 0 : this.getLabelNo().hashCode());
		result = 37 * result
				+ (getRowId() == null ? 0 : this.getRowId().hashCode());
		result = 37 * result
				+ (getRecedeNo() == null ? 0 : this.getRecedeNo().hashCode());
		return result;
	}

}