package com.sealinkin.bset.model;

import java.util.Date;

import com.sealinkin.bset.po.Bill_Expenses_List;

public class Bill_Expenses_ListModel extends Bill_Expenses_List {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String ownerNo;
	private String billingProject;
	private String groupNo;
	private Date beginDate;
	private Date endDate;
	private Double area;
	private Double tray;
	private Double qty;
	private Double volume;
	private Double weight;
	private Double reserved1;
	private Double reserved2;
	private Double reserved3;
	private Double reserved4;
	private Double reserved5;
	private Double reserved6;
	private Integer serialNo;
	private String billingType;
	private String flag;
	private Double value;

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
	public String getBillingProject() {
		return billingProject;
	}
	public void setBillingProject(String billingProject) {
		this.billingProject = billingProject;
	}
	public String getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public Double getTray() {
		return tray;
	}
	public void setTray(Double tray) {
		this.tray = tray;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getReserved1() {
		return reserved1;
	}
	public void setReserved1(Double reserved1) {
		this.reserved1 = reserved1;
	}
	public Double getReserved2() {
		return reserved2;
	}
	public void setReserved2(Double reserved2) {
		this.reserved2 = reserved2;
	}
	public Double getReserved3() {
		return reserved3;
	}
	public void setReserved3(Double reserved3) {
		this.reserved3 = reserved3;
	}
	public Double getReserved4() {
		return reserved4;
	}
	public void setReserved4(Double reserved4) {
		this.reserved4 = reserved4;
	}
	public Double getReserved5() {
		return reserved5;
	}
	public void setReserved5(Double reserved5) {
		this.reserved5 = reserved5;
	}
	public Double getReserved6() {
		return reserved6;
	}
	public void setReserved6(Double reserved6) {
		this.reserved6 = reserved6;
	}
	public Integer getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	public String getBillingType() {
		return billingType;
	}
	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
}
