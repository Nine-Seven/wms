package com.sealinkin.protocolExchange.odata;

/** 标签重量MODEL
 *  huangb 20160720
 */
public class ODataLabelWeighModel {
	private String enterpriseNo; 
	private String warehouseNo;
	private String dockNo;
	private String labelNo;
	private Double weigh;//称重重量
	private String userId;
	
	private String status;//标签状态
	private String useType;//标签用途
	private String statusName;//标签状态备注
	private Double labelWeigh;//标签重量
	
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
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public Double getWeigh() {
		return weigh;
	}
	public void setWeigh(Double weigh) {
		this.weigh = weigh;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Double getLabelWeigh() {
		return labelWeigh;
	}
	public void setLabelWeigh(Double labelWeigh) {
		this.labelWeigh = labelWeigh;
	}
 
}
