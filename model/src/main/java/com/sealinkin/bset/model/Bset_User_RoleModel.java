package com.sealinkin.bset.model;

import java.math.BigDecimal;

import com.sealinkin.bset.po.Bset_User_Role;

public class Bset_User_RoleModel extends Bset_User_Role{

	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String workerNo;
	private BigDecimal roleId;
	private Boolean flag;
	private String workerName;
	
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getWorkerNo() {
		return workerNo;
	}
	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}
	public BigDecimal getRoleId() {
		return roleId;
	}
	public void setRoleId(BigDecimal roleId) {
		this.roleId = roleId;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	
	

}
