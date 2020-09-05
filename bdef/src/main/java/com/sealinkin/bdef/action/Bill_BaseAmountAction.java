/**
 * 模块名称：费用清单维护Action
 * 模块编码：B102
 * 创建：hcx 
 */
package com.sealinkin.bdef.action;

import java.util.List;

import com.sealinkin.bdef.service.IBill_BaseAmountService;
import com.sealinkin.bset.model.Bill_Base_AmountModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

public class Bill_BaseAmountAction extends CommAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	IBill_BaseAmountService bill_BaseAmountImpl;
	private String str;
	private String billingProject;
	private String amountDate;
	private String billingCycle;
    private String strOwnerNo;
    
	public void getSerialNo(){
		try {
			MsgRes msg=bill_BaseAmountImpl.getSerialNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//保存基础费用清单
	public void saveBillBaseAmount(){
		try
		{	
			bill_BaseAmountImpl.saveBillBaseAmount(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}		
	}
	//获取基础费用清单信息
	public void getBillBaseAmountList(){
		try 
		{			
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bill_Base_AmountModel> billBaseAmountList = bill_BaseAmountImpl.getBillBaseAmountList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr(),
					pageBo,
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeObj(billBaseAmountList);		
		} catch (Exception e){
				e.printStackTrace();
		}
	}
		//获取用于查找的货主下拉
	public void getOwnerNoForQuery(){
		try 
		{
			List<ComboxBo> list = bill_BaseAmountImpl.getOwnerNoForQuery(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}	
	//获取UI的计费项目
	public void getBillingProjectForUI(){
		try 
		{
			List<ComboxBo> list = bill_BaseAmountImpl.getBillingProjectForUI(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取新增窗口货主下拉
	public void getOwnerNoForWindow(){
		try 
		{
			List<ComboxBo> list = bill_BaseAmountImpl.getOwnerNoForWindow(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	//获取UI的计费项目
	public void getBillingProjectForWindow(){
		try 
		{
			List<ComboxBo> list = bill_BaseAmountImpl.getBillingProjectForWindow(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					this.getStr());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据计费项目获取固定值和计费周期
	 */
	public void getFixedValueAndBillingCycle()
	{
		try 
		{
			List<Bill_Base_AmountModel> list=bill_BaseAmountImpl.getFixedValueAndBillingCycle(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					this.getStr());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public void check(){
		try {
			MsgRes msg=bill_BaseAmountImpl.check(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strOwnerNo,billingProject,amountDate,billingCycle);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	public IBill_BaseAmountService getBill_BaseAmountImpl() {
		return bill_BaseAmountImpl;
	}
	public void setBill_BaseAmountImpl(IBill_BaseAmountService bill_BaseAmountImpl) {
		this.bill_BaseAmountImpl = bill_BaseAmountImpl;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}

	public String getBillingProject() {
		return billingProject;
	}

	public void setBillingProject(String billingProject) {
		this.billingProject = billingProject;
	}

	public String getAmountDate() {
		return amountDate;
	}

	public void setAmountDate(String amountDate) {
		this.amountDate = amountDate;
	}

	public String getBillingCycle() {
		return billingCycle;
	}

	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}

	public String getStrOwnerNo() {
		return strOwnerNo;
	}

	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}
	
}
