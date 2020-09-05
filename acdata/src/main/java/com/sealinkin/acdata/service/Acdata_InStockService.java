package com.sealinkin.acdata.service;

import java.util.List;

import com.sealinkin.acdata.model.Acdata_InstockDModel;
import com.sealinkin.acdata.model.Acdata_InstockMModel;
import com.sealinkin.acdata.model.Acdata_OrderDModel;
import com.sealinkin.acdata.model.Acdata_OrderMModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface Acdata_InStockService{
	//获取入库单头档
	public ExtListDataBo<Acdata_InstockMModel> getAcdataInstockMList(String warehouseNo,String queryStr,PageBo pageBo);
	//获取入库单明细
	public ExtListDataBo<Acdata_InstockDModel> getAcdataInstockDList(String wheresql,PageBo pageBo);
	//获取单号选择列表
	public ExtListDataBo<Acdata_OrderMModel> getAcdataCheckSourceNoList(String queryStr,PageBo pageBo);
	//填充货主单号
	public List<Acdata_OrderMModel> queryAcdataSImport(String strWheresql)throws Exception;
	//根据输入的货主单号获取接单头档
	public List<Acdata_OrderMModel> queryAcdataOrderMList(String strSourceNo)throws Exception;
	//获取接单表明细
	public List<Acdata_OrderDModel> getAcdataOrderDList(String strOrderNo);
	//保存
	public MsgRes save(String jsonMaster, String jsonDetail)throws Exception;
	//修改
	public MsgRes edit(String strInstockNo,String strArticleName,String strBarcodeNo, String strIstockQty, String strIstockWt, String strIstockVl)throws Exception;
	//审核
	public MsgRes send(String strWarehouseNo,String strOrderNo,String strSourceNo,String strInstockNo)throws Exception;

}
