package com.sealinkin.odata.service;

import com.sealinkin.comm.model.MsgRes;

/* 装并板
 * Add by sunl 2016年3月1日 
 */
public interface IOdata_MergePalService {
 
	//扫箱扫板
	public MsgRes tscScanPlateNo(String strRecvData)throws Exception;
 

}
