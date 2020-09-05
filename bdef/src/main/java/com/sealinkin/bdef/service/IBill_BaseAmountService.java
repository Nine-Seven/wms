/**
 * 模块名称：费用清单维护
 * 模块编码：B102
 * 创建：hcx 
 */
package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_ArticlePackingModel;
import com.sealinkin.bset.model.Bill_Base_AmountModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface IBill_BaseAmountService {
	
	public MsgRes getSerialNo(String enterpriseNo,String warehouseNo)throws Exception;
//	//保存基础费用清单
	public void saveBillBaseAmount(String str) throws Exception;
//	
	//获取费用清单信息
	public ExtListDataBo<Bill_Base_AmountModel> getBillBaseAmountList(
			String enterpriseNo,String warehouseNo, String str, PageBo pageBo,String workerOwner) throws Exception; 

	//获取用于查找的货主下拉
	public List<ComboxBo> getOwnerNoForQuery(String enterpriseNo,String warehouseNo,String workerOwner) throws Exception;

	//获取UI界面的项目名称
	public List<ComboxBo> getBillingProjectForUI(String enterpriseNo,String warehouseNo,String str) throws Exception;
	//获取新增窗口货主下拉
	public List<ComboxBo> getOwnerNoForWindow(String enterpriseNo,String warehouseNo,String workerOwner) throws Exception;
	//获取新增窗口计费项目
	public List<ComboxBo> getBillingProjectForWindow(String enterpriseNo,String warehouseNo,String workerOwner,String str) throws Exception;

	//根据计费项目获取固定值和周期
	public List<Bill_Base_AmountModel> getFixedValueAndBillingCycle(
			String enterpriseNo,String warehouseNo,String workerOwner,String str)throws Exception;
	public MsgRes check(String strEnterpriseNo,String warehouseNo,String strOwnerNo,String billingProject,String amountDate,String billingCycle)throws Exception;

}
