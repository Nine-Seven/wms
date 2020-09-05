package com.sealinkin.protocolExchange.fcdata;

import java.io.Serializable;

/**
 * 库存查询 StuStockContentQueryReq
 * @author lich
 */
@SuppressWarnings("serial")
public class ReqStockContentQueryModel implements Serializable{
	private String enterpriseNo;//企业
	private String warehouseNo;//仓别
	private String barcode;//条码
	private String cellNo;//储位
	private Integer flag;//1:按条码；2：按储位
	
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
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}
