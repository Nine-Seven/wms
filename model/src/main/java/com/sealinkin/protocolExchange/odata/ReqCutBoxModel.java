package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;
/**
 *  切箱请求（ StuReqCutBox）
 * @author lich
 *
 */
public class ReqCutBoxModel implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String EnterpriseNo;
	private String  wareHouseNo;
     private String  cellNo;
     private String  userId;
     private String  paperUserId;
     private Integer operateTools;
     private String pntName;
     
     public String getEnterpriseNo(){
    	 return EnterpriseNo;
     }
     public void setEnterpriseNo(String EnterpriseNo){
    	 this.EnterpriseNo=EnterpriseNo;
     }
     
	public String getWareHouseNo() {
		return wareHouseNo;
	}
	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPaperUserId() {
		return paperUserId;
	}
	public void setPaperUserId(String paperUserId) {
		this.paperUserId = paperUserId;
	}
	public Integer getOperateTools() {
		return operateTools;
	}
	public void setOperateTools(Integer operateTools) {
		this.operateTools = operateTools;
	}
	public String getPntName() {
		return pntName;
	}
	public void setPntName(String pntName) {
		this.pntName = pntName;
	}
	
}
