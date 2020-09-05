/**
 * 模块名称：科目设置维护Action
 * 模块编码：B301
 * 创建：chensr 
 */
package com.sealinkin.bdef.action;

import java.util.List;

import com.sealinkin.bdef.service.IBill_AccountService;
import com.sealinkin.bset.model.Bill_Account_DModel;
import com.sealinkin.bset.model.Bill_Account_MModel;
import com.sealinkin.bset.model.Bill_Account_Model;
import com.sealinkin.bset.model.Bill_FormulasetModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public class Bill_AccountAction extends CommAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IBill_AccountService bill_AccountImpl;
	private String str;
	private String warehouseNo;
	private String ownerNo;
	private String accountNo;
	private String billingProject;
		
	//获取科目信息列表
	public void getAccountList(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bill_Account_MModel> accountList = bill_AccountImpl.getAccountList(
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
			List<String> list = bill_AccountImpl.accountNoCheck(
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
	public void getDiscountAccountNoComboList(){
		try 
		{
			List<ComboxBo> list = bill_AccountImpl.getDiscountAccountNoComboList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//保存科目信息
	public void saveAccount_m(){
		try
		{	
			bill_AccountImpl.saveAccount_m(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}		
	}
	
	//获取用于查找的货主下拉
	public void getOwnerNoForQuery(){
		try 
		{
			List<ComboxBo> list = bill_AccountImpl.getOwnerNoForQuery(
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
			List<ComboxBo> list = bill_AccountImpl.getAccountForUI(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//根据货主和苍别，获取尚未维护的计费项目
	public void getFormulasetList(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bill_FormulasetModel> formulasetList = bill_AccountImpl.getFormulasetList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getStr(), pageBo,this.getAccountNo());
			super.writeObj(formulasetList);		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//根据货主和仓别，获取科目和计费项目的关系
	public void getAccount_DListWithCondition(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bill_Account_DModel> formulasetList = bill_AccountImpl.getAccount_DListWithCondition(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getStr(), pageBo);
			super.writeObj(formulasetList);		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//保存科目和计费项目的关系
	public void saveAccount_D(){
		try {
			bill_AccountImpl.saveAccount_D(this.getStr());
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(true, "保存失败", ""));
		}	
	}
	
	//删除科目和计费项目的关系
	public void deleteAccount_D(){
		try {
			bill_AccountImpl.deleteAccount_D(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getWarehouseNo(),
					this.getOwnerNo(),
					this.getAccountNo(),this.getBillingProject());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取优惠阶梯信息列表
	public void getBillAccountList(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bill_Account_Model> accountList = bill_AccountImpl.getBillAccountList(
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
			List<ComboxBo> list = bill_AccountImpl.getDiscountFlagList();
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//保存科目信息
	public void saveAccount(){
		try
		{	
			bill_AccountImpl.saveAccount(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}		
	}	
	public IBill_AccountService getBill_AccountImpl() {
		return bill_AccountImpl;
	}

	public void setBill_AccountImpl(IBill_AccountService bill_AccountImpl) {
		this.bill_AccountImpl = bill_AccountImpl;
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
}
