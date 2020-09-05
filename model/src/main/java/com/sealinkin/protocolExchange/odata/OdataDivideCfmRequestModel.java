package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;

/**
 * 分播回单参数结构 对应StuOMDivideCfmRequest
 * @author lich
 *
 */
public class OdataDivideCfmRequestModel implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String WarehouseNo;
	private String ownerNo;//委托业主
    private String divideNo;//分播单号
    private String scontainerNo;//来源容器号	
    private String sourceLabelNo;//来源标签
    private String destLabelNo;//目的标签
    private Double realDivideQty;//分播数量
    private String articleNo;//商品编号
    private String barcode;//条码
    private String scellNo;//来源储位
    private String batchNo;//批次号
    private String lotNo;//批号
    private String produceDate;//生产日期
    private String expireDate;//有效期
    private String quality; //品质 
    private String rsvBatch1;//	strRsvBatch1
    private String rsvBatch2;//	strRsvBatch2
    private String rsvBatch3;//	strRsvBatch3
    private String rsvBatch4;//	strRsvBatch4
    private String rsvBatch5;//	strRsvBatch5
    private String rsvBatch6;//	strRsvBatch6
    private String rsvBatch7;//	strRsvBatch7
    private String rsvBatch8;//	strRsvBatch8
    private Double packQty;//包装
    private String deliverObj;//配送对象
    private String custNo;//客户
    private String userID;//分播人员
    
    private String AddFlag;//1：按数量回单；2：按记录数回单
    private String ContainerType;//

    private Double articleQty;//预计量
    
	public Double getArticleQty() {
		return articleQty;
	}
	public void setArticleQty(Double articleQty) {
		this.articleQty = articleQty;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getWarehouseNo() {
		return WarehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		WarehouseNo = warehouseNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getDivideNo() {
		return divideNo;
	}
	public void setDivideNo(String divideNo) {
		this.divideNo = divideNo;
	}
	public String getScontainerNo() {
		return scontainerNo;
	}
	public void setScontainerNo(String scontainerNo) {
		this.scontainerNo = scontainerNo;
	}
	public String getSourceLabelNo() {
		return sourceLabelNo;
	}
	public void setSourceLabelNo(String sourceLabelNo) {
		this.sourceLabelNo = sourceLabelNo;
	}
	public String getDestLabelNo() {
		return destLabelNo;
	}
	public void setDestLabelNo(String destLabelNo) {
		this.destLabelNo = destLabelNo;
	}
	public Double getRealDivideQty() {
		return realDivideQty;
	}
	public void setRealDivideQty(Double realDivideQty) {
		this.realDivideQty = realDivideQty;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	
	public String getScellNo() {
		return scellNo;
	}
	public void setScellNo(String scellNo) {
		this.scellNo = scellNo;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
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
	public Double getPackQty() {
		return packQty;
	}
	public void setPackQty(Double packQty) {
		this.packQty = packQty;
	}
	public String getDeliverObj() {
		return deliverObj;
	}
	public void setDeliverObj(String deliverObj) {
		this.deliverObj = deliverObj;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getAddFlag() {
		return AddFlag;
	}
	public void setAddFlag(String AddFlag) {
		this.AddFlag = AddFlag;
	}
	public String getContainerType() {
		return ContainerType;
	}
	public void setContainerType(String ContainerType) {
		this.ContainerType = ContainerType;
	}
}
