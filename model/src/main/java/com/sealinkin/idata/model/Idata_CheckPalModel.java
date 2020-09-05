package com.sealinkin.idata.model;

import com.sealinkin.idata.po.Idata_CheckPal;

public class Idata_CheckPalModel extends Idata_CheckPal{

	private static final long serialVersionUID = 1L;
	
	private String warehouseNo;
	private String ownerNo;
	private String SCheckNo;
	private String checkNo;
	private short checkRowId;
	private String containerNo;
	private String labelNo;
	private String subLabelNo;
	
	private String articleName;
	private String packingUnit;
	private String spec;
	private String barcode;
	private Double pkQty;
	private String ownerArticleNo;
	private String poNo;
	private String temperature;
	private String packingUnitQmin;//中包装单位
	private String Unit;//基本包装单位
	private String packingSpec;//箱包装规格
	private String packingSpecQmin;//中包装规格
	private Double realBox;//实际箱数
	private Double realDis;//实际散数
	private Double realQmin;//实际中包数
	private Double planBox;//计划箱数
	private Double planDis;//计划散数
	private Double planQmin;//计划中包数
	private Double qminOperatePacking;//最小操作包装数量
	private Double unitPacking;//基本包装数量
	
	
	
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
	public String getSCheckNo() {
		return SCheckNo;
	}
	public void setSCheckNo(String sCheckNo) {
		SCheckNo = sCheckNo;
	}
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public short getCheckRowId() {
		return checkRowId;
	}
	public void setCheckRowId(short checkRowId) {
		this.checkRowId = checkRowId;
	}
	public String getContainerNo() {
		return containerNo;
	}
	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getSubLabelNo() {
		return subLabelNo;
	}
	public void setSubLabelNo(String subLabelNo) {
		this.subLabelNo = subLabelNo;
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
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public Double getPkQty() {
		return pkQty;
	}
	public void setPkQty(Double pkQty) {
		this.pkQty = pkQty;
	}
	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}
	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
		
}
