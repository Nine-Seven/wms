package com.sealinkin.protocolExchange.bdef;

import java.io.Serializable;



/**
 * 拣货位采集》 传入参数model
 * @author huangb 20160525
 *
 */
@SuppressWarnings("serial")
public class ReqPickCellByArticleModel implements Serializable{
	private String enterpriseNo;
	private String WarehouseNo;
    private String ownerNo;
	private String articleNo;
    private String articleName;
    private String cellNo;
    private String pickType;
    private String packingQty;
    private String packingUnit;
    private String scanNo;//huangb 20160525
    private String lineId;//huangb 20160525
    
    private Double maxQtyA;
    private Double alertQtyA;
    private Double suppQtyA;
    private Double keepCellsA;

    private Double maxQtyNa;
    private Double alertQtyNa;
    private Double suppQtyNa;
    private Double keepCells;
    
    private String userId;
    
    
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getWarehouseNo() {
		return WarehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		WarehouseNo = warehouseNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getPickType() {
		return pickType;
	}
	public void setPickType(String pickType) {
		this.pickType = pickType;
	}
	public String getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(String packingQty) {
		this.packingQty = packingQty;
	}	
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public Double getMaxQtyA() {
		return maxQtyA;
	}
	public void setMaxQtyA(Double maxQtyA) {
		this.maxQtyA = maxQtyA;
	}
	public Double getAlertQtyA() {
		return alertQtyA;
	}
	public void setAlertQtyA(Double alertQtyA) {
		this.alertQtyA = alertQtyA;
	}
	public Double getSuppQtyA() {
		return suppQtyA;
	}
	public void setSuppQtyA(Double suppQtyA) {
		this.suppQtyA = suppQtyA;
	}
	public Double getKeepCellsA() {
		return keepCellsA;
	}
	public void setKeepCellsA(Double keepCellsA) {
		this.keepCellsA = keepCellsA;
	}
	public Double getMaxQtyNa() {
		return maxQtyNa;
	}
	public void setMaxQtyNa(Double maxQtyNa) {
		this.maxQtyNa = maxQtyNa;
	}
	public Double getAlertQtyNa() {
		return alertQtyNa;
	}
	public void setAlertQtyNa(Double alertQtyNa) {
		this.alertQtyNa = alertQtyNa;
	}
	public Double getSuppQtyNa() {
		return suppQtyNa;
	}
	public void setSuppQtyNa(Double suppQtyNa) {
		this.suppQtyNa = suppQtyNa;
	}
	public Double getKeepCells() {
		return keepCells;
	}
	public void setKeepCells(Double keepCells) {
		this.keepCells = keepCells;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getScanNo() {
		return scanNo;
	}
	public void setScanNo(String scanNo) {
		this.scanNo = scanNo;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}		
}
