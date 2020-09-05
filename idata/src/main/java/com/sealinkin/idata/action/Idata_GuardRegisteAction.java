package com.sealinkin.idata.action;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.Idata_GuardRegisteModel;
import com.sealinkin.idata.model.Idata_OrderSheetModel;
import com.sealinkin.idata.model.Idata_OrderStatusModel;
import com.sealinkin.idata.service.Iidata_GuardRegisteService;
import com.sealinkin.util.ExceptionUtil;

public class Idata_GuardRegisteAction extends CommAction{
	
	private static final long serialVersionUID = 1L;
	private Iidata_GuardRegisteService idata_GuardRegisteImpl;
	private String strAppointDate;
	private String str;
	private String strWheresql;
	private String jsonMaster;
	private String jsonDetail;
	private String strSaveType;
	private String status;
	
	public void getOrderSerialList()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=idata_GuardRegisteImpl.getOrderSerialList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strAppointDate,strWheresql,str);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获取货主下拉
	public void queryOwnerCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=idata_GuardRegisteImpl.queryOwnerCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strAppointDate,str);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取供应商下拉
	public void querySupplierNoCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=idata_GuardRegisteImpl.querySupplierNoCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strAppointDate,str);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取状态下拉
	public void queryStatusCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=idata_GuardRegisteImpl.queryStatusCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strAppointDate,str);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取预约状况信息列表
	public void getIdata_Order_StatusList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Idata_OrderStatusModel> list=idata_GuardRegisteImpl.getIdata_Order_StatusList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strAppointDate,
					str, 
					pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}
	//获取报到状况信息列表
	public void getGuardRegisteList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Idata_GuardRegisteModel> list=idata_GuardRegisteImpl.getGuardRegisteList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					str,
					strWheresql,
					status,
					pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}
	//获取单据信息列表
	public void getIdata_Order_SheetList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Idata_OrderSheetModel> list=idata_GuardRegisteImpl.getIdata_Order_SheetList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//保存报到信息
	public void save(){
		try {
				MsgRes msg=idata_GuardRegisteImpl.save(jsonMaster, jsonDetail, strSaveType,
						super.getMdBdef_DefWorker().getWorkSpaceNo());
				super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public Iidata_GuardRegisteService getIdata_GuardRegisteImpl() {
		return idata_GuardRegisteImpl;
	}
	public void setIdata_GuardRegisteImpl(
			Iidata_GuardRegisteService idata_GuardRegisteImpl) {
		this.idata_GuardRegisteImpl = idata_GuardRegisteImpl;
	}


	public String getStrAppointDate() {
		return strAppointDate;
	}

	public void setStrAppointDate(String strAppointDate) {
		this.strAppointDate = strAppointDate;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
	
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}

	public String getJsonMaster() {
		return jsonMaster;
	}
	public void setJsonMaster(String jsonMaster) {
		this.jsonMaster = jsonMaster;
	}
	public String getJsonDetail() {
		return jsonDetail;
	}
	public void setJsonDetail(String jsonDetail) {
		this.jsonDetail = jsonDetail;
	}

	public String getStrSaveType() {
		return strSaveType;
	}

	public void setStrSaveType(String strSaveType) {
		this.strSaveType = strSaveType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
