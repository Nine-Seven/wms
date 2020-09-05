package com.sealinkin.bdef.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Bdef_WmsTaskallotRuleMModel;
import com.sealinkin.bdef.model.Bdef_WmsTaskallotRuleModel;
import com.sealinkin.bdef.po.Bdef_WmsTaskallotRule;
import com.sealinkin.bdef.po.Bdef_WmsTaskallotRuleM;
import com.sealinkin.bdef.service.IBdef_WmsTaskallotRuleMService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

/** 模块名称：任务切分规则配置
 * 模块编码：I201
 * 创建：huangb 20160612
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class Bdef_WmsTaskallotRuleMImpl implements IBdef_WmsTaskallotRuleMService{
		
	private IGenericManager genDao;
		
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
    
	/**
	 * 获取任务切分规则头档数据
	 * 增加onedeliveronepickText(同一个配送对象一次性拣货：0-不一次拣货；1-要求一次拣货) huangb 20160801
	 */
	@Override
	public ExtListDataBo<Bdef_WmsTaskallotRuleMModel> getMasterInfo(
			String strEnterpriseNo, String strQuery, PageBo pageBo)
			throws Exception {
		
	    	String strSql="";
	    	strSql = " select wtr.enterprise_no, wtr.task_ruleid, wtr.rule_type, wtr.para_value, wtr.memo, " +
                     "        wtr.rgst_name, wtr.rgst_date, wtr.task_type, wtr.print_type, wtr.onedeliveronepick, wtr.task_get_type, " +
                     "        '[' || wtr.task_ruleid || ']' || wtr.memo as taskRuleidText, " +
                     "        '[' || wtr.rule_type || ']' || f_get_fieldtext('WMS_TASKALLOT_RULE_M', 'RULE_TYPE', wtr.rule_type) ruleTypeText, " +
                     "        (case when nvl(wtr.task_type,'N') <> 'N' then " +
                     "        '[' || wtr.task_type || ']' || f_get_fieldtext('ODATA_OUTSTOCK_M', 'TASK_TYPE', wtr.task_type) end) taskTypeText, " +
                     "        (case when nvl(wtr.print_type,'N') <> 'N' then " +
                     "        '[' || wtr.print_type || ']' || f_get_fieldtext('WMS_TASKALLOT_RULE', 'PRINT_TYPE', wtr.print_type) end) printTypeText, " +
                     "        (case when nvl(wtr.task_get_type,'N') <> 'N' then " +
                     "        '[' || wtr.task_get_type || ']' || f_get_fieldtext('N', 'TASK_GET_TYPE', wtr.task_get_type) end) taskGetTypeText,  " +
                     "        (case when nvl(wtr.task_get_type,'N') <> 'N' then " + 
                     "        '[' || wtr.task_get_type || ']' || f_get_fieldtext('WMS_TASKALLOT_RULE_M', 'ONEDELIVERONEPICK', wtr.onedeliveronepick) end) onedeliveronepickText " + 
                     "        from wms_taskallot_rule_m wtr " +
                     "       where wtr.enterprise_no = '"+ strEnterpriseNo +"' ";
	   	
	   	    if(strQuery!=null && !strQuery.equals(""))
	   	    {
	   		    String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
	   		    strSql=strSql+strWs;
	   	    }
	   	
	   	    strSql+=" order by wtr.task_ruleid, wtr.rule_type ";
	   	    List<Bdef_WmsTaskallotRuleMModel> list = genDao.getListByNativeSql(strSql,Bdef_WmsTaskallotRuleMModel.class/*,pageBo.getStart(),pageBo.getPagesize()*/);
	   	    Integer intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");		
	   	    ExtListDataBo<Bdef_WmsTaskallotRuleMModel> extListBo= new ExtListDataBo<Bdef_WmsTaskallotRuleMModel>(list, intCount);
	   	    return extListBo;
	}
	
	/**
	 * 获取任务切分规则明细数据
	 */
	@Override
	public ExtListDataBo<Bdef_WmsTaskallotRuleModel> getDetailInfo(
			String strQuery, PageBo pageBo) throws Exception {
		
		String strSql="";
    	strSql = " select wtr.operate_type,wtr.allot_rule,wtr.box_flag,wtr.para_value,wtr.task_type, " +
                 "        wtr.memo,wtr.rgst_name,wtr.rgst_date,wtr.print_type,wtr.outstock_type,wtr.task_get_type, " +
                "        '[' || wtr.operate_type || ']' || f_get_fieldtext('N', 'OPERATE_TYPE', wtr.operate_type) operateTypeText, " +
                "        '[' || wtr.allot_rule || ']' || f_get_fieldtext('WMS_TASKALLOT_RULE', 'ALLOT_RULE', wtr.allot_rule) allotRuleText, " +
                "        '[' || wtr.box_flag || ']' || f_get_fieldtext('WMS_TASKALLOT_RULE', 'BOX_FLAG', wtr.box_flag) boxFlagText, " +
                "        '[' || wtr.task_type || ']' || f_get_fieldtext('ODATA_OUTSTOCK_M', 'TASK_TYPE', wtr.task_type) taskTypeText, " +
                "        '[' || wtr.print_type || ']' || f_get_fieldtext('WMS_TASKALLOT_RULE', 'PRINT_TYPE', wtr.print_type) printTypeText, " +
                "        '[' || wtr.outstock_type || ']' || f_get_fieldtext('N', 'OUTSTOCK_TYPE', wtr.outstock_type) outstockTypeText, " +
                "        '[' || wtr.task_get_type || ']' || f_get_fieldtext('N', 'TASK_GET_TYPE', wtr.task_get_type) taskGetTypeText " +
                "   from wms_taskallot_rule wtr " +
                "  where 1 = 1 ";
   	
   	    if(strQuery!=null && !strQuery.equals(""))
   	    {
   		    String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
   		    strSql=strSql+strWs;
   	    }
   	
   	    strSql+=" order by wtr.outstock_type,wtr.operate_type ";
   	    List<Bdef_WmsTaskallotRuleModel> list = genDao.getListByNativeSql(strSql,Bdef_WmsTaskallotRuleModel.class/*,pageBo.getStart(),pageBo.getPagesize()*/);
   	    Integer intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");		
   	    ExtListDataBo<Bdef_WmsTaskallotRuleModel> extListBo= new ExtListDataBo<Bdef_WmsTaskallotRuleModel>(list, intCount);
   	    return extListBo;
	}
	
	/**
	 * 获取波次规则下拉框
	 */
	@Override
	public List<ComboxBo> getWaveRuleForUI(String strEnterpriseNo, String strQuery) throws Exception {
		String strSql=" select wod.rule_id value, " + 
                      "        wod.rule_name text, " + 
                      " 	   '[' || wod.rule_id || ']' || wod.rule_name dropValue " + 
                      "   from wms_outwaveplan_d wod " + 
                      "  where wod.enterprise_no = '"+ strEnterpriseNo +"' " + 
                      "  order by wod.rule_id " ;
	    List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
	    return (List<ComboxBo>) list;
    }
	
	/**
	 * 保存任务切分规则头档
	 */
	@Override
	public MsgRes saveWmsTaskallotRuleM(String str) throws Exception {
		Bdef_WmsTaskallotRuleM costWmsTaskallotRuleM=(Bdef_WmsTaskallotRuleM)JSON.parseObject(str,Bdef_WmsTaskallotRuleM.class);
		genDao.saveOrUpdateObj(costWmsTaskallotRuleM);
		return new MsgRes(true, "数据保存成功！", "");
	}
	
	/**
	 * 保存任务切分规则明细
	 */
	@Override
	public MsgRes saveWmsTaskallotRuleD(String str) throws Exception {
		Bdef_WmsTaskallotRule costWmsTaskallotRuleD=(Bdef_WmsTaskallotRule)JSON.parseObject(str,Bdef_WmsTaskallotRule.class);
		genDao.saveOrUpdateObj(costWmsTaskallotRuleD);
		return new MsgRes(true, "数据保存成功！", "");
	}
	
	/**
	 * 删除任务切分规则头档
	 */
	@Override
	public MsgRes deleteWmsTaskallotRuleM(String enterpriseNo, String strQuery)
			throws Exception {
		if(strQuery==null || strQuery.equals("")){
			return new MsgRes(false,"底层传参失败，不能全部删除","");
		}
		
		String deleteSql=" delete wms_taskallot_rule_m wtr where enterprise_no = '"+enterpriseNo+"' ";
		if(strQuery!=null && !strQuery.equals("")){
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			deleteSql=deleteSql+strWs;
		}
		genDao.updateBySql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}
	
	/**
	 * 删除任务切分规则明细
	 */
	@Override
	public MsgRes deleteWmsTaskallotRuleD(String strQuery) throws Exception {
		if(strQuery==null || strQuery.equals("")){
			return new MsgRes(false,"底层传参失败，不能全部删除","");
		}
		
		String deleteSql=" delete wms_taskallot_rule wtr where 1=1 ";
		if(strQuery!=null && !strQuery.equals("")){
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			deleteSql=deleteSql+strWs;
		}
		genDao.updateBySql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}
	
}















