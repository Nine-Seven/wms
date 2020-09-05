package com.sealinkin.odata.model;

import java.util.Date;

import com.sealinkin.odata.po.odata_WmsOutlocateStrategyM;

public class odata_WmsOutlocateStrategyMModel extends odata_WmsOutlocateStrategyM{
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;  //主键
	private Integer locateStrategyId;
	
	private String strategyName;  //其它
	private String defalutFlag;
	private String defalutFlagText;
	
	private String rgstName;
	private Date rgstDate;
	
	public String getRgstName() {
		return rgstName;
	}
	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}
	public Date getRgstDate() {
		return rgstDate;
	}
	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}
	public String getDefalutFlagText() {
		return defalutFlagText;
	}
	public void setDefalutFlagText(String defalutFlagText) {
		this.defalutFlagText = defalutFlagText;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public Integer getLocateStrategyId() {
		return locateStrategyId;
	}
	public void setLocateStrategyId(Integer locateStrategyId) {
		this.locateStrategyId = locateStrategyId;
	}
	public String getStrategyName() {
		return strategyName;
	}
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}
	public String getDefalutFlag() {
		return defalutFlag;
	}
	public void setDefalutFlag(String defalutFlag) {
		this.defalutFlag = defalutFlag;
	}
	
	
}
