package com.sealinkin.odata.po;

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
 * WmsCheckStrategy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_CHECK_STRATEGY", schema = "SEALINKWMS_V3_2")
public class Odata_WmsCheckStrategy implements java.io.Serializable {

	// Fields

	private Odata_WmsCheckStrategyId id;
	private String checkStrategyName;
	private String skipPickFlag;
	private String checkLevel;
	private String autoCloseBox;
	private String packAdvance;
	private String weightFlag;
	private String volumFlag;
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
	public Odata_WmsCheckStrategy() {
	}

	/** minimal constructor */
	public Odata_WmsCheckStrategy(Odata_WmsCheckStrategyId id, String checkStrategyName,
			String skipPickFlag, String checkLevel, String autoCloseBox,
			String packAdvance, String weightFlag, String volumFlag,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.checkStrategyName = checkStrategyName;
		this.skipPickFlag = skipPickFlag;
		this.checkLevel = checkLevel;
		this.autoCloseBox = autoCloseBox;
		this.packAdvance = packAdvance;
		this.weightFlag = weightFlag;
		this.volumFlag = volumFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Odata_WmsCheckStrategy(Odata_WmsCheckStrategyId id, String checkStrategyName,
			String skipPickFlag, String checkLevel, String autoCloseBox,
			String packAdvance, String weightFlag, String volumFlag,
			String rsvValue1, String rsvValue2, String rsvValue3,
			String rsvValue4, String rsvValue5, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.checkStrategyName = checkStrategyName;
		this.skipPickFlag = skipPickFlag;
		this.checkLevel = checkLevel;
		this.autoCloseBox = autoCloseBox;
		this.packAdvance = packAdvance;
		this.weightFlag = weightFlag;
		this.volumFlag = volumFlag;
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
			@AttributeOverride(name = "checkType", column = @Column(name = "CHECK_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "checkStrategyId", column = @Column(name = "CHECK_STRATEGY_ID", nullable = false, precision = 5, scale = 0)) })
	public Odata_WmsCheckStrategyId getId() {
		return this.id;
	}

	public void setId(Odata_WmsCheckStrategyId id) {
		this.id = id;
	}

	@Column(name = "CHECK_STRATEGY_NAME", nullable = false, length = 50)
	public String getCheckStrategyName() {
		return this.checkStrategyName;
	}

	public void setCheckStrategyName(String checkStrategyName) {
		this.checkStrategyName = checkStrategyName;
	}

	@Column(name = "SKIP_PICK_FLAG", nullable = false, length = 2)
	public String getSkipPickFlag() {
		return this.skipPickFlag;
	}

	public void setSkipPickFlag(String skipPickFlag) {
		this.skipPickFlag = skipPickFlag;
	}

	@Column(name = "CHECK_LEVEL", nullable = false, length = 1)
	public String getCheckLevel() {
		return this.checkLevel;
	}

	public void setCheckLevel(String checkLevel) {
		this.checkLevel = checkLevel;
	}

	@Column(name = "AUTO_CLOSE_BOX", nullable = false, length = 1)
	public String getAutoCloseBox() {
		return this.autoCloseBox;
	}

	public void setAutoCloseBox(String autoCloseBox) {
		this.autoCloseBox = autoCloseBox;
	}

	@Column(name = "PACK_ADVANCE", nullable = false, length = 1)
	public String getPackAdvance() {
		return this.packAdvance;
	}

	public void setPackAdvance(String packAdvance) {
		this.packAdvance = packAdvance;
	}

	@Column(name = "WEIGHT_FLAG", nullable = false, length = 1)
	public String getWeightFlag() {
		return this.weightFlag;
	}

	public void setWeightFlag(String weightFlag) {
		this.weightFlag = weightFlag;
	}

	@Column(name = "VOLUM_FLAG", nullable = false, length = 1)
	public String getVolumFlag() {
		return this.volumFlag;
	}

	public void setVolumFlag(String volumFlag) {
		this.volumFlag = volumFlag;
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