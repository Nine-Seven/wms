package com.sealinkin.report.model;

import java.util.Date;

import com.sealinkin.idata.po.Idata_CheckD;

public class Idata_Check_DSourceReportModel extends Idata_CheckD{
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String checkNo;
	private String ownerNo;
	private Short rowId;
	
	private String SCheckNo;
	private String supplierNo;
	private String supplierName;
	private String importNo;
	private String poNo;
	private String SImportNo;
	private String serialNo;
	private String ownerArticleNo;
	private String articleNo;
	private String articleName;
	private String style;
	private String colorDesc;
	private String sizeDesc;
	private Double packingQty;
	private Double poQty;
	private Double marginQty;
	private Double checkQty;
	private String groupNo;
	private String groupName;
	private String MGroupNo;
	private String MGroupName;
	private String LGroupNo;
	private String LGroupName;
	private Double boxQty;
	private Double lingQty;
	private String status;
	private Date checkEndDate;
	private Date checkDate;
	private Date importEndDate;
	private Date checkEndTime;
	private Date checkTime;
	private Date importEndTime;
	private String workerName;
	private String batchSerialNo;
	private String season;
	private String Yearmon;
	private String containerNo;
	private String sourceNo;
	private String rgstName;
	private String scanLabelNo;
	
	public String getSCheckNo() {
		return SCheckNo;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setSCheckNo(String sCheckNo) {
		SCheckNo = sCheckNo;
	}
	public Double getLingQty() {
		return lingQty;
	}
	public void setLingQty(Double lingQty) {
		this.lingQty = lingQty;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public Short getRowId() {
		return rowId;
	}
	public void setRowId(Short rowId) {
		this.rowId = rowId;
	}
	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getImportNo() {
		return importNo;
	}
	public void setImportNo(String importNo) {
		this.importNo = importNo;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getSImportNo() {
		return SImportNo;
	}
	public void setSImportNo(String sImportNo) {
		SImportNo = sImportNo;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}
	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
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
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getColorDesc() {
		return colorDesc;
	}
	public void setColorDesc(String colorDesc) {
		this.colorDesc = colorDesc;
	}
	public String getSizeDesc() {
		return sizeDesc;
	}
	public void setSizeDesc(String sizeDesc) {
		this.sizeDesc = sizeDesc;
	}
	public Double getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}
	public Double getPoQty() {
		return poQty;
	}
	public void setPoQty(Double poQty) {
		this.poQty = poQty;
	}
	public Double getMarginQty() {
		return marginQty;
	}
	public void setMarginQty(Double marginQty) {
		this.marginQty = marginQty;
	}
	public Double getCheckQty() {
		return checkQty;
	}
	public void setCheckQty(Double checkQty) {
		this.checkQty = checkQty;
	}
	public String getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	public String getMGroupNo() {
		return MGroupNo;
	}
	public void setMGroupNo(String mGroupNo) {
		MGroupNo = mGroupNo;
	}
	public String getMGroupName() {
		return MGroupName;
	}
	public void setMGroupName(String mGroupName) {
		MGroupName = mGroupName;
	}
	public String getLGroupNo() {
		return LGroupNo;
	}
	public void setLGroupNo(String lGroupNo) {
		LGroupNo = lGroupNo;
	}
	public String getLGroupName() {
		return LGroupName;
	}
	public void setLGroupName(String lGroupName) {
		LGroupName = lGroupName;
	}
	public Double getBoxQty() {
		return boxQty;
	}
	public void setBoxQty(Double boxQty) {
		this.boxQty = boxQty;
	}
	public Double getlingQty() {
		return lingQty;
	}
	public void setlingQty(Double lingQty) {
		this.lingQty = lingQty;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCheckEndDate() {
		return checkEndDate;
	}
	public void setCheckEndDate(Date checkEndDate) {
		this.checkEndDate = checkEndDate;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public Date getImportEndDate() {
		return importEndDate;
	}
	public void setImportEndDate(Date importEndDate) {
		this.importEndDate = importEndDate;
	}
	public Date getCheckEndTime() {
		return checkEndTime;
	}
	public void setCheckEndTime(Date checkEndTime) {
		this.checkEndTime = checkEndTime;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public Date getImportEndTime() {
		return importEndTime;
	}
	public void setImportEndTime(Date importEndTime) {
		this.importEndTime = importEndTime;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getBatchSerialNo() {
		return batchSerialNo;
	}
	public void setBatchSerialNo(String batchSerialNo) {
		this.batchSerialNo = batchSerialNo;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getYearmon() {
		return Yearmon;
	}
	public void setYearmon(String yearmon) {
		Yearmon = yearmon;
	}
	public String getContainerNo() {
		return containerNo;
	}
	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}
	public String getSourceNo() {
		return sourceNo;
	}
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}
	public String getRgstName() {
		return rgstName;
	}
	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}
	public String getScanLabelNo() {
		return scanLabelNo;
	}
	public void setScanLabelNo(String scanLabelNo) {
		this.scanLabelNo = scanLabelNo;
	}
}
