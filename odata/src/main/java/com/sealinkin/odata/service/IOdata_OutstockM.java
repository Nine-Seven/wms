package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_OutstockDirectModel;


/**
 * 模块名称：拣货发单
 * 模块编码：3401,3402
 * 创建：周欢
 */
public interface IOdata_OutstockM {
	/**
	 * 获取拣货发单头档信息
	 * @author 周欢
	 * @param strSendFlag 
	 * @param strSendFlag 
	 * @throws Exception 
	 */
	public ExtListDataBo<Odata_OutstockDirectModel> getOdataOutStockDirect(
			String enterpriseNo,
			String warehouseNo,
			String owner,
			String flag,
			String str,
			String strOwnerNo, String strSendFlag,String strIndustryFlag) throws Exception;
	
	 /**
     * 拣货发单 出货单别、波次……下拉
     * @author 周欢
     */
	public List<ComboxBo> getOdata_GetCombo(
			String enterpriseNo,
			String warehouseNo,
			String owner,
			String flag,
			String str,
			String strSendFlag,
			int start,
			int pagesize,
			String strIndustryFlag) ;
	/**
	 * @发单
	 * @param warehouseNo
	 * @param owner
	 * @param workerNo
	 * @param dockNo
	 * @param pickWorker
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public MsgRes send(
			String enterpriseNo,
			String warehouseNo,
			String workerNo,
			String dockNo,
			String strOutStockType,
			String strPrintPaperType,
			String pickWorker,
			String str,
			String ownerNo,String expType,String waveNo,
			String areaNo,String operateType,String batchNo) throws Exception;
	/**
	 * @按配送对象发单

	 */
	public MsgRes sendObj(
			String enterpriseNo,
			String warehouseNo,
			String owner,
			String workerNo,
			String dockNo,
			String strOutStockType,
			String strPrintPaperType,
			String pickWorker,
			String str) throws Exception;
	
	/*
	 * @func 获得批属性字符串
	 * @return list 货主列表
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public List<String> getArticleAttrString(String strFlag) throws Exception;
	
	/**
	 * 拣货批量发单过程
	 * @param strDetail
	 * @return
	 * @throws Exception
	 */
	public MsgRes sendTscAuto(
			String strDetail,
			String enterpriseNo,
			String strDockNo,
			String strWorkerNo)throws Exception;
	/*
	 * 自动发单
	 * 2016.06.22
	 * czh
	 */
	public MsgRes sendOrderAuto(
			String strDetail,
			String enterpriseNo,
			String strDockNo,
			String strWorkerNo)throws Exception;
	/*
	 * 获取批次发单信息
	 * 2016.06.23
	 * czh
	 */
	public List<Odata_OutstockDirectModel> getBatchSendOrder(
			String enterpriseNo,
			String strWarehouseNo,
			String strOwnerNo,
			String str);
}
