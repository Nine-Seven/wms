package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_WmsTaskallotRuleM;

/**
 * 模块名称：任务切分规则配置MODEL
 * 模块编码：I201
 * 创建：huangb20160612
 */
public class Bdef_WmsTaskallotRuleMModel extends Bdef_WmsTaskallotRuleM {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3735998530092660406L;
	// Fields
	private String enterpriseNo;
	private Short taskRuleid;
	private String taskRuleidText;
	private String ruleTypeText;
	private String taskTypeText;
	private String printTypeText;
	private String taskGetTypeText;
	private String onedeliveronepickText;
	
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getTaskRuleidText() {
		return taskRuleidText;
	}
	public void setTaskRuleidText(String taskRuleidText) {
		this.taskRuleidText = taskRuleidText;
	}
	public String getTaskTypeText() {
		return taskTypeText;
	}
	public void setTaskTypeText(String taskTypeText) {
		this.taskTypeText = taskTypeText;
	}
	public String getPrintTypeText() {
		return printTypeText;
	}
	public void setPrintTypeText(String printTypeText) {
		this.printTypeText = printTypeText;
	}
	public String getTaskGetTypeText() {
		return taskGetTypeText;
	}
	public void setTaskGetTypeText(String taskGetTypeText) {
		this.taskGetTypeText = taskGetTypeText;
	}
	public String getRuleTypeText() {
		return ruleTypeText;
	}
	public void setRuleTypeText(String ruleTypeText) {
		this.ruleTypeText = ruleTypeText;
	}
	public Short getTaskRuleid() {
		return taskRuleid;
	}
	public void setTaskRuleid(Short taskRuleid) {
		this.taskRuleid = taskRuleid;
	}
	public String getOnedeliveronepickText() {
		return onedeliveronepickText;
	}
	public void setOnedeliveronepickText(String onedeliveronepickText) {
		this.onedeliveronepickText = onedeliveronepickText;
	}
	
	
	
	
}