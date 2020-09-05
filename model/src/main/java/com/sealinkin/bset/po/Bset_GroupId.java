package com.sealinkin.bset.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BsetGroupId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bset_GroupId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String printerGroupNo;

	// Constructors

	/** default constructor */
	public Bset_GroupId() {
	}

	/** full constructor */
	public Bset_GroupId(String enterpriseNo, String warehouseNo,
			String printerGroupNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.printerGroupNo = printerGroupNo;
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

	@Column(name = "PRINTER_GROUP_NO", nullable = false, length = 5)
	public String getPrinterGroupNo() {
		return this.printerGroupNo;
	}

	public void setPrinterGroupNo(String printerGroupNo) {
		this.printerGroupNo = printerGroupNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bset_GroupId))
			return false;
		Bset_GroupId castOther = (Bset_GroupId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getPrinterGroupNo() == castOther.getPrinterGroupNo()) || (this
						.getPrinterGroupNo() != null
						&& castOther.getPrinterGroupNo() != null && this
						.getPrinterGroupNo().equals(
								castOther.getPrinterGroupNo())));
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
		result = 37
				* result
				+ (getPrinterGroupNo() == null ? 0 : this.getPrinterGroupNo()
						.hashCode());
		return result;
	}

}