package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;

/// <summary>
 /// 存储验收商品信息结构 StuIMStockCheckArticleInfo
 /// </summary>
public class IdataStockCheckArticleInfoModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String WarehouseNo;
	private String articleNo; 
    private String barcode;
    private String articleName; 
    private String lotNo;//医药行业属性
    private String produceDate;
    private String expireDate;
    private String quality;           
    private String importBatchNo;//	验收批次 
    private String rsvBatch1;//	strRsvBatch1
    private String rsvBatch2;//	strRsvBatch2
    private String rsvBatch3;//	strRsvBatch3
    private String rsvBatch4;//	strRsvBatch4
    private String rsvBatch5;//	strRsvBatch5
    private String rsvBatch6;//	strRsvBatch6
    private String rsvBatch7;//	strRsvBatch7
    private String rsvBatch8;//	strRsvBatch8
    private String expiryDays;//有效期
    private String qpalette;//堆叠
    private String palBaseQbox;
    private String palHeightQbox;
    private String packingQty;//箱包装数量
    private String rowId;
    private String poQty; //未验量
    
    private Double qminOperatePacking;//最小操作包装数量
	private Double unitPacking;//基本包装数量
	private String packingUnit;//箱包装单位
	private String packingUnitQmin;//中包装单位
	private String Unit;//基本包装单位
	private String packingSpec;//箱包装规格
	private String packingSpecQmin;//中包装规格
	private String spec;//基本包装规格
	private Double planBox;//计划箱数
	private Double planDis;//计划散数
	private Double planQmin;//计划中包数
	private Double realBox;//实际箱数
	private Double realDis;//实际散数
	private Double realQmin;//实际中包数

    private String alarmRate; //报警比率
    private String freezeRate;//冻结比率
    private String icAlarmRate; //达到报警比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
    private String icFreezeRate;//达到冻结比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
    
    private String scanFlag;//扫描标识；0：不扫描；1：扫描，医药行业的扫码电子监管码根据此字段判断
    private String lotType;//1.只管批次 2.只管生产日期 3.批次+生产日期 4=都不管
	
    private String TemperatureFlag;//温控标识。0：无温度限制；1：有温度限制
    private String checkOverFlag;//是否超品标识:0-不是;1-是
    private String checkOverFlagUI;//是否超品标识:0-不是;1-是 用以界面判断

	public String getWarehouseNo() {
		return WarehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		WarehouseNo = warehouseNo;
	}

	public String getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
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

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public String getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getImportBatchNo() {
		return importBatchNo;
	}

	public void setImportBatchNo(String importBatchNo) {
		this.importBatchNo = importBatchNo;
	}

	public String getRsvBatch1() {
		return rsvBatch1;
	}

	public void setRsvBatch1(String rsvBatch1) {
		this.rsvBatch1 = rsvBatch1;
	}

	public String getRsvBatch2() {
		return rsvBatch2;
	}

	public void setRsvBatch2(String rsvBatch2) {
		this.rsvBatch2 = rsvBatch2;
	}

	public String getRsvBatch3() {
		return rsvBatch3;
	}

	public void setRsvBatch3(String rsvBatch3) {
		this.rsvBatch3 = rsvBatch3;
	}

	public String getRsvBatch4() {
		return rsvBatch4;
	}

	public void setRsvBatch4(String rsvBatch4) {
		this.rsvBatch4 = rsvBatch4;
	}

	public String getRsvBatch5() {
		return rsvBatch5;
	}

	public void setRsvBatch5(String rsvBatch5) {
		this.rsvBatch5 = rsvBatch5;
	}

	public String getRsvBatch6() {
		return rsvBatch6;
	}

	public void setRsvBatch6(String rsvBatch6) {
		this.rsvBatch6 = rsvBatch6;
	}

	public String getRsvBatch7() {
		return rsvBatch7;
	}

	public void setRsvBatch7(String rsvBatch7) {
		this.rsvBatch7 = rsvBatch7;
	}

	public String getRsvBatch8() {
		return rsvBatch8;
	}

	public void setRsvBatch8(String rsvBatch8) {
		this.rsvBatch8 = rsvBatch8;
	}

	public String getExpiryDays() {
		return expiryDays;
	}

	public void setExpiryDays(String expiryDays) {
		this.expiryDays = expiryDays;
	}

	public String getQpalette() {
		return qpalette;
	}

	public void setQpalette(String qpalette) {
		this.qpalette = qpalette;
	}

	public String getPalBaseQbox() {
		return palBaseQbox;
	}

	public void setPalBaseQbox(String palBaseQbox) {
		this.palBaseQbox = palBaseQbox;
	}

	public String getPalHeightQbox() {
		return palHeightQbox;
	}

	public void setPalHeightQbox(String palHeightQbox) {
		this.palHeightQbox = palHeightQbox;
	}

	public String getPackingQty() {
		return packingQty;
	}

	public void setPackingQty(String packingQty) {
		this.packingQty = packingQty;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getPoQty() {
		return poQty;
	}

	public void setPoQty(String poQty) {
		this.poQty = poQty;
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

	public String getAlarmRate() {
		return alarmRate;
	}

	public void setAlarmRate(String alarmRate) {
		this.alarmRate = alarmRate;
	}

	public String getFreezeRate() {
		return freezeRate;
	}

	public void setFreezeRate(String freezeRate) {
		this.freezeRate = freezeRate;
	}

	public String getIcAlarmRate() {
		return icAlarmRate;
	}

	public void setIcAlarmRate(String icAlarmRate) {
		this.icAlarmRate = icAlarmRate;
	}

	public String getIcFreezeRate() {
		return icFreezeRate;
	}

	public void setIcFreezeRate(String icFreezeRate) {
		this.icFreezeRate = icFreezeRate;
	}

	public String getScanFlag() {
		return scanFlag;
	}

	public void setScanFlag(String scanFlag) {
		this.scanFlag = scanFlag;
	}

	public String getLotType() {
		return lotType;
	}

	public void setLotType(String lotType) {
		this.lotType = lotType;
	}

	public String getTemperatureFlag() {
		return TemperatureFlag;
	}

	public void setTemperatureFlag(String temperatureFlag) {
		TemperatureFlag = temperatureFlag;
	}

	public String getCheckOverFlag() {
		return checkOverFlag;
	}

	public void setCheckOverFlag(String checkOverFlag) {
		this.checkOverFlag = checkOverFlag;
	}

	public String getCheckOverFlagUI() {
		return checkOverFlagUI;
	}

	public void setCheckOverFlagUI(String checkOverFlagUI) {
		this.checkOverFlagUI = checkOverFlagUI;
	}
    
   
}
