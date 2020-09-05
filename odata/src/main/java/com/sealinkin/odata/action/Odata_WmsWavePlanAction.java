package com.sealinkin.odata.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.odata.service.IOdata_WmsWavePlanService;
import com.sealinkin.util.ExceptionUtil;
import com.sealinkin.wms.model.Wms_OutwaveplanDModel;
import com.sealinkin.wms.model.Wms_OutwaveplanMModel;

public class Odata_WmsWavePlanAction extends CommAction {

	private static final long serialVersionUID = 1L;
	private IOdata_WmsWavePlanService odata_WmsWavePlanImpl;
	private String str;
	private String strQuery;
	private String strategyId;
	private String expType;
	private String strategyType;
	private String seqOrder;
	private String flag;          //排序标志，当flag=1时，向上移；当flag=-1时，向下移
	
	private String flagSet;			//标志位，区分试算配置的列表选项
	private String columnId;		
	
	private String strWheresql;
	
	//获取头档
	public void getWmsWavePlanM(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Wms_OutwaveplanMModel> wavePlanM =odata_WmsWavePlanImpl.getWmsWarePlanM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo()
					,pageBo,
					this.getStrQuery());
			super.writeObj(wavePlanM);		
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	//获取订单类型
	public void queryExpTypeCombo(){
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = odata_WmsWavePlanImpl.queryExpTypeCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	
	}
	
	//保存头档
	public void saveOrUpdatePlanM(){
		try {
			MsgRes msg = this.odata_WmsWavePlanImpl.saveOrUpdatePlanM(getStr());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(Boolean.valueOf(false), ExceptionUtil.getCacseMessage(e), ""));
		}
	}
	
	//删除头档
	public void deletePlanM(){

		try {
			System.out.println("测试: " + this.getStrategyId());
			MsgRes msg = this.odata_WmsWavePlanImpl.deletePlanM(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					this.getStrategyId());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(Boolean.valueOf(false), ExceptionUtil.getCacseMessage(e), ""));
		}
	}
	
	//获取明细
	public void getWmsWavePlanD(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Wms_OutwaveplanDModel> wavePlanM =odata_WmsWavePlanImpl.getWmsWavePlanD(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo()
					,pageBo,
					this.getStr());
			super.writeObj(wavePlanM);		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//获得试算配置规则id对应记录的详细信息 6-23
	public void getWmsWavePlanDTrialDetail(){
		try 
		{	
			System.out.println("测试: " + this.getFlagSet() + "  " + this.getColumnId());
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Wms_OutwaveplanDModel> wavePlanTrialDetail =odata_WmsWavePlanImpl.getWmsWavePlanDTrialDetail(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					pageBo, 
					this.getFlagSet(),
					this.getColumnId());
			super.writeObj(wavePlanTrialDetail);		
		} catch (Exception e){
			e.printStackTrace();
		}
	}	
	
	//获取试算配置列表  6-22
	public void getWmsWavePlanDTrial(){
		try 
		{		
			//PageBo pageBo = new PageBo(getStart(), getLimit());
			List<Wms_OutwaveplanDModel> wavePlanDTrial =odata_WmsWavePlanImpl.getWmsWavePlanDTrial();
			//super.writeObj(wavePlanDTrial);	
			super.writeArray(wavePlanDTrial);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//获取试算配置列表对应下拉框  6-22
	public void getWmsWavePlanDTrialSelect(){
    	try 
		{
    		System.out.println("测试: " + this.getFlagSet());
			List<ComboxBo> list = odata_WmsWavePlanImpl.getWmsWavePlanDTrialSelect(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getFlagSet());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }	
	
	//填充区域限制下拉控件 8-6添加  
	public void queryCdefAreaCombo()
	{
		try 
		{
			System.out.println("strWheresql:"+strWheresql);
			List<ComboxBo> list = odata_WmsWavePlanImpl.queryCdefAreaCombo(
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strWheresql);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}	
	
	//保存明细
	public void saveOrUpdatePlanD(){
		try {
			MsgRes msg = this.odata_WmsWavePlanImpl.saveOrUpdatePlanD(getStr());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(Boolean.valueOf(false), ExceptionUtil.getCacseMessage(e), ""));
		}	
	}
	
	//删除明细
	public void deletePlanD(){
		try {
			System.out.println("值: " +this.getStrategyType()+"  " 
					+this.getStrategyId()+" "+ this.getSeqOrder());
			MsgRes msg = this.odata_WmsWavePlanImpl.deletePlanD(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					this.getStrategyId(),
					//this.getExpType(),
					this.getStrategyType(),
					this.getSeqOrder());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(Boolean.valueOf(false), ExceptionUtil.getCacseMessage(e), ""));
		}	
	}
	
	//排序
	public void seqencing(){
		try {
			System.out.println("值测试: " + this.getStrategyType() + "  " +
					this.getStrategyId() + "  " + this.getSeqOrder() + "  " + this.getFlag());
			MsgRes msg = this.odata_WmsWavePlanImpl.seqencing(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					this.getStrategyId(),
					//this.getExpType(),
					this.getStrategyType(),
					this.getSeqOrder(),
					this.getFlag());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(Boolean.valueOf(false), ExceptionUtil.getCacseMessage(e), ""));
		}
	}
	
	//判断是否可分播
	public void ifDivede(){
		try {
			MsgRes msg = this.odata_WmsWavePlanImpl.ifDivede(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					this.getExpType());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(Boolean.valueOf(false), ExceptionUtil.getCacseMessage(e), ""));
		}
	
	}
	
	//获取策略名称
	public void getStrategy(){

		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = odata_WmsWavePlanImpl.getStrategy(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getStr());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	
	
	}
	public IOdata_WmsWavePlanService getOdata_WmsWavePlanImpl() {
		return odata_WmsWavePlanImpl;
	}

	public void setOdata_WmsWavePlanImpl(
			IOdata_WmsWavePlanService odata_WmsWavePlanImpl) {
		this.odata_WmsWavePlanImpl = odata_WmsWavePlanImpl;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(String strategyId) {
		this.strategyId = strategyId;
	}

	public String getExpType() {
		return expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	public String getStrategyType() {
		return strategyType;
	}

	public void setStrategyType(String strategyType) {
		this.strategyType = strategyType;
	}

	public String getSeqOrder() {
		return seqOrder;
	}

	public void setSeqOrder(String seqOrder) {
		this.seqOrder = seqOrder;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getFlagSet() {
		return flagSet;
	}

	public void setFlagSet(String flagSet) {
		this.flagSet = flagSet;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getStrWheresql() {
		return strWheresql;
	}

	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}	
	
}
