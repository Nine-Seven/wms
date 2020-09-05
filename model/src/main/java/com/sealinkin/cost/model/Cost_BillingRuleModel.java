package com.sealinkin.cost.model;

import com.sealinkin.cost.po.Cost_BillingRule;

public class Cost_BillingRuleModel extends Cost_BillingRule{

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String billingUnit;
	private Short ruleId;
	private String statusText;
	private String billingUnitText;
	
	
	public String getBillingUnitText() {
		return billingUnitText;
	}
	public void setBillingUnitText(String billingUnitText) {
		this.billingUnitText = billingUnitText;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getBillingUnit() {
		return billingUnit;
	}
	public void setBillingUnit(String billingUnit) {
		this.billingUnit = billingUnit;
	}
	public Short getRuleId() {
		return ruleId;
	}
	public void setRuleId(Short ruleId) {
		this.ruleId = ruleId;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	
}
