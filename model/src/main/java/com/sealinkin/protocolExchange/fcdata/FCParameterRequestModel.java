package com.sealinkin.protocolExchange.fcdata;

import java.io.Serializable;

/**
 * 盘点参数结构
 * @author 周欢
 */
@SuppressWarnings("serial")
public class FCParameterRequestModel implements Serializable{
	private String enterpriseNo;//企业
	private String WarehouseNo;//仓别
	private String serialNo;//流水号
	private String checkNo;//盘点单号
	private String ownerNo;//委托业主
	private String checkType;//盘点次数
	private String cellNo; //储位号
	private String LabelNo;//标签号

	private Integer firstQty;//初盘数量
	private Integer secondQty;//复盘数量
	private Integer thirdQty;//三盘数量
	private Integer stockQty;//计划数量
	private Integer mPackQty;//包装数量 
    private String addFlag;//新增状态(1:新增)
    private String checkFlag;//储位状态(是否盘点：0未盘，1已盘)
    private String userID;//盘点人员

    private String unit;
    private Double packQty;
    private Double palBaseBox;
    private Double palHeightBox;
    private String articleNo;
    private String articleID;
    private String barcode;
    private String articleName;
    private String style;
    private String fancy;
    private String cup;
    private String colorCode;
    private String sizeCode;
    private String spec;//规格
    private String itemType;
    //医药行业属性
    private String lotNo;
    private String produceDate;
    private String expireDate;
    private String quality;
    private String factory;
        
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getWarehouseNo() {
		return WarehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		WarehouseNo = warehouseNo;
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
	public String getLabelNo() {
		return LabelNo;
	}
	public void setLabelNo(String LabelNo) {
		this.LabelNo = LabelNo;
	}
	public Integer getFirstQty() {
		return firstQty;
	}
	public void setFirstQty(Integer firstQty) {
		this.firstQty = firstQty;
	}
	public Integer getSecondQty() {
		return secondQty;
	}
	public void setSecondQty(Integer secondQty) {
		this.secondQty = secondQty;
	}
	public Integer getThirdQty() {
		return thirdQty;
	}
	public void setThirdQty(Integer thirdQty) {
		this.thirdQty = thirdQty;
	}
	public Integer getStockQty() {
		return stockQty;
	}
	public void setStockQty(Integer stockQty) {
		this.stockQty = stockQty;
	}
	public Integer getmPackQty() {
		return mPackQty;
	}
	public void setmPackQty(Integer mPackQty) {
		this.mPackQty = mPackQty;
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
	public String getArticleID() {
		return articleID;
	}
	public void setArticleID(String articleID) {
		this.articleID = articleID;
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
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getFancy() {
		return fancy;
	}
	public void setFancy(String fancy) {
		this.fancy = fancy;
	}
	public String getCup() {
		return cup;
	}
	public void setCup(String cup) {
		this.cup = cup;
	}
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public String getSizeCode() {
		return sizeCode;
	}
	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
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
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
}
