package com.sealinkin.comm.model;

public class PageBo
{
	private int start;
	private int pagesize;
   
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public PageBo() {
		super();
	}
	public PageBo(int start, int pagesize) {
		super();
		this.start = start;
		this.pagesize = pagesize;
	}
    
}
