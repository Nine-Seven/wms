package com.sealinkin.bdef.po;

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
 * WmsLogiboxRule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_LOGIBOX_RULE")
public class Wms_LogiboxRule implements java.io.Serializable {

	// Fields

	private Wms_LogiboxRuleId id;
	private String allotRule;
	private BigDecimal allotRuleValue;
	private String areaRule;
	private String volFlag;
	private String weightFlag;
	private BigDecimal boxSkuLimit;
	private String memo;
	private String rgstName;
	private Date rgstDate;
	private String onedeliveronepick;
	private String splitboxFlag;

	// Constructors

	/** default constructor */
	public Wms_LogiboxRule() {
	}

	/** minimal constructor */
	public Wms_LogiboxRule(Wms_LogiboxRuleId id, String allotRule,
			BigDecimal allotRuleValue, String areaRule, BigDecimal boxSkuLimit,
			String onedeliveronepick, String splitboxFlag) {
		this.id = id;
		this.allotRule = allotRule;
		this.allotRuleValue = allotRuleValue;
		this.areaRule = areaRule;
		this.boxSkuLimit = boxSkuLimit;
		this.onedeliveronepick = onedeliveronepick;
		this.splitboxFlag = splitboxFlag;
	}

	/** full constructor */
	public Wms_LogiboxRule(Wms_LogiboxRuleId id, String allotRule,
			BigDecimal allotRuleValue, String areaRule, String volFlag,
			String weightFlag, BigDecimal boxSkuLimit, String memo,
			String rgstName, Date rgstDate, String onedeliveronepick,
			String splitboxFlag) {
		this.id = id;
		this.allotRule = allotRule;
		this.allotRuleValue = allotRuleValue;
		this.areaRule = areaRule;
		this.volFlag = volFlag;
		this.weightFlag = weightFlag;
		this.boxSkuLimit = boxSkuLimit;
		this.memo = memo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.onedeliveronepick = onedeliveronepick;
		this.splitboxFlag = splitboxFlag;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "ruleId", column = @Column(name = "RULE_ID", nullable = false, length = 5)) })
	public Wms_LogiboxRuleId getId() {
		return this.id;
	}

	public void setId(Wms_LogiboxRuleId id) {
		this.id = id;
	}

	@Column(name = "ALLOT_RULE", nullable = false, length = 1)
	public String getAllotRule() {
		return this.allotRule;
	}

	public void setAllotRule(String allotRule) {
		this.allotRule = allotRule;
	}

	@Column(name = "ALLOT_RULE_VALUE", nullable = false, precision = 22, scale = 0)
	public BigDecimal getAllotRuleValue() {
		return this.allotRuleValue;
	}

	public void setAllotRuleValue(BigDecimal allotRuleValue) {
		this.allotRuleValue = allotRuleValue;
	}

	@Column(name = "AREA_RULE", nullable = false, length = 1)
	public String getAreaRule() {
		return this.areaRule;
	}

	public void setAreaRule(String areaRule) {
		this.areaRule = areaRule;
	}

	@Column(name = "VOL_FLAG", length = 1)
	public String getVolFlag() {
		return this.volFlag;
	}

	public void setVolFlag(String volFlag) {
		this.volFlag = volFlag;
	}

	@Column(name = "WEIGHT_FLAG", length = 1)
	public String getWeightFlag() {
		return this.weightFlag;
	}

	public void setWeightFlag(String weightFlag) {
		this.weightFlag = weightFlag;
	}

	@Column(name = "BOX_SKU_LIMIT", nullable = false, precision = 22, scale = 0)
	public BigDecimal getBoxSkuLimit() {
		return this.boxSkuLimit;
	}

	public void setBoxSkuLimit(BigDecimal boxSkuLimit) {
		this.boxSkuLimit = boxSkuLimit;
	}

	@Column(name = "MEMO", length = 100)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	@Column(name = "ONEDELIVERONEPICK", nullable = false, length = 1)
	public String getOnedeliveronepick() {
		return this.onedeliveronepick;
	}

	public void setOnedeliveronepick(String onedeliveronepick) {
		this.onedeliveronepick = onedeliveronepick;
	}

	@Column(name = "SPLITBOX_FLAG", nullable = false, length = 1)
	public String getSplitboxFlag() {
		return this.splitboxFlag;
	}

	public void setSplitboxFlag(String splitboxFlag) {
		this.splitboxFlag = splitboxFlag;
	}

}