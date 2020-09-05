package com.sealinkin.acdata.model;

import com.sealinkin.acdata.po.Acdata_OutstockD;

public class Acdata_OutstockDModel extends Acdata_OutstockD{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5023855459170267035L;
	private String warehouseNo;
	private String outstockNo;
	private String articleName;
	
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getOutstockNo() {
		return outstockNo;
	}
	public void setOutstockNo(String outstockNo) {
		this.outstockNo = outstockNo;
	}
	
}
