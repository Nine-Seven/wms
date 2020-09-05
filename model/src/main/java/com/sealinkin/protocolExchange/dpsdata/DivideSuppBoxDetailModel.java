package com.sealinkin.protocolExchange.dpsdata;

import java.io.Serializable;

/**
 * 对应 StuDivideSuppBoxDetail
 * @author lich
 *
 */
public class DivideSuppBoxDetailModel implements Serializable{    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private short ctrlNo;
    private short address;
    private String  sourceNo;
    private String  custNo;
    private String  batchNo;
    private String  asorterChuteNo;
    private String  articleNo;
    private String  barcode;
    private float packingQty;
    private int cellDispLength;
    private int cellStartPos;
    private int dpsDispLength;
    private String  deliverObj;
    private double qminOperatePacking;//小嘴存在包装大于0小于1的数据，所以这里放弃INT改用double
    private double qty;
	public short getCtrlNo() {
		return ctrlNo;
	}
	public void setCtrlNo(short ctrlNo) {
		this.ctrlNo = ctrlNo;
	}
	public short getAddress() {
		return address;
	}
	public void setAddress(short address) {
		this.address = address;
	}
	public String getSourceNo() {
		return sourceNo;
	}
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getAsorterChuteNo() {
		return asorterChuteNo;
	}
	public void setAsorterChuteNo(String asorterChuteNo) {
		this.asorterChuteNo = asorterChuteNo;
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
	public float getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(float packingQty) {
		this.packingQty = packingQty;
	}
	public int getCellDispLength() {
		return cellDispLength;
	}
	public void setCellDispLength(int cellDispLength) {
		this.cellDispLength = cellDispLength;
	}
	public int getCellStartPos() {
		return cellStartPos;
	}
	public void setCellStartPos(int cellStartPos) {
		this.cellStartPos = cellStartPos;
	}
	public int getDpsDispLength() {
		return dpsDispLength;
	}
	public void setDpsDispLength(int dpsDispLength) {
		this.dpsDispLength = dpsDispLength;
	}
	public String getDeliverObj() {
		return deliverObj;
	}
	public void setDeliverObj(String deliverObj) {
		this.deliverObj = deliverObj;
	}
	public double getQminOperatePacking() {
		return qminOperatePacking;
	}
	public void setQminOperatePacking(double qminOperatePacking) {
		this.qminOperatePacking = qminOperatePacking;
	}
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}	
}
