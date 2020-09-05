package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;
/**
 * 扫描标签号 对应客户端IMCheckLabelRequest
 * @author lich
 *
 */
public class IdataCheckLabelRequestMode implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
    private String simportNo;
    private String labelNo; //板号
    private String quality; //品质 huangb 20160714
        
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
	public String getSimportNo() {
		return simportNo;
	}
	public void setSimportNo(String simportNo) {
		this.simportNo = simportNo;
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
