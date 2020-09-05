package com.sealinkin.bdef.service;



import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.wms.model.Wms_InterfaceLogModel;

public interface IBdef_ControlLogService {
	//获取接口监控日志列表
	public ExtListDataBo<Wms_InterfaceLogModel> getInterfaceLogList(
			String enterpriseNo,
			PageBo pageBo)throws Exception;
	
	//获取接口监控日志汇总列表
	public ExtListDataBo<Wms_InterfaceLogModel> getInterfaceLogSumList(
			String enterpriseNo,
			PageBo pageBo)throws Exception;

}

