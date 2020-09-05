package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;

/**
 * 商品对应包装信息
 * @author sunl
 *
 */
public class IdataGetArticlePackingInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	  
	/// <summary>
    /// 企业编码
    /// </summary>
    private String EnterpriseNO; 
    /// <summary>
    /// 仓别
    /// </summary>
    private String WarehouseNO; 
    /// <summary>
    /// 流水号
    /// </summary>
    private String SerialNO;
    /// <summary>
    /// 商品编码
    /// </summary>
    private String ArticleNO;
    /// <summary>
    /// 行号（进货汇总明细）
    /// </summary>
    private String RowID;

    /// <summary>
    /// 最小包装数量
    /// </summary>
    public double PackingMin;
    /// <summary>
    /// 最小操作包装数
    /// </summary>
    public double PackingMINOPERATE;
    /// <summary>
    /// 订单包装
    /// </summary>
    public double PackingOrder;


    /// <summary>
    /// 最小包装单位
    /// </summary>
    private String UnitMin;
    /// <summary>
    /// 最小操作包装单位
    /// </summary>
    private String UnitMINOPERATE;
    /// <summary>
    /// 订单单位
    /// </summary>
    private String UnitOrder;
	public String getEnterpriseNO() {
		return EnterpriseNO;
	}
	public void setEnterpriseNO(String enterpriseNO) {
		EnterpriseNO = enterpriseNO;
	}
	public String getWarehouseNO() {
		return WarehouseNO;
	}
	public void setWarehouseNO(String warehouseNO) {
		WarehouseNO = warehouseNO;
	}
	public String getSerialNO() {
		return SerialNO;
	}
	public void setSerialNO(String serialNO) {
		SerialNO = serialNO;
	}
	public String getArticleNO() {
		return ArticleNO;
	}
	public void setArticleNO(String articleNO) {
		ArticleNO = articleNO;
	}
	public String getRowID() {
		return RowID;
	}
	public void setRowID(String rowID) {
		RowID = rowID;
	}
	public double getPackingMin() {
		return PackingMin;
	}
	public void setPackingMin(double packingMin) {
		PackingMin = packingMin;
	}
	public double getPackingMINOPERATE() {
		return PackingMINOPERATE;
	}
	public void setPackingMINOPERATE(double packingMINOPERATE) {
		PackingMINOPERATE = packingMINOPERATE;
	}
	public double getPackingOrder() {
		return PackingOrder;
	}
	public void setPackingOrder(double packingOrder) {
		PackingOrder = packingOrder;
	}
	public String getUnitMin() {
		return UnitMin;
	}
	public void setUnitMin(String unitMin) {
		UnitMin = unitMin;
	}
	public String getUnitMINOPERATE() {
		return UnitMINOPERATE;
	}
	public void setUnitMINOPERATE(String unitMINOPERATE) {
		UnitMINOPERATE = unitMINOPERATE;
	}
	public String getUnitOrder() {
		return UnitOrder;
	}
	public void setUnitOrder(String unitOrder) {
		UnitOrder = unitOrder;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
