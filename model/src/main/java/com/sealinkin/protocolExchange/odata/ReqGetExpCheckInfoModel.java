package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;
/**
 * 出库扫描请求
 * @author huangb 20160519
 *
 */
public class ReqGetExpCheckInfoModel implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String EnterpriseNo;
	private String wareHouseNo;
	private String ownerNo;
    private String SourceExpNo;
    private String ScanNo;
    private String UserID;
    private String articleNo;
    private String scanPackingQty;
    private String scanQty;
    private String rowID;
    
	public String getEnterpriseNo() {
		return EnterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		EnterpriseNo = enterpriseNo;
	}
	public String getWareHouseNo() {
		return wareHouseNo;
	}
	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}
	public String getSourceExpNo() {
		return SourceExpNo;
	}
	public void setSourceExpNo(String sourceExpNo) {
		SourceExpNo = sourceExpNo;
	}
	public String getScanNo() {
		return ScanNo;
	}
	public void setScanNo(String scanNo) {
		ScanNo = scanNo;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
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
	public String getScanPackingQty() {
		return scanPackingQty;
	}
	public void setScanPackingQty(String scanPackingQty) {
		this.scanPackingQty = scanPackingQty;
	}
	public String getScanQty() {
		return scanQty;
	}
	public void setScanQty(String scanQty) {
		this.scanQty = scanQty;
	}
	public String getRowID() {
		return rowID;
	}
	public void setRowID(String rowID) {
		this.rowID = rowID;
	}
	
}
