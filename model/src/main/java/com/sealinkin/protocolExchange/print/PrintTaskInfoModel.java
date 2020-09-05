package com.sealinkin.protocolExchange.print;

import java.io.Serializable;

/**
 * 打印任务信息  StuPrintTaskInfo
 * @author lich
 *
 */
public class PrintTaskInfoModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String TaskNo;
	private String TaskType;
	private String ReportID;
	private String PrintName;	//打印机名称
	private boolean ReprintFlag;
	public String getTaskNo() {
		return TaskNo;
	}
	public void setTaskNo(String taskNo) {
		TaskNo = taskNo;
	}
	public String getTaskType() {
		return TaskType;
	}
	public void setTaskType(String taskType) {
		TaskType = taskType;
	}
	public String getReportID() {
		return ReportID;
	}
	public void setReportID(String reportID) {
		ReportID = reportID;
	}
	public String getPrintName() {
		return PrintName;
	}
	public void setPrintName(String printName) {
		PrintName = printName;
	}
	public boolean isReprintFlag() {
		return ReprintFlag;
	}
	public void setReprintFlag(boolean reprintFlag) {
		ReprintFlag = reprintFlag;
	}
}
