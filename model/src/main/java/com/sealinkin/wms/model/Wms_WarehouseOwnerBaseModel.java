package com.sealinkin.wms.model;

import com.sealinkin.wms.po.Wms_WarehouseOwnerBase;

public class Wms_WarehouseOwnerBaseModel extends Wms_WarehouseOwnerBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String colname;
	private String groupNoText;
	private String subGroupNoText;
	private String sdefineText ;
	private String ownerNameText;
	private String wareHouseNameText;
	
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
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
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getOwnerNameText() {
		return ownerNameText;
	}
	public void setOwnerNameText(String ownerNameText) {
		this.ownerNameText = ownerNameText;
	}
	public String getWareHouseNameText() {
		return wareHouseNameText;
	}
	public void setWareHouseNameText(String wareHouseNameText) {
		this.wareHouseNameText = wareHouseNameText;
	}
	
}
