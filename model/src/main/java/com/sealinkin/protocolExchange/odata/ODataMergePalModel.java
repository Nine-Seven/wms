package com.sealinkin.protocolExchange.odata;

/* RF 装并板Model 
 * Add by sunl 2016年2月25日
 * */
public class ODataMergePalModel {
	  /// <summary>
    /// 企业编码
    /// </summary>
    private String enterpriseNo;
    /// <summary>
    /// 仓别
    /// </summary>
    private String warehouseNo;
    /// <summary>
    /// 板号
    /// </summary>
    private String plateNo;
    /// <summary>
    /// 箱号
    /// </summary>
    private String boxNo; 
    /// <summary>
    /// 客户名称
    /// </summary>
    private String custName;
    /// <summary>
    /// 标签状态描述
    /// </summary>
    private String StatusText;
    /// <summary>
    /// 操作人
    /// </summary>
    private String userID;
    /// <summary>
    /// 标记当前是扫箱扫板（1）/扫板扫箱（2）
    /// </summary>
    private String useType;

    /// <summary>
    /// 已扫箱标签个数
    /// </summary>
    private Integer labelCount;
    
	public Integer getLabelCount() {
		return labelCount;
	}
	public void setLabelCount(Integer labelCount) {
		this.labelCount = labelCount;
	}
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
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getBoxNo() {
		return boxNo;
	}
	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getStatusText() {
		return StatusText;
	}
	public void setStatusText(String statusText) {
		StatusText = statusText;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	} 
    
}
