package com.sealinkin.comm.model.socket.biz.user;

import java.io.Serializable;

public class StuLoginRequest implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strUsername;
	private String strPassword;
	private String enterpriseNo;
	
	public String getEnterpriseNo() {
		return enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	public String getStrUsername()
	{
		return strUsername;
	}

	public void setStrUsername(String strUsername)
	{
		this.strUsername = strUsername;
	}

	public String getStrPassword()
	{
		return strPassword;
	}

	public void setStrPassword(String strPassword)
	{
		this.strPassword = strPassword;
	}
}
