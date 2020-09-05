package com.sealinkin.idata.service;

import java.util.Date;
import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.Idata_InstockDModel;
import com.sealinkin.idata.model.Idata_InstockMModel;

/*
 *  @上架回单接口
 *  @author 周欢
 */
public interface Iidata_InstockService {
	
	/**
	 * 上架单头档
	 * @param str
	 * @param pageBo
	 * @param strFlag 
	 * @param dateEndDate 
	 * @param dateBeginDate 
	 * @param strInstockNo 
	 * @return
	 */
	public ExtListDataBo<Idata_InstockMModel> getInstockMList
	(String enterpriseNo,String warehouseNo,String Workowner,PageBo pageBo, String strFlag, 
			String queryStr);
	
	/**
	 * 上架单明细
	 * @param str
	 * @param strFlag 
	 * @param pageBo
	 * @return
	 * @throws Exception 
	 */
	public ExtListDataBo<Idata_InstockDModel> getInstockDList(String enterpriseNo,String warehouseNo,String owner,String str,String strFlag, PageBo pageBo) throws Exception;
	/**
	 * 保存上架单
	 */
	public MsgRes save(String str,String str1) throws Exception;

	public List<Idata_InstockMModel> getInstockNoCombo(String enterpriseNo, String warehouseNo,
			String workerOwner, String strWheresql, String strFlag, String strOwnerNo, int i,
			int j);
	/**
     * 储位下拉
     */
	public List<ComboxBo> getCellNoComboList(String enterpriseNo, String warehouseNo,
			String strOwnerNo, String strWheresql, int i, int j);

	/*
	 * @func 获取货主下拉列表
	 * @return list 货主列表
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public List<ComboxBo> getOwnerComboList(String enterpriseNo,String wareHouseNo, String ownerNo, String strFlag) throws Exception;

	/*
	 * @func 获得批属性字符串
	 * @return list 货主列表
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public List<String> getArticleAttrString(String strFlag) throws Exception;
	
	/**
	 * Rf输入板号
	 * @param strWarehouseNo
	 * @param strLabelNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes IdataInstockLabelNo(String strRecvData)throws Exception;
	
	/**
	 * Rf上架扫条码
	 * @param strWarehouseNo
	 * @param strLabelNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes IdataInstockBarcodeNo(String strRecvData)throws Exception;	

	/**
	 * 上架回单	
	 */
	public MsgRes tscSaveIdataInStock(String strEnterpriseNo,
			String strWarehouseNo,
			String strInstockNo,
			String strDestCellNo,
			String strRealCellNo,
			String strLabelNo,
			String strArticleNo,                                
			Date dtProduceDate,
			Date dtExpireDate,
			String strQuality,//品质                                
			String strLotNo,//批次号
			String strRSV_BATCH1, //预留批属性1
			String strRSV_BATCH2, //预留批属性2
			String strRSV_BATCH3, //预留批属性3
			String strRSV_BATCH4, //预留批属性4
			String strRSV_BATCH5, //预留批属性5
			String strRSV_BATCH6, //预留批属性6
			String strRSV_BATCH7, //预留批属性7
			String strRSV_BATCH8, //预留批属性8                                
			Double nPackingQty,
			Double nArticleQty,
			Double nRealQty,
			String strUserId, //上架人
			String strPaperUserId, //回单人
			String strTools,
			Integer nIsAddFlag//是否新增记录 1是0否
			)throws Exception;
	
	/**
	 * Rf上架回单确认
	 */
	public MsgRes tscRfComfireIdataInStock(String strEnterpriseNo,
			String strWarehouseNo,
			String strInstockNo,
			String strUserId,
			String strPaperUserId
			)throws Exception;
	
	/**
	 * RF上架 确认	
	 * 1、调用tscSaveIdataInStock
	 * 2、调用tscRfComfireIdataInStock
	 */
	public MsgRes tscComfire(String strRecvData)throws Exception;	
	
	/**
	 * Rf箱码采集扫储位	
	 * @throws Exception
	 */
	public MsgRes RfBoxCodeInput(String strRecvData)throws Exception;	
	
	/**
	 * RF上架 确认	
	 * 1、调用tscSaveIdataInStock
	 * 2、调用tscRfComfireIdataInStock
	 */
	public MsgRes tscRfBoxCodeOk(String strRecvData			
			)throws Exception;		
	
	
	/**
	 * 条码采集 扫流水
	 */
	public MsgRes barCodeSerialNo(String strRecvData)throws Exception;

	/**
	 * 条码采集 确认
	 */
	public MsgRes tscRfbarCodeOK(String strRecvData)throws Exception;	

	/**
	 * 物流码采集 条码扫描
	 */
	public MsgRes LogisticsBarcodeInput(String strRecvData)throws Exception;

	/**
	 * 条码采集 物流码确认
	 */
	public MsgRes tscRflogisticsCodeOK(String strRecvData)throws Exception;

	/**
	 * 箱码采集 扫流水
	 */
	public MsgRes boxCodeSerialNo(String strRecvData)throws Exception;
}
