package com.sealinkin.odata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Odata_ExpCancelMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Odata_ExpCancelMId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String cancelNo;

	// Constructors

	/** default constructor */
	public Odata_ExpCancelMId() {
	}

	/** full constructor */
	public Odata_ExpCancelMId(String enterpriseNo, String warehouseNo,
			String cancelNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.cancelNo = cancelNo;
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

	@Column(name = "CANCEL_NO", nullable = false, length = 20)
	public String getCancelNo() {
		return this.cancelNo;
	}

	public void setCancelNo(String cancelNo) {
		this.cancelNo = cancelNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Odata_ExpCancelMId))
			return false;
		Odata_ExpCancelMId castOther = (Odata_ExpCancelMId) other;

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
						.getCancelNo().equals(castOther.getCancelNo())));
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
		return result;
	}

}