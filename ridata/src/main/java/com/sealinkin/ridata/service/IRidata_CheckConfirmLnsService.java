package com.sealinkin.ridata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.ridata.model.Ridata_CheckDModel;
import com.sealinkin.ridata.model.Ridata_CheckMModel;
import com.sealinkin.ridata.model.Ridata_CheckPalTmpModel;

public interface IRidata_CheckConfirmLnsService {

	ExtListDataBo<Ridata_CheckMModel> queryCheckM(String enterpriseNo,String warehouseNo,
			String strQuery, PageBo poPageBo) throws Exception;

	ExtListDataBo<Ridata_CheckDModel> queryCheckD(String enterpriseNo,String warehouseNo,
			String sCheckNo)throws Exception;

	MsgRes tscCheckPalTmp(String enterpriseNo,String warehouseNo, String ownerNo, String sUntreadNo,
			String sCheckNo, String workerNo, String dockNo)throws Exception;

	MsgRes saveChenkPalTmp(String jsonDetail)throws Exception;



}
