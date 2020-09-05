package com.sealinkin.protocolExchange.odata;

/* RF 获取下架单信息 Model 
 * Add by sunl 2016年3月6日
 * */
public class ODataGetOutStockInfoModel {
	/// <summary>
    /// 下架单
    /// </summary>
	private String outStockNo; 
    /// <summary>
    /// 操作类型
    /// </summary>
	private String operateType; 
    /// <summary>
    /// 总箱数
    /// </summary>
	private String qty; 
    public String getOutStockNo() {
		return outStockNo;
	}
	public void setOutStockNo(String outStockNo) {
		this.outStockNo = outStockNo;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getArticleNum() {
		return articleNum;
	}
	public void setArticleNum(String articleNum) {
		this.articleNum = articleNum;
	}
	/// <summary>
    /// 品项数
    /// </summary>
	private String articleNum; 
}
