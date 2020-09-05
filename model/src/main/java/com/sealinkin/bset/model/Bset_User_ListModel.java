package com.sealinkin.bset.model;

import com.sealinkin.bset.po.Bset_User_List;

public class Bset_User_ListModel extends Bset_User_List{
	private static final long serialVersionUID = 1L;
	private String ownerName;
	private String warehouseName;
	private String workerOwner;
	private String workSpaceNo;//工作站No
	private String workSpaceName;//工作站名�?
	
	public String getWorkSpaceName() {
		return workSpaceName;
	}
	public void setWorkSpaceName(String workSpaceName) {
		this.workSpaceName = workSpaceName;
	}
	public String getWorkSpaceNo() {
		return workSpaceNo;
	}
	public void setWorkSpaceNo(String workSpaceNo) {
		this.workSpaceNo = workSpaceNo;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getWorkerOwner() {
		return workerOwner;
	}
	public void setWorkerOwner(String workerOwner) {
		String s="";
		if(workerOwner !=null && !workerOwner.equals("")){
			String[] owner=workerOwner.split(",");
			for (String str : owner) {
				s+="'"+str+"',";
			}
		}
		this.workerOwner = s.substring(0, s.length()-1);
	}
}
