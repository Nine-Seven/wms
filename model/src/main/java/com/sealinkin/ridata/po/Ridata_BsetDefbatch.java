package com.sealinkin.ridata.po;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BsetDefbatch entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BSET_DEFBATCH")
public class Ridata_BsetDefbatch implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ridata_BsetDefbatchId id;
	private String startTime;
	private String endTime;
	private String rgstName;
	private Date rgstDate;
	private String status;
	private BigDecimal currRecord;
	private String batchType;

	// Constructors

	/** default constructor */
	public Ridata_BsetDefbatch() {
	}

	/** minimal constructor */
	public Ridata_BsetDefbatch(Ridata_BsetDefbatchId id, String batchType) {
		this.id = id;
		this.batchType = batchType;
	}

	/** full constructor */
	public Ridata_BsetDefbatch(Ridata_BsetDefbatchId id, String startTime, String endTime,
			String rgstName, Date rgstDate, String status,
			BigDecimal currRecord, String batchType) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.status = status;
		this.currRecord = currRecord;
		this.batchType = batchType;

	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "batchNo", column = @Column(name = "BATCH_NO", nullable = false, length = 2)),
			@AttributeOverride(name = "operateDate", column = @Column(name = "OPERATE_DATE", nullable = false, length = 7)) })
	public Ridata_BsetDefbatchId getId() {
		return this.id;
	}

	public void setId(Ridata_BsetDefbatchId id) {
		this.id = id;
	}

	@Column(name = "START_TIME", length = 5)
	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "END_TIME", length = 5)
	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "RGST_NAME", length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Column(name = "RGST_DATE", length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CURR_RECORD", precision = 22, scale = 0)
	public BigDecimal getCurrRecord() {
		return this.currRecord;
	}

	public void setCurrRecord(BigDecimal currRecord) {
		this.currRecord = currRecord;
	}
	@Column(name = "BATCH_TYPE", nullable = false, length = 1)
	public String getBatchType() {
		return this.batchType;
	}

	public void setBatchType(String batchType) {
		this.batchType = batchType;
	}

}