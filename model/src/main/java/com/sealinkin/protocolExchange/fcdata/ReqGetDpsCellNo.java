package com.sealinkin.protocolExchange.fcdata;
import java.io.Serializable;
/**
 * stuGetDpsCellNoReq
 * @author wyf
 *
 */
@SuppressWarnings("serial")
public class ReqGetDpsCellNo implements Serializable{
	private String enterpriseNo;//企业
	private String warehouseNo;//仓别
	private String articleNo;//商品编码
	private String labelNo;//标签
	private String ContainerNo;// 
	private String cellNo;//储位
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
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getContainerNo() {
		return ContainerNo;
	}
	public void setContainerNo(String containerNo) {
		ContainerNo = containerNo;
	}
}
