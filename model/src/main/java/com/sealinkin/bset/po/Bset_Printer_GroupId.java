package com.sealinkin.bset.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BsetPrinterGroupId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bset_Printer_GroupId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String printerGroupNo;
	private String printerNo;
	private String enterpriseNo;

	// Constructors

	/** default constructor */
	public Bset_Printer_GroupId() {
	}

	/** full constructor */
	public Bset_Printer_GroupId(String warehouseNo, String printerGroupNo,
			String printerNo, String enterpriseNo) {
		this.warehouseNo = warehouseNo;
		this.printerGroupNo = printerGroupNo;
		this.printerNo = printerNo;
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

	@Column(name = "PRINTER_GROUP_NO", nullable = false, length = 5)
	public String getPrinterGroupNo() {
		return this.printerGroupNo;
	}

	public void setPrinterGroupNo(String printerGroupNo) {
		this.printerGroupNo = printerGroupNo;
	}

	@Column(name = "PRINTER_NO", nullable = false, length = 5)
	public String getPrinterNo() {
		return this.printerNo;
	}

	public void setPrinterNo(String printerNo) {
		this.printerNo = printerNo;
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
		if (!(other instanceof Bset_Printer_GroupId))
			return false;
		Bset_Printer_GroupId castOther = (Bset_Printer_GroupId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getPrinterGroupNo() == castOther.getPrinterGroupNo()) || (this
						.getPrinterGroupNo() != null
						&& castOther.getPrinterGroupNo() != null && this
						.getPrinterGroupNo().equals(
								castOther.getPrinterGroupNo())))
				&& ((this.getPrinterNo() == castOther.getPrinterNo()) || (this
						.getPrinterNo() != null
						&& castOther.getPrinterNo() != null && this
						.getPrinterNo().equals(castOther.getPrinterNo())))
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
				+ (getPrinterGroupNo() == null ? 0 : this.getPrinterGroupNo()
						.hashCode());
		result = 37 * result
				+ (getPrinterNo() == null ? 0 : this.getPrinterNo().hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		return result;
	}

}