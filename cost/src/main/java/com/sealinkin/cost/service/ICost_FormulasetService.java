/**
 * 模块名称：计费公式管理接口
 * 模块编码：B101
 * 创建：chensr 
 */
package com.sealinkin.cost.service;

import java.io.File;
import java.util.List;

import com.sealinkin.cost.model.Cost_FormulaArticlefamilyModel;
import com.sealinkin.cost.model.Cost_FormulaDiscountModel;
import com.sealinkin.cost.model.Cost_FormulasetModel;
import com.sealinkin.bset.po.Bill_FormulasetTmp;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface ICost_FormulasetService {

	//保存计费公式
	public void saveFormulaset(String str) throws Exception;
	//保存计费公式
	public void saveFormulaset2(String str) throws Exception;
	
	//获取计费公式信息
	public ExtListDataBo<Cost_FormulasetModel> getFormulasetList(
			String enterpriseNo,String warehouseNo, String str, PageBo pageBo,String workerOwner) throws Exception; 

	//获取用于查找的货主下拉
	public List<ComboxBo> getOwnerNoForQuery(String enterpriseNo,String warehouseNo,String workerOwner) throws Exception;

	//获取UI界面的项目名称
	public List<ComboxBo> getBillingProjectForUI(String enterpriseNo,String warehouseNo,String str) throws Exception;

	//判断计费项目编号是否唯一
	public List<String> billingProjectCheck(String enterpriseNo,String str, String warehouseNo,String ownerNo) throws Exception;

	//获取计费类型
	public List<ComboxBo> getBillingTypeForWind(String enterpriseNo,String str) throws Exception;
	//获取取值策略
	public List<ComboxBo> getValueFlagCombo(String enterpriseNo,String billingType,
			String billingUnit);
	//获取参数说明
	public List<String> getRemarkCombo(String billingType, String billingUnit,
			String ruleId);
	//获取商品群组
	public List<ComboxBo> getarticleFamilyNoCombo(String enterpriseNo,String workerOwner,String str)throws Exception;
	//保存计费公式
	public void saveFamilyUnitPrice(String str) throws Exception;
	/**
	 * 上传Excel导入数据库
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public MsgRes upLoad(File file,String strWarehouseNo,String strEnterpriseNo,String strUserId)throws Exception;
	/**
	 * Excel数据转List
	 * @return
	 * @throws Exception
	 */
	public List<Bill_FormulasetTmp> changeexcelBean(String fileName,String strWarehouseNo,String strEnterpriseNo)throws Exception;
	
	/**
	 * 解析Excel
	 * @param execelFile
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<List> impExcel(String execelFile)throws Exception;
	//获取优惠策略
	public ExtListDataBo<Cost_FormulaDiscountModel> getFormulaDiscountList(
				String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner, String str, PageBo pageBo) throws Exception; 
	//获取计费项目与商品群组关系列表
	public ExtListDataBo<Cost_FormulaArticlefamilyModel> getFormulaArticlefamilyList(
				String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner, String str, PageBo pageBo) throws Exception; 
	//获取商品群组信息列表
	public ExtListDataBo<Cost_FormulaArticlefamilyModel> getFormulaArticlefamily(
				String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner, 
				String falg, String str,String strWhereSql,  PageBo pageBo) throws Exception; 
	//包含商品群组添加商品群组
	public boolean saveFormulaArticlefamilyList(String str)throws Exception;
	//包含商品群组移除商品群组
	public boolean deleteFormulaArticlefamilyList(
			String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String strBillingProject,String strFamilyNo)throws Exception;
	//保存计费公式-优惠策略
	public void saveFormulaDiscount(String str) throws Exception;
	//获取新增窗口计费类型信息
	public ExtListDataBo<Cost_FormulasetModel> getFormulasetForWindList(
				String strEnterpriseNo,String strWarehouseNo, String workerOwner,String str, PageBo pageBo) throws Exception; 

	//删除计费项目
	public MsgRes deleteBillingProject(String strEnterpriseNo,String strWarehouseNo,String ownerNo,String billingType,String strBillingProject) throws Exception;

}
