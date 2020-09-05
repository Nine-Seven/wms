package com.sealinkin.protocolExchange.fcdata;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AnsFCScanSerialModel implements Serializable{
	private String checkNo;
	private String cellNo;
	private String ownerNo;
	private String dispCellNo;
	
	
	
	public String getDispCellNo() {
		return dispCellNo;
	}
	public void setDispCellNo(String dispCellNo) {
		this.dispCellNo = dispCellNo;
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
