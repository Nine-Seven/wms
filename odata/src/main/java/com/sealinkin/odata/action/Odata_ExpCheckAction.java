package com.sealinkin.odata.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_ExpensesListModel;
import com.sealinkin.odata.model.Odata_ExpMModel;
import com.sealinkin.odata.service.IOdata_ExpCheckService;
import com.sealinkin.util.ExceptionUtil;

public class Odata_ExpCheckAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private IOdata_ExpCheckService odata_ExpCheckImpl;
	private String strQuery;
	private String strWheresql;
	private String beginDate;
	private String endDate;
	private String strSourceexpNo;
	private String strTableName;
	
	
	
	//获得出库审核信息列表  hj
	public void getOdata_NoCkeckOrderList(){
		try {
				PageBo pageBo = new PageBo(getStart(), getLimit());
				ExtListDataBo<Odata_ExpMModel> list = odata_ExpCheckImpl.getOdata_NoCkeckOrderList(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						super.getMdBdef_DefWorker().getWorkerOwner(),
						strQuery, 
						pageBo);
				super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//取出货申请单下拉列表
	public void getOrderNumberList()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = odata_ExpCheckImpl.getOrderNumberList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(), 
					strWheresql, 
					strQuery,
					strTableName);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}	
	
	//取客户编码下拉列表
	public void getCustomNumberList()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = odata_ExpCheckImpl.getCustomNumberList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(), 
					strWheresql, 
					strQuery,
					strTableName);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}	
	
	//获得出货单商品详细信息列表
	public void getOrder_GoodsDetailList(){
		try {
				PageBo pageBo = new PageBo(getStart(), getLimit());
				ExtListDataBo<Odata_ExpMModel> list = odata_ExpCheckImpl.getOdata_NoCkeckOrderList(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						super.getMdBdef_DefWorker().getWorkerOwner(),
						strQuery, 
						pageBo);
				super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获得货主商品编码下拉列表
	public void getCustomGoodsNumberList()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = odata_ExpCheckImpl.getCustomGoodsNumberList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(), 
					strWheresql, 
					strQuery);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}		
	
	//获得货主商品编码下拉列表
	public void getStatusList()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = odata_ExpCheckImpl.getStatusList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(), 
					strWheresql, 
					strQuery);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//审核操作
	public void checkOrder(){
		try {
			MsgRes msg = odata_ExpCheckImpl.checkOrder(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(), 
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getRgstName(),
					strSourceexpNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	
	
	
	public String getStrWheresql() {
		return strWheresql;
	}

	public String getStrSourceexpNo() {
		return strSourceexpNo;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public IOdata_ExpCheckService getOdata_ExpCheckImpl() {
		return odata_ExpCheckImpl;
	}

	public void setOdata_ExpCheckImpl(IOdata_ExpCheckService odata_ExpCheckImpl) {
		this.odata_ExpCheckImpl = odata_ExpCheckImpl;
	}
	
	public void setStrSourceexpNo(String strSourceexpNo) {
		this.strSourceexpNo = strSourceexpNo;
	}

	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}

	public String getStrTableName() {
		return strTableName;
	}

	public void setStrTableName(String strTableName) {
		this.strTableName = strTableName;
	}
	
	
	
}
































