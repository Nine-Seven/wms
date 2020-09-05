package com.sealinkin.bset.model;

import com.sealinkin.bset.po.Bset_WorkerOwner;

public class Bset_WorkerOwnerModel extends Bset_WorkerOwner{

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String ownerNo;
	private String workerNo;
	
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getWorkerNo() {
		return workerNo;
	}
	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}
	
	

}
