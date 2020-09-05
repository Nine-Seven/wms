package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_Defloc;

public class Bdef_DeflocModel extends Bdef_Defloc{

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private boolean flag;
	
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	private String creatFlagText;
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getCreatFlagText() {
		return creatFlagText;
	}
	public void setCreatFlagText(String creatFlagText) {
		this.creatFlagText = creatFlagText;
	}
}
