package com.sealinkin.idata.model;

import java.util.Date;

import com.sealinkin.idata.po.Idata_ImportD;

public class Idata_ImportDModel extends Idata_ImportD{

	private static final long serialVersionUID = 1L;
	
	private String importNo;
	private String warehouseNo;
	private String ownerNo;
	private short poId;
	private String ownerArticleNo;
	private String poNo;
	
	private String articleName;
	private String barcode;
	private String custNo;
	private String custName;
	private String packingUnit;
	private String spec;//基本规格
	private String packingSpec;//箱包装规格
	private Double planBox;//计划箱数
	private Double planDis;//计划散数
	private Double planQmin;//计划中包数
	private Double qminOperatePacking;//最小操作包装数量
	private Double unitPacking;//基本包装数量
	private String inQty;
	private Double checkQty;
	private Double noCheckQty;
	private Double canCheckQty;
	private String poNoAndBarcodeText;
	private String articleText;
	private String articleIdentifier;
	private String printFlag;
	private Date produceDateText;
	private Date expireDateText;
	private String lotNo;
	private String lotType;
	private Integer expiryDays;
	private String alarmrate;
	private String freezerate;
	private Integer checkExcess;
	private String temperatureFlag;
	private String takeType;
	private String qpalette;
	private String qpaletteText;
	private String isDiffConfirmFlag;//单据是否可以做差异确认(0-不允许;1-允许) huangb 20160721
	private String modifyFlag;//是否可修改允许原单，跨境单重新下单后对原单做调整，0：不允许；1：允许 haungb 20160726
	private String importType;//进货单据类型  haungb 20160726
	
	public String getQpalette() {
		return qpalette;
	}
	public void setQpalette(String qpalette) {
		this.qpalette = qpalette;
	}
	public String getTakeType() {
		return takeType;
	}
	public void setTakeType(String takeType) {
		this.takeType = takeType;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getImportNo() {
		return importNo;
	}
	public void setImportNo(String importNo) {
		this.importNo = importNo;
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
	public short getPoId() {
		return poId;
	}
	public void setPoId(short poId) {
		this.poId = poId;
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
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
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
	
	public String getPackingSpec() {
		return packingSpec;
	}
	public void setPackingSpec(String packingSpec) {
		this.packingSpec = packingSpec;
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
	public String getInQty() {
		return inQty;
	}
	public void setInQty(String inQty) {
		this.inQty = inQty;
	}
	public Double getCheckQty() {
		return checkQty;
	}
	public void setCheckQty(Double checkQty) {
		this.checkQty = checkQty;
	}
	public Double getNoCheckQty() {
		return noCheckQty;
	}
	public void setNoCheckQty(Double noCheckQty) {
		this.noCheckQty = noCheckQty;
	}
	public Double getCanCheckQty() {
		return canCheckQty;
	}
	public void setCanCheckQty(Double canCheckQty) {
		this.canCheckQty = canCheckQty;
	}
	public String getPoNoAndBarcodeText() {
		return poNoAndBarcodeText;
	}
	public void setPoNoAndBarcodeText(String poNoAndBarcodeText) {
		this.poNoAndBarcodeText = poNoAndBarcodeText;
	}
	public String getArticleText() {
		return articleText;
	}
	public void setArticleText(String articleText) {
		this.articleText = articleText;
	}
	public String getArticleIdentifier() {
		return articleIdentifier;
	}
	public void setArticleIdentifier(String articleIdentifier) {
		this.articleIdentifier = articleIdentifier;
	}
	public String getPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}
	
	public Date getProduceDateText() {
		return produceDateText;
	}
	public void setProduceDateText(Date produceDateText) {
		this.produceDateText = produceDateText;
	}
	public Date getExpireDateText() {
		return expireDateText;
	}
	public void setExpireDateText(Date expireDateText) {
		this.expireDateText = expireDateText;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getLotType() {
		return lotType;
	}
	public void setLotType(String lotType) {
		this.lotType = lotType;
	}
	public Integer getExpiryDays() {
		return expiryDays;
	}
	public void setExpiryDays(Integer expiryDays) {
		this.expiryDays = expiryDays;
	}
	public String getAlarmrate() {
		return alarmrate;
	}
	public void setAlarmrate(String alarmrate) {
		this.alarmrate = alarmrate;
	}
	public String getFreezerate() {
		return freezerate;
	}
	public void setFreezerate(String freezerate) {
		this.freezerate = freezerate;
	}
	public Integer getCheckExcess() {
		return checkExcess;
	}
	public void setCheckExcess(Integer checkExcess) {
		this.checkExcess = checkExcess;
	}
	public String getTemperatureFlag() {
		return temperatureFlag;
	}
	public void setTemperatureFlag(String temperatureFlag) {
		this.temperatureFlag = temperatureFlag;
	}
	public String getQpaletteText() {
		return qpaletteText;
	}
	public void setQpaletteText(String qpaletteText) {
		this.qpaletteText = qpaletteText;
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
	public String getIsDiffConfirmFlag() {
		return isDiffConfirmFlag;
	}
	public void setIsDiffConfirmFlag(String isDiffConfirmFlag) {
		this.isDiffConfirmFlag = isDiffConfirmFlag;
	}
	public String getModifyFlag() {
		return modifyFlag;
	}
	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}
	public String getImportType() {
		return importType;
	}
	public void setImportType(String importType) {
		this.importType = importType;
	}
	
	
}
