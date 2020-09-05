package com.sealinkin.comm.service;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.wms.model.Wms_JobConfigModel;

/**
 * 后台管理控制
 * @author hkl
 *
 */
public interface IWmsJobConfigService {
	
	//后台定时调用存储过程（类似JOB）
	public MsgRes tscjobConfig()throws Exception;
	
	//获取后台管理控制表list
	public ExtListDataBo<Wms_JobConfigModel> getWms_JobConfugList(String currEnterpriseNo,String warehouseNo,PageBo pageBo)throws Exception;

	//修改后台管理配置的状态
	public MsgRes updateWmsJobConfig(String enterpriseNo,String warehouseNo,String procName,String flag) throws Exception;
}
