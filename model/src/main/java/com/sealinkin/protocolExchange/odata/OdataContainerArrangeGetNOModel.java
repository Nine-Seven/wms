package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;

/**
 * 容器整理-容器整理取号
 * @author lich
 *
 */
public class OdataContainerArrangeGetNOModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;//企业编码
    private String warehouseNo;//仓别
    private String slabelNo;//源容器
    private String containerType;//P栈板 B物流箱 
    private String dockNo;//工作站
    
    private String dlabelNo;//取到的目的号
    
    public String getDlabelNo() {
		return dlabelNo;
	}
	public void setDlabelNo(String dlabelNo) {
		this.dlabelNo = dlabelNo;
	} 
	public String getContainerType() {
		return containerType;
	}
	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}
	public String getDockNo() {
		return dockNo;
	}
	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}
	public String getWorkerNO() {
		return workerNO;
	}
	public void setWorkerNO(String workerNO) {
		this.workerNO = workerNO;
	}
	private String workerNO;//操作人
    
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
}
