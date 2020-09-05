package com.sealinkin.protocolExchange.fcdata;

import java.io.Serializable;
/**
 * 库存查询应答 StuStockContentInfoAns
 * @author lich
 */
@SuppressWarnings("serial")
public class AnsStockContentInfoModel implements Serializable{
	
	private String articleNo;
	private String ownerArticleNo;
    private String barcode;
    private String articleName;
    private String spec;//规格        
    private String packunit;//包装单位
    public Double packQty;//包装
    private String qpalette;//堆叠
    
    private String produceDate;//生产日期
    private String expireDate;//有效日期
    private String quality;//品质

    private Double qty;//库存数量
    private Double outqty;//预下数量
    private Double inqty;//预上数量
    private Double canUseQty;//可用数量
    private String cellNo;   //库存所在储位
    private String pickCellNo;   //拣货位
    private String supplierNo;	//供应商编码 
    private String LabelNo;//标签号
    

	private String ownerNo;
    
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getOwnerArticleNo() {
		return ownerArticleNo;
	}
	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
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
	public String getPackunit() {
		return packunit;
	}
	public void setPackunit(String packunit) {
		this.packunit = packunit;
	}
	public Double getPackQty() {
		return packQty;
	}
	public void setPackQty(Double packQty) {
		this.packQty = packQty;
	}
	public String getQpalette() {
		return qpalette;
	}
	public void setQpalette(String qpalette) {
		this.qpalette = qpalette;
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
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public Double getOutqty() {
		return outqty;
	}
	public void setOutqty(Double outqty) {
		this.outqty = outqty;
	}
	public Double getInqty() {
		return inqty;
	}
	public void setInqty(Double inqty) {
		this.inqty = inqty;
	}
	public Double getCanUseQty() {
		return canUseQty;
	}
	public void setCanUseQty(Double canUseQty) {
		this.canUseQty = canUseQty;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getPickCellNo() {
		return pickCellNo;
	}
	public void setPickCellNo(String pickCellNo) {
		this.pickCellNo = pickCellNo;
	}
	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	public String getLabelNo() {
		return LabelNo;
	}
	public void setLabelNo(String labelNo) {
		LabelNo = labelNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	
}
