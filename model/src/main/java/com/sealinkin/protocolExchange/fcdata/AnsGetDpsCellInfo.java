package com.sealinkin.protocolExchange.fcdata;
import java.io.Serializable;
/**
 * stuGetDpsCellInfoAns
 * @author wyf
 *
 */
@SuppressWarnings("serial")
public class AnsGetDpsCellInfo implements Serializable{
	private String BarCode;
	private String ArticleName;
	private String ArticleNo;
	private String LabelNo;
	private String CellNo;
	private String Qty;
	private String custName;
	
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getBarCode() {
		return BarCode;
	}
	public void setBarCode(String barCode) {
		BarCode = barCode;
	}
	public String getArticleName() {
		return ArticleName;
	}
	public void setArticleName(String articleName) {
		ArticleName = articleName;
	}
	public String getArticleNo() {
		return ArticleNo;
	}
	public void setArticleNo(String articleNo) {
		ArticleNo = articleNo;
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
	public String getQty() {
		return Qty;
	}
	public void setQty(String qty) {
		Qty = qty;
	}
}
