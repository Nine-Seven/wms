package com.sealinkin.cost.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_ExpensesListModel;

public interface ICost_ManualCostService {
	//获取UI界面的科目
	public List<ComboxBo> getAccountNoForUI(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner,String str) throws Exception;
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

	//手工生成费用
	public MsgRes saveManualCost(String strEnterpriseNo,String strWarehouseNo,String str,String strJoin)throws Exception;
}
