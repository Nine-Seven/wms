package com.sealinkin.protocolExchange.odata;

public class StuAreaBarcodeInfoModel {
	private String DeviceGroupNo;    //设备组号
	private String DeviceNo;         //设备号
	private int AreaNo;              //最小区域
	private int StockNo;             //最小通道
	private String Barcode;          //商品条码
	private String LabelNo;          //最小箱号
	
	private String OutStockNo;		 //下架单号（电子标签可能扫B型标签）
	
	
	public String getOutStockNo() {
		return OutStockNo;
	}
	public void setOutStockNo(String outStockNo) {
		OutStockNo = outStockNo;
	}
	public String getDeviceGroupNo() {
		return DeviceGroupNo;
	}
	public void setDeviceGroupNo(String deviceGroupNo) {
		DeviceGroupNo = deviceGroupNo;
	}
	public String getDeviceNo() {
		return DeviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		DeviceNo = deviceNo;
	}
	public int getAreaNo() {
		return AreaNo;
	}
	public void setAreaNo(int areaNo) {
		AreaNo = areaNo;
	}
	public int getStockNo() {
		return StockNo;
	}
	public void setStockNo(int stockNo) {
		StockNo = stockNo;
	}
	public String getBarcode() {
		return Barcode;
	}
	public void setBarcode(String barcode) {
		Barcode = barcode;
	}
	public String getLabelNo() {
		return LabelNo;
	}
	public void setLabelNo(String labelNo) {
		LabelNo = labelNo;
	}
	
}
