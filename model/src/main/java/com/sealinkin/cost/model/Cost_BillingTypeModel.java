package com.sealinkin.cost.model;

import com.sealinkin.cost.po.Cost_BillingType;

public class Cost_BillingTypeModel extends Cost_BillingType{
	
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String billingType;
	private String statusText;
	private String billingTypeText;
	
	
	public String getBillingTypeText() {
		return billingTypeText;
	}
	public void setBillingTypeText(String billingTypeText) {
		this.billingTypeText = billingTypeText;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getBillingType() {
		return billingType;
	}
	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}	

}
