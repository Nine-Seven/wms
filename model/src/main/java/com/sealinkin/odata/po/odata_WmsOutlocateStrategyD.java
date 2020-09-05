package com.sealinkin.odata.po;

// default package

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
 * WmsOutlocateStrategyD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_OUTLOCATE_STRATEGY_D", schema = "SEALINKWMS_V3_2")
public class odata_WmsOutlocateStrategyD implements java.io.Serializable {

	// Fields

	private odata_WmsOutlocateStrategyDId id;
	private String locateRuleName;
	private String deliverObjLevel;
	private String PFlag;
	private String MFlag;
	private String WFlag;
	private String SFlag;
	private String DFlag;
	//private String BDivideFlag;
	//private String CDivideFlag;
	private String commitType;
	private String shortqtyType;
	private String rsvControl1;
	private String rsvControl2;
	private String rsvControl3;
	private String rsvControl4;
	private String rsvControl5;
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
	public odata_WmsOutlocateStrategyD() {
	}

	/** minimal constructor */
	public odata_WmsOutlocateStrategyD(odata_WmsOutlocateStrategyDId id,
			String deliverObjLevel, String MFlag, String WFlag, String SFlag,
			String DFlag, String BDivideFlag, String CDivideFlag,
			String commitType, String shortqtyType, String rgstName,
			Date rgstDate) {
		this.id = id;
		this.deliverObjLevel = deliverObjLevel;
		this.MFlag = MFlag;
		this.WFlag = WFlag;
		this.SFlag = SFlag;
		this.DFlag = DFlag;
		//this.BDivideFlag = BDivideFlag;
		//this.CDivideFlag = CDivideFlag;
		this.commitType = commitType;
		this.shortqtyType = shortqtyType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public odata_WmsOutlocateStrategyD(odata_WmsOutlocateStrategyDId id,
			String locateRuleName, String deliverObjLevel, String PFlag,
			String MFlag, String WFlag, String SFlag, String DFlag,
			String BDivideFlag, String CDivideFlag, String commitType,
			String shortqtyType, String rsvControl1, String rsvControl2,
			String rsvControl3, String rsvControl4, String rsvControl5,
			String rsvValue1, String rsvValue2, String rsvValue3,
			String rsvValue4, String rsvValue5, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.locateRuleName = locateRuleName;
		this.deliverObjLevel = deliverObjLevel;
		this.PFlag = PFlag;
		this.MFlag = MFlag;
		this.WFlag = WFlag;
		this.SFlag = SFlag;
		this.DFlag = DFlag;
		//this.BDivideFlag = BDivideFlag;
		//this.CDivideFlag = CDivideFlag;
		this.commitType = commitType;
		this.shortqtyType = shortqtyType;
		this.rsvControl1 = rsvControl1;
		this.rsvControl2 = rsvControl2;
		this.rsvControl3 = rsvControl3;
		this.rsvControl4 = rsvControl4;
		this.rsvControl5 = rsvControl5;
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
			@AttributeOverride(name = "locateStrategyId", column = @Column(name = "LOCATE_STRATEGY_ID", nullable = false, precision = 5, scale = 0)),
			@AttributeOverride(name = "locateRuleId", column = @Column(name = "LOCATE_RULE_ID", nullable = false, length = 5)) })
	public odata_WmsOutlocateStrategyDId getId() {
		return this.id;
	}

	public void setId(odata_WmsOutlocateStrategyDId id) {
		this.id = id;
	}

	@Column(name = "LOCATE_RULE_NAME", length = 50)
	public String getLocateRuleName() {
		return this.locateRuleName;
	}

	public void setLocateRuleName(String locateRuleName) {
		this.locateRuleName = locateRuleName;
	}

	@Column(name = "DELIVER_OBJ_LEVEL", nullable = false, length = 1)
	public String getDeliverObjLevel() {
		return this.deliverObjLevel;
	}

	public void setDeliverObjLevel(String deliverObjLevel) {
		this.deliverObjLevel = deliverObjLevel;
	}

	@Column(name = "P_FLAG", length = 1)
	public String getPFlag() {
		return this.PFlag;
	}

	public void setPFlag(String PFlag) {
		this.PFlag = PFlag;
	}

	@Column(name = "M_FLAG", nullable = false, length = 1)
	public String getMFlag() {
		return this.MFlag;
	}

	public void setMFlag(String MFlag) {
		this.MFlag = MFlag;
	}

	@Column(name = "W_FLAG", nullable = false, length = 1)
	public String getWFlag() {
		return this.WFlag;
	}

	public void setWFlag(String WFlag) {
		this.WFlag = WFlag;
	}

	@Column(name = "S_FLAG", nullable = false, length = 1)
	public String getSFlag() {
		return this.SFlag;
	}

	public void setSFlag(String SFlag) {
		this.SFlag = SFlag;
	}

	@Column(name = "D_FLAG", nullable = false, length = 1)
	public String getDFlag() {
		return this.DFlag;
	}

	public void setDFlag(String DFlag) {
		this.DFlag = DFlag;
	}
/*
	@Column(name = "B_DIVIDE_FLAG", nullable = false, length = 1)
	public String getBDivideFlag() {
		return this.BDivideFlag;
	}

	public void setBDivideFlag(String BDivideFlag) {
		this.BDivideFlag = BDivideFlag;
	}

	@Column(name = "C_DIVIDE_FLAG", nullable = false, length = 1)
	public String getCDivideFlag() {
		return this.CDivideFlag;
	}

	public void setCDivideFlag(String CDivideFlag) {
		this.CDivideFlag = CDivideFlag;
	}
*/
	@Column(name = "COMMIT_TYPE", nullable = false, length = 1)
	public String getCommitType() {
		return this.commitType;
	}

	public void setCommitType(String commitType) {
		this.commitType = commitType;
	}

	@Column(name = "SHORTQTY_TYPE", nullable = false, length = 1)
	public String getShortqtyType() {
		return this.shortqtyType;
	}

	public void setShortqtyType(String shortqtyType) {
		this.shortqtyType = shortqtyType;
	}

	@Column(name = "RSV_CONTROL1", length = 5)
	public String getRsvControl1() {
		return this.rsvControl1;
	}

	public void setRsvControl1(String rsvControl1) {
		this.rsvControl1 = rsvControl1;
	}

	@Column(name = "RSV_CONTROL2", length = 5)
	public String getRsvControl2() {
		return this.rsvControl2;
	}

	public void setRsvControl2(String rsvControl2) {
		this.rsvControl2 = rsvControl2;
	}

	@Column(name = "RSV_CONTROL3", length = 5)
	public String getRsvControl3() {
		return this.rsvControl3;
	}

	public void setRsvControl3(String rsvControl3) {
		this.rsvControl3 = rsvControl3;
	}

	@Column(name = "RSV_CONTROL4", length = 5)
	public String getRsvControl4() {
		return this.rsvControl4;
	}

	public void setRsvControl4(String rsvControl4) {
		this.rsvControl4 = rsvControl4;
	}

	@Column(name = "RSV_CONTROL5", length = 5)
	public String getRsvControl5() {
		return this.rsvControl5;
	}

	public void setRsvControl5(String rsvControl5) {
		this.rsvControl5 = rsvControl5;
	}

	@Column(name = "RSV_VALUE1", length = 20)
	public String getRsvValue1() {
		return this.rsvValue1;
	}

	public void setRsvValue1(String rsvValue1) {
		this.rsvValue1 = rsvValue1;
	}

	@Column(name = "RSV_VALUE2", length = 20)
	public String getRsvValue2() {
		return this.rsvValue2;
	}

	public void setRsvValue2(String rsvValue2) {
		this.rsvValue2 = rsvValue2;
	}

	@Column(name = "RSV_VALUE3", length = 20)
	public String getRsvValue3() {
		return this.rsvValue3;
	}

	public void setRsvValue3(String rsvValue3) {
		this.rsvValue3 = rsvValue3;
	}

	@Column(name = "RSV_VALUE4", length = 20)
	public String getRsvValue4() {
		return this.rsvValue4;
	}

	public void setRsvValue4(String rsvValue4) {
		this.rsvValue4 = rsvValue4;
	}

	@Column(name = "RSV_VALUE5", length = 20)
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

	@Temporal(TemporalType.TIMESTAMP)//@Temporal(TemporalType.DATE)
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

	@Temporal(TemporalType.TIMESTAMP)//@Temporal(TemporalType.DATE)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}