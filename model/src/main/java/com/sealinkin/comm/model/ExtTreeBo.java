package com.sealinkin.comm.model;

import java.util.ArrayList;
import java.util.List;

public class ExtTreeBo {
	private String text;
	private List<ExtTreeLeafBo> children=new ArrayList<ExtTreeLeafBo>();
	private Boolean leaf=false;
	private String cls="file";
	private String id;
	private Boolean expanded;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getExpanded() {
		return expanded;
	}
	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<ExtTreeLeafBo> getChildren() {
		return children;
	}
	public void setChildren(List<ExtTreeLeafBo> children) {
		this.children = children;
	}


	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	
	
}
