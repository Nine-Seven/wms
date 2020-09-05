/**
 * 模块名称：计费公式管理Action
 * 模块编码：B101
 * 创建：chensr 
 */
package com.sealinkin.bdef.action;

import java.io.File;
import java.util.List;

import com.sealinkin.bdef.service.IBill_FormulasetService;
import com.sealinkin.bset.model.Bill_FormulasetModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

public class Bill_FormulasetAction extends CommAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	IBill_FormulasetService bill_FormulasetImpl;
	private String str;
	private String ownerNo;
	private String billingType;
	private String billingUnit;
	private String valuepolicyset;
	private String ruleId;
	private File file;

	
	//获取计费公式信息
	public void getFormulasetList(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bill_FormulasetModel> formulasetList = bill_FormulasetImpl.getFormulasetList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr(),
					pageBo,
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeObj(formulasetList);		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//保存计费公式
	public void saveFormulaset(){
		try
		{	
			bill_FormulasetImpl.saveFormulaset(this.getStr()) ;
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
			List<ComboxBo> list = bill_FormulasetImpl.getOwnerNoForQuery(
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
			List<ComboxBo> list = bill_FormulasetImpl.getBillingProjectForUI(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//判断项目编码是否唯一
	public void billingProjectCheck(){
		try 
		{
			List<String> list = bill_FormulasetImpl.billingProjectCheck(
					this.getMdBdef_DefWorker().getEnterpriseNo(),
					this.getStr(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getOwnerNo());	
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    //获取取值策略
    public void getValueFlagCombo(){
    	try 
		{
			List<ComboxBo> list = bill_FormulasetImpl.getValueFlagCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getBillingType(),
					this.getBillingUnit());
			super.writeArray(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    //获取参数说明
    public void getRemark(){

		try 
		{
			List<String> list =bill_FormulasetImpl.getRemarkCombo(this.getBillingType(),this.getBillingUnit(),this.getRuleId());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	
    }
	//获取商品群组
	public void getarticleFamilyNoCombo(){
		try {
			List<ComboxBo> list = bill_FormulasetImpl.getarticleFamilyNoCombo(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					this.getStr()
					);		
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//保存商品群组单价
	public void saveFamilyUnitPrice(){
		try
		{	
			bill_FormulasetImpl.saveFamilyUnitPrice(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}		
	}
	//上传
	public void upLoad(){
		try {
			MsgRes msg = bill_FormulasetImpl.upLoad(
					file,super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
		
	}
	public IBill_FormulasetService getBill_FormulasetImpl() {
		return bill_FormulasetImpl;
	}
	public void setBill_FormulasetImpl(IBill_FormulasetService bill_FormulasetImpl) {
		this.bill_FormulasetImpl = bill_FormulasetImpl;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getBillingType() {
		return billingType;
	}
	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}
	public String getBillingUnit() {
		return billingUnit;
	}
	public void setBillingUnit(String billingUnit) {
		this.billingUnit = billingUnit;
	}
	public String getValuepolicyset() {
		return valuepolicyset;
	}
	public void setValuepolicyset(String valuepolicyset) {
		this.valuepolicyset = valuepolicyset;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
