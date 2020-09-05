package com.sealinkin.odata.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OdataOutstockDirectId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Odata_OutstockDirectId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private Long directSerial;
	private Date operateDate;

	// Constructors

	/** default constructor */
	public Odata_OutstockDirectId() {
	}

	/** full constructor */
	public Odata_OutstockDirectId(String warehouseNo, Long directSerial,
			Date operateDate) {
		this.warehouseNo = warehouseNo;
		this.directSerial = directSerial;
		this.operateDate = operateDate;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "DIRECT_SERIAL", nullable = false, precision = 10, scale = 0)
	public Long getDirectSerial() {
		return this.directSerial;
	}

	public void setDirectSerial(Long directSerial) {
		this.directSerial = directSerial;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OPERATE_DATE", nullable = false, length = 7)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Odata_OutstockDirectId))
			return false;
		Odata_OutstockDirectId castOther = (Odata_OutstockDirectId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getDirectSerial() == castOther.getDirectSerial()) || (this
						.getDirectSerial() != null
						&& castOther.getDirectSerial() != null && this
						.getDirectSerial().equals(castOther.getDirectSerial())))
				&& ((this.getOperateDate() == castOther.getOperateDate()) || (this
						.getOperateDate() != null
						&& castOther.getOperateDate() != null && this
						.getOperateDate().equals(castOther.getOperateDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37
				* result
				+ (getDirectSerial() == null ? 0 : this.getDirectSerial()
						.hashCode());
		result = 37
				* result
				+ (getOperateDate() == null ? 0 : this.getOperateDate()
						.hashCode());
		return result;
	}

}