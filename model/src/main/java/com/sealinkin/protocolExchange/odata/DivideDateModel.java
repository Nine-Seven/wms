package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;

import com.sealinkin.protocolExchange.idata.BdefArticleInfo;
import com.sealinkin.protocolExchange.idata.PackInfoModel;

/**
 * 分播信息结构
 * @author lich
 *
 */
public class DivideDateModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String ownerNo;//委托业主
	private String divideNo;//分播单号 
	private String deliverObj;//配送对象  
	private String batchNo;//批次号
	private String custNo;//客户编号
	private String scellNo;//来源储位
	private String scontainerNo;//来源容器号	
	private String custName;//客户名称
	private Double divideQty;//计划数量
	private BdefArticleInfo stuArticleInfo;//商品信息
	private PackInfoModel stuPackInfo;//包装信息   
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getDivideNo() {
		return divideNo;
	}
	public void setDivideNo(String divideNo) {
		this.divideNo = divideNo;
	}
	public String getDeliverObj() {
		return deliverObj;
	}
	public void setDeliverObj(String deliverObj) {
		this.deliverObj = deliverObj;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getScellNo() {
		return scellNo;
	}
	public void setScellNo(String scellNo) {
		this.scellNo = scellNo;
	}
	
	public String getScontainerNo() {
		return scontainerNo;
	}
	public void setScontainerNo(String scontainerNo) {
		this.scontainerNo = scontainerNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Double getDivideQty() {
		return divideQty;
	}
	public void setDivideQty(Double divideQty) {
		this.divideQty = divideQty;
	}
	public BdefArticleInfo getStuArticleInfo() {
		return stuArticleInfo;
	}
	public void setStuArticleInfo(BdefArticleInfo stuArticleInfo) {
		this.stuArticleInfo = stuArticleInfo;
	}
	public PackInfoModel getStuPackInfo() {
		return stuPackInfo;
	}
	public void setStuPackInfo(PackInfoModel stuPackInfo) {
		this.stuPackInfo = stuPackInfo;
	}    	         
}
