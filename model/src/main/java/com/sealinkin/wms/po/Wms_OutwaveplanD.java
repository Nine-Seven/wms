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
 * WmsOutwaveplanD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_OUTWAVEPLAN_D")
public class Wms_OutwaveplanD implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Wms_OutwaveplanDId id;
	private String batchRuleName;
	private Integer seqOrder;
	private String status;
	private String batchComputeType;
	private String batchCompute;
	private Short taskOrder;
	private Short minOrder;
	private String orderSource;
	private Short repeatTimes;
	private String deliverType;
	private String deliverAddress;
	private String lineFlag;
	private String shipperNo;
	private Short skuLimmit;
	private Long waitTimes;
	private Long intervalTimes;
	private String CLimmit;
	private String areaLimmit;
	private String itemTypeFlag;
	private String printEnvoice;
	private String printWaybill;
	private String printPacklist;
	private Short custLogiboxRuleId;
	private Short divideLogiboxRuleId;
	private Short taskRuleId;
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
	private BigDecimal skucount;
	private String printValue1;
	private String printValue2;
	private String lastPickFlag;
	private Integer pickStrategyId;
	private String skuCountMode;
	private String CDivideFlag;
	private String BDivideFlag;
	private String areaLimmitValue;
	private String areaAllow;
	private String ruleFlag;

	// Constructors

	/** default constructor */
	public Wms_OutwaveplanD() {
	}

	/** minimal constructor */
	public Wms_OutwaveplanD(Wms_OutwaveplanDId id, Integer seqOrder,
			String status, String batchCompute, Short taskOrder,
			Short minOrder, String orderSource, Short repeatTimes,
			String deliverType, String deliverAddress, String lineFlag,
			String shipperNo, Short skuLimmit, Long waitTimes,
			Long intervalTimes, String CLimmit, String areaLimmit,
			String itemTypeFlag, String printEnvoice, String printWaybill,
			String printPacklist, Short custLogiboxRuleId,
			Short divideLogiboxRuleId, Short taskRuleId, String rgstName,
			Date rgstDate, BigDecimal skucount, Integer pickStrategyId,
			String skuCountMode, String CDivideFlag, String BDivideFlag) {
		this.id = id;
		this.seqOrder = seqOrder;
		this.status = status;
		this.batchCompute = batchCompute;
		this.taskOrder = taskOrder;
		this.minOrder = minOrder;
		this.orderSource = orderSource;
		this.repeatTimes = repeatTimes;
		this.deliverType = deliverType;
		this.deliverAddress = deliverAddress;
		this.lineFlag = lineFlag;
		this.shipperNo = shipperNo;
		this.skuLimmit = skuLimmit;
		this.waitTimes = waitTimes;
		this.intervalTimes = intervalTimes;
		this.CLimmit = CLimmit;
		this.areaLimmit = areaLimmit;
		this.itemTypeFlag = itemTypeFlag;
		this.printEnvoice = printEnvoice;
		this.printWaybill = printWaybill;
		this.printPacklist = printPacklist;
		this.custLogiboxRuleId = custLogiboxRuleId;
		this.divideLogiboxRuleId = divideLogiboxRuleId;
		this.taskRuleId = taskRuleId;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.skucount = skucount;
		this.pickStrategyId = pickStrategyId;
		this.skuCountMode = skuCountMode;
		this.CDivideFlag = CDivideFlag;
		this.BDivideFlag = BDivideFlag;
	}

	/** full constructor */
	public Wms_OutwaveplanD(Wms_OutwaveplanDId id, String batchRuleName,
			Integer seqOrder, String status, String batchComputeType,
			String batchCompute, Short taskOrder, Short minOrder,
			String orderSource, Short repeatTimes, String deliverType,
			String deliverAddress, String lineFlag, String shipperNo,
			Short skuLimmit, Long waitTimes, Long intervalTimes,
			String CLimmit, String areaLimmit, String itemTypeFlag,
			String printEnvoice, String printWaybill, String printPacklist,
			Short custLogiboxRuleId, Short divideLogiboxRuleId,
			Short taskRuleId, String rsvControl1, String rsvControl2,
			String rsvControl3, String rsvControl4, String rsvControl5,
			String rsvValue1, String rsvValue2, String rsvValue3,
			String rsvValue4, String rsvValue5, String rgstName, Date rgstDate,
			String updtName, Date updtDate, BigDecimal skucount,
			String printValue1, String printValue2, String lastPickFlag,
			Integer pickStrategyId, String skuCountMode, String CDivideFlag,
			String BDivideFlag, String areaLimmitValue, String areaAllow,
			String ruleFlag) {
		this.id = id;
		this.batchRuleName = batchRuleName;
		this.seqOrder = seqOrder;
		this.status = status;
		this.batchComputeType = batchComputeType;
		this.batchCompute = batchCompute;
		this.taskOrder = taskOrder;
		this.minOrder = minOrder;
		this.orderSource = orderSource;
		this.repeatTimes = repeatTimes;
		this.deliverType = deliverType;
		this.deliverAddress = deliverAddress;
		this.lineFlag = lineFlag;
		this.shipperNo = shipperNo;
		this.skuLimmit = skuLimmit;
		this.waitTimes = waitTimes;
		this.intervalTimes = intervalTimes;
		this.CLimmit = CLimmit;
		this.areaLimmit = areaLimmit;
		this.itemTypeFlag = itemTypeFlag;
		this.printEnvoice = printEnvoice;
		this.printWaybill = printWaybill;
		this.printPacklist = printPacklist;
		this.custLogiboxRuleId = custLogiboxRuleId;
		this.divideLogiboxRuleId = divideLogiboxRuleId;
		this.taskRuleId = taskRuleId;
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
		this.skucount = skucount;
		this.printValue1 = printValue1;
		this.printValue2 = printValue2;
		this.lastPickFlag = lastPickFlag;
		this.pickStrategyId = pickStrategyId;
		this.skuCountMode = skuCountMode;
		this.CDivideFlag = CDivideFlag;
		this.BDivideFlag = BDivideFlag;
		this.areaLimmitValue = areaLimmitValue;
		this.areaAllow = areaAllow;
		this.ruleFlag = ruleFlag;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "batchStrategyId", column = @Column(name = "BATCH_STRATEGY_ID", nullable = false, precision = 5, scale = 0)),
			@AttributeOverride(name = "batchRuleId", column = @Column(name = "BATCH_RULE_ID", nullable = false, length = 5)) })
	public Wms_OutwaveplanDId getId() {
		return this.id;
	}

	public void setId(Wms_OutwaveplanDId id) {
		this.id = id;
	}

	@Column(name = "BATCH_RULE_NAME", length = 50)
	public String getBatchRuleName() {
		return this.batchRuleName;
	}

	public void setBatchRuleName(String batchRuleName) {
		this.batchRuleName = batchRuleName;
	}

	@Column(name = "SEQ_ORDER", nullable = false, precision = 8, scale = 0)
	public Integer getSeqOrder() {
		return this.seqOrder;
	}

	public void setSeqOrder(Integer seqOrder) {
		this.seqOrder = seqOrder;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "BATCH_COMPUTE_TYPE", length = 1)
	public String getBatchComputeType() {
		return this.batchComputeType;
	}

	public void setBatchComputeType(String batchComputeType) {
		this.batchComputeType = batchComputeType;
	}

	@Column(name = "BATCH_COMPUTE", nullable = false, length = 1)
	public String getBatchCompute() {
		return this.batchCompute;
	}

	public void setBatchCompute(String batchCompute) {
		this.batchCompute = batchCompute;
	}

	@Column(name = "TASK_ORDER", nullable = false, precision = 4, scale = 0)
	public Short getTaskOrder() {
		return this.taskOrder;
	}

	public void setTaskOrder(Short taskOrder) {
		this.taskOrder = taskOrder;
	}

	@Column(name = "MIN_ORDER", nullable = false, precision = 4, scale = 0)
	public Short getMinOrder() {
		return this.minOrder;
	}

	public void setMinOrder(Short minOrder) {
		this.minOrder = minOrder;
	}

	@Column(name = "ORDER_SOURCE", nullable = false, length = 40)
	public String getOrderSource() {
		return this.orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	@Column(name = "REPEAT_TIMES", nullable = false, precision = 4, scale = 0)
	public Short getRepeatTimes() {
		return this.repeatTimes;
	}

	public void setRepeatTimes(Short repeatTimes) {
		this.repeatTimes = repeatTimes;
	}

	@Column(name = "DELIVER_TYPE", nullable = false, length = 3)
	public String getDeliverType() {
		return this.deliverType;
	}

	public void setDeliverType(String deliverType) {
		this.deliverType = deliverType;
	}

	@Column(name = "DELIVER_ADDRESS", nullable = false, length = 10)
	public String getDeliverAddress() {
		return this.deliverAddress;
	}

	public void setDeliverAddress(String deliverAddress) {
		this.deliverAddress = deliverAddress;
	}

	@Column(name = "LINE_FLAG", nullable = false, length = 1)
	public String getLineFlag() {
		return this.lineFlag;
	}

	public void setLineFlag(String lineFlag) {
		this.lineFlag = lineFlag;
	}

	@Column(name = "SHIPPER_NO", nullable = false, length = 20)
	public String getShipperNo() {
		return this.shipperNo;
	}

	public void setShipperNo(String shipperNo) {
		this.shipperNo = shipperNo;
	}

	@Column(name = "SKU_LIMMIT", nullable = false, precision = 4, scale = 0)
	public Short getSkuLimmit() {
		return this.skuLimmit;
	}

	public void setSkuLimmit(Short skuLimmit) {
		this.skuLimmit = skuLimmit;
	}

	@Column(name = "WAIT_TIMES", nullable = false, precision = 10, scale = 0)
	public Long getWaitTimes() {
		return this.waitTimes;
	}

	public void setWaitTimes(Long waitTimes) {
		this.waitTimes = waitTimes;
	}

	@Column(name = "INTERVAL_TIMES", nullable = false, precision = 10, scale = 0)
	public Long getIntervalTimes() {
		return this.intervalTimes;
	}

	public void setIntervalTimes(Long intervalTimes) {
		this.intervalTimes = intervalTimes;
	}

	@Column(name = "C_LIMMIT", nullable = false, length = 1)
	public String getCLimmit() {
		return this.CLimmit;
	}

	public void setCLimmit(String CLimmit) {
		this.CLimmit = CLimmit;
	}

	@Column(name = "AREA_LIMMIT", nullable = false, length = 1)
	public String getAreaLimmit() {
		return this.areaLimmit;
	}

	public void setAreaLimmit(String areaLimmit) {
		this.areaLimmit = areaLimmit;
	}

	@Column(name = "ITEM_TYPE_FLAG", nullable = false, length = 1)
	public String getItemTypeFlag() {
		return this.itemTypeFlag;
	}

	public void setItemTypeFlag(String itemTypeFlag) {
		this.itemTypeFlag = itemTypeFlag;
	}

	@Column(name = "PRINT_ENVOICE", nullable = false, length = 1)
	public String getPrintEnvoice() {
		return this.printEnvoice;
	}

	public void setPrintEnvoice(String printEnvoice) {
		this.printEnvoice = printEnvoice;
	}

	@Column(name = "PRINT_WAYBILL", nullable = false, length = 1)
	public String getPrintWaybill() {
		return this.printWaybill;
	}

	public void setPrintWaybill(String printWaybill) {
		this.printWaybill = printWaybill;
	}

	@Column(name = "PRINT_PACKLIST", nullable = false, length = 1)
	public String getPrintPacklist() {
		return this.printPacklist;
	}

	public void setPrintPacklist(String printPacklist) {
		this.printPacklist = printPacklist;
	}

	@Column(name = "CUST_LOGIBOX_RULE_ID", nullable = false, precision = 4, scale = 0)
	public Short getCustLogiboxRuleId() {
		return this.custLogiboxRuleId;
	}

	public void setCustLogiboxRuleId(Short custLogiboxRuleId) {
		this.custLogiboxRuleId = custLogiboxRuleId;
	}

	@Column(name = "DIVIDE_LOGIBOX_RULE_ID", nullable = false, precision = 4, scale = 0)
	public Short getDivideLogiboxRuleId() {
		return this.divideLogiboxRuleId;
	}

	public void setDivideLogiboxRuleId(Short divideLogiboxRuleId) {
		this.divideLogiboxRuleId = divideLogiboxRuleId;
	}

	@Column(name = "TASK_RULE_ID", nullable = false, precision = 4, scale = 0)
	public Short getTaskRuleId() {
		return this.taskRuleId;
	}

	public void setTaskRuleId(Short taskRuleId) {
		this.taskRuleId = taskRuleId;
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

	@Column(name = "SKUCOUNT", nullable = false, precision = 22, scale = 0)
	public BigDecimal getSkucount() {
		return this.skucount;
	}

	public void setSkucount(BigDecimal skucount) {
		this.skucount = skucount;
	}

	@Column(name = "PRINT_VALUE1", length = 1)
	public String getPrintValue1() {
		return this.printValue1;
	}

	public void setPrintValue1(String printValue1) {
		this.printValue1 = printValue1;
	}

	@Column(name = "PRINT_VALUE2", length = 1)
	public String getPrintValue2() {
		return this.printValue2;
	}

	public void setPrintValue2(String printValue2) {
		this.printValue2 = printValue2;
	}

	@Column(name = "LAST_PICK_FLAG", length = 1)
	public String getLastPickFlag() {
		return this.lastPickFlag;
	}

	public void setLastPickFlag(String lastPickFlag) {
		this.lastPickFlag = lastPickFlag;
	}

	@Column(name = "PICK_STRATEGY_ID", nullable = false, precision = 5, scale = 0)
	public Integer getPickStrategyId() {
		return this.pickStrategyId;
	}

	public void setPickStrategyId(Integer pickStrategyId) {
		this.pickStrategyId = pickStrategyId;
	}

	@Column(name = "SKU_COUNT_MODE", nullable = false, length = 1)
	public String getSkuCountMode() {
		return this.skuCountMode;
	}

	public void setSkuCountMode(String skuCountMode) {
		this.skuCountMode = skuCountMode;
	}

	@Column(name = "C_DIVIDE_FLAG", nullable = false, length = 1)
	public String getCDivideFlag() {
		return this.CDivideFlag;
	}

	public void setCDivideFlag(String CDivideFlag) {
		this.CDivideFlag = CDivideFlag;
	}

	@Column(name = "B_DIVIDE_FLAG", nullable = false, length = 1)
	public String getBDivideFlag() {
		return this.BDivideFlag;
	}

	public void setBDivideFlag(String BDivideFlag) {
		this.BDivideFlag = BDivideFlag;
	}

	@Column(name = "AREA_LIMMIT_VALUE", length = 10)
	public String getAreaLimmitValue() {
		return this.areaLimmitValue;
	}

	public void setAreaLimmitValue(String areaLimmitValue) {
		this.areaLimmitValue = areaLimmitValue;
	}

	@Column(name = "AREA_ALLOW", length = 10)
	public String getAreaAllow() {
		return this.areaAllow;
	}

	public void setAreaAllow(String areaAllow) {
		this.areaAllow = areaAllow;
	}

	@Column(name = "RULE_FLAG", length = 1)
	public String getRuleFlag() {
		return this.ruleFlag;
	}

	public void setRuleFlag(String ruleFlag) {
		this.ruleFlag = ruleFlag;
	}

}