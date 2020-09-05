package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.jk.model.JkBymSheetwarehouseModel;

public interface IOdata_BYMSheetService {

	//获取BYM出货订单
	ExtListDataBo<JkBymSheetwarehouseModel> getBYMSheet(String str, PageBo pageBo, String workerNo) throws Exception;

	//获取登陆人拥有的仓别
	List<ComboxBo> getWarehouse(String workerNo);

	//保存BYM出货订单
	void saveBYMSheet(String str) throws Exception;

	//删除出货订单
	MsgRes deleteBYMSheet(String warehouseNo, String sheetNo, String rgstName,
			String rgstDate, String corpkey, String sheettype);

	//生成等待数据
	MsgRes createData(String workerNo) throws Exception;

	//获取所属公司
	List<ComboxBo> getCompany()throws Exception;
	
	//获取BYM接口生产的错误信息
	ExtListDataBo<JkBymSheetwarehouseModel> getBymSheetErrorInfo(String str, PageBo pageBo, String warehouseNo) throws Exception;

	//获取单据类型
	List<ComboxBo> getsheetType()throws Exception;	
}
