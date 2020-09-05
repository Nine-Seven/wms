package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;


/**
 * 容器整理-检查目的容器请求 对应stuOMExistsDLabelNoRequest
 * @author lich
 *
 */
public class OdataExistsDLabelNoRequestModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;//企业编码
    private String warehouseNo;//仓别
    private String slabelNo;//源容器
    private String dlabelNo;//目的容器
    
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
	public String getDlabelNo() {
		return dlabelNo;
	}
	public void setDlabelNo(String dlabelNo) {
		this.dlabelNo = dlabelNo;
	}
}
