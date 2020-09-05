package com.sealinkin.rodata.service;

import java.io.File;
import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.ridata.po.Ridata_UntreadTmp;
import com.sealinkin.rodata.model.Rodata_RecedeMModel;
import com.sealinkin.rodata.po.Rodata_RecedeTmp;
import com.sealinkin.stock.model.Stock_ContentModel;

public interface IRodata_RecedeMTTHService {

	List<ComboxBo> getSupplierNo(String enterpriseNo,String workerOwner, String warehouseNo,String strQuery)throws Exception;
	List<ComboxBo> getRecede_type(String enterpriseNo,String workerOwner, String warehouseNo)throws Exception;

	
	ExtListDataBo<Stock_ContentModel> getRodateRecedeList(String enterpriseNo,String warehouseNo,String str)throws Exception;

	MsgRes send(String enterpriseNo,String workerNo, String warehouseNo,String workSpaceNo, String str,String cellNo)throws Exception;

	ExtListDataBo<Rodata_RecedeMModel> getRodata_RecedeM(String enterpriseNo,String warehouseNo,
			String workerOwner, String strQuery,String str)throws Exception;


	//获取定位次数
	List<ComboxBo> getLocateTime(String currEnterpriseNo, String workerOwner,
			String warehouseNo, String strQuery);

	//退单审核
	MsgRes tscReviewComfir(String workerNo, String workSpaceNo, String jsonMaster)throws Exception;

	//获取储位
	List<ComboxBo> getCdef_DefCellCombo(String currEnterpriseNo,
			String warehouseNo, String str, String strWheresql, int i, int j)throws Exception;

	MsgRes checkCell(String enterpriseNo, String warehouseNo, String ownerNo,
			String recedeNo, String cellNo)throws Exception;
	// 上传Excel导入数据库
	public MsgRes upLoad(File file,String strWarehouseNo,String strEnterpriseNo, String workNo)throws Exception;
			
	//Excel数据转List
	public List<Rodata_RecedeTmp> changeexcelBean(String fileName,String strWarehouseNo,String strEnterpriseNo)throws Exception;
			
	// 解析Excel
	@SuppressWarnings("rawtypes")
	public List<List> impExcel(String execelFile)throws Exception;
	
	//取消订单     7-14
	public MsgRes cancelOrder(String enterpriseNo,String warehouseNo,String workerNo,String recedeNo)throws Exception;

}










