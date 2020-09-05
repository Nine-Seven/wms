package com.sealinkin.protocolExchange.ridata;

import java.io.Serializable;

public class RIUntreadNoAndWallNoModel implements Serializable{
	 /**
	 * 扫描反配单号
	 */
	private static final long serialVersionUID = 1L;
     private String enterpriseNo;
	 private String warehouseNo;
     private String ownerNo;
     private String SUntreadNo;
     private String poNo;
     private String wallNo;
     private String dockNo;
     private String userId;
     private String quality;
     
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
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getSUntreadNo() {
		return SUntreadNo;
	}
	public void setSUntreadNo(String sUntreadNo) {
		SUntreadNo = sUntreadNo;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getWallNo() {
		return wallNo;
	}
	public void setWallNo(String wallNo) {
		this.wallNo = wallNo;
	}
	public String getDockNo() {
		return dockNo;
	}
	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
         
}
