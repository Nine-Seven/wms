package com.sealinkin.wms.po;

import java.math.BigDecimal;
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
 * WmsDefcustomMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_DEFCUSTOM_MENU")
public class Wms_Defcustom_Menu implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Wms_Defcustom_MenuId id;
	private BigDecimal seq;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String show;

	// Constructors

	/** default constructor */
	public Wms_Defcustom_Menu() {
	}

	/** minimal constructor */
	public Wms_Defcustom_Menu(Wms_Defcustom_MenuId id) {
		this.id = id;
	}

	/** full constructor */
	public Wms_Defcustom_Menu(Wms_Defcustom_MenuId id, BigDecimal seq,
			String rgstName, Date rgstDate, String updtName, Date updtDate,
			String show) {
		this.id = id;
		this.seq = seq;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.show = show;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "moduleId", column = @Column(name = "MODULE_ID", nullable = false, length = 20)),
			@AttributeOverride(name = "customId", column = @Column(name = "CUSTOM_ID", nullable = false, length = 30)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)) })
	public Wms_Defcustom_MenuId getId() {
		return this.id;
	}

	public void setId(Wms_Defcustom_MenuId id) {
		this.id = id;
	}

	@Column(name = "SEQ", precision = 22, scale = 0)
	public BigDecimal getSeq() {
		return this.seq;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

	@Column(name = "RGST_NAME", length = 20)
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

	@Column(name = "SHOW", length = 1)
	public String getShow() {
		return this.show;
	}

	public void setShow(String show) {
		this.show = show;
	}

}