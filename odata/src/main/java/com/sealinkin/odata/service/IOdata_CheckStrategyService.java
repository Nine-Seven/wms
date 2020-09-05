package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.odata.model.Odata_WmsCheckStrategyModel;


public interface IOdata_CheckStrategyService {
	//获取策略头档列表
	public ExtListDataBo<Odata_WmsCheckStrategyModel> getCheckStrategyList(
			String strQuery,PageBo pageBo)throws Exception;
	//获取UI策略类型下拉
	public List<ComboxBo> getCheckTypeForUI(String strQuery) throws Exception;
	//获取UI复核级别下拉
	public List<ComboxBo> getCheckLevelForUI(String strQuery) throws Exception;
	//获取UI策略id下拉
	public List<ComboxBo> getCheckStrategyIdAndName(String strQuery,String str) throws Exception;
	//保存策略头档
	public MsgRes saveStrategy(String strQuery)throws Exception;
	//删除策略头档
	public MsgRes deleteStrategy(String strQuery)throws Exception;
	
	
	

}