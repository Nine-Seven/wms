package com.sealinkin.protocolExchange.LoginDataChange;

import java.io.Serializable;
import java.util.Map;

import com.sealinkin.comm.model.socket.biz.role.StuPermission;

public class LoginAnswerModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strSessionID;      //对话ID 
	private String strOwnerPermissions; //货主权限
	private Map<String, StuPermission> arrStuCpermission;       //用户名
	public String getStrSessionID() {
		return strSessionID;
	}
	public void setStrSessionID(String strSessionID) {
		this.strSessionID = strSessionID;
	}
	
	public String getStrOwnerPermissions() {
		return strOwnerPermissions;
	}
	public void setStrOwnerPermissions(String strOwnerPermissions) {
		this.strOwnerPermissions = strOwnerPermissions;
	}
	public Map<String, StuPermission> getArrStuCpermission() {
		return arrStuCpermission;
	}
	public void setArrStuCpermission(Map<String, StuPermission> arrStuCpermission) {
		this.arrStuCpermission = arrStuCpermission;
	}	
}
