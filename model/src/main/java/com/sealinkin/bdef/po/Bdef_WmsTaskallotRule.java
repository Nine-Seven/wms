package com.sealinkin.bdef.po;

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
 * 模块名称：任务切分规则配置MODEL
 * 模块编码：I201
 * 创建：huangb20160612
 */
@Entity
@Table(name = "WMS_TASKALLOT_RULE")
public class Bdef_WmsTaskallotRule implements java.io.Serializable {

	// Fields

	private Bdef_WmsTaskallotRuleId id;
	private String allotRule;
	private String boxFlag;
	private Short paraValue;
	private String taskType;
	private String memo;
	private String rgstName;
	private Date rgstDate;
	private String printType;
	private String taskGetType;

	// Constructors

	/** default constructor */
	public Bdef_WmsTaskallotRule() {
	}

	/** minimal constructor */
	public Bdef_WmsTaskallotRule(Bdef_WmsTaskallotRuleId id, String allotRule,
			String boxFlag, Short paraValue, String taskType, String rgstName,
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
	public Bdef_WmsTaskallotRule(Bdef_WmsTaskallotRuleId id, String allotRule,
			String boxFlag, Short paraValue, String taskType, String memo,
			String rgstName, Date rgstDate, String printType, String taskGetType) {
		this.id = id;
		this.allotRule = allotRule;
		this.boxFlag = boxFlag;
		this.paraValue = paraValue;
		this.taskType = taskType;
		this.memo = memo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.printType = printType;
		this.taskGetType = taskGetType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "operateType", column = @Column(name = "OPERATE_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "outstockType", column = @Column(name = "OUTSTOCK_TYPE", nullable = false, length = 1)) })
	public Bdef_WmsTaskallotRuleId getId() {
		return this.id;
	}

	public void setId(Bdef_WmsTaskallotRuleId id) {
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
	public Short getParaValue() {
		return this.paraValue;
	}

	public void setParaValue(Short paraValue) {
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RGST_DATE", nullable = false, length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "PRINT_TYPE", length = 1)
	public String getPrintType() {
		return this.printType;
	}

	public void setPrintType(String printType) {
		this.printType = printType;
	}

	@Column(name = "TASK_GET_TYPE", length = 1)
	public String getTaskGetType() {
		return this.taskGetType;
	}

	public void setTaskGetType(String taskGetType) {
		this.taskGetType = taskGetType;
	}

}