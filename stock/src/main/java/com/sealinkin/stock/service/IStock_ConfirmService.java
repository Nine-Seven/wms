package com.sealinkin.stock.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.stock.model.Stock_ConfirmDModel;
import com.sealinkin.stock.model.Stock_ConfirmMModel;

public interface IStock_ConfirmService {
	//填充货主下拉
	public List<ComboxBo> queryOwnerCombo(String enterpriseNo,String warehouseNo, String strWorkerOwner)throws Exception;
	//填充调整类型下拉
	public List<ComboxBo> queryPlanTypeCombo(String enterpriseNo,String warehouseNo, String strWorkerOwner,String str)throws Exception;
	//获取调账确认单头档信息列表
	public ExtListDataBo<Stock_ConfirmMModel> getStockConfirmMList(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String str,PageBo pageBo)throws Exception;
	//获取调账确认单明细信息列表
	public List<Stock_ConfirmDModel> getStockConfirmDList(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String str,PageBo pageBo)throws Exception;
	//调帐确认
	public MsgRes tscConfirm(String strJsonMaster)throws Exception;

}
