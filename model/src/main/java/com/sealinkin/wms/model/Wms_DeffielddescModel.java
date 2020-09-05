package com.sealinkin.wms.model;

import com.sealinkin.wms.po.Wms_Deffielddesc;

public class Wms_DeffielddescModel extends Wms_Deffielddesc{

	private static final long serialVersionUID = 1L;
	
	private String tableName;
	private String fieldId;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	
	

}
