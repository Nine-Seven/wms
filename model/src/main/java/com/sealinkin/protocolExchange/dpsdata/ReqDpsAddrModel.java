package com.sealinkin.protocolExchange.dpsdata;

import java.io.Serializable;

/**
        电子标签数据   StuReqDpsAddr
 * @author lich
 *
 */
public class ReqDpsAddrModel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String EnterpriseNo;
	private String sessionID;           //对话ID 
    private String warehouseNo;         //仓库代码
    private int     useType;            //作业类型
    private String DeviceGroupNo;        //设备组号

    public String getEnterpriseNo() {
		return EnterpriseNo;
	}
	public void setEnterpriseNo(String EnterpriseNo) {
		this.EnterpriseNo = EnterpriseNo;
	}
    
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public int getUseType() {
		return useType;
	}
	public void setUseType(int useType) {
		this.useType = useType;
	}
	public String getDeviceGroupNo() {
		return DeviceGroupNo;
	}
	public void setDeviceGroupNo(String deviceGroupNo) {
		DeviceGroupNo = deviceGroupNo;
	}           
}
