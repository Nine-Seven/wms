package com.sealinkin.protocolExchange.LoginDataChange;
import java.io.Serializable;



public class UpdatePwdModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private String strUsername;         //用户标识
	private String Password;       //登录密码
	private String NewPwd;         //新密码
	private String WorkerNo;
	public String getStrUsername() {
		return strUsername;
	}
	public void setStrUsername(String strUsername) {
		this.strUsername = strUsername;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getNewPwd() {
		return NewPwd;
	}
	public void setNewPwd(String newPwd) {
		NewPwd = newPwd;
	}
	public String getWorkerNo() {
		return WorkerNo;
	}
	public void setWorkerNo(String workerNo) {
		WorkerNo = workerNo;
	}        
			
}
