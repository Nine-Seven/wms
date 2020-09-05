package com.sealinkin.odata.model;

import com.sealinkin.odata.po.Odata_ExpM;

public class Odata_ExpMModel extends Odata_ExpM{

	private static final long serialVersionUID = 1L;

	private String enterpriseNo;
	private String warehouseNo;
	private String expNo;
	private String expType;
	
	private String custName;
	private String custAlias;
	private Double articleItems;
	private Double totalQty;
	private Double totalVolumn;
	private Double sumVolumn;
	private Double sumWeight;
	private Double sumBoxQty;
	private String statusText;
	private Integer expNoCount;
	private Integer custCount;
	private String shipperName;
	private String lineName;
	private String lineNo;
	private String ownerName;
	private String calculateCount;
	private String custPhone1;
	private String contactorName1;
	
	private String strRgstDate;
	
	
	public String getStrRgstDate() {
		return strRgstDate;
	}
	public void setStrRgstDate(String strRgstDate) {
		this.strRgstDate = strRgstDate;
	}
	
	public String getCustPhone1() {
		return custPhone1;
	}
	public void setCustPhone1(String custPhone1) {
		this.custPhone1 = custPhone1;
	}
	public String getContactorName1() {
		return contactorName1;
	}
	public void setContactorName1(String contactorName1) {
		this.contactorName1 = contactorName1;
	}
	public String getShipperName() {
		return shipperName;
	}
	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getExpNo() {
		return expNo;
	}
	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}
	public String getExpType() {
		return expType;
	}
	public void setExpType(String expType) {
		this.expType = expType;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Double getArticleItems() {
		return articleItems;
	}
	public void setArticleItems(Double articleItems) {
		this.articleItems = articleItems;
	}
	public Double getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(Double totalQty) {
		this.totalQty = totalQty;
	}
	public Double getTotalVolumn() {
		return totalVolumn;
	}
	public void setTotalVolumn(Double totalVolumn) {
		this.totalVolumn = totalVolumn;
	}
	public Double getSumVolumn() {
		return sumVolumn;
	}
	public void setSumVolumn(Double sumVolumn) {
		this.sumVolumn = sumVolumn;
	}
	public Double getSumWeight() {
		return sumWeight;
	}
	public void setSumWeight(Double sumWeight) {
		this.sumWeight = sumWeight;
	}
	public Double getSumBoxQty() {
		return sumBoxQty;
	}
	public void setSumBoxQty(Double sumBoxQty) {
		this.sumBoxQty = sumBoxQty;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public Integer getExpNoCount() {
		return expNoCount;
	}
	public void setExpNoCount(Integer expNoCount) {
		this.expNoCount = expNoCount;
	}
	public Integer getCustCount() {
		return custCount;
	}
	public void setCustCount(Integer custCount) {
		this.custCount = custCount;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getCalculateCount() {
		return calculateCount;
	}
	public void setCalculateCount(String calculateCount) {
		this.calculateCount = calculateCount;
	}
	public String getCustAlias() {
		return custAlias;
	}
	public void setCustAlias(String custAlias) {
		this.custAlias = custAlias;
	}
	
	
}
