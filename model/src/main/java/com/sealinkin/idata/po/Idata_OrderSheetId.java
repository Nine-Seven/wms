package com.sealinkin.idata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Idata_OrderSheetId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Idata_OrderSheetId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String warehouseNo;
	private String orderSerial;
	private String importNo;
	private String poType;
	private String cancelStatus;

	// Constructors

	/** default constructor */
	public Idata_OrderSheetId() {
	}

	/** full constructor */
	public Idata_OrderSheetId(String warehouseNo, String orderSerial,
			String importNo, String poType, String cancelStatus) {
		this.warehouseNo = warehouseNo;
		this.orderSerial = orderSerial;
		this.importNo = importNo;
		this.poType = poType;
		this.cancelStatus = cancelStatus;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "ORDER_SERIAL", nullable = false, length = 20)
	public String getOrderSerial() {
		return this.orderSerial;
	}

	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}

	@Column(name = "IMPORT_NO", nullable = false, length = 20)
	public String getImportNo() {
		return this.importNo;
	}

	public void setImportNo(String importNo) {
		this.importNo = importNo;
	}

	@Column(name = "PO_TYPE", nullable = false, length = 5)
	public String getPoType() {
		return this.poType;
	}

	public void setPoType(String poType) {
		this.poType = poType;
	}

	@Column(name = "CANCEL_STATUS", nullable = false, length = 1)
	public String getCancelStatus() {
		return this.cancelStatus;
	}

	public void setCancelStatus(String cancelStatus) {
		this.cancelStatus = cancelStatus;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Idata_OrderSheetId))
			return false;
		Idata_OrderSheetId castOther = (Idata_OrderSheetId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOrderSerial() == castOther.getOrderSerial()) || (this
						.getOrderSerial() != null
						&& castOther.getOrderSerial() != null && this
						.getOrderSerial().equals(castOther.getOrderSerial())))
				&& ((this.getImportNo() == castOther.getImportNo()) || (this
						.getImportNo() != null
						&& castOther.getImportNo() != null && this
						.getImportNo().equals(castOther.getImportNo())))
				&& ((this.getPoType() == castOther.getPoType()) || (this
						.getPoType() != null && castOther.getPoType() != null && this
						.getPoType().equals(castOther.getPoType())))
				&& ((this.getCancelStatus() == castOther.getCancelStatus()) || (this
						.getCancelStatus() != null
						&& castOther.getCancelStatus() != null && this
						.getCancelStatus().equals(castOther.getCancelStatus())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37
				* result
				+ (getOrderSerial() == null ? 0 : this.getOrderSerial()
						.hashCode());
		result = 37 * result
				+ (getImportNo() == null ? 0 : this.getImportNo().hashCode());
		result = 37 * result
				+ (getPoType() == null ? 0 : this.getPoType().hashCode());
		result = 37
				* result
				+ (getCancelStatus() == null ? 0 : this.getCancelStatus()
						.hashCode());
		return result;
	}

}