package com.sealinkin.cost.model;

import com.sealinkin.cost.po.Cost_OtherSet;

public class Cost_OtherSetModel extends Cost_OtherSet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String costNo;
	private String costNoText;
	
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getCostNo() {
		return costNo;
	}
	public void setCostNo(String costNo) {
		this.costNo = costNo;
	}
	public String getCostNoText() {
		return costNoText;
	}
	public void setCostNoText(String costNoText) {
		this.costNoText = costNoText;
	}
}
