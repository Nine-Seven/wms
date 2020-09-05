package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;
/**
 * 上架回单请求 对应客户端StuIMInStockRequest
 * @author lich
 *
 */
public class IdataInStockRequestModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String wareHouseNo;//
	private String ownerNo;//
    private String instockNo;//
    private String destCellNo;//
    private String realCellNo;//
    private String labelNo;//
    private String articleNo;//                         
    private String produceDate;//
    private String quality;//                               
    private String lotNo;//--批次号
    private String rsvBatch1;//--预留批属性1
    private String rsvBatch2;//--预留批属性2
    private String rsvBatch3;//--预留批属性3
    private String rsvBatch4;//--预留批属性4
    private String rsvBatch5;//--预留批属性5
    private String rsvBatch6;//--预留批属性6
    private String rsvBatch7;//--预留批属性7
    private String rsvBatch8;//--预留批属性8                                
    private String packingQty;//
    private String ArticleQty;
    private String realQty;//
    private String userId;//--上架人
    private String paperUserId;//--回单人
    private String isAddFlag;//--是否新增记录 1是0否
    private String tools;//工具 1-电脑；2-RF 
    
    private String expireDate;//到期日
    
    
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
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
	public String getInstockNo() {
		return instockNo;
	}
	public void setInstockNo(String instockNo) {
		this.instockNo = instockNo;
	}
	public String getDestCellNo() {
		return destCellNo;
	}
	public void setDestCellNo(String destCellNo) {
		this.destCellNo = destCellNo;
	}
	public String getRealCellNo() {
		return realCellNo;
	}
	public void setRealCellNo(String realCellNo) {
		this.realCellNo = realCellNo;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
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
	public String getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(String packingQty) {
		this.packingQty = packingQty;
	}
	public String getRealQty() {
		return realQty;
	}
	public void setRealQty(String realQty) {
		this.realQty = realQty;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPaperUserId() {
		return paperUserId;
	}
	public void setPaperUserId(String paperUserId) {
		this.paperUserId = paperUserId;
	}
	public String getIsAddFlag() {
		return isAddFlag;
	}
	public void setIsAddFlag(String isAddFlag) {
		this.isAddFlag = isAddFlag;
	}
	public String getTools() {
		return tools;
	}
	public void setTools(String tools) {
		this.tools = tools;
	}
	public String getArticleQty() {
		return ArticleQty;
	}
	public void setArticleQty(String articleQty) {
		ArticleQty = articleQty;
	}
	
	
}
