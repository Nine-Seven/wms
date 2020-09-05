package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;

/**
 * 容器整理-整箱转移 对应stuArrangeBoxsRequest
 * @author lich
 *
 */
public class ArrangeBoxsRequestModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;//企业编码
	private String warehouseNo;
    private String slabelNo;//源容器
    private String dlabelNo;//目的容器
    private String userId;//操作人员
    private String terminalFlag;//操作设备
    
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTerminalFlag() {
		return terminalFlag;
	}
	public void setTerminalFlag(String terminalFlag) {
		this.terminalFlag = terminalFlag;
	}
}
