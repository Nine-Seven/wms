package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;
/**
 * 上架扫描标签号 对应客户端StuIMInStockLabelRequest
 * @author lich
 *
 */
public class IdataInStockLabelRequestModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String OwnerPermissions;//货主权限	
    private String labelNo; //板号
    
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
	public String getOwnerPermissions() {
		return OwnerPermissions;
	}
	public void setOwnerPermissions(String ownerPermissions) {
		OwnerPermissions = ownerPermissions;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
}
