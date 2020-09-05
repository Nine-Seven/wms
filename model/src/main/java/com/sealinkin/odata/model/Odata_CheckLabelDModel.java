package com.sealinkin.odata.model;

import java.util.Date;

import com.sealinkin.odata.po.Odata_CheckLabelD;

public class Odata_CheckLabelDModel extends Odata_CheckLabelD {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String checkNo;
	private Long rowId;
	
	private Double uncheckQty;
	private String barcode;
	private String articleName;
	
	private Date produceDate;
	private Date expireDate;
	private String quality;
	private String lotNo;
	private String importBatchNo;
	private String rsvBatch1;
	private String rsvBatch2;
	private String rsvBatch3;
	private String rsvBatch4;
	private String rsvBatch5;
	private String rsvBatch6;
	private String rsvBatch7;
	private String rsvBatch8;
	private String textQuality;
	
	private Double qminOperatePacking;
	private String qminOperateSpec;
	private String qminOperateUnit;
	private String advanceCellNo;
	
	private String custNo;
	private String checkChuteNo;
	private String custName;
	private String custAlias;
	private String dLabelNo;
	private String realQminQty;
	
	private String packingUnit;//箱包装单位
	private String packingSpec;//箱包装规格
	private String deliverObj;
	private String strCloseFlag;//--封箱标识，'Y' 完成；‘N'未完成
	private String strFinishFlag;//--完成标识，'Y' 完成；‘N'未完成
	
	private String strPackMateName;//提示包材名称
	
	public String getStrPackMateName() {
		return strPackMateName;
	}
	public void setStrPackMateName(String strPackMateName) {
		this.strPackMateName = strPackMateName;
	}
	private String strPackMeteOut;//要求扫描包材出货
	

	public String getStrPackMeteOut() {
		return strPackMeteOut;
	}
	public void setStrPackMeteOut(String strPackMeteOut) {
		this.strPackMeteOut = strPackMeteOut;
	}
	private Double unitPacking;//基本包装数量
	private String packingUnitQmin;//中包装单位
	private String Unit;//基本包装单位
	private String packingSpecQmin;//中包装规格
	private String spec;//基本包装规格
	private Double planBox;//计划箱数
	private Double planDis;//计划散数
	private Double planQmin;//计划中包数
	private Double realBox;//实际箱数
	private Double realDis;//实际散数
	private Double realQmin;//实际中包数
	
	
	public Double getUnitPacking() {
		return unitPacking;
	}
	public void setUnitPacking(Double unitPacking) {
		this.unitPacking = unitPacking;
	}
	public String getPackingUnitQmin() {
		return packingUnitQmin;
	}
	public void setPackingUnitQmin(String packingUnitQmin) {
		this.packingUnitQmin = packingUnitQmin;
	}
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	public String getPackingSpecQmin() {
		return packingSpecQmin;
	}
	public void setPackingSpecQmin(String packingSpecQmin) {
		this.packingSpecQmin = packingSpecQmin;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
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
	public String getStrCloseFlag() {
		return strCloseFlag;
	}
	public void setStrCloseFlag(String strCloseFlag) {
		this.strCloseFlag = strCloseFlag;
	}
	public String getDeliverObj() {
		return deliverObj;
	}
	public void setDeliverObj(String deliverObj) {
		this.deliverObj = deliverObj;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCheckChuteNo() {
		return checkChuteNo;
	}
	public void setCheckChuteNo(String checkChuteNo) {
		this.checkChuteNo = checkChuteNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustAlias() {
		return custAlias;
	}
	public void setCustAlias(String custAlias) {
		this.custAlias = custAlias;
	}
	public String getdLabelNo() {
		return dLabelNo;
	}
	public void setdLabelNo(String dLabelNo) {
		this.dLabelNo = dLabelNo;
	}
	public String getRealQminQty() {
		return realQminQty;
	}
	public void setRealQminQty(String realQminQty) {
		this.realQminQty = realQminQty;
	}
	public Double getQminOperatePacking() {
		return qminOperatePacking;
	}
	public void setQminOperatePacking(Double qminOperatePacking) {
		this.qminOperatePacking = qminOperatePacking;
	}
	public String getQminOperateSpec() {
		return qminOperateSpec;
	}
	public void setQminOperateSpec(String qminOperateSpec) {
		this.qminOperateSpec = qminOperateSpec;
	}
	public String getQminOperateUnit() {
		return qminOperateUnit;
	}
	public void setQminOperateUnit(String qminOperateUnit) {
		this.qminOperateUnit = qminOperateUnit;
	}
	public String getAdvanceCellNo() {
		return advanceCellNo;
	}
	public void setAdvanceCellNo(String advanceCellNo) {
		this.advanceCellNo = advanceCellNo;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
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
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public Long getRowId() {
		return rowId;
	}
	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}
	public Double getUncheckQty() {
		return uncheckQty;
	}
	public void setUncheckQty(Double uncheckQty) {
		this.uncheckQty = uncheckQty;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public Date getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getImportBatchNo() {
		return importBatchNo;
	}
	public void setImportBatchNo(String importBatchNo) {
		this.importBatchNo = importBatchNo;
	}
	public String getRsvBatch1() {
		return rsvBatch1;
	}
	public void setRsvBatch1(String rsvBatch1) {
		this.rsvBatch1 = rsvBatch1;
	}
	public String getRsvBatch2() {
		return rsvBatch2;
	}
	public void setRsvBatch2(String rsvBatch2) {
		this.rsvBatch2 = rsvBatch2;
	}
	public String getRsvBatch3() {
		return rsvBatch3;
	}
	public void setRsvBatch3(String rsvBatch3) {
		this.rsvBatch3 = rsvBatch3;
	}
	public String getRsvBatch4() {
		return rsvBatch4;
	}
	public void setRsvBatch4(String rsvBatch4) {
		this.rsvBatch4 = rsvBatch4;
	}
	public String getRsvBatch5() {
		return rsvBatch5;
	}
	public void setRsvBatch5(String rsvBatch5) {
		this.rsvBatch5 = rsvBatch5;
	}
	public String getRsvBatch6() {
		return rsvBatch6;
	}
	public void setRsvBatch6(String rsvBatch6) {
		this.rsvBatch6 = rsvBatch6;
	}
	public String getRsvBatch7() {
		return rsvBatch7;
	}
	public void setRsvBatch7(String rsvBatch7) {
		this.rsvBatch7 = rsvBatch7;
	}
	public String getRsvBatch8() {
		return rsvBatch8;
	}
	public void setRsvBatch8(String rsvBatch8) {
		this.rsvBatch8 = rsvBatch8;
	}
	public String getTextQuality() {
		return textQuality;
	}
	public void setTextQuality(String textQuality) {
		this.textQuality = textQuality;
	}
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public String getPackingSpec() {
		return packingSpec;
	}
	public void setPackingSpec(String packingSpec) {
		this.packingSpec = packingSpec;
	}
	public String getStrFinishFlag() {
		return strFinishFlag;
	}
	public void setStrFinishFlag(String strFinishFlag) {
		this.strFinishFlag = strFinishFlag;
	}
	
}
