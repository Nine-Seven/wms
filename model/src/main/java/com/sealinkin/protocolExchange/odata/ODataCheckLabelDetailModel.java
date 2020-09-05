package com.sealinkin.protocolExchange.odata;

/* RF 标签检查Model
 * 用于显示标签的明细信息
 * Add by sunl 2016年2月25日
 * */
public class ODataCheckLabelDetailModel {
	private String labelNo;//标签号 
	private String articleNo;//商品名称
	private String articleName;//商品名称
	private String barcode;//条码 
	private String articleIdentifier;//助记码
	private String dpsCellNo;//电子标签储位
	private Double articleQty;//实际数量
	private String status;//标签明细状态
	private String statusText;//标签明细状态说明
	
	private Double packingQty;//包装数量
    private String packingUnit;//包装单位
	private Double qminOperatePacking;//最小操作包装数量   
	private String qminOperatePackingUnit;//最小操作包装单位
	private String workerName;//员工名
	private Integer NUMID;//序号 
	
	 
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public Double getQminOperatePacking() {
		return qminOperatePacking;
	}
	public void setQminOperatePacking(Double qminOperatePacking) {
		this.qminOperatePacking = qminOperatePacking;
	}
	public String getQminOperatePackingUnit() {
		return qminOperatePackingUnit;
	}
	public void setQminOperatePackingUnit(String qminOperatePackingUnit) {
		this.qminOperatePackingUnit = qminOperatePackingUnit;
	}
	public Double getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
 
 
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getArticleIdentifier() {
		return articleIdentifier;
	}
	public void setArticleIdentifier(String articleIdentifier) {
		this.articleIdentifier = articleIdentifier;
	}
	public String getDpsCellNo() {
		return dpsCellNo;
	}
	public void setDpsCellNo(String dpsCellNo) {
		this.dpsCellNo = dpsCellNo;
	}
	public Double getArticleQty() {
		return articleQty;
	}
	public void setArticleQty(Double articleQty) {
		this.articleQty = articleQty;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public Integer getNUMID() {
		return NUMID;
	}
	public void setNUMID(Integer nUMID) {
		NUMID = nUMID;
	} 

}
