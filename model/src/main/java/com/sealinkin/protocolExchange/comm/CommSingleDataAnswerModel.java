package com.sealinkin.protocolExchange.comm;

//通用单数据应答结构StuCommSingleDataAnswer
public class CommSingleDataAnswerModel {
	private String AnsObj;
	private String custName;
	
	
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getAnsObj() {
		return AnsObj;
	}

	public void setAnsObj(String ansObj) {
		AnsObj = ansObj;
	}	
}
