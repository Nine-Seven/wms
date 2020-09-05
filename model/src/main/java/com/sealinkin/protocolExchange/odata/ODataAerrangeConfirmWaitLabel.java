package com.sealinkin.protocolExchange.odata; 
/**
 * 获取待整理确认的标签及其状态信息
 * @author sunl
 *
 */
public class ODataAerrangeConfirmWaitLabel {
	private String LabelNo;   
	private String StatusValue;
	private String StatusText;
	private String OutStockNo;

	private String WorkerName;//任务领取人
	
	
	
	public String getWorkerName() {
		return WorkerName;
	}
	public void setWorkerName(String workerName) {
		WorkerName = workerName;
	}
	public String getOutStockNo() {
		return OutStockNo;
	}
	public void setOutStockNo(String outStockNo) {
		OutStockNo = outStockNo;
	}
	public String getStatusValue() {
		return StatusValue;
	}
	public void setStatusValue(String statusValue) {
		StatusValue = statusValue;
	}
	public String getStatusText() {
		return StatusText;
	}
	public void setStatusText(String statusText) {
		StatusText = statusText;
	}
	public String getLabelNo(){
   	 return LabelNo;
    }
    public void setLabelNo(String LabelNo){
   	 this.LabelNo=LabelNo;
    }
}
