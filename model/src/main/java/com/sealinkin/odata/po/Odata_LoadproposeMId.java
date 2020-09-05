package com.sealinkin.odata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OdataLoadproposeMId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Odata_LoadproposeMId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String loadproposeNo;
	private String enterpriseNo;

	// Constructors

	/** default constructor */
	public Odata_LoadproposeMId() {
	}

	/** full constructor */
	public Odata_LoadproposeMId(String warehouseNo, String loadproposeNo,
			String enterpriseNo) {
		this.warehouseNo = warehouseNo;
		this.loadproposeNo = loadproposeNo;
		this.enterpriseNo = enterpriseNo;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "LOADPROPOSE_NO", nullable = false, length = 20)
	public String getLoadproposeNo() {
		return this.loadproposeNo;
	}

	public void setLoadproposeNo(String loadproposeNo) {
		this.loadproposeNo = loadproposeNo;
	}

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Odata_LoadproposeMId))
			return false;
		Odata_LoadproposeMId castOther = (Odata_LoadproposeMId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getLoadproposeNo() == castOther.getLoadproposeNo()) || (this
						.getLoadproposeNo() != null
						&& castOther.getLoadproposeNo() != null && this
						.getLoadproposeNo()
						.equals(castOther.getLoadproposeNo())))
				&& ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
						.getEnterpriseNo() != null
						&& castOther.getEnterpriseNo() != null && this
						.getEnterpriseNo().equals(castOther.getEnterpriseNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37
				* result
				+ (getLoadproposeNo() == null ? 0 : this.getLoadproposeNo()
						.hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		return result;
	}

}