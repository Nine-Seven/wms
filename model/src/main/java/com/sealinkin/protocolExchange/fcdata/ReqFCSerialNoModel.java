package com.sealinkin.protocolExchange.fcdata;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ReqFCSerialNoModel implements Serializable{
	private String serialNo;
	private String checkType;
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	
}
