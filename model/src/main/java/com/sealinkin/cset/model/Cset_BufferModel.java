package com.sealinkin.cset.model;

import com.sealinkin.cset.po.Cset_AreaBackupD;
import com.sealinkin.cset.po.Oset_Buffer;

/**
 * 
 * @author panzhenxing
 *
 */
public class Cset_BufferModel extends Oset_Buffer{
	

	private String enterpriseNo;
	private String warehouseNo;
	private String bufferName;
	private String wareNo;
	private String areaNo;
	private String stockNo;
	private String cellNo;
	private String statusText;
	
	public String getBufferName() {
		return bufferName;
	}
	public void setBufferName(String bufferName) {
		this.bufferName = bufferName;
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
	
	public String getWareNo() {
		return wareNo;
	}
	public void setWareNo(String wareNo) {
		this.wareNo = wareNo;
	}
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getStockNo() {
		return stockNo;
	}
	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	
	
}
