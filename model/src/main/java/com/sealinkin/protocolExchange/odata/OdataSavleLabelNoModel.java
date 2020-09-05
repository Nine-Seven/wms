package com.sealinkin.protocolExchange.odata;

public class OdataSavleLabelNoModel {

    private String strSessionID;  
    private String EnterpriseNo;
    private String WareHouseNo;
    private String SourceNo;
    private String LabelNo;
    private String CellNo;
    private String CustNo;
    private String ArticleNo;
    private String Barcode;
    private float PackingQty;
    private double RealQty;
    private String UserId;
    private String PaperUserId;
    private int OperateTools;
    private int AddupFlag; //累加标记 0：非累加 1：累加
    
	public String getStrSessionID() {
		return strSessionID;
	}
	public void setStrSessionID(String strSessionID) {
		this.strSessionID = strSessionID;
	}
	public String getEnterpriseNo() {
		return EnterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		EnterpriseNo = enterpriseNo;
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
	public float getPackingQty() {
		return PackingQty;
	}
	public void setPackingQty(float packingQty) {
		PackingQty = packingQty;
	}
	public double getRealQty() {
		return RealQty;
	}
	public void setRealQty(double realQty) {
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
	public int getOperateTools() {
		return OperateTools;
	}
	public void setOperateTools(int operateTools) {
		OperateTools = operateTools;
	}
	public int getAddupFlag() {
		return AddupFlag;
	}
	public void setAddupFlag(int addupFlag) {
		AddupFlag = addupFlag;
	}
}
