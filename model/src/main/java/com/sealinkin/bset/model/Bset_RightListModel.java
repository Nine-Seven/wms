package com.sealinkin.bset.model;

import java.math.BigDecimal;

import com.sealinkin.bset.po.Bset_Rightlist;

public class Bset_RightListModel extends Bset_Rightlist{

	private static final long serialVersionUID = 1L;
	
	private String moduleId;
	private String moduleName;
	private String name;
	private String rightName;
	private String butId;
	private String butName;
	private BigDecimal status;
	private BigDecimal orderNo;
	private String parentId;
	private String terminalFlag;
	
	private Boolean flag;
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRightName() {
		return rightName;
	}
	public void setRightName(String rightName) {
		this.rightName = rightName;
	}
	public String getButId() {
		return butId;
	}
	public void setButId(String butId) {
		this.butId = butId;
	}
	public String getButName() {
		return butName;
	}
	public void setButName(String butName) {
		this.butName = butName;
	}
	public BigDecimal getStatus() {
		return status;
	}
	public void setStatus(BigDecimal status) {
		this.status = status;
	}
	public BigDecimal getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(BigDecimal orderNo) {
		this.orderNo = orderNo;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getTerminalFlag() {
		return terminalFlag;
	}
	public void setTerminalFlag(String terminalFlag) {
		this.terminalFlag = terminalFlag;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

}
