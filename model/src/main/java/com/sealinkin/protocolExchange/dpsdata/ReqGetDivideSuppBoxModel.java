package com.sealinkin.protocolExchange.dpsdata;

import java.io.Serializable;

/**
        电子标签数据   StuReqDpsAddr
 * @author lich
 *
 */
public class ReqGetDivideSuppBoxModel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  strSessionID;           //对话ID
	private String EnterpriseNo;
    private String  warehouseNo;    //仓库代码
    private Integer useType;             //标签用途
    private Integer ctrlNo;              //控制箱号
    private Integer areaNo;              //区域
    private Integer stockNo;             //通道
    private String  boxNo;          //箱号     
	public String getStrSessionID() {
		return strSessionID;
	}
	public void setStrSessionID(String strSessionID) {
		this.strSessionID = strSessionID;
	}
	public String getEnterpriseNo() {
		return EnterpriseNo;
	}
	public void setEnterpriseNo(String EnterpriseNo) {
		this.EnterpriseNo = EnterpriseNo;
	}
	
	
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public Integer getUseType() {
		return useType;
	}
	public void setUseType(Integer useType) {
		this.useType = useType;
	}
	public Integer getCtrlNo() {
		return ctrlNo;
	}
	public void setCtrlNo(Integer ctrlNo) {
		this.ctrlNo = ctrlNo;
	}
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
	public String getBoxNo() {
		return boxNo;
	}
	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}    
}
