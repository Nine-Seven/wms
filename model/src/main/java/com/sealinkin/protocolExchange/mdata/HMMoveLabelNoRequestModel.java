package com.sealinkin.protocolExchange.mdata;

/**
 * 移库扫描标签请求 StuHMMoveLabelNoRequest
 * @author lich
 *
 */
public class HMMoveLabelNoRequestModel {
	private String  enterpriseNo;
	 private String  wareHouseNo;
     private String  labelNo;
     private String  status;
     
     
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getWareHouseNo() {
		return wareHouseNo;
	}
	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}         
}
