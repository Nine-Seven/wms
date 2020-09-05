package com.sealinkin.rodata.model;

import java.util.Date;

import com.sealinkin.rodata.po.Rodata_OutstockD;

public class Rodata_OutstockDModel extends Rodata_OutstockD{
	private static final long serialVersionUID = 1L;
	private String outstockNo;
	private String warehouseNo;
	private String ownerNo;
	private String sourceNo;
	private Integer divideId;
	private String SCellNo;
	private Long SCellId;
	private String outstockCellNo;
	
	private String articleName;
	private String barcode;
	private Double planWholenum;
	private Double realWholenum;
	private String quality;
	private String recedeNo;
	private String qualityText;
	private String ownerArticleNo;
	private String poNo;
	private Double unitCost;//单价
	private Double qtyPrice;//总金额
	private String statusText;
	private String checkQty;
	private String unCheckQty;
	private String scanQty;
	private String unScanQty;
	
    private String rsvBatch1;//	strRsvBatch1
    private String rsvBatch2;//	strRsvBatch2
    private String rsvBatch3;//	strRsvBatch3
    private String rsvBatch4;//	strRsvBatch4
    private String rsvBatch5;//	strRsvBatch5
    private String rsvBatch6;//	strRsvBatch6
    private String rsvBatch7;//	strRsvBatch7
    private String rsvBatch8;//	strRsvBatch8
    private String lotNo;//医药行业属性
    private Date produceDate;
    private Date expireDate;
    //update by czh 2016.5.30
    private Double qminOperatePacking;//最小操作包装数量
	private Double unitPacking;//基本包装数量
	private String packingUnit;//包装单位
	private String packingUnitQmin;//中包装单位
	private String Unit;//基本包装单位
	private String packingSpec;//包装规格
	private String packingSpecQmin;//中包装规格
	private String spec;//基本包装规格
	private Double planBox;//计划箱数
	private Double planDis;//计划散数
	private Double planQmin;//计划中包数
	private Double realBox;//实际箱数
	private Double realDis;//实际散数
	private Double realQmin;//实际中包数
    
	
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
	public String getQualityText() {
		return qualityText;
	}
	public void setQualityText(String qualityText) {
		this.qualityText = qualityText;
	}
	public String getRecedeNo() {
		return recedeNo;
	}
	public void setRecedeNo(String recedeNo) {
		this.recedeNo = recedeNo;
	}
	public String getOutstockNo() {
		return outstockNo;
	}
	public void setOutstockNo(String outstockNo) {
		this.outstockNo = outstockNo;
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
	public String getSourceNo() {
		return sourceNo;
	}
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}
	public Integer getDivideId() {
		return divideId;
	}
	public void setDivideId(Integer divideId) {
		this.divideId = divideId;
	}
	public String getSCellNo() {
		return SCellNo;
	}
	public void setSCellNo(String sCellNo) {
		SCellNo = sCellNo;
	}
	public Long getSCellId() {
		return SCellId;
	}
	public void setSCellId(Long sCellId) {
		SCellId = sCellId;
	}
	public String getOutstockCellNo() {
		return outstockCellNo;
	}
	public void setOutstockCellNo(String outstockCellNo) {
		this.outstockCellNo = outstockCellNo;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public Double getPlanWholenum() {
		return planWholenum;
	}
	public void setPlanWholenum(Double planWholenum) {
		this.planWholenum = planWholenum;
	}
	
	public Double getRealWholenum() {
		return realWholenum;
	}
	public void setRealWholenum(Double realWholenum) {
		this.realWholenum = realWholenum;
	}
	
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}
	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public Double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}
	public Double getQtyPrice() {
		return qtyPrice;
	}
	public void setQtyPrice(Double qtyPrice) {
		this.qtyPrice = qtyPrice;
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
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
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
	public String getUnCheckQty() {
		return unCheckQty;
	}
	public void setUnCheckQty(String unCheckQty) {
		this.unCheckQty = unCheckQty;
	}
	public String getScanQty() {
		return scanQty;
	}
	public void setScanQty(String scanQty) {
		this.scanQty = scanQty;
	}
	public String getUnScanQty() {
		return unScanQty;
	}
	public void setUnScanQty(String unScanQty) {
		this.unScanQty = unScanQty;
	}
	public String getCheckQty() {
		return checkQty;
	}
	public void setCheckQty(String checkQty) {
		this.checkQty = checkQty;
	}	
	
	
}
