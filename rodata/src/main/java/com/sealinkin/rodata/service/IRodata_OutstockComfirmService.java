package com.sealinkin.rodata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_OutstockDModel;
import com.sealinkin.rodata.model.Rodata_OutstockMModel;

/*
 * 退厂确认
 * hekangli
 */

public interface IRodata_OutstockComfirmService {
	MsgRes tscRoComfirm(
			String enterpriseNo,
			String strWarehouseNo,
			String strOwnerNo,
			String strRecedeNo, 
			String strGrossWeight, 
			String strUserID,
			String strDock_No) throws Exception;

	/**
	 * 退厂确认 查询头档信息
	 * @param warehouseNo
	 * @author lich
	 * @param start
	 * @param limit
	 * @param queryStr
	 * @return
	 */
	ExtListDataBo<Rodata_OutstockMModel> getRodata_OutstockMComfirm(
			String enterpriseNo,
			String warehouseNo, 
			Integer start, 
			Integer limit, 
			String strSuppliersNo);

	/**
	 * 退厂确认 查询明细信息
	 * @author lich
	 * @param string
	 * @param start
	 * @param limit
	 * @return
	 */
	ExtListDataBo<Rodata_OutstockDModel> getRodata_OutstockDComfirm(String strPoNo, 
			String enterpriseNo,
			String warehouseNo, 
			Integer start,
			Integer limit);	
	
	/**
     * 退厂确认 货主、供应商……下拉
     * @author lich
	 * @param  
     */
	public List<ComboxBo> getRodata_OutstockComfirmCombo(
			String enterpriseNo,
			String strWarehouseNo,
			String strWorkerOwner, 
			String strFlag, 
			String str) ;
	
	
	
}
