package com.sealinkin.bdef.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PntdefPrinterGrpgatherId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Pntdef_PrinterGrpgatherId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String enterpriseNo;
	private String grpgatherNo;

	// Constructors

	/** default constructor */
	public Pntdef_PrinterGrpgatherId() {
	}

	/** full constructor */
	public Pntdef_PrinterGrpgatherId(String warehouseNo, String enterpriseNo,
			String grpgatherNo) {
		this.warehouseNo = warehouseNo;
		this.enterpriseNo = enterpriseNo;
		this.grpgatherNo = grpgatherNo;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "GRPGATHER_NO", nullable = false, length = 2)
	public String getGrpgatherNo() {
		return this.grpgatherNo;
	}

	public void setGrpgatherNo(String grpgatherNo) {
		this.grpgatherNo = grpgatherNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Pntdef_PrinterGrpgatherId))
			return false;
		Pntdef_PrinterGrpgatherId castOther = (Pntdef_PrinterGrpgatherId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
						.getEnterpriseNo() != null
						&& castOther.getEnterpriseNo() != null && this
						.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getGrpgatherNo() == castOther.getGrpgatherNo()) || (this
						.getGrpgatherNo() != null
						&& castOther.getGrpgatherNo() != null && this
						.getGrpgatherNo().equals(castOther.getGrpgatherNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37
				* result
				+ (getGrpgatherNo() == null ? 0 : this.getGrpgatherNo()
						.hashCode());
		return result;
	}

}