/**
 * 模块名称：科目设置维护Action
 * 模块编码：B303
 * 创建：hcx 
 */
package com.sealinkin.cost.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_AccountDModel;
import com.sealinkin.cost.model.Cost_AccountDiscountModel;
import com.sealinkin.cost.model.Cost_AccountFormulaModel;
import com.sealinkin.cost.model.Cost_AccountMModel;
import com.sealinkin.cost.model.Cost_AccountSetModel;
import com.sealinkin.cost.model.Cost_FormulasetModel;
import com.sealinkin.cost.service.ICost_AccountService;
import com.sealinkin.util.ExceptionUtil;

public class Cost_AccountAction extends CommAction {

	private static final long serialVersionUID = 1L;
	private ICost_AccountService cost_AccountImpl;
	private String str;
	private String warehouseNo;
	private String ownerNo;
	private String accountNo;
	private String billingProject;
	private String strWhereSql;
	private String flag;
	private String jsonMaster;
	private String jsonDetail;
	private String strQuery;
	
	//获取科目代码信息列表
	public void getCostAccountSetList(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Cost_AccountSetModel> accountSetList = cost_AccountImpl.getCostAccountSetList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strQuery, pageBo);
			super.writeStr(covtObjectToJson(accountSetList));
		} catch (Exception e){
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//科目代码维护-判断科目代码是否唯一
	public void checkAccountNo(){
		try 
		{
			List<String> list = cost_AccountImpl.checkAccountNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					str);	
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//科目代码维护-保存科目代码
	public void saveCostAccountSet(){
		try
		{	
			cost_AccountImpl.saveCostAccountSet(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}	
	}
	//科目代码维护-删除科目代码
	public void deleteCostAccountSet(){
		try {
			MsgRes msg = cost_AccountImpl.deleteCostAccountSet(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					str);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取科目信息列表
	public void getAccountList(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Cost_AccountDModel> accountList = cost_AccountImpl.getAccountList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr(), pageBo,
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeStr(covtObjectToJson(accountList));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	//获取科目明细信息列表
	public void getAccountDList(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Cost_AccountDModel> accountList = cost_AccountImpl.getAccountDList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr(), pageBo,
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeStr(covtObjectToJson(accountList));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	//判断科目代码是否唯一
	public void accountNoCheck(){
		try 
		{
			List<String> list = cost_AccountImpl.accountNoCheck(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getStr(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getOwnerNo());	
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取优惠项目代码
	public void getBillingProjectComboList(){
		try 
		{
			List<ComboxBo> list = cost_AccountImpl.getBillingProjectComboList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//保存科目信息
	public void saveAccount(){
		try
		{	
			cost_AccountImpl.saveAccount( jsonMaster, jsonDetail) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}		
	}
	
	//获取用于查找的货主下拉
	public void getOwnerNoForQuery(){
		try 
		{
			List<ComboxBo> list = cost_AccountImpl.getOwnerNoForQuery(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//根据货主和仓别获取科目的下拉
	public void getAccountForUI(){
		try 
		{
			List<ComboxBo> list = cost_AccountImpl.getAccountForUI(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取尚未维护的计费项目
	public void getFormulasetList(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Cost_FormulasetModel> formulasetList = cost_AccountImpl.getFormulasetList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					flag,
					this.getStr(),
					strWhereSql,
					pageBo);
			super.writeObj(formulasetList);		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//获取科目和计费项目的关系
	public void getAccountFormulaList(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Cost_AccountFormulaModel> formulasetList = cost_AccountImpl.getAccountFormulaList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					this.getStr(), pageBo);
			super.writeObj(formulasetList);		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//保存科目和计费项目的关系
	public void saveAccountFormula(){
		try {
			cost_AccountImpl.saveAccountFormula(this.getStr());
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}	
	}
	
	//删除科目和计费项目的关系
	public void deleteAccountFormula(){
		try {
			MsgRes msg = cost_AccountImpl.deleteAccountFormula(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getWarehouseNo(),
					this.getOwnerNo(),
					this.getAccountNo(),this.getBillingProject());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据保存失败！
		}
	}
	//获取优惠策略信息列表
	public void getAccountDiscountList(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Cost_AccountDiscountModel> accountList = cost_AccountImpl.getAccountDiscountList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr(), pageBo,
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeStr(covtObjectToJson(accountList));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//获取优惠阶梯-优惠方式下拉
	public void getDiscountFlagList(){
		try 
		{
			List<ComboxBo> list = cost_AccountImpl.getDiscountFlagList();
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//保存科目优惠策略信息
	public void saveAccountDiscount(){
		try
		{	
			cost_AccountImpl.saveAccountDiscount(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}		
	}	
	//获取结账周期
	public void getAccountCycle(){
			try 
			{
				List<Cost_AccountMModel> list = cost_AccountImpl.getAccountCycle(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						super.getMdBdef_DefWorker().getWorkerOwner(),
						this.getStr());
				super.writeArray(list);
			} catch (Exception e) {
				e.printStackTrace();
				super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

			}
		}
	//校验科目周期 
	public void getAccountMList(){
		try 
		{	
			List<Cost_AccountMModel> list = cost_AccountImpl.getAccountMList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr(),
					super.getMdBdef_DefWorker().getWorkerOwner());	
			super.writeArray(list);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	//获取结账序号
	public void getAccountId(){
		try 
		{
			String max=cost_AccountImpl.getAccountId(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					this.getStr());
			super.writeStr(max);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取最大账单组编码
	public void getMaxAccountGroupNo(){
		try 
		{
			String max=cost_AccountImpl.getMaxAccountGroupNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					this.getStr());
			super.writeStr(max);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取科目组编码下拉
	public void getAccountGroupNoCombo(){
		try 
		{
			List<ComboxBo> list = cost_AccountImpl.getAccountGroupNoCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					str);
				super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//根据货主和仓别获取科目的下拉
	public void getAccountForWind(){
		try 
		{
			List<ComboxBo> list = cost_AccountImpl.getAccountForWind(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getStr());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//科目设置维护-删除科目
	public void deleteCostAccount(){
		try {
			MsgRes msg = cost_AccountImpl.deleteCostAccount(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
                    ownerNo,
                    flag,
					str);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}	
	public ICost_AccountService getCost_AccountImpl() {
		return cost_AccountImpl;
	}

	public void setCost_AccountImpl(ICost_AccountService cost_AccountImpl) {
		this.cost_AccountImpl = cost_AccountImpl;
	}

	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getBillingProject() {
		return billingProject;
	}
	public void setBillingProject(String billingProject) {
		this.billingProject = billingProject;
	}
	public String getStrWhereSql() {
		return strWhereSql;
	}
	public void setStrWhereSql(String strWhereSql) {
		this.strWhereSql = strWhereSql;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getJsonMaster() {
		return jsonMaster;
	}
	public void setJsonMaster(String jsonMaster) {
		this.jsonMaster = jsonMaster;
	}
	public String getJsonDetail() {
		return jsonDetail;
	}
	public void setJsonDetail(String jsonDetail) {
		this.jsonDetail = jsonDetail;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
}
