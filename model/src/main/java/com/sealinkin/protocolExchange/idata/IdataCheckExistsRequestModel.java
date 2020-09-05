package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;
/**
 * 验收检验应答 StuImCheckExistsRequest
 * @author lich
 *
 */
public class IdataCheckExistsRequestModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String wareHouseNo;//仓库编码
	private String ownerNo;//货主编码
    private String simportNo;//进货汇总单号
    private String articleNo;//商品编码
    private Double packingQty;//商品包装
    private String produceDate;//生产日期
    private String expireDate;//到期日期                                
    private String checkQty;//验收数量
    private String overFlag;//超品标识，0-正常；1-超品 huangb 20160711
        
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
	public String getSimportNo() {
		return simportNo;
	}
	public void setSimportNo(String simportNo) {
		this.simportNo = simportNo;
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
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getCheckQty() {
		return checkQty;
	}
	public void setCheckQty(String checkQty) {
		this.checkQty = checkQty;
	}
	public Double getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}
	public String getOverFlag() {
		return overFlag;
	}
	public void setOverFlag(String overFlag) {
		this.overFlag = overFlag;
	}
	
	
}