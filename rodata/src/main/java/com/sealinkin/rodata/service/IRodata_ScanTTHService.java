package com.sealinkin.rodata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_OutstockDModel;

public interface IRodata_ScanTTHService {

	List<ComboxBo> getRodata_ScanM(String enterpriseNo, String warehouseNo,
			String workerOwner, String strQuery, Integer start, Integer limit);
     //未扫描商品信息
	ExtListDataBo<Rodata_OutstockDModel> getRodata_ScanD(String strQuery,
			Integer start, Integer limit) throws Exception;
	
	//已扫描
	ExtListDataBo<Rodata_OutstockDModel> getRodata_ScanDNot(String strQuery,
			Integer start, Integer limit) throws Exception;
	
	MsgRes checkArticleNo(String enterpriseNo,String barcode, String outstockNo,String WarehouseNo)throws Exception;
	//检验该下架单是否扫描完成
	MsgRes checkIsFinish(String enterpriseNo,String outstockNo,String WarehouseNo)throws Exception;
	
	MsgRes saveOutstock(String enterpriseNo,String articleNo, String outstockNo, String workerNo,
			String workSpaceNo, String warehouseNo, String labelNo)throws Exception;

	MsgRes save(String enterpriseNo,String outstockNo, String workerNo, String workSpaceNo,
			String warehouseNo, String ownerNo, String scanNo)throws Exception;

	MsgRes getLoadBox(String enterpriseNo,String warehouseNo, String scanNo)throws Exception;

	MsgRes closeBox(String enterpriseNo,String workSpaceNo, String warehouseNo, String ownerNo,
			String scanNo, String labelNo)throws Exception;
	//获取标签列
	ExtListDataBo<Rodata_OutstockDModel> getScanPackLabel(String enterpriseNo,String warehouseNo,String strQuery,
			Integer start, Integer limit) throws Exception;
	
	//取标签的扫描量
	MsgRes tscRodataBoxQty(String enterpriseNo,String warehouseNo, String labelNo,String outstockNo)throws Exception;

	
}
