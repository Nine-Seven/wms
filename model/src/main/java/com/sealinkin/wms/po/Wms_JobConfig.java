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
 * WmsJobConfig entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_JOB_CONFIG")
public class Wms_JobConfig implements java.io.Serializable {

	// Fields

	private Wms_JobConfigId id;
	private String procNameDesc;
	private String DNodeId;
	private String startTimeLimit;
	private String endTimeLimit;
	private Date lastRunTime;
	private String driveTable;
	private Long driveLastSerial;
	private String executeStatus;
	private String runCountType;
	private String runTimeInterval;

	// Constructors

	/** default constructor */
	public Wms_JobConfig() {
	}

	/** minimal constructor */
	public Wms_JobConfig(Wms_JobConfigId id, String startTimeLimit,
			String endTimeLimit, String driveTable, Long driveLastSerial,
			String executeStatus, String runCountType,
			String runTimeInterval) {
		this.id = id;
		this.startTimeLimit = startTimeLimit;
		this.endTimeLimit = endTimeLimit;
		this.driveTable = driveTable;
		this.driveLastSerial = driveLastSerial;
		this.executeStatus = executeStatus;
		this.runCountType = runCountType;
		this.runTimeInterval = runTimeInterval;
	}

	/** full constructor */
	public Wms_JobConfig(Wms_JobConfigId id, String procNameDesc, String DNodeId,
			String startTimeLimit, String endTimeLimit, Date lastRunTime,
			String driveTable, Long driveLastSerial, String executeStatus,
			String runCountType, String runTimeInterval) {
		this.id = id;
		this.procNameDesc = procNameDesc;
		this.DNodeId = DNodeId;
		this.startTimeLimit = startTimeLimit;
		this.endTimeLimit = endTimeLimit;
		this.lastRunTime = lastRunTime;
		this.driveTable = driveTable;
		this.driveLastSerial = driveLastSerial;
		this.executeStatus = executeStatus;
		this.runCountType = runCountType;
		this.runTimeInterval = runTimeInterval;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "procName", column = @Column(name = "PROC_NAME", nullable = false, length = 30)) })
	public Wms_JobConfigId getId() {
		return this.id;
	}

	public void setId(Wms_JobConfigId id) {
		this.id = id;
	}

	@Column(name = "PROC_NAME_DESC", length = 60)
	public String getProcNameDesc() {
		return this.procNameDesc;
	}

	public void setProcNameDesc(String procNameDesc) {
		this.procNameDesc = procNameDesc;
	}

	@Column(name = "D_NODE_ID", length = 30)
	public String getDNodeId() {
		return this.DNodeId;
	}

	public void setDNodeId(String DNodeId) {
		this.DNodeId = DNodeId;
	}

	@Column(name = "START_TIME_LIMIT", nullable = false, length = 8)
	public String getStartTimeLimit() {
		return this.startTimeLimit;
	}

	public void setStartTimeLimit(String startTimeLimit) {
		this.startTimeLimit = startTimeLimit;
	}

	@Column(name = "END_TIME_LIMIT", nullable = false, length = 8)
	public String getEndTimeLimit() {
		return this.endTimeLimit;
	}

	public void setEndTimeLimit(String endTimeLimit) {
		this.endTimeLimit = endTimeLimit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_RUN_TIME", length = 7)
	public Date getLastRunTime() {
		return this.lastRunTime;
	}

	public void setLastRunTime(Date lastRunTime) {
		this.lastRunTime = lastRunTime;
	}

	@Column(name = "DRIVE_TABLE", nullable = false, length = 30)
	public String getDriveTable() {
		return this.driveTable;
	}

	public void setDriveTable(String driveTable) {
		this.driveTable = driveTable;
	}

	@Column(name = "DRIVE_LAST_SERIAL", nullable = false, precision = 10, scale = 0)
	public Long getDriveLastSerial() {
		return this.driveLastSerial;
	}

	public void setDriveLastSerial(Long driveLastSerial) {
		this.driveLastSerial = driveLastSerial;
	}

	@Column(name = "EXECUTE_STATUS", nullable = false, precision = 22, scale = 0)
	public String getExecuteStatus() {
		return this.executeStatus;
	}

	public void setExecuteStatus(String executeStatus) {
		this.executeStatus = executeStatus;
	}

	@Column(name = "RUN_COUNT_TYPE", nullable = false, precision = 22, scale = 0)
	public String getRunCountType() {
		return this.runCountType;
	}

	public void setRunCountType(String runCountType) {
		this.runCountType = runCountType;
	}

	@Column(name = "RUN_TIME_INTERVAL", nullable = false, precision = 22, scale = 0)
	public String getRunTimeInterval() {
		return this.runTimeInterval;
	}

	public void setRunTimeInterval(String runTimeInterval) {
		this.runTimeInterval = runTimeInterval;
	}

}