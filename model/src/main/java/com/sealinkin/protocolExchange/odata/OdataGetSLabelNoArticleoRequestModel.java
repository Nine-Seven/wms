package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;

/**
 * 容器整理-检查源容器请求 对应stuOMExistsSLabelNoRequest
 * @author lich
 *
 */
public class OdataGetSLabelNoArticleoRequestModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;//企业编码
    private String warehouseNo;//仓别
    private String slabelNo;//源容器
    private String barcode;//商品条码
    
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
	public String getSlabelNo() {
		return slabelNo;
	}
	public void setSlabelNo(String slabelNo) {
		this.slabelNo = slabelNo;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
}
