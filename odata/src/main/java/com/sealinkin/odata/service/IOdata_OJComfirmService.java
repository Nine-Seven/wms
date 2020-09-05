package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_ExpDModel;
import com.sealinkin.odata.model.Odata_ExpMModel;

public interface IOdata_OJComfirmService {
	
	//整理确认
	//public MsgRes tscPOM_AerrangeConfirm(String strEnterpriseNo,String strWarehouseNo,String strLabelNo,String strWorkerNo)throws Exception;
	
	
	/*
	 * 获得自提头档数据
	 *
	 */
	public List<Odata_ExpMModel> getOdata_OJComfirmM(String strEnterpriseNo,String strWarehouseNo,//仓别
			String strStr
			)throws Exception ;
	
	
	/*
	 * 获得自提明细数据
	 *
	 */
	public List<Odata_ExpDModel> getOdata_OJComfirmDComfirm(String strEnterpriseNo,String strWarehouseNo,//仓别
			String strQuery
			)throws Exception ;
	
	/*
	 * 确认
	 *
	 */
	public MsgRes tscOComfirm(String strEnterpriseNo,String strWarehouseNo,//仓别
			String workerNo,String workSpaceNo,String strQuery
			)throws Exception ;
	
	
}
