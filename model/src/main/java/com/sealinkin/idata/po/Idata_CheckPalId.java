package com.sealinkin.idata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Idata_CheckPalId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Idata_CheckPalId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String warehouseNo;
	private String ownerNo;
	private String SCheckNo;
	private String checkNo;
	private short checkRowId;
	private String containerNo;
	private String labelNo;
	private String subLabelNo;

	// Constructors

	/** default constructor */
	public Idata_CheckPalId() {
	}

	/** full constructor */
	public Idata_CheckPalId(String warehouseNo, String ownerNo, String SCheckNo,
			String checkNo, short checkRowId, String containerNo,
			String labelNo, String subLabelNo) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.SCheckNo = SCheckNo;
		this.checkNo = checkNo;
		this.checkRowId = checkRowId;
		this.containerNo = containerNo;
		this.labelNo = labelNo;
		this.subLabelNo = subLabelNo;
	}

	// Property accessors

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

	@Column(name = "S_CHECK_NO", nullable = false, length = 20)
	public String getSCheckNo() {
		return this.SCheckNo;
	}

	public void setSCheckNo(String SCheckNo) {
		this.SCheckNo = SCheckNo;
	}

	@Column(name = "CHECK_NO", nullable = false, length = 20)
	public String getCheckNo() {
		return this.checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	@Column(name = "CHECK_ROW_ID", nullable = false, precision = 4, scale = 0)
	public short getCheckRowId() {
		return this.checkRowId;
	}

	public void setCheckRowId(short checkRowId) {
		this.checkRowId = checkRowId;
	}

	@Column(name = "CONTAINER_NO", nullable = false, length = 24)
	public String getContainerNo() {
		return this.containerNo;
	}

	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}

	@Column(name = "LABEL_NO", nullable = false, length = 24)
	public String getLabelNo() {
		return this.labelNo;
	}

	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}

	@Column(name = "SUB_LABEL_NO", nullable = false, length = 24)
	public String getSubLabelNo() {
		return this.subLabelNo;
	}

	public void setSubLabelNo(String subLabelNo) {
		this.subLabelNo = subLabelNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Idata_CheckPalId))
			return false;
		Idata_CheckPalId castOther = (Idata_CheckPalId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getSCheckNo() == castOther.getSCheckNo()) || (this
						.getSCheckNo() != null
						&& castOther.getSCheckNo() != null && this
						.getSCheckNo().equals(castOther.getSCheckNo())))
				&& ((this.getCheckNo() == castOther.getCheckNo()) || (this
						.getCheckNo() != null && castOther.getCheckNo() != null && this
						.getCheckNo().equals(castOther.getCheckNo())))
				&& (this.getCheckRowId() == castOther.getCheckRowId())
				&& ((this.getContainerNo() == castOther.getContainerNo()) || (this
						.getContainerNo() != null
						&& castOther.getContainerNo() != null && this
						.getContainerNo().equals(castOther.getContainerNo())))
				&& ((this.getLabelNo() == castOther.getLabelNo()) || (this
						.getLabelNo() != null && castOther.getLabelNo() != null && this
						.getLabelNo().equals(castOther.getLabelNo())))
				&& ((this.getSubLabelNo() == castOther.getSubLabelNo()) || (this
						.getSubLabelNo() != null
						&& castOther.getSubLabelNo() != null && this
						.getSubLabelNo().equals(castOther.getSubLabelNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getSCheckNo() == null ? 0 : this.getSCheckNo().hashCode());
		result = 37 * result
				+ (getCheckNo() == null ? 0 : this.getCheckNo().hashCode());
		result = 37 * result + this.getCheckRowId();
		result = 37
				* result
				+ (getContainerNo() == null ? 0 : this.getContainerNo()
						.hashCode());
		result = 37 * result
				+ (getLabelNo() == null ? 0 : this.getLabelNo().hashCode());
		result = 37
				* result
				+ (getSubLabelNo() == null ? 0 : this.getSubLabelNo()
						.hashCode());
		return result;
	}

}