package com.sealinkin.ridata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.ridata.model.Ridata_CheckDModel;
import com.sealinkin.ridata.model.Ridata_CheckMModel;

public interface IRidata_CheckService2 {

	/**
	 * 返配表单验收列
	 * 
	 * @param str
	 * @param pageBo
	 * @return
	 */
	public ExtListDataBo<Ridata_CheckMModel> getRidata_Check_MList(
			String enterpriseNo,
			String warehouseNo, String workerOwner, String queryStr,
			PageBo pageBo);

	/**
	 * 返配表单验收列
	 * 
	 * @param str
	 * @param pageBo
	 * @return
	 */
	public ExtListDataBo<Ridata_CheckDModel> getRidata_Check_DList(
			String enterpriseNo,String warehouseNo, 
			String whereSql, PageBo pageBo);

	public void save(String jsonMaster, String jsonDetail) throws Exception;

	// 获得返配汇总单号
	public List<ComboxBo> getUntreadNoList(String enterpriseNo,String strOwnerNo,
			String strWarehouseNo, String strPageSql) throws Exception;
	//根据返配汇总单号获取货主编码
	public MsgRes getOwnerNo(String strEnterpriseNo,String strWarehouseNo,String strSUntreadNo)throws Exception;

}
