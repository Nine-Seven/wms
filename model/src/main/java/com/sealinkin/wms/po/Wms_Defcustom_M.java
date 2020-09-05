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
 * WmsDefcustomM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_DEFCUSTOM_M")
public class Wms_Defcustom_M implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Wms_Defcustom_MId id;
	private String customName;
	private String customEname;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Wms_Defcustom_M() {
	}

	/** minimal constructor */
	public Wms_Defcustom_M(Wms_Defcustom_MId id) {
		this.id = id;
	}

	/** full constructor */
	public Wms_Defcustom_M(Wms_Defcustom_MId id, String customName,
			String customEname, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.customName = customName;
		this.customEname = customEname;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "customId", column = @Column(name = "CUSTOM_ID", nullable = false, length = 30)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)) })
	public Wms_Defcustom_MId getId() {
		return this.id;
	}

	public void setId(Wms_Defcustom_MId id) {
		this.id = id;
	}

	@Column(name = "CUSTOM_NAME", length = 50)
	public String getCustomName() {
		return this.customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	@Column(name = "CUSTOM_ENAME", length = 50)
	public String getCustomEname() {
		return this.customEname;
	}

	public void setCustomEname(String customEname) {
		this.customEname = customEname;
	}

	@Column(name = "RGST_NAME", length = 50)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
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

}