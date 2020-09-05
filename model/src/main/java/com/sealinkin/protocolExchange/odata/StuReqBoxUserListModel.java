package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;


// <summary>
// 读取物流箱已分播用户列表请求
// </summary>
public class StuReqBoxUserListModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public String strSessionID;           //对话ID
    public String EnterpriseNo;        //企业代码
    public String WareHouseNo;
    public int UseType;             //标签用途
    public String LabelNo;
	public String getStrSessionID() {
		return strSessionID;
	}
	public void setStrSessionID(String strSessionID) {
		this.strSessionID = strSessionID;
	}
	public String getEnterpriseNo() {
		return EnterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		EnterpriseNo = enterpriseNo;
	}
	public String getWareHouseNo() {
		return WareHouseNo;
	}
	public void setWareHouseNo(String wareHouseNo) {
		WareHouseNo = wareHouseNo;
	}
	public int getUseType() {
		return UseType;
	}
	public void setUseType(int useType) {
		UseType = useType;
	}
	public String getLabelNo() {
		return LabelNo;
	}
	public void setLabelNo(String labelNo) {
		LabelNo = labelNo;
	}         

	
}
