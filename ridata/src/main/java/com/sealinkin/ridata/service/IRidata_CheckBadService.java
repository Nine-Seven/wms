package com.sealinkin.ridata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.ridata.model.Ridata_CheckDModel;
import com.sealinkin.ridata.model.Ridata_CheckPalTmpModel;

public interface IRidata_CheckBadService {
	
	
	/**
	 * 返配次品扫描验收列
	 * @param str
	 * @param pageBo
	 * @return
	 */
	public ExtListDataBo<Ridata_CheckDModel> getRidata_CheckBadDList(
			String enterpriseNO,String warehouseNo,String strWhereSql);
	

	/*
	 * 获取货主...下拉
	 */
	public List<ComboxBo> getComboList(String enterpriseNO,String warehouseNo, String workerOwner,
			String strFlag);


	// 储位下拉	
	public List<ComboxBo> getCdef_DefCellCombo(String enterpriseNo,String warehouseNo,
			 int i, int j);
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
