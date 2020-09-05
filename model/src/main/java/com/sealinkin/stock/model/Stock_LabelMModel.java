package com.sealinkin.stock.model;

import com.sealinkin.stock.po.Stock_LabelM;

public class Stock_LabelMModel extends Stock_LabelM{

	private static final long serialVersionUID = 1L;
	
	private String warehouseNo;
	private String labelNo;
	private String containerNo;
	private String containerType;
	
	private String custName;
	private String stItems;
	private String articleItems;
	private Double boxQty;
	private Double volumn;
	private Double weight;
	private String waveNo;
	
	private String shipperNo;//承运商 通过线路关联
	private String statusText;
	
	private String deliverObj;
	private String countArticleNo;
	
	private String articleName;		//8-11添加
	private String articleNo;
	private String articleQty;
	private String realQty;
	private String shipperDeliverNo;
	private String barcode;
	
	public String getCountArticleNo() {
		return countArticleNo;
	}
	public void setCountArticleNo(String countArticleNo) {
		this.countArticleNo = countArticleNo;
	}
	public String getDeliverObj() {
		return deliverObj;
	}
	public void setDeliverObj(String deliverObj) {
		this.deliverObj = deliverObj;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getContainerNo() {
		return containerNo;
	}
	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}
	public String getContainerType() {
		return containerType;
	}
	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getStItems() {
		return stItems;
	}
	public void setStItems(String stItems) {
		this.stItems = stItems;
	}
	public String getArticleItems() {
		return articleItems;
	}
	public void setArticleItems(String articleItems) {
		this.articleItems = articleItems;
	}
	public Double getBoxQty() {
		return boxQty;
	}
	public void setBoxQty(Double boxQty) {
		this.boxQty = boxQty;
	}
	public Double getVolumn() {
		return volumn;
	}
	public void setVolumn(Double volumn) {
		this.volumn = volumn;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getWaveNo() {
		return waveNo;
	}
	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
	}
	public String getShipperNo() {
		return shipperNo;
	}
	public void setShipperNo(String shipperNo) {
		this.shipperNo = shipperNo;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getArticleQty() {
		return articleQty;
	}
	public void setArticleQty(String articleQty) {
		this.articleQty = articleQty;
	}
	public String getRealQty() {
		return realQty;
	}
	public void setRealQty(String realQty) {
		this.realQty = realQty;
	}
	public String getShipperDeliverNo() {
		return shipperDeliverNo;
	}
	public void setShipperDeliverNo(String shipperDeliverNo) {
		this.shipperDeliverNo = shipperDeliverNo;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
}
