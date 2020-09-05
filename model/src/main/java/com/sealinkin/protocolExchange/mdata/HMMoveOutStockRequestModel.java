package com.sealinkin.protocolExchange.mdata;

/**
 * 移库下架请求StuHMMoveOutStockRequest
 * @author lich
 *
 */
public class HMMoveOutStockRequestModel {
	 private String enterpriseNo;
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

     private String realOutStockCellNo;//下架储位
     private String realOutStockQty; //下架量
     private String operateType;//作业类型（B,C）
     private String outstockType;//0：出货拣货；1：出货补货；2：批次补货移库；3：安全量移库；4：人工移库
     private String stockNo;//移库单号	
     private String labelNo;//标签号
     private String inStockCellNo;//预上储位     
     private String userID;
     
     
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
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
	public String getRealOutStockCellNo() {
		return realOutStockCellNo;
	}
	public void setRealOutStockCellNo(String realOutStockCellNo) {
		this.realOutStockCellNo = realOutStockCellNo;
	}
	public String getRealOutStockQty() {
		return realOutStockQty;
	}
	public void setRealOutStockQty(String realOutStockQty) {
		this.realOutStockQty = realOutStockQty;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getOutstockType() {
		return outstockType;
	}
	public void setOutstockType(String outstockType) {
		this.outstockType = outstockType;
	}
	public String getStockNo() {
		return stockNo;
	}
	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getInStockCellNo() {
		return inStockCellNo;
	}
	public void setInStockCellNo(String inStockCellNo) {
		this.inStockCellNo = inStockCellNo;
	}        
}
