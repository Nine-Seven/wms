package com.sealinkin.wms.model;

import java.math.BigDecimal;

import com.sealinkin.wms.po.Wms_Defreport;

public class Wms_DefreportModel extends Wms_Defreport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reportId;
	private BigDecimal subReportId;
	private String reprintFlag;
	private BigDecimal sqlOrder;
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public BigDecimal getSubReportId() {
		return subReportId;
	}
	public void setSubReportId(BigDecimal subReportId) {
		this.subReportId = subReportId;
	}
	public String getReprintFlag() {
		return reprintFlag;
	}
	public void setReprintFlag(String reprintFlag) {
		this.reprintFlag = reprintFlag;
	}
	public BigDecimal getSqlOrder() {
		return sqlOrder;
	}
	public void setSqlOrder(BigDecimal sqlOrder) {
		this.sqlOrder = sqlOrder;
	}
}
