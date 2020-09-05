/**
 * 模块名称：工作站与设备组关系维护
 * 模块编码：1U01
 * 创建：hcx 
 */
package com.sealinkin.bset.model;

import com.sealinkin.bset.po.Bset_WorkstationDivide;

public class Bset_WorkstationDivideModel extends Bset_WorkstationDivide{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String warehouseNo;
	private String deviceGroupNo;
	private String workstationNo;
	private String workstationName;
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
	public String getDeviceGroupNo() {
		return deviceGroupNo;
	}
	public void setDeviceGroupNo(String deviceGroupNo) {
		this.deviceGroupNo = deviceGroupNo;
	}
	public String getWorkstationNo() {
		return workstationNo;
	}
	public void setWorkstationNo(String workstationNo) {
		this.workstationNo = workstationNo;
	}
	public String getWorkstationName() {
		return workstationName;
	}
	public void setWorkstationName(String workstationName) {
		this.workstationName = workstationName;
	}
}
