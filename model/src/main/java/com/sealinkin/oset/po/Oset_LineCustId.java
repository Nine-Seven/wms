package com.sealinkin.oset.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OsetLineCustId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Oset_LineCustId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String custNo;
	private String lineNo;

	// Constructors

	/** default constructor */
	public Oset_LineCustId() {
	}

	/** full constructor */
	public Oset_LineCustId(String enterpriseNo, String warehouseNo,
			String custNo, String lineNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.custNo = custNo;
		this.lineNo = lineNo;
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

	@Column(name = "CUST_NO", nullable = false, length = 20)
	public String getCustNo() {
		return this.custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	@Column(name = "LINE_NO", nullable = false, length = 4)
	public String getLineNo() {
		return this.lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Oset_LineCustId))
			return false;
		Oset_LineCustId castOther = (Oset_LineCustId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getCustNo() == castOther.getCustNo()) || (this
						.getCustNo() != null && castOther.getCustNo() != null && this
						.getCustNo().equals(castOther.getCustNo())))
				&& ((this.getLineNo() == castOther.getLineNo()) || (this
						.getLineNo() != null && castOther.getLineNo() != null && this
						.getLineNo().equals(castOther.getLineNo())));
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
				+ (getCustNo() == null ? 0 : this.getCustNo().hashCode());
		result = 37 * result
				+ (getLineNo() == null ? 0 : this.getLineNo().hashCode());
		return result;
	}

}