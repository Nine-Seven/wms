package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_WmsDefbase;



public class Bdef_WmsDefbaseModel extends Bdef_WmsDefbase{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String colname;
	private String groupNo;
	private String subGroupNo;
	private String useLevel;
	private String sdefine;
	private Double ndefine;
	private String subGroupNoText;
	private String sdefineText;
	private String groupNoText;
	
	public String getSubGroupNoText() {
		return subGroupNoText;
	}
	public void setSubGroupNoText(String subGroupNoText) {
		this.subGroupNoText = subGroupNoText;
	}
	public String getSdefineText() {
		return sdefineText;
	}
	public void setSdefineText(String sdefineText) {
		this.sdefineText = sdefineText;
	}
	public String getGroupNoText() {
		return groupNoText;
	}
	public void setGroupNoText(String groupNoText) {
		this.groupNoText = groupNoText;
	}
	private String memo;
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
	public Double getNdefine() {
		return ndefine;
	}
	public void setNdefine(Double ndefine) {
		this.ndefine = ndefine;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	

}
