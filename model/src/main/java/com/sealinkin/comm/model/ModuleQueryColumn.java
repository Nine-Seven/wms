package com.sealinkin.comm.model;

public class ModuleQueryColumn {
	 private String moduleId;
	 private String columnId;
	 private String columnName;
	 private String xType;
	 private int orderNo;
	 private String value;
	 
	public ModuleQueryColumn() {
		super();
	}
	public ModuleQueryColumn(String moduleId, String columnId, String columnName,
			String xType, int orderNo,String value) {
		super();
		this.moduleId = moduleId;
		this.columnId = columnId;
		this.columnName = columnName;
		this.xType = xType;
		this.orderNo = orderNo;
		this.value = value;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getxType() {
		return xType;
	}
	public void setxType(String xType) {
		this.xType = xType;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	 
	 
}
