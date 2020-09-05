/**
 * 货主仓别参数配置service接口
 * @author chensr
 */
package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.wms.model.Wms_WarehouseOwnerBaseModel;

public interface IBdef_WmsWarehouseOwnerBaseService {
	/*
	 * @func 获得货主仓别参数配置信息
	 * @param strWheresql 查询条件， pageBo 显示条数的限制
	 * @return list  查询获得的货主仓别参数配置信息
	 */
	public ExtListDataBo<Wms_WarehouseOwnerBaseModel> getWarehouseOwnerBaselist(
			String enterpriseNo,String warehouseNo, String strWheresql, PageBo pageBo) throws Exception;

	/*
	 * @func 保存货主仓别参数信息
	 */
	public void saveWarehouseOwnerBase(String str) throws Exception;

	/*
	 * @func 根据group_no获取子colname下拉按钮
	 * @return list colName列表
	 */
	public List<ComboxBo> getColNameComboList(String str) throws Exception;

	/*
	 * @func 获取groupNo下拉列表
	 * @return groupNo列表
	 */
	public List<ComboxBo> getGroupNoComboList() throws Exception;;

	/*
	 * @func 获取subGroupNo下拉列表
	 * @return subGroupNo 列表
	 */
	public List<ComboxBo> getSubGroupNoComboList(String strQuery) throws Exception;;

	/*
	 * @func 根据colName获取memo
	 * @return ndefine memo列表
	 */
	public List<String> getMemo(String strQuery) throws Exception;;

	/*
	 * @func 获取sdefine下拉列表
	 * @return sdefine 货主列表
	 */
	public List<ComboxBo> getSdefineComboList(String strQuery) throws Exception;;

	/*
	 * 获取业务下拉按钮(用于UI界面的业务下拉)
	 */
	public List<ComboxBo> getGroupNoComboListForUI(String enterpriseNo,String warehouseNo) throws Exception;


	/*
	 * 获取子业务下拉按钮(用于UI界面)
	 */
	public List<ComboxBo> getSubGroupNoComboListForUI(String enterpriseNo,String warehouseNo,
			String strQuery) throws Exception;

	

	

}
