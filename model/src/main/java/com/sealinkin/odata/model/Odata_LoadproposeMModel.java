package com.sealinkin.odata.model;

import com.sealinkin.odata.po.Odata_LoadproposeM;

public class Odata_LoadproposeMModel extends Odata_LoadproposeM{

	private static final long serialVersionUID = 1L;
	
	private String warehouseNo;
	private String loadproposeNo;
	private String enterpriseNo;
	
	private String userId;
	private String paperUserId;
	private String custName;
	private String carNo;
	private String labelNo;
	private String currArea;
	private String deliverObj;
	private String loadOrder;
	private String carPlanNo;
	private String printFlag;
	private double deliverBox;

	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getLoadproposeNo() {
		return loadproposeNo;
	}
	public void setLoadproposeNo(String loadproposeNo) {
		this.loadproposeNo = loadproposeNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPaperUserId() {
		return paperUserId;
	}
	public void setPaperUserId(String paperUserId) {
		this.paperUserId = paperUserId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getCurrArea() {
		return currArea;
	}
	public void setCurrArea(String currArea) {
		this.currArea = currArea;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getDeliverObj() {
		return deliverObj;
	}
	public void setDeliverObj(String deliverObj) {
		this.deliverObj = deliverObj;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getLoadOrder() {
		return loadOrder;
	}
	public void setLoadOrder(String loadOrder) {
		this.loadOrder = loadOrder;
	}
	public String getCarPlanNo() {
		return carPlanNo;
	}
	public void setCarPlanNo(String carPlanNo) {
		this.carPlanNo = carPlanNo;
	}
	public String getPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}
	public double getDeliverBox() {
		return deliverBox;
	}
	public void setDeliverBox(double deliverBox) {
		this.deliverBox = deliverBox;
	}
	
}
