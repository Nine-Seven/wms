package com.sealinkin.protocolExchange.print;

import java.io.Serializable;

/**
 * 获取字段信息请求 请求  StuReqFieldDesc
 * @author lich
 *
 */
public class CProgramDescModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fieldId;
	private String fieldName;
	private String toolTips;
	private String sfieldName;
	private String stoolTips;
	private int descFlag;
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getToolTips() {
		return toolTips;
	}
	public void setToolTips(String toolTips) {
		this.toolTips = toolTips;
	}
	public String getSfieldName() {
		return sfieldName;
	}
	public void setSfieldName(String sfieldName) {
		this.sfieldName = sfieldName;
	}
	public String getStoolTips() {
		return stoolTips;
	}
	public void setStoolTips(String stoolTips) {
		this.stoolTips = stoolTips;
	}
	public int getDescFlag() {
		return descFlag;
	}
	public void setDescFlag(int descFlag) {
		this.descFlag = descFlag;
	}
}
