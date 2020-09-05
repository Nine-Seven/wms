package com.sealinkin.sodata.model;

import com.sealinkin.sodata.po.Sodata_WasteD;

public class Sodata_WasteDModel extends Sodata_WasteD{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String wasteNo;
	private Short poId;
	
	private Double poBox;
	private Double poPcs;
	private Double realPcs;
	
	private String ownerArticleNo;
	private String barcode;
	private String articleName;
	
	
	private Double qminOperatePacking;//最小操作包装数量
	private Double unitPacking;//基本包装数量
	private String packingUnit;//包装单位
	private String packingUnitQmin;//中包装单位
	private String Unit;//基本包装单位
	private String packingSpec;//包装规格
	private String packingSpecQmin;//中包装规格
	private String spec;//基本包装规格
	private Double planBox;//计划箱数
	private Double planDis;//计划散数
	private Double planQmin;//计划中包数
	private Double realBox;//实际箱数
	private Double realDis;//实际散数
	private Double realQmin;//实际中包数
	
	
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
	public String getPackingUnitQmin() {
		return packingUnitQmin;
	}
	public void setPackingUnitQmin(String packingUnitQmin) {
		this.packingUnitQmin = packingUnitQmin;
	}
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	public String getPackingSpec() {
		return packingSpec;
	}
	public void setPackingSpec(String packingSpec) {
		this.packingSpec = packingSpec;
	}
	public String getPackingSpecQmin() {
		return packingSpecQmin;
	}
	public void setPackingSpecQmin(String packingSpecQmin) {
		this.packingSpecQmin = packingSpecQmin;
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
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getWasteNo() {
		return wasteNo;
	}
	public void setWasteNo(String wasteNo) {
		this.wasteNo = wasteNo;
	}
	public Short getPoId() {
		return poId;
	}
	public void setPoId(Short poId) {
		this.poId = poId;
	}
	
	public Double getPoBox() {
		return poBox;
	}
	public void setPoBox(Double poBox) {
		this.poBox = poBox;
	}
	public Double getPoPcs() {
		return poPcs;
	}
	public void setPoPcs(Double poPcs) {
		this.poPcs = poPcs;
	}
	public Double getRealBox() {
		return realBox;
	}
	public void setRealBox(Double realBox) {
		this.realBox = realBox;
	}
	public Double getRealPcs() {
		return realPcs;
	}
	public void setRealPcs(Double realPcs) {
		this.realPcs = realPcs;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}
	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	
	
}
