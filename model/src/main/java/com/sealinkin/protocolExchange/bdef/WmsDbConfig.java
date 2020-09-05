package com.sealinkin.protocolExchange.bdef;

import java.io.Serializable;

public class WmsDbConfig implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String connetType;
    private String connetUser;
    private String connetPw;
    private String fieldName;
    private String ip;
    private String station;
	public String getConnetType() {
		return connetType;
	}
	public void setConnetType(String connetType) {
		this.connetType = connetType;
	}
	public String getConnetUser() {
		return connetUser;
	}
	public void setConnetUser(String connetUser) {
		this.connetUser = connetUser;
	}
	public String getConnetPw() {
		return connetPw;
	}
	public void setConnetPw(String connetPw) {
		this.connetPw = connetPw;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}       
}
