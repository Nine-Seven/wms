package com.sealinkin.cdef.model;

import com.sealinkin.cdef.po.Cdef_Defcell;

public class Cdef_DefcellModel extends Cdef_Defcell{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String warehouseNo;
	private String cellNo;
	private String areaUsetype;
	private String areaAttribute;
	private String wareName;//库区编码+名称的组合
	private String areaName;
	
	
	
	public String getWareName() {
		return wareName;
	}
	public void setWareName(String wareName) {
		this.wareName = wareName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getAreaUsetype() {
		return areaUsetype;
	}
	public void setAreaUsetype(String areaUsetype) {
		this.areaUsetype = areaUsetype;
	}
	public String getAreaAttribute() {
		return areaAttribute;
	}
	public void setAreaAttribute(String areaAttribute) {
		this.areaAttribute = areaAttribute;
	}
}
