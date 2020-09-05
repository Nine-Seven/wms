package com.sealinkin.rodata.service;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_OutstockDModel;
import com.sealinkin.rodata.model.Rodata_OutstockMModel;


public interface IRodata_OutstockMService {

	ExtListDataBo<Rodata_OutstockMModel> getRodata_OutstockM(
			String enterpriseNo,String warehouseNo, Integer start, Integer limit, String queryStr);

	ExtListDataBo<Rodata_OutstockDModel> getRodata_OutstockD(String string, Integer start,
			Integer limit);

	MsgRes save(String workerNo, String outUserId, String str, String workSpaceNo) throws Exception;
	/**
	 * RF退厂回单 扫标签获取下架信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes ROdataCheckLabelNo(String strRecvData)throws Exception;
	/**
	 * RF退厂回单 扫商品条码校验
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes ROdataCheckBarcode(String strRecvData)throws Exception;
	/**
	 * RF退厂回单 
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscROPickReceipt(String strRecvData)throws Exception;

	
/*	MsgRes tscRoComfirm(
			String enterpriseNo,
			String strWarehouseNo,
			String strOwnerNo,
			String strRecedeNo, 
			String strUserID,
			String strDock_No) throws Exception;

	*//**
	 * 退厂确认 查询头档信息
	 * @param warehouseNo
	 * @author lich
	 * @param start
	 * @param limit
	 * @param queryStr
	 * @return
	 *//*
	ExtListDataBo<Rodata_OutstockMModel> getRodata_OutstockMComfirm(
			String enterpriseNo,
			String warehouseNo, 
			Integer start, 
			Integer limit, 
			String queryStr);

	*//**
	 * 退厂确认 查询明细信息
	 * @author lich
	 * @param string
	 * @param start
	 * @param limit
	 * @return
	 *//*
	ExtListDataBo<Rodata_OutstockDModel> getRodata_OutstockDComfirm(String string, 
			Integer start,
			Integer limit);	
	
	*//**
     * 退厂确认 货主、供应商……下拉
     * @author lich
	 * @param  
     *//*
	public List<ComboxBo> getRodata_OutstockComfirmCombo(
			String enterpriseNo,
			String strWarehouseNo,
			String strWorkerOwner, 
			String strFlag, 
			String str) ;
	
	*/
}
