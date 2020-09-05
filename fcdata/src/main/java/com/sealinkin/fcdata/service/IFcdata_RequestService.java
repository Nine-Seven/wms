package com.sealinkin.fcdata.service;

import java.util.Date;
import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.fcdata.model.Fcdata_CheckDModel;
import com.sealinkin.fcdata.model.Fcdata_CheckMModel;
import com.sealinkin.protocolExchange.fcdata.AnsPackingModel;
import com.sealinkin.protocolExchange.fcdata.ReqFCScanBarcodeModel;

/**
 * 盘点回单接口
 */
public interface IFcdata_RequestService {
	
	///////////////////////////////////初盘回单///////////////////////////////////////////////////
	
	// 获取初盘回单头档
	public ExtListDataBo<Fcdata_CheckMModel> queryFcdataCheckM(
			String enterpriseNo,
			String strWarehouseNo,
			String strOwnerNo,
			String strFcdataType,
			PageBo poPageBo)throws Exception;
	
	// 获取初盘回单明细 
	public ExtListDataBo<Fcdata_CheckDModel> queryFcdataCheckD(
			String enterpriseNo,
			String strWarehouseNo,
			String strOwnerNo,
			String strWheresql)throws Exception;
	// 保存盘点回单
	public MsgRes tscSaveFcdataCheckD(String strDetail)throws Exception;
	
	 //盘点保存
	public MsgRes saveFcdateD(
			String enterpriseNo,String checkNo, String checkType,
			Double checkQty,String cellNo, String ownerNo,  
			String articleNo, Date expireDate,
			String lotNo, Date produceDate, String quality, String rsvBatch1,
			String rsvBatch2, String rsvBatch3, String rsvBatch4,
			String rsvBatch5, String rsvBatch6, String rsvBatch7,
			String rsvBatch8, String warehouseNo,
			Double packQty,String checkWorker,String labelNo,
			String subLabelNo,String stockType,String stockValue,String Addflag)throws Exception;
	
	// 根据单号填充储位
	public List<ComboxBo> queryCdefDefCellCombo(
			String enterpriseNo,
			String strWarehouseNo,
			String strFlag,
			String strCheckNo,
			String strWheresql) ;
	
	// 获取批号下拉 
	public List<Fcdata_CheckDModel> queryLot(
			String enterpriseNo,
			String strWarehouseNo,
			String articleNo,
			String produceDate)throws Exception;
	
	//盘点回单--新增品项--根据货位找货主
	public MsgRes queryOwnerCellNo(
			String enterpriseNo,String strWarehouseNo, String strWheresql
		)throws Exception;
	
	
	//商品下拉选择 
	public List<Fcdata_CheckDModel> queryArticleInfo(
			String enterpriseNo,
			String strWarehouseNo,
			String strWheresql)throws Exception;
	
	//校验主板号是否存在库存表，并且是否与储位匹配
	public MsgRes checkLabelNo(
			String enterpriseNo,
			String strWarehouseNo,
			String strLabelNo,
			String strCellNo)throws Exception;
	
	// 校验主板号与子板号是否存在库存表，并且是否与储位匹配
	public MsgRes checkLabelNoAndSubLabelNo(
			String enterpriseNo,
			String strWarehouseNo,
			String strLabelNo,
			String strSubLabelNo,
			String strCellNo)throws Exception;
	
	// 无差异保存
	public MsgRes tscNoDifferenceSave(
			String enterpriseNo,
			String strWarehouseNo,
			String strWheresql)throws Exception;
	
	//零回单
	public MsgRes tscZeroReceiptSave(
			String enterpriseNo,
			String strWarehouseNo,
			String strWheresql)throws Exception;
	
	// 回单确认
	public MsgRes tscFcdataconfirm(
			String enterpriseNo,
			String strWarehouseNo,
			String strWheresql)throws Exception;
	
	///////////////////////////////////////////////////////////////////////////////////////////
	
	
	//获取复盘/三盘回单头档
	public ExtListDataBo<Fcdata_CheckMModel> querySecondFcdataCheckM(
			String enterpriseNo,
			String strWarehouseNo,
			String strOwnerNo,
			String strCheckType,
			PageBo poPageBo)throws Exception;
	
	
	
	
	// 根据批号获取日期
	public List<Fcdata_CheckDModel> getExpireDateByLot(
			String enterpriseNo,
			String strWarehouseNo,
			String strWheresql)throws Exception;
	
	////////////////////////////////////RF////////////////////////////////////////////////////////////
	
	//1.扫描盘点流水号
	public MsgRes fcdataCheckSerialNo(String strRecvData)throws Exception;
	
    //2.扫描储位
	public MsgRes fcdataCheckCellNo(String strRecvData)throws Exception;
	
	//盘点确认
	public MsgRes saveFcdataSure(String strEnterpriseNo,String strCheckNo, String strCheckType,
			String strWarehouseNo,String strOwnerNo,String strWorkerNo,String strLabelNo,String flag) throws Exception;
	//扫描商品条码
	public MsgRes fcdataCheckBarcode(ReqFCScanBarcodeModel resMod);

	//扫描商品条码获取包装信息
	public MsgRes fcdataScanBarcode(String strRecvData) throws Exception;
	
	public MsgRes GetArticleInfoByBarcode(String strRecvData)throws Exception;

	public MsgRes GetProductDateByArticleNO(String strRecvData)throws Exception;
	
	public MsgRes GetArticlePacking(String strRecvData)throws Exception;
	
	public List<AnsPackingModel> GetArticlePacking(String strEnterpriseNo,String strArticleNo) throws Exception;

	public MsgRes StockContentQuery(String strRecvData)throws Exception;
	/**
	 * 扫描标签获取商品
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes DpsScanLabelNo(String strRecvData)throws Exception;
	/**
	 * 获取商品电子标签分播储位
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes DpsGetCell(String strRecvData)throws Exception;
	/**
	 * 获取商品分播信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes DpsGetCellInfo(String strRecvData)throws Exception;
}
