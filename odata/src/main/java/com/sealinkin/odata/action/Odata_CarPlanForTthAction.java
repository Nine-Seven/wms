package com.sealinkin.odata.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.odata.service.IOdata_CarPlanForTthService;
import com.sealinkin.stock.model.Stock_LabelDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;
import com.sealinkin.util.ExceptionUtil;



public class Odata_CarPlanForTthAction extends CommAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IOdata_CarPlanForTthService odata_CarPlanForTthImpl;
	public String strQuery;
	public String lineNo;
	public String custNo;
	public String wheresql;
	public String str;
	
	//获取客户
	public void getCust(){
		try{
			List<ComboxBo> list = odata_CarPlanForTthImpl.getCust(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取线路
	public void getLineNoCombo(){
		try{
			List<ComboxBo> list = odata_CarPlanForTthImpl.getLineNoCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStrQuery());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取标签头档
	public void queryStockLabelM(){
		try 
		{
			ExtListDataBo<Stock_LabelMModel> list=odata_CarPlanForTthImpl.queryStockLabel(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					this.getLineNo(),this.getCustNo());
			super.writeObj(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	//获取明细
	public void searchDetail(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			System.out.println(wheresql);
			 
			ExtListDataBo<Stock_LabelDModel> list=odata_CarPlanForTthImpl.searchDetail(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					wheresql,pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//勾选中的单据保存进临时表
	public void saveLabelTmp(){
		try 
		{
			MsgRes msg=odata_CarPlanForTthImpl.saveStockTmp(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr());
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	// 装车建议单按扭(封车)
	public void closeCar(){
		try 
		{
			MsgRes msg=odata_CarPlanForTthImpl.tscCloseCar(this.getStr(),
					super.getMdBdef_DefWorker().getWorkSpaceNo());
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	public IOdata_CarPlanForTthService getOdata_CarPlanForTthImpl() {
		return odata_CarPlanForTthImpl;
	}
	public void setOdata_CarPlanForTthImpl(
			IOdata_CarPlanForTthService odata_CarPlanForTthImpl) {
		this.odata_CarPlanForTthImpl = odata_CarPlanForTthImpl;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getWheresql() {
		return wheresql;
	}

	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
}
