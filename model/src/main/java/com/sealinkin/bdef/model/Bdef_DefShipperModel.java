package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_Defshipper;

public class Bdef_DefShipperModel extends Bdef_Defshipper{

	private static final long serialVersionUID = 8915207298819228328L;
	private String enterpriseNo;
	private String warehouseNo;
	private String shipperNo;
	private String statusText;
	
	private String reportId;
	private String printType;
	private String paperType;
	private String singleLocateFlag;
	private String shipperType;
	private String paperComifireFlag;
	private String getPaperType;
	private String shipperTypeText;
	private String paperTypeText;
	
	
	
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
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
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getPrintType() {
		return printType;
	}
	public void setPrintType(String printType) {
		this.printType = printType;
	}
	public String getPaperType() {
		return paperType;
	}
	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}
	public String getSingleLocateFlag() {
		return singleLocateFlag;
	}
	public void setSingleLocateFlag(String singleLocateFlag) {
		this.singleLocateFlag = singleLocateFlag;
	}
	public String getShipperType() {
		return shipperType;
	}
	public void setShipperType(String shipperType) {
		this.shipperType = shipperType;
	}
	public String getPaperComifireFlag() {
		return paperComifireFlag;
	}
	public void setPaperComifireFlag(String paperComifireFlag) {
		this.paperComifireFlag = paperComifireFlag;
	}
	public String getGetPaperType() {
		return getPaperType;
	}
	public void setGetPaperType(String getPaperType) {
		this.getPaperType = getPaperType;
	}
	public String getShipperTypeText() {
		return shipperTypeText;
	}
	public void setShipperTypeText(String shipperTypeText) {
		this.shipperTypeText = shipperTypeText;
	}
	public String getPaperTypeText() {
		return paperTypeText;
	}
	public void setPaperTypeText(String paperTypeText) {
		this.paperTypeText = paperTypeText;
	}
	
}
