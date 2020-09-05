package com.sealinkin.bdef.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.bdef.model.Device_DivideDModel;
import com.sealinkin.bdef.model.Device_DivideMModel;
import com.sealinkin.bdef.model.Device_Divide_GroupModel;
import com.sealinkin.bdef.service.IDivice_DivideService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

public class Divice_DivideAction extends CommAction {

	private static final long serialVersionUID = 1L;
	private IDivice_DivideService Divice_DivideImpl;
	private String strQuery;
	private String str;
	private String perfix;
	private String bayXMin;
	private String bayXMax;
	private String floorMin;
	private String floorMax;
	private String strDeviceDivideGroup;
	private String strDeviceNo;
	
	//获取设备组
	public void getDeviceDivideGroup(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Device_Divide_GroupModel> list=Divice_DivideImpl.getDeviceDivideGroup(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery, pageBo);
				super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	//保存设备组
	public void saveDeviceDivideGroup(){
		try{
			this.Divice_DivideImpl.saveDeviceDivideGroup(this.str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch(Exception e){
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}
	}
	
	//获取设备
	public void getDeviceDivideM(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Device_DivideMModel> list=Divice_DivideImpl.getDeviceDivideM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery, pageBo);
				super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	//获取设备组combo
	public void getDeviceDivideGroupCombo(){
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=Divice_DivideImpl.getDeviceDivideGroupCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//设备唯一性
	public void checkDeviceNo1S01(){
		try {
			String no=Divice_DivideImpl.checkDeviceNo1S01(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), str);
			super.writeStr(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	//保存设备
	public void saveDeviceDivideM(){
		try{
			this.Divice_DivideImpl.saveDeviceDivideM(this.str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch(Exception e){
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}
	
	}
	//获取设备combo
	public void getDeviceDivideMCombo(){
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=Divice_DivideImpl.getDeviceDivideMCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}	
	}
	
	//保存格子号
	public void saveDeviceDivideD(){
		try{
			this.Divice_DivideImpl.saveDeviceDivideD(
					this.getMdBdef_DefWorker().getEnterpriseNo(),
					this.getMdBdef_DefWorker().getWarehouseNo(),
					this.getMdBdef_DefWorker().getWorkerNo(),
					this.str,
					this.getPerfix(),
					this.getBayXMin(),
					this.getBayXMax(),
					this.getFloorMin(),
					this.getFloorMax());
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch(Exception e){
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}
	}
	
	//获取格子号信息
	public void getDeviceDivideD(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Device_DivideDModel> list=Divice_DivideImpl.getDeviceDivideD(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery, pageBo);
				super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	
	}
	
	//修改pickOrder
	public void changePickOrder(){
		try{
			this.Divice_DivideImpl.changePickOrder(this.str);
			super.writeObj(new MsgRes(true, "修改成功", ""));
		} catch(Exception e){
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "修改失败", ""));
		}
	}
	
	//编辑格子号
	public void editDeviceDivideD(){
		System.out.println("XXXXXXXXXXXXXXXXX");
		try{
			this.Divice_DivideImpl.editDeviceDivideD(this.str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch(Exception e){
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}	
	}
	
	//检验储位
	public void checkCell(){
    	try{	
			MsgRes msg=this.Divice_DivideImpl.checkCell(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStrDeviceDivideGroup(),
					this.getStrDeviceNo(),
					this.getPerfix(),
					this.getBayXMin(),
					this.getBayXMax(),
					this.getFloorMin(),
					this.getFloorMax()				
			);
			super.writeObj(msg);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}
    
    
		
	}
	
	public void setDivice_DivideImpl(IDivice_DivideService divice_DivideImpl) {
		Divice_DivideImpl = divice_DivideImpl;
	}	
	public String getStrQuery() {
		return strQuery;
	}	
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getPerfix() {
		return perfix;
	}
	public void setPerfix(String perfix) {
		this.perfix = perfix;
	}
	public String getBayXMin() {
		return bayXMin;
	}
	public void setBayXMin(String bayXMin) {
		this.bayXMin = bayXMin;
	}
	public String getBayXMax() {
		return bayXMax;
	}
	public void setBayXMax(String bayXMax) {
		this.bayXMax = bayXMax;
	}
	public String getFloorMin() {
		return floorMin;
	}
	public void setFloorMin(String floorMin) {
		this.floorMin = floorMin;
	}
	public String getFloorMax() {
		return floorMax;
	}
	public void setFloorMax(String floorMax) {
		this.floorMax = floorMax;
	}

	public String getStrDeviceDivideGroup() {
		return strDeviceDivideGroup;
	}

	public void setStrDeviceDivideGroup(String strDeviceDivideGroup) {
		this.strDeviceDivideGroup = strDeviceDivideGroup;
	}
	public String getStrDeviceNo() {
		return strDeviceNo;
	}
	public void setStrDeviceNo(String strDeviceNo) {
		this.strDeviceNo = strDeviceNo;
	}
}
