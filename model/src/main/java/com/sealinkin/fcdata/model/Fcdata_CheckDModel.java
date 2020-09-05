package com.sealinkin.fcdata.model;

import com.sealinkin.fcdata.po.Fcdata_CheckD;

public class Fcdata_CheckDModel extends Fcdata_CheckD{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String warehouseNo;
	private String checkNo;
	private Integer rowId;
	
	private String wareNo;
	private String areaNo;
	private String stockNo;
	private String articleName;
	private double guarantee;//保质期（天数）
	private String rgstName;
	private String fcdataType;
	private String printType;
	private String field;
	private String checkType;
	private Boolean difFlag;
	private String qualityText;
	private String differentFlagText;
    private String ownerArticleNo;
    private String lotType;
    
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
	private Double rePlanBox;//计划箱数
	private Double rePlanQmin;//计划散数
	private Double rePlanDis;//计划中包数
	private Double thirdPlanBox;//计划箱数
	private Double thirdPlanqQmin;//计划散数
	private Double thirdPlanDis;//计划中包数
    
	
	public Double getQminOperatePacking() {
		return qminOperatePacking;
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
	public Double getPlanQmin() {
		return planQmin;
	}
	public void setPlanQmin(Double planQmin) {
		this.planQmin = planQmin;
	}
	public Double getRePlanQmin() {
		return rePlanQmin;
	}
	public void setRePlanQmin(Double rePlanQmin) {
		this.rePlanQmin = rePlanQmin;
	}
	public Double getThirdPlanqQmin() {
		return thirdPlanqQmin;
	}
	public void setThirdPlanqQmin(Double thirdPlanqQmin) {
		this.thirdPlanqQmin = thirdPlanqQmin;
	}
	public void setQminOperatePacking(Double qminOperatePacking) {
		this.qminOperatePacking = qminOperatePacking;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public double getGuarantee() {
		return guarantee;
	}
	public void setGuarantee(double guarantee) {
		this.guarantee = guarantee;
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
	public Double getRePlanBox() {
		return rePlanBox;
	}
	public void setRePlanBox(Double rePlanBox) {
		this.rePlanBox = rePlanBox;
	}
	public Double getRePlanDis() {
		return rePlanDis;
	}
	public void setRePlanDis(Double rePlanDis) {
		this.rePlanDis = rePlanDis;
	}
	public Double getThirdPlanBox() {
		return thirdPlanBox;
	}
	public void setThirdPlanBox(Double thirdPlanBox) {
		this.thirdPlanBox = thirdPlanBox;
	}
	public Double getThirdPlanDis() {
		return thirdPlanDis;
	}
	public void setThirdPlanDis(Double thirdPlanDis) {
		this.thirdPlanDis = thirdPlanDis;
	}
	public String getRgstName() {
		return rgstName;
	}
	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}
	public String getFcdataType() {
		return fcdataType;
	}
	public void setFcdataType(String fcdataType) {
		this.fcdataType = fcdataType;
	}
	public String getPrintType() {
		return printType;
	}
	public void setPrintType(String printType) {
		this.printType = printType;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
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
	public String getStockNo() {
		return stockNo;
	}
	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public Boolean getDifFlag() {
		return difFlag;
	}
	public void setDifFlag(Boolean difFlag) {
		this.difFlag = difFlag;
	}
	public String getQualityText() {
		return qualityText;
	}
	public void setQualityText(String qualityText) {
		this.qualityText = qualityText;
	}
	public String getDifferentFlagText() {
		return differentFlagText;
	}
	public void setDifferentFlagText(String differentFlagText) {
		this.differentFlagText = differentFlagText;
	}
	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}
	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}
	public String getLotType() {
		return lotType;
	}
	public void setLotType(String lotType) {
		this.lotType = lotType;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	
}
