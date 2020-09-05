package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.wms.model.Wms_OutwaveplanDModel;
import com.sealinkin.wms.model.Wms_OutwaveplanMModel;

public interface IOdata_WmsWavePlanService {

	ExtListDataBo<Wms_OutwaveplanMModel> getWmsWarePlanM(
			String currEnterpriseNo, PageBo pageBo, String str) throws Exception;

	List<ComboxBo> queryExpTypeCombo(String currEnterpriseNo) throws Exception;

	MsgRes saveOrUpdatePlanM(String str) throws Exception;

	MsgRes deletePlanM(String enterpriseNo, String batchStrategyId) throws Exception;

	ExtListDataBo<Wms_OutwaveplanDModel> getWmsWavePlanD(
			String currEnterpriseNo, PageBo pageBo, String str) throws Exception;
	
	//获得试算配置规则id对应记录的详细信息 6-23
	ExtListDataBo<Wms_OutwaveplanDModel> getWmsWavePlanDTrialDetail(
			String currEnterpriseNo, PageBo pageBo, String flagSet,String columnId) throws Exception;
	
	//获得试算配置列表 6-22
	List<Wms_OutwaveplanDModel> getWmsWavePlanDTrial() throws Exception;
	
	//获得试算配置列表对应下拉框 6-22
	List<ComboxBo> getWmsWavePlanDTrialSelect(String enterpriseNo,String flag);
	
	//填充限制区域下拉控件   8-6添加  
	public List<ComboxBo> queryCdefAreaCombo(
			String wareHouseNo,String strWheresql)throws Exception;

	MsgRes saveOrUpdatePlanD(String str) throws Exception;

	MsgRes deletePlanD(String enterpriseNo, String strategyId, 
			String strategyType, String seqOrder) throws Exception;  //String expType,

	MsgRes seqencing(String enterpriseNo, String strategyId, 
			String strategyType, String seqOrder, String flag) throws Exception;  //String expType,

	MsgRes ifDivede(String enterpriseNo,String strategyType) throws Exception;

	List<ComboxBo> getStrategy(String currEnterpriseNo,
			String str)throws Exception;

}
