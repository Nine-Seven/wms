package com.sealinkin.wms.model;

import com.sealinkin.wms.po.Wms_Outorderandflow;

public class Wms_OutorderandflowModel extends Wms_Outorderandflow{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private short flowValue;
	private String flowText;
	public short getFlowValue() {
		return flowValue;
	}
	public void setFlowValue(short flowValue) {
		this.flowValue = flowValue;
	}
	public String getFlowText() {
		return flowText;
	}
	public void setFlowText(String flowText) {
		this.flowText = flowText;
	}
	
	
}
