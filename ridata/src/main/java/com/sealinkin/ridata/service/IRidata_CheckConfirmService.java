package com.sealinkin.ridata.service;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.ridata.model.Ridata_CheckDModel;
import com.sealinkin.ridata.model.Ridata_CheckMModel;
import com.sealinkin.ridata.model.Ridata_CheckPalModel;

/**
 * 返配验收确认
 * @author hkl
 */
public interface IRidata_CheckConfirmService {
	
	

	/**
	 * 获取验收确认单头档
	 * @param strWarehouseNo
	 * @param strOwnerNo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Ridata_CheckMModel> queryCheckM(
			String strenterpriseNo,
			String strWarehouseNo,String strWheresql,PageBo poPageBo)throws Exception;
	
	/**
	 * 获以验收确认单明细
	 * @param strCheckNo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Ridata_CheckDModel> queryCheckD(String strenterpriseNo,String strWarehouseNo,String strCheckNo)throws Exception;
	
	/**
	 * 获取验收确认板明细
	 * @param strCheckNo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Ridata_CheckPalModel> queryCheckPal(
			String strEnterpriseNo,
			String strCheckNo)throws Exception;
	/**
	 * 获取验收确认未封板明细
	 * @param sImportNo 
	 */
	public ExtListDataBo<Ridata_CheckPalModel> queryUnCheckPal(String strEnterpriseNo,String strSUntreadNo) throws Exception;

	/**
	 * 验收确认
	 * @param strJsonMaster
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscConfirm(String strJsonMaster)throws Exception;
	
	/**
	 * 校验临时表中是否还有数据
	 * @param strWarehouseNo
	 * @param strSImportNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes checkPalTmp(String strEnterpriseNo,String strWarehouseNo,String strSUntreadNo)throws Exception;
	
	/**
	 * 校验计划数量和验收数量
	 * @param strWarehouseNo
	 * @param strSImportNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes checkQty(String strEnterpriseNo,String strWarehouseNo,String strWheresql)throws Exception;
	
}
