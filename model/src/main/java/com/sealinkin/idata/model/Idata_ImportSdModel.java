package com.sealinkin.idata.model;

import java.util.Date;

import com.sealinkin.idata.po.Idata_ImportSd;

public class Idata_ImportSdModel extends Idata_ImportSd{

	private static final long serialVersionUID = 1L;
	
	private String SImportNo;
	private String warehouseNo;
	private String ownerNo;
	private short rowId;
	
	private String importNo;
	private String articleName;
	private String barcode;
	private Date produceDate;
	private Date expireDate;
	private String lotNo;
	private Double pkQty;
	private Double checkQty;
	private Integer expiryDays;
	private Double alarmrate;
	private Double freezerate;
	private Double checkExcess;
	private Double noCheckQty;
	private String wareArea;
	private Double inQty;
	private String boxPickType;
	private String disPickType;
	private Double qpalette;
	
	private String ownerArticleNo;
	private String lotType;
	private String excessQty;
	private String temperatureFlag;
	private String packingUnit;//箱包装单位
	private String packingUnitQmin;//中包装单位
	private String Unit;//基本包装单位
	private String packingSpec;//箱包装规格
	private String packingSpecQmin;//中包装规格
	private String spec;//基本包装规格
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
	public String getTemperatureFlag() {
		return temperatureFlag;
	}
	public void setTemperatureFlag(String temperatureFlag) {
		this.temperatureFlag = temperatureFlag;
	}
	public String getSImportNo() {
		return SImportNo;
	}
	public void setSImportNo(String sImportNo) {
		SImportNo = sImportNo;
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
	public short getRowId() {
		return rowId;
	}
	public void setRowId(short rowId) {
		this.rowId = rowId;
	}
	public String getImportNo() {
		return importNo;
	}
	public void setImportNo(String importNo) {
		this.importNo = importNo;
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
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	
	public Double getPkQty() {
		return pkQty;
	}
	public void setPkQty(Double pkQty) {
		this.pkQty = pkQty;
	}
	public Double getCheckQty() {
		return checkQty;
	}
	public void setCheckQty(Double checkQty) {
		this.checkQty = checkQty;
	}
	public Integer getExpiryDays() {
		return expiryDays;
	}
	public void setExpiryDays(Integer expiryDays) {
		this.expiryDays = expiryDays;
	}
	public Double getAlarmrate() {
		return alarmrate;
	}
	public void setAlarmrate(Double alarmrate) {
		this.alarmrate = alarmrate;
	}
	public Double getFreezerate() {
		return freezerate;
	}
	public void setFreezerate(Double freezerate) {
		this.freezerate = freezerate;
	}
	public Double getCheckExcess() {
		return checkExcess;
	}
	public void setCheckExcess(Double checkExcess) {
		this.checkExcess = checkExcess;
	}
	public Double getNoCheckQty() {
		return noCheckQty;
	}
	public void setNoCheckQty(Double noCheckQty) {
		this.noCheckQty = noCheckQty;
	}
	public String getWareArea() {
		return wareArea;
	}
	public void setWareArea(String wareArea) {
		this.wareArea = wareArea;
	}
	public Double getInQty() {
		return inQty;
	}
	public void setInQty(Double inQty) {
		this.inQty = inQty;
	}
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public String getBoxPickType() {
		return boxPickType;
	}
	public void setBoxPickType(String boxPickType) {
		this.boxPickType = boxPickType;
	}
	public String getDisPickType() {
		return disPickType;
	}
	public void setDisPickType(String disPickType) {
		this.disPickType = disPickType;
	}
	public Double getQpalette() {
		return qpalette;
	}
	public void setQpalette(Double qpalette) {
		this.qpalette = qpalette;
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
	public String getExcessQty() {
		return excessQty;
	}
	public void setExcessQty(String excessQty) {
		this.excessQty = excessQty;
	}

}
