package com.sealinkin.comm.po;

// default package

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DefReportId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class DefReportId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String reportId;
	private BigDecimal subReportId;
	private String reprintFlag;
	private BigDecimal sqlOrder;

	// Constructors

	/** default constructor */
	public DefReportId() {
	}

	/** full constructor */
	public DefReportId(String reportId, BigDecimal subReportId,
			String reprintFlag, BigDecimal sqlOrder) {
		this.reportId = reportId;
		this.subReportId = subReportId;
		this.reprintFlag = reprintFlag;
		this.sqlOrder = sqlOrder;
	}

	// Property accessors

	@Column(name = "REPORT_ID", nullable = false, length = 20)
	public String getReportId() {
		return this.reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	@Column(name = "SUB_REPORT_ID", nullable = false, precision = 22, scale = 0)
	public BigDecimal getSubReportId() {
		return this.subReportId;
	}

	public void setSubReportId(BigDecimal subReportId) {
		this.subReportId = subReportId;
	}

	@Column(name = "REPRINT_FLAG", nullable = false, length = 1)
	public String getReprintFlag() {
		return this.reprintFlag;
	}

	public void setReprintFlag(String reprintFlag) {
		this.reprintFlag = reprintFlag;
	}

	@Column(name = "SQL_ORDER", nullable = false, precision = 22, scale = 0)
	public BigDecimal getSqlOrder() {
		return this.sqlOrder;
	}

	public void setSqlOrder(BigDecimal sqlOrder) {
		this.sqlOrder = sqlOrder;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DefReportId))
			return false;
		DefReportId castOther = (DefReportId) other;

		return ((this.getReportId() == castOther.getReportId()) || (this
				.getReportId() != null && castOther.getReportId() != null && this
				.getReportId().equals(castOther.getReportId())))
				&& ((this.getSubReportId() == castOther.getSubReportId()) || (this
						.getSubReportId() != null
						&& castOther.getSubReportId() != null && this
						.getSubReportId().equals(castOther.getSubReportId())))
				&& ((this.getReprintFlag() == castOther.getReprintFlag()) || (this
						.getReprintFlag() != null
						&& castOther.getReprintFlag() != null && this
						.getReprintFlag().equals(castOther.getReprintFlag())))
				&& ((this.getSqlOrder() == castOther.getSqlOrder()) || (this
						.getSqlOrder() != null
						&& castOther.getSqlOrder() != null && this
						.getSqlOrder().equals(castOther.getSqlOrder())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getReportId() == null ? 0 : this.getReportId().hashCode());
		result = 37
				* result
				+ (getSubReportId() == null ? 0 : this.getSubReportId()
						.hashCode());
		result = 37
				* result
				+ (getReprintFlag() == null ? 0 : this.getReprintFlag()
						.hashCode());
		result = 37 * result
				+ (getSqlOrder() == null ? 0 : this.getSqlOrder().hashCode());
		return result;
	}

}