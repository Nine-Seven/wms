package com.sealinkin.protocolExchange.print;

import java.io.Serializable;

/**
 * 获取字段信息请求 请求  StuReqFieldDesc
 * @author lich
 *
 */
public class ReqFieldDescModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sessionID;

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

}
