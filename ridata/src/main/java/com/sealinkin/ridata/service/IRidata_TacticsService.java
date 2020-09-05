package com.sealinkin.ridata.service;

import java.util.List;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.wms.model.Wms_OutorderFlowModel;
import com.sealinkin.wms.model.Wms_RiorderModel;
import com.sealinkin.wms.model.Wms_OwnerRiorderModel;
import com.sealinkin.wms.model.Wms_OutwaveplanMModel;
import com.sealinkin.wms.model.Wms_OwnerOutorderModel;

public interface IRidata_TacticsService {
	////////////////////////////////////////系统级出货策略配置///////////////////////////////////////////////////

	/**
	 * 获取策略列表主档
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Wms_RiorderModel> queryWmsRiOrderList(PageBo poPageBo,String enterpriseNo)throws Exception;
	
	/**
	 * 保存或修改
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public MsgRes saveOrUpdateTactics(String strJsonDetail)throws Exception;
	
	/**
	 * 获取策略配置（查看）
	 * @param strExpType
	 * @param strExpType2 
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Wms_OutorderFlowModel> queryWmsOutOrderFlow(String enterpriseNo,String strExpType,String strType )throws Exception;
	
	
	public MsgRes saveTactics(String strJsonDetail)throws Exception;
	
	public MsgRes deleteTactics(String levelFlag,String wheresql,String enterpriseNo)throws Exception;
	
	public MsgRes checkExpType(String enterpriseNo,String warehouseNo, 
			String strExpType,String strQuality,String strPriority,String wheresql,String ownerNo)throws Exception;

	public List<Wms_OutwaveplanMModel> queryStrategy(String enterpriseNo,String strExpType )throws Exception;

////////////////////////////////////////仓别级出货策略配置///////////////////////////////////////////////////
	//获取仓别单据主档
	public ExtListDataBo<Wms_RiorderModel> queryWmsWarehouseOutorderList(
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
	public ExtListDataBo<Wms_OwnerRiorderModel> queryWmsOwnerOutOrderList(
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
