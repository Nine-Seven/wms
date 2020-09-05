package com.sealinkin.stock.model;

import com.sealinkin.stock.po.Stock_LabelD;

public class Stock_LabelDModel extends Stock_LabelD{

	private static final long serialVersionUID = 1L;
	
	private String warehouseNo;
	private String containerNo;
	private long rowId;
	
	private String labelNo;
	private String articleName;
	private String barcode;
	private String poBox;
	private String poDis;
	private String ownerArticleNo;
	
	private String rsvAttr1;
	private String rsvAttr2;
	private String rsvAttr3;
	private String rsvAttr4;
	private String rsvAttr5;
	private String rsvAttr6;
	private String rsvAttr7;
	private String subLabelNo;
	
	private String packingUnit;//箱包装单位
	private String packingSpec;//箱包装规格
	private Double unitPacking;//基本包装数量
	private String packingUnitQmin;//中包装单位
	private String Unit;//基本包装单位
	private String packingSpecQmin;//中包装规格
	private String spec;//基本包装规格
	private Double planBox;//计划箱数
	private Double planDis;//计划散数
	private Double planQmin;//计划中包数
	private Double realBox;//实际箱数
	private Double realDis;//实际散数
	private Double realQmin;//实际中包数
	
	
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
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getContainerNo() {
		return containerNo;
	}
	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}
	public long getRowId() {
		return rowId;
	}
	public void setRowId(long rowId) {
		this.rowId = rowId;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
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
	public String getPoBox() {
		return poBox;
	}
	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}
	public String getPoDis() {
		return poDis;
	}
	public void setPoDis(String poDis) {
		this.poDis = poDis;
	}
	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}
	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}
	public String getRsvAttr1() {
		return rsvAttr1;
	}
	public void setRsvAttr1(String rsvAttr1) {
		this.rsvAttr1 = rsvAttr1;
	}
	public String getRsvAttr2() {
		return rsvAttr2;
	}
	public void setRsvAttr2(String rsvAttr2) {
		this.rsvAttr2 = rsvAttr2;
	}
	public String getRsvAttr3() {
		return rsvAttr3;
	}
	public void setRsvAttr3(String rsvAttr3) {
		this.rsvAttr3 = rsvAttr3;
	}
	public String getRsvAttr4() {
		return rsvAttr4;
	}
	public void setRsvAttr4(String rsvAttr4) {
		this.rsvAttr4 = rsvAttr4;
	}
	public String getRsvAttr5() {
		return rsvAttr5;
	}
	public void setRsvAttr5(String rsvAttr5) {
		this.rsvAttr5 = rsvAttr5;
	}
	public String getRsvAttr6() {
		return rsvAttr6;
	}
	public void setRsvAttr6(String rsvAttr6) {
		this.rsvAttr6 = rsvAttr6;
	}
	public String getRsvAttr7() {
		return rsvAttr7;
	}
	public void setRsvAttr7(String rsvAttr7) {
		this.rsvAttr7 = rsvAttr7;
	}
	public String getSubLabelNo() {
		return subLabelNo;
	}
	public void setSubLabelNo(String subLabelNo) {
		this.subLabelNo = subLabelNo;
	}
	public String getPackingSpec() {
		return packingSpec;
	}
	public void setPackingSpec(String packingSpec) {
		this.packingSpec = packingSpec;
	}
	
}
