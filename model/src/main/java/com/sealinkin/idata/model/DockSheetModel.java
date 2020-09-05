package com.sealinkin.idata.model;

import com.sealinkin.idata.po.DockSheet;

public class DockSheetModel extends DockSheet{
	
	private static final long serialVersionUID = 1L;
	
	private String rgstName;
	private String startTime;
	private String endTime;
	public String getRgstName() {
		return rgstName;
	}
	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	

}
