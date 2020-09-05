package com.sealinkin.rodata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_RecedeDModel;
import com.sealinkin.rodata.model.Rodata_RecedeMModel;
import com.sealinkin.stock.model.Stock_ContentModel;


public interface IRodata_RecedeMService {


	List<String> getRodata_RecedeMByArticleNO(
			String enterpriseNo, String articleNo, String recedeNo, Integer start, Integer limit);

	MsgRes saverodata_recede(String jsonMaster, String jsonDetail) throws Exception;

	ExtListDataBo<Rodata_RecedeDModel> getRodata_RecedeD(String str,String recedeNo, Integer start, Integer limit);


	MsgRes send(String enterpriseNo,String workerNo, String warehouseNo,String workSpaceNo, String str) throws Exception;

   //检查商品唯一性
	public MsgRes checkPoNo(String enterpriseNo,String strWarehouseNo,String strPoNo)throws Exception;

	

}






























