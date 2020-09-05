package com.sealinkin.odata.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.odata.service.IOdata_TacticsService;
import com.sealinkin.util.ExceptionUtil;
import com.sealinkin.wms.model.Wms_OutorderFlowModel;
import com.sealinkin.wms.model.Wms_OutorderModel;
import com.sealinkin.wms.model.Wms_OutwaveplanMModel;
import com.sealinkin.wms.model.Wms_OwnerOutorderModel;
import com.sealinkin.wms.model.Wms_strategyDModel;
import com.sealinkin.wms.model.Wms_strategyMModel;

public class Odata_TacticsAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private IOdata_TacticsService odata_TacticsImpl;
	private String strJsonDetail;
	private String strExpType;
	private String strType;
	private String wheresql;
	private String ownerNo;
	private String strQuery;
	private String strategyFlag;
	private String industryflag;
	
	////////////////////////////////////////系统级出货策略配置///////////////////////////////////////////////////
	//获取主档


	public IOdata_TacticsService getOdata_TacticsImpl() {
		return odata_TacticsImpl;
	}

	public void queryWmsOutOrderList(){
		try {
			PageBo poPageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Wms_OutorderModel> list=
					odata_TacticsImpl.queryWmsOutOrderList(poPageBo,
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),strExpType,industryflag,strQuery);
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//出货单别类型
	public void getExptypeForUI(){
		try 
		{
			List<ComboxBo> list = odata_TacticsImpl.getExptypeForUI(
					strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//行业标识
	public void getIndustryFlagForUI(){
		try 
		{
			List<ComboxBo> list = odata_TacticsImpl.getIndustryFlagForUI(
					strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 保存或新增策略
	 */
	public void saveOrUpdateTactics(){
		try {
			MsgRes msg = odata_TacticsImpl.saveOrUpdateTactics(strJsonDetail);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据保存失败！
		}
	}
	
	//获取工作流配置（查看时）
	public void queryWmsOutOrderFlow(){
		try {
			ExtListDataBo<Wms_OutorderFlowModel> list=odata_TacticsImpl.queryWmsOutOrderFlow(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strExpType,strType);
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据保存失败！
		}
	}
	
	//保存工作流
	public void saveTactics(){
		try {
			MsgRes msg=odata_TacticsImpl.saveTactics(strJsonDetail);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteTactics(){
		try {
			MsgRes msg=odata_TacticsImpl.deleteTactics(strExpType,super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//校验该单据是否存在（三种级别公用）
	public void checkExpType(){
		try {
			MsgRes msg=odata_TacticsImpl.checkExpType(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strExpType,wheresql,ownerNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//根据出货单别获取波次规则号（三种级别公用）
	public void queryStrategy(){
		try {
			List<Wms_OutwaveplanMModel> list=odata_TacticsImpl.queryStrategy(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strExpType);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据保存失败！
		}
	}
	//策略类型信息列表
	public void getStrategyTypeList(){
		try {
			PageBo poPageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Wms_OutorderModel> list=
					odata_TacticsImpl.getStrategyTypeList(strQuery,poPageBo);
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取策略ID下拉
	public void getStrategyidCom(){
		try 
		{
			List<ComboxBo> list = odata_TacticsImpl.getStrategyidCom(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strategyFlag,strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//策略头档信息列表
	public void getStrategyMList(){
		try {
			PageBo poPageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Wms_strategyMModel> list=
					odata_TacticsImpl.getStrategyMList(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						strategyFlag,strQuery,poPageBo);
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//策略明细信息列表
	public void getStrategyDList(){
		try {
			PageBo poPageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Wms_strategyDModel> list=
					odata_TacticsImpl.getStrategyDList(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						strategyFlag,strQuery,poPageBo);
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
////////////////////////////////////////仓别级出货策略配置///////////////////////////////////////////////////
	
	//获取仓别单据主档
	public void queryWmsWarehouseOutorderList(){
		try {
			PageBo poPageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Wms_OutorderModel> list=
					odata_TacticsImpl.queryWmsWarehouseOutorderList(poPageBo,
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//获取仓别工作流配置（查看时）
	public void queryWmsWarehouseOutOrderFlow(){
		try {
			ExtListDataBo<Wms_OutorderFlowModel> list=odata_TacticsImpl.queryWmsWarehouseOutOrderFlow(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strExpType,strType,ownerNo);
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据获取失败！
		}
	}
	
	/**
	 * 保存或新增策略
	 */
	public void saveOrUpdateWasehouseTactics(){
		try {
			MsgRes msg = odata_TacticsImpl.saveOrUpdateWasehouseTactics(strJsonDetail);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据保存失败！
		}
	}
	
	//保存仓别配置工作流
	public void saveWasehouseTactics(){
		try {
			MsgRes msg=odata_TacticsImpl.saveWasehouseTactics(strJsonDetail);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
////////////////////////////////////////货主级出货策略配置///////////////////////////////////////////////////
	//获取仓别货主单据主档
	public void queryWmsOwnerOutOrderList(){
		try {
			PageBo poPageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Wms_OwnerOutorderModel> list=
					odata_TacticsImpl.queryWmsOwnerOutOrderList(poPageBo,
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取货主工作流配置（查看时）
	public void queryWmsOwnerOutOrderFlow(){
		try {
			ExtListDataBo<Wms_OutorderFlowModel> list=odata_TacticsImpl.queryWmsOwnerOutOrderFlow(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strExpType,strType,ownerNo);
			super.writeObj(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据获取失败！
		}
	}
	
	/**
	 * 保存或新增货主单据策略
	 */
	public void saveOrUpdateOwnerTactics(){
		try {
			MsgRes msg = odata_TacticsImpl.saveOrUpdateOwnerTactics(strJsonDetail);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据保存失败！
		}
	}
	
	//保存货主配置工作流
	public void saveOwnerTactics(){
		try {
			MsgRes msg=odata_TacticsImpl.saveOwnerTactics(strJsonDetail);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getWheresql() {
		return wheresql;
	}

	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}

	public void setOdata_TacticsImpl(IOdata_TacticsService odata_TacticsImpl) {
		this.odata_TacticsImpl = odata_TacticsImpl;
	}

	public String getStrJsonDetail() {
		return strJsonDetail;
	}

	public void setStrJsonDetail(String strJsonDetail) {
		this.strJsonDetail = strJsonDetail;
	}

	public String getStrExpType() {
		return strExpType;
	}

	public void setStrExpType(String strExpType) {
		this.strExpType = strExpType;
	}

	public String getStrType() {
		return strType;
	}

	public void setStrType(String strType) {
		this.strType = strType;
	}

	public String getOwnerNo() {
		return ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getStrategyFlag() {
		return strategyFlag;
	}

	public void setStrategyFlag(String strategyFlag) {
		this.strategyFlag = strategyFlag;
	}
	public String getIndustryflag() {
		return industryflag;
	}

	public void setIndustryflag(String industryflag) {
		this.industryflag = industryflag;
	}
}
