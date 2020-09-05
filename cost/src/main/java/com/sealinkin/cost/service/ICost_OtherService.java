package com.sealinkin.cost.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_OtherListModel;
import com.sealinkin.cost.model.Cost_OtherSetModel;
/**
 * 模块名称：杂项费用维护
 * 模块编码：B203
 * 创建：hcx 
 */
public interface ICost_OtherService {

	//获取杂项费用信息列表
	public ExtListDataBo<Cost_OtherListModel> getCostOtherList(
			String strEnterpriseNo,String strWarehouseNo, String strWorkerOwner,String str, PageBo pageBo) throws Exception;
	//导出杂项费用信息
	public List getCostOtherList2(
				String strEnterpriseNo,String strWarehouseNo, String strWorkerOwner,String strQuery, Integer intRequestFlag) throws Exception;
	//获取用于查找的货主下拉
	public List<ComboxBo> getOwnerNoForQuery(String strEnterpriseNo,String strWarehouseNo, String strWorkerOwner) throws Exception;
	//获取费用代码的下拉
	public List<ComboxBo> getCostNoForUI(String strEnterpriseNo,String strWarehouseNo, String strWorkerOwner,String str) throws Exception;
	//获取费用代码的下拉
	public List<ComboxBo> getCostNoForWindown(String strEnterpriseNo,String str) throws Exception;
	//获取其他费用信息列表
	public ExtListDataBo<Cost_OtherSetModel> getCostOtherSetList(
			String strEnterpriseNo,String queryStr, PageBo pageBo) throws Exception;
	//保存杂项费用清单
	public void saveCostOtherList(String str) throws Exception;
	//删除杂项费用清单
	public MsgRes deleteCostOtherList(String str,String strCostDate) throws Exception;
	//保存其他费用
	public void saveCostOtherSet(String str) throws Exception;
	//校验费用代码是否已存在
	public String checkCostNo(String strEnterpriseNo,String strCostNo)throws Exception;
	//删除其他费用
	public MsgRes deleteCostOtherSet(String strEnterpriseNo,String strCostNo,String str) throws Exception;

}
