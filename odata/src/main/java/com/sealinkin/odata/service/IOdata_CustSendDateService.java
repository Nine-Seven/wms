package com.sealinkin.odata.service;
/*
 * 出货日期确认
 * hkl
 * 3922
 */
import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_ExpDModel;
import com.sealinkin.odata.model.Odata_ExpMModel;

public interface IOdata_CustSendDateService {

	/*
	 * 获得单据数据
	 *
	 */
	public ExtListDataBo<Odata_ExpMModel> getOdata_CustSendDate(String strEnterpriseNo,String strWarehouseNo,//仓别
			String strStr,String strQuery
			)throws Exception ;
	
    //获取客户下拉
	public List<ComboxBo> queryCustCombo(String strEnterpriseNo,String strWarehouseNo,//仓别
			String strStr
			)throws Exception ;
	
	/*
	 * 根据确认日期获取材积
	 *
	 */
	public MsgRes queryVolumn(String strEnterpriseNo,String strWarehouseNo,//仓别
			String str)throws Exception ;
	/*
	 * 日期确认
	 *
	 */
	public MsgRes tscCustSendComfirm(String str,String workerNo)throws Exception ;
	
	
}
