package com.sealinkin.acdata.service;

import java.util.List;

import com.sealinkin.acdata.model.Acdata_OrderMModel;
import com.sealinkin.acdata.model.Acdata_OutstockDModel;
import com.sealinkin.acdata.model.Acdata_OutstockMModel;
import com.sealinkin.acdata.model.Acdata_StockcontentModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface Acdata_OutStockService{
	
	public ExtListDataBo<Acdata_OutstockMModel> getOut_MList(String warehouseNo,String queryStr,PageBo pageBo);
	
	public ExtListDataBo<Acdata_OutstockDModel> getOut_DList(String wheresql,PageBo pageBo);
	
	public ExtListDataBo<Acdata_OrderMModel> getOrder_MList(String warehouseNo,PageBo pageBo);

	public ExtListDataBo<Acdata_StockcontentModel> getContent_List(String warehouseNo,String sourceNo,String orderNo);
	
	public MsgRes saveOut(String OUTM,String OUTD)throws Exception;
	
	public List<Acdata_OrderMModel> getsourceNo(String warehouseNo,String strSourceNo,String strWheresql);

	public ExtListDataBo<Acdata_OrderMModel> getOutMBySourceNo(String warehouseNo,String sourceNo);
	
	MsgRes saveTrues(String strHouseNo,String strOrderNo,String strSourceNo,String strOutStockNO) throws Exception;
}
