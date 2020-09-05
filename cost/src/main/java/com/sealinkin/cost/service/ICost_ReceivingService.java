package com.sealinkin.cost.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_FinancialModel;
import com.sealinkin.cost.model.Cost_ReturnAmountModel;

public interface ICost_ReceivingService {
	//获取用于查找的货主下拉
	public List<ComboxBo> getOwnerNoForQuery(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner) throws Exception;
	//获取UI界面的对账单号
	public List<ComboxBo> getCheckNoForUI(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner,String str) throws Exception;
	//取已出账账单信息列表
	public ExtListDataBo<Cost_FinancialModel> getFinList(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,
			String strQuery,
			PageBo poPageBo,
			Integer intRequestFlag)throws Exception;
	//取导出的已出账账单信息
	public List getFinList2(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,
			String strQuery,
			Integer intRequestFlag)throws Exception;
	//填充状态下拉
	public List<ComboxBo> getStatusList(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,String str)throws Exception;
	//取回款信息列表
	public ExtListDataBo<Cost_ReturnAmountModel> getReturnAmountList(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,
			String strQuery,
			PageBo poPageBo)throws Exception;
	//保存回款信息
	public void saveReturnAmount(String str)throws Exception;
	//获回款信息rowid
	public String getRowId(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner,String str) throws Exception;
	//修改账单状态
	public MsgRes update(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner,String strCheckNo,String strCostFlag,String strStatus) throws Exception;

}
