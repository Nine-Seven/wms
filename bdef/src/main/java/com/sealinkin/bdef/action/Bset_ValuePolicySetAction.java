package com.sealinkin.bdef.action;

import java.util.List;

import com.sealinkin.bdef.service.IBset_ValuePolicySetService;
import com.sealinkin.bset.model.Bset_ValuePolicySetModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

public class Bset_ValuePolicySetAction extends CommAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IBset_ValuePolicySetService bset_ValuePolicySetImpl;
	private MsgRes msgRes;
	private String strRuleId;
	private String strBillingUnit;
	private String strBillingType;
	private String strBillingProject;
	private String strOwnerNo;
	private String strValueFlag;
	private String strWarehouseNo;
	
	private String strBsetValuePolicySet;
	
	/**
	 * 获取仓别参数列
	 */
	public void getValuePolicySetList()
	{
		try 
		{
			PageBo poPagebo= new PageBo(getStart(), getLimit());
			ExtListDataBo<Bset_ValuePolicySetModel> pageListBo = 
					bset_ValuePolicySetImpl.getValuePolicySetList(super.getMdBdef_DefWorker().getWarehouseNo(),strOwnerNo,poPagebo);
			super.writeObj(pageListBo);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 保存、修改仓别参数
	 * @author
	 */
	public void saveOrUpdateValuePolicySet(){
		try{
			MsgRes msg=bset_ValuePolicySetImpl.saveValuePolicySet(strBsetValuePolicySet,strWarehouseNo,strOwnerNo,strBillingProject);
			super.writeObj(msg);
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "savefail", ""));
		}
	}
	
	
	/**
	 * 获取当前仓别和当前用户权限下货主 和 all
	 */
	public void getAllOwnerComboList()
	{
		try 
		{
			List<ComboxBo> list=bset_ValuePolicySetImpl.getAllOwnerComboList(super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取当前仓别和当前用户权限下货主
	 */
	public void getOwnerComboList()
	{
		try 
		{
			List<ComboxBo> list=bset_ValuePolicySetImpl.getOwnerComboList(super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取当前仓别对应的name
	 */
	public void getHouseNameComboList()
	{
		try 
		{
			List<ComboxBo> list=bset_ValuePolicySetImpl.getHouseNameComboList(super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取BillingProject 下拉
	 */
	public void getBillingProjectCombo()
	{
		try 
		{
			List<ComboxBo> list=bset_ValuePolicySetImpl.getBillingProjectCombo(super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * 获取BillingProject 下拉
	 */
	public void getBillingUnitCombo()
	{
		try 
		{
			List<ComboxBo> list=bset_ValuePolicySetImpl.getBillingUnitCombo();
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * 获取rule_id 下拉
	 */
	public void getValueFlagCombo()
	{
		try 
		{
			List<String> list =bset_ValuePolicySetImpl.getValueFlagCombo(strBillingUnit,strBillingProject);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 *通过取值方式加载Remark
	 */
	public void getRemarkCombo()
	{
		try 
		{
			List<String> list =bset_ValuePolicySetImpl.getRemarkCombo(strBillingUnit, strBillingProject);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	//删除 租仓策略设置数据
	public void deleteLine() {
	try {
		MsgRes msg = this.bset_ValuePolicySetImpl.deleteLine(strBillingProject,strOwnerNo,
			super.getMdBdef_DefWorker().getWarehouseNo());
		super.writeObj(msg);
	} catch (Exception e) {
		e.printStackTrace();
		super.writeObj(new MsgRes(Boolean.valueOf(false), ExceptionUtil
				.getCacseMessage(e), ""));
	} finally {
		writeObj(this.msgRes);
	}
}
	public String getStrValueFlag() {
		return strValueFlag;
	}
	public void setStrValueFlag(String strValueFlag) {
		this.strValueFlag = strValueFlag;
	}
	public IBset_ValuePolicySetService getBset_ValuePolicySetImpl() {
		return bset_ValuePolicySetImpl;
	}
	public void setBset_ValuePolicySetImpl(
			IBset_ValuePolicySetService bset_ValuePolicySetImpl) {
		this.bset_ValuePolicySetImpl = bset_ValuePolicySetImpl;
	}
	public String getStrOwnerNo() {
		return strOwnerNo;
	}
	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}

	public String getStrBsetValuePolicySet() {
		return strBsetValuePolicySet;
	}

	public void setStrBsetValuePolicySet(String strBsetValuePolicySet) {
		this.strBsetValuePolicySet = strBsetValuePolicySet;
	}
	public String getStrBillingProject() {
		return strBillingProject;
	}
	public void setStrBillingProject(String strBillingProject) {
		this.strBillingProject = strBillingProject;
	}
	public MsgRes getMsgRes() {
		return msgRes;
	}
	public void setMsgRes(MsgRes msgRes) {
		this.msgRes = msgRes;
	}

	public String getStrRuleId() {
		return strRuleId;
	}

	public void setStrRuleId(String strRuleId) {
		this.strRuleId = strRuleId;
	}
	public String getStrBillingUnit() {
		return strBillingUnit;
	}
	public void setStrBillingUnit(String strBillingUnit) {
		this.strBillingUnit = strBillingUnit;
	}
	public String getStrBillingType() {
		return strBillingType;
	}
	public void setStrBillingType(String strBillingType) {
		this.strBillingType = strBillingType;
	}

	public String getStrWarehouseNo() {
		return strWarehouseNo;
	}

	public void setStrWarehouseNo(String strWarehouseNo) {
		this.strWarehouseNo = strWarehouseNo;
	}
	
	
	
}
