package com.sealinkin.ridata.service;

import com.sealinkin.comm.model.MsgRes;

public interface IRidata_DivideReceiptService {

	
	public MsgRes divide(String enterpriseNo,String warehouseNo,String workerNo, String strLabelNo) throws Exception;
	
	public MsgRes closeBox(String enterpriseNo,String warehouseNo, String workerNo, String strCellNo,String workSpaceNo) throws Exception;

}
