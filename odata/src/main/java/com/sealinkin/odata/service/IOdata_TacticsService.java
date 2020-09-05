package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.wms.model.Wms_OutorderFlowModel;
import com.sealinkin.wms.model.Wms_OutorderModel;
import com.sealinkin.wms.model.Wms_OutwaveplanMModel;
import com.sealinkin.wms.model.Wms_OwnerOutorderModel;
import com.sealinkin.wms.model.Wms_strategyDModel;
import com.sealinkin.wms.model.Wms_strategyMModel;

public interface IOdata_TacticsService {
	////////////////////////////////////////系统级出货策略配置///////////////////////////////////////////////////

	/**
	 * 获取策略列表主档
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Wms_OutorderModel> queryWmsOutOrderList(PageBo poPageBo,String enterpriseNo,String exptype ,String industryflag, String strQuery)throws Exception;
	
	/**
	 * 保存或修改
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public MsgRes saveOrUpdateTactics(String strJsonDetail)throws Exception;
	//获取UI出货单别类型下拉
	public List<ComboxBo> getExptypeForUI(String strQuery) throws Exception;
	//获取UI出货单别类型下拉
	public List<ComboxBo> getIndustryFlagForUI(String strQuery) throws Exception;
	/**
	 * 获取策略配置（查看）
	 * @param strExpType
	 * @param strExpType2 
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Wms_OutorderFlowModel> queryWmsOutOrderFlow(String enterpriseNo,String strExpType,String strType )throws Exception;
	
	
	public MsgRes saveTactics(String strJsonDetail)throws Exception;
	
	public MsgRes deleteTactics(String strExpType,String enterpriseNo)throws Exception;
	
	public MsgRes checkExpType(String enterpriseNo,String warehouseNo, 
			String strExpType,String wheresql,String ownerNo)throws Exception;

	public List<Wms_OutwaveplanMModel> queryStrategy(String enterpriseNo,String strExpType )throws Exception;

	//策略类型信息列表
	public ExtListDataBo<Wms_OutorderModel> getStrategyTypeList(String strQuery,PageBo poPageBo )throws Exception;
	//获取策略ID下拉
	public List<ComboxBo> getStrategyidCom(String strEnterpriseNo,String strategyFlag,String strQuery ) throws Exception;
	//策略头档信息列表
	public ExtListDataBo<Wms_strategyMModel> getStrategyMList(String strEnterpriseNo,String strategyFlag,String strQuery,PageBo poPageBo )throws Exception;
	//策略明细信息列表
	public ExtListDataBo<Wms_strategyDModel> getStrategyDList(String strEnterpriseNo,String strategyFlag,String strQuery,PageBo poPageBo )throws Exception;

////////////////////////////////////////仓别级出货策略配置///////////////////////////////////////////////////
	//获取仓别单据主档
	public ExtListDataBo<Wms_OutorderModel> queryWmsWarehouseOutorderList(
			PageBo poPageBo,String enterpriseNo,String warehouseNo)throws Exception;
	//获取仓别工作流配置（查看时）
	public ExtListDataBo<Wms_OutorderFlowModel> queryWmsWarehouseOutOrderFlow(
			String enterpriseNo,String warehouseNo,
			String strExpType,String strType,String ownerNo )throws Exception;
	/**
	 * 保存或新增策略
	 */
	public MsgRes saveOrUpdateWasehouseTactics(String strJsonDetail)throws Exception;
	//保存仓别配置工作流
	public MsgRes saveWasehouseTactics(String strJsonDetail)throws Exception;

	
	
////////////////////////////////////////货主级出货策略配置///////////////////////////////////////////////////
	//获取货主单据主档
	public ExtListDataBo<Wms_OwnerOutorderModel> queryWmsOwnerOutOrderList(
			PageBo poPageBo,String enterpriseNo,String warehouseNo)throws Exception;
	//获取货主工作流配置（查看时）
	public ExtListDataBo<Wms_OutorderFlowModel> queryWmsOwnerOutOrderFlow(
			String enterpriseNo,
			String strExpType,String strType,String ownerNo )throws Exception;
	
	/**
	 * 保存或新增策略
	 */
	public MsgRes saveOrUpdateOwnerTactics(String strJsonDetail)throws Exception;
	//保存货主配置工作流
	public MsgRes saveOwnerTactics(String strJsonDetail)throws Exception;

	
}
