package com.sealinkin.protocolExchange.ridata;

/**
 * 返配验收 扫描条码请求StuRIBarcodeRequest
 * @author lich
 *
 */
public class RISerialNoAnswerModel {
	private String enterpriseNo;
	private String warehouseNo;
    private String serialNo;
    private String ownerNo;
    private String suntreadNo;
    private String dateNow;
    private String quality;//品质 huangb 20160811
    private String qualityDesc;//品质描述 huangb 20160812
    private String isSelectQuality;//是否可以选择品质 是否选品质:0-不选(默认和返配单据头档一致);1-人工选择 huangb 20160811
    private String untreadType;//单据类型 huangb 20160815
    private String classType;//0：存储，1：直通 huangb 20160815
    private String deviceNo;//扫描墙编号，返配验收绑定了扫描墙后，需要回写此信息，并且一张返配汇总单只能对应一个扫描墙。 huangb 20160815
    
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
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getSuntreadNo() {
		return suntreadNo;
	}
	public void setSuntreadNo(String suntreadNo) {
		this.suntreadNo = suntreadNo;
	}
	public String getDateNow() {
		return dateNow;
	}
	public void setDateNow(String dateNow) {
		this.dateNow = dateNow;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getIsSelectQuality() {
		return isSelectQuality;
	}
	public void setIsSelectQuality(String isSelectQuality) {
		this.isSelectQuality = isSelectQuality;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getQualityDesc() {
		return qualityDesc;
	}
	public void setQualityDesc(String qualityDesc) {
		this.qualityDesc = qualityDesc;
	}
	public String getUntreadType() {
		return untreadType;
	}
	public void setUntreadType(String untreadType) {
		this.untreadType = untreadType;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
}
