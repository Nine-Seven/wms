package com.sealinkin.comm.model;

import java.math.BigDecimal;

import com.sealinkin.comm.po.WmsDefPrintCenterPo;

/**
 * 打印中心配置表
 * 
 * @author 翔
 * 
 */
public class WmsDefPrintCenterBo extends WmsDefPrintCenterPo {

	private static final long serialVersionUID = 1L;
	private String reportId;
	private String reportType;
	private BigDecimal subReportId;

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public BigDecimal getSubReportId() {
		return subReportId;
	}

	public void setSubReportId(BigDecimal subReportId) {
		this.subReportId = subReportId;
	}
}
