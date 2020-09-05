package com.sealinkin.report.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.report.service.IAuto_Service;
import com.sealinkin.wms.model.Wms_Defcustom_DModel;

public class Auto_Action extends CommAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IAuto_Service auto_Impl;
	private String customId;
	private String value0;
	private String value1;
	private String value2;
	private String value3;
	private String value4;
	private String value5;
	private String value6;
	private String value7;
	private String value8;
	private String value9;
	
	//获取显示明细字段
	public void getCombo(){
		try{
			List<Wms_Defcustom_DModel> list =auto_Impl.getCombo(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					this.getCustomId()
					);
			super.writeArray(list);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//执行
	public void implement(){
		try{
			MsgRes msg = auto_Impl.implement(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getCustomId(),
					this.getValue0(),
					this.getValue1(),
					this.getValue2(),
					this.getValue3(),
					this.getValue4(),
					this.getValue5(),
					this.getValue6(),
					this.getValue7(),
					this.getValue8(),
					this.getValue9());
			super.writeObj(msg);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
		
	public void setAuto_Impl(IAuto_Service auto_Impl) {
		this.auto_Impl = auto_Impl;
	}
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}

	public String getValue0() {
		return value0;
	}

	public void setValue0(String value0) {
		this.value0 = value0;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getValue4() {
		return value4;
	}

	public void setValue4(String value4) {
		this.value4 = value4;
	}

	public String getValue5() {
		return value5;
	}

	public void setValue5(String value5) {
		this.value5 = value5;
	}

	public String getValue6() {
		return value6;
	}

	public void setValue6(String value6) {
		this.value6 = value6;
	}

	public String getValue7() {
		return value7;
	}

	public void setValue7(String value7) {
		this.value7 = value7;
	}

	public String getValue8() {
		return value8;
	}

	public void setValue8(String value8) {
		this.value8 = value8;
	}

	public String getValue9() {
		return value9;
	}

	public void setValue9(String value9) {
		this.value9 = value9;
	}
}
