package com.sealinkin.protocolExchange.odata;
 
/**
 * 集货信息 stuCollectGoods
 * @author sunl
 *
 */
public class stuCollectGoods{
	
    /// <summary>
    /// 企业编码
    /// </summary>
    private String EnterpriseNO;
    /// <summary>
    /// 仓别
    /// </summary>
    private String WarehouseNO;
    /// <summary>
    /// 货主
    /// </summary>
    private String OwnerNO;
    /// <summary>
    /// PO_NO
    /// </summary>
    private String PONO;
    /// <summary>
    /// SourceExpNO
    /// </summary>
    private String SourceExpNO;
    /// <summary>
    /// 操作人
    /// </summary>
    private String WorkerNO;
    /// <summary>
    /// 操作类型1，入库；2：出库
    /// </summary>
    private String CType;
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
	public String getOwnerNO() {
		return OwnerNO;
	}
	public void setOwnerNO(String ownerNO) {
		OwnerNO = ownerNO;
	}
	public String getPONO() {
		return PONO;
	}
	public void setPONO(String pONO) {
		PONO = pONO;
	}
	public String getSourceExpNO() {
		return SourceExpNO;
	}
	public void setSourceExpNO(String sourceExpNO) {
		SourceExpNO = sourceExpNO;
	}
	public String getWorkerNO() {
		return WorkerNO;
	}
	public void setWorkerNO(String workerNO) {
		WorkerNO = workerNO;
	}
	public String getCType() {
		return CType;
	}
	public void setCType(String cType) {
		CType = cType;
	}
    
    
}
