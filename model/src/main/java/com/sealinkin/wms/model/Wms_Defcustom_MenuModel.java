package com.sealinkin.wms.model;

import com.sealinkin.wms.po.Wms_Defcustom_Menu;

public class Wms_Defcustom_MenuModel extends Wms_Defcustom_Menu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String showText;
	private String customName;
	private String moduleId;
	private String customId;
	private String enterpriseNo;
	
	public String getShowText() {
		return showText;
	}
	public void setShowText(String showText) {
		this.showText = showText;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
}
