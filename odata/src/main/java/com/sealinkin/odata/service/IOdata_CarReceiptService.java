/**
 * 模块名称：出车回单
 * 模块编码：3926
 * 创建：hcx
 */

package com.sealinkin.odata.service;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.odata.model.Odata_CarReceiptModel;

@SuppressWarnings("rawtypes")
public interface IOdata_CarReceiptService {
	//取配送物流箱信息列表
	public ExtListDataBo<Odata_CarReceiptModel> getCarReceiptList(
			String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner,
			String queryStr,PageBo pageBo)throws Exception;
	public MsgRes save(String queryStr)throws Exception;

	
}
