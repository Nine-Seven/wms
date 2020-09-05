package com.sealinkin.cost.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_CostListModel;

public interface ICost_ManualAccountService {
	//获取UI界面的科目
	public List<ComboxBo> getAccountNoForUI(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner,String str) throws Exception;
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
	//手工生成账单
	public MsgRes saveManualAccount(String strEnterpriseNo,String strWarehouseNo,String str,String strWheresql,String strJoin)throws Exception;
}
