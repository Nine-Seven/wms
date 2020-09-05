package com.sealinkin.rodata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_OutstockDirectModel;


public interface IRodata_OutstockDirectService {


	ExtListDataBo<Rodata_OutstockDirectModel> getRodata_OutstockDirect(
			String str, Integer start, Integer limit);

	List<ComboxBo> getSupplierAndRecedeNoCombo(String enterpriseNo,String warehouseNo,String owenrNo,String str,String wheresql, String flag, Integer start,
			Integer pagesize);

	MsgRes send(String enterpriseNo,String warehouseNo,String workerNo,String sendWorker, String str, String workSpaceNo) throws Exception;


}
