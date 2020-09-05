package com.sealinkin.protocolExchange.bdef;
import java.io.Serializable;
import java.util.List;

import com.sealinkin.protocolExchange.fcdata.AnsPackingModel;
import com.sealinkin.protocolExchange.fcdata.ArticleInfoModel;
/**
 * 堆叠采集》新增商品信息资料
 * @author Administrator
 *
 */
public class AnsPickingCellInPutBarcodeModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String WarehouseNo;
    private String ownerNo;//委托业主编码
	private String articleNo;//商品编码
    private String articleName;//商品名称
    private String barcode;//商品条码
    private String suppliernotecode;//供应商助记码
    private String  ArtcicleNum; //是否一码对应多品项（1：没有对应任何品项，2：有且只对应一个品项，3：对应多个品项）
    
    private List<AnsPackingModel> listPacking;
    private List<ArticleInfoModel> listArticlinfo;
    public String getArtcicleNum() {
		return ArtcicleNum;
	}
	public void setArtcicleNum(String artcicleNum) {
		ArtcicleNum = artcicleNum;
	}
	public List<AnsPackingModel> getListPacking() {
		return listPacking;
	}
	public void setListPacking(List<AnsPackingModel> listPacking) {
		this.listPacking = listPacking;
	}
	public List<ArticleInfoModel> getListArticlinfo() {
		return listArticlinfo;
	}
	public void setListArticlinfo(List<ArticleInfoModel> listArticlinfo) {
		this.listArticlinfo = listArticlinfo;
	}
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
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getSuppliernotecode() {
		return suppliernotecode;
	}
	public void setSuppliernotecode(String suppliernotecode) {
		this.suppliernotecode = suppliernotecode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
      

}
