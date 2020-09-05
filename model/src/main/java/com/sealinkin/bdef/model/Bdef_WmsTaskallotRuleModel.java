package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_WmsTaskallotRule;

/**
 * 模块名称：任务切分规则配置MODEL
 * 模块编码：I201
 * 创建：huangb20160612
 */
public class Bdef_WmsTaskallotRuleModel extends Bdef_WmsTaskallotRule {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3735998530092660406L;
	// Fields
	private String operateType;
	private String outstockType;
	private String operateTypeText;
	private String allotRuleText;
	private String boxFlagText;
	private String taskTypeText;
	private String printTypeText;
	private String outstockTypeText;
	private String taskGetTypeText;
	
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getOutstockType() {
		return outstockType;
	}
	public void setOutstockType(String outstockType) {
		this.outstockType = outstockType;
	}
	public String getOperateTypeText() {
		return operateTypeText;
	}
	public void setOperateTypeText(String operateTypeText) {
		this.operateTypeText = operateTypeText;
	}
	public String getAllotRuleText() {
		return allotRuleText;
	}
	public void setAllotRuleText(String allotRuleText) {
		this.allotRuleText = allotRuleText;
	}
	public String getBoxFlagText() {
		return boxFlagText;
	}
	public void setBoxFlagText(String boxFlagText) {
		this.boxFlagText = boxFlagText;
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
	public String getOutstockTypeText() {
		return outstockTypeText;
	}
	public void setOutstockTypeText(String outstockTypeText) {
		this.outstockTypeText = outstockTypeText;
	}
	public String getTaskGetTypeText() {
		return taskGetTypeText;
	}
	public void setTaskGetTypeText(String taskGetTypeText) {
		this.taskGetTypeText = taskGetTypeText;
	}
	
	
	
	
}