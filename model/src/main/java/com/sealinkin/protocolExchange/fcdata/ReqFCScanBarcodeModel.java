package com.sealinkin.protocolExchange.fcdata;

import java.io.Serializable;
import java.util.Date;
/**
 * 盘点参数结构CHParameterStu
 * @author lich
 *
 */
@SuppressWarnings("serial")
public class ReqFCScanBarcodeModel implements Serializable{
	 private String enterpriseNo;//企业
	 private String warehouseNo;//仓别
     private String serialNo;//流水号
     private String checkNo;//盘点单号
     private String ownerNo;//委托业主
     private String checkType;//盘点次数
     private String cellNo; //储位号
     private String LabelNo;//标签号
     private String SubLabelNo;//子标签号
     
     private Double firstQty;//初盘数量
     private Double secondQty;//复盘数量
     private Double thirdQty;//三盘数量
     private Double stockQty;//计划数量
     private Double MPackQty;//包装数量 
     private String addFlag;//新增状态(1:新增)
     private String checkFlag;//储位状态(是否盘点：0未盘，1已盘)
     private String userID;//盘点人员

     //包装信息
     private String unit;
     private Double packQty;
     private Double palBaseBox;
     private Double palHeightBox;
     //商品信息
     private String articleNo;
     private String barcode;
     private String articleName;
     private String spec;//规格
     private String itemType;
     //医药行业属性
     private String lotNo;
     private Date produceDate;
     private Date expireDate;
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

     private Double expiryDays;//有效期
     
     private String flag;	//盘点回单是否强制回单 0：否，1：是

     private String dispCellNo;	//盘点回单是否强制回单 0：否，1：是
     private String qminOperatePacking;//最小操作包装
     private String qminOpeartePackingUnit;//最小操作包装单位
     
	public String getDispCellNo() {
		return dispCellNo;
	}

	public void setDispCellNo(String dispCellNo) {
		this.dispCellNo = dispCellNo;
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

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getCellNo() {
		return cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	public Double getFirstQty() {
		return firstQty;
	}

	public void setFirstQty(Double firstQty) {
		this.firstQty = firstQty;
	}

	public Double getSecondQty() {
		return secondQty;
	}

	public void setSecondQty(Double secondQty) {
		this.secondQty = secondQty;
	}

	public Double getThirdQty() {
		return thirdQty;
	}

	public void setThirdQty(Double thirdQty) {
		this.thirdQty = thirdQty;
	}

	public Double getStockQty() {
		return stockQty;
	}

	public void setStockQty(Double stockQty) {
		this.stockQty = stockQty;
	}

	public Double getMPackQty() {
		return MPackQty;
	}

	public void setMPackQty(Double mPackQty) {
		MPackQty = mPackQty;
	}

	public String getAddFlag() {
		return addFlag;
	}

	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getPackQty() {
		return packQty;
	}

	public void setPackQty(Double packQty) {
		this.packQty = packQty;
	}

	public Double getPalBaseBox() {
		return palBaseBox;
	}

	public void setPalBaseBox(Double palBaseBox) {
		this.palBaseBox = palBaseBox;
	}

	public Double getPalHeightBox() {
		return palHeightBox;
	}

	public void setPalHeightBox(Double palHeightBox) {
		this.palHeightBox = palHeightBox;
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

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
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

	public void setExpiryDays(Double expiryDays) {
		this.expiryDays = expiryDays;
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

	public Double getExpiryDays() {
		return expiryDays;
	}

	public String getLabelNo() {
		return LabelNo;
	}

	public void setLabelNo(String labelNo) {
		LabelNo = labelNo;
	}

	public String getSubLabelNo() {
		return SubLabelNo;
	}

	public void setSubLabelNo(String subLabelNo) {
		SubLabelNo = subLabelNo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getQminOperatePacking() {
		return qminOperatePacking;
	}

	public void setQminOperatePacking(String qminOperatePacking) {
		this.qminOperatePacking = qminOperatePacking;
	}

	public String getQminOpeartePackingUnit() {
		return qminOpeartePackingUnit;
	}

	public void setQminOpeartePackingUnit(String qminOpeartePackingUnit) {
		this.qminOpeartePackingUnit = qminOpeartePackingUnit;
	}
	
}
