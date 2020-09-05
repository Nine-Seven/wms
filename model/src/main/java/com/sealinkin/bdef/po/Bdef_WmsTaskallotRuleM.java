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
@Table(name = "WMS_TASKALLOT_RULE_M")
public class Bdef_WmsTaskallotRuleM implements java.io.Serializable {

	// Fields

	private Bdef_WmsTaskallotRuleMId id;
	private String ruleType;
	private Short paraValue;
	private String memo;
	private String rgstName;
	private Date rgstDate;
	private String taskType;
	private String printType;
	private String taskGetType;
    private String onedeliveronepick; //拣货限制(同一个配送对象一次性拣货：0-不一次拣货；1-要求一次拣货) huangb 20160801
    
	// Constructors

	/** default constructor */
	public Bdef_WmsTaskallotRuleM() {
	}

	/** minimal constructor */
	public Bdef_WmsTaskallotRuleM(Bdef_WmsTaskallotRuleMId id, String ruleType,
			Short paraValue) {
		this.id = id;
		this.ruleType = ruleType;
		this.paraValue = paraValue;
	}

	/** full constructor */
	public Bdef_WmsTaskallotRuleM(Bdef_WmsTaskallotRuleMId id, String ruleType,
			Short paraValue, String memo, String rgstName, Date rgstDate,
			String taskType, String printType, String taskGetType) {
		this.id = id;
		this.ruleType = ruleType;
		this.paraValue = paraValue;
		this.memo = memo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.taskType = taskType;
		this.printType = printType;
		this.taskGetType = taskGetType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "taskRuleid", column = @Column(name = "TASK_RULEID", nullable = false, precision = 4, scale = 0)) })
	public Bdef_WmsTaskallotRuleMId getId() {
		return this.id;
	}

	public void setId(Bdef_WmsTaskallotRuleMId id) {
		this.id = id;
	}

	@Column(name = "RULE_TYPE", nullable = false, length = 5)
	public String getRuleType() {
		return this.ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	@Column(name = "PARA_VALUE", nullable = false, precision = 3, scale = 0)
	public Short getParaValue() {
		return this.paraValue;
	}

	public void setParaValue(Short paraValue) {
		this.paraValue = paraValue;
	}

	@Column(name = "MEMO", length = 100)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	@Column(name = "TASK_TYPE", length = 1)
	public String getTaskType() {
		return this.taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
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

	public String getOnedeliveronepick() {
		return onedeliveronepick;
	}

	public void setOnedeliveronepick(String onedeliveronepick) {
		this.onedeliveronepick = onedeliveronepick;
	}

}