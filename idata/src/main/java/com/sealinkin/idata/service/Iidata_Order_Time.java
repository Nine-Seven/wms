package com.sealinkin.idata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.DockSheetModel;
import com.sealinkin.idata.model.Idata_ImportMModel;
import com.sealinkin.idata.model.Idata_OrderSheetModel;
import com.sealinkin.idata.model.Idata_OrderStatusModel;

public interface Iidata_Order_Time {
	public List<DockSheetModel> getDock_Sheet(String enterpriseNo,
			String warehouseNo) throws Exception;
	
	public List<ComboxBo> getIdata_Order_Time_GetCombo(
			String enterpriseNo,
			String warehouseNo,
			String owner,
			String flag,
			String str,
			int start,
			int pagesize) ;
	//获取预约状况码头下拉列表
	public List<ComboxBo> getDockNoCombo(
			String strEnterpriseNo,
			String strWarehouseNo,
			String strOwnerNo,
			String strWheresql,
			String str) ;
	
	public ExtListDataBo<Idata_ImportMModel> getIdata_Import_MList(
			String enterpriseNo,
			String warehouseNo,
			String owner,
			String str,
			PageBo pageBo)throws Exception;
	
	public MsgRes save(
			String enterpriseNo,
			String warehouseNo,
			String importNo,
			String time,
			String workerNo,
			String strWorkSpaceNo,
			String strDockNo,
			String flag, 
			String printFlag) throws Exception;
	
	//修改保存
	public MsgRes editSave(
			String enterpriseNo,
			String warehouseNo,
			String workerNo,
			String strWorkSpaceNo,
			String strDockNo,String time,String str) throws Exception;
	
	public ExtListDataBo<Idata_OrderStatusModel> getIdata_Order_StatusList(
			String enterpriseNo,
			String warehouseNo,
			String owner,
			String flag,
			String str,
			PageBo pageBo)throws Exception;
	
	public ExtListDataBo<Idata_OrderSheetModel> getIdata_Order_SheetList(
			String enterpriseNo,
			String warehouseNo,
			String owner,
			String str,
			PageBo pageBo)throws Exception;
	
}
