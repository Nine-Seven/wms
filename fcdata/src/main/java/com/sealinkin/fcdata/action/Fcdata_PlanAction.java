
package com.sealinkin.fcdata.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefarticleModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.fcdata.model.Fcdata_PlanDModel;
import com.sealinkin.fcdata.model.Fcdata_PlanMModel;
import com.sealinkin.fcdata.service.IFcdata_PlanService;
import com.sealinkin.util.ExceptionUtil;

//盘点手建计划单Action
public class Fcdata_PlanAction extends CommAction{

	private static final long serialVersionUID = 1L;

	private IFcdata_PlanService fcdata_PlanImpl;
	private String strSql;
	private String strQuery;
	private String strGroupNo;
	private String[] wheresql;
	private String objPlanM;
	private String objPlanD1;
	private String strPlanNo;
	private String strJson;
	private String str;
	private String strArticleNo;
	private String strWheresql;
	private String strOwnerNo;
	private String strFcdataType;
	
	
	public String getStrGroupNo() {
		return strGroupNo;
	}
	public void setStrGroupNo(String strGroupNo) {
		this.strGroupNo = strGroupNo;
	}
	public String[] getWheresql() {
		return wheresql;
	}
	public void setWheresql(String[] wheresql) {
		this.wheresql = wheresql;
	}
	public String getObjPlanM() {
		return objPlanM;
	}
	public void setObjPlanM(String objPlanM) {
		this.objPlanM = objPlanM;
	}
	public String getObjPlanD1() {
		return objPlanD1;
	}
	public void setObjPlanD1(String objPlanD1) {
		this.objPlanD1 = objPlanD1;
	}
	public void setfcdata_PlanImpl(IFcdata_PlanService fcdataPlanImpl) {
		fcdata_PlanImpl = fcdataPlanImpl;
	}
	
	
	public IFcdata_PlanService getFcdata_PlanImpl() {
		return fcdata_PlanImpl;
	}
	public void setFcdata_PlanImpl(IFcdata_PlanService fcdata_PlanImpl) {
		this.fcdata_PlanImpl = fcdata_PlanImpl;
	}
	public String getStrSql() {
		return strSql;
	}
	public void setStrSql(String strSql) {
		this.strSql = strSql;
	}
	
	public String getStrPlanNo() {
		return strPlanNo;
	}
	public void setStrPlanNo(String strPlanNo) {
		this.strPlanNo = strPlanNo;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getStrJson() {
		return strJson;
	}
	public void setStrJson(String strJson) {
		this.strJson = strJson;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getStrArticleNo() {
		return strArticleNo;
	}
	public void setStrArticleNo(String strArticleNo) {
		this.strArticleNo = strArticleNo;
	}
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
	public String getStrOwnerNo() {
		return strOwnerNo;
	}
	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}
	public String getStrFcdataType() {
		return strFcdataType;
	}
	public void setStrFcdataType(String strFcdataType) {
		this.strFcdataType = strFcdataType;
	}
	/**
	 * 获得盘点计划单头档
	 * @throws Exception
	 */
	public void getPlanMList()
	{
		try {
			System.out.println("str: " + this.getStr());
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Fcdata_PlanMModel> list=fcdata_PlanImpl.getPlanM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str,strOwnerNo,
					getStrQuery(), 
					pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得盘点计划单明细
	 * @throws Exception
	 */
	public void getPlanDList()
	{
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Fcdata_PlanDModel> list=fcdata_PlanImpl.getFcdataPlanD(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					wheresql, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存盘点计划单
	 * @throws Exception
	 */
	public void saveFcdata_Plan()
	{
		try {
			MsgRes msg=fcdata_PlanImpl.saveFcdata_Plan(objPlanM,objPlanD1);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据保存失败！
		}
	}
	
	/**
	 * 删除盘点计划单
	 * @throws Exception
	 */
	public void deleteFcdata_Plan()
	{
		try {
			MsgRes msg=fcdata_PlanImpl.deleteFcdata_Plan(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strPlanNo);
			super.writeObj(msg);//数据删除成功！
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据删除失败！
		}
	}
	
	/**
	 * 手建盘点计划单》定位
	 * @throws Exception
	 */
	public void sendFcdataPlan()
	{
		try {
			MsgRes msg=fcdata_PlanImpl.sendFcdataPlan(objPlanM);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//定位失败！
		}
	}
	
	/*
	 * 结案
	 */
	public void sendClosePlan()
	{
		try {
			MsgRes msg=fcdata_PlanImpl.sendClosePlan(objPlanM);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//操作失败！
		}
	}
	//取消订单
	public void closeOrder(){
		try {
			MsgRes msg = fcdata_PlanImpl.closeOrder(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					strPlanNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
     * 储位下拉
     */
	public void getCdef_DefCellCombo() 
	{
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=fcdata_PlanImpl.getCdef_DefCellCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStrJson(),
					strWheresql,strOwnerNo);
			super.writeArray(list);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
     /**
     * 仓库下拉
     */
	public void getCdef_DefWareCombo() 
	{
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=fcdata_PlanImpl.getCdef_DefWareCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr(),strWheresql,strOwnerNo);
			super.writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
    /**
     * 储区下拉
     */
	public void getCdef_DefAreaCombo() 
	{
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=fcdata_PlanImpl.getCdef_DefAreaCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr(),strWheresql, strOwnerNo);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
    /**
     * 通道下拉
     */
	public void getCdef_DefStockCombo() {
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=fcdata_PlanImpl.getCdef_DefStockCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr(),strWheresql, strOwnerNo);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 /**
     * 通过artilce_no获取商品类别名称和编码
     */
	public void getGroupByArticle() {
		try{
			List<Bdef_DefarticleModel> list=fcdata_PlanImpl.getGroupByArticle(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strArticleNo);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取商品下拉
	 */
	public void queryBdefArticleCombo()
	{
		try 
		{
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list = fcdata_PlanImpl.query_BdefArticleCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(), 
					strGroupNo, 
					strWheresql);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获取状态
	public void getStatus(){
		try {
			List<String> statusList = new ArrayList<String>();
			List<String> list = fcdata_PlanImpl.getStatus(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(), strPlanNo, 0, 100);
			if(list.size()!=0 && list.get(0).equals("10"))
			{
				statusList.add("1");
			}else 
			{
				statusList.add("2");
			}
			super.writeArray(statusList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
