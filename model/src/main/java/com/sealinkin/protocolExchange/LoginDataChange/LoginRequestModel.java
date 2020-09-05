package com.sealinkin.protocolExchange.LoginDataChange;

import java.io.Serializable;

public class LoginRequestModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strUsername;         //用户标识
	private String strPassword;       //登录密码
	private String enterpriseNo;       //登录企业
	public String getStrUsername() {
		return strUsername;
	}
	public void setStrUsername(String strUsername) {
		this.strUsername = strUsername;
	}
	public String getStrPassword() {
		return strPassword;
	}
	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	
	
}
