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
 * WmsOutorder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_OUTORDER")
public class Wms_Outorder implements java.io.Serializable {

	// Fields

	private Wms_OutorderId id;
	private Short priority;
	private Short autobatchStrategyId;
	private Short manualbatchStrategyId;
	private Short locateStrategyId;
	private Short computeStrategyId;
	private Short workflowStrategyId;
	private Short divideStrategyId;
	private Short BCheckStrategyId;
	private Short CCheckStrategyId;
	private Short comfireStrategyId;
	private Short loadStrategyId;
	private Short rsv1StrategyId;
	private Short rsv2StrategyId;
	private Short rsv3StrategyId;
	private Short rsv4StrategyId;
	private Short rsv5StrategyId;
	private Short rsv6StrategyId;
	private Short rsv7StrategyId;
	private Short rsv8StrategyId;
	private Short rsv9StrategyId;
	private Short rsv10StrategyId;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String printPacklist;
	private String printEnvoice;
	private String printWaybill;
	private String printValue1;
	private String printValue2;
	private String industryFlag;

	// Constructors

	/** default constructor */
	public Wms_Outorder() {
	}

	/** minimal constructor */
	public Wms_Outorder(Wms_OutorderId id, Short priority,
			Short autobatchStrategyId, Short manualbatchStrategyId,
			Short locateStrategyId, Short computeStrategyId,
			Short workflowStrategyId, String industryFlag) {
		this.id = id;
		this.priority = priority;
		this.autobatchStrategyId = autobatchStrategyId;
		this.manualbatchStrategyId = manualbatchStrategyId;
		this.locateStrategyId = locateStrategyId;
		this.computeStrategyId = computeStrategyId;
		this.workflowStrategyId = workflowStrategyId;
		this.industryFlag = industryFlag;
	}

	/** full constructor */
	public Wms_Outorder(Wms_OutorderId id, Short priority,
			Short autobatchStrategyId, Short manualbatchStrategyId,
			Short locateStrategyId, Short computeStrategyId,
			Short workflowStrategyId, Short divideStrategyId,
			Short BCheckStrategyId, Short CCheckStrategyId,
			Short comfireStrategyId, Short loadStrategyId,
			Short rsv1StrategyId, Short rsv2StrategyId, Short rsv3StrategyId,
			Short rsv4StrategyId, Short rsv5StrategyId, Short rsv6StrategyId,
			Short rsv7StrategyId, Short rsv8StrategyId, Short rsv9StrategyId,
			Short rsv10StrategyId, String rgstName, Date rgstDate,
			String updtName, Date updtDate, String printPacklist,
			String printEnvoice, String printWaybill, String printValue1,
			String printValue2, String industryFlag) {
		this.id = id;
		this.priority = priority;
		this.autobatchStrategyId = autobatchStrategyId;
		this.manualbatchStrategyId = manualbatchStrategyId;
		this.locateStrategyId = locateStrategyId;
		this.computeStrategyId = computeStrategyId;
		this.workflowStrategyId = workflowStrategyId;
		this.divideStrategyId = divideStrategyId;
		this.BCheckStrategyId = BCheckStrategyId;
		this.CCheckStrategyId = CCheckStrategyId;
		this.comfireStrategyId = comfireStrategyId;
		this.loadStrategyId = loadStrategyId;
		this.rsv1StrategyId = rsv1StrategyId;
		this.rsv2StrategyId = rsv2StrategyId;
		this.rsv3StrategyId = rsv3StrategyId;
		this.rsv4StrategyId = rsv4StrategyId;
		this.rsv5StrategyId = rsv5StrategyId;
		this.rsv6StrategyId = rsv6StrategyId;
		this.rsv7StrategyId = rsv7StrategyId;
		this.rsv8StrategyId = rsv8StrategyId;
		this.rsv9StrategyId = rsv9StrategyId;
		this.rsv10StrategyId = rsv10StrategyId;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.printPacklist = printPacklist;
		this.printEnvoice = printEnvoice;
		this.printWaybill = printWaybill;
		this.printValue1 = printValue1;
		this.printValue2 = printValue2;
		this.industryFlag = industryFlag;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "expType", column = @Column(name = "EXP_TYPE", nullable = false, length = 5)) })
	public Wms_OutorderId getId() {
		return this.id;
	}

	public void setId(Wms_OutorderId id) {
		this.id = id;
	}

	@Column(name = "PRIORITY", nullable = false, precision = 3, scale = 0)
	public Short getPriority() {
		return this.priority;
	}

	public void setPriority(Short priority) {
		this.priority = priority;
	}

	@Column(name = "AUTOBATCH_STRATEGY_ID", nullable = false, precision = 4, scale = 0)
	public Short getAutobatchStrategyId() {
		return this.autobatchStrategyId;
	}

	public void setAutobatchStrategyId(Short autobatchStrategyId) {
		this.autobatchStrategyId = autobatchStrategyId;
	}

	@Column(name = "MANUALBATCH_STRATEGY_ID", nullable = false, precision = 4, scale = 0)
	public Short getManualbatchStrategyId() {
		return this.manualbatchStrategyId;
	}

	public void setManualbatchStrategyId(Short manualbatchStrategyId) {
		this.manualbatchStrategyId = manualbatchStrategyId;
	}

	@Column(name = "LOCATE_STRATEGY_ID", nullable = false, precision = 4, scale = 0)
	public Short getLocateStrategyId() {
		return this.locateStrategyId;
	}

	public void setLocateStrategyId(Short locateStrategyId) {
		this.locateStrategyId = locateStrategyId;
	}

	@Column(name = "COMPUTE_STRATEGY_ID", nullable = false, precision = 4, scale = 0)
	public Short getComputeStrategyId() {
		return this.computeStrategyId;
	}

	public void setComputeStrategyId(Short computeStrategyId) {
		this.computeStrategyId = computeStrategyId;
	}

	@Column(name = "WORKFLOW_STRATEGY_ID", nullable = false, precision = 4, scale = 0)
	public Short getWorkflowStrategyId() {
		return this.workflowStrategyId;
	}

	public void setWorkflowStrategyId(Short workflowStrategyId) {
		this.workflowStrategyId = workflowStrategyId;
	}

	@Column(name = "DIVIDE_STRATEGY_ID", precision = 4, scale = 0)
	public Short getDivideStrategyId() {
		return this.divideStrategyId;
	}

	public void setDivideStrategyId(Short divideStrategyId) {
		this.divideStrategyId = divideStrategyId;
	}

	@Column(name = "B_CHECK_STRATEGY_ID", precision = 4, scale = 0)
	public Short getBCheckStrategyId() {
		return this.BCheckStrategyId;
	}

	public void setBCheckStrategyId(Short BCheckStrategyId) {
		this.BCheckStrategyId = BCheckStrategyId;
	}

	@Column(name = "C_CHECK_STRATEGY_ID", precision = 4, scale = 0)
	public Short getCCheckStrategyId() {
		return this.CCheckStrategyId;
	}

	public void setCCheckStrategyId(Short CCheckStrategyId) {
		this.CCheckStrategyId = CCheckStrategyId;
	}

	@Column(name = "COMFIRE_STRATEGY_ID", precision = 4, scale = 0)
	public Short getComfireStrategyId() {
		return this.comfireStrategyId;
	}

	public void setComfireStrategyId(Short comfireStrategyId) {
		this.comfireStrategyId = comfireStrategyId;
	}

	@Column(name = "LOAD_STRATEGY_ID", precision = 4, scale = 0)
	public Short getLoadStrategyId() {
		return this.loadStrategyId;
	}

	public void setLoadStrategyId(Short loadStrategyId) {
		this.loadStrategyId = loadStrategyId;
	}

	@Column(name = "RSV1_STRATEGY_ID", precision = 4, scale = 0)
	public Short getRsv1StrategyId() {
		return this.rsv1StrategyId;
	}

	public void setRsv1StrategyId(Short rsv1StrategyId) {
		this.rsv1StrategyId = rsv1StrategyId;
	}

	@Column(name = "RSV2_STRATEGY_ID", precision = 4, scale = 0)
	public Short getRsv2StrategyId() {
		return this.rsv2StrategyId;
	}

	public void setRsv2StrategyId(Short rsv2StrategyId) {
		this.rsv2StrategyId = rsv2StrategyId;
	}

	@Column(name = "RSV3_STRATEGY_ID", precision = 4, scale = 0)
	public Short getRsv3StrategyId() {
		return this.rsv3StrategyId;
	}

	public void setRsv3StrategyId(Short rsv3StrategyId) {
		this.rsv3StrategyId = rsv3StrategyId;
	}

	@Column(name = "RSV4_STRATEGY_ID", precision = 4, scale = 0)
	public Short getRsv4StrategyId() {
		return this.rsv4StrategyId;
	}

	public void setRsv4StrategyId(Short rsv4StrategyId) {
		this.rsv4StrategyId = rsv4StrategyId;
	}

	@Column(name = "RSV5_STRATEGY_ID", precision = 4, scale = 0)
	public Short getRsv5StrategyId() {
		return this.rsv5StrategyId;
	}

	public void setRsv5StrategyId(Short rsv5StrategyId) {
		this.rsv5StrategyId = rsv5StrategyId;
	}

	@Column(name = "RSV6_STRATEGY_ID", precision = 4, scale = 0)
	public Short getRsv6StrategyId() {
		return this.rsv6StrategyId;
	}

	public void setRsv6StrategyId(Short rsv6StrategyId) {
		this.rsv6StrategyId = rsv6StrategyId;
	}

	@Column(name = "RSV7_STRATEGY_ID", precision = 4, scale = 0)
	public Short getRsv7StrategyId() {
		return this.rsv7StrategyId;
	}

	public void setRsv7StrategyId(Short rsv7StrategyId) {
		this.rsv7StrategyId = rsv7StrategyId;
	}

	@Column(name = "RSV8_STRATEGY_ID", precision = 4, scale = 0)
	public Short getRsv8StrategyId() {
		return this.rsv8StrategyId;
	}

	public void setRsv8StrategyId(Short rsv8StrategyId) {
		this.rsv8StrategyId = rsv8StrategyId;
	}

	@Column(name = "RSV9_STRATEGY_ID", precision = 4, scale = 0)
	public Short getRsv9StrategyId() {
		return this.rsv9StrategyId;
	}

	public void setRsv9StrategyId(Short rsv9StrategyId) {
		this.rsv9StrategyId = rsv9StrategyId;
	}

	@Column(name = "RSV10_STRATEGY_ID", precision = 4, scale = 0)
	public Short getRsv10StrategyId() {
		return this.rsv10StrategyId;
	}

	public void setRsv10StrategyId(Short rsv10StrategyId) {
		this.rsv10StrategyId = rsv10StrategyId;
	}

	@Column(name = "RGST_NAME", length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
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

	@Column(name = "PRINT_PACKLIST", length = 1)
	public String getPrintPacklist() {
		return this.printPacklist;
	}

	public void setPrintPacklist(String printPacklist) {
		this.printPacklist = printPacklist;
	}

	@Column(name = "PRINT_ENVOICE", length = 1)
	public String getPrintEnvoice() {
		return this.printEnvoice;
	}

	public void setPrintEnvoice(String printEnvoice) {
		this.printEnvoice = printEnvoice;
	}

	@Column(name = "PRINT_WAYBILL", length = 1)
	public String getPrintWaybill() {
		return this.printWaybill;
	}

	public void setPrintWaybill(String printWaybill) {
		this.printWaybill = printWaybill;
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

	@Column(name = "INDUSTRY_FLAG", nullable = false, length = 1)
	public String getIndustryFlag() {
		return this.industryFlag;
	}

	public void setIndustryFlag(String industryFlag) {
		this.industryFlag = industryFlag;
	}
}