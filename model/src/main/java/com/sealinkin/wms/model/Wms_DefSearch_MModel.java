package com.sealinkin.wms.model;

import com.sealinkin.wms.po.Wms_DefSearch_M;

public class Wms_DefSearch_MModel extends Wms_DefSearch_M{

	private static final long serialVersionUID = 1L;
	private String pgmId;
	private String enterpriseNo;
	
	public String getPgmId() {
		return pgmId;
	}
	public void setPgmId(String pgmId) {
		this.pgmId = pgmId;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	
	
	
}
