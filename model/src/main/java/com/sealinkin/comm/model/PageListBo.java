package com.sealinkin.comm.model;

import java.util.List;

public class PageListBo<T> {

	private List<T> rows;
	private Integer total;
	private boolean isSucc;
	private String msg;
	
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
	public void setSucc(boolean isSucc) {
		this.isSucc = isSucc;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
