package com.sealinkin.rodata.service;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_OutstockDModel;
import com.sealinkin.rodata.model.Rodata_OutstockMModel;

public interface IRodata_OutstockMTTHService {

	public ExtListDataBo<Rodata_OutstockMModel> getRodataOutstockM(
			String enterpriseNo, String warehouseNo, Integer start, Integer limit, String strQuery)throws Exception;

	public ExtListDataBo<Rodata_OutstockDModel> getRodata_OutstockD(
			String strQuery, String flag, Integer start, Integer limit)throws Exception;

	public MsgRes savePaper(String workerNo, String outUserId, String str,
			String workSpaceNo)throws Exception;

	public MsgRes saveLabel(String workerNo, String outUserId, String str,
			String workSpaceNo)throws Exception;

}
