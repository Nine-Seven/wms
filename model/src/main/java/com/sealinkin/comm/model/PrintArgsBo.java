package com.sealinkin.comm.model;

import java.io.Serializable;
/**
 * 打印参数
 * @author 翔
 *
 */
public class PrintArgsBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param warehouseNo			仓储No
	 * @param workStationNo			工作站No
	 * @param sourceNo				单据编号
	 * @param reportId				报表ID
	 * @param labelNoList           标签ID
	 * @param rgstName				打印人
	 */
	private String enterpriseNo;
	private String warehouseNo;
	private String workStationNo;
	private String sourceNo;
	private String reportId;
	private String htyFlag;
	private String rgstName;
	private String labelNoList ;
	private String printNum;
	
	
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
	public String getWorkStationNo() {
		return workStationNo;
	}
	public void setWorkStationNo(String workStationNo) {
		this.workStationNo = workStationNo;
	}
	public String getSourceNo() {
		return sourceNo;
	}
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getHtyFlag() {
		return htyFlag;
	}
	public void setHtyFlag(String htyFlag) {
		this.htyFlag = htyFlag;
	}
	public String getRgstName() {
		return rgstName;
	}
	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}
	public String getLabelNoList() {
		return labelNoList;
	}
	public void setLabelNoList(String labelNoList) {
		this.labelNoList = labelNoList;
	}
	public String getPrintNum() {
		return printNum;
	}
	public void setPrintNum(String printNum) {
		this.printNum = printNum;
	}
	
}
