package com.sealinkin.bdef.po;
// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Bdef_Defarticle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BDEF_DEFARTICLE", uniqueConstraints = @UniqueConstraint(columnNames = {
		"OWNER_ARTICLE_NO", "ENTERPRISE_NO", "OWNER_NO" }))
public class Bdef_Defarticle implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String articleNo;
	private String ownerNo;
	private String ownerArticleNo;
	private String articleName;
	private String articleEname;
	private String articleOname;
	private String articleAlias;
	private String groupNo;
	private String batchId;
	private String articleIdentifier;
	private String unit;
	private Double qminOperatePacking;
	private Integer expiryDays;
	private Double alarmrate;
	private Double freezerate;
	private String abc;
	private Double unitVolumn;
	private Double unitWeight;
	private Double cumulativeVolumn;
	private String AOut;
	private String ruleFlag;
	private String spec;
	private Double sellPrice;
	private String status;
	private String createFlag;
	private String virtualFlag;
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
	private String codeType;
	private String divideBox;
	private String deliverType;
	private String deptNo;
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
	private String rsvAttr1;
	private String rsvAttr2;
	private String rsvAttr3;
	private String rsvAttr4;
	private String rsvAttr5;
	private String rsvAttr6;
	private String rsvAttr7;
	private String rsvAttr8;
	private String rsvAttr9;
	private String rsvAttr10;
	private String rsvAttr11;
	private String rsvAttr12;
	private String rsvAttr13;
	private String rsvAttr14;
	private String rsvAttr15;
	private String rsvAttr16;
	private String rsvAttr17;
	private String rsvAttr18;
	private String rsvAttr19;
	private String rsvAttr20;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String supplierNo;
	private String printFlag;
	private String lotType;
	private String enterpriseNo;
	private String barcode;
	private String operateType;
	private String rsvControl1;
	private String rsvControl2;
	private String rsvControl3;
	private String rsvControl4;
	private String rsvControl5;
	private String qminOperatePackingUnit;
	private Double unitPacking;
	
	// Constructors

	/** default constructor */
	public Bdef_Defarticle() {
	}

	/** minimal constructor */
	public Bdef_Defarticle(String ownerNo, String ownerArticleNo,
			String articleName, String groupNo, String batchId, String unit,
			Double qminOperatePacking, Integer expiryDays, Double alarmrate,
			Double freezerate, String abc, Double unitVolumn, Double unitWeight,
			Double cumulativeVolumn, String ruleFlag, String virtualFlag,
			String turnOverRule, Double checkExcess, Double umCheckExcess,
			Double pickExcess, Double divideExcess, String temperatureFlag,
			String measureMode, String scanFlag, String checkQtyFlag,
			Double checkQtyRate, String checkWeightFlag, Double checkWeightRate,
			String qcFlag, Double qcRate, String mixFlag, String doubleCheck,
			String codeType, String divideBox, String deliverType,
			String deptNo, String IStrategy, String OStrategy,
			String MStrategy, String riStrategy, String roStrategy,
			String fcStrategy, String rgstName, Date rgstDate, String operateType, Double unitPacking) {
		this.ownerNo = ownerNo;
		this.ownerArticleNo = ownerArticleNo;
		this.articleName = articleName;
		this.groupNo = groupNo;
		this.batchId = batchId;
		this.unit = unit;
		this.qminOperatePacking = qminOperatePacking;
		this.expiryDays = expiryDays;
		this.alarmrate = alarmrate;
		this.freezerate = freezerate;
		this.abc = abc;
		this.unitVolumn = unitVolumn;
		this.unitWeight = unitWeight;
		this.cumulativeVolumn = cumulativeVolumn;
		this.ruleFlag = ruleFlag;
		this.virtualFlag = virtualFlag;
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
		this.codeType = codeType;
		this.divideBox = divideBox;
		this.deliverType = deliverType;
		this.deptNo = deptNo;
		this.IStrategy = IStrategy;
		this.OStrategy = OStrategy;
		this.MStrategy = MStrategy;
		this.riStrategy = riStrategy;
		this.roStrategy = roStrategy;
		this.fcStrategy = fcStrategy;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.operateType = operateType;
		this.unitPacking = unitPacking;
	}

	/** full constructor */
	public Bdef_Defarticle(String ownerNo, String ownerArticleNo,
			String articleName, String articleEname, String articleOname,
			String articleAlias, String groupNo, String batchId,
			String articleIdentifier, String unit, Double qminOperatePacking,
			Integer expiryDays, Double alarmrate, Double freezerate, String abc,
			Double unitVolumn, Double unitWeight, Double cumulativeVolumn,
			String AOut, String ruleFlag, String spec, Double sellPrice,
			String status, String createFlag, String virtualFlag,
			String turnOverRule, Double checkExcess, Double umCheckExcess,
			Double pickExcess, Double divideExcess, String temperatureFlag,
			String measureMode, String scanFlag, String checkQtyFlag,
			Double checkQtyRate, String checkWeightFlag, Double checkWeightRate,
			String qcFlag, Double qcRate, String mixFlag, String doubleCheck,
			String codeType, String divideBox, String deliverType,
			String deptNo, String IStrategy, String OStrategy,
			String MStrategy, String riStrategy, String roStrategy,
			String fcStrategy, String rsvStrategy1, String rsvStrategy2,
			String rsvStrategy3, String rsvStrategy4, String rsvStrategy5,
			String rsvStrategy6, String rsvAttr1, String rsvAttr2,
			String rsvAttr3, String rsvAttr4, String rsvAttr5, String rsvAttr6,
			String rsvAttr7, String rsvAttr8, String rsvAttr9,
			String rsvAttr10, String rsvAttr11, String rsvAttr12,
			String rsvAttr13, String rsvAttr14, String rsvAttr15,
			String rsvAttr16, String rsvAttr17, String rsvAttr18,
			String rsvAttr19, String rsvAttr20, String rgstName, Date rgstDate,
			String updtName, Date updtDate, String supplierNo,
			String printFlag, String lotType, String enterpriseNo,
			String barcode,
			String operateType,String rsvControl1,String rsvControl2,
			String rsvControl3,String rsvControl4,String rsvControl5, String qminOperatePackingUnit,
			Double unitPacking) {
		this.ownerNo = ownerNo;
		this.ownerArticleNo = ownerArticleNo;
		this.articleName = articleName;
		this.articleEname = articleEname;
		this.articleOname = articleOname;
		this.articleAlias = articleAlias;
		this.groupNo = groupNo;
		this.batchId = batchId;
		this.articleIdentifier = articleIdentifier;
		this.unit = unit;
		this.qminOperatePacking = qminOperatePacking;
		this.expiryDays = expiryDays;
		this.alarmrate = alarmrate;
		this.freezerate = freezerate;
		this.abc = abc;
		this.unitVolumn = unitVolumn;
		this.unitWeight = unitWeight;
		this.cumulativeVolumn = cumulativeVolumn;
		this.AOut = AOut;
		this.ruleFlag = ruleFlag;
		this.spec = spec;
		this.sellPrice = sellPrice;
		this.status = status;
		this.createFlag = createFlag;
		this.virtualFlag = virtualFlag;
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
		this.codeType = codeType;
		this.divideBox = divideBox;
		this.deliverType = deliverType;
		this.deptNo = deptNo;
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
		this.rsvAttr1 = rsvAttr1;
		this.rsvAttr2 = rsvAttr2;
		this.rsvAttr3 = rsvAttr3;
		this.rsvAttr4 = rsvAttr4;
		this.rsvAttr5 = rsvAttr5;
		this.rsvAttr6 = rsvAttr6;
		this.rsvAttr7 = rsvAttr7;
		this.rsvAttr8 = rsvAttr8;
		this.rsvAttr9 = rsvAttr9;
		this.rsvAttr10 = rsvAttr10;
		this.rsvAttr11 = rsvAttr11;
		this.rsvAttr12 = rsvAttr12;
		this.rsvAttr13 = rsvAttr13;
		this.rsvAttr14 = rsvAttr14;
		this.rsvAttr15 = rsvAttr15;
		this.rsvAttr16 = rsvAttr16;
		this.rsvAttr17 = rsvAttr17;
		this.rsvAttr18 = rsvAttr18;
		this.rsvAttr19 = rsvAttr19;
		this.rsvAttr20 = rsvAttr20;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.supplierNo = supplierNo;
		this.printFlag = printFlag;
		this.lotType = lotType;
		this.enterpriseNo = enterpriseNo;
		this.barcode = barcode;
		this.operateType = operateType;
		this.rsvControl1 = rsvControl1;
		this.rsvControl2 = rsvControl2;
		this.rsvControl3 = rsvControl3;
		this.rsvControl4 = rsvControl4;
		this.rsvControl5 = rsvControl5;
		this.qminOperatePackingUnit = qminOperatePackingUnit;
		this.unitPacking = unitPacking;
	}

	// Property accessors
	@Id
	@Column(name = "ARTICLE_NO", unique = true, nullable = false, length = 15)
	public String getArticleNo() {
		return this.articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "OWNER_ARTICLE_NO", nullable = false, length = 25)
	public String getOwnerArticleNo() {
		return this.ownerArticleNo;
	}

	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}

	@Column(name = "ARTICLE_NAME", nullable = false, length = 120)
	public String getArticleName() {
		return this.articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	@Column(name = "ARTICLE_ENAME", length = 120)
	public String getArticleEname() {
		return this.articleEname;
	}

	public void setArticleEname(String articleEname) {
		this.articleEname = articleEname;
	}

	@Column(name = "ARTICLE_ONAME", length = 120)
	public String getArticleOname() {
		return this.articleOname;
	}

	public void setArticleOname(String articleOname) {
		this.articleOname = articleOname;
	}

	@Column(name = "ARTICLE_ALIAS", length = 60)
	public String getArticleAlias() {
		return this.articleAlias;
	}

	public void setArticleAlias(String articleAlias) {
		this.articleAlias = articleAlias;
	}

	@Column(name = "GROUP_NO", nullable = false, length = 20)
	public String getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	@Column(name = "BATCH_ID", nullable = false, length = 2)
	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	@Column(name = "ARTICLE_IDENTIFIER", length = 50)
	public String getArticleIdentifier() {
		return this.articleIdentifier;
	}

	public void setArticleIdentifier(String articleIdentifier) {
		this.articleIdentifier = articleIdentifier;
	}

	@Column(name = "UNIT", nullable = false, length = 10)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	
	@Column(name = "UNIT_PACKING", nullable = false, precision = 18, scale = 5)
	public Double getUnitPacking() {
		return this.unitPacking;
	}

	public void setUnitPacking(Double unitPacking) {
		this.unitPacking = unitPacking;
	}
	
	@Column(name = "QMIN_OPERATE_PACKING", nullable = false, precision = 9, scale = 5)
	public Double getQminOperatePacking() {
		return this.qminOperatePacking;
	}

	public void setQminOperatePacking(Double qminOperatePacking) {
		this.qminOperatePacking = qminOperatePacking;
	}

	@Column(name = "EXPIRY_DAYS", nullable = false, precision = 5, scale = 0)
	public Integer getExpiryDays() {
		return this.expiryDays;
	}

	public void setExpiryDays(Integer expiryDays) {
		this.expiryDays = expiryDays;
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

	@Column(name = "ABC", nullable = false, length = 1)
	public String getAbc() {
		return this.abc;
	}

	public void setAbc(String abc) {
		this.abc = abc;
	}

	@Column(name = "UNIT_VOLUMN", nullable = false, precision = 18, scale = 5)
	public Double getUnitVolumn() {
		return this.unitVolumn;
	}

	public void setUnitVolumn(Double unitVolumn) {
		this.unitVolumn = unitVolumn;
	}

	@Column(name = "UNIT_WEIGHT", nullable = false, precision = 17, scale = 5)
	public Double getUnitWeight() {
		return this.unitWeight;
	}

	public void setUnitWeight(Double unitWeight) {
		this.unitWeight = unitWeight;
	}

	@Column(name = "CUMULATIVE_VOLUMN", nullable = false, precision = 18, scale = 5)
	public Double getCumulativeVolumn() {
		return this.cumulativeVolumn;
	}

	public void setCumulativeVolumn(Double cumulativeVolumn) {
		this.cumulativeVolumn = cumulativeVolumn;
	}

	@Column(name = "A_OUT", length = 1)
	public String getAOut() {
		return this.AOut;
	}

	public void setAOut(String AOut) {
		this.AOut = AOut;
	}

	@Column(name = "RULE_FLAG", nullable = false, length = 1)
	public String getRuleFlag() {
		return this.ruleFlag;
	}

	public void setRuleFlag(String ruleFlag) {
		this.ruleFlag = ruleFlag;
	}

	@Column(name = "SPEC", length = 50)
	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	@Column(name = "SELL_PRICE", scale = 5)
	public Double getSellPrice() {
		return this.sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CREATE_FLAG", length = 1)
	public String getCreateFlag() {
		return this.createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

	@Column(name = "VIRTUAL_FLAG", nullable = false, length = 1)
	public String getVirtualFlag() {
		return this.virtualFlag;
	}

	public void setVirtualFlag(String virtualFlag) {
		this.virtualFlag = virtualFlag;
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

	@Column(name = "CODE_TYPE", nullable = false, length = 1)
	public String getCodeType() {
		return this.codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	@Column(name = "DIVIDE_BOX", nullable = false, length = 50)
	public String getDivideBox() {
		return this.divideBox;
	}

	public void setDivideBox(String divideBox) {
		this.divideBox = divideBox;
	}

	@Column(name = "DELIVER_TYPE", nullable = false, length = 1)
	public String getDeliverType() {
		return this.deliverType;
	}

	public void setDeliverType(String deliverType) {
		this.deliverType = deliverType;
	}

	@Column(name = "DEPT_NO", nullable = false, length = 10)
	public String getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
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

	@Column(name = "RSV_ATTR1", length = 50)
	public String getRsvAttr1() {
		return this.rsvAttr1;
	}

	public void setRsvAttr1(String rsvAttr1) {
		this.rsvAttr1 = rsvAttr1;
	}

	@Column(name = "RSV_ATTR2", length = 50)
	public String getRsvAttr2() {
		return this.rsvAttr2;
	}

	public void setRsvAttr2(String rsvAttr2) {
		this.rsvAttr2 = rsvAttr2;
	}

	@Column(name = "RSV_ATTR3", length = 50)
	public String getRsvAttr3() {
		return this.rsvAttr3;
	}

	public void setRsvAttr3(String rsvAttr3) {
		this.rsvAttr3 = rsvAttr3;
	}

	@Column(name = "RSV_ATTR4", length = 50)
	public String getRsvAttr4() {
		return this.rsvAttr4;
	}

	public void setRsvAttr4(String rsvAttr4) {
		this.rsvAttr4 = rsvAttr4;
	}

	@Column(name = "RSV_ATTR5", length = 50)
	public String getRsvAttr5() {
		return this.rsvAttr5;
	}

	public void setRsvAttr5(String rsvAttr5) {
		this.rsvAttr5 = rsvAttr5;
	}

	@Column(name = "RSV_ATTR6", length = 50)
	public String getRsvAttr6() {
		return this.rsvAttr6;
	}

	public void setRsvAttr6(String rsvAttr6) {
		this.rsvAttr6 = rsvAttr6;
	}

	@Column(name = "RSV_ATTR7", length = 50)
	public String getRsvAttr7() {
		return this.rsvAttr7;
	}

	public void setRsvAttr7(String rsvAttr7) {
		this.rsvAttr7 = rsvAttr7;
	}

	@Column(name = "RSV_ATTR8", length = 50)
	public String getRsvAttr8() {
		return this.rsvAttr8;
	}

	public void setRsvAttr8(String rsvAttr8) {
		this.rsvAttr8 = rsvAttr8;
	}

	@Column(name = "RSV_ATTR9", length = 50)
	public String getRsvAttr9() {
		return this.rsvAttr9;
	}

	public void setRsvAttr9(String rsvAttr9) {
		this.rsvAttr9 = rsvAttr9;
	}

	@Column(name = "RSV_ATTR10", length = 50)
	public String getRsvAttr10() {
		return this.rsvAttr10;
	}

	public void setRsvAttr10(String rsvAttr10) {
		this.rsvAttr10 = rsvAttr10;
	}

	@Column(name = "RSV_ATTR11", length = 50)
	public String getRsvAttr11() {
		return this.rsvAttr11;
	}

	public void setRsvAttr11(String rsvAttr11) {
		this.rsvAttr11 = rsvAttr11;
	}

	@Column(name = "RSV_ATTR12", length = 50)
	public String getRsvAttr12() {
		return this.rsvAttr12;
	}

	public void setRsvAttr12(String rsvAttr12) {
		this.rsvAttr12 = rsvAttr12;
	}

	@Column(name = "RSV_ATTR13", length = 50)
	public String getRsvAttr13() {
		return this.rsvAttr13;
	}

	public void setRsvAttr13(String rsvAttr13) {
		this.rsvAttr13 = rsvAttr13;
	}

	@Column(name = "RSV_ATTR14", length = 50)
	public String getRsvAttr14() {
		return this.rsvAttr14;
	}

	public void setRsvAttr14(String rsvAttr14) {
		this.rsvAttr14 = rsvAttr14;
	}

	@Column(name = "RSV_ATTR15", length = 50)
	public String getRsvAttr15() {
		return this.rsvAttr15;
	}

	public void setRsvAttr15(String rsvAttr15) {
		this.rsvAttr15 = rsvAttr15;
	}

	@Column(name = "RSV_ATTR16", length = 50)
	public String getRsvAttr16() {
		return this.rsvAttr16;
	}

	public void setRsvAttr16(String rsvAttr16) {
		this.rsvAttr16 = rsvAttr16;
	}

	@Column(name = "RSV_ATTR17", length = 50)
	public String getRsvAttr17() {
		return this.rsvAttr17;
	}

	public void setRsvAttr17(String rsvAttr17) {
		this.rsvAttr17 = rsvAttr17;
	}

	@Column(name = "RSV_ATTR18", length = 50)
	public String getRsvAttr18() {
		return this.rsvAttr18;
	}

	public void setRsvAttr18(String rsvAttr18) {
		this.rsvAttr18 = rsvAttr18;
	}

	@Column(name = "RSV_ATTR19", length = 50)
	public String getRsvAttr19() {
		return this.rsvAttr19;
	}

	public void setRsvAttr19(String rsvAttr19) {
		this.rsvAttr19 = rsvAttr19;
	}

	@Column(name = "RSV_ATTR20", length = 50)
	public String getRsvAttr20() {
		return this.rsvAttr20;
	}

	public void setRsvAttr20(String rsvAttr20) {
		this.rsvAttr20 = rsvAttr20;
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

	@Column(name = "SUPPLIER_NO", length = 10)
	public String getSupplierNo() {
		return this.supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	@Column(name = "PRINT_FLAG", length = 1)
	public String getPrintFlag() {
		return this.printFlag;
	}

	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

	@Column(name = "LOT_TYPE", length = 1)
	public String getLotType() {
		return this.lotType;
	}

	public void setLotType(String lotType) {
		this.lotType = lotType;
	}

	@Column(name = "ENTERPRISE_NO", length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "BARCODE", length = 50)
	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	@Column(name = "OPERATE_TYPE", nullable = false, length = 1)
	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Column(name = "RSV_CONTROL1", length = 50)
	public String getRsvControl1() {
		return rsvControl1;
	}

	public void setRsvControl1(String rsvControl1) {
		this.rsvControl1 = rsvControl1;
	}
	
	@Column(name = "RSV_CONTROL2", length = 50)
	public String getRsvControl2() {
		return rsvControl2;
	}

	public void setRsvControl2(String rsvControl2) {
		this.rsvControl2 = rsvControl2;
	}
	
	@Column(name = "RSV_CONTROL3", length = 50)
	public String getRsvControl3() {
		return rsvControl3;
	}

	public void setRsvControl3(String rsvControl3) {
		this.rsvControl3 = rsvControl3;
	}

	@Column(name = "RSV_CONTROL4", length = 50)
	public String getRsvControl4() {
		return rsvControl4;
	}
	public void setRsvControl4(String rsvControl4) {
		this.rsvControl4 = rsvControl4;
	}
	
	@Column(name = "RSV_CONTROL5", length = 50)
	public String getRsvControl5() {
		return rsvControl5;
	}

	public void setRsvControl5(String rsvControl5) {
		this.rsvControl5 = rsvControl5;
	}

	@Column(name = "QMIN_OPERATE_PACKING_UNIT", length = 10)
	public String getQminOperatePackingUnit() {
		return qminOperatePackingUnit;
	}

	public void setQminOperatePackingUnit(String qminOperatePackingUnit) {
		this.qminOperatePackingUnit = qminOperatePackingUnit;
	}

	

	
	
	
	
	
	
}