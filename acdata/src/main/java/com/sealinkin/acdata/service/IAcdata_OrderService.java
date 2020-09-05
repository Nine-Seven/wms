package com.sealinkin.acdata.service;

import java.util.List;

import com.sealinkin.acdata.model.Acdata_OrderDModel;
import com.sealinkin.acdata.model.Acdata_OrderMModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface IAcdata_OrderService {

	//获取订单信息
   public ExtListDataBo<Acdata_OrderMModel> getOrderMList(String warehouseNo,
			String strQuery, PageBo pageBo) throws Exception;

public List<Acdata_OrderDModel> getOrderDList(String strOrderNo) throws Exception;

public MsgRes saveOrUpdate(String orderD, String orderM, String flag) throws Exception;

}
