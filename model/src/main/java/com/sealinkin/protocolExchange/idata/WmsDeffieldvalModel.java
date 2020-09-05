package com.sealinkin.protocolExchange.idata;

/** 码表model
 * haungb 20160712
 */
public class WmsDeffieldvalModel{
	
	private String tableName;
    private String colname;
    private String value;
    private String text;
    private Double sortOrder;
    private String memo;
    private String status;
    
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColname() {
		return colname;
	}
	public void setColname(String colname) {
		this.colname = colname;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Double getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Double sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
