package com.sealinkin.wms.model;

import com.sealinkin.wms.po.Wms_JobConfig;

public class Wms_JobConfigModel extends Wms_JobConfig{
	
	private static final long serialVersionUID = 1L;
	
	private String warehouseNo;
	private String enterpriseNo;
	private String procName;
	private String executeStatusText;
	
	private String runCountTypeText;
	
	
	
	
	public String getRunCountTypeText() {
		return runCountTypeText;
	}
	public void setRunCountTypeText(String runCountTypeText) {
		this.runCountTypeText = runCountTypeText;
	}
	public String getExecuteStatusText() {
		return executeStatusText;
	}
	public void setExecuteStatusText(String executeStatusText) {
		this.executeStatusText = executeStatusText;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getProcName() {
		return procName;
	}
	public void setProcName(String procName) {
		this.procName = procName;
	}
	

}
