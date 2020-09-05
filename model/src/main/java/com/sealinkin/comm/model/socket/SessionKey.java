package com.sealinkin.comm.model.socket;

import java.io.Serializable;
import java.util.Date;

public class SessionKey implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ip;
	private String sessionId;
	private Date createDate;
	public String getIp()
	{
		return ip;
	}
	public void setIp(String ip)
	{
		this.ip = ip;
	}
	public String getSessionId()
	{
		return sessionId;
	}
	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}
	public Date getCreateDate()
	{
		return createDate;
	}
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}
	
}
