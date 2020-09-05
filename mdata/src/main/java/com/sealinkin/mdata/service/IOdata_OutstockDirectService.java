package com.sealinkin.mdata.service;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_OutstockDirectModel;

public interface IOdata_OutstockDirectService {

	ExtListDataBo<Odata_OutstockDirectModel> getAreaList(String enterpriseNo,String str,
			String warehouseNo,Integer start, Integer pageSize);

	ExtListDataBo<Odata_OutstockDirectModel> getOdata_OutstockDirectList(
			String enterpriseNo,String areaNo,  String warehouseNo, String ownerNo,
			String outstockType, Integer start, Integer pageSize);

	MsgRes send(String enterpriseNo,String warehouseNo,String workerNo, String celltype,
			String outstockworker, String str, String workSpaceNo, String operateType) throws Exception;


/*	List<ComboxBo> getOdata_GetWaveCombo(String enterpriseNo,String warehouseNo, String workerOwner);
*/
}
