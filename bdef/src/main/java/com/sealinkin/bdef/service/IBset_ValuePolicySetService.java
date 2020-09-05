package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bset.model.Bset_ValuePolicySetModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface IBset_ValuePolicySetService {
	/**
	 * 获租仓策略设置列表
	 * @param strWarehouseNo
	 * @param strSubGroupNo
	 * @param poPageBo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Bset_ValuePolicySetModel> getValuePolicySetList(
			String strWarehouseNo,String strOwnerNo,PageBo poPageBo)throws Exception;
	
	/**
	 * 保存租仓策略设置
	 * @param strColnameNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes saveValuePolicySet(String strBsetValuePolicySet,String warehouseNo,
			String strOwnerNo,String strBillingProject)throws Exception;
	
	/**
	 *  获取货主下拉列表
	 * @return
	 * @throws Exception
	 * */
	public List<ComboxBo> getOwnerComboList(String workerOwner) throws Exception;
	
	/**
	 *  获取仓别名字
	 * @return
	 * @throws Exception
	 * */
	public List<ComboxBo> getHouseNameComboList(String workerOwner) throws Exception;
	
	/**
	 * 获取货主下拉列表  包括 All
	 * @return
	 * @throws Exception
	 * */
	public List<ComboxBo> getAllOwnerComboList(String workerOwner)throws Exception;

	
	/**
	 * 获取租仓策略设置的 BillingProject 下拉
	 * @param warehouseNo 
	 * @return
	 * @throws Exception
	 * */
	public List<ComboxBo> getBillingProjectCombo(String warehouseNo)throws Exception;
	
	/**
	 * 获取租仓策略设置的 BillingUnit 下拉
	 * @return
	 * @throws Exception
	 * */
	public List<ComboxBo> getBillingUnitCombo()throws Exception;
	
	/**
	 * 获取租仓策略设置的 sdefine 下拉
	 * @return
	 * @throws Exception
	 * */
	public List<String>  getValueFlagCombo(String strBillingUnit,String strBillingProject)throws Exception;

	/**
	 * 通过模块加载全部数据  all
	 * *//*
	public List<ComboxBo> getAllFromGroupNoCombo();
	
	*//**
	 * 加载全部列名 colname
	 * *//*
	public List<ComboxBo> getColnameCombo(String strGroupNo);
	*//**
	 * 加载 Memo
	 * */
	public List<String> getRemarkCombo(String strBillingUnit,String strBillingProject);
	
	//删除 租仓策略设置
	public abstract MsgRes deleteLine(String strBillingProject, String strOwnerNo,
			String warehouseNo);

}
