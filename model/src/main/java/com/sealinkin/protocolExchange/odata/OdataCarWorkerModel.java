package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;

/**
 * 按客户波次装车--车辆类型或司机信息
 * @author huangb 20160708
 *
 */
public class OdataCarWorkerModel{
	
	private String enterpriseNo;//企业编号
	private String warehouseNo;//仓别
	private String carNo;//车辆代码
	private String carPlate;//车牌号
	private String workerNo;//司机编号
	private String workerName;//司机名称
	private String tel;//电话号码
	private String getDataFlag;//获取数据标识 0-获取车辆信息;1-获取用户信息
	
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
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getCarPlate() {
		return carPlate;
	}
	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}
	public String getWorkerNo() {
		return workerNo;
	}
	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getGetDataFlag() {
		return getDataFlag;
	}
	public void setGetDataFlag(String getDataFlag) {
		this.getDataFlag = getDataFlag;
	}
	
	
	 
}
