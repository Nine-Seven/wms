package com.sealinkin.comm.model;

import java.io.Serializable;
/**
 * 补印报表信息对象
 * 用于补印中、查询出来的数据
 * @author 翔
 *
 */
public class RepairReportInfoBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  ownerNo;  //委托业主NO
	private String  labelNo;  //外部标签号
	private String  sourceNo;  //单据NO
	private String  custNo;  //客户/供应商
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getSourceNo() {
		return sourceNo;
	}
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	
}
