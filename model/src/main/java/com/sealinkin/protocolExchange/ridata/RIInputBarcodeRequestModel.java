package com.sealinkin.protocolExchange.ridata;

/**
 * RF返配验收 扫描条码请求
 * @author sunl
 *
 */
public class RIInputBarcodeRequestModel {
	 /// <summary>
    /// 企业码
    /// </summary>
	private String enterPriseNo;
    /// <summary>
    /// 仓别
    /// </summary>
    private String warehouseNo;
    /// <summary>
    /// 流水号
    /// </summary>
    private String serialNo;
    /// <summary>
    /// 反配单号
    /// </summary>
    private String untreadNo;
    /// <summary>
    /// 品质--明细
    /// </summary>
    private String quality;
	/// <summary>
    /// 堆叠
    /// </summary>
    private String qpalette;
    /// <summary>
    /// 商品编码
    /// </summary>
    private String articleNo;
    /// <summary>
    /// 商品名称
    /// </summary>
    private String articleName; 
    /// <summary>
    /// 验收数量
    /// </summary>
    private Double checkqty;
    /// <summary>
    /// 数量
    /// </summary>
    private Double qty;
    /// <summary>
    /// 条码
    /// </summary>
    private String barcode;
    /// <summary>
    /// 批次类型
    /// </summary>
    private String lotType;

    /// <summary>
    /// 分播墙号
    /// </summary>    
    private String deviceNo;
    /// <summary>
    /// 类型
    /// </summary>
    private String classType;

    /// <summary>
    /// 反配单类型
    /// </summary>
    private String untreadType; 
	/// <summary>
    /// 品质--单
    /// </summary>
    private String qualityFlag; 

	/// <summary>
    /// 到期日
    /// </summary>
    private String expirydays;
	/// <summary>
    /// 委托业主
    /// </summary>
    private String ownerNo;

    /// <summary>
    /// 包装数量
    /// </summary>
    private Double packQty; //包装数量
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
	public String getEnterPriseNo() {
		return enterPriseNo;
	}
	public void setEnterPriseNo(String enterPriseNo) {
		this.enterPriseNo = enterPriseNo;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getUntreadNo() {
		return untreadNo;
	}
	public void setUntreadNo(String untreadNo) {
		this.untreadNo = untreadNo;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getQpalette() {
		return qpalette;
	}
	public void setQpalette(String qpalette) {
		this.qpalette = qpalette;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public Double getCheckqty() {
		return checkqty;
	}
	public void setCheckqty(Double checkqty) {
		this.checkqty = checkqty;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getLotType() {
		return lotType;
	}
	public void setLotType(String lotType) {
		this.lotType = lotType;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getUntreadType() {
		return untreadType;
	}
	public void setUntreadType(String untreadType) {
		this.untreadType = untreadType;
	}
	public String getQualityFlag() {
		return qualityFlag;
	}
	public void setQualityFlag(String qualityFlag) {
		this.qualityFlag = qualityFlag;
	}
	public String getExpirydays() {
		return expirydays;
	}
	public void setExpirydays(String expirydays) {
		this.expirydays = expirydays;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public Double getPackQty() {
		return packQty;
	}
	public void setPackQty(Double packQty) {
		this.packQty = packQty;
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
	
	
}
