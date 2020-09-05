package com.sealinkin.wms.model;

import com.sealinkin.wms.po.Wms_OutorderFlow;

public class Wms_OutorderFlowModel extends Wms_OutorderFlow{

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String expType;
	private short flowOrder;
	
	private boolean flag;
	private String flowvalueText;
	
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getExpType() {
		return expType;
	}
	public void setExpType(String expType) {
		this.expType = expType;
	}
	public short getFlowOrder() {
		return flowOrder;
	}
	public void setFlowOrder(short flowOrder) {
		this.flowOrder = flowOrder;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getFlowvalueText() {
		return flowvalueText;
	}
	public void setFlowvalueText(String flowvalueText) {
		this.flowvalueText = flowvalueText;
	}
	
	
	

}
