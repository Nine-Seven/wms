package com.sealinkin.protocolExchange.print;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *  获取报表详细信息 应答  StuAnsReportInfo
 * @author lich
 *
 */
public class AnsReportInfoModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strReportType;
	private BigDecimal reportCopies;//打印份数
	private List<Object> lstDtReport;
	private List<String> lstReportName;
	public String getStrReportType() {
		return strReportType;
	}
	public void setStrReportType(String strReportType) {
		this.strReportType = strReportType;
	}
	public BigDecimal getReportCopies() {
		return reportCopies;
	}
	public void setReportCopies(BigDecimal reportCopies) {
		this.reportCopies = reportCopies;
	}
	public List<Object> getLstDtReport() {
		return lstDtReport;
	}
	public void setLstDtReport(List<Object> lstDtReport) {
		this.lstDtReport = lstDtReport;
	}
	public List<String> getLstReportName() {
		return lstReportName;
	}
	public void setLstReportName(List<String> lstReportName) {
		this.lstReportName = lstReportName;
	}
}
