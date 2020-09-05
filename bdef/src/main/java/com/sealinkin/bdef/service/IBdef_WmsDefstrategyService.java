package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_WmsDefstrategyDModel;
import com.sealinkin.bdef.model.Bdef_WmsDefstrategyModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface IBdef_WmsDefstrategyService {
	//获取策略头档列表
	public ExtListDataBo<Bdef_WmsDefstrategyModel> getWmsDefstrategyList(
			String strQuery,PageBo pageBo)throws Exception;
	//获取策略明细列表
	public ExtListDataBo<Bdef_WmsDefstrategyDModel> getWmsDefstrategyDList(
				String strQuery,PageBo pageBo)throws Exception;
	//获取UI策略类型下拉
	public List<ComboxBo> getStrategyTypeForUI(String strQuery) throws Exception;
	//获取UI默认标识下拉
	public List<ComboxBo> getDefaultFlagForUI(String strQuery) throws Exception;
	//获取UI策略id/名称下拉
	public List<ComboxBo> getStrategyIdOrName(String strQuery,String str) throws Exception;
	//保存策略头档
	public MsgRes saveStrategy(String strQuery)throws Exception;
	//删除策略头档
	public MsgRes deleteStrategy(String strQuery)throws Exception;
	//获取策略明细窗口规则ID下拉
	public List<ComboxBo> getRuleIdQuery(String strQuery,String str) throws Exception;
	//保存策略明细
	public MsgRes saveStrategyD(String strQuery)throws Exception;
	//删除策略明细
	public MsgRes deleteStrategyD(String strQuery)throws Exception;
	//策略明细上移、下移
	public MsgRes ruleOrderMove(String strStrategyType,
		String strStrategyId, String strRuleOrder, String strRuleOrderChoice,String flag)throws Exception;

}
