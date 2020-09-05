package com.sealinkin.odata.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.service.IOdata_CarPlanForXzService;
import com.sealinkin.stock.model.Stock_LabelDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;
import com.sealinkin.util.ExceptionUtil;

public class Odata_CarPlanForXzAction extends CommAction{

	private static final long serialVersionUID = 1L;

	private IOdata_CarPlanForXzService odata_CarPlanForXzImpl;
	private String strLineNo;
	private String strShipperNo;
	private String strDeliverObj;
	private String strWheresql;
	private String strFlag;
	private String strLoadproposeNo;
	private String strJsonDetail1;
	private String strQuery;
	
	/**
	 * 根据车辆获取司机
	 */
	public void getDriverName(){
		try 
		{
			MsgRes msg=odata_CarPlanForXzImpl.getDriverName(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	//检查临时表数据是否存在
	public void labelTmpCheck(){
		try 
		{
			List<String> list = odata_CarPlanForXzImpl.labelTmpCheck(
					this.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery);	
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 删除临时表中的数据
	 */
	public void deleteTmp(){
		try 
		{
			MsgRes msg=odata_CarPlanForXzImpl.deleteTmp(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取勾选单据的品项明细
	 */
	public void getItems()
	{
		try 
		{
			List<Stock_LabelMModel> list = odata_CarPlanForXzImpl.getItems(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo()
					);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 查看明细
	 */
	public void searchDetail()
	{
		try 
		{
			ExtListDataBo<Stock_LabelDModel> list=odata_CarPlanForXzImpl.searchDetail(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strFlag,strWheresql);
			super.writeObj(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 装车建议单按扭(封车)
	 */
	public void closeCar(){
		try 
		{
			MsgRes msg=odata_CarPlanForXzImpl.tscCloseCar(
					strWheresql,
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					strQuery);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 整单封车
	 */
	public void loadCar()
	{
		try 
		{
			MsgRes msg=odata_CarPlanForXzImpl.tscLoadCar(strJsonDetail1);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//车辆代码下拉
	public void getCarNoQuery(){
		try 
		{
			List<ComboxBo> list = odata_CarPlanForXzImpl.getCarNoQuery(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public IOdata_CarPlanForXzService getOdata_CarPlanForXzImpl() {
		return odata_CarPlanForXzImpl;
	}

	public void setOdata_CarPlanForXzImpl(
			IOdata_CarPlanForXzService odata_CarPlanForXzImpl) {
		this.odata_CarPlanForXzImpl = odata_CarPlanForXzImpl;
	}

	public String getStrLineNo() {
		return strLineNo;
	}
	public void setStrLineNo(String strLineNo) {
		this.strLineNo = strLineNo;
	}

	public String getStrShipperNo() {
		return strShipperNo;
	}
	public void setStrShipperNo(String strShipperNo) {
		this.strShipperNo = strShipperNo;
	}

	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}

	public String getStrFlag() {
		return strFlag;
	}
	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}

	public String getStrLoadproposeNo() {
		return strLoadproposeNo;
	}
	public void setStrLoadproposeNo(String strLoadproposeNo) {
		this.strLoadproposeNo = strLoadproposeNo;
	}

	public String getStrJsonDetail1() {
		return strJsonDetail1;
	}
	public void setStrJsonDetail1(String strJsonDetail1) {
		this.strJsonDetail1 = strJsonDetail1;
	}

	public String getStrDeliverObj() {
		return strDeliverObj;
	}
	public void setStrDeliverObj(String strDeliverObj) {
		this.strDeliverObj = strDeliverObj;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
		
	
	
}
