package com.sealinkin.odata.model;

import java.util.Date;

import com.sealinkin.odata.po.Odata_ExpD;

public class Odata_ExpDModel extends Odata_ExpD{

	private static final long serialVersionUID = 1L;
	
	private String warehouseNo;
	private String ownerNo;
	private String expNo;
	private short rowId;
	private String articleNo;
	private double packingQty;
	private double articleQty;
	private double scheduleQty;
	private double locateQty;
	private double realQty;
	private double unitCost;
	private String ownerArticleNo;
	private String produceCondition;
	private Date produceValue1;
	private Date produceValue2;
	private String expireCondition;
	private Date expireValue1;
	private Date expireValue2;
	private String qualityCondition;
	private String qualityValue1;
	private String qualityValue2;
	private String lotnoCondition; 
	private String lotnoValue1;
	private String lotnoValue2;
	private String rsvbatch1Condition;
	private String rsvbatch1Value1;
	private String rsvbatch1Value2;
	private String rsvbatch2Condition;
	private String rsvbatch2Value1;
	private String rsvbatch2Value2;
	private String rsvbatch3Condition;
	private String rsvbatch3Value1;
	private String rsvbatch3Value2;
	private String rsvbatch4Condition;
	private String rsvbatch4Value1;
	private String rsvbatch4Value2;
	private String rsvbatch5Condition;
	private String rsvbatch5Value1;
	private String rsvbatch5Value2;
	private String rsvbatch6Condition;
	private String rsvbatch6Value1;
	private String rsvbatch6Value2;
	private String rsvbatch7Condition;
	private String rsvbatch7Value1;
	private String rsvbatch7Value2;
	private String rsvbatch8Condition;
	private String rsvbatch8Value1;
	private String rsvbatch8Value2;
	private String specifyField;
	private String specifyCondition;
	private String specifyValue1;
	private String specifyValue2;
	private String status;
	private String errorStatus;
	private Date rgstDate;
	private Date expDate;
	
	private String barcode;
	private String articleName;
	private Double totalQty;
	private Double availableQty;
	private Double noEnoughQty;
	
	private String ownerName;
	private String condition;
	private String specialBatch;
	private Double volumn;
	private Double weight;
	private Double boxs;
	private double pobox;
	private double popcs;
	
	private String produceCond;
	private Date produceV1;
	private Date produceV2;
	
	private String sourceexpNo;
	private String labelNo;
	
	private Double planQty;
	private Double scanQty;
	private Double diffQty;
	
	private String custNo;
	private String custName;
	private String strRgstDate2;
	private String statusDesc;
	
	//add by huangcx at 20160528
	private Double qminOperatePacking;//最小操作包装数量
	private Double unitPacking;//基本包装数量
	private String packingUnit;//箱包装单位
	private String packingUnitQmin;//中包装单位
	private String Unit;//基本包装单位
	private String packingSpec;//箱包装规格
	private String packingSpecQmin;//中包装规格
	private String spec;//基本包装规格
	private Double planBox;//计划箱数
	private Double planDis;//计划散数
	private Double planQmin;//计划中包数
	private Double realBox;//实际箱数
	private Double realDis;//实际散数
	private Double realQmin;//实际中包数
	//end add
	

	
	
	
	
	public String getStrRgstDate2() {
		return strRgstDate2;
	}
	public void setStrRgstDate2(String strRgstDate2) {
		this.strRgstDate2 = strRgstDate2;
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
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public Double getPlanQty() {
		return planQty;
	}
	public void setPlanQty(Double planQty) {
		this.planQty = planQty;
	}
	public Double getScanQty() {
		return scanQty;
	}
	public void setScanQty(Double scanQty) {
		this.scanQty = scanQty;
	}
	public Double getDiffQty() {
		return diffQty;
	}
	public void setDiffQty(Double diffQty) {
		this.diffQty = diffQty;
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
	public String getExpNo() {
		return expNo;
	}
	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public double getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(double packingQty) {
		this.packingQty = packingQty;
	}
	public double getArticleQty() {
		return articleQty;
	}
	public void setArticleQty(double articleQty) {
		this.articleQty = articleQty;
	}
	public double getScheduleQty() {
		return scheduleQty;
	}
	public void setScheduleQty(double scheduleQty) {
		this.scheduleQty = scheduleQty;
	}
	public double getLocateQty() {
		return locateQty;
	}
	public void setLocateQty(double locateQty) {
		this.locateQty = locateQty;
	}
	public double getRealQty() {
		return realQty;
	}
	public void setRealQty(double realQty) {
		this.realQty = realQty;
	}
	public double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}
	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}
	public String getProduceCondition() {
		return produceCondition;
	}
	public void setProduceCondition(String produceCondition) {
		this.produceCondition = produceCondition;
	}
	public Date getProduceValue1() {
		return produceValue1;
	}
	public void setProduceValue1(Date produceValue1) {
		this.produceValue1 = produceValue1;
	}
	public Date getProduceValue2() {
		return produceValue2;
	}
	public void setProduceValue2(Date produceValue2) {
		this.produceValue2 = produceValue2;
	}
	public String getExpireCondition() {
		return expireCondition;
	}
	public void setExpireCondition(String expireCondition) {
		this.expireCondition = expireCondition;
	}
	public Date getExpireValue1() {
		return expireValue1;
	}
	public void setExpireValue1(Date expireValue1) {
		this.expireValue1 = expireValue1;
	}
	public Date getExpireValue2() {
		return expireValue2;
	}
	public void setExpireValue2(Date expireValue2) {
		this.expireValue2 = expireValue2;
	}
	public String getQualityCondition() {
		return qualityCondition;
	}
	public void setQualityCondition(String qualityCondition) {
		this.qualityCondition = qualityCondition;
	}
	public String getQualityValue1() {
		return qualityValue1;
	}
	public void setQualityValue1(String qualityValue1) {
		this.qualityValue1 = qualityValue1;
	}
	public String getQualityValue2() {
		return qualityValue2;
	}
	public void setQualityValue2(String qualityValue2) {
		this.qualityValue2 = qualityValue2;
	}
	public String getLotnoCondition() {
		return lotnoCondition;
	}
	public void setLotnoCondition(String lotnoCondition) {
		this.lotnoCondition = lotnoCondition;
	}
	public String getLotnoValue1() {
		return lotnoValue1;
	}
	public void setLotnoValue1(String lotnoValue1) {
		this.lotnoValue1 = lotnoValue1;
	}
	public String getLotnoValue2() {
		return lotnoValue2;
	}
	public void setLotnoValue2(String lotnoValue2) {
		this.lotnoValue2 = lotnoValue2;
	}
	public String getRsvbatch1Condition() {
		return rsvbatch1Condition;
	}
	public void setRsvbatch1Condition(String rsvbatch1Condition) {
		this.rsvbatch1Condition = rsvbatch1Condition;
	}
	public String getRsvbatch1Value1() {
		return rsvbatch1Value1;
	}
	public void setRsvbatch1Value1(String rsvbatch1Value1) {
		this.rsvbatch1Value1 = rsvbatch1Value1;
	}
	public String getRsvbatch1Value2() {
		return rsvbatch1Value2;
	}
	public void setRsvbatch1Value2(String rsvbatch1Value2) {
		this.rsvbatch1Value2 = rsvbatch1Value2;
	}
	public String getRsvbatch2Condition() {
		return rsvbatch2Condition;
	}
	public void setRsvbatch2Condition(String rsvbatch2Condition) {
		this.rsvbatch2Condition = rsvbatch2Condition;
	}
	public String getRsvbatch2Value1() {
		return rsvbatch2Value1;
	}
	public void setRsvbatch2Value1(String rsvbatch2Value1) {
		this.rsvbatch2Value1 = rsvbatch2Value1;
	}
	public String getRsvbatch2Value2() {
		return rsvbatch2Value2;
	}
	public void setRsvbatch2Value2(String rsvbatch2Value2) {
		this.rsvbatch2Value2 = rsvbatch2Value2;
	}
	public String getRsvbatch3Condition() {
		return rsvbatch3Condition;
	}
	public void setRsvbatch3Condition(String rsvbatch3Condition) {
		this.rsvbatch3Condition = rsvbatch3Condition;
	}
	public String getRsvbatch3Value1() {
		return rsvbatch3Value1;
	}
	public void setRsvbatch3Value1(String rsvbatch3Value1) {
		this.rsvbatch3Value1 = rsvbatch3Value1;
	}
	public String getRsvbatch3Value2() {
		return rsvbatch3Value2;
	}
	public void setRsvbatch3Value2(String rsvbatch3Value2) {
		this.rsvbatch3Value2 = rsvbatch3Value2;
	}
	public String getRsvbatch4Condition() {
		return rsvbatch4Condition;
	}
	public void setRsvbatch4Condition(String rsvbatch4Condition) {
		this.rsvbatch4Condition = rsvbatch4Condition;
	}
	public String getRsvbatch4Value1() {
		return rsvbatch4Value1;
	}
	public void setRsvbatch4Value1(String rsvbatch4Value1) {
		this.rsvbatch4Value1 = rsvbatch4Value1;
	}
	public String getRsvbatch4Value2() {
		return rsvbatch4Value2;
	}
	public void setRsvbatch4Value2(String rsvbatch4Value2) {
		this.rsvbatch4Value2 = rsvbatch4Value2;
	}
	public String getRsvbatch5Condition() {
		return rsvbatch5Condition;
	}
	public void setRsvbatch5Condition(String rsvbatch5Condition) {
		this.rsvbatch5Condition = rsvbatch5Condition;
	}
	public String getRsvbatch5Value1() {
		return rsvbatch5Value1;
	}
	public void setRsvbatch5Value1(String rsvbatch5Value1) {
		this.rsvbatch5Value1 = rsvbatch5Value1;
	}
	public String getRsvbatch5Value2() {
		return rsvbatch5Value2;
	}
	public void setRsvbatch5Value2(String rsvbatch5Value2) {
		this.rsvbatch5Value2 = rsvbatch5Value2;
	}
	public String getRsvbatch6Condition() {
		return rsvbatch6Condition;
	}
	public void setRsvbatch6Condition(String rsvbatch6Condition) {
		this.rsvbatch6Condition = rsvbatch6Condition;
	}
	public String getRsvbatch6Value1() {
		return rsvbatch6Value1;
	}
	public void setRsvbatch6Value1(String rsvbatch6Value1) {
		this.rsvbatch6Value1 = rsvbatch6Value1;
	}
	public String getRsvbatch6Value2() {
		return rsvbatch6Value2;
	}
	public void setRsvbatch6Value2(String rsvbatch6Value2) {
		this.rsvbatch6Value2 = rsvbatch6Value2;
	}
	public String getRsvbatch7Condition() {
		return rsvbatch7Condition;
	}
	public void setRsvbatch7Condition(String rsvbatch7Condition) {
		this.rsvbatch7Condition = rsvbatch7Condition;
	}
	public String getRsvbatch7Value1() {
		return rsvbatch7Value1;
	}
	public void setRsvbatch7Value1(String rsvbatch7Value1) {
		this.rsvbatch7Value1 = rsvbatch7Value1;
	}
	public String getRsvbatch7Value2() {
		return rsvbatch7Value2;
	}
	public void setRsvbatch7Value2(String rsvbatch7Value2) {
		this.rsvbatch7Value2 = rsvbatch7Value2;
	}
	public String getRsvbatch8Condition() {
		return rsvbatch8Condition;
	}
	public void setRsvbatch8Condition(String rsvbatch8Condition) {
		this.rsvbatch8Condition = rsvbatch8Condition;
	}
	public String getRsvbatch8Value1() {
		return rsvbatch8Value1;
	}
	public void setRsvbatch8Value1(String rsvbatch8Value1) {
		this.rsvbatch8Value1 = rsvbatch8Value1;
	}
	public String getRsvbatch8Value2() {
		return rsvbatch8Value2;
	}
	public void setRsvbatch8Value2(String rsvbatch8Value2) {
		this.rsvbatch8Value2 = rsvbatch8Value2;
	}
	public String getSpecifyField() {
		return specifyField;
	}
	public void setSpecifyField(String specifyField) {
		this.specifyField = specifyField;
	}
	public String getSpecifyCondition() {
		return specifyCondition;
	}
	public void setSpecifyCondition(String specifyCondition) {
		this.specifyCondition = specifyCondition;
	}
	public String getSpecifyValue1() {
		return specifyValue1;
	}
	public void setSpecifyValue1(String specifyValue1) {
		this.specifyValue1 = specifyValue1;
	}
	public String getSpecifyValue2() {
		return specifyValue2;
	}
	public void setSpecifyValue2(String specifyValue2) {
		this.specifyValue2 = specifyValue2;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorStatus() {
		return errorStatus;
	}
	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}
	public Date getRgstDate() {
		return rgstDate;
	}
	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public Double getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(Double totalQty) {
		this.totalQty = totalQty;
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
	public Double getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(Double availableQty) {
		this.availableQty = availableQty;
	}
	public Double getNoEnoughQty() {
		return noEnoughQty;
	}
	public void setNoEnoughQty(Double noEnoughQty) {
		this.noEnoughQty = noEnoughQty;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getSpecialBatch() {
		return specialBatch;
	}
	public void setSpecialBatch(String specialBatch) {
		this.specialBatch = specialBatch;
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
	public Double getBoxs() {
		return boxs;
	}
	public void setBoxs(Double boxs) {
		this.boxs = boxs;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	public double getPobox() {
		return pobox;
	}
	public void setPobox(double pobox) {
		this.pobox = pobox;
	}
	public double getPopcs() {
		return popcs;
	}
	public void setPopcs(double popcs) {
		this.popcs = popcs;
	}
	public String getProduceCond() {
		return produceCond;
	}
	public void setProduceCond(String produceCond) {
		this.produceCond = produceCond;
	}
	public Date getProduceV1() {
		return produceV1;
	}
	public void setProduceV1(Date produceV1) {
		this.produceV1 = produceV1;
	}
	public Date getProduceV2() {
		return produceV2;
	}
	public void setProduceV2(Date produceV2) {
		this.produceV2 = produceV2;
	}
	public short getRowId() {
		return rowId;
	}
	public void setRowId(short rowId) {
		this.rowId = rowId;
	}
	public String getSourceexpNo() {
		return sourceexpNo;
	}
	public void setSourceexpNo(String sourceexpNo) {
		this.sourceexpNo = sourceexpNo;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
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
	public String getPackingSpec() {
		return packingSpec;
	}
	public void setPackingSpec(String packingSpec) {
		this.packingSpec = packingSpec;
	}
	public String getPackingSpecQmin() {
		return packingSpecQmin;
	}
	public void setPackingSpecQmin(String packingSpecQmin) {
		this.packingSpecQmin = packingSpecQmin;
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
}
