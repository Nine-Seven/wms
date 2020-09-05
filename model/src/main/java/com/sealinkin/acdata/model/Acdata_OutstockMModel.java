package com.sealinkin.acdata.model;

import com.sealinkin.acdata.po.Acdata_OutstockM;

public class Acdata_OutstockMModel extends Acdata_OutstockM{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5023855459170267035L;
	private String warehouseNo;
	private String outstockNo;
	private String statusText;

	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
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
