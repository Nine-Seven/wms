package com.sealinkin.fcdata.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefarticleModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.fcdata.model.Fcdata_PlanDModel;
import com.sealinkin.fcdata.model.Fcdata_PlanMModel;

/*
 * @手建盘点单接口
 * @周欢
 */
public interface IFcdata_PlanService {

	// 获得盘点计划单头档
	public ExtListDataBo<Fcdata_PlanMModel> getPlanM(
			String enterpriseNo,
			String warehouseNo,
			String str,
			String strOwnerNo,
			String queryStr,
		    PageBo pageBo)throws Exception;
	
	// 获得盘点计划单明细
	public ExtListDataBo<Fcdata_PlanDModel> getFcdataPlanD(String enterpriseNo,String warehouseNo,String[] wheresql,
			PageBo pageBo)throws Exception;
	
	// 保存盘点计划单

	public MsgRes saveFcdata_Plan(String planM,String planD1)throws Exception;
	
	// 删除盘点计划单
	public MsgRes deleteFcdata_Plan(String enterpriseNo,String warehouseNo,String planNo)throws Exception;
	
	// 定位
	public MsgRes sendFcdataPlan(String planM)throws Exception;
	
	//结案
	public MsgRes sendClosePlan(String planM)throws Exception;
	//取消订单
	public MsgRes closeOrder(String enterpriseNo,String warehouseNo,String workerNo,String planNo)throws Exception;
	//储位下拉 
	public List<ComboxBo> getCdef_DefCellCombo(String enterpriseNo,String strWarehouseNo, String strJson,
			String str,String strOwnerNo);
	
	 // 储区下拉
	public List<ComboxBo> getCdef_DefAreaCombo(String enterpriseNo,
			String strWarehouseNo, String str,String strWheresql,
			String strOwnerNo);

	 //通道下拉
	public List<ComboxBo> getCdef_DefStockCombo(String enterpriseNo,String strWarehouseNo, String str,String strWheresql,
			String strOwnerNo);

	// 仓库下拉 
	public List<ComboxBo> getCdef_DefWareCombo(String enterpriseNo,
			String strWarehouseNo, String str,String strWheresql,String strOwnerNo);

	//通过artilce_no获取商品类别名称和编码
	public List<Bdef_DefarticleModel> getGroupByArticle(
			String enterpriseNo,
			String strArticleNo);

	// 获取商品下拉
	public List<ComboxBo> query_BdefArticleCombo(String enterpriseNo,
			String strGroupNo, String strWheresql);

	//获得单头状态
	public List<String> getStatus(String enterpriseNo,String string, String workerOwner,
			String strPlanNo, int i, int j);
}
