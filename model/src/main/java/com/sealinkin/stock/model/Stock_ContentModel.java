package com.sealinkin.stock.model;

import java.util.Date;

import com.sealinkin.stock.po.Stock_Content;

public class Stock_ContentModel extends Stock_Content{
	
	private static final long serialVersionUID = 1L;
	
	private String warehouseNo;
	private String cellNo;
	private long cellId;
	
	private String articleName;
	private String barcode;
	private String unit;
	private String spec;
	private Date produceDate;
	private Date expireDate;
	private Double noEnoughQty;//缺量
	private Double recedeQty;//计划数量
	private Double availableQty;//可用数量
	//private Double scatterednum;//零散数量
	//private Double wholenum;//整件数
	private String SCellNo;
	private String containerNo;
	private String DCellNo;
	private String ownerArticleNo;
	private String labelNo;
	private String lotNo;
	private String quality;
	private String textQuality;
	private String alertQty;
	private String demandQty;
	private String maxQty;
	private String keepCell;
	private String budegtQty;
	private Double pkQty;//数量
	private Double pkOutstockQty;//预下数量
	private Double pkInstockQty;//预上数量
	private String packingUnit;//包装单位
	private String wareNo;
	private String wareName;
	private String areaNo;
	private String areaName;
	private Double planBox;//计划箱数
	private Double planDis;//计划散数
	private Double planQmin;//计划中包数
	private Double realBox;//可用箱数
	private Double realDis;//可用散数
	private Double realQmin;//可用中包数
	private Double qminOperatePacking;//最小操作包装数量
	
	private String packingSpec;//箱包装规格
	
	
	
	public Double getRealBox() {
		return realBox;
	}
	public void setRealBox(Double realBox) {
		this.realBox = realBox;
	}
	public Double getRealDis() {
		return realDis;
	}
	public void setRealDis(Double realDis) {
		this.realDis = realDis;
	}
	public Double getRealQmin() {
		return realQmin;
	}
	public void setRealQmin(Double realQmin) {
		this.realQmin = realQmin;
	}
	public Double getQminOperatePacking() {
		return qminOperatePacking;
	}
	public void setQminOperatePacking(Double qminOperatePacking) {
		this.qminOperatePacking = qminOperatePacking;
	}
	public Double getPlanBox() {
		return planBox;
	}
	public void setPlanBox(Double planBox) {
		this.planBox = planBox;
	}
	public Double getPlanDis() {
		return planDis;
	}
	public void setPlanDis(Double planDis) {
		this.planDis = planDis;
	}
	public Double getPlanQmin() {
		return planQmin;
	}
	public void setPlanQmin(Double planQmin) {
		this.planQmin = planQmin;
	}
	public String getPackingSpec() {
		return packingSpec;
	}
	public void setPackingSpec(String packingSpec) {
		this.packingSpec = packingSpec;
	}
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public Double getPkQty() {
		return pkQty;
	}
	public void setPkQty(Double pkQty) {
		this.pkQty = pkQty;
	}
	public Double getPkOutstockQty() {
		return pkOutstockQty;
	}
	public void setPkOutstockQty(Double pkOutstockQty) {
		this.pkOutstockQty = pkOutstockQty;
	}
	public Double getPkInstockQty() {
		return pkInstockQty;
	}
	public void setPkInstockQty(Double pkInstockQty) {
		this.pkInstockQty = pkInstockQty;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public long getCellId() {
		return cellId;
	}
	public void setCellId(long cellId) {
		this.cellId = cellId;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public Date getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public Double getNoEnoughQty() {
		return noEnoughQty;
	}
	public void setNoEnoughQty(Double noEnoughQty) {
		this.noEnoughQty = noEnoughQty;
	}
	public Double getRecedeQty() {
		return recedeQty;
	}
	public void setRecedeQty(Double recedeQty) {
		this.recedeQty = recedeQty;
	}
	public Double getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(Double availableQty) {
		this.availableQty = availableQty;
	}
	
	public String getSCellNo() {
		return SCellNo;
	}
	public void setSCellNo(String sCellNo) {
		SCellNo = sCellNo;
	}
	public String getContainerNo() {
		return containerNo;
	}
	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}
	public String getDCellNo() {
		return DCellNo;
	}
	public void setDCellNo(String dCellNo) {
		DCellNo = dCellNo;
	}
	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}
	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getTextQuality() {
		return textQuality;
	}
	public void setTextQuality(String textQuality) {
		this.textQuality = textQuality;
	}
	public String getAlertQty() {
		return alertQty;
	}
	public void setAlertQty(String alertQty) {
		this.alertQty = alertQty;
	}
	public String getDemandQty() {
		return demandQty;
	}
	public void setDemandQty(String demandQty) {
		this.demandQty = demandQty;
	}
	public String getMaxQty() {
		return maxQty;
	}
	public void setMaxQty(String maxQty) {
		this.maxQty = maxQty;
	}
	public String getKeepCell() {
		return keepCell;
	}
	public void setKeepCell(String keepCell) {
		this.keepCell = keepCell;
	}
	public String getBudegtQty() {
		return budegtQty;
	}
	public void setBudegtQty(String budegtQty) {
		this.budegtQty = budegtQty;
	}
	public String getWareNo() {
		return wareNo;
	}
	public void setWareNo(String wareNo) {
		this.wareNo = wareNo;
	}
	public String getWareName() {
		return wareName;
	}
	public void setWareName(String wareName) {
		this.wareName = wareName;
	}
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
}
