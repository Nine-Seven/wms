package com.sealinkin.comm.model;

import java.util.List;

public class MsgResGenericity<T> {
	
	private Boolean isSucc;
	private String msg;
	private T t;
	private List<T> list;
	
	public MsgResGenericity(){
		
	}
	
	public MsgResGenericity(Boolean isSucc,String msg,T t,List<T> list)
	{
		this.isSucc = isSucc;
		this.msg = msg;
		this.t = t;
		this.list = list;
	}
	
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
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
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	
	
}
