package com.sealinkin.protocolExchange.odata; 
/**
 * 自动装并板+容器整理界面使用
 * @author sunl
 *
 */
public class ODataMergePalAndArrangeConfirm {
	private String DeliverObj;   
	private String DeliverArea;
	private String WaveNo;
	private String CustName;
	private String NnLabelNum;

	private String EnterpriseNo;//企业编码
    private String WarehouseNo;//仓别
    private String WorkerNO;//操作人
    private String LabelNo; 
	
	
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
	public String getWorkerNO() {
		return WorkerNO;
	}
	public void setWorkerNO(String workerNO) {
		WorkerNO = workerNO;
	}
	public String getLabelNo() {
		return LabelNo;
	}
	public void setLabelNo(String labelNo) {
		LabelNo = labelNo;
	}
	public String getDeliverObj() {
		return DeliverObj;
	}
	public void setDeliverObj(String deliverObj) {
		DeliverObj = deliverObj;
	}
	public String getDeliverArea() {
		return DeliverArea;
	}
	public void setDeliverArea(String deliverArea) {
		DeliverArea = deliverArea;
	}
	public String getWaveNo() {
		return WaveNo;
	}
	public void setWaveNo(String waveNo) {
		WaveNo = waveNo;
	}
	public String getCustName() {
		return CustName;
	}
	public void setCustName(String custName) {
		CustName = custName;
	}
	public String getNnLabelNum() {
		return NnLabelNum;
	}
	public void setNnLabelNum(String nnLabelNum) {
		NnLabelNum = nnLabelNum;
	}
	
	
}
