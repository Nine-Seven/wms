/**
 * 货主参数配置service接口
 * @author chensr
 */
package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.wms.model.Wms_OwnerBaseModel;

public interface IBdef_WmsOwnerBaseService {
	/*
	 * @func 获得货主参数配置信息
	 * @param strQuery 查询条件， pageBo 显示条数的限制
	 * @return list  查询获得的货主参数配置信息
	 */
	public ExtListDataBo<Wms_OwnerBaseModel> getWmsOwnerBaseList(String enterpriseNo,String str,PageBo pageBo) throws Exception;
	
	/*
	 * @func 保存货主参数信息
	 */
	public  void saveWmsOwnerBase(String str)throws Exception;
	
	/*
	 * @func 根据group_no获取子colname下拉按钮
	 * @return list colName列表
	 */
	public List<ComboxBo> getColNameComboList(String str)throws Exception;
	
	/*
	 * @func 获取groupNo下拉列表
	 * @return groupNo列表
	 */
	public List<ComboxBo> getGroupNoComboList()throws Exception;
	
	/*
	 * @func 获取subGroupNo下拉列表(窗口)
	 * @return subGroupNo 列表
	 */
	public List<ComboxBo> getSubGroupNoComboList(String strQuery)throws Exception;
	
	/*
	 * @func 获取ndefine下拉列表
	 * @return Sdefine 货主列表
	 */
	public List<ComboxBo> getSdefineComboList(String strQuery)throws Exception;
	
	/*
	 * @func 根据colName获取memo
	 * @return ndefine memo列表
	 */
	public List<String> getMemo(String strQuery)throws Exception;

	/*
	 * 获取业务下拉按钮(用于UI界面的业务下拉)
	 */
	public List<ComboxBo> getGroupNoComboListForUI(String enterpriseNo)throws Exception;

	/*
	 * @func 获取subGroupNo下拉列表(UI)
	 * @return subGroupNo 列表
	 */
	public List<ComboxBo> getSubGroupNoComboListForUI(String enterpriseNo,String strQuery)throws Exception;

}
