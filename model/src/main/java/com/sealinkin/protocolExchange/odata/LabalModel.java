package com.sealinkin.protocolExchange.odata;

public class LabalModel {
	private String labelNo;//标签号
	private String LabelQty;//已扫标签数
	private String Qty;//未扫标签数
	public String getLabelQty() {
		return LabelQty;
	}

	public void setLabelQty(String labelQty) {
		LabelQty = labelQty;
	}

	public String getQty() {
		return Qty;
	}

	public void setQty(String qty) {
		Qty = qty;
	}

	public String getLabelNo() {
		return labelNo;
	}

	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}

	

}
