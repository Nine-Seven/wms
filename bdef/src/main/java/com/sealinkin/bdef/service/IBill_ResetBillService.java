package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bset.model.Bill_Cost_ListModel;
import com.sealinkin.bset.model.Bill_Expenses_ListModel;
import com.sealinkin.bset.model.Bill_FinancialModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface IBill_ResetBillService {
	public MsgRes tscResetExp(String enterpriseNo,
							  String warehouseNo,
							  String ownerNo,
							  String beginDate,
							  String endDate)throws Exception;
	
	public MsgRes tscResetCost(
			  String enterpriseNo,
			  String warehouseNo,
			  String ownerNo,
			  String beginDate,
			  String endDate)throws Exception;
	
	public MsgRes tscResetFin( String enterpriseNo,String warehouseNo,
			  String ownerNo,
			  String month)throws Exception;
	
	public ExtListDataBo<Bill_Expenses_ListModel> getExpList(String enterpriseNo,String warehouseNo,
			String strOwnerNo,
			String strQuery,
			PageBo poPageBo,
			Integer intRequestFlag)throws Exception;
	public List getExpList2(String enterpriseNo,String warehouseNo,
			String strOwnerNo,
			String strQuery,
			Integer intRequestFlag)throws Exception;
	
	public ExtListDataBo<Bill_Cost_ListModel> getCostList(String enterpriseNo,String warehouseNo,
			String strOwnerNo,
			String strQuery,
			PageBo poPageBo,
			Integer intRequestFlag)throws Exception;
	public List getCostList2(String enterpriseNo,String warehouseNo,
			String strOwnerNo,
			String strQuery,
			Integer intRequestFlag)throws Exception;

	public ExtListDataBo<Bill_FinancialModel> getFinList(
			String enterpriseNo,
			String warehouseNo,
			String strOwnerNo,
			String strQuery,
			PageBo poPageBo,
			Integer intRequestFlag)throws Exception;
	public List getFinList2(
			String enterpriseNo,
			String warehouseNo,
			String strOwnerNo,
			String strQuery,
			Integer intRequestFlag)throws Exception;
	//费用上缴
	public MsgRes paidCost(String jsonStr) throws Exception;
	//取消费用
    public MsgRes undoCost(String jsonStr) throws Exception;
    //填充状态下拉
	public List<ComboxBo> getStatusList(String enterpriseNo,String workerOwner, String warehouseNo,String str)throws Exception;

}
