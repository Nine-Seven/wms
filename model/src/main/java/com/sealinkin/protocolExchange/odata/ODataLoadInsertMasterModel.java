package com.sealinkin.protocolExchange.odata;

/** 承运商装车新增建议单头档返回model
 * huangb 20160715
 */
public class ODataLoadInsertMasterModel {
	private String loadProposeNo;//装车建议单号
	private String shipperName;//承运商名称
	private String scanLabelCount;//已扫描数量
	
	public String getLoadProposeNo() {
		return loadProposeNo;
	}
	public void setLoadProposeNo(String loadProposeNo) {
		this.loadProposeNo = loadProposeNo;
	}
	public String getShipperName() {
		return shipperName;
	}
	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}
	public String getScanLabelCount() {
		return scanLabelCount;
	}
	public void setScanLabelCount(String scanLabelCount) {
		this.scanLabelCount = scanLabelCount;
	}
}
