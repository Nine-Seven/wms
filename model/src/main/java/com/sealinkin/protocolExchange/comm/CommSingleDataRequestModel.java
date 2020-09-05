package com.sealinkin.protocolExchange.comm;

public class CommSingleDataRequestModel {
	private String EnterpriseNo;
	private String WarehouseNo;
	private String ReqObj;
	private String FieldEX1;//拓展字段1
	private String FieldEX2;//拓展字段2
	private String FieldEX3;//拓展字段3(分播边拣边分使用)yefk
	
	
	public String getFieldEX3() {
		return FieldEX3;
	}

	public void setFieldEX3(String fieldEX3) {
		FieldEX3 = fieldEX3;
	}

	public String getFieldEX2() {
		return FieldEX2;
	}

	public void setFieldEX2(String fieldEX2) {
		FieldEX2 = fieldEX2;
	}

	public String getFieldEX1() {
		return FieldEX1;
	}

	public void setFieldEX1(String fieldEX1) {
		FieldEX1 = fieldEX1;
	}

	public String getEnterpriseNo() {
		return EnterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		EnterpriseNo = enterpriseNo;
	}

	public String getWarehouseNo() {
		return WarehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		WarehouseNo = warehouseNo;
	}

	public String getReqObj() {
		return ReqObj;
	}

	public void setReqObj(String reqObj) {
		ReqObj = reqObj;
	}
	
}
