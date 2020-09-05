package com.sealinkin.wms.model;

import com.sealinkin.wms.po.Wms_Pntsetmodulereportset;

public class Wms_PntsetModuleReportSetModel extends Wms_Pntsetmodulereportset{

private static final long serialVersionUID = 1L;
	
	private String moduleId;
	private String enterpriseNo;
	
	
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	
}
