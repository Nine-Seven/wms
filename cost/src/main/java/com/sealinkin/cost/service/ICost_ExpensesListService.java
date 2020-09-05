package com.sealinkin.cost.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_ExpensesListModel;

public interface ICost_ExpensesListService {
	//获取用于查找的货主下拉
	public List<ComboxBo> getOwnerNoForQuery(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner) throws Exception;
	//获取UI界面的计费项目
	public List<ComboxBo> getBillingProjectForUI(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner,String str) throws Exception;
	//取消费清单信息列表
	public ExtListDataBo<Cost_ExpensesListModel> getExpList(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,
			String strQuery,
			String buildDate,
			String beginDate,
			String endDate,
			PageBo poPageBo,
			Integer intRequestFlag)throws Exception;
	//取导出的消费清单信息
	public List getExpList2(String strEnterpriseNo,String strWarehouseNo,
				String strWorkerOwner,
				String strQuery,
				String buildDate,
				String beginDate,
				String endDate,
	
				Integer intRequestFlag)throws Exception;
	//填充状态下拉
	public List<ComboxBo> getStatusList(String strEnterpriseNo,String strWarehouseNo,
				String strWorkerOwner,String str)throws Exception;
	//取来源单号下拉
	public List<ComboxBo> getSourceNoList(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,String strWheresql,String str,String buildDate,
			String beginDate,String endDate)throws Exception;
	//重算消费清单
	public MsgRes tscResetExp(String strEnterpriseNo,String strWarehouseNo,
			String strOwnerNo,String billingProject,String beginDate,
			String endDate,String strWorkerNo)throws Exception;
	//删除消费清单
	public MsgRes deleteExpensesList(String strQuery,String strBuildDate) throws Exception;

}
