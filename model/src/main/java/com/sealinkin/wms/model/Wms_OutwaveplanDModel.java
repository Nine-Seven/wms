package com.sealinkin.wms.model;

import com.sealinkin.wms.po.Wms_OutwaveplanD;

public class Wms_OutwaveplanDModel extends Wms_OutwaveplanD {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String expType;
	private Short strategyId;
	private String strategyType;
	private String strategyName;
	private String status;
	
	
	private String textTest;
	
	private String value;		//6-23
	private String text;
	private String trialRuleId;
	private String ruleId;
	private String memo;
	private String allotRule;
	private String batchRuleId;
	private String orderSource;
	private String deliverType;
	
	private Integer pickStrategyId;		//7-6
	private String pickStrategyName;
	private String pickDiffFlag;
	private String printType;
	private String printTypeTest;
	
	
	
	public String getTextTest() {
		return textTest;
	}
	public void setTextTest(String textTest) {
		this.textTest = textTest;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getExpType() {
		return expType;
	}
	public void setExpType(String expType) {
		this.expType = expType;
	}
	public Short getStrategyId() {
		return strategyId;
	}
	public void setStrategyId(Short strategyId) {
		this.strategyId = strategyId;
	}
	public String getStrategyType() {
		return strategyType;
	}
	public void setStrategyType(String strategyType) {
		this.strategyType = strategyType;
	}
	public String getStrategyName() {
		return strategyName;
	}
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTrialRuleId() {
		return trialRuleId;
	}
	public void setTrialRuleId(String trialRuleId) {
		this.trialRuleId = trialRuleId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getAllotRule() {
		return allotRule;
	}
	public void setAllotRule(String allotRule) {
		this.allotRule = allotRule;
	}
	public String getBatchRuleId() {
		return batchRuleId;
	}
	public void setBatchRuleId(String batchRuleId) {
		this.batchRuleId = batchRuleId;
	}
	public String getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	public String getDeliverType() {
		return deliverType;
	}
	public void setDeliverType(String deliverType) {
		this.deliverType = deliverType;
	}
	public Integer getPickStrategyId() {
		return pickStrategyId;
	}
	public void setPickStrategyId(Integer pickStrategyId) {
		this.pickStrategyId = pickStrategyId;
	}
	public String getPickStrategyName() {
		return pickStrategyName;
	}
	public void setPickStrategyName(String pickStrategyName) {
		this.pickStrategyName = pickStrategyName;
	}
	public String getPickDiffFlag() {
		return pickDiffFlag;
	}
	public void setPickDiffFlag(String pickDiffFlag) {
		this.pickDiffFlag = pickDiffFlag;
	}
	public String getPrintType() {
		return printType;
	}
	public void setPrintType(String printType) {
		this.printType = printType;
	}
	public String getPrintTypeTest() {
		return printTypeTest;
	}
	public void setPrintTypeTest(String printTypeTest) {
		this.printTypeTest = printTypeTest;
	}
	
	
}
