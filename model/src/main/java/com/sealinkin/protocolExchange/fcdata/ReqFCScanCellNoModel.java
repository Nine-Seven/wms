package com.sealinkin.protocolExchange.fcdata;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ReqFCScanCellNoModel implements Serializable{
	private String checkNo;
	private String checkType;
	private String cellNo;
	private String ownerNo;
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
}
