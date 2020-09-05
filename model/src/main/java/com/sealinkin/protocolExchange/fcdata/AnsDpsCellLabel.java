package com.sealinkin.protocolExchange.fcdata;
import java.io.Serializable;
/**
 * stuDpsCellLabelAns
 * @author wyf
 *
 */
@SuppressWarnings("serial")
public class AnsDpsCellLabel implements Serializable{
	private String Barcode;
	private String ArticleNo;
	private String Articlename;
	private String ContainerNo;
	public String getBarcode() {
		return Barcode;
	}
	public void setBarcode(String barcode) {
		Barcode = barcode;
	}
	public String getArticleNo() {
		return ArticleNo;
	}
	public void setArticleNo(String articleNo) {
		ArticleNo = articleNo;
	}
	public String getArticlename() {
		return Articlename;
	}
	public void setArticlename(String articlename) {
		Articlename = articlename;
	}
	public String getContainerNo() {
		return ContainerNo;
	}
	public void setContainerNo(String containerNo) {
		ContainerNo = containerNo;
	}
}
