package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;

/**
 * 下架信息结构StuOutStockDate
 * @author lich
 *
 */
public class OutStockDateAnswerModel implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ownerNo;//委托业主
     private String outStockNo;//下架单号  
     private String operateType;//下架类型
     private String LabelNo;//标签号
     private String dLabelNo;//目的标签号yfk 2016623
     private String divideNo;//分播号
	private String scellNo;//来源储位 
     private String dcellNo;//目的储位
     public double articleQty;//计划数量
     private String deliverObj;//配送对象yfk 2016623
     private String dpsCellNo;//格子号
	
	private double realQty;//下架数量yfk 2016623
   
	
	private String articleNo;
     private String barcode;
     private String articleName;
     private String itemType;
     //医药行业属性
     private String lotNo;
     private String produceDate;
     private String expireDate;
     private String quality;

     private String importBatchNo;//	验收批次 
     private String rsvBatch1;//	rsvBatch1
     private String rsvBatch2;//	rsvBatch2
     private String rsvBatch3;//	rsvBatch3
     private String rsvBatch4;//	rsvBatch4
     private String rsvBatch5;//	rsvBatch5
     private String rsvBatch6;//	rsvBatch6
     private String rsvBatch7;//	rsvBatch7
     private String rsvBatch8;//	rsvBatch8

     private String expiryDays;//有效期

     public double palBaseBox;
     public double palHeightBox;
     
     private String dispCellNo;//来源储位 显示用
     private String WareNo;//波次 
 	
	private double  packQty;
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
 	
 	public String getDpsCellNo() {
		return dpsCellNo;
	}
	public void setDpsCellNo(String dpsCellNo) {
		this.dpsCellNo = dpsCellNo;
	}
	public String getDivideNo() {
		return divideNo;
	}
	public void setDivideNo(String divideNo) {
		this.divideNo = divideNo;
	}
	public String getWareNo() {
		return WareNo;
	}
	public void setWareNo(String wareNo) {
		WareNo = wareNo;
	}
	public String getdLabelNo() {
 		return dLabelNo;
 	}
 	public void setdLabelNo(String dLabelNo) {
 		this.dLabelNo = dLabelNo;
 	}
 	public double getRealQty() {
		return realQty;
	}
	public void setRealQty(double realQty) {
		this.realQty = realQty;
	}
 	 public String getDeliverObj() {
 		return deliverObj;
 	}
 	public void setDeliverObj(String deliverObj) {
 		this.deliverObj = deliverObj;
 	}
	
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getOutStockNo() {
		return outStockNo;
	}
	public void setOutStockNo(String outStockNo) {
		this.outStockNo = outStockNo;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getLabelNo() {
		return LabelNo;
	}
	public void setLabelNo(String labelNo) {
		LabelNo = labelNo;
	}
	public String getScellNo() {
		return scellNo;
	}
	public void setScellNo(String scellNo) {
		this.scellNo = scellNo;
	}
	public String getDcellNo() {
		return dcellNo;
	}
	public void setDcellNo(String dcellNo) {
		this.dcellNo = dcellNo;
	}
	public double getArticleQty() {
		return articleQty;
	}
	public void setArticleQty(double articleQty) {
		this.articleQty = articleQty;
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
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
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
	public String getExpiryDays() {
		return expiryDays;
	}
	public void setExpiryDays(String expiryDays) {
		this.expiryDays = expiryDays;
	}
	public double getPalBaseBox() {
		return palBaseBox;
	}
	public void setPalBaseBox(double palBaseBox) {
		this.palBaseBox = palBaseBox;
	}
	public double getPalHeightBox() {
		return palHeightBox;
	}
	public void setPalHeightBox(double palHeightBox) {
		this.palHeightBox = palHeightBox;
	}
	public String getDispCellNo() {
		return dispCellNo;
	}
	public void setDispCellNo(String dispCellNo) {
		this.dispCellNo = dispCellNo;
	}
	public double getPackQty() {
		return packQty;
	}
	public void setPackQty(double packQty) {
		this.packQty = packQty;
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
     
     
}
