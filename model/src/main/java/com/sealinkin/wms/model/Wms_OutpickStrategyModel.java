package com.sealinkin.wms.model;

import com.sealinkin.wms.po.Wms_OutpickStrategy;

public class Wms_OutpickStrategyModel extends Wms_OutpickStrategy {
   
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private Integer pickStrategyId;
	private String pickDiffName;
	private String pickAutoName;
	private String autoGetdivideName;
	private String autoDividesaveName;
	
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public Integer getPickStrategyId() {
		return pickStrategyId;
	}
	public void setPickStrategyId(Integer pickStrategyId) {
		this.pickStrategyId = pickStrategyId;
	}
	public String getPickDiffName() {
		return pickDiffName;
	}
	public void setPickDiffName(String pickDiffName) {
		this.pickDiffName = pickDiffName;
	}
	public String getPickAutoName() {
		return pickAutoName;
	}
	public void setPickAutoName(String pickAutoName) {
		this.pickAutoName = pickAutoName;
	}
	public String getAutoGetdivideName() {
		return autoGetdivideName;
	}
	public void setAutoGetdivideName(String autoGetdivideName) {
		this.autoGetdivideName = autoGetdivideName;
	}
	public String getAutoDividesaveName() {
		return autoDividesaveName;
	}
	public void setAutoDividesaveName(String autoDividesaveName) {
		this.autoDividesaveName = autoDividesaveName;
	}
	
	
}
