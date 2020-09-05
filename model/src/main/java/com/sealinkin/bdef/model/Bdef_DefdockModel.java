package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_Defdock;

public class Bdef_DefdockModel extends Bdef_Defdock{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String warehouseNo;
	private String dockNo;
	private String dockTypeText;
	private String adjustBoardText;
	private String locateTypeText;
	
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getDockNo() {
		return dockNo;
	}
	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}
	public String getDockTypeText() {
		return dockTypeText;
	}
	public void setDockTypeText(String dockTypeText) {
		this.dockTypeText = dockTypeText;
	}
	public String getAdjustBoardText() {
		return adjustBoardText;
	}
	public void setAdjustBoardText(String adjustBoardText) {
		this.adjustBoardText = adjustBoardText;
	}
	public String getLocateTypeText() {
		return locateTypeText;
	}
	public void setLocateTypeText(String locateTypeText) {
		this.locateTypeText = locateTypeText;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}	
}
