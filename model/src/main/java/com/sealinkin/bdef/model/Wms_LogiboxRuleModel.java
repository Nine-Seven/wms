package com.sealinkin.bdef.model;

import java.math.BigDecimal;
import java.util.Date;

import com.sealinkin.bdef.po.Wms_LogiboxRule;

public class Wms_LogiboxRuleModel extends Wms_LogiboxRule{

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String ruleId; 
	private String allotRuleText; 
	private String areaRuleText;
	private String volFlagText;
	private String weightFlagText;
	private BigDecimal boxSkuLimitText; 
	private String onedeliveronepickText;
	private String splitboxFlagText;
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getAllotRuleText() {
		return allotRuleText;
	}
	public void setAllotRuleText(String allotRuleText) {
		this.allotRuleText = allotRuleText;
	}
	public String getAreaRuleText() {
		return areaRuleText;
	}
	public void setAreaRuleText(String areaRuleText) {
		this.areaRuleText = areaRuleText;
	}
	public String getVolFlagText() {
		return volFlagText;
	}
	public void setVolFlagText(String volFlagText) {
		this.volFlagText = volFlagText;
	}
	public String getWeightFlagText() {
		return weightFlagText;
	}
	public void setWeightFlagText(String weightFlagText) {
		this.weightFlagText = weightFlagText;
	}
	public BigDecimal getBoxSkuLimitText() {
		return boxSkuLimitText;
	}
	public void setBoxSkuLimitText(BigDecimal boxSkuLimitText) {
		this.boxSkuLimitText = boxSkuLimitText;
	}
	public String getOnedeliveronepickText() {
		return onedeliveronepickText;
	}
	public void setOnedeliveronepickText(String onedeliveronepickText) {
		this.onedeliveronepickText = onedeliveronepickText;
	}
	public String getSplitboxFlagText() {
		return splitboxFlagText;
	}
	public void setSplitboxFlagText(String splitboxFlagText) {
		this.splitboxFlagText = splitboxFlagText;
	}
}
