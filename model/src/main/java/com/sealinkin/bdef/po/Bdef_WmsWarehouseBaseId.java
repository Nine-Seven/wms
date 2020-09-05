package com.sealinkin.bdef.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsWarehouseBaseId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bdef_WmsWarehouseBaseId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String colname;

	// Constructors

	/** default constructor */
	public Bdef_WmsWarehouseBaseId() {
	}

	/** full constructor */
	public Bdef_WmsWarehouseBaseId(String enterpriseNo, String warehouseNo,
			String colname) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.colname = colname;
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

	@Column(name = "COLNAME", nullable = false, length = 50)
	public String getColname() {
		return this.colname;
	}

	public void setColname(String colname) {
		this.colname = colname;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bdef_WmsWarehouseBaseId))
			return false;
		Bdef_WmsWarehouseBaseId castOther = (Bdef_WmsWarehouseBaseId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getColname() == castOther.getColname()) || (this
						.getColname() != null && castOther.getColname() != null && this
						.getColname().equals(castOther.getColname())));
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
				+ (getColname() == null ? 0 : this.getColname().hashCode());
		return result;
	}

}