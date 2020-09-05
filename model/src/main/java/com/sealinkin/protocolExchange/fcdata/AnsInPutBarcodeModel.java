package com.sealinkin.protocolExchange.fcdata;

import java.io.Serializable;
import java.util.List;
/**
 * 扫描商品条码 对应客户端StuInPutBarcodeAns
 * @author lich
 *
 */
@SuppressWarnings("serial")
public class AnsInPutBarcodeModel implements Serializable{
   private String  EnterpriseNo;//企业
   private String  WarehouseNo;//仓别
   private String  SerialNo;//流水号
   private String  CheckNo;//盘点单号
   private String  OwnerNo;//委托业主
   private String  CheckType;//盘点次数
   private String  CellNo; //储位号
   private String  CheckQty;//已盘点数量
   private String  ArtcicleNum; //是否一码对应多品项（1：没有对应任何品项，2：有且只对应一个品项，3：对应多个品项）
   
   private List<AnsPackingModel> listPacking;
   private List<ArticleInfoModel> listArticlinfo;
      
   
	public String getEnterpriseNo() {
		return EnterpriseNo;
    }
	public void setEnterpriseNo(String enterpriseNo) {
		EnterpriseNo = enterpriseNo;
	}
	public String getWarehouseNo() {
		return WarehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		WarehouseNo = warehouseNo;
	}
	public String getSerialNo() {
		return SerialNo;
	}
	public void setSerialNo(String serialNo) {
		SerialNo = serialNo;
	}
	public String getCheckNo() {
		return CheckNo;
	}
	public void setCheckNo(String checkNo) {
		CheckNo = checkNo;
	}
	public String getOwnerNo() {
		return OwnerNo;
	}
	public void setOwnerNo(String ownerNo) {
		OwnerNo = ownerNo;
	}
	public String getCheckType() {
		return CheckType;
	}
	public void setCheckType(String checkType) {
		CheckType = checkType;
	}
	public String getCellNo() {
		return CellNo;
	}
	public void setCellNo(String cellNo) {
		CellNo = cellNo;
	}
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
	public String getCheckQty() {
		return CheckQty;
	}
	public void setCheckQty(String checkQty) {
		CheckQty = checkQty;
	}
}
