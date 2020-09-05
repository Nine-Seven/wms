package com.sealinkin.bdef.model;

import com.sealinkin.bdef.po.Bdef_WmsWarehouseBase;

@SuppressWarnings("serial")
public class Bdef_WarehouseBaseModel extends Bdef_WmsWarehouseBase{
	private String warehouseNo;
	private String colname;
	private String groupNoText;
	private String subGroupNoText;
	private String sdefineText;
	private String wareHouseText;
	
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getColname() {
		return colname;
	}
	public void setColname(String colname) {
		this.colname = colname;
	}
	public String getGroupNoText() {
		return groupNoText;
	}
	public void setGroupNoText(String groupNoText) {
		this.groupNoText = groupNoText;
	}
	public String getSubGroupNoText() {
		return subGroupNoText;
	}
	public void setSubGroupNoText(String subGroupNoText) {
		this.subGroupNoText = subGroupNoText;
	}
	public String getSdefineText() {
		return sdefineText;
	}
	public void setSdefineText(String sdefineText) {
		this.sdefineText = sdefineText;
	}
	public String getWareHouseText() {
		return wareHouseText;
	}
	public void setWareHouseText(String wareHouseText) {
		this.wareHouseText = wareHouseText;
	}
	
}
