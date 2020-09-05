package com.sealinkin.comm.po;

// default package

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WmsDefPrintCenterPoId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class WmsDefPrintCenterPoId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String reportId;
	private String reportType;
	private BigDecimal subReportId;

	// Constructors

	/** default constructor */
	public WmsDefPrintCenterPoId() {
	}

	/** full constructor */
	public WmsDefPrintCenterPoId(String reportId, String reportType,
			BigDecimal subReportId) {
		this.reportId = reportId;
		this.reportType = reportType;
		this.subReportId = subReportId;
	}

	// Property accessors

	@Column(name = "REPORT_ID", nullable = false, length = 20)
	public String getReportId() {
		return this.reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	@Column(name = "REPORT_TYPE", nullable = false, length = 1)
	public String getReportType() {
		return this.reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	@Column(name = "SUB_REPORT_ID", nullable = false, precision = 22, scale = 0)
	public BigDecimal getSubReportId() {
		return this.subReportId;
	}

	public void setSubReportId(BigDecimal subReportId) {
		this.subReportId = subReportId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WmsDefPrintCenterPoId))
			return false;
		WmsDefPrintCenterPoId castOther = (WmsDefPrintCenterPoId) other;

		return ((this.getReportId() == castOther.getReportId()) || (this
				.getReportId() != null && castOther.getReportId() != null && this
				.getReportId().equals(castOther.getReportId())))
				&& ((this.getReportType() == castOther.getReportType()) || (this
						.getReportType() != null
						&& castOther.getReportType() != null && this
						.getReportType().equals(castOther.getReportType())))
				&& ((this.getSubReportId() == castOther.getSubReportId()) || (this
						.getSubReportId() != null
						&& castOther.getSubReportId() != null && this
						.getSubReportId().equals(castOther.getSubReportId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getReportId() == null ? 0 : this.getReportId().hashCode());
		result = 37
				* result
				+ (getReportType() == null ? 0 : this.getReportType()
						.hashCode());
		result = 37
				* result
				+ (getSubReportId() == null ? 0 : this.getSubReportId()
						.hashCode());
		return result;
	}

}