package com.sealinkin.rodata.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RodataOutstockDirectId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Rodata_OutstockDirectId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date operateDate;
	private Integer directSerial;

	// Constructors

	/** default constructor */
	public Rodata_OutstockDirectId() {
	}

	/** full constructor */
	public Rodata_OutstockDirectId(Date operateDate, Integer directSerial) {
		this.operateDate = operateDate;
		this.directSerial = directSerial;
	}

	// Property accessors
	@Temporal(TemporalType.DATE)
	@Column(name = "OPERATE_DATE", nullable = false, length = 7)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "DIRECT_SERIAL", nullable = false, precision = 8, scale = 0)
	public Integer getDirectSerial() {
		return this.directSerial;
	}

	public void setDirectSerial(Integer directSerial) {
		this.directSerial = directSerial;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Rodata_OutstockDirectId))
			return false;
		Rodata_OutstockDirectId castOther = (Rodata_OutstockDirectId) other;

		return ((this.getOperateDate() == castOther.getOperateDate()) || (this
				.getOperateDate() != null && castOther.getOperateDate() != null && this
				.getOperateDate().equals(castOther.getOperateDate())))
				&& ((this.getDirectSerial() == castOther.getDirectSerial()) || (this
						.getDirectSerial() != null
						&& castOther.getDirectSerial() != null && this
						.getDirectSerial().equals(castOther.getDirectSerial())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getOperateDate() == null ? 0 : this.getOperateDate()
						.hashCode());
		result = 37
				* result
				+ (getDirectSerial() == null ? 0 : this.getDirectSerial()
						.hashCode());
		return result;
	}

}