package com.sealinkin.comm.po;

// default package

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * DefReport entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PNTDEF_REPORT")
public class DefReport implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private DefReportId id;
	private String reportType;
	private String reportFilename;
	private String sqlText;
	private String reportName;
	private BigDecimal reportCopies;
	private String sqlType;
	private String rgstName;
	private Timestamp rgstDate;
	private String updtName;
	private Timestamp updtDate;

	// Constructors

	/** default constructor */
	public DefReport() {
	}

	/** minimal constructor */
	public DefReport(DefReportId id) {
		this.id = id;
	}

	/** full constructor */
	public DefReport(DefReportId id, String reportType, String reportFilename,
			String sqlText, String reportName, BigDecimal reportCopies,
			String sqlType, String rgstName, Timestamp rgstDate,
			String updtName, Timestamp updtDate) {
		this.id = id;
		this.reportType = reportType;
		this.reportFilename = reportFilename;
		this.sqlText = sqlText;
		this.reportName = reportName;
		this.reportCopies = reportCopies;
		this.sqlType = sqlType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "reportId", column = @Column(name = "REPORT_ID", nullable = false, length = 20)),
			@AttributeOverride(name = "subReportId", column = @Column(name = "SUB_REPORT_ID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "reprintFlag", column = @Column(name = "REPRINT_FLAG", nullable = false, length = 1)),
			@AttributeOverride(name = "sqlOrder", column = @Column(name = "SQL_ORDER", nullable = false, precision = 22, scale = 0)) })
	public DefReportId getId() {
		return this.id;
	}

	public void setId(DefReportId id) {
		this.id = id;
	}

	@Column(name = "REPORT_TYPE", length = 1)
	public String getReportType() {
		return this.reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	@Column(name = "REPORT_FILENAME", length = 50)
	public String getReportFilename() {
		return this.reportFilename;
	}

	public void setReportFilename(String reportFilename) {
		this.reportFilename = reportFilename;
	}

	@Column(name = "SQL_TEXT", length = 4000)
	public String getSqlText() {
		return this.sqlText;
	}

	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}

	@Column(name = "REPORT_NAME", length = 50)
	public String getReportName() {
		return this.reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	@Column(name = "REPORT_COPIES", precision = 22, scale = 0)
	public BigDecimal getReportCopies() {
		return this.reportCopies;
	}

	public void setReportCopies(BigDecimal reportCopies) {
		this.reportCopies = reportCopies;
	}

	@Column(name = "SQL_TYPE", length = 1)
	public String getSqlType() {
		return this.sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

	@Column(name = "RGST_NAME", length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Column(name = "RGST_DATE", length = 7)
	public Timestamp getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Timestamp rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "UPDT_NAME", length = 20)
	public String getUpdtName() {
		return this.updtName;
	}

	public void setUpdtName(String updtName) {
		this.updtName = updtName;
	}

	@Column(name = "UPDT_DATE", length = 7)
	public Timestamp getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Timestamp updtDate) {
		this.updtDate = updtDate;
	}

}