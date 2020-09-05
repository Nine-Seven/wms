package com.sealinkin.oset.po;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * OsetTaskAllotD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OSET_TASK_ALLOT_D")
public class Oset_TaskAllotD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Oset_TaskAllotDId id;
	private String allotRule;
	private String boxFlag;
	private short paraValue;
	private String taskType;
	private String memo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Oset_TaskAllotD() {
	}

	/** minimal constructor */
	public Oset_TaskAllotD(Oset_TaskAllotDId id, String allotRule,
			String boxFlag, short paraValue, String taskType, String rgstName,
			Date rgstDate) {
		this.id = id;
		this.allotRule = allotRule;
		this.boxFlag = boxFlag;
		this.paraValue = paraValue;
		this.taskType = taskType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Oset_TaskAllotD(Oset_TaskAllotDId id, String allotRule,
			String boxFlag, short paraValue, String taskType, String memo,
			String rgstName, Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.allotRule = allotRule;
		this.boxFlag = boxFlag;
		this.paraValue = paraValue;
		this.taskType = taskType;
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
			@AttributeOverride(name = "taskId", column = @Column(name = "TASK_ID", nullable = false, precision = 3, scale = 0)),
			@AttributeOverride(name = "outstockType", column = @Column(name = "OUTSTOCK_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "operateType", column = @Column(name = "OPERATE_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "sourceType", column = @Column(name = "SOURCE_TYPE", nullable = false, length = 2)) })
	public Oset_TaskAllotDId getId() {
		return this.id;
	}

	public void setId(Oset_TaskAllotDId id) {
		this.id = id;
	}

	@Column(name = "ALLOT_RULE", nullable = false, length = 1)
	public String getAllotRule() {
		return this.allotRule;
	}

	public void setAllotRule(String allotRule) {
		this.allotRule = allotRule;
	}

	@Column(name = "BOX_FLAG", nullable = false, length = 2)
	public String getBoxFlag() {
		return this.boxFlag;
	}

	public void setBoxFlag(String boxFlag) {
		this.boxFlag = boxFlag;
	}

	@Column(name = "PARA_VALUE", nullable = false, precision = 3, scale = 0)
	public short getParaValue() {
		return this.paraValue;
	}

	public void setParaValue(short paraValue) {
		this.paraValue = paraValue;
	}

	@Column(name = "TASK_TYPE", nullable = false, length = 1)
	public String getTaskType() {
		return this.taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
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