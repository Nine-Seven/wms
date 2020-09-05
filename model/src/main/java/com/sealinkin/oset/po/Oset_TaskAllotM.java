package com.sealinkin.oset.po;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * OsetTaskAllotM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OSET_TASK_ALLOT_M")
public class Oset_TaskAllotM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Oset_TaskAllotMId id;
	private String taskName;
	private String defaultFlag;
	private String memo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Oset_TaskAllotM() {
	}

	/** minimal constructor */
	public Oset_TaskAllotM(Oset_TaskAllotMId id, String taskName,
			String defaultFlag, String rgstName, Date rgstDate) {
		this.id = id;
		this.taskName = taskName;
		this.defaultFlag = defaultFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Oset_TaskAllotM(Oset_TaskAllotMId id, String taskName,
			String defaultFlag, String memo, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.taskName = taskName;
		this.defaultFlag = defaultFlag;
		this.memo = memo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "taskId", column = @Column(name = "TASK_ID", nullable = false, precision = 3, scale = 0)) })
	public Oset_TaskAllotMId getId() {
		return this.id;
	}

	public void setId(Oset_TaskAllotMId id) {
		this.id = id;
	}

	@Column(name = "TASK_NAME", nullable = false, length = 100)
	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Column(name = "DEFAULT_FLAG", nullable = false, length = 1)
	public String getDefaultFlag() {
		return this.defaultFlag;
	}

	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	@Column(name = "MEMO", length = 100)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

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

	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}