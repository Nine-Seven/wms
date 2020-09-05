package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_WarehousePacking;

@SuppressWarnings("serial")
public class Bdef_WarehousePackingModel extends Bdef_WarehousePacking{
	private String enterpriseNo;
	private String articleNo;
	private Double packingQty;
	
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public Double getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	
}
