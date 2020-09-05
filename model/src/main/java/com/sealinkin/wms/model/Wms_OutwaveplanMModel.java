package com.sealinkin.wms.model;

import com.sealinkin.wms.po.Wms_OutwaveplanM;

public class Wms_OutwaveplanMModel extends Wms_OutwaveplanM {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private Integer batchStrategyId;
	private String rgstDateText;
	
	
	
	
	public String getRgstDateText() {
		return rgstDateText;
	}
	public void setRgstDateText(String rgstDateText) {
		this.rgstDateText = rgstDateText;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public Integer getBatchStrategyId() {
		return batchStrategyId;
	}
	public void setBatchStrategyId(Integer batchStrategyId) {
		this.batchStrategyId = batchStrategyId;
	}
	
	
	
}
