package com.sealinkin.comm.po;

// default package


import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * WmsDefPrintCenterPoPo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_DEFPRINT_CENTER")
public class WmsDefPrintCenterPo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private WmsDefPrintCenterPoId id;
	private String reportName;
	private String sqlText;
	private String sqlTextThy;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String sqlText2;
	private String sqlTexHty2;

	// Constructors

	/** default constructor */
	public WmsDefPrintCenterPo() {
	}

	/** minimal constructor */
	public WmsDefPrintCenterPo(WmsDefPrintCenterPoId id) {
		this.id = id;
	}

	/** full constructor */
	public WmsDefPrintCenterPo(WmsDefPrintCenterPoId id, String reportName,
			String sqlText, String sqlTextThy, String rgstName, Date rgstDate,
			String updtName, Date updtDate, String sqlText2, String sqlTexHty2) {
		this.id = id;
		this.reportName = reportName;
		this.sqlText = sqlText;
		this.sqlTextThy = sqlTextThy;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.sqlText2 = sqlText2;
		this.sqlTexHty2 = sqlTexHty2;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "reportId", column = @Column(name = "REPORT_ID", nullable = false, length = 20)),
			@AttributeOverride(name = "reportType", column = @Column(name = "REPORT_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "subReportId", column = @Column(name = "SUB_REPORT_ID", nullable = false, precision = 22, scale = 0)) })
	public WmsDefPrintCenterPoId getId() {
		return this.id;
	}

	public void setId(WmsDefPrintCenterPoId id) {
		this.id = id;
	}

	@Column(name = "REPORT_NAME", length = 50)
	public String getReportName() {
		return this.reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	@Column(name = "SQL_TEXT", length = 4000)
	public String getSqlText() {
		return this.sqlText;
	}

	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}

	@Column(name = "SQL_TEXT_THY", length = 4000)
	public String getSqlTextThy() {
		return this.sqlTextThy;
	}

	public void setSqlTextThy(String sqlTextThy) {
		this.sqlTextThy = sqlTextThy;
	}

	@Column(name = "RGST_NAME", length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	
	@Column(name = "RGST_DATE", length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "UPDT_NAME", length = 20)
	public String getUpdtName() {
		return this.updtName;
	}

	public void setUpdtName(String updtName) {
		this.updtName = updtName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	@Column(name = "SQL_TEXT2", length = 4000)
	public String getSqlText2() {
		return this.sqlText2;
	}

	public void setSqlText2(String sqlText2) {
		this.sqlText2 = sqlText2;
	}

	@Column(name = "SQL_TEX_HTY2", length = 4000)
	public String getSqlTexHty2() {
		return this.sqlTexHty2;
	}

	public void setSqlTexHty2(String sqlTexHty2) {
		this.sqlTexHty2 = sqlTexHty2;
	}

}