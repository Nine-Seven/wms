package com.sealinkin.report.service;

import java.util.List;

import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.wms.model.Wms_Defcustom_DModel;

public interface IAuto_Service {

	//获取显示明细字段
	List<Wms_Defcustom_DModel> getCombo(String enterpriseNo, String customId)throws Exception;

	//执行
	MsgRes implement(String enterpriseNo, String warehouseNo, String customId,
			String value0, String value1, String value2, String value3,
			String value4, String value5, String value6, String value7,
			String value8, String value9)throws Exception;

}
