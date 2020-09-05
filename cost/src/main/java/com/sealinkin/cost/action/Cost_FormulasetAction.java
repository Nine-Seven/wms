/**
 * 模块名称：计费公式管理Action
 * 模块编码：B103
 * 创建：hcx 
 */
package com.sealinkin.cost.action;

import java.io.File;
import java.util.List;

import com.sealinkin.cost.model.Cost_FormulaArticlefamilyModel;
import com.sealinkin.cost.model.Cost_FormulaDiscountModel;
import com.sealinkin.cost.model.Cost_FormulasetModel;
import com.sealinkin.cost.service.ICost_FormulasetService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

public class Cost_FormulasetAction extends CommAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ICost_FormulasetService cost_FormulasetImpl;
	private String str;
	private String ownerNo;
	private String billingType;
	private String billingUnit;
	private String valuepolicyset;
	private String ruleId;
	private File file;
	private String strWhereSql;
	private String flag;
    private String strWarehouseNo;
    private String sttrOwnerNo;
    private String strBillingProject;
    private String strFamilyNo;
	
	//获取计费公式信息
	public void getFormulasetList(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Cost_FormulasetModel> formulasetList = cost_FormulasetImpl.getFormulasetList(
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
			cost_FormulasetImpl.saveFormulaset(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}		
	}
	//保存计费公式
	public void saveFormulaset2(){
		try
		{	
			cost_FormulasetImpl.saveFormulaset2(this.getStr()) ;
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
			List<ComboxBo> list = cost_FormulasetImpl.getOwnerNoForQuery(
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
			List<ComboxBo> list = cost_FormulasetImpl.getBillingProjectForUI(
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
			List<String> list = cost_FormulasetImpl.billingProjectCheck(
					this.getMdBdef_DefWorker().getEnterpriseNo(),
					this.getStr(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getOwnerNo());	
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取计费类型
	public void getBillingTypeForWind(){
		try 
		{
			List<ComboxBo> list = cost_FormulasetImpl.getBillingTypeForWind(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getStr());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
    //获取取值策略
    public void getValueFlagCombo(){
    	try 
		{
			List<ComboxBo> list = cost_FormulasetImpl.getValueFlagCombo(
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
			List<String> list =cost_FormulasetImpl.getRemarkCombo(this.getBillingType(),this.getBillingUnit(),this.getRuleId());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	
    }
	//获取商品群组
	public void getarticleFamilyNoCombo(){
		try {
			List<ComboxBo> list = cost_FormulasetImpl.getarticleFamilyNoCombo(
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
			cost_FormulasetImpl.saveFamilyUnitPrice(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}		
	}
	//上传
	public void upLoad(){
		try {
			MsgRes msg = cost_FormulasetImpl.upLoad(
					file,super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
		
	}
	
	//获取优惠信息列表
	public void getFormulaDiscountList(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Cost_FormulaDiscountModel> list = cost_FormulasetImpl.getFormulaDiscountList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					this.getStr(),
					pageBo);
			super.writeObj(list);		
		} catch (Exception e){
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}
	//获取包含商品群组信息列表
	public void getFormulaArticlefamilyList(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Cost_FormulaArticlefamilyModel> list = cost_FormulasetImpl.getFormulaArticlefamilyList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					this.getStr(),
					pageBo);
			super.writeObj(list);		
		} catch (Exception e){
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}
	//获取包含商品群组信息列表
	public void getFormulaArticlefamily(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Cost_FormulaArticlefamilyModel> list = cost_FormulasetImpl.getFormulaArticlefamily(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					flag,
					this.getStr(),
					strWhereSql,
					pageBo);
			super.writeObj(list);		
		} catch (Exception e){
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));

		}
	}
	//包含商品群组添加商品群组
	public void saveFormulaArticlefamilyList(){
		try {
			cost_FormulasetImpl.saveFormulaArticlefamilyList(str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//包含商品群组移除商品群组
	public void deleteFormulaArticlefamilyList(){
		try {
			cost_FormulasetImpl.deleteFormulaArticlefamilyList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strWarehouseNo,sttrOwnerNo, strBillingProject,strFamilyNo);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	//保存计费公式-优惠策略
	public void saveFormulaDiscount(){
		try
		{	
			cost_FormulasetImpl.saveFormulaDiscount(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}		
	}
	//获取新增窗口计费类型信息
	public void getFormulasetForWindList(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Cost_FormulasetModel> formulasetList = cost_FormulasetImpl.getFormulasetForWindList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					str,
					pageBo);
			super.writeObj(formulasetList);		
		} catch (Exception e){
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//删除计费项目
	public void deleteBillingProject(){
		try {
			MsgRes msg = cost_FormulasetImpl.deleteBillingProject(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
	                ownerNo,
	                billingType,
	                strBillingProject);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public ICost_FormulasetService getcost_FormulasetImpl() {
		return cost_FormulasetImpl;
	}
	public void setcost_FormulasetImpl(ICost_FormulasetService cost_FormulasetImpl) {
		this.cost_FormulasetImpl = cost_FormulasetImpl;
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

	public String getStrWarehouseNo() {
		return strWarehouseNo;
	}

	public void setStrWarehouseNo(String strWarehouseNo) {
		this.strWarehouseNo = strWarehouseNo;
	}

	public String getSttrOwnerNo() {
		return sttrOwnerNo;
	}

	public void setSttrOwnerNo(String sttrOwnerNo) {
		this.sttrOwnerNo = sttrOwnerNo;
	}

	public String getStrBillingProject() {
		return strBillingProject;
	}

	public void setStrBillingProject(String strBillingProject) {
		this.strBillingProject = strBillingProject;
	}

	public String getStrFamilyNo() {
		return strFamilyNo;
	}

	public void setStrFamilyNo(String strFamilyNo) {
		this.strFamilyNo = strFamilyNo;
	}
}
