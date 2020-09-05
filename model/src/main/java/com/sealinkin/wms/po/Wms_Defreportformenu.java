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
 * WmsDefreportformenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_DEFREPORTFORMENU")
public class Wms_Defreportformenu implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Wms_DefreportformenuId id;
	private BigDecimal orderNo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String show;

	// Constructors

	/** default constructor */
	public Wms_Defreportformenu() {
	}

	/** minimal constructor */
	public Wms_Defreportformenu(Wms_DefreportformenuId id, BigDecimal orderNo,
			String rgstName, String show) {
		this.id = id;
		this.orderNo = orderNo;
		this.rgstName = rgstName;
		this.show = show;
	}

	/** full constructor */
	public Wms_Defreportformenu(Wms_DefreportformenuId id, BigDecimal orderNo,
			String rgstName, Date rgstDate, String updtName, Date updtDate,
			String show) {
		this.id = id;
		this.orderNo = orderNo;
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
			@AttributeOverride(name = "pgmId", column = @Column(name = "PGM_ID", nullable = false, length = 20)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)) })
	public Wms_DefreportformenuId getId() {
		return this.id;
	}

	public void setId(Wms_DefreportformenuId id) {
		this.id = id;
	}

	@Column(name = "ORDER_NO", nullable = false, precision = 22, scale = 0)
	public BigDecimal getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(BigDecimal orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
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

	@Column(name = "SHOW", nullable = false, length = 1)
	public String getShow() {
		return this.show;
	}

	public void setShow(String show) {
		this.show = show;
	}

}