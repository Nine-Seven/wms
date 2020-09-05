package com.sealinkin.wms.po;
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
 * WmsOwnerOutorder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_OWNER_OUTORDER")
public class Wms_OwnerOutorder implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private Wms_OwnerOutorderId id;
	private Short priority;
	private String deliverObjLevel;
	private String MFlag;
	private String WFlag;
	private String SFlag;
	private String DFlag;
	private String BDivideFlag;
	private String CDivideFlag;
	private String recheckCompute;
	private String sendbufCompute;
	private String commitType;
	private String shortqtyType;
	private String sorterCompute;
	private String batchCompute;
	private String lineFlag;
	private String orderRsv01;
	private String orderRsv02;
	private String orderRsv03;
	private String orderRsv04;
	private String orderRsv05;
	private String orderRsv06;
	private String orderRsv07;
	private String orderRsv08;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String abnormalFlag;
	private Integer strategyId;
	private String PFlag;
	private String batchComputeType;
	private String sendComputeLevel;
	private String BWorkFlowFlag;
	private String CWorkFlowFlag;
	private String autodivideflag;
	private String autooutstockflag;

	// Constructors

	/** default constructor */
	public Wms_OwnerOutorder() {
	}

	/** minimal constructor */
	public Wms_OwnerOutorder(Wms_OwnerOutorderId id, Short priority,
			String deliverObjLevel, String MFlag, String WFlag, String SFlag,
			String DFlag, String BDivideFlag, String CDivideFlag,
			String recheckCompute, String sendbufCompute, String commitType,
			String shortqtyType, String sorterCompute, String batchCompute,
			String lineFlag, String abnormalFlag) {
		this.id = id;
		this.priority = priority;
		this.deliverObjLevel = deliverObjLevel;
		this.MFlag = MFlag;
		this.WFlag = WFlag;
		this.SFlag = SFlag;
		this.DFlag = DFlag;
		this.BDivideFlag = BDivideFlag;
		this.CDivideFlag = CDivideFlag;
		this.recheckCompute = recheckCompute;
		this.sendbufCompute = sendbufCompute;
		this.commitType = commitType;
		this.shortqtyType = shortqtyType;
		this.sorterCompute = sorterCompute;
		this.batchCompute = batchCompute;
		this.lineFlag = lineFlag;
		this.abnormalFlag = abnormalFlag;
	}

	/** full constructor */
	public Wms_OwnerOutorder(Wms_OwnerOutorderId id, Short priority,
			String deliverObjLevel, String MFlag, String WFlag, String SFlag,
			String DFlag, String BDivideFlag, String CDivideFlag,
			String recheckCompute, String sendbufCompute, String commitType,
			String shortqtyType, String sorterCompute, String batchCompute,
			String lineFlag, String orderRsv01, String orderRsv02,
			String orderRsv03, String orderRsv04, String orderRsv05,
			String orderRsv06, String orderRsv07, String orderRsv08,
			String rgstName, Date rgstDate, String updtName, Date updtDate,
			String abnormalFlag, Integer strategyId, String PFlag,
			String batchComputeType, String sendComputeLevel,
			String BWorkFlowFlag, String CWorkFlowFlag, String autodivideflag,
			String autooutstockflag) {
		this.id = id;
		this.priority = priority;
		this.deliverObjLevel = deliverObjLevel;
		this.MFlag = MFlag;
		this.WFlag = WFlag;
		this.SFlag = SFlag;
		this.DFlag = DFlag;
		this.BDivideFlag = BDivideFlag;
		this.CDivideFlag = CDivideFlag;
		this.recheckCompute = recheckCompute;
		this.sendbufCompute = sendbufCompute;
		this.commitType = commitType;
		this.shortqtyType = shortqtyType;
		this.sorterCompute = sorterCompute;
		this.batchCompute = batchCompute;
		this.lineFlag = lineFlag;
		this.orderRsv01 = orderRsv01;
		this.orderRsv02 = orderRsv02;
		this.orderRsv03 = orderRsv03;
		this.orderRsv04 = orderRsv04;
		this.orderRsv05 = orderRsv05;
		this.orderRsv06 = orderRsv06;
		this.orderRsv07 = orderRsv07;
		this.orderRsv08 = orderRsv08;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.abnormalFlag = abnormalFlag;
		this.strategyId = strategyId;
		this.PFlag = PFlag;
		this.batchComputeType = batchComputeType;
		this.sendComputeLevel = sendComputeLevel;
		this.BWorkFlowFlag = BWorkFlowFlag;
		this.CWorkFlowFlag = CWorkFlowFlag;
		this.autodivideflag = autodivideflag;
		this.autooutstockflag = autooutstockflag;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "expType", column = @Column(name = "EXP_TYPE", nullable = false, length = 5)) })
	public Wms_OwnerOutorderId getId() {
		return this.id;
	}

	public void setId(Wms_OwnerOutorderId id) {
		this.id = id;
	}

	@Column(name = "PRIORITY", nullable = false, precision = 3, scale = 0)
	public Short getPriority() {
		return this.priority;
	}

	public void setPriority(Short priority) {
		this.priority = priority;
	}

	@Column(name = "DELIVER_OBJ_LEVEL", nullable = false, length = 1)
	public String getDeliverObjLevel() {
		return this.deliverObjLevel;
	}

	public void setDeliverObjLevel(String deliverObjLevel) {
		this.deliverObjLevel = deliverObjLevel;
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

	@Column(name = "RECHECK_COMPUTE", nullable = false, length = 1)
	public String getRecheckCompute() {
		return this.recheckCompute;
	}

	public void setRecheckCompute(String recheckCompute) {
		this.recheckCompute = recheckCompute;
	}

	@Column(name = "SENDBUF_COMPUTE", nullable = false, length = 1)
	public String getSendbufCompute() {
		return this.sendbufCompute;
	}

	public void setSendbufCompute(String sendbufCompute) {
		this.sendbufCompute = sendbufCompute;
	}

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

	@Column(name = "SORTER_COMPUTE", nullable = false, length = 1)
	public String getSorterCompute() {
		return this.sorterCompute;
	}

	public void setSorterCompute(String sorterCompute) {
		this.sorterCompute = sorterCompute;
	}

	@Column(name = "BATCH_COMPUTE", nullable = false, length = 1)
	public String getBatchCompute() {
		return this.batchCompute;
	}

	public void setBatchCompute(String batchCompute) {
		this.batchCompute = batchCompute;
	}

	@Column(name = "LINE_FLAG", nullable = false, length = 1)
	public String getLineFlag() {
		return this.lineFlag;
	}

	public void setLineFlag(String lineFlag) {
		this.lineFlag = lineFlag;
	}

	@Column(name = "ORDER_RSV01", length = 20)
	public String getOrderRsv01() {
		return this.orderRsv01;
	}

	public void setOrderRsv01(String orderRsv01) {
		this.orderRsv01 = orderRsv01;
	}

	@Column(name = "ORDER_RSV02", length = 20)
	public String getOrderRsv02() {
		return this.orderRsv02;
	}

	public void setOrderRsv02(String orderRsv02) {
		this.orderRsv02 = orderRsv02;
	}

	@Column(name = "ORDER_RSV03", length = 20)
	public String getOrderRsv03() {
		return this.orderRsv03;
	}

	public void setOrderRsv03(String orderRsv03) {
		this.orderRsv03 = orderRsv03;
	}

	@Column(name = "ORDER_RSV04", length = 20)
	public String getOrderRsv04() {
		return this.orderRsv04;
	}

	public void setOrderRsv04(String orderRsv04) {
		this.orderRsv04 = orderRsv04;
	}

	@Column(name = "ORDER_RSV05", length = 1)
	public String getOrderRsv05() {
		return this.orderRsv05;
	}

	public void setOrderRsv05(String orderRsv05) {
		this.orderRsv05 = orderRsv05;
	}

	@Column(name = "ORDER_RSV06", length = 1)
	public String getOrderRsv06() {
		return this.orderRsv06;
	}

	public void setOrderRsv06(String orderRsv06) {
		this.orderRsv06 = orderRsv06;
	}

	@Column(name = "ORDER_RSV07", length = 1)
	public String getOrderRsv07() {
		return this.orderRsv07;
	}

	public void setOrderRsv07(String orderRsv07) {
		this.orderRsv07 = orderRsv07;
	}

	@Column(name = "ORDER_RSV08", length = 1)
	public String getOrderRsv08() {
		return this.orderRsv08;
	}

	public void setOrderRsv08(String orderRsv08) {
		this.orderRsv08 = orderRsv08;
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

	@Column(name = "ABNORMAL_FLAG", nullable = false, length = 1)
	public String getAbnormalFlag() {
		return this.abnormalFlag;
	}

	public void setAbnormalFlag(String abnormalFlag) {
		this.abnormalFlag = abnormalFlag;
	}

	@Column(name = "STRATEGY_ID", precision = 5, scale = 0)
	public Integer getStrategyId() {
		return this.strategyId;
	}

	public void setStrategyId(Integer strategyId) {
		this.strategyId = strategyId;
	}

	@Column(name = "P_FLAG", length = 1)
	public String getPFlag() {
		return this.PFlag;
	}

	public void setPFlag(String PFlag) {
		this.PFlag = PFlag;
	}

	@Column(name = "BATCH_COMPUTE_TYPE", length = 1)
	public String getBatchComputeType() {
		return this.batchComputeType;
	}

	public void setBatchComputeType(String batchComputeType) {
		this.batchComputeType = batchComputeType;
	}

	@Column(name = "SEND_COMPUTE_LEVEL", length = 1)
	public String getSendComputeLevel() {
		return this.sendComputeLevel;
	}

	public void setSendComputeLevel(String sendComputeLevel) {
		this.sendComputeLevel = sendComputeLevel;
	}

	@Column(name = "B_WORK_FLOW_FLAG", length = 1)
	public String getBWorkFlowFlag() {
		return this.BWorkFlowFlag;
	}

	public void setBWorkFlowFlag(String BWorkFlowFlag) {
		this.BWorkFlowFlag = BWorkFlowFlag;
	}

	@Column(name = "C_WORK_FLOW_FLAG", length = 1)
	public String getCWorkFlowFlag() {
		return this.CWorkFlowFlag;
	}

	public void setCWorkFlowFlag(String CWorkFlowFlag) {
		this.CWorkFlowFlag = CWorkFlowFlag;
	}

	@Column(name = "AUTODIVIDEFLAG", length = 1)
	public String getAutodivideflag() {
		return this.autodivideflag;
	}

	public void setAutodivideflag(String autodivideflag) {
		this.autodivideflag = autodivideflag;
	}

	@Column(name = "AUTOOUTSTOCKFLAG", length = 1)
	public String getAutooutstockflag() {
		return this.autooutstockflag;
	}

	public void setAutooutstockflag(String autooutstockflag) {
		this.autooutstockflag = autooutstockflag;
	}
}