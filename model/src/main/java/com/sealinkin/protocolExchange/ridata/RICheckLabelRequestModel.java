package com.sealinkin.protocolExchange.ridata;

/**
 * 返配验收 扫描板号请求StuRICheckLabelRequest
 * @author lich
 *
 */
public class RICheckLabelRequestModel {
	private String warehouseNo;
    private String serialNo;
    private String labelNo;
    private String quality;
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
}
