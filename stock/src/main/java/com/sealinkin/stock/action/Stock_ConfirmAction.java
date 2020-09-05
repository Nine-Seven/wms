package com.sealinkin.stock.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.stock.model.Stock_ConfirmDModel;
import com.sealinkin.stock.model.Stock_ConfirmMModel;
import com.sealinkin.stock.service.IStock_ConfirmService;
import com.sealinkin.util.ExceptionUtil;



public class Stock_ConfirmAction extends CommAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IStock_ConfirmService stock_ConfirmServiceImpl;
	private String str;
	private String strJsonMaster;
	
	/**
	 * 填充委托业主下拉(调账确认)
	 */
	public void queryOwnerCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=stock_ConfirmServiceImpl.queryOwnerCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 填充调整类型下拉(调账确认)
	 */
	public void queryPlanTypeCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=stock_ConfirmServiceImpl.queryPlanTypeCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					str);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取调账确认单头档列表
	public void getStockConfirmMList()
	{
		try 
			{
				PageBo pageBo=new PageBo(getStart(),getLimit());
				ExtListDataBo<Stock_ConfirmMModel> list  = stock_ConfirmServiceImpl.getStockConfirmMList(
				    super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
				    super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					str,pageBo);
				super.writeStr(covtObjectToJson(list));
			} catch (Exception e) 
			{
				e.printStackTrace();
				super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
			}
	}
	//获取调帐单明细
	public void getStockConfirmDList(){
		try{
			  PageBo pageBo=new PageBo(getStart(),getLimit());
			  List<Stock_ConfirmDModel> list = stock_ConfirmServiceImpl.getStockConfirmDList(
			      super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
			      super.getMdBdef_DefWorker().getWarehouseNo(),
				  super.getMdBdef_DefWorker().getWorkerOwner(),
				  str ,pageBo);
			  super.writeArray(list);
		}catch (Exception e) {
		      e.printStackTrace();
			  super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public void confirm()
	{
		try 
		{
			MsgRes msg=stock_ConfirmServiceImpl.tscConfirm(strJsonMaster);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public IStock_ConfirmService getStock_ConfirmServiceImpl() {
		return stock_ConfirmServiceImpl;
	}
	public void setStock_ConfirmServiceImpl(
			IStock_ConfirmService stock_ConfirmServiceImpl) {
		this.stock_ConfirmServiceImpl = stock_ConfirmServiceImpl;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getStrJsonMaster() {
		return strJsonMaster;
	}
	public void setStrJsonMaster(String strJsonMaster) {
		this.strJsonMaster = strJsonMaster;
	}
	
}
