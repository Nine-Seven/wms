package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;

/**
 * 获取分播箱信息或明细 对应StuReqGetDivideBox
 * @author lich
 *
 */
public class ReqGetDivideBoxModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strSessionID;           //对话ID
	private String enterpriseNo;
	private String WarehouseNo;      //仓库代码
    private Integer UseType;             //标签用途
    private Integer CtrlNo;              //控制箱号
    private Integer AreaNo;              //区域
    private Integer StockNo;             //通道
    private String BoxNo;            //箱号
    private String Barcode;          //商品条码	
    private String DeviceGroupNo;	 //设备组号
    
	public String getStrSessionID() {
		return strSessionID;
	}
	public void setStrSessionID(String strSessionID) {
		this.strSessionID = strSessionID;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getWarehouseNo() {
		return WarehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		WarehouseNo = warehouseNo;
	}
	public Integer getUseType() {
		return UseType;
	}
	public void setUseType(Integer useType) {
		UseType = useType;
	}
	public Integer getCtrlNo() {
		return CtrlNo;
	}
	public void setCtrlNo(Integer ctrlNo) {
		CtrlNo = ctrlNo;
	}
	public Integer getAreaNo() {
		return AreaNo;
	}
	public void setAreaNo(Integer areaNo) {
		AreaNo = areaNo;
	}
	public Integer getStockNo() {
		return StockNo;
	}
	public void setStockNo(Integer stockNo) {
		StockNo = stockNo;
	}
	public String getBoxNo() {
		return BoxNo;
	}
	public void setBoxNo(String boxNo) {
		BoxNo = boxNo;
	}
	public String getBarcode() {
		return Barcode;
	}
	public void setBarcode(String barcode) {
		Barcode = barcode;
	}
	public String getDeviceGroupNo() {
		return DeviceGroupNo;
	}
	public void setDeviceGroupNo(String deviceGroupNo) {
		DeviceGroupNo = deviceGroupNo;
	}       
}
