package com.sealinkin.ridata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.ridata.model.Ridata_CheckDModel;
import com.sealinkin.ridata.model.Ridata_CheckPalTmpModel;

public interface IRidata_QingchangService {
	
	
	/**
	 * 返配清场扫描验收列
	 * @param pageBo 
	 * @param str
	 * @param pageBo
	 * @return
	 */
	public ExtListDataBo<Ridata_CheckDModel> getRidata_QingchangDList(
			String enterpriseNO,String warehouseNo,String strWhereSql, PageBo pageBo);
	

	/*
	 * 获取货主...下拉
	 */
	public List<ComboxBo> getComboList(String enterpriseNO,String warehouseNo, String workerOwner,
			String strFlag);

    //保存
	public MsgRes save(String enterpriseNO,String strWarehouseNo,String jsonDetail) throws Exception;
	
	
	/**
	 * 校验码头与单号是否一致
	 * @param strWarehouseNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes checkDockNoUNo(String enterpriseNO,String strWarehouseNo,String strUntreadNo,String strDockNo)throws Exception;
	
	/**
	 * 校验条码
	 * @param strWarehouseNo
	 * @param strBarcode
	 * @param strOwnerNo
	 * @param strSImportNo
	 * @param strSUntreadNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes queryBarcode(
			String enterpriseNO,
			String strWarehouseNo,
			String strBarcode,
			String strOwnerNo,
			String strUntreadNo)throws Exception;
	
	/**
	 * 校验目的储位
	 */
	public MsgRes checkCellNo(
			String enterpriseNO,
			String strWarehouseNo,
			String strWhereSql,
			String strOwnerNo,String strSUntreadNo2,String strSCheckNo)throws Exception;
	
	
	/**
	 *  换单时校验该码头是否还有未封箱的数据
	 */
	public MsgRes checkCloseBox(
			String enterpriseNO,
			String strWarehouseNo,
			String strDockNo,String strUntreadNo)throws Exception;
	/**
	 *取该单的扫描数量
	 */
	public MsgRes tscBoxQty(
			String enterpriseNO,
			String strWarehouseNo,
			String strDockNo,String strUntreadNo)throws Exception;
	
	/**
	 * 封板
	 * @param strDetail
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscClosePal(String strDetail)throws Exception;
	/**
	 * 获取临时表数据
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Ridata_CheckPalTmpModel> queryCheckPalTmp(String enterpriseNO,
			String strWarehouseNo,String strDockNo,
			String strSUntreadNo2,String strUntreadNo)throws Exception;
	
	

	
}
