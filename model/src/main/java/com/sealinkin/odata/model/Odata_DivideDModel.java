package com.sealinkin.odata.model;

import java.util.Date;

import com.sealinkin.odata.po.Odata_DivideD;

public class Odata_DivideDModel extends Odata_DivideD{
	private static final long serialVersionUID = 1L;
	private String divideNo;
	private String warehouseNo;
	private String ownerNo;
	private Long divideId;
	private String enterpriseNo;
    private String custContainerNo;
    private String status;
	
    private String shipperDeliverNo;//快递单号
    private String advanCellNO;//来源储位    
	
	private String barcode;
	private String articleName;
	private String labelNo;
	private String DlabelNo;
	private Double planWholenum;
	private Double planScatterednum;
	private Double realWholenum;
	private Double realScatterednum;
	private String custName;
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
	private Double divideExcess;
	private Double qty;
	private String ownerArticleNo;
	//add by huangcx at 20160601
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
	
	//add by hj at 20160704
	private String dpsCellNo;
	private Double articleQty;
	private Double realQty;
	private String deliverObj;
	
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getCustContainerNo() {
		return custContainerNo;
	}
	public void setCustContainerNo(String custContainerNo) {
		this.custContainerNo = custContainerNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getDivideNo() {
		return divideNo;
	}
	public void setDivideNo(String divideNo) {
		this.divideNo = divideNo;
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
	public Long getDivideId() {
		return divideId;
	}
	public void setDivideId(Long divideId) {
		this.divideId = divideId;
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

	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getDlabelNo() {
		return DlabelNo;
	}
	public void setDlabelNo(String dlabelNo) {
		DlabelNo = dlabelNo;
	}
	public Double getPlanWholenum() {
		return planWholenum;
	}
	public void setPlanWholenum(Double planWholenum) {
		this.planWholenum = planWholenum;
	}
	public Double getPlanScatterednum() {
		return planScatterednum;
	}
	public void setPlanScatterednum(Double planScatterednum) {
		this.planScatterednum = planScatterednum;
	}
	public Double getRealWholenum() {
		return realWholenum;
	}
	public void setRealWholenum(Double realWholenum) {
		this.realWholenum = realWholenum;
	}
	public Double getRealScatterednum() {
		return realScatterednum;
	}
	public void setRealScatterednum(Double realScatterednum) {
		this.realScatterednum = realScatterednum;
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
	public Double getDivideExcess() {
		return divideExcess;
	}
	public void setDivideExcess(Double divideExcess) {
		this.divideExcess = divideExcess;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}
	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
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
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
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
	public String getDpsCellNo() {
		return dpsCellNo;
	}
	public void setDpsCellNo(String dpsCellNo) {
		this.dpsCellNo = dpsCellNo;
	}
	public Double getArticleQty() {
		return articleQty;
	}
	public void setArticleQty(Double articleQty) {
		this.articleQty = articleQty;
	}
	public Double getRealQty() {
		return realQty;
	}
	public void setRealQty(Double realQty) {
		this.realQty = realQty;
	}
	public String getDeliverObj() {
		return deliverObj;
	}
	public void setDeliverObj(String deliverObj) {
		this.deliverObj = deliverObj;
	}
	public String getShipperDeliverNo() {
		return shipperDeliverNo;
	}
	public void setShipperDeliverNo(String shipperDeliverNo) {
		this.shipperDeliverNo = shipperDeliverNo;
	}
	
	public String getadvanCellNO() {
		return advanCellNO;
	}
	public void setadvanCellNO(String advanCellNO) {
		this.advanCellNO = advanCellNO;
	}
	
}
