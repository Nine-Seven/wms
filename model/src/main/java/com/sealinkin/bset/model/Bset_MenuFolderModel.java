package com.sealinkin.bset.model;

import java.math.BigDecimal;

import com.sealinkin.bset.po.Bset_MenuFolder;

public class Bset_MenuFolderModel extends Bset_MenuFolder{

	private static final long serialVersionUID = 1L;
	
	private BigDecimal menugroupId;
	private String folderId;
	
	public BigDecimal getMenugroupId() {
		return menugroupId;
	}
	public void setMenugroupId(BigDecimal menugroupId) {
		this.menugroupId = menugroupId;
	}
	public String getFolderId() {
		return folderId;
	}
	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}
	
	
	

}
