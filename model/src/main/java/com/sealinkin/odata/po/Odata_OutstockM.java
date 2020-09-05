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
 * OdataOutstockM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ODATA_OUTSTOCK_M" )
public class Odata_OutstockM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Odata_OutstockMId id;
	private Date operateDate;
	private String outstockType;
	private String batchNo;
	private String operateType;
	private String pickType;
	private String taskType;
	private String status;
	private Short priority;
	private String dockNo;
	private Date handinDate;
	private Date handoutDate;
	private String handinName;
	private String handoutName;
	private String printStatus;
	private Date expDate;
	private String sourceType;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String ownerNo;

	// Constructors

	/** default constructor */
	public Odata_OutstockM() {
	}

	/** minimal constructor */
	public Odata_OutstockM(Odata_OutstockMId id, Date operateDate,
			String outstockType, String batchNo, String operateType,
			String pickType, String taskType, String status, Short priority,
			String printStatus, Date expDate, String sourceType,
			String rgstName, Date rgstDate, String ownerNo) {
		this.id = id;
		this.operateDate = operateDate;
		this.outstockType = outstockType;
		this.batchNo = batchNo;
		this.operateType = operateType;
		this.pickType = pickType;
		this.taskType = taskType;
		this.status = status;
		this.priority = priority;
		this.printStatus = printStatus;
		this.expDate = expDate;
		this.sourceType = sourceType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.ownerNo = ownerNo;
	}

	/** full constructor */
	public Odata_OutstockM(Odata_OutstockMId id, Date operateDate,
			String outstockType, String batchNo, String operateType,
			String pickType, String taskType, String status, Short priority,
			String dockNo, Date handinDate, Date handoutDate,
			String handinName, String handoutName, String printStatus,
			Date expDate, String sourceType, String rgstName, Date rgstDate,
			String updtName, Date updtDate, String ownerNo) {
		this.id = id;
		this.operateDate = operateDate;
		this.outstockType = outstockType;
		this.batchNo = batchNo;
		this.operateType = operateType;
		this.pickType = pickType;
		this.taskType = taskType;
		this.status = status;
		this.priority = priority;
		this.dockNo = dockNo;
		this.handinDate = handinDate;
		this.handoutDate = handoutDate;
		this.handinName = handinName;
		this.handoutName = handoutName;
		this.printStatus = printStatus;
		this.expDate = expDate;
		this.sourceType = sourceType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.ownerNo = ownerNo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "outstockNo", column = @Column(name = "OUTSTOCK_NO", nullable = false, length = 20)) })
	public Odata_OutstockMId getId() {
		return this.id;
	}

	public void setId(Odata_OutstockMId id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OPERATE_DATE", nullable = false, length = 7)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "OUTSTOCK_TYPE", nullable = false, length = 1)
	public String getOutstockType() {
		return this.outstockType;
	}

	public void setOutstockType(String outstockType) {
		this.outstockType = outstockType;
	}

	@Column(name = "BATCH_NO", nullable = false, length = 2)
	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	@Column(name = "OPERATE_TYPE", nullable = false, length = 1)
	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Column(name = "PICK_TYPE", nullable = false, length = 1)
	public String getPickType() {
		return this.pickType;
	}

	public void setPickType(String pickType) {
		this.pickType = pickType;
	}

	@Column(name = "TASK_TYPE", nullable = false, length = 1)
	public String getTaskType() {
		return this.taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "PRIORITY", nullable = false, precision = 3, scale = 0)
	public Short getPriority() {
		return this.priority;
	}

	public void setPriority(Short priority) {
		this.priority = priority;
	}

	@Column(name = "DOCK_NO", length = 3)
	public String getDockNo() {
		return this.dockNo;
	}

	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "HANDIN_DATE", length = 7)
	public Date getHandinDate() {
		return this.handinDate;
	}

	public void setHandinDate(Date handinDate) {
		this.handinDate = handinDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "HANDOUT_DATE", length = 7)
	public Date getHandoutDate() {
		return this.handoutDate;
	}

	public void setHandoutDate(Date handoutDate) {
		this.handoutDate = handoutDate;
	}

	@Column(name = "HANDIN_NAME", length = 20)
	public String getHandinName() {
		return this.handinName;
	}

	public void setHandinName(String handinName) {
		this.handinName = handinName;
	}

	@Column(name = "HANDOUT_NAME", length = 20)
	public String getHandoutName() {
		return this.handoutName;
	}

	public void setHandoutName(String handoutName) {
		this.handoutName = handoutName;
	}

	@Column(name = "PRINT_STATUS", nullable = false, length = 2)
	public String getPrintStatus() {
		return this.printStatus;
	}

	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXP_DATE", nullable = false, length = 7)
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	@Column(name = "SOURCE_TYPE", nullable = false, length = 2)
	public String getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
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

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

}