package com.sealinkin.idata.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.DockSheetModel;
import com.sealinkin.idata.model.Idata_ImportMModel;
import com.sealinkin.idata.model.Idata_OrderSheetModel;
import com.sealinkin.idata.model.Idata_OrderStatusModel;
import com.sealinkin.idata.service.Iidata_Order_Time;

public class Idata_Order_TimeAction extends CommAction{
	
	private static final long serialVersionUID = 1L;
	private Iidata_Order_Time idata_Order_TimeImpl;
	private String flag;
	private String str;
	private String time;
	private String printFlag;
	private String strDockNo;
	private String strWheresql;
	public Iidata_Order_Time getIdata_Order_TimeImpl() {
		return idata_Order_TimeImpl;
	}

	public void setIdata_Order_TimeImpl(Iidata_Order_Time idata_Order_TimeImpl) {
		this.idata_Order_TimeImpl = idata_Order_TimeImpl;
	}
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	

	public String getPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

	public String getStrDockNo() {
		return strDockNo;
	}

	public void setStrDockNo(String strDockNo) {
		this.strDockNo = strDockNo;
	}

	public String getStrWheresql() {
		return strWheresql;
	}

	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}

	public void getDock_Sheet(){
		try {
			List<DockSheetModel> list=idata_Order_TimeImpl.getDock_Sheet(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getIdata_Order_Time_GetCombo() {
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=idata_Order_TimeImpl.getIdata_Order_Time_GetCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getFlag(),getStr(),0,100);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 获得码头下拉
	 */
	public void getDockNoCombo()throws Exception{
		try {
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=idata_Order_TimeImpl.getDockNoCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
			        super.getMdBdef_DefWorker().getWorkerOwner(),
			        strWheresql,str);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获得进货通知单
	 * @throws Exception
	 */
	public void getIdata_Import_MList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Idata_ImportMModel> list=idata_Order_TimeImpl.getIdata_Import_MList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStr(), pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 进货预约》集单保存
	 */
	public void save(){
		try {
				
			MsgRes msg=idata_Order_TimeImpl.save(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr(), 
					getTime(), 
					super.getMdBdef_DefWorker().getWorkerNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					strDockNo,
					this.getFlag(),
					this.getPrintFlag()
					);
			super.writeObj(msg);
		} catch (Exception e) {
			super.writeObj(new MsgRes(false,"保存失败",e.getMessage()));
			e.printStackTrace();
		}
	}
	
	/**
	 * 进货预约》修改保存
	 */
	public void editSave(){
		try {
				
			MsgRes msg=idata_Order_TimeImpl.editSave(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					strDockNo,getTime(),str
					);
			super.writeObj(msg);
		} catch (Exception e) {
			super.writeObj(new MsgRes(false,"保存失败",e.getMessage()));
			e.printStackTrace();
		}
	}
	/**
	 * 预约状况记录表
	 */
	public void getIdata_Order_StatusList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Idata_OrderStatusModel> list=idata_Order_TimeImpl.getIdata_Order_StatusList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getFlag(),
					getStr(), 
					pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 预约单号记录档
	 */
	public void getIdata_Order_SheetList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Idata_OrderSheetModel> list=idata_Order_TimeImpl.getIdata_Order_SheetList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStr(), pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
