package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;

/**
 * 分播取号
 * @author sunl
 *
 */
public class DivideGetNOModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 778718580250586544L;
	/**
	 * 
	 */

	 /// <summary>
    /// 企业编号
    /// </summary>
    private String EnterpriseNo;
    /// <summary>
    /// 仓别
    /// </summary>
    private String WareHouseNo;
	/// <summary>
    /// 客户编码
    /// </summary>
    private String CustNo;
    /// <summary>
    /// 客户名称
    /// </summary>
    private String CustName;
    /// <summary>
    /// 物流类型
    /// </summary>
    private String MType;
    /// <summary>
    /// 工作区编码
    /// </summary>
    private String DockNo;
    /// <summary>
    /// 商品编码
    /// </summary>
    private String ArticleNO;
    /// <summary>
    /// 分播单号
    /// </summary>
    private String DivideNO;
    /// <summary>
    /// 标签号
    /// </summary>
    private String LableNO;
    /// <summary>
    /// 操作人
    /// </summary>
    private String WorkerNO;
    
    
    public String getWorkerNO() {
		return WorkerNO;
	}
	public void setWorkerNO(String workerNO) {
		WorkerNO = workerNO;
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
	public String getCustNo() {
		return CustNo;
	}
	public void setCustNo(String custNo) {
		CustNo = custNo;
	}
	public String getCustName() {
		return CustName;
	}
	public void setCustName(String custName) {
		CustName = custName;
	}
	public String getMType() {
		return MType;
	}
	public void setMType(String mType) {
		MType = mType;
	}
	public String getDockNo() {
		return DockNo;
	}
	public void setDockNo(String dockNo) {
		DockNo = dockNo;
	}
	public String getArticleNO() {
		return ArticleNO;
	}
	public void setArticleNO(String articleNO) {
		ArticleNO = articleNO;
	}
	public String getDivideNO() {
		return DivideNO;
	}
	public void setDivideNO(String divideNO) {
		DivideNO = divideNO;
	}
	public String getLableNO() {
		return LableNO;
	}
	public void setLableNO(String lableNO) {
		LableNO = lableNO;
	}
	
}
