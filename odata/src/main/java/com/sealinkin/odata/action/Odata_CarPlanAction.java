package com.sealinkin.odata.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_LoadproposeMModel;
import com.sealinkin.odata.service.IOdata_CarPlanService;
import com.sealinkin.stock.model.Stock_LabelDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;
import com.sealinkin.util.ExceptionUtil;

public class Odata_CarPlanAction extends CommAction{

	private static final long serialVersionUID = 1L;

	private IOdata_CarPlanService odata_CarPlanImpl;
	private String strLineNo;
	private String strShipperNo;
	private String strDeliverObj;
	private String strWheresql;
	private String strFlag;
	private String strLoadproposeNo;
	private String strJsonDetail1;
	
	/**
	 * 填充线路下拉
	 */
	public void queryLineNoCombo()
	{
		try 
		{
			List<ComboxBo> list=odata_CarPlanImpl.queryLineNoCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), "",strFlag);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 填充承运商下拉
	 */
	public void queryShipperNoCombo()
	{
		try {
			List<ComboxBo> list=odata_CarPlanImpl.queryShipperNoCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), strFlag);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取标签下的承运商下拉
	 */
	public void queryLabelShipperLineNo()
	{
		try 
		{
			List<ComboxBo> list = odata_CarPlanImpl.queryLabelShipperLineNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), strShipperNo);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取标签头档
	 */
	public void queryStockLabelM()
	{
		try 
		{
			ExtListDataBo<Stock_LabelMModel> list=odata_CarPlanImpl.queryStockLabelM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strLineNo, strShipperNo,strDeliverObj);
			super.writeObj(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取临时表中的数据
	 */
	public void queryStockLabelTmp(){
		try 
		{
			ExtListDataBo<Stock_LabelMModel> list=odata_CarPlanImpl.queryStockLabelTmp(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeObj(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 勾选中的单据保存进临时表
	 */
	public void saveLabelTmp(){
		try 
		{
			MsgRes msg=odata_CarPlanImpl.saveStockTmp(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strWheresql);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 删除取消勾选的单据
	 */
	public void deleteLabelTmp(){
		try 
		{
			MsgRes msg=odata_CarPlanImpl.deleteStockTmp(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strWheresql);
			super.writeObj(msg);
		} catch (Exception e) 
		{
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
			MsgRes msg=odata_CarPlanImpl.deleteTmp(
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
			List<Stock_LabelMModel> list = odata_CarPlanImpl.getItems(
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
			ExtListDataBo<Stock_LabelDModel> list=odata_CarPlanImpl.searchDetail(
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
			MsgRes msg=odata_CarPlanImpl.tscCloseCar(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strWheresql,
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo());
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取建议单号
	 */
	public void queryLoadproposeNo(){
		try {
			List<ComboxBo> list=odata_CarPlanImpl.queryLoadproposeNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取建议单头档
	 */
	public void queryLoadproposeM()
	{
		try 
		{
			ExtListDataBo<Odata_LoadproposeMModel> list=odata_CarPlanImpl.queryLoadProposeM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), strLineNo,strLoadproposeNo);
			super.writeObj(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取承运商下的线路
	 */
	public void queryLoadproposeLineCombo()
	{
		try 
		{
			List<ComboxBo> list = odata_CarPlanImpl.queryLoadproposeLineCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), strShipperNo);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取车辆下拉
	 */
	public void queryCarCombo(){
		try 
		{
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list=odata_CarPlanImpl.queryCarCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), strWheresql);
			super.writeArray(list);
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
			MsgRes msg=odata_CarPlanImpl.tscLoadCar(strJsonDetail1);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取配送对象下拉
	 */
	public void queryDeliverCombo(){
		try {
			List<ComboxBo> list=odata_CarPlanImpl.queryDeliverCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 检查配送对象是否与客户一至
	 */
	public void checkDeliverObj(){
		try {
			MsgRes msg=odata_CarPlanImpl.checkDeliverObj(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), strWheresql);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//加载派车单下拉
	public void queryCarPlanCombo(){
		try {
			List<ComboxBo> list=odata_CarPlanImpl.queryCarPlanCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 根据派车单获取配送对象
	 */
	public void queryDeliverObj()
	{
		try 
		{
			ExtListDataBo<Odata_LoadproposeMModel> list=odata_CarPlanImpl.queryDeliverObj(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), strWheresql);
			super.writeObj(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	public void setOdata_CarPlanImpl(IOdata_CarPlanService odata_CarPlanImpl) {
		this.odata_CarPlanImpl = odata_CarPlanImpl;
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
	
	
	
	
	
}
