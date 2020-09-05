package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;
/**
 * 出库扫描返回
 * @author huangb 20160519
 *
 */
public class StuGetExpCheckInfoModel implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String SourceExpNo;
    private String ownerNo;
    private String articleNo;
	private String articleName;
	private String packingQty;
	private String rowId;
	private String orderQty;
	private String yesScanQty;
	private String noScanQty;
	private String scanFlag;//是否整单扫描完成
    
	public String getSourceExpNo() {
		return SourceExpNo;
	}
	public void setSourceExpNo(String sourceExpNo) {
		SourceExpNo = sourceExpNo;
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
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getYesScanQty() {
		return yesScanQty;
	}
	public void setYesScanQty(String yesScanQty) {
		this.yesScanQty = yesScanQty;
	}
	public String getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(String orderQty) {
		this.orderQty = orderQty;
	}
	public String getNoScanQty() {
		return noScanQty;
	}
	public void setNoScanQty(String noScanQty) {
		this.noScanQty = noScanQty;
	}
	public String getScanFlag() {
		return scanFlag;
	}
	public void setScanFlag(String scanFlag) {
		this.scanFlag = scanFlag;
	}
	
}
