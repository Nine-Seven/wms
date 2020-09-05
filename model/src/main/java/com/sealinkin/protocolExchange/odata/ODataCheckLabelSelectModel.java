package com.sealinkin.protocolExchange.odata;

import java.util.List;

/* RF 标签检查Model
 * 用于查询及显示标签信息
 * Add by sunl 2016年2月25日
 * */
public class ODataCheckLabelSelectModel {
	private String labelNo;//标签号 
	private String useType;//类型 1：子标签 ；2.标签明细
	private List<ODataCheckLabelDetailModel> detailInfo;//标签明细信息
	private List<ODataCheckSubLabelModel> subLabelInfo;//子标签信息

	private String warehouseNo;//仓别
	private String enterpriseNo;//企业编码  
	
	
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public List<ODataCheckLabelDetailModel> getDetailInfo() {
		return detailInfo;
	}
	public void setDetailInfo(List<ODataCheckLabelDetailModel> detailInfo) {
		this.detailInfo = detailInfo;
	}
	public List<ODataCheckSubLabelModel> getSubLabelInfo() {
		return subLabelInfo;
	}
	public void setSubLabelInfo(List<ODataCheckSubLabelModel> subLabelInfo) {
		this.subLabelInfo = subLabelInfo;
	}
 
}
