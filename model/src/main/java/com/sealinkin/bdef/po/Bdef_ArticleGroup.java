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
 * BdefArticleGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BDEF_ARTICLE_GROUP")
public class Bdef_ArticleGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bdef_ArticleGroupId id;
	private String groupName;
	private String MGroupNo;
	private String MGroupName;
	private String LGroupNo;
	private String LGroupName;
	private String batchId;
	private String status;
	private String createFlag;
	private Double alarmrate;
	private Double freezerate;
	private String turnOverRule;
	private Double checkExcess;
	private Double umCheckExcess;
	private Double pickExcess;
	private Double divideExcess;
	private String temperatureFlag;
	private String measureMode;
	private String scanFlag;
	private String checkQtyFlag;
	private Double checkQtyRate;
	private String checkWeightFlag;
	private Double checkWeightRate;
	private String qcFlag;
	private Double qcRate;
	private String mixFlag;
	private String doubleCheck;
	private String IStrategy;
	private String OStrategy;
	private String MStrategy;
	private String riStrategy;
	private String roStrategy;
	private String fcStrategy;
	private String rsvStrategy1;
	private String rsvStrategy2;
	private String rsvStrategy3;
	private String rsvStrategy4;
	private String rsvStrategy5;
	private String rsvStrategy6;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String printFlag;
	// Constructors

	/** default constructor */
	public Bdef_ArticleGroup() {
	}

	/** minimal constructor */
	public Bdef_ArticleGroup(Bdef_ArticleGroupId id, String groupName,
			String batchId, String status, String createFlag, Double alarmrate,
			Double freezerate, String turnOverRule, Double checkExcess,
			Double umCheckExcess, Double pickExcess, Double divideExcess,
			String temperatureFlag, String measureMode, String scanFlag,
			String checkQtyFlag, Double checkQtyRate, String checkWeightFlag,
			Double checkWeightRate, String qcFlag, Double qcRate, String mixFlag,
			String doubleCheck, String IStrategy, String OStrategy,
			String MStrategy, String riStrategy, String roStrategy,
			String fcStrategy, String rgstName, Date rgstDate,String printFlag) {
		this.id = id;
		this.groupName = groupName;
		this.batchId = batchId;
		this.status = status;
		this.createFlag = createFlag;
		this.alarmrate = alarmrate;
		this.freezerate = freezerate;
		this.turnOverRule = turnOverRule;
		this.checkExcess = checkExcess;
		this.umCheckExcess = umCheckExcess;
		this.pickExcess = pickExcess;
		this.divideExcess = divideExcess;
		this.temperatureFlag = temperatureFlag;
		this.measureMode = measureMode;
		this.scanFlag = scanFlag;
		this.checkQtyFlag = checkQtyFlag;
		this.checkQtyRate = checkQtyRate;
		this.checkWeightFlag = checkWeightFlag;
		this.checkWeightRate = checkWeightRate;
		this.qcFlag = qcFlag;
		this.qcRate = qcRate;
		this.mixFlag = mixFlag;
		this.doubleCheck = doubleCheck;
		this.IStrategy = IStrategy;
		this.OStrategy = OStrategy;
		this.MStrategy = MStrategy;
		this.riStrategy = riStrategy;
		this.roStrategy = roStrategy;
		this.fcStrategy = fcStrategy;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.printFlag = printFlag;
	}

	/** full constructor */
	public Bdef_ArticleGroup(Bdef_ArticleGroupId id, String groupName,
			String MGroupNo, String MGroupName, String LGroupNo,
			String LGroupName, String batchId, String status,
			String createFlag, Double alarmrate, Double freezerate,
			String turnOverRule, Double checkExcess, Double umCheckExcess,
			Double pickExcess, Double divideExcess, String temperatureFlag,
			String measureMode, String scanFlag, String checkQtyFlag,
			Double checkQtyRate, String checkWeightFlag, Double checkWeightRate,
			String qcFlag, Double qcRate, String mixFlag, String doubleCheck,
			String IStrategy, String OStrategy, String MStrategy,
			String riStrategy, String roStrategy, String fcStrategy,
			String rsvStrategy1, String rsvStrategy2, String rsvStrategy3,
			String rsvStrategy4, String rsvStrategy5, String rsvStrategy6,
			String rgstName, Date rgstDate, String updtName, Date updtDate,String printFlag) {
		this.id = id;
		this.groupName = groupName;
		this.MGroupNo = MGroupNo;
		this.MGroupName = MGroupName;
		this.LGroupNo = LGroupNo;
		this.LGroupName = LGroupName;
		this.batchId = batchId;
		this.status = status;
		this.createFlag = createFlag;
		this.alarmrate = alarmrate;
		this.freezerate = freezerate;
		this.turnOverRule = turnOverRule;
		this.checkExcess = checkExcess;
		this.umCheckExcess = umCheckExcess;
		this.pickExcess = pickExcess;
		this.divideExcess = divideExcess;
		this.temperatureFlag = temperatureFlag;
		this.measureMode = measureMode;
		this.scanFlag = scanFlag;
		this.checkQtyFlag = checkQtyFlag;
		this.checkQtyRate = checkQtyRate;
		this.checkWeightFlag = checkWeightFlag;
		this.checkWeightRate = checkWeightRate;
		this.qcFlag = qcFlag;
		this.qcRate = qcRate;
		this.mixFlag = mixFlag;
		this.doubleCheck = doubleCheck;
		this.IStrategy = IStrategy;
		this.OStrategy = OStrategy;
		this.MStrategy = MStrategy;
		this.riStrategy = riStrategy;
		this.roStrategy = roStrategy;
		this.fcStrategy = fcStrategy;
		this.rsvStrategy1 = rsvStrategy1;
		this.rsvStrategy2 = rsvStrategy2;
		this.rsvStrategy3 = rsvStrategy3;
		this.rsvStrategy4 = rsvStrategy4;
		this.rsvStrategy5 = rsvStrategy5;
		this.rsvStrategy6 = rsvStrategy6;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.printFlag = printFlag;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "groupNo", column = @Column(name = "GROUP_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "groupLevel", column = @Column(name = "GROUP_LEVEL", nullable = false, length = 1)) })
	public Bdef_ArticleGroupId getId() {
		return this.id;
	}

	public void setId(Bdef_ArticleGroupId id) {
		this.id = id;
	}

	@Column(name = "GROUP_NAME", nullable = false, length = 45)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "M_GROUP_NO", length = 20)
	public String getMGroupNo() {
		return this.MGroupNo;
	}

	public void setMGroupNo(String MGroupNo) {
		this.MGroupNo = MGroupNo;
	}

	@Column(name = "M_GROUP_NAME", length = 45)
	public String getMGroupName() {
		return this.MGroupName;
	}

	public void setMGroupName(String MGroupName) {
		this.MGroupName = MGroupName;
	}

	@Column(name = "L_GROUP_NO", length = 20)
	public String getLGroupNo() {
		return this.LGroupNo;
	}

	public void setLGroupNo(String LGroupNo) {
		this.LGroupNo = LGroupNo;
	}

	@Column(name = "L_GROUP_NAME", length = 45)
	public String getLGroupName() {
		return this.LGroupName;
	}

	public void setLGroupName(String LGroupName) {
		this.LGroupName = LGroupName;
	}

	@Column(name = "BATCH_ID", nullable = false, length = 2)
	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CREATE_FLAG", nullable = false, length = 1)
	public String getCreateFlag() {
		return this.createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

	@Column(name = "ALARMRATE", nullable = false, precision = 3, scale = 0)
	public Double getAlarmrate() {
		return this.alarmrate;
	}

	public void setAlarmrate(Double alarmrate) {
		this.alarmrate = alarmrate;
	}

	@Column(name = "FREEZERATE", nullable = false, precision = 3, scale = 0)
	public Double getFreezerate() {
		return this.freezerate;
	}

	public void setFreezerate(Double freezerate) {
		this.freezerate = freezerate;
	}

	@Column(name = "TURN_OVER_RULE", nullable = false, length = 4)
	public String getTurnOverRule() {
		return this.turnOverRule;
	}

	public void setTurnOverRule(String turnOverRule) {
		this.turnOverRule = turnOverRule;
	}

	@Column(name = "CHECK_EXCESS", nullable = false, precision = 3, scale = 0)
	public Double getCheckExcess() {
		return this.checkExcess;
	}

	public void setCheckExcess(Double checkExcess) {
		this.checkExcess = checkExcess;
	}

	@Column(name = "UM_CHECK_EXCESS", nullable = false, precision = 3, scale = 0)
	public Double getUmCheckExcess() {
		return this.umCheckExcess;
	}

	public void setUmCheckExcess(Double umCheckExcess) {
		this.umCheckExcess = umCheckExcess;
	}

	@Column(name = "PICK_EXCESS", nullable = false, precision = 3, scale = 0)
	public Double getPickExcess() {
		return this.pickExcess;
	}

	public void setPickExcess(Double pickExcess) {
		this.pickExcess = pickExcess;
	}

	@Column(name = "DIVIDE_EXCESS", nullable = false, precision = 3, scale = 0)
	public Double getDivideExcess() {
		return this.divideExcess;
	}

	public void setDivideExcess(Double divideExcess) {
		this.divideExcess = divideExcess;
	}

	@Column(name = "TEMPERATURE_FLAG", nullable = false, length = 1)
	public String getTemperatureFlag() {
		return this.temperatureFlag;
	}

	public void setTemperatureFlag(String temperatureFlag) {
		this.temperatureFlag = temperatureFlag;
	}

	@Column(name = "MEASURE_MODE", nullable = false, length = 1)
	public String getMeasureMode() {
		return this.measureMode;
	}

	public void setMeasureMode(String measureMode) {
		this.measureMode = measureMode;
	}

	@Column(name = "SCAN_FLAG", nullable = false, length = 1)
	public String getScanFlag() {
		return this.scanFlag;
	}

	public void setScanFlag(String scanFlag) {
		this.scanFlag = scanFlag;
	}

	@Column(name = "CHECK_QTY_FLAG", nullable = false, length = 1)
	public String getCheckQtyFlag() {
		return this.checkQtyFlag;
	}

	public void setCheckQtyFlag(String checkQtyFlag) {
		this.checkQtyFlag = checkQtyFlag;
	}

	@Column(name = "CHECK_QTY_RATE", nullable = false, precision = 3, scale = 0)
	public Double getCheckQtyRate() {
		return this.checkQtyRate;
	}

	public void setCheckQtyRate(Double checkQtyRate) {
		this.checkQtyRate = checkQtyRate;
	}

	@Column(name = "CHECK_WEIGHT_FLAG", nullable = false, length = 1)
	public String getCheckWeightFlag() {
		return this.checkWeightFlag;
	}

	public void setCheckWeightFlag(String checkWeightFlag) {
		this.checkWeightFlag = checkWeightFlag;
	}

	@Column(name = "CHECK_WEIGHT_RATE", nullable = false, precision = 3, scale = 0)
	public Double getCheckWeightRate() {
		return this.checkWeightRate;
	}

	public void setCheckWeightRate(Double checkWeightRate) {
		this.checkWeightRate = checkWeightRate;
	}

	@Column(name = "QC_FLAG", nullable = false, length = 1)
	public String getQcFlag() {
		return this.qcFlag;
	}

	public void setQcFlag(String qcFlag) {
		this.qcFlag = qcFlag;
	}

	@Column(name = "QC_RATE", nullable = false, precision = 3, scale = 0)
	public Double getQcRate() {
		return this.qcRate;
	}

	public void setQcRate(Double qcRate) {
		this.qcRate = qcRate;
	}

	@Column(name = "MIX_FLAG", nullable = false, length = 1)
	public String getMixFlag() {
		return this.mixFlag;
	}

	public void setMixFlag(String mixFlag) {
		this.mixFlag = mixFlag;
	}

	@Column(name = "DOUBLE_CHECK", nullable = false, length = 1)
	public String getDoubleCheck() {
		return this.doubleCheck;
	}

	public void setDoubleCheck(String doubleCheck) {
		this.doubleCheck = doubleCheck;
	}

	@Column(name = "I_STRATEGY", nullable = false, length = 2)
	public String getIStrategy() {
		return this.IStrategy;
	}

	public void setIStrategy(String IStrategy) {
		this.IStrategy = IStrategy;
	}

	@Column(name = "O_STRATEGY", nullable = false, length = 2)
	public String getOStrategy() {
		return this.OStrategy;
	}

	public void setOStrategy(String OStrategy) {
		this.OStrategy = OStrategy;
	}

	@Column(name = "M_STRATEGY", nullable = false, length = 2)
	public String getMStrategy() {
		return this.MStrategy;
	}

	public void setMStrategy(String MStrategy) {
		this.MStrategy = MStrategy;
	}

	@Column(name = "RI_STRATEGY", nullable = false, length = 2)
	public String getRiStrategy() {
		return this.riStrategy;
	}

	public void setRiStrategy(String riStrategy) {
		this.riStrategy = riStrategy;
	}

	@Column(name = "RO_STRATEGY", nullable = false, length = 2)
	public String getRoStrategy() {
		return this.roStrategy;
	}

	public void setRoStrategy(String roStrategy) {
		this.roStrategy = roStrategy;
	}

	@Column(name = "FC_STRATEGY", nullable = false, length = 2)
	public String getFcStrategy() {
		return this.fcStrategy;
	}

	public void setFcStrategy(String fcStrategy) {
		this.fcStrategy = fcStrategy;
	}

	@Column(name = "RSV_STRATEGY1", length = 2)
	public String getRsvStrategy1() {
		return this.rsvStrategy1;
	}

	public void setRsvStrategy1(String rsvStrategy1) {
		this.rsvStrategy1 = rsvStrategy1;
	}

	@Column(name = "RSV_STRATEGY2", length = 2)
	public String getRsvStrategy2() {
		return this.rsvStrategy2;
	}

	public void setRsvStrategy2(String rsvStrategy2) {
		this.rsvStrategy2 = rsvStrategy2;
	}

	@Column(name = "RSV_STRATEGY3", length = 2)
	public String getRsvStrategy3() {
		return this.rsvStrategy3;
	}

	public void setRsvStrategy3(String rsvStrategy3) {
		this.rsvStrategy3 = rsvStrategy3;
	}

	@Column(name = "RSV_STRATEGY4", length = 2)
	public String getRsvStrategy4() {
		return this.rsvStrategy4;
	}

	public void setRsvStrategy4(String rsvStrategy4) {
		this.rsvStrategy4 = rsvStrategy4;
	}

	@Column(name = "RSV_STRATEGY5", length = 2)
	public String getRsvStrategy5() {
		return this.rsvStrategy5;
	}

	public void setRsvStrategy5(String rsvStrategy5) {
		this.rsvStrategy5 = rsvStrategy5;
	}

	@Column(name = "RSV_STRATEGY6", length = 2)
	public String getRsvStrategy6() {
		return this.rsvStrategy6;
	}

	public void setRsvStrategy6(String rsvStrategy6) {
		this.rsvStrategy6 = rsvStrategy6;
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

	@Column(name = "UPDT_NAME", length = 20)
	public String getUpdtName() {
		return this.updtName;
	}

	public void setUpdtName(String updtName) {
		this.updtName = updtName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	@Column(name = "PRINT_FLAG", length = 1)
	public String getPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

}