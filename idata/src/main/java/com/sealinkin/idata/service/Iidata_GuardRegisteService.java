package com.sealinkin.idata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.Idata_GuardRegisteModel;
import com.sealinkin.idata.model.Idata_OrderSheetModel;
import com.sealinkin.idata.model.Idata_OrderStatusModel;


public interface Iidata_GuardRegisteService {
	//获取预约流水号下拉
	public List<ComboxBo> getOrderSerialList(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner,String strAppointDate,String strWheresql,String str)throws Exception;
	//获取货主下拉strWarehouseNo
	public List<ComboxBo> queryOwnerCombo(String strEnterpriseNo,String strWarehouseNo, String strWorkerOwner,String strAppointDate,String str)throws Exception;
	//获取供应商下拉
	public List<ComboxBo> querySupplierNoCombo(String strEnterpriseNo,String strWarehouseNo, String strWorkerOwner,String strAppointDate,String str)throws Exception;
	//获取状态下拉
	public List<ComboxBo> queryStatusCombo(String strEnterpriseNo,String strWarehouseNo, String strWorkerOwner,String strAppointDate,String str)throws Exception;
	//获取预约状况信息列表
	public ExtListDataBo<Idata_OrderStatusModel> getIdata_Order_StatusList(
			String strEnterpriseNo,String strWarehouseNo, String strWorkerOwner,String strAppointDate,String str,PageBo pageBo)throws Exception;
	//获取报到状况信息列表
	public ExtListDataBo<Idata_GuardRegisteModel> getGuardRegisteList(
			String strEnterpriseNo,String strWarehouseNo, String strWorkerOwner,String str,String strWheresql,String status,PageBo pageBo)throws Exception;
	//获取单据信息列表
	public ExtListDataBo<Idata_OrderSheetModel> getIdata_Order_SheetList(
			String strEnterpriseNo,String strWarehouseNo, String str,PageBo pageBo)throws Exception;
	//保存报到信息
	public MsgRes save(String jsonMaster, String jsonDetail,String strSaveType,String strWorkSpaceNo) throws Exception;

}
