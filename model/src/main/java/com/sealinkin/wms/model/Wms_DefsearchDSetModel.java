package com.sealinkin.wms.model;

import com.sealinkin.wms.po.Wms_DefsearchDSet;

public class Wms_DefsearchDSetModel extends Wms_DefsearchDSet{

	private static final long serialVersionUID = 1L;
	
	private String pgmId;
	private String name;
	private String fieldId;
	private String rgstName;
	private String fieldName;
	private Boolean showFlag;
	private Boolean statisticsFlagText;
	private String fieldType;
	public String getPgmId() {
		return pgmId;
	}
	public void setPgmId(String pgmId) {
		this.pgmId = pgmId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getRgstName() {
		return rgstName;
	}
	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Boolean getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(Boolean showFlag) {
		this.showFlag = showFlag;
	}
	public Boolean getStatisticsFlagText() {
		return statisticsFlagText;
	}
	public void setStatisticsFlagText(Boolean statisticsFlagText) {
		this.statisticsFlagText = statisticsFlagText;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}	
}
