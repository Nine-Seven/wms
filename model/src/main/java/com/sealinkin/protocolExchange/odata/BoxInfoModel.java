package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;

/**
 * 返回当前箱最少分播区域及条码列表 对应stuAnsBoxInfo
 * @author lich
 *
 */
public class BoxInfoModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String  DeviceGroupNo;//设备组
    private String  DeviceNo;	//设备号
	private Integer areaNo;                 //最小区域
    private Integer stockNo;                //最小通道
    private String  barcode;    //条码列表
	public Integer getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(Integer areaNo) {
		this.areaNo = areaNo;
	}
	public Integer getStockNo() {
		return stockNo;
	}
	public void setStockNo(Integer stockNo) {
		this.stockNo = stockNo;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getDeviceNo() {
		return DeviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		DeviceNo = deviceNo;
	}
	public String getDeviceGroupNo() {
		return DeviceGroupNo;
	}
	public void setDeviceGroupNo(String deviceGroupNo) {
		DeviceGroupNo = deviceGroupNo;
	}
}
