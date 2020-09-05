package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.bset.model.Bset_Role_ListModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_ExpensesListModel;
import com.sealinkin.odata.model.Odata_ExpMModel;

/**
 * 模块名称：RF出库扫描
 * 创建：huangb 20160519
 */
public interface IOdata_ExpCheckService {

	
	/**
	 * RF获取上位系统下传的出货单号
	 * @param strRecvData
	 * @throws Exception
	 */
	public MsgRes GetSourceExpNo(String strRecvData)throws Exception;
    
	/**
	 * 出库扫描-整单扫描
	 * @param strRecvData
	 * @throws Exception
	 */
	public MsgRes tscExpCheckScanOrder(String strRecvData) throws Exception;

	/**
	 * 根据扫描码获取商品信息
	 * @param strRecvData
	 * @throws Exception
	 */
	MsgRes GetArticleInfoByScan(String strRecvData) throws Exception;
	
	/**
	 * 出库扫描-商品扫描保存数据 huangb 20160523
	 * @param strRecvData
	 * @throws Exception
	 */
	public MsgRes tscExpCheckScanBarcode(String strRecvData) throws Exception;
	
	/**
	 * 获得出库审核信息列表  hj
	 * @param strEnterpriseNo,strWarehouseNo,strWorkerOwner,strQuery,poPageBo
	 * @throws Exception
	 */
	public ExtListDataBo<Odata_ExpMModel> getOdata_NoCkeckOrderList(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,
			String strQuery,
			PageBo poPageBo)throws Exception;
	
	//取出货申请单下拉列表
	public List<ComboxBo> getOrderNumberList(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,String strWheresql,String str,String strTableName)throws Exception;
	
	
	//取客户编码下拉列表
	public List<ComboxBo> getCustomNumberList(String strEnterpriseNo,String strWarehouseNo,
		String strWorkerOwner,String strWheresql,String str,String strTableName)throws Exception;
	
	
	//获得货主商品编码下拉列表
	public List<ComboxBo> getCustomGoodsNumberList(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,String strWheresql,String str)throws Exception;
	
	//获得状态下拉列表
	public List<ComboxBo> getStatusList(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,String strWheresql,String str)throws Exception;
	
	//审核操作
	public MsgRes checkOrder(String strEnterpriseNo, String strWarehouseNo,String strUserId,String strSourceexpNo)throws Exception;
	
}