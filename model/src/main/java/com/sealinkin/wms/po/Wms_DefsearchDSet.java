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
 * WmsDefsearchDSet entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_DEFSEARCH_D_SET")
public class Wms_DefsearchDSet implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Wms_DefsearchDSetId id;
	private BigDecimal seq;
	private String orderType;
	private String statisticsFlag;
	private BigDecimal width;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String statisticsFormulae;

	// Constructors

	/** default constructor */
	public Wms_DefsearchDSet() {
	}

	/** minimal constructor */
	public Wms_DefsearchDSet(Wms_DefsearchDSetId id) {
		this.id = id;
	}

	/** full constructor */
	public Wms_DefsearchDSet(Wms_DefsearchDSetId id, BigDecimal seq,
			String orderType, String statisticsFlag, BigDecimal width,
			Date rgstDate, String updtName, Date updtDate,
			String statisticsFormulae) {
		this.id = id;
		this.seq = seq;
		this.orderType = orderType;
		this.statisticsFlag = statisticsFlag;
		this.width = width;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.statisticsFormulae = statisticsFormulae;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "pgmId", column = @Column(name = "PGM_ID", nullable = false, length = 20)),
			@AttributeOverride(name = "name", column = @Column(name = "NAME", nullable = false, length = 20)),
			@AttributeOverride(name = "fieldId", column = @Column(name = "FIELD_ID", nullable = false, length = 30)),
			@AttributeOverride(name = "rgstName", column = @Column(name = "RGST_NAME", nullable = false, length = 20)) })
	public Wms_DefsearchDSetId getId() {
		return this.id;
	}

	public void setId(Wms_DefsearchDSetId id) {
		this.id = id;
	}

	@Column(name = "SEQ", precision = 22, scale = 0)
	public BigDecimal getSeq() {
		return this.seq;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

	@Column(name = "ORDER_TYPE", length = 4)
	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Column(name = "STATISTICS_FLAG", length = 1)
	public String getStatisticsFlag() {
		return this.statisticsFlag;
	}

	public void setStatisticsFlag(String statisticsFlag) {
		this.statisticsFlag = statisticsFlag;
	}

	@Column(name = "WIDTH", precision = 22, scale = 0)
	public BigDecimal getWidth() {
		return this.width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
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

	@Column(name = "STATISTICS_FORMULAE", length = 20)
	public String getStatisticsFormulae() {
		return this.statisticsFormulae;
	}

	public void setStatisticsFormulae(String statisticsFormulae) {
		this.statisticsFormulae = statisticsFormulae;
	}

}