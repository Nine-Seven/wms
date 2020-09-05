package com.sealinkin.cost.service;

import java.util.List;

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
/**
 * 模块名称：科目设置维护
 * 模块编码：B303
 * 创建：hcx 
 */
public interface ICost_AccountService {

	//科目代码维护-获取科目代码信息列表
	public ExtListDataBo<Cost_AccountSetModel> getCostAccountSetList(
				String strEnterpriseNo, String strQuery, PageBo pageBo) throws Exception;
	//科目代码维护-判断科目代码是否唯一
	public List<String> checkAccountNo(String strEnterpriseNo,String str) throws Exception;
	//科目代码维护-保存科目代码
	public void saveCostAccountSet(String str) throws Exception;
	//科目代码维护-删除科目代码
	public MsgRes deleteCostAccountSet(String strEnterpriseNo,String str) throws Exception;
	//获取科目信息列表
	public ExtListDataBo<Cost_AccountDModel> getAccountList(
			String enterpriseNo,String warehouseNo, String str, PageBo pageBo, String workerOwner) throws Exception;
	//获取科目明细信息列表
	public ExtListDataBo<Cost_AccountDModel> getAccountDList(
				String enterpriseNo,String warehouseNo, String str, PageBo pageBo, String workerOwner) throws Exception;

	//科目设置维护-判断科目代码是否唯一
	public List<String> accountNoCheck(String enterpriseNo,String str, String warehouseNo,String ownerNo) throws Exception;

	//保存科目信息
	public void saveAccount(String jsonMaster,String jsonDetail) throws Exception;

	//获取优惠项目代码
	public List<ComboxBo> getBillingProjectComboList(String enterpriseNo,String warehouseNo,String str) throws Exception;

	//获取用于查找的货主下拉
	public List<ComboxBo> getOwnerNoForQuery(String enterpriseNo,String warehouseNo,String workerOwner) throws Exception;

	//根据货主和仓别获取科目的下拉
	public List<ComboxBo> getAccountForUI(String enterpriseNo,String warehouseNo,String str) throws Exception;

	//根据获取尚未维护的计费项目
	public ExtListDataBo<Cost_FormulasetModel> getFormulasetList(
			String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner, 
			String falg, String str,String strWhereSql,  PageBo pageBo)  throws Exception;

	//获取科目和计费项目的关系
	public ExtListDataBo<Cost_AccountFormulaModel> getAccountFormulaList(
			String enterpriseNo,String warehouseNo, String ownerNo,String str, PageBo pageBo);

	//保存科目和计费项目的关系
	public void saveAccountFormula(String str);

	////删除科目和计费项目的关系
	public MsgRes deleteAccountFormula(String enterpriseNo,String warehouseNo, String ownerNo,
			String accountNo, String billingProject)throws Exception ;
	//获取科目优惠策略信息列表
	public ExtListDataBo<Cost_AccountDiscountModel> getAccountDiscountList(
			String enterpriseNo,String warehouseNo, String str, PageBo pageBo, String workerOwner) throws Exception;
	//获取优惠策略-优惠方式下拉
	public List<ComboxBo> getDiscountFlagList() throws Exception;
	//保存阶梯信息 
	public void saveAccountDiscount(String str) throws Exception;
	//根据货主和仓别获取科目的下拉
	public List<Cost_AccountMModel> getAccountCycle(String enterpriseNo,String warehouseNo,String ownerNo,String str) throws Exception;
	//校验科目周期 
	public List<Cost_AccountMModel> getAccountMList(
				String enterpriseNo,String warehouseNo, String str,String workerOwner) throws Exception;
	//获取账单单内序号
	public String getAccountId(String enterpriseNo,String warehouseNo,String ownerNo,String str) throws Exception;
	//获取最大科目组编码
	public String getMaxAccountGroupNo(String enterpriseNo,String warehouseNo,String ownerNo,String str) throws Exception;
	//获取科目组编码下拉
	public List<ComboxBo> getAccountGroupNoCombo(String enterpriseNo,String warehouseNo,String workerOwner,String str) throws Exception;
	//获取新增科目下拉
	public List<ComboxBo> getAccountForWind(String enterpriseNo,String str) throws Exception;
	//科目设置维护-删除科目
	public MsgRes deleteCostAccount(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String strDeleteType,String str) throws Exception;

}
