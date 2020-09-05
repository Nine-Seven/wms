package com.sealinkin.protocolExchange.ridata;

import java.io.Serializable;

public class RICloseBoxModel implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
    private String warehouseNo;
    private String dockNo;
    private String userId;
    private String wallNo;
    private String labelId;
    private String destCellNo;
    private String labelNo;
    private String ownerNo;
    private String qualityFlag;
    private String SCheckNo;
    private String SUntreadNo;
    
    private String classType;
    private String untreadType;
    
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
	public String getWallNo() {
		return wallNo;
	}
	public void setWallNo(String wallNo) {
		this.wallNo = wallNo;
	}
	public String getLabelId() {
		return labelId;
	}
	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}
	public String getDestCellNo() {
		return destCellNo;
	}
	public void setDestCellNo(String destCellNo) {
		this.destCellNo = destCellNo;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getQualityFlag() {
		return qualityFlag;
	}
	public void setQualityFlag(String qualityFlag) {
		this.qualityFlag = qualityFlag;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getUntreadType() {
		return untreadType;
	}
	public void setUntreadType(String untreadType) {
		this.untreadType = untreadType;
	}
	public String getSCheckNo() {
		return SCheckNo;
	}
	public void setSCheckNo(String sCheckNo) {
		SCheckNo = sCheckNo;
	}
	public String getSUntreadNo() {
		return SUntreadNo;
	}
	public void setSUntreadNo(String sUntreadNo) {
		SUntreadNo = sUntreadNo;
	}
     
     
}
