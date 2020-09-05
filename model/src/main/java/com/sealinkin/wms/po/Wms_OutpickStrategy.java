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
 * WmsOutpickStrategy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_OUTPICK_STRATEGY")
public class Wms_OutpickStrategy implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Wms_OutpickStrategyId id;
	private String pickStrategyName;
	private String pickDiffFlag;
	private String pickAutoFlag;
	private String autoGetdivideFlag;
	private String autoDividesaveFlag;
	private String rsvValue1;
	private String rsvValue2;
	private String rsvValue3;
	private String rsvValue4;
	private String rsvValue5;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Wms_OutpickStrategy() {
	}

	/** minimal constructor */
	public Wms_OutpickStrategy(Wms_OutpickStrategyId id, String rgstName,
			Date rgstDate) {
		this.id = id;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Wms_OutpickStrategy(Wms_OutpickStrategyId id, String pickStrategyName,
			String pickDiffFlag, String pickAutoFlag, String autoGetdivideFlag,
			String autoDividesaveFlag, String rsvValue1, String rsvValue2,
			String rsvValue3, String rsvValue4, String rsvValue5,
			String rgstName, Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.pickStrategyName = pickStrategyName;
		this.pickDiffFlag = pickDiffFlag;
		this.pickAutoFlag = pickAutoFlag;
		this.autoGetdivideFlag = autoGetdivideFlag;
		this.autoDividesaveFlag = autoDividesaveFlag;
		this.rsvValue1 = rsvValue1;
		this.rsvValue2 = rsvValue2;
		this.rsvValue3 = rsvValue3;
		this.rsvValue4 = rsvValue4;
		this.rsvValue5 = rsvValue5;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "pickStrategyId", column = @Column(name = "PICK_STRATEGY_ID", nullable = false, precision = 5, scale = 0)) })
	public Wms_OutpickStrategyId getId() {
		return this.id;
	}

	public void setId(Wms_OutpickStrategyId id) {
		this.id = id;
	}

	@Column(name = "PICK_STRATEGY_NAME", length = 50)
	public String getPickStrategyName() {
		return this.pickStrategyName;
	}

	public void setPickStrategyName(String pickStrategyName) {
		this.pickStrategyName = pickStrategyName;
	}

	@Column(name = "PICK_DIFF_FLAG", length = 1)
	public String getPickDiffFlag() {
		return this.pickDiffFlag;
	}

	public void setPickDiffFlag(String pickDiffFlag) {
		this.pickDiffFlag = pickDiffFlag;
	}

	@Column(name = "PICK_AUTO_FLAG", length = 1)
	public String getPickAutoFlag() {
		return this.pickAutoFlag;
	}

	public void setPickAutoFlag(String pickAutoFlag) {
		this.pickAutoFlag = pickAutoFlag;
	}

	@Column(name = "AUTO_GETDIVIDE_FLAG", length = 1)
	public String getAutoGetdivideFlag() {
		return this.autoGetdivideFlag;
	}

	public void setAutoGetdivideFlag(String autoGetdivideFlag) {
		this.autoGetdivideFlag = autoGetdivideFlag;
	}

	@Column(name = "AUTO_DIVIDESAVE_FLAG", length = 1)
	public String getAutoDividesaveFlag() {
		return this.autoDividesaveFlag;
	}

	public void setAutoDividesaveFlag(String autoDividesaveFlag) {
		this.autoDividesaveFlag = autoDividesaveFlag;
	}

	@Column(name = "RSV_VALUE1", length = 1)
	public String getRsvValue1() {
		return this.rsvValue1;
	}

	public void setRsvValue1(String rsvValue1) {
		this.rsvValue1 = rsvValue1;
	}

	@Column(name = "RSV_VALUE2", length = 1)
	public String getRsvValue2() {
		return this.rsvValue2;
	}

	public void setRsvValue2(String rsvValue2) {
		this.rsvValue2 = rsvValue2;
	}

	@Column(name = "RSV_VALUE3", length = 1)
	public String getRsvValue3() {
		return this.rsvValue3;
	}

	public void setRsvValue3(String rsvValue3) {
		this.rsvValue3 = rsvValue3;
	}

	@Column(name = "RSV_VALUE4", length = 1)
	public String getRsvValue4() {
		return this.rsvValue4;
	}

	public void setRsvValue4(String rsvValue4) {
		this.rsvValue4 = rsvValue4;
	}

	@Column(name = "RSV_VALUE5", length = 1)
	public String getRsvValue5() {
		return this.rsvValue5;
	}

	public void setRsvValue5(String rsvValue5) {
		this.rsvValue5 = rsvValue5;
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