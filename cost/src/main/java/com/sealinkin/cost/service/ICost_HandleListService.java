/**
 * 模块名称：费用清单维护
 * 模块编码：B102
 * 创建：hcx 
 */
package com.sealinkin.cost.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_HandleListModel;

public interface ICost_HandleListService {
	
	public MsgRes getSerialNo(String enterpriseNo,String warehouseNo)throws Exception;
	//保存手工录入消费清单
	public void saveCostHandleList(String str) throws Exception;
	//获取手工录入消费清单信息
	public ExtListDataBo<Cost_HandleListModel> getCostHandleList(
			String enterpriseNo,String warehouseNo, String str, PageBo pageBo,String workerOwner) throws Exception; 
	//获取导出的手工录入消费清单信息
	public List getCostHandleList2(
				String enterpriseNo,String warehouseNo, String str, String workerOwner) throws Exception; 
	//获取新增窗口货主下拉
	public List<ComboxBo> getOwnerNoForWindow(String enterpriseNo,String warehouseNo,String workerOwner) throws Exception;
	//获取新增窗口计费项目
	public List<ComboxBo> getBillingProjectForWindow(String enterpriseNo,String warehouseNo,String workerOwner,String str) throws Exception;

	//根据计费项目获取默认单价和费用标识
	public List<Cost_HandleListModel> getUnitPriceAndcostFlag(
			String enterpriseNo,String warehouseNo,String workerOwner,String str)throws Exception;

	//删除消费清单信息
	public MsgRes deleteHandleList(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String strSerialNo) throws Exception;
	//判断计费项目编号与日期是否唯一
	public List<String> billingProjectCheck(String strEnterpriseNo, String strWarehouseNo,String strOwnerNo,
			String strBillingProject,String strAmountDate) throws Exception;
	//获取UI界面的查询条件下拉
	public List<ComboxBo> getSelectForUI(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner,String strSelectFlag,String strQuery,String strWheresql) throws Exception;

}
