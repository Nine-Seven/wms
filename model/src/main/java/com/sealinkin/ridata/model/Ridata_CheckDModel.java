package com.sealinkin.ridata.model;

import com.sealinkin.ridata.po.Ridata_CheckD;


public class Ridata_CheckDModel extends Ridata_CheckD{

	private static final long serialVersionUID = 1L;
	private String checkNo;
	private String warehouseNo;
	private String ownerNo;
	private Short rowId;
	private String supplierNo;
	private String enterpriseNo;
	 
	private String articleName;
	private Double pobox;
	private Double popcs;
	private Double checkbox;
	private Double checkpcs;
	private Double untreadQty;
	//private String unit;
	//private String spec;
	private String untreadNo;
	private String SUntreadNo;
	private String ownerArticleNo;

	//private String packingUnit;
	//private Double packingQty;
	private String expiryDays;
	private String printerGroupNo;
	private String dockNo;
	private String workerNo;
	private String checkTools;
	private String batchSerialNo;
	private String itemType;
	private String custNo;
	private String custName;
	private Double qty;
	private String labelNo;
	private String subLabelNo;
	private String lotType;
	private String deviceNo;
	private String labelId;
	private String classType;
	private String rsvAttr2;//款号
	private String qualityFlag;//单据品质 
	private String cellNo;//目的储位号
	private String qualityText;//商品品质
	private String strPrintFlag;
	private String untreadType;
	
	private Double realBox;//实际箱数
	private Double realDis;//实际散数
	private Double realQmin;//实际中包数
	private Double planBox;//计划箱数
	private Double planDis;//计划散数
	private Double planQmin;//计划中包数
	private String packingUnit;//箱包装单位
	private String packingUnitQmin;//中包装单位
	private String unit;//基本包装单位
	private String packingSpec;//箱包装规格
	private Double qminOperatePacking;//最小操作包装数量
	private Double unitPacking;//基本包装数量
	private String packingSpecQmin;//中包装规格
	private String spec;//基本包装规格
	  
	  
	public Double getRealBox() {
		return realBox;
	}
	public void setRealBox(Double realBox) {
		this.realBox = realBox;
	}
	public Double getRealDis() {
		return realDis;
	}
	public void setRealDis(Double realDis) {
		this.realDis = realDis;
	}
	public Double getRealQmin() {
		return realQmin;
	}
	public void setRealQmin(Double realQmin) {
		this.realQmin = realQmin;
	}
	public Double getPlanBox() {
		return planBox;
	}
	public void setPlanBox(Double planBox) {
		this.planBox = planBox;
	}
	public Double getPlanDis() {
		return planDis;
	}
	public void setPlanDis(Double planDis) {
		this.planDis = planDis;
	}
	public Double getPlanQmin() {
		return planQmin;
	}
	public void setPlanQmin(Double planQmin) {
		this.planQmin = planQmin;
	}
	public String getPackingUnitQmin() {
		return packingUnitQmin;
	}
	public void setPackingUnitQmin(String packingUnitQmin) {
		this.packingUnitQmin = packingUnitQmin;
	}
	public String getPackingSpec() {
		return packingSpec;
	}
	public void setPackingSpec(String packingSpec) {
		this.packingSpec = packingSpec;
	}
	public Double getQminOperatePacking() {
		return qminOperatePacking;
	}
	public void setQminOperatePacking(Double qminOperatePacking) {
		this.qminOperatePacking = qminOperatePacking;
	}
	public Double getUnitPacking() {
		return unitPacking;
	}
	public void setUnitPacking(Double unitPacking) {
		this.unitPacking = unitPacking;
	}
	public String getPackingSpecQmin() {
		return packingSpecQmin;
	}
	public void setPackingSpecQmin(String packingSpecQmin) {
		this.packingSpecQmin = packingSpecQmin;
	}
	public String getUntreadType() {
		return untreadType;
	}
	public void setUntreadType(String untreadType) {
		this.untreadType = untreadType;
	}
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
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
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public Double getPobox() {
		return pobox;
	}
	public void setPobox(Double pobox) {
		this.pobox = pobox;
	}
	public Double getPopcs() {
		return popcs;
	}
	public void setPopcs(Double popcs) {
		this.popcs = popcs;
	}
	public Double getCheckbox() {
		return checkbox;
	}
	public void setCheckbox(Double checkbox) {
		this.checkbox = checkbox;
	}
	public Double getCheckpcs() {
		return checkpcs;
	}
	public void setCheckpcs(Double checkpcs) {
		this.checkpcs = checkpcs;
	}
	
	public Double getUntreadQty() {
		return untreadQty;
	}
	public void setUntreadQty(Double untreadQty) {
		this.untreadQty = untreadQty;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getUntreadNo() {
		return untreadNo;
	}
	public void setUntreadNo(String untreadNo) {
		this.untreadNo = untreadNo;
	}
	public String getSUntreadNo() {
		return SUntreadNo;
	}
	public void setSUntreadNo(String sUntreadNo) {
		SUntreadNo = sUntreadNo;
	}

	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}
	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}
	public String getExpiryDays() {
		return expiryDays;
	}
	public void setExpiryDays(String expiryDays) {
		this.expiryDays = expiryDays;
	}
	public String getPrinterGroupNo() {
		return printerGroupNo;
	}
	public void setPrinterGroupNo(String printerGroupNo) {
		this.printerGroupNo = printerGroupNo;
	}
	public String getDockNo() {
		return dockNo;
	}
	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}
	public String getWorkerNo() {
		return workerNo;
	}
	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}
	public String getCheckTools() {
		return checkTools;
	}
	public void setCheckTools(String checkTools) {
		this.checkTools = checkTools;
	}
	public String getBatchSerialNo() {
		return batchSerialNo;
	}
	public void setBatchSerialNo(String batchSerialNo) {
		this.batchSerialNo = batchSerialNo;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public String getSubLabelNo() {
		return subLabelNo;
	}
	public void setSubLabelNo(String subLabelNo) {
		this.subLabelNo = subLabelNo;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getLotType() {
		return lotType;
	}
	public void setLotType(String lotType) {
		this.lotType = lotType;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getLabelId() {
		return labelId;
	}
	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	
	public String getRsvAttr2() {
		return rsvAttr2;
	}
	public void setRsvAttr2(String rsvAttr2) {
		this.rsvAttr2 = rsvAttr2;
	}
	public String getQualityFlag() {
		return qualityFlag;
	}
	public void setQualityFlag(String qualityFlag) {
		this.qualityFlag = qualityFlag;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getQualityText() {
		return qualityText;
	}
	public void setQualityText(String qualityText) {
		this.qualityText = qualityText;
	}
	public String getStrPrintFlag() {
		return strPrintFlag;
	}
	public void setStrPrintFlag(String strPrintFlag) {
		this.strPrintFlag = strPrintFlag;
	}

	

}
