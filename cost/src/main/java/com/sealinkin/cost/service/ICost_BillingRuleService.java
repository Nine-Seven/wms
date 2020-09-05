package com.sealinkin.cost.service;

import java.util.List;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_BillingRuleModel;
import com.sealinkin.cost.model.Cost_BillingTypeModel;

/**
 * 模块名称：计费取值方式维护
 * 模块编码：B1003
 * @author czh
 *
 */
public interface ICost_BillingRuleService {

	//获取计费类型列表
	public ExtListDataBo<Cost_BillingTypeModel> getRuleTypeList(
			String strEnterpriseNo, String strQuery, PageBo pageBo) throws Exception;
	//获取计费取值方式列表
	public ExtListDataBo<Cost_BillingRuleModel> getBillingRuleList(
			String strEnterpriseNo, String billingType,String strQuery, PageBo pageBo) throws Exception;
	//保存计费类型
	public void saveCostRuleType(String str) throws Exception;
	//判断计费类型代码是否唯一
	public List<String> checkRuleTypeNo(String strEnterpriseNo,String str) throws Exception;
	//删除计费类型
	public MsgRes deleteCostRuleType(String strEnterpriseNo,String str) throws Exception;
	//保存计费取值方式
	public void saveCostRule(String str) throws Exception;
	//删除计费取值方式
	public MsgRes deleteCostRule(String strEnterpriseNo,String str) throws Exception;
}
