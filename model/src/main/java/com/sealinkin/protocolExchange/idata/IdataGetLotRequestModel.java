package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;
/**
 * 获取批次请求 对应StuIMGetLotRequest
 * @author lich
 *
 */
public class IdataGetLotRequestModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String WarehouseNo;
	private String articleNo;
	private String produceDate;
	private String simportNo;	
    private String lotNo;
        
	public String getWarehouseNo() {
		return WarehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		WarehouseNo = warehouseNo;
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
	public String getSimportNo() {
		return simportNo;
	}
	public void setSimportNo(String simportNo) {
		this.simportNo = simportNo;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
    
}
