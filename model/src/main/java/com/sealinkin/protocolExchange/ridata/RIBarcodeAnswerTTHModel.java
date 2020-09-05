package com.sealinkin.protocolExchange.ridata;

import java.io.Serializable;

public class RIBarcodeAnswerTTHModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String articleNo;
	private String articleName;
	private String packingUnit; 
	private String unCheckBox;
	private String uncheckPsc;
	private String checkBox;
	private String checkPsc;
	private String cellNo;
	
	private String unCheckSumCQty;
	private String unCheckSumSQty;
	private String custName;
	
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
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

	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public String getUnCheckBox() {
		return unCheckBox;
	}
	public void setUnCheckBox(String unCheckBox) {
		this.unCheckBox = unCheckBox;
	}
	public String getUncheckPsc() {
		return uncheckPsc;
	}
	public void setUncheckPsc(String uncheckPsc) {
		this.uncheckPsc = uncheckPsc;
	}
	public String getCheckBox() {
		return checkBox;
	}
	public void setCheckBox(String checkBox) {
		this.checkBox = checkBox;
	}
	public String getCheckPsc() {
		return checkPsc;
	}
	public void setCheckPsc(String checkPsc) {
		this.checkPsc = checkPsc;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getUnCheckSumCQty() {
		return unCheckSumCQty;
	}
	public void setUnCheckSumCQty(String unCheckSumCQty) {
		this.unCheckSumCQty = unCheckSumCQty;
	}
	public String getUnCheckSumSQty() {
		return unCheckSumSQty;
	}
	public void setUnCheckSumSQty(String unCheckSumSQty) {
		this.unCheckSumSQty = unCheckSumSQty;
	}
	
}
