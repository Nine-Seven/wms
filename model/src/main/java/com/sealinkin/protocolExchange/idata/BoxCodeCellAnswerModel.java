package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;
/**
 * 箱码采集 储位扫描 对应stuBoxCodeCellNoAnswer
 * @author lich
 *
 */
public class BoxCodeCellAnswerModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String articleNo;
	private String ownerArticleNo; 
    private String articleName;
    private String packingQty;
    
    
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}
	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(String packingQty) {
		this.packingQty = packingQty;
	} 
	
}
