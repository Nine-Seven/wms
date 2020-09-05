package com.sealinkin.protocolExchange.odata; 
/**
 * 获取待整理确认的信息
 * @author sunl
 *
 */
public class ODataWaitAerrangeInfo {
	private String CustNo;
	private String CustName;
	private String DeliverArea;
	private String WaveNo;
	private String NnLabelNum;
	public String getCustNo() {
		return CustNo;
	}
	public void setCustNo(String custNo) {
		CustNo = custNo;
	}
	public String getCustName() {
		return CustName;
	}
	public void setCustName(String custName) {
		CustName = custName;
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
	public String getNnLabelNum() {
		return NnLabelNum;
	}
	public void setNnLabelNum(String nnLabelNum) {
		NnLabelNum = nnLabelNum;
	}


}
