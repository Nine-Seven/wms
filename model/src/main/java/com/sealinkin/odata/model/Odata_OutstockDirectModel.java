package com.sealinkin.odata.model;

import java.util.Date;

import com.sealinkin.odata.po.Odata_OutstockDirect;

public class Odata_OutstockDirectModel extends Odata_OutstockDirect{
	private static final long serialVersionUID = 1L;
	private String articleName;
	private String areaNo;
	private String areaName;
	private String warehouseNo;
	private String subCustName;
	private Long directSerial;
	private Date operateDate;
	private String ownerName;
	private String custName;
	private String barcode;
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
	private String outstockTypeText;
	private String expTypeText;
	private String sourceTypeText;
	private String wareNo;
	private String wareaNo;
	private String printPaperType;//是否打印表单标识，0：不打印，1，打印  
	private String ownerArticleNo;
	
	private Double qminOperatePacking;//最小操作包装数量
	private Double unitPacking;//基本包装数量
	private String packingUnit;//箱包装单位
	private String packingUnitQmin;//中包装单位
	private String Unit;//基本包装单位
	private String packingSpec;//箱包装规格
	private String packingSpecQmin;//中包装规格
	private String spec;//基本包装规格
	
	private String flag;//发单用1：不可勾选，0可勾选
	private String printFaceSheet;
	private String printBuilt;
	private String printInvoice;
	private String industryFlag;//行业标识；1：传统；2：电商
	private String isSengflag;//是否可发单标识;0-不可发单;1-可发单 huangb 20160805
		
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getSubCustName() {
		return subCustName;
	}
	public void setSubCustName(String subCustName) {
		this.subCustName = subCustName;
	}
	public Long getDirectSerial() {
		return directSerial;
	}
	public void setDirectSerial(Long directSerial) {
		this.directSerial = directSerial;
	}
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
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
	public String getOutstockTypeText() {
		return outstockTypeText;
	}
	public void setOutstockTypeText(String outstockTypeText) {
		this.outstockTypeText = outstockTypeText;
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
	public String getExpTypeText() {
		return expTypeText;
	}
	public void setExpTypeText(String expTypeText) {
		this.expTypeText = expTypeText;
	}
	public String getSourceTypeText() {
		return sourceTypeText;
	}
	public void setSourceTypeText(String sourceTypeText) {
		this.sourceTypeText = sourceTypeText;
	}
	public String getWareNo() {
		return wareNo;
	}
	public void setWareNo(String wareNo) {
		this.wareNo = wareNo;
	}
	public String getWareaNo() {
		return wareaNo;
	}
	public void setWareaNo(String wareaNo) {
		this.wareaNo = wareaNo;
	}
	public String getPrintPaperType() {
		return printPaperType;
	}
	public void setPrintPaperType(String printPaperType) {
		this.printPaperType = printPaperType;
	}
	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}
	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getPrintFaceSheet() {
		return printFaceSheet;
	}
	public void setPrintFaceSheet(String printFaceSheet) {
		this.printFaceSheet = printFaceSheet;
	}
	public String getPrintBuilt() {
		return printBuilt;
	}
	public void setPrintBuilt(String printBuilt) {
		this.printBuilt = printBuilt;
	}
	public String getPrintInvoice() {
		return printInvoice;
	}
	public void setPrintInvoice(String printInvoice) {
		this.printInvoice = printInvoice;
	}
	public String getIndustryFlag() {
		return industryFlag;
	}
	public void setIndustryFlag(String industryFlag) {
		this.industryFlag = industryFlag;
	}
	public String getIsSengflag() {
		return isSengflag;
	}
	public void setIsSengflag(String isSengflag) {
		this.isSengflag = isSengflag;
	}
}
