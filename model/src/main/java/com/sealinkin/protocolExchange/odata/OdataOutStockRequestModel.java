package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;

/**
 * 拣货回单参数结构 对应StuOMOutStockRequest
 * @author lich
 *
 */
public class OdataOutStockRequestModel implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*private BdefArticleInfo stuArticleInfo;
	private PackInfoModel stuPackInfo;*/
	
	private String enterpriseNo;//企业号
    private String warehouseNo;//仓别
    private String ownerNo;//委托业主
    private String scellNo;
    private double outStockQty; 
    private String dcellNo;
    private String outStockNo;
    private String userId;//分播人员
    private String LabelNo;//标签号
    private String dLabelNo;//目的标签号    
    private Double realQty;//下架数量
    private String deliverObj;//配送对象
    private Double realDivideQty;//分播数量
    private String dpsCellNo;//格子号
    
    public String getDeliverObj() {
		return deliverObj;
	}
	public void setDeliverObj(String deliverObj) {
		this.deliverObj = deliverObj;
	}
	public Double getRealQty() {
		return realQty;
	}
	public void setRealQty(Double realQty) {
		this.realQty = realQty;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String articleNo; 
    private String barcode;
    private String articleName; 
    private String spec;//规格
    private String itemType;
    
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
    
	private String unit;
    private Double packQty;
    private Double palBaseBox;
    private Double palHeightBox;
    private Double articleQty;
    

	private String custNo;//客户
	private String waveNo;//波次
	private String custName;//客户
	private String divide_no;
	
    
	public String getDivide_no() {
		return divide_no;
	}
	public void setDivide_no(String divide_no) {
		this.divide_no = divide_no;
	}
	public String getDpsCellNo() {
		return dpsCellNo;
	}
	public void setDpsCellNo(String dpsCellNo) {
		this.dpsCellNo = dpsCellNo;
	}
	public String getdLabelNo() {
		return dLabelNo;
	}
	public void setdLabelNo(String dLabelNo) {
		this.dLabelNo = dLabelNo;
	}
	public Double getRealDivideQty() {
		return realDivideQty;
	}
	public void setRealDivideQty(Double realDivideQty) {
		this.realDivideQty = realDivideQty;
	}
	
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getWaveNo() {
		return waveNo;
	}
	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
	}
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
	public String getScellNo() {
		return scellNo;
	}
	public void setScellNo(String scellNo) {
		this.scellNo = scellNo;
	}
	public double getOutStockQty() {
		return outStockQty;
	}
	public void setOutStockQty(double outStockQty) {
		this.outStockQty = outStockQty;
	}
	public String getDcellNo() {
		return dcellNo;
	}
	public void setDcellNo(String dcellNo) {
		this.dcellNo = dcellNo;
	}
	public String getOutStockNo() {
		return outStockNo;
	}
	public void setOutStockNo(String outStockNo) {
		this.outStockNo = outStockNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLabelNo() {
		return LabelNo;
	}
	public void setLabelNo(String labelNo) {
		LabelNo = labelNo;
	}
	public String getDlabelNo() {
		return dLabelNo;
	}
	public void setDlabelNo(String dlabelNo) {
		this.dLabelNo = dlabelNo;
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
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getPackQty() {
		return packQty;
	}
	public void setPackQty(Double packQty) {
		this.packQty = packQty;
	}
	public Double getPalBaseBox() {
		return palBaseBox;
	}
	public void setPalBaseBox(Double palBaseBox) {
		this.palBaseBox = palBaseBox;
	}
	public Double getPalHeightBox() {
		return palHeightBox;
	}
	public void setPalHeightBox(Double palHeightBox) {
		this.palHeightBox = palHeightBox;
	}
	public Double getArticleQty() {
		return articleQty;
	}
	public void setArticleQty(Double articleQty) {
		this.articleQty = articleQty;
	}
	
	
}
