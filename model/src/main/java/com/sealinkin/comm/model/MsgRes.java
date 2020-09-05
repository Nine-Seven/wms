package com.sealinkin.comm.model;

public class MsgRes {
	private Boolean isSucc;
	private String msg;
	private Object obj;
	
	public MsgRes(Boolean isSucc,String msg,Object obj){
		this.isSucc=isSucc;
		this.msg=msg;
		this.obj=obj;
	}
	public MsgRes(){
		
	}
	public Boolean getIsSucc() {
		return isSucc;
	}
	public void setIsSucc(Boolean isSucc) {
		this.isSucc = isSucc;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
}

