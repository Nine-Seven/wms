package com.sealinkin.stock.model;


import com.sealinkin.stock.po.Stock_ConfirmD;

public class Stock_ConfirmDModel extends Stock_ConfirmD{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String confirmNo;
	private Long rowId;
	private String articleName;
	private String barcode;
	private String ownerArticleNo;
	//private String unit;
	private Double pobox;
	private Double popcs;
	private Double realPobox;
	private Double realPopcs;
	
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
	private Double articleQty;		//计划数量
	
	
	
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
	public Double getArticleQty() {
		return articleQty;
	}
	public void setArticleQty(Double articleQty) {
		this.articleQty = articleQty;
	}
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
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getConfirmNo() {
		return confirmNo;
	}
	public void setConfirmNo(String confirmNo) {
		this.confirmNo = confirmNo;
	}
	public Long getRowId() {
		return rowId;
	}
	public void setRowId(Long rowId) {
		this.rowId = rowId;
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
	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}
	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getPobox() {
		return pobox;
	}
	public void setPobox(Double pobox) {
		this.pobox = pobox;
	}
	public Double getPopcs() {
		return popcs;
	}
	public void setPopcs(Double popcs) {
		this.popcs = popcs;
	}
	public Double getRealPobox() {
		return realPobox;
	}
	public void setRealPobox(Double realPobox) {
		this.realPobox = realPobox;
	}
	public Double getRealPopcs() {
		return realPopcs;
	}
	public void setRealPopcs(Double realPopcs) {
		this.realPopcs = realPopcs;
	}
}
