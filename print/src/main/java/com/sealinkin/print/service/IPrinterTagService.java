package com.sealinkin.print.service;

import com.sealinkin.bdef.model.Bdef_DefarticleModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface IPrinterTagService {

	ExtListDataBo<Bdef_DefarticleModel> getDefarticle(String workerOwner,
			String strQuery, PageBo poPagebo);
	
	public MsgRes Get_PrintTask(String strRecvData)throws Exception;
	
	public MsgRes Update_PrintTask(String strRecvData)throws Exception;
	
	public MsgRes Get_ReportInfo(String strRecvData)throws Exception;
	
	public MsgRes Get_FieldInfo(String strRecvData)throws Exception;

	public MsgRes sendTask(String printNumber, String barcode, String enterpriseNo,String warehouseNo,
			String workStationNo, String rgstName, String articleNo) throws Exception;

	
}
