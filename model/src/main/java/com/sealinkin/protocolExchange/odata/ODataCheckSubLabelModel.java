package com.sealinkin.protocolExchange.odata;

public class ODataCheckSubLabelModel {
	private String labelNo;//标签号 
	private String status;//标签状态
	private String statusText;//标签状态描述
	private Integer subLabelCount;//子标签个数
	private String subLabelNo;//子标签号 
	private String itemCount;//子标签品项数
	private String itemTotalQty;//子标签总数量 
	private Integer NUMID;//序号 
	


	public Integer getNUMID() {
		return NUMID;
	}
	public void setNUMID(Integer nUMID) {
		NUMID = nUMID;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public Integer getSubLabelCount() {
		return subLabelCount;
	}
	public void setSubLabelCount(Integer subLabelCount) {
		this.subLabelCount = subLabelCount;
	}
	public String getSubLabelNo() {
		return subLabelNo;
	}
	public void setSubLabelNo(String subLabelNo) {
		this.subLabelNo = subLabelNo;
	}
	public String getItemCount() {
		return itemCount;
	}
	public void setItemCount(String itemCount) {
		this.itemCount = itemCount;
	}
	public String getItemTotalQty() {
		return itemTotalQty;
	}
	public void setItemTotalQty(String itemTotalQty) {
		this.itemTotalQty = itemTotalQty;
	} 
	
}
