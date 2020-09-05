package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;

public class IdataIMLogisticsCodeRequestModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String EnterpriseNo;
	private String WarehouseNo;
	private String OwnerNo;
	private String ArticleNo;//商品编号 hb create 20140721
	private String ScanBarcode;
	private String SourceNo;
	private String WorkerNo;
	private String LabelNo;
	private String Type;
	
	
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
	public String getArticleNo() {
		return ArticleNo;
	}
	public void setArticleNo(String articleNo) {
		ArticleNo = articleNo;
	}
	public String getScanBarcode() {
		return ScanBarcode;
	}
	public void setScanBarcode(String scanBarcode) {
		ScanBarcode = scanBarcode;
	}
	public String getSourceNo() {
		return SourceNo;
	}
	public void setSourceNo(String sourceNo) {
		SourceNo = sourceNo;
	}
	public String getWorkerNo() {
		return WorkerNo;
	}
	public void setWorkerNo(String workerNo) {
		WorkerNo = workerNo;
	}
	public String getLabelNo() {
		return LabelNo;
	}
	public void setLabelNo(String labelNo) {
		LabelNo = labelNo;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
}
