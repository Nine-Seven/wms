package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;
/**
 * 验收流水号请求 对应StuIMSerialNoRequest
 * @author lich
 *
 */
public class IdataSerialNoRequestModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String EnterpriseNo;
	private String WarehouseNo;
	private String OwnerPermissions;//货主权限
	private String SerialNo;
		
	public String getEnterpriseNo() {
		return EnterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		EnterpriseNo = enterpriseNo;
	}
	public String getWarehouseNo() {
		return WarehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		WarehouseNo = warehouseNo;
	}
	public String getOwnerPermissions() {
		return OwnerPermissions;
	}
	public void setOwnerPermissions(String ownerPermissions) {
		OwnerPermissions = ownerPermissions;
	}
	public String getSerialNo() {
		return SerialNo;
	}
	public void setSerialNo(String serialNo) {
		SerialNo = serialNo;
	}
}
