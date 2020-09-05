package com.sealinkin.odata.action;


import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.odata.model.Odata_ExpCancelDModel;
import com.sealinkin.odata.model.Odata_ExpCancelMModel;
import com.sealinkin.odata.service.IOdata_ExpCancelService;
import com.sealinkin.util.ExceptionUtil;

public class Odata_ExpCancelAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private IOdata_ExpCancelService odata_ExpCancelImpl;
    private String str;
    private String strOperateDate;
    private String strDetail;
    private String strFlag;
    
	//获取货主下拉内容
	public void getOwnerCombo()
	{
		try 
		{
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list = odata_ExpCancelImpl.getOwnerCombo(
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
	
	//获取病单来源下拉内容
		public void getStatusTextCombo()
		{
			try 
			{
				List<ComboxBo> list = new ArrayList<ComboxBo>();
				list = odata_ExpCancelImpl.getStatusTextCombo(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						str, strOperateDate);
				super.writeArray(list);
			} catch (Exception e) 
			{
				e.printStackTrace();
				super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
			}
		}
	//获取病单来源下拉内容
	public void getSuorceTypeCombo()
	{
		try 
		{
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list = odata_ExpCancelImpl.getSuorceTypeCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str,strFlag);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取撕票单号下拉内容
	public void getCancelNoCombo()
		{
			try 
			{
				List<ComboxBo> list = new ArrayList<ComboxBo>();
				list = odata_ExpCancelImpl.getCancelNoCombo(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						str,strOperateDate,strFlag);
				super.writeArray(list);
			} catch (Exception e) 
			{
				e.printStackTrace();
				super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
			}
		}
	//获取病单处理单头档
	public void getExpCancelMList()
	{
		try 
		{
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Odata_ExpCancelMModel> list  = odata_ExpCancelImpl.getExpCancelMList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str,strOperateDate,strFlag,pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取病单处理单明细
	public void getExpCancelDList()
	{
		try 
		{
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Odata_ExpCancelDModel> list  = odata_ExpCancelImpl.getExpCancelDList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str,strFlag,pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//上传	
	public void upLocad(){
		try {
			MsgRes msg=odata_ExpCancelImpl.upLocad(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strDetail,strFlag) ;
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//补拣	
	public void repeatLocate(){
		try {
			MsgRes msg=odata_ExpCancelImpl.repeatLocate(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strDetail,strFlag) ;
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
		
	public IOdata_ExpCancelService getOdata_ExpCancelImpl() {
		return odata_ExpCancelImpl;
	}
	public void setOdata_ExpCancelImpl(IOdata_ExpCancelService odata_ExpCancelImpl) {
		this.odata_ExpCancelImpl = odata_ExpCancelImpl;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getStrOperateDate() {
		return strOperateDate;
	}
	public void setStrOperateDate(String strOperateDate) {
		this.strOperateDate = strOperateDate;
	}
	public String getStrDetail() {
		return strDetail;
	}
	public void setStrDetail(String strDetail) {
		this.strDetail = strDetail;
	}
	public String getStrFlag() {
		return strFlag;
	}
	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}
}
