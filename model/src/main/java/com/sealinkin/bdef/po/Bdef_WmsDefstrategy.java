package com.sealinkin.bdef.po;

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
 * WmsDefstrategy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_DEFSTRATEGY")
public class Bdef_WmsDefstrategy implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Bdef_WmsDefstrategyId id;
	private String strategyName;
	private String defaultFlag;
	private String rgstName;
	private Date rgstDate;

	// Constructors

	/** default constructor */
	public Bdef_WmsDefstrategy() {
	}

	/** full constructor */
	public Bdef_WmsDefstrategy(Bdef_WmsDefstrategyId id, String strategyName,
			String defaultFlag, String rgstName, Date rgstDate) {
		this.id = id;
		this.strategyName = strategyName;
		this.defaultFlag = defaultFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "strategyType", column = @Column(name = "STRATEGY_TYPE", nullable = false, length = 2)),
			@AttributeOverride(name = "strategyId", column = @Column(name = "STRATEGY_ID", nullable = false, precision = 3, scale = 0)) })
	public Bdef_WmsDefstrategyId getId() {
		return this.id;
	}

	public void setId(Bdef_WmsDefstrategyId id) {
		this.id = id;
	}

	@Column(name = "STRATEGY_NAME", nullable = false, length = 50)
	public String getStrategyName() {
		return this.strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	@Column(name = "DEFAULT_FLAG", nullable = false, length = 1)
	public String getDefaultFlag() {
		return this.defaultFlag;
	}

	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RGST_DATE", nullable = false, length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

}