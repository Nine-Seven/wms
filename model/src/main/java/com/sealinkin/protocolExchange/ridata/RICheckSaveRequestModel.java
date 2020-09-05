package com.sealinkin.protocolExchange.ridata;

/**
 *  返配验收 保存请求 StuRICheckSaveRequest
 * @author lich
 *
 */
public class RICheckSaveRequestModel {
	private String enterpriseNo; 
	private String wareHouseNo;     
    private String ownerNo;
    private String suntreadNo;
    private String articleNo;       
    private String barcode;
    private String packingQty;
    private String checkQty;
    private String printerGroupNo;
    private String dockNo;//码头
    private String workerNo;
    private String checkTools;
    private String isAdd;

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

	private String labelNo;
    private String subLabelNo;
    private String supplierNo;
    private String fixPalFlag;
    private String businessType;
    
    private String labelID;
    private String deviceNo;
    private String cellNo; 
	private String batchNo;
    private String classType;
	private String qualityFlag;
	
	private String untreadType;
    
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
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	} 
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
    public String getLabelID() {
		return labelID;
	}
	public void setLabelID(String labelID) {
		this.labelID = labelID;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getWareHouseNo() {
		return wareHouseNo;
	}
	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getSuntreadNo() {
		return suntreadNo;
	}
	public void setSuntreadNo(String suntreadNo) {
		this.suntreadNo = suntreadNo;
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
	public String getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(String packingQty) {
		this.packingQty = packingQty;
	}
	public String getCheckQty() {
		return checkQty;
	}
	public void setCheckQty(String checkQty) {
		this.checkQty = checkQty;
	}
	public String getPrinterGroupNo() {
		return printerGroupNo;
	}
	public void setPrinterGroupNo(String printerGroupNo) {
		this.printerGroupNo = printerGroupNo;
	}
	public String getDockNo() {
		return dockNo;
	}
	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}
	public String getWorkerNo() {
		return workerNo;
	}
	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}
	public String getCheckTools() {
		return checkTools;
	}
	public void setCheckTools(String checkTools) {
		this.checkTools = checkTools;
	}
	public String getIsAdd() {
		return isAdd;
	}
	public void setIsAdd(String isAdd) {
		this.isAdd = isAdd;
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
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getSubLabelNo() {
		return subLabelNo;
	}
	public void setSubLabelNo(String subLabelNo) {
		this.subLabelNo = subLabelNo;
	}
	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	public String getFixPalFlag() {
		return fixPalFlag;
	}
	public void setFixPalFlag(String fixPalFlag) {
		this.fixPalFlag = fixPalFlag;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}       
}
