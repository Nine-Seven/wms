package com.sealinkin.cost.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_CostListModel;

public interface ICost_NoAccountListService {
	//获取用于查找的货主下拉
	public List<ComboxBo> getOwnerNoForQuery(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner) throws Exception;
	//获取UI界面的计费项目
	public List<ComboxBo> getBillingProjectForUI(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner,String str) throws Exception;
	//取未出账清单信息列表
	public ExtListDataBo<Cost_CostListModel> getNoAccountList(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,
			String strQuery,
			String strMenuType,
			PageBo poPageBo,
			Integer intRequestFlag)throws Exception;
	//取导出的未出账清单信息
	public List getNoAccountList2(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,
			String strQuery,
			Integer intRequestFlag)throws Exception;
	//重算费用明细
	public MsgRes tscResetCost(String strEnterpriseNo,String strWarehouseNo,
			String strOwnerNo,String billingProject,String beginDate,
			String endDate,String strWorkerNo)throws Exception;
	//费用明细回退
	public MsgRes tscUndoCost(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,String str)throws Exception;
}
