package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;
/**
 * 上架扫描标签号 对应客户端StuIMInStockLabelAnswer
 * @author lich
 *
 */
public class IdataInStockLabelAnswerModel implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
    private String ownerNo;
    private String articleNo;
    private String barcode;
    private String articleName;
    private String spec;//规格

    private String lotNo;
    private String produceDate;
    private String expireDate;
    private String quality;
    private String importBatchNo;//验收批次 
    private String rsvBatch1;//	rsv_batch1
    private String rsvBatch2;//	rsv_batch2
    private String rsvBatch3;//	rsv_batch3
    private String rsvBatch4;//	rsv_batch4
    private String rsvBatch5;//	rsv_batch5
    private String rsvBatch6;//	rsv_batch6
    private String rsvBatch7;//	rsv_batch7
    private String rsvBatch8;//	rsv_batch8
    private String expiryDays;//有效期
    private String qpalette;//堆叠
    private String palBaseQbox;
    private String palHeightQbox;
    private String packingUnit;
    private String qminOperatePacking;
    private String packingQty;
    private String cellNo;//预上储位
    private String poQty; //上架量
    private String instockNo;//上架单号
    
    private String labelNo;//标签号 因为有可能录入流水号
    private String serialNo;//流水号
    private String autoLocateFlag;//自动定位标识。1-自动定位，上架不允许修改储位；0-手工指定储位
    
    private String dispCellNo;//目的储位 显示用 
    
    private String qminOpeartePackingUnit;//最小操作包装单位
    
	private String packingUnitQmin;//中包装单位
	private String Unit;//基本包装单位
	private String packingSpec;//箱包装规格
	private String packingSpecQmin;//中包装规格
	private String unitPacking;
    	
	public String getQminOpeartePackingUnit() {
		return qminOpeartePackingUnit;
	}
	public void setQminOpeartePackingUnit(String qminOpeartePackingUnit) {
		this.qminOpeartePackingUnit = qminOpeartePackingUnit;
	}
	
	public String getDispCellNo() {
		return dispCellNo;
	}
	public void setDispCellNo(String dispCellNo) {
		this.dispCellNo = dispCellNo;
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
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
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
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public String getQminOperatePacking() {
		return qminOperatePacking;
	}
	public void setQminOperatePacking(String qminOperatePacking) {
		this.qminOperatePacking = qminOperatePacking;
	}
	public String getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(String packingQty) {
		this.packingQty = packingQty;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getPoQty() {
		return poQty;
	}
	public void setPoQty(String poQty) {
		this.poQty = poQty;
	}
	public String getInstockNo() {
		return instockNo;
	}
	public void setInstockNo(String instockNo) {
		this.instockNo = instockNo;
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
	public String getUnitPacking() {
		return unitPacking;
	}
	public void setUnitPacking(String unitPacking) {
		this.unitPacking = unitPacking;
	}   		
}
