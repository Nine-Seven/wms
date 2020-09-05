package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.stock.model.Stock_LabelDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;

public interface IOdata_CarPlanForTthService {

	List<ComboxBo> getCust(String enterpriseNo, String warehouseNo) throws Exception;

	List<ComboxBo> getLineNoCombo(String enterpriseNo, String warehouseNo,String strQuery) throws Exception;

	ExtListDataBo<Stock_LabelMModel> queryStockLabel(String enterpriseNo,String warehouseNo,
			String lineNo, String custNo) throws Exception;

	ExtListDataBo<Stock_LabelDModel> searchDetail(String enterpriseNo,String warehouseNo,String wheresql, PageBo pageBo);

	MsgRes tscCloseCar(String str,  String workSpaceNo)throws Exception;

	MsgRes saveStockTmp(String enterpriseNo,String warehouseNo, String str)throws Exception;

}
