package com.sealinkin.cost.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_FinancialModel;

public interface ICost_FinancialService {
	//获取用于查找的货主下拉
	public List<ComboxBo> getOwnerNoForQuery(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner) throws Exception;
	//获取UI界面的科目
	public List<ComboxBo> getAccountNoForUI(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner,String str) throws Exception;
	//获取UI界面的对账单号
	public List<ComboxBo> getCheckNoForUI(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner,String str,String strWheresql) throws Exception;
	//取已出账账单信息列表
	public ExtListDataBo<Cost_FinancialModel> getFinList(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,
			String strQuery,
			String buildDate,
			PageBo poPageBo,
			Integer intRequestFlag)throws Exception;
	//取导出的已出账账单信息
	public List getFinList2(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,
			String strQuery,
			Integer intRequestFlag)throws Exception;
	//获取科目组
	public String getAccountGroupNo(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,String str) throws Exception;
	//获取对账单生成标识
	public String getCreateFlag(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,String str) throws Exception;
	//重算账单
	public MsgRes tscResetFin(String strEnterpriseNo,String strWarehouseNo,
				String strWorkerOwner,String str)throws Exception;
	//账单回退
	public MsgRes tscUndoFinancial(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,String str)throws Exception;
}
