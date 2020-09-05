package com.sealinkin.comm.model;

import java.util.List;

public class ListDataBo<T> {
	private List<T> rootList;
	private Integer totalCount;
	
	public ListDataBo(){
		
	}
	
	public ListDataBo(List<T> rootList,Integer totalCount){
		this.rootList=rootList;
		this.totalCount=totalCount;
	}
	
	public List<T> getRootList() {
		return rootList;
	}
	public void setRootList(List<T> rootList) {
		this.rootList = rootList;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
}
