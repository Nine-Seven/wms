package com.sealinkin.rodata.service;


import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_OutstockDModel;

public interface IRodata_ScanQCService {

	MsgRes getRodata_ScanM(String currEnterpriseNo, String warehouseNo,
			String workerOwner, String strQuery)throws Exception;

	ExtListDataBo<Rodata_OutstockDModel> getRodata_ScanD(String strQuery,
			String enterpriseNo, String warehouseNo, Integer start, Integer limit)throws Exception;

	ExtListDataBo<Rodata_OutstockDModel> ScanDNot(String enterpriseNo,
			String warehouseNo, String strQuery, String strWorker,
			Integer start, Integer limit)throws Exception;

	MsgRes checkArticleNo(String currEnterpriseNo, String barcode, String poNo,
			String warehouseNo)throws Exception;

	MsgRes tscScan(String currEnterpriseNo, String warehouseNo, String ownerNo,
			String recedeNo, String articleNo,String scanNum, String workerNo)throws Exception;

	List<ComboxBo> getPoNoQC(String currEnterpriseNo, String warehouseNo,
			String strQuery, String pageSql)throws Exception;

	MsgRes recede(String currEnterpriseNo, String warehouseNo, String ownerNo,
			String recedeNo, String strWorker)throws Exception;

	MsgRes recedeAgain(String currEnterpriseNo, String warehouseNo,
			String ownerNo, String recedeNo, String strWorker)throws Exception;

}
