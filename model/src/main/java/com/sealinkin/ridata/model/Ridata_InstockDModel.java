package com.sealinkin.ridata.model;

import java.util.Date;

import com.sealinkin.ridata.po.Ridata_InstockD;

public class Ridata_InstockDModel extends Ridata_InstockD{

	private static final long serialVersionUID = 1L;
	private String instockNo;
    private String warehouseNo;
    private String ownerNo;
    private long instockId;
    private String articleName;
    //private String spec;
    private String barcode;
    //private String unit;
    //private String realBox;
    //private String realDis;
    private Date produceDate;
    private String workerName;
    private String userId;
    private String lotNo;
    private String qualityText;
    
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
    
	
	
	public Double getRealBox() {
		return realBox;
	}
	public Double getRealDis() {
		return realDis;
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
	public void setRealBox(Double realBox) {
		this.realBox = realBox;
	}
	public void setRealDis(Double realDis) {
		this.realDis = realDis;
	}
	public String getInstockNo() {
		return instockNo;
	}
	public void setInstockNo(String instockNo) {
		this.instockNo = instockNo;
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
	public long getInstockId() {
		return instockId;
	}
	public void setInstockId(long instockId) {
		this.instockId = instockId;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/*public String getRealBox() {
		return realBox;
	}
	public void setRealBox(String realBox) {
		this.realBox = realBox;
	}
	public String getRealDis() {
		return realDis;
	}
	public void setRealDis(String realDis) {
		this.realDis = realDis;
	}*/
	public Date getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getQualityText() {
		return qualityText;
	}
	public void setQualityText(String qualityText) {
		this.qualityText = qualityText;
	}
	
}
