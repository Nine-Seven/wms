package com.sealinkin.bset.po;
// default package

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bset_Module entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BSET_MODULE")
public class Bset_Module implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String moduleId;
	private String moduleCaption;
	private String moduleHint;
	private BigDecimal moduleRightvalues;
	private BigDecimal moduleEnabled;
	private String moduleUrl;
	private String moduleController;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Bset_Module() {
	}

	/** minimal constructor */
	public Bset_Module(BigDecimal moduleRightvalues, BigDecimal moduleEnabled) {
		this.moduleRightvalues = moduleRightvalues;
		this.moduleEnabled = moduleEnabled;
	}

	/** full constructor */
	public Bset_Module(String moduleCaption, String moduleHint,
			BigDecimal moduleRightvalues, BigDecimal moduleEnabled,
			String moduleUrl, String moduleController, String rgstName,
			Date rgstDate, String updtName, Date updtDate) {
		this.moduleCaption = moduleCaption;
		this.moduleHint = moduleHint;
		this.moduleRightvalues = moduleRightvalues;
		this.moduleEnabled = moduleEnabled;
		this.moduleUrl = moduleUrl;
		this.moduleController = moduleController;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "MODULE_ID", unique = true, nullable = false, length = 24)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name = "MODULE_CAPTION", length = 64)
	public String getModuleCaption() {
		return this.moduleCaption;
	}

	public void setModuleCaption(String moduleCaption) {
		this.moduleCaption = moduleCaption;
	}

	@Column(name = "MODULE_HINT", length = 128)
	public String getModuleHint() {
		return this.moduleHint;
	}

	public void setModuleHint(String moduleHint) {
		this.moduleHint = moduleHint;
	}

	@Column(name = "MODULE_RIGHTVALUES", nullable = false, precision = 22, scale = 0)
	public BigDecimal getModuleRightvalues() {
		return this.moduleRightvalues;
	}

	public void setModuleRightvalues(BigDecimal moduleRightvalues) {
		this.moduleRightvalues = moduleRightvalues;
	}

	@Column(name = "MODULE_ENABLED", nullable = false, precision = 22, scale = 0)
	public BigDecimal getModuleEnabled() {
		return this.moduleEnabled;
	}

	public void setModuleEnabled(BigDecimal moduleEnabled) {
		this.moduleEnabled = moduleEnabled;
	}

	@Column(name = "MODULE_URL", length = 256)
	public String getModuleUrl() {
		return this.moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	@Column(name = "MODULE_CONTROLLER", length = 256)
	public String getModuleController() {
		return this.moduleController;
	}

	public void setModuleController(String moduleController) {
		this.moduleController = moduleController;
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

	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}