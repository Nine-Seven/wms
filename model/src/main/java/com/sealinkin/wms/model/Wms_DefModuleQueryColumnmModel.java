package com.sealinkin.wms.model;

import com.sealinkin.wms.po.Wms_Defmodulequerycolumn;

public class Wms_DefModuleQueryColumnmModel extends Wms_Defmodulequerycolumn{
	
	private static final long serialVersionUID = 1L;
	
	private String moduleid;
	private String columnid;
	private String enterpriseNo;
	public String getModuleid() {
		return moduleid;
	}
	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}
	public String getColumnid() {
		return columnid;
	}
	public void setColumnid(String columnid) {
		this.columnid = columnid;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}	
}
