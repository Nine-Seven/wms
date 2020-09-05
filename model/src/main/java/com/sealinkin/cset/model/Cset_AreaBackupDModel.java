package com.sealinkin.cset.model;

import com.sealinkin.cset.po.Cset_AreaBackupD;


public class Cset_AreaBackupDModel extends Cset_AreaBackupD{
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String lineId;
	private String wareNo;
	private String areaNo;
	private Integer ALevel;
	private String stockNo;
	
	private String areaName;
	private String wareName;
	private String mergerFlagText;
	private String stockFlagText;
	private String floorFlagText;
	private String bayFlagText;
	private String sortFlagText;
	private String stockxFlagText;
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getWareNo() {
		return wareNo;
	}
	public void setWareNo(String wareNo) {
		this.wareNo = wareNo;
	}
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public Integer getALevel() {
		return ALevel;
	}
	public void setALevel(Integer aLevel) {
		ALevel = aLevel;
	}
	public String getStockNo() {
		return stockNo;
	}
	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getWareName() {
		return wareName;
	}
	public void setWareName(String wareName) {
		this.wareName = wareName;
	}
	public String getMergerFlagText() {
		return mergerFlagText;
	}
	public void setMergerFlagText(String mergerFlagText) {
		this.mergerFlagText = mergerFlagText;
	}
	public String getStockFlagText() {
		return stockFlagText;
	}
	public void setStockFlagText(String stockFlagText) {
		this.stockFlagText = stockFlagText;
	}
	public String getFloorFlagText() {
		return floorFlagText;
	}
	public void setFloorFlagText(String floorFlagText) {
		this.floorFlagText = floorFlagText;
	}
	public String getBayFlagText() {
		return bayFlagText;
	}
	public void setBayFlagText(String bayFlagText) {
		this.bayFlagText = bayFlagText;
	}
	public String getSortFlagText() {
		return sortFlagText;
	}
	public void setSortFlagText(String sortFlagText) {
		this.sortFlagText = sortFlagText;
	}
	public String getStockxFlagText() {
		return stockxFlagText;
	}
	public void setStockxFlagText(String stockxFlagText) {
		this.stockxFlagText = stockxFlagText;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	
}
