package com.sealinkin.bdef.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BdefDefprinterId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bdef_DefprinterId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String printerNo;

	// Constructors

	/** default constructor */
	public Bdef_DefprinterId() {
	}

	/** full constructor */
	public Bdef_DefprinterId(String enterpriseNo, String warehouseNo,
			String printerNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.printerNo = printerNo;
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

	@Column(name = "PRINTER_NO", nullable = false, length = 5)
	public String getPrinterNo() {
		return this.printerNo;
	}

	public void setPrinterNo(String printerNo) {
		this.printerNo = printerNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bdef_DefprinterId))
			return false;
		Bdef_DefprinterId castOther = (Bdef_DefprinterId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getPrinterNo() == castOther.getPrinterNo()) || (this
						.getPrinterNo() != null
						&& castOther.getPrinterNo() != null && this
						.getPrinterNo().equals(castOther.getPrinterNo())));
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
				+ (getPrinterNo() == null ? 0 : this.getPrinterNo().hashCode());
		return result;
	}

}