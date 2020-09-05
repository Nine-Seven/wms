package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;
/**
 * 
 * @author csr
 * 直通验收获取商品信息结构（天天惠）
 *
 */
public class IdataGetArticleInfoIDAnswerModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String EnterpriseNo;
	private String WarehouseNo;
	private String OwnerNo;
	private String ArticleNo;
	private String Barcode;
	private String ArticleName;

	private String LotNo;
	private String ProduceDate;
	private String ExpireDate;
	private String Quality;
	private String ImportBatchNo;//验收批次 
	private String RsvBatch1;//	rsv_batch1
	private String RsvBatch2;//	rsv_batch2
	private String RsvBatch3;//	rsv_batch3
	private String RsvBatch4;//	rsv_batch4
	private String RsvBatch5;//	rsv_batch5
	private String RsvBatch6;//	rsv_batch6
	private String RsvBatch7;//	rsv_batch7
	private String RsvBatch8;//	rsv_batch8
	private String ExpiryDays;//有效期
	private String Qpalette;//堆叠
	private String PalBaseQbox;
	private String PalHeightQbox;
	private String QminOperatePacking;
	private String PackingQty;
	private String cellNo;
	private String RowId;//行号
	private String PoQty; //未验量
	private String instockNo;//上架单号
   
    //数据校验
	private String alarmRate; //报警比率
	private String freezeRate;//冻结比率
	private String icAlarmRate; //达到报警比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
	private String icFreezeRate;//达到冻结比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入

	private String scanFlag;//扫描标识；0：不扫描；1：扫描，医药行业的扫码电子监管码根据此字段判断
	private String lotType;//1.只管批次 2.只管生产日期 3.批次+生产日期 4=都不管

    //验收数据
	private String packingUnit;//箱包装单位
	private String packingUnitQmin;//中包装单位
	private String Unit;//基本包装单位
	private String packingSpec;//箱包装规格
	private String packingSpecQmin;//中包装规格
	private String spec;//基本包装规格
	private String unitPacking;
	private String checkBoxs;  //验收箱数
	private String checkPsc;   //验收散数
	private String unCheckBoxs;//未验收箱数
	private String unCheckPcs; //未验收散数

	private String labelNo;
	private String serialNo;
	private String autoLocateFlag;
	private String sImportNo;
	
	private String deviceNo;
	private String unDeviceCheckPcs;
	
	public String getEnterpriseNo() {
		return EnterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		EnterpriseNo = enterpriseNo;
	}
	public String getWarehouseNo() {
		return WarehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		WarehouseNo = warehouseNo;
	}
	public String getOwnerNo() {
		return OwnerNo;
	}
	public void setOwnerNo(String ownerNo) {
		OwnerNo = ownerNo;
	}
	public String getArticleNo() {
		return ArticleNo;
	}
	public void setArticleNo(String articleNo) {
		ArticleNo = articleNo;
	}
	public String getBarcode() {
		return Barcode;
	}
	public void setBarcode(String barcode) {
		Barcode = barcode;
	}
	public String getArticleName() {
		return ArticleName;
	}
	public void setArticleName(String articleName) {
		ArticleName = articleName;
	}
	
	public String getLotNo() {
		return LotNo;
	}
	public void setLotNo(String lotNo) {
		LotNo = lotNo;
	}
	public String getProduceDate() {
		return ProduceDate;
	}
	public void setProduceDate(String produceDate) {
		ProduceDate = produceDate;
	}
	public String getExpireDate() {
		return ExpireDate;
	}
	public void setExpireDate(String expireDate) {
		ExpireDate = expireDate;
	}
	public String getQuality() {
		return Quality;
	}
	public void setQuality(String quality) {
		Quality = quality;
	}
	public String getImportBatchNo() {
		return ImportBatchNo;
	}
	public void setImportBatchNo(String importBatchNo) {
		ImportBatchNo = importBatchNo;
	}
	public String getRsvBatch1() {
		return RsvBatch1;
	}
	public void setRsvBatch1(String rsvBatch1) {
		RsvBatch1 = rsvBatch1;
	}
	public String getRsvBatch2() {
		return RsvBatch2;
	}
	public void setRsvBatch2(String rsvBatch2) {
		RsvBatch2 = rsvBatch2;
	}
	public String getRsvBatch3() {
		return RsvBatch3;
	}
	public void setRsvBatch3(String rsvBatch3) {
		RsvBatch3 = rsvBatch3;
	}
	public String getRsvBatch4() {
		return RsvBatch4;
	}
	public void setRsvBatch4(String rsvBatch4) {
		RsvBatch4 = rsvBatch4;
	}
	public String getRsvBatch5() {
		return RsvBatch5;
	}
	public void setRsvBatch5(String rsvBatch5) {
		RsvBatch5 = rsvBatch5;
	}
	public String getRsvBatch6() {
		return RsvBatch6;
	}
	public void setRsvBatch6(String rsvBatch6) {
		RsvBatch6 = rsvBatch6;
	}
	public String getRsvBatch7() {
		return RsvBatch7;
	}
	public void setRsvBatch7(String rsvBatch7) {
		RsvBatch7 = rsvBatch7;
	}
	public String getRsvBatch8() {
		return RsvBatch8;
	}
	public void setRsvBatch8(String rsvBatch8) {
		RsvBatch8 = rsvBatch8;
	}
	public String getExpiryDays() {
		return ExpiryDays;
	}
	public void setExpiryDays(String expiryDays) {
		ExpiryDays = expiryDays;
	}
	public String getQpalette() {
		return Qpalette;
	}
	public void setQpalette(String qpalette) {
		Qpalette = qpalette;
	}
	public String getPalBaseQbox() {
		return PalBaseQbox;
	}
	public void setPalBaseQbox(String palBaseQbox) {
		PalBaseQbox = palBaseQbox;
	}
	public String getPalHeightQbox() {
		return PalHeightQbox;
	}
	public void setPalHeightQbox(String palHeightQbox) {
		PalHeightQbox = palHeightQbox;
	}
	
	public String getQminOperatePacking() {
		return QminOperatePacking;
	}
	public void setQminOperatePacking(String qminOperatePacking) {
		QminOperatePacking = qminOperatePacking;
	}
	public String getPackingQty() {
		return PackingQty;
	}
	public void setPackingQty(String packingQty) {
		PackingQty = packingQty;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getRowId() {
		return RowId;
	}
	public void setRowId(String rowId) {
		RowId = rowId;
	}
	public String getPoQty() {
		return PoQty;
	}
	public void setPoQty(String poQty) {
		PoQty = poQty;
	}
	public String getInstockNo() {
		return instockNo;
	}
	public void setInstockNo(String instockNo) {
		this.instockNo = instockNo;
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
	public String getCheckBoxs() {
		return checkBoxs;
	}
	public void setCheckBoxs(String checkBoxs) {
		this.checkBoxs = checkBoxs;
	}
	public String getCheckPsc() {
		return checkPsc;
	}
	public void setCheckPsc(String checkPsc) {
		this.checkPsc = checkPsc;
	}
	public String getUnCheckBoxs() {
		return unCheckBoxs;
	}
	public void setUnCheckBoxs(String unCheckBoxs) {
		this.unCheckBoxs = unCheckBoxs;
	}
	public String getUnCheckPcs() {
		return unCheckPcs;
	}
	public void setUnCheckPcs(String unCheckPcs) {
		this.unCheckPcs = unCheckPcs;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getAutoLocateFlag() {
		return autoLocateFlag;
	}
	public void setAutoLocateFlag(String autoLocateFlag) {
		this.autoLocateFlag = autoLocateFlag;
	}
	public String getsImportNo() {
		return sImportNo;
	}
	public void setsImportNo(String sImportNo) {
		this.sImportNo = sImportNo;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getUnDeviceCheckPcs() {
		return unDeviceCheckPcs;
	}
	public void setUnDeviceCheckPcs(String unDeviceCheckPcs) {
		this.unDeviceCheckPcs = unDeviceCheckPcs;
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
	public String getUnitPacking() {
		return unitPacking;
	}
	public void setUnitPacking(String unitPacking) {
		this.unitPacking = unitPacking;
	}	
}
