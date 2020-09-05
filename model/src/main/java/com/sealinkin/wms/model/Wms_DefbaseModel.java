package com.sealinkin.wms.model;

import com.sealinkin.wms.po.Wms_Defbase;

public class Wms_DefbaseModel extends Wms_Defbase{
	
	private static final long serialVersionUID = 1L;
	
	private String colname;
	private String groupNo;
	private String subGroupNo;
	private String useLevel;
	private String sdefine;
	private double ndefine;
	private String memo;
	
	private String moduleId;//模块id
	private String fieldName;//字段名字
	private String flag;//状态
	
	
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getColname() {
		return colname;
	}
	public void setColname(String colname) {
		this.colname = colname;
	}
	public String getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	public String getSubGroupNo() {
		return subGroupNo;
	}
	public void setSubGroupNo(String subGroupNo) {
		this.subGroupNo = subGroupNo;
	}
	public String getUseLevel() {
		return useLevel;
	}
	public void setUseLevel(String useLevel) {
		this.useLevel = useLevel;
	}
	public String getSdefine() {
		return sdefine;
	}
	public void setSdefine(String sdefine) {
		this.sdefine = sdefine;
	}
	public double getNdefine() {
		return ndefine;
	}
	public void setNdefine(double ndefine) {
		this.ndefine = ndefine;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	

}
