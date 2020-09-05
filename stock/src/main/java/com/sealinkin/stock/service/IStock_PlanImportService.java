package com.sealinkin.stock.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.stock.model.Stock_ContentModel;
import com.sealinkin.stock.model.Stock_PlanDModel;
import com.sealinkin.stock.model.Stock_PlanMModel;


public interface IStock_PlanImportService {
	//获取调账单头档信息列表
	public ExtListDataBo<Stock_PlanMModel> getStockPlanMList(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String str,String queryStr,PageBo pageBo)throws Exception;
	//获取商品缺量信息
	ExtListDataBo<Stock_ContentModel> getStockPlanArticleList(String enterpriseNo,String warehouseNo,String str)throws Exception;
	//获取调账单明细信息列表
	public List<Stock_PlanDModel> getStockPlanDList(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String str,PageBo pageBo)throws Exception;
	//校验原调账单号是否存在
	public String checkPoNo(String enterpriseNo, String warehouseNo,String strOwnerNo, String poNo)throws Exception;
	//取商品下拉
	public List<Stock_PlanDModel> getArticle(String articleNo,String strEnterpriseNo)throws Exception;
	//取批号
	public List<ComboxBo> queryLot(String strEnterpriseNo,String articleNo,String produceDate,String lotNo)throws Exception;
	//货位下拉 
	public List<ComboxBo> getCdef_DefCellCombo(String enterpriseNo,String strWarehouseNo, String strJson,
				String str,String strQuery,  int i, int j);
	//校验所输储位商品是否有库存
	public String checkCellNo(String enterpriseNo, String warehouseNo,String strOwnerNo, String cellNo,String articleNo)throws Exception;

	//保存
	public MsgRes save(String strJsonMaster, String strJsonDetail) throws Exception;
	//定位
	public MsgRes send(String enterpriseNo,String workerNo, 
			String warehouseNo, String strDockNo,String str,String strQuery)throws Exception;
	//取消订单
	public MsgRes closeOrder(String strQuery)throws Exception;
	
	//8-4 添加   修改
	public MsgRes changeStock(String strJsonMaster,String strJsonDetail)throws Exception;
		
	//8-4 删除
	public void deleteStock(String currEnterpriseNo,String planNo)throws Exception;
}













