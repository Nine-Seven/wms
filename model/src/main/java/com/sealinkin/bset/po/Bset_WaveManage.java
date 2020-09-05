package com.sealinkin.bset.po;
// default package

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
 * Bset_WaveManage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BSET_WAVE_MANAGE")
public class Bset_WaveManage implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bset_WaveManageId id;
	private String startTime;
	private String endTime;
	private String rgstName;
	private Date rgstDate;
	private String status;
	private Date operateDate;
	private BigDecimal currBatch;
	private String waveType;

	// Constructors

	/** default constructor */
	public Bset_WaveManage() {
	}

	/** minimal constructor */
	public Bset_WaveManage(Bset_WaveManageId id, String status,
			Date operateDate, String waveType) {
		this.id = id;
		this.status = status;
		this.operateDate = operateDate;
		this.waveType = waveType;
	}

	/** full constructor */
	public Bset_WaveManage(Bset_WaveManageId id, String startTime,
			String endTime, String rgstName, Date rgstDate, String status,
			Date operateDate, BigDecimal currBatch, String waveType) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.status = status;
		this.operateDate = operateDate;
		this.currBatch = currBatch;
		this.waveType = waveType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "waveNo", column = @Column(name = "WAVE_NO", nullable = false, length = 15)) })
	public Bset_WaveManageId getId() {
		return this.id;
	}

	public void setId(Bset_WaveManageId id) {
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

	@Temporal(TemporalType.DATE)
	@Column(name = "RGST_DATE", length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OPERATE_DATE", nullable = false, length = 7)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "CURR_BATCH", precision = 22, scale = 0)
	public BigDecimal getCurrBatch() {
		return this.currBatch;
	}

	public void setCurrBatch(BigDecimal currBatch) {
		this.currBatch = currBatch;
	}

	@Column(name = "WAVE_TYPE", nullable = false, length = 1)
	public String getWaveType() {
		return this.waveType;
	}

	public void setWaveType(String waveType) {
		this.waveType = waveType;
	}

}