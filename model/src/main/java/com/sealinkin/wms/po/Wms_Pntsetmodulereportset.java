package com.sealinkin.wms.po;

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
 * PntsetModuleReportSet entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PNTSET_MODULE_REPORT_SET")
public class Wms_Pntsetmodulereportset implements java.io.Serializable {

	// Fields

	private Wms_PntsetmodulereportsetId id;
	private String sqlText;
	private String sqlText2;
	private String sqlText3;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Wms_Pntsetmodulereportset() {
	}

	/** minimal constructor */
	public Wms_Pntsetmodulereportset(Wms_PntsetmodulereportsetId id) {
		this.id = id;
	}

	/** full constructor */
	public Wms_Pntsetmodulereportset(Wms_PntsetmodulereportsetId id, String sqlText,
			String sqlText2, String sqlText3, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.sqlText = sqlText;
		this.sqlText2 = sqlText2;
		this.sqlText3 = sqlText3;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "moduleId", column = @Column(name = "MODULE_ID", nullable = false, length = 24)) })
	public Wms_PntsetmodulereportsetId getId() {
		return this.id;
	}

	public void setId(Wms_PntsetmodulereportsetId id) {
		this.id = id;
	}

	@Column(name = "SQL_TEXT", length = 4000)
	public String getSqlText() {
		return this.sqlText;
	}

	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}

	@Column(name = "SQL_TEXT2", length = 4000)
	public String getSqlText2() {
		return this.sqlText2;
	}

	public void setSqlText2(String sqlText2) {
		this.sqlText2 = sqlText2;
	}

	@Column(name = "SQL_TEXT3", length = 4000)
	public String getSqlText3() {
		return this.sqlText3;
	}

	public void setSqlText3(String sqlText3) {
		this.sqlText3 = sqlText3;
	}

	@Column(name = "RGST_NAME", length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.TIMESTAMP)
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}
