package com.sealinkin.odata.model;


import com.sealinkin.odata.po.Odata_ExpCancelD;

public class Odata_ExpCancelDModel extends Odata_ExpCancelD{
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String cancelNo;
	private String expNo;
	private String articleNo;
	
	private String ownerArticleNo;
	private String articleName;
	private String barcode;
	private Double differenceQty;//差异数量
	private Double availableQty;//可用数量
	private Double noEnoughQty;//缺少数量
	
	private Double realBox;//实际箱数
	private Double realDis;//实际散数
	private Double realQmin;//实际中包数
	private Double planBox;//计划箱数
	private Double planDis;//计划散数
	private Double planQmin;//计划中包数
	private String packingUnit;//箱包装单位
	private String packingUnitQmin;//中包装单位
	private String unit;//基本包装单位
	private String packingSpec;//箱包装规格
	private Double qminOperatePacking;//最小操作包装数量
	private Double unitPacking;//基本包装数量
	private String packingSpecQmin;//中包装规格
	private String spec;//基本包装规格
	
	private Double differenceBox;//差异箱数
	private Double differenceDis;//差异散数
	private Double differenceQmin;//差异中包数
	
	

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
	public String getCancelNo() {
		return cancelNo;
	}
	public void setCancelNo(String cancelNo) {
		this.cancelNo = cancelNo;
	}
	public String getExpNo() {
		return expNo;
	}
	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}
	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
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
	public Double getDifferenceQty() {
		return differenceQty;
	}
	public void setDifferenceQty(Double differenceQty) {
		this.differenceQty = differenceQty;
	}
	public Double getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(Double availableQty) {
		this.availableQty = availableQty;
	}
	public Double getNoEnoughQty() {
		return noEnoughQty;
	}
	public void setNoEnoughQty(Double noEnoughQty) {
		this.noEnoughQty = noEnoughQty;
	}
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
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public String getPackingUnitQmin() {
		return packingUnitQmin;
	}
	public void setPackingUnitQmin(String packingUnitQmin) {
		this.packingUnitQmin = packingUnitQmin;
	}
	public String getPackingSpec() {
		return packingSpec;
	}
	public void setPackingSpec(String packingSpec) {
		this.packingSpec = packingSpec;
	}
	public Double getQminOperatePacking() {
		return qminOperatePacking;
	}
	public void setQminOperatePacking(Double qminOperatePacking) {
		this.qminOperatePacking = qminOperatePacking;
	}
	public Double getUnitPacking() {
		return unitPacking;
	}
	public void setUnitPacking(Double unitPacking) {
		this.unitPacking = unitPacking;
	}
	public String getPackingSpecQmin() {
		return packingSpecQmin;
	}
	public void setPackingSpecQmin(String packingSpecQmin) {
		this.packingSpecQmin = packingSpecQmin;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public Double getDifferenceBox() {
		return differenceBox;
	}
	public void setDifferenceBox(Double differenceBox) {
		this.differenceBox = differenceBox;
	}
	public Double getDifferenceDis() {
		return differenceDis;
	}
	public void setDifferenceDis(Double differenceDis) {
		this.differenceDis = differenceDis;
	}
	public Double getDifferenceQmin() {
		return differenceQmin;
	}
	public void setDifferenceQmin(Double differenceQmin) {
		this.differenceQmin = differenceQmin;
	}
	
}
