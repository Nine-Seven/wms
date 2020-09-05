/**
 * 拣货参数配置service接口
 * @author chensr
 */
package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.wms.model.Wms_OutpickStrategyModel;

public interface IBdef_WmsOutPickService {
	/*
	 * @func 获得拣货参数配置信息
	 * @param strQuery 查询条件， pageBo 显示条数的限制
	 * @return list  查询获得的货主参数配置信息
	 */
	public ExtListDataBo<Wms_OutpickStrategyModel> getWmsOutPickList(String enterpriseNo,String str,PageBo pageBo) throws Exception;
	
	/*
	 * @func 保存拣货参数信息
	 */
	public  void saveWmsOutPick(String str)throws Exception;
	 

}
