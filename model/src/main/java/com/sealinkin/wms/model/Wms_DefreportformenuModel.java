package com.sealinkin.wms.model;

import com.sealinkin.wms.po.Wms_Defreportformenu;

public class Wms_DefreportformenuModel extends Wms_Defreportformenu{

	private static final long serialVersionUID = 1L;
	
	private String moduleId;
	private String pgmId;
	private String enterpriseNo;
	
	private String procName;
	private String showText;

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getPgmId() {
		return pgmId;
	}

	public void setPgmId(String pgmId) {
		this.pgmId = pgmId;
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public String getShowText() {
		return showText;
	}

	public void setShowText(String showText) {
		this.showText = showText;
	}

	public String getEnterpriseNo() {
		return enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	
}
