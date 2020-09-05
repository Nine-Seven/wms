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
 * WmsOutwaveplanM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_OUTWAVEPLAN_M")
public class Wms_OutwaveplanM implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Wms_OutwaveplanMId id;
	private String strategyName;
	private String defalutFlag;
	private String rgstName;
	private Date rgstDate;

	// Constructors

	/** default constructor */
	public Wms_OutwaveplanM() {
	}

	/** full constructor */
	public Wms_OutwaveplanM(Wms_OutwaveplanMId id, String strategyName,
			String defalutFlag, String rgstName, Date rgstDate) {
		this.id = id;
		this.strategyName = strategyName;
		this.defalutFlag = defalutFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "batchStrategyId", column = @Column(name = "BATCH_STRATEGY_ID", nullable = false, precision = 5, scale = 0)) })
	public Wms_OutwaveplanMId getId() {
		return this.id;
	}

	public void setId(Wms_OutwaveplanMId id) {
		this.id = id;
	}

	@Column(name = "STRATEGY_NAME", nullable = false, length = 50)
	public String getStrategyName() {
		return this.strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	@Column(name = "DEFALUT_FLAG", nullable = false, length = 1)
	public String getDefalutFlag() {
		return this.defalutFlag;
	}

	public void setDefalutFlag(String defalutFlag) {
		this.defalutFlag = defalutFlag;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RGST_DATE", nullable = false, length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

}