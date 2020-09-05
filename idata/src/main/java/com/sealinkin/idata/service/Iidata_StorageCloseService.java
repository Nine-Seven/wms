package com.sealinkin.idata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.idata.model.Idata_ImportSdModel;

public interface Iidata_StorageCloseService {

	public List<ComboxBo> getCdef_DefCellCombo(String enterpriseNo, String warehouseNo, String str,
			String strWheresql, int i, int j)throws Exception;

	public List<Idata_ImportSdModel> queryIdataImportMMCombo(
			String enterpriseNo,String warehouseNo, String strOwnerNo,
			String strFlag,String strWheresql,String strQuery)throws Exception;

	public List<ComboxBo> queryOwnerCombo(String enterpriseNo,String workerOwner) throws Exception;

	public ExtListDataBo<Idata_ImportSdModel> queryIdataImportSd(
			String enterpriseNo,String warehouseNo, 
			String workerOwner, String strWheresql) throws Exception;

	public MsgRes tscSaveCheck(String strJsonMaster, String strJsonDetail1, String cellNo) throws Exception;

	public List<String> queryLotProduceDate(String enterpriseNo,String warehouseNo, 
			String date, String sImportNo,String articleNo)throws Exception;

}
