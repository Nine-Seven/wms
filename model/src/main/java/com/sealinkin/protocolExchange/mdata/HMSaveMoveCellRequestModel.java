package com.sealinkin.protocolExchange.mdata;

/**
 * 移库扫描条码请求StuHMMoveBarcodeRequest
 * @author lich
 *
 */
public class HMSaveMoveCellRequestModel {
	private String enterpriseNo;
    private String warehouseNo;
    private String ownerNo;
    private String articleNo;
    private String outStockCellNo;//预下储位
    private String labelNo; //板号
    private String inStockCellNo;//预上储位
    private String packingQty;        
    private String produceDate;
    private String expireDate;
    private String quality;
    private String lotNo;       
    private String rsvBatch1;//	rsv_batch1
    private String rsvBatch2;//	rsv_batch2
    private String rsvBatch3;//	rsv_batch3
    private String rsvBatch4;//	rsv_batch4
    private String rsvBatch5;//	rsv_batch5
    private String rsvBatch6;//	rsv_batch6
    private String rsvBatch7;//	rsv_batch7
    private String rsvBatch8;//	rsv_batch8
    private String realQty; //实际移库数量
    private String terminalFlag; //设备类型
    private String sourceType; //                     
    private String userId;
    
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
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getOutStockCellNo() {
		return outStockCellNo;
	}
	public void setOutStockCellNo(String outStockCellNo) {
		this.outStockCellNo = outStockCellNo;
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
	public String getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(String packingQty) {
		this.packingQty = packingQty;
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
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
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
	public String getRealQty() {
		return realQty;
	}
	public void setRealQty(String realQty) {
		this.realQty = realQty;
	}
	public String getTerminalFlag() {
		return terminalFlag;
	}
	public void setTerminalFlag(String terminalFlag) {
		this.terminalFlag = terminalFlag;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}      
}
