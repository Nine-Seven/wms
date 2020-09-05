package com.sealinkin.protocolExchange.odata;
import java.io.Serializable;
/**
 *  读取用户当前区域任务请求（ StuReqGetTaskLabel）
 * @author wyf
 *
 */
public class ReqGetTaskLabel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String strSessionID;           //对话ID
	private String EnterpriseNo;          //企业代码
	private String WareHouseNo;
	private String UserId;
	private Integer UseType;             //标签用途
	private Integer CtrlNo;              //控制箱号
	private Integer AreaNo;              //区域
	private Integer StockNo;             //通道
	
	public String getstrSessionID(){
	   	 return strSessionID;
	    }
	    public void setstrSessionID(String strSessionID){
	   	 this.strSessionID=strSessionID;
	    }
	
	public String getEnterpriseNo(){
   	 return EnterpriseNo;
    }
    public void setEnterpriseNo(String EnterpriseNo){
   	 this.EnterpriseNo=EnterpriseNo;
    }
    
	public String getWareHouseNo() {
		return WareHouseNo;
	}
	public void setWareHouseNo(String wareHouseNo) {
		this.WareHouseNo = wareHouseNo;
	}
	
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		this.UserId = userId;
	}
	
	public Integer getUseType() {
		return UseType;
	}
	public void setUseType(Integer UseType) {
		this.UseType = UseType;
	}
	
	public Integer getCtrlNo() {
		return CtrlNo;
	}
	public void setCtrlNo(Integer CtrlNo) {
		this.CtrlNo = CtrlNo;
	}
	
	public Integer getAreaNo() {
		return AreaNo;
	}
	public void setAreaNo(Integer AreaNo) {
		this.AreaNo = AreaNo;
	}
	
	public Integer getStockNo() {
		return StockNo;
	}
	public void setStockNo(Integer StockNo) {
		this.StockNo = StockNo;
	}
}
