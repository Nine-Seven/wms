package com.sealinkin.cdef.model;

import com.sealinkin.cdef.po.Cdef_Defware;

public class Cdef_DefwareModel extends Cdef_Defware{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String warehouseNo;
	private String wareNo;
	
	
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
	public String getWareNo() {
		return wareNo;
	}
	public void setWareNo(String wareNo) {
		this.wareNo = wareNo;
	}
	
	

}
