package com.sealinkin.cset.model;

import com.sealinkin.cset.po.Cset_AreaBackupM;

public class Cset_AreaBackupMModel extends Cset_AreaBackupM{
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private Integer lineId;
	private String defaultFlagText;
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public String getDefaultFlagText() {
		return defaultFlagText;
	}
	public void setDefaultFlagText(String defaultFlagText) {
		this.defaultFlagText = defaultFlagText;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	
}
