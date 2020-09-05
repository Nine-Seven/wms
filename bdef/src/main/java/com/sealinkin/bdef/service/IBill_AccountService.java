package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bset.model.Bill_Account_DModel;
import com.sealinkin.bset.model.Bill_Account_MModel;
import com.sealinkin.bset.model.Bill_Account_Model;
import com.sealinkin.bset.model.Bill_FormulasetModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
/**
 * 模块名称：科目和计费公式的关系维护
 * 模块编码：B301
 * 创建：chensr 
 */
public interface IBill_AccountService {

	//获取科目信息列表
	public ExtListDataBo<Bill_Account_MModel> getAccountList(
			String enterpriseNo,String warehouseNo, String str, PageBo pageBo, String workerOwner) throws Exception;

	//判断科目代码是否唯一
	public List<String> accountNoCheck(String enterpriseNo,String str, String warehouseNo,String ownerNo) throws Exception;

	//保存科目信息
	public void saveAccount_m(String str) throws Exception;

	//获取优惠项目代码
	public List<ComboxBo> getDiscountAccountNoComboList(String enterpriseNo,String warehouseNo) throws Exception;

	//获取用于查找的货主下拉
	public List<ComboxBo> getOwnerNoForQuery(String enterpriseNo,String warehouseNo,String workerOwner) throws Exception;

	//根据货主和仓别获取科目的下拉
	public List<ComboxBo> getAccountForUI(String enterpriseNo,String warehouseNo,String str) throws Exception;

	//根据货主和苍别，获取尚未维护的计费项目
	public ExtListDataBo<Bill_FormulasetModel> getFormulasetList(String enterpriseNo,String str,
			PageBo pageBo, String accountNo)  throws Exception;

	//根据货主和苍别，获取科目和计费项目的关系
	public ExtListDataBo<Bill_Account_DModel> getAccount_DListWithCondition(
			String enterpriseNo,String str, PageBo pageBo);

	//保存科目和计费项目的关系
	public void saveAccount_D(String str);

	////删除科目和计费项目的关系
	public void deleteAccount_D(String enterpriseNo,String warehouseNo, String ownerNo,
			String accountNo, String billingProject);
	//获取科目阶梯信息列表
	public ExtListDataBo<Bill_Account_Model> getBillAccountList(
			String enterpriseNo,String warehouseNo, String str, PageBo pageBo, String workerOwner) throws Exception;
	//获取优惠阶梯-优惠方式下拉
	public List<ComboxBo> getDiscountFlagList() throws Exception;
	//保存阶梯信息
	public void saveAccount(String str) throws Exception;
}
