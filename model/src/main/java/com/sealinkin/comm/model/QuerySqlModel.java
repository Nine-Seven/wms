package com.sealinkin.comm.model;

public class QuerySqlModel {
	 private String columnId;//字段
	 private String condition;//条件
    /*{id:'1',name:'等于'},
	  {id:'2',name:'大于'},
	  {id:'3',name:'小于'},
	  {id:'4',name:'大于等于'},
	  {id:'5',name:'小于等于'},
	  {id:'6',name:'不等�?},
	  {id:'7',name:'包含'}*/
	 private String value;//数�?
	 private String logic;//逻辑
	 private String valueType;//类型1：string 2:int 3:日期……
	 
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLogic() {
		return logic;
	}
	public void setLogic(String logic) {
		this.logic = logic;
	}
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
}
