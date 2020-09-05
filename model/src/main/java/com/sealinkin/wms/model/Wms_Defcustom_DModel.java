package com.sealinkin.wms.model;

import com.sealinkin.wms.po.Wms_DefcustomD;

public class Wms_Defcustom_DModel extends Wms_DefcustomD {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customId;
	private String paramName;
	private String enterpriseNo;
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
}
