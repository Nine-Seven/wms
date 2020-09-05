package com.sealinkin.protocolExchange.idata;
/**
 * 封板事件 对应StuIMCloseLablRequest
 * @author lich
 *
 */
public class IdataCloseLablRequestModel {
	private String EnterpriseNo;
	private String WarehouseNo;
    private String OwnerNo; //委托业主编码
    private String simportNo; //进货汇总单号
    private String scheckNo; //验收汇总单号
    private String LabelNo; //板号
    private String WorkerNo; //操作人
    private String FixPalFlag; //1：固定板号；2：流水板号;3：固定流水混合验收
    private String dockNo; //
    
	public String getEnterpriseNo() {
		return EnterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		EnterpriseNo = enterpriseNo;
	}
	public String getWarehouseNo() {
		return WarehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		WarehouseNo = warehouseNo;
	}
	public String getOwnerNo() {
		return OwnerNo;
	}
	public void setOwnerNo(String ownerNo) {
		OwnerNo = ownerNo;
	}
	public String getSimportNo() {
		return simportNo;
	}
	public void setSimportNo(String simportNo) {
		this.simportNo = simportNo;
	}
	
	public String getScheckNo() {
		return scheckNo;
	}
	public void setScheckNo(String scheckNo) {
		this.scheckNo = scheckNo;
	}
	public String getLabelNo() {
		return LabelNo;
	}
	public void setLabelNo(String labelNo) {
		LabelNo = labelNo;
	}
	public String getWorkerNo() {
		return WorkerNo;
	}
	public void setWorkerNo(String workerNo) {
		WorkerNo = workerNo;
	}
	public String getFixPalFlag() {
		return FixPalFlag;
	}
	public void setFixPalFlag(String fixPalFlag) {
		FixPalFlag = fixPalFlag;
	}
	public String getDockNo() {
		return dockNo;
	}
	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}
	
}
