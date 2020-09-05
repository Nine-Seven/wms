package com.sealinkin.wms.model;

import com.sealinkin.wms.po.Wms_DefSearch_D;

public class Wms_DefSearch_DModel extends Wms_DefSearch_D{
	private static final long serialVersionUID = 1L;
	private String pgmId;
	private String fieldId;
	private String enterpriseNo;
	private String statisticsFlagText;
	
	public String getPgmId() {
		return pgmId;
	}
	public void setPgmId(String pgmId) {
		this.pgmId = pgmId;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getStatisticsFlagText() {
		return statisticsFlagText;
	}
	public void setStatisticsFlagText(String statisticsFlagText) {
		this.statisticsFlagText = statisticsFlagText;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
}
