package com.sealinkin.protocolExchange.dpsdata;

import java.io.Serializable;

/**
        电子标签数据   StuDPSAddress
 * @author lich
 *
 */
public class DPSAddressModel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String DeviceNo;       //设备号

	private String cellNo;         //储位代码
    private int ctrNo;               //控制箱号
    private int deviceType;          //标签类型
    private short tagNode;           //标签地址
    private short barcodeTagNode;    //所属枪地址
    private short dispLabelTagNode; //显示标签地址
    private short voiceTagNode;      //声音标签地址
    private short stockLampTagNode;  //巷道灯地址
    private int areaNo;              //区域
    private int stockNo;             //通道
    private int dispFlag;            //是否提示标签
    private String pntName;        //打印机名
    private int DpsDispLength;      //标签长度
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public int getCtrNo() {
		return ctrNo;
	}
	public void setCtrNo(int ctrNo) {
		this.ctrNo = ctrNo;
	}
	public int getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	public short getTagNode() {
		return tagNode;
	}
	public void setTagNode(short tagNode) {
		this.tagNode = tagNode;
	}
	public short getBarcodeTagNode() {
		return barcodeTagNode;
	}
	public void setBarcodeTagNode(short barcodeTagNode) {
		this.barcodeTagNode = barcodeTagNode;
	}
	public short getDispLabelTagNode() {
		return dispLabelTagNode;
	}
	public void setDispLabelTagNode(short dispLabelTagNode) {
		this.dispLabelTagNode = dispLabelTagNode;
	}
	public short getVoiceTagNode() {
		return voiceTagNode;
	}
	public void setVoiceTagNode(short voiceTagNode) {
		this.voiceTagNode = voiceTagNode;
	}
	public short getStockLampTagNode() {
		return stockLampTagNode;
	}
	public void setStockLampTagNode(short stockLampTagNode) {
		this.stockLampTagNode = stockLampTagNode;
	}
	public int getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(int areaNo) {
		this.areaNo = areaNo;
	}
	public int getStockNo() {
		return stockNo;
	}
	public void setStockNo(int stockNo) {
		this.stockNo = stockNo;
	}
	public int getDispFlag() {
		return dispFlag;
	}
	public void setDispFlag(int dispFlag) {
		this.dispFlag = dispFlag;
	}
	public String getPntName() {
		return pntName;
	}
	public void setPntName(String pntName) {
		this.pntName = pntName;
	}
	public int getDpsDispLength() {
		return DpsDispLength;
	}
	public void setDpsDispLength(int dpsDispLength) {
		DpsDispLength = dpsDispLength;
	}
	public String getDeviceNo() {
		return DeviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		DeviceNo = deviceNo;
	}
	
}
