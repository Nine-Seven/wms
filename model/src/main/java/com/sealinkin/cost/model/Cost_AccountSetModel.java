package com.sealinkin.cost.model;

import com.sealinkin.cost.po.Cost_AccountSet;

public class Cost_AccountSetModel extends Cost_AccountSet {

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String accountNo;
	
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
}
