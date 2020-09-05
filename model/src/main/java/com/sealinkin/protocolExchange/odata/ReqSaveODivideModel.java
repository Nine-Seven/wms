package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;
/**
 * 分播回单请求（StuReqSaveDivideSupp）
 * @author lich
 *
 */
public class ReqSaveODivideModel implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String EnterpriseNo;
	private String WareHouseNo;
     private String SourceNo;
     private String LabelNo;
     private String CellNo;
     private String CustNo;
     private String ArticleNo;
     private String Barcode;
     private Float PackingQty;
     private Double RealQty;
     private String UserId;
     private String PaperUserId;
     private Integer OperateTools;
     private Integer AddupFlag; //累加标记 0：非累加 1：累加
     
     public String getEnterpriseNo()
     {
    	 return EnterpriseNo;
     }
     public void setEnterpriseNo(String EnterpriseNo)
     {
    	 this.EnterpriseNo=EnterpriseNo;
     }
	public String getWareHouseNo() {
		return WareHouseNo;
	}
	public void setWareHouseNo(String wareHouseNo) {
		WareHouseNo = wareHouseNo;
	}
	public String getSourceNo() {
		return SourceNo;
	}
	public void setSourceNo(String sourceNo) {
		SourceNo = sourceNo;
	}
	public String getLabelNo() {
		return LabelNo;
	}
	public void setLabelNo(String labelNo) {
		LabelNo = labelNo;
	}
	public String getCellNo() {
		return CellNo;
	}
	public void setCellNo(String cellNo) {
		CellNo = cellNo;
	}
	public String getCustNo() {
		return CustNo;
	}
	public void setCustNo(String custNo) {
		CustNo = custNo;
	}
	public String getArticleNo() {
		return ArticleNo;
	}
	public void setArticleNo(String articleNo) {
		ArticleNo = articleNo;
	}
	public String getBarcode() {
		return Barcode;
	}
	public void setBarcode(String barcode) {
		Barcode = barcode;
	}
	public Float getPackingQty() {
		return PackingQty;
	}
	public void setPackingQty(Float packingQty) {
		PackingQty = packingQty;
	}
	public Double getRealQty() {
		return RealQty;
	}
	public void setRealQty(Double realQty) {
		RealQty = realQty;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getPaperUserId() {
		return PaperUserId;
	}
	public void setPaperUserId(String paperUserId) {
		PaperUserId = paperUserId;
	}
	public Integer getOperateTools() {
		return OperateTools;
	}
	public void setOperateTools(Integer operateTools) {
		OperateTools = operateTools;
	}
	public Integer getAddupFlag() {
		return AddupFlag;
	}
	public void setAddupFlag(Integer addupFlag) {
		AddupFlag = addupFlag;
	}     
}
