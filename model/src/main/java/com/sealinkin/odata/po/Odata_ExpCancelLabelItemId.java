package com.sealinkin.odata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OdataExpCancelLabelItemId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Odata_ExpCancelLabelItemId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String cancelNo;
	private String expNo;
	private String labelNo;

	// Constructors

	/** default constructor */
	public Odata_ExpCancelLabelItemId() {
	}

	/** full constructor */
	public Odata_ExpCancelLabelItemId(String enterpriseNo, String warehouseNo,
			String cancelNo, String expNo, String labelNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.cancelNo = cancelNo;
		this.expNo = expNo;
		this.labelNo = labelNo;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 5)
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

	@Column(name = "CANCEL_NO", nullable = false, length = 3)
	public String getCancelNo() {
		return this.cancelNo;
	}

	public void setCancelNo(String cancelNo) {
		this.cancelNo = cancelNo;
	}

	@Column(name = "EXP_NO", nullable = false, length = 20)
	public String getExpNo() {
		return this.expNo;
	}

	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}

	@Column(name = "LABEL_NO", nullable = false, length = 24)
	public String getLabelNo() {
		return this.labelNo;
	}

	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Odata_ExpCancelLabelItemId))
			return false;
		Odata_ExpCancelLabelItemId castOther = (Odata_ExpCancelLabelItemId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getCancelNo() == castOther.getCancelNo()) || (this
						.getCancelNo() != null
						&& castOther.getCancelNo() != null && this
						.getCancelNo().equals(castOther.getCancelNo())))
				&& ((this.getExpNo() == castOther.getExpNo()) || (this
						.getExpNo() != null && castOther.getExpNo() != null && this
						.getExpNo().equals(castOther.getExpNo())))
				&& ((this.getLabelNo() == castOther.getLabelNo()) || (this
						.getLabelNo() != null && castOther.getLabelNo() != null && this
						.getLabelNo().equals(castOther.getLabelNo())));
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
				+ (getCancelNo() == null ? 0 : this.getCancelNo().hashCode());
		result = 37 * result
				+ (getExpNo() == null ? 0 : this.getExpNo().hashCode());
		result = 37 * result
				+ (getLabelNo() == null ? 0 : this.getLabelNo().hashCode());
		return result;
	}

}