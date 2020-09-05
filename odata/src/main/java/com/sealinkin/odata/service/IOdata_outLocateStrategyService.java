package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.odata.model.odata_WmsOutlocateStrategyDModel;
import com.sealinkin.odata.model.odata_WmsOutlocateStrategyMModel;

public interface IOdata_outLocateStrategyService {
	//获取策略头档列表
	public ExtListDataBo<odata_WmsOutlocateStrategyMModel> getOutLocateStrategyMList(
			String strQuery,PageBo pageBo)throws Exception;
	//获取策略明细列表
	//public ExtListDataBo<odata_WmsOutlocateStrategyDModel> getOutLocateStrategyDList(
				//String strQuery,PageBo pageBo)throws Exception;
	         
	//获取UI策略名下拉
	public List<ComboxBo> getStrategyNameForUI(String strQuery) throws Exception;
	//获取UI默认标识下拉
	public List<ComboxBo> getDefaultFlagForUI(String strQuery) throws Exception;
	//获取UI策略id下拉
	public List<ComboxBo> getStrategyId(String strQuery,String str) throws Exception;
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
	

	ExtListDataBo<odata_WmsOutlocateStrategyDModel> getOutLocateStrategyDList(
			String strQuery, PageBo pageBo) throws Exception;
	//获取策略明细列表
	ExtListDataBo<odata_WmsOutlocateStrategyDModel> getOutLocateStrategyDList1(
			String strQuery, PageBo pageBo) throws Exception;
	

	

	
	

}
