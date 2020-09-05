package com.sealinkin.cost.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_BillingRuleModel;
import com.sealinkin.cost.model.Cost_BillingTypeModel;
import com.sealinkin.cost.service.ICost_BillingRuleService;
import com.sealinkin.util.ExceptionUtil;

public class Cost_BillingRuleAction extends CommAction{

	private static final long serialVersionUID = 1L;
	ICost_BillingRuleService cost_BillingRuleImpl;
	private String strQuery;
	private String billingType;
	private String str;
	//获取计费类型列表
	public void getRuleTypeList(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Cost_BillingTypeModel> ruleTypeList = cost_BillingRuleImpl.getRuleTypeList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getStrQuery(),
					pageBo);
			super.writeObj(ruleTypeList);		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	//获取取值方式列表
	public void getBillingRuleList(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Cost_BillingRuleModel> ruleTypeList = cost_BillingRuleImpl.getBillingRuleList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getBillingType(),
					this.getStrQuery(),
					pageBo);
			super.writeObj(ruleTypeList);		
		} catch (Exception e){
			e.printStackTrace();
		}
	}	
	//保存计费类型
	public void saveCostRuleType(){
		try
		{	
			cost_BillingRuleImpl.saveCostRuleType(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}	
	}
	//判断计费类型代码是否唯一
	public void checkRuleTypeNo(){
		try 
		{
			List<String> list = cost_BillingRuleImpl.checkRuleTypeNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					str);	
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//删除计费类型代码
	public void deleteCostRuleType(){
		try {
			MsgRes msg = cost_BillingRuleImpl.deleteCostRuleType(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					str);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//保存计费取值方式
	public void saveCostRule(){
		try
		{	
			cost_BillingRuleImpl.saveCostRule(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}	
	}
	//删除计费类型代码
	public void deleteCostRule(){
		try {
			MsgRes msg = cost_BillingRuleImpl.deleteCostRule(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					str);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	public ICost_BillingRuleService getCost_BillingRuleImpl() {
		return cost_BillingRuleImpl;
	}
	public void setCost_BillingRuleImpl(
			ICost_BillingRuleService cost_BillingRuleImpl) {
		this.cost_BillingRuleImpl = cost_BillingRuleImpl;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getBillingType() {
		return billingType;
	}
	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	
	
}
