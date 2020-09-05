package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_WmsTaskallotRuleMModel;
import com.sealinkin.bdef.model.Bdef_WmsTaskallotRuleModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

/** 模块名称：任务切分规则配置
 * 模块编码：I201
 * 创建：huangb 20160612
 */
public interface IBdef_WmsTaskallotRuleMService {
    
	//获取任务切分规则头档数据
	public ExtListDataBo<Bdef_WmsTaskallotRuleMModel> getMasterInfo(
			String strEnterpriseNo, String strQuery, PageBo pageBo)
			throws Exception;
	
	//获取任务切分规则明细数据
	public ExtListDataBo<Bdef_WmsTaskallotRuleModel> getDetailInfo(
			String strQuery, PageBo pageBo)
			throws Exception;
	
	//获取波次规则下拉框
	public List<ComboxBo> getWaveRuleForUI(String strEnterpriseNo, String strQuery) throws Exception;
		
	//保存任务切分规则头档
	public MsgRes saveWmsTaskallotRuleM(String str) throws Exception;
    
	//保存任务切分规则明细
	public MsgRes saveWmsTaskallotRuleD(String str) throws Exception;
    
	//删除任务切分规则头档
	public MsgRes deleteWmsTaskallotRuleM(String enterpriseNo, String queryStr) throws Exception;
	
	//删除任务切分规则明细
	public MsgRes deleteWmsTaskallotRuleD(String strQuery) throws Exception;
    
	
	
}
















































