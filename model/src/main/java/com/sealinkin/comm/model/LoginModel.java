package com.sealinkin.comm.model;

import java.io.Serializable;

public class LoginModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginModel(){}
	
	public LoginModel(boolean flag,String msg){
		this.flag=flag;
		this.msg=msg;
	}
	
	private boolean flag;//登陆状态
	private String msg;//提示信息
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
