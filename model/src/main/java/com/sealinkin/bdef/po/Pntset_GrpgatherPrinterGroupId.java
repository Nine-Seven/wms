package com.sealinkin.bdef.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PntsetGrpgatherPrinterGroupId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Pntset_GrpgatherPrinterGroupId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String grpgatherNo;
	private String printerGroupNo;

	// Constructors

	/** default constructor */
	public Pntset_GrpgatherPrinterGroupId() {
	}

	/** full constructor */
	public Pntset_GrpgatherPrinterGroupId(String enterpriseNo,
			String warehouseNo, String grpgatherNo, String printerGroupNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.grpgatherNo = grpgatherNo;
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

	@Column(name = "GRPGATHER_NO", nullable = false, length = 2)
	public String getGrpgatherNo() {
		return this.grpgatherNo;
	}

	public void setGrpgatherNo(String grpgatherNo) {
		this.grpgatherNo = grpgatherNo;
	}

	@Column(name = "PRINTER_GROUP_NO", nullable = false, length = 20)
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
		if (!(other instanceof Pntset_GrpgatherPrinterGroupId))
			return false;
		Pntset_GrpgatherPrinterGroupId castOther = (Pntset_GrpgatherPrinterGroupId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getGrpgatherNo() == castOther.getGrpgatherNo()) || (this
						.getGrpgatherNo() != null
						&& castOther.getGrpgatherNo() != null && this
						.getGrpgatherNo().equals(castOther.getGrpgatherNo())))
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
				+ (getGrpgatherNo() == null ? 0 : this.getGrpgatherNo()
						.hashCode());
		result = 37
				* result
				+ (getPrinterGroupNo() == null ? 0 : this.getPrinterGroupNo()
						.hashCode());
		return result;
	}

}