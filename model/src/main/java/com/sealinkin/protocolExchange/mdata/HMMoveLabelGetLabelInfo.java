package com.sealinkin.protocolExchange.mdata;


/**
 * 过季转应季获取标库存签信息  StuHMSeasonChangeReq
 * @author wyf
 *
 */
public class HMMoveLabelGetLabelInfo {
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
    private String articleNo;
    private String articleName;
    private String packingUnit;
    //private Integer articleID;
    private String SCellNo;
    private String SLabelNo;
    private String labelNo;
    private String packingQty;
    private String moveQty;
    private String produceDate;
    private String expireDate;
    private String quality;
    private String lotNo;
    private String rsvBatch1;//	rsv_batch1
    private String rsvBatch2;//	rsv_batch2
    private String rsvBatch3;//	rsv_batch3
    private String rsvBatch4;//	rsv_batch4
    private String rsvBatch5;//	rsv_batch5
    private String rsvBatch6;//	rsv_batch6
    private String rsvBatch7;//	rsv_batch7
    private String rsvBatch8;//	rsv_batch8
    
    private Double qminOperatePacking;// 最小操作包装数
	private String packingUnitQmin;//中包装单位
	private String Unit;//基本包装单位
	private String packingSpec;//箱包装规格
	private String packingSpecQmin;//中包装规格
	private String spec;//基本包装规格
	private String unitPacking;
    
    private Integer rowid;
    
    private String SourceNo;//
    private String PrinterGroupNo;//
    private String DockNo;//
    private String Barcode;//
    private String DCellNo;//
    private String Qty;//
    private String UserID;//
    

    private String DispDCellNo;//目的储位显示用
    public String getDispDCellNo() {
		return DispDCellNo;
	}
	public void setDispDCellNo(String dispDCellNo) {
		DispDCellNo = dispDCellNo;
	}
	private String DispSCellNo;//来源储位显示用
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
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	//public Integer getArticleID() {
	//	return articleID;
	//}
	//public void setArticleID(Integer articleID) {
	//	this.articleID = articleID;
	//}	
	public String getSCellNo() {
		return SCellNo;
	}
	public void setSCellNo(String SCellNo) {
		this.SCellNo = SCellNo;
	}
	public String getSLabelNo() {
		return SLabelNo;
	}
	public void setSLabelNo(String SLabelNo) {
		this.SLabelNo = SLabelNo;
	}
	
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(String packingQty) {
		this.packingQty = packingQty;
	}
	public String getMoveQty() {
		return moveQty;
	}
	public void setMoveQty(String moveQty) {
		this.moveQty = moveQty;
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
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
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
	
	public Integer getrowid() {
		return rowid;
	}
	public void setrowid(Integer rowid) {
		this.rowid = rowid;
	}	
	
	public String getSourceNo() {
		return SourceNo;
	}
	public void setSourceNo(String SourceNo) {
		this.SourceNo = SourceNo;
	}
	public String getPrinterGroupNo() {
		return PrinterGroupNo;
	}
	public void setPrinterGroupNo(String PrinterGroupNo) {
		this.PrinterGroupNo = PrinterGroupNo;
	}
	public String getDockNo() {
		return DockNo;
	}
	public void setDockNo(String DockNo) {
		this.DockNo = DockNo;
	}
	public String getBarcode() {
		return Barcode;
	}
	public void setBarcode(String Barcode) {
		this.Barcode = Barcode;
	}
	public String getDCellNo() {
		return DCellNo;
	}
	public void setDCellNo(String DCellNo) {
		this.DCellNo = DCellNo;
	}
	public String getQty() {
		return Qty;
	}
	public void setQty(String Qty) {
		this.Qty = Qty;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String UserID) {
		this.UserID = UserID;
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
	public String getUnitPacking() {
		return unitPacking;
	}
	public void setUnitPacking(String unitPacking) {
		this.unitPacking = unitPacking;
	}
	public String getDispSCellNo() {
		return DispSCellNo;
	}
	public void setDispSCellNo(String dispSCellNo) {
		DispSCellNo = dispSCellNo;
	}
	public Double getQminOperatePacking() {
		return qminOperatePacking;
	}
	public void setQminOperatePacking(Double qminOperatePacking) {
		this.qminOperatePacking = qminOperatePacking;
	}
}
