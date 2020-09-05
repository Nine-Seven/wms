package com.sealinkin.idata.service;

import java.util.Date;
import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.Idata_CheckDModel;
import com.sealinkin.idata.model.Idata_CheckMModel;
import com.sealinkin.idata.model.Idata_CheckPalModel;
import com.sealinkin.idata.model.Idata_Check_ResulModel;
import com.sealinkin.idata.model.Idata_ImportDModel;
import com.sealinkin.idata.model.Idata_ImportMModel;
import com.sealinkin.idata.model.Idata_ImportSdModel;
import com.sealinkin.protocolExchange.idata.IdataGetLotAnswerModel;

/**
 * 验收
 * @author JUN
 */
@SuppressWarnings({"rawtypes"})
public interface Iidata_CheckService {
	
	
	/**
	 * 填充进货汇总单下拉
	 * @param strWarehouseNo
	 * @param strOwnerNo
	 * @return
	 * @throws Exception
	 */
	public List<Idata_ImportSdModel> queryIdataImportMMCombo(
			String enterpriseNo,String strWarehouseNo,String strOwnerNo,String strFlag,String strWheresql)throws Exception;
	/**
	 * 填充供应商下拉(混合板验收)
	 * @param enterpriseNo
	 * @param workerOwner
	 * @param warehouseNo
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> getSupplierNo(String enterpriseNo,String workerOwner, String warehouseNo,String str)throws Exception;
	
	/**
	 * 填充单据类型(混合板验收)
	 * @param enterpriseNo
	 * @param workerOwner
	 * @param warehouseNo
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> getImportType(String enterpriseNo,String workerOwner, String warehouseNo,String str)throws Exception;
	
	//取品质
	public List<ComboxBo> getQualityCombo(String enterpriseNo,String warehouseNo,String strOwnerNo,String str)throws Exception;

	
	/**
     * 校验预约流水号
     * @param strEnterpriseNo
     * @param strWarehouseNo
     * @param str
     * @return
     * @throws Exception
     */
	public MsgRes checkOrderSerial(String strEnterpriseNo,String strWarehouseNo,String workerOwner,String str)throws Exception;

	/**
	 * 填充采购单号下拉(混合板验收)
	 * @param enterpriseNo
	 * @param warehouseNo
	 * @param ownerNo
	 * @param strWheresql
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> getPoNoList(String enterpriseNo,String warehouseNo,String ownerNo,String strWheresql,String str)throws Exception;
	/**
	 * 获取单据列表(混合板验收)
	 * @param strEnterpriseNo
	 * @param strWarehouseNo
	 * @param strOwnerNo
	 * @param str
	 * @param pageBo
	 * @return
	 * @throws Exception
	 */
	 
	public ExtListDataBo<Idata_ImportMModel> getPoNoAndSImportNoList(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String strWheresql,String str,String orderNo,PageBo pageBo)throws Exception;
	/**
	 * 获取汇总单明细(混合板验收)
	 * @param strEnterpriseNo
	 * @param wheresql
	 * @param pageBo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Idata_ImportDModel> getImportSDList(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String str,PageBo pageBo)throws Exception;
	/**
	 * 填充助记码/条码(混合板验收)
	 * @param enterpriseNo
	 * @param warehouseNo
	 * @param ownerNo
	 * @param strWheresql
	 * @param str
	 * @return
	 * @throws Exception
	 */
	
	public List<ComboxBo> getIdentifierOrBarcode1List(String enterpriseNo,String warehouseNo,String ownerNo,String strWheresql,String str)throws Exception;
 

	/**
	 * 获取验收明细
	 * @param strWarehouseNo
	 * @param strOwner
	 * @param strWheresql
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Idata_ImportSdModel> queryIdataImportSd(String enterpriseNo,String strWarehouseNo,String strOwner,
			String strWheresql)throws Exception;
	
	/**
	 * 获取进货单号
	 * @param strSImportNo
	 * @return
	 * @throws Exception
	 */
	public List<Idata_ImportMModel> getImportNo(String enterpriseNo,String warehouseNo,String strOwnerNo,String strSImportNo)throws Exception;
	
	/**
	 * 获取进货汇总单号
	 * @param strImportNo
	 * @return
	 * @throws Exception
	 */
	public List<Idata_ImportMModel> getSImportNo(String strOwnerNo,String strImportNo)throws Exception;
	
	/**
	 * 根据商品编号和批号获取相应的生产日期
	 * @param strLotNo
	 * @param strArticleNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes queryLotProduceDate(String strRecvData)throws Exception;
	
	/**
	 * 保存(流水板)
	 * @param strJsonMaster
	 * @param strJsonDetail1
	 * @param printFlag 
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscSaveCheck(String strJsonMaster,String strJsonDetail1, String printFlag)throws Exception;
	
	/**
	 * 保存(人工板)
	 * @param strJsonMaster
	 * @param strJsonDetail1
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscSaveCheck2(String strJsonMaster,String strJsonDetail1)throws Exception;
	
	/**
	 * 前台单品验收校验是否能验收
	 * @param strJsonMaster
	 * @param strJsonDetail1
	 * @param printFlag 
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscCheckExists(String strSImportNo,String strJsonDetail1)throws Exception;

	/**
	 * 保存(混合板)
	 * @param strJsonMaster
	 * @param strJsonDetail1
	 * @param printFlag 
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscSaveCheck3(String strJsonMaster,String strJsonDetail1, String printFlag)throws Exception;
	//验收确认货主下拉
	public List<ComboxBo> ownerForConfirmCombo(String enterpriseNo,String warehouseNo, String strWorkerOwner)throws Exception;

	//判断验收确认单是否存在 
	public List<String> checkNoCheck(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner,String strQuery) throws Exception;

	/**
	 * 获取验收确认单头档
	 * @param strWarehouseNo
	 * @param strOwnerNo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Idata_CheckMModel> queryCheckM(
			String enterpriseNo,String strWarehouseNo,String strWheresql,PageBo poPageBo)throws Exception;
	
	/**
	 * 获以验收确认单明细
	 * @param strCheckNo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Idata_CheckDModel> queryCheckD(String enterpriseNo,String strWarehouseNo,String strCheckNo)throws Exception;
	
	/**
	 * 获取验收确认板明细
	 * @param strCheckNo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Idata_CheckPalModel> queryCheckPal(String enterpriseNo,String strWarehouseNo,String strCheckNo)throws Exception;
	
	/**
	 * 验收确认
	 * @param strJsonMaster
	 * @param flag 
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscConfirm(String strJsonMaster, String flag)throws Exception;
	
	/**
	 * 填充货主下拉
	 * @param strOwnerNo
	 * @param strFlag
	 * @param strWheresql
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> queryOwnerCombo(String enterpriseNo,String warehouseNo, String strWorkerOwner)throws Exception;
	
	/**
	 * 校验板号
	 * @param strLabelNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes IdataCheckLabelNo(String strWarehouseNo,String strLabelNo)throws Exception;
	
	/**
	 * 校验板号
	 * @param strWarehouseNo
	 * @param strSImportNo
	 * @param strLabelNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes IdataCheckLabelNo(String strEnterpriseNo, String strWarehouseNo, String strSImportNo,
			String strLabelNo)throws Exception;
	/**
	 * 校验商品条码
	 * @param strWarehouseNo
	 * @param strBarcode
	 * @param strOwnerNo
	 * @param strSImportNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes IdataCheckBarcode(
			String strWarehouseNo,String strBarcode,
			String strOwnerNo,String strSImportNo)throws Exception;

	
	public MsgRes idataCheckSerialNo(String strRecvData,String strClassType) throws Exception;

	/**
	 * 普通保存验收数据
	 * @return
	 * @throws Exception
	 */
	public List tscSaveCheckDataIS(String strEnterpriseNo,String strWareHouseNo,String strOwnerNo,String strsImportNo,String strArticleNo,
			String strBarcode,Double nPackingQty,Double nCheckQty,String strPrinterGroupNo,String strDockNo,
			String strWorkerNo,String strCheckTools,Integer nIsAdd,String strQuality,
			Date dtProduceDate,Date dtExpireDate,String strLotNo,
			String strRSV_BATCH1,String strRSV_BATCH2,String strRSV_BATCH3,String strRSV_BATCH4,
			String strRSV_BATCH5,String strRSV_BATCH6,String strRSV_BATCH7,String strRSV_BATCH8,
			String strLabelNo,String strSubLabelNo,String strFixPalFlag,String strBusinessType,String strTemperature
			)throws Exception;
	
	/**
	 * 封板验收主程序
	 * @return
	 * @throws Exception
	 */
	public List tscClosePalMain(String strEnterpriseNo,String strWareHouseNo,String strOwnerNo,String strsImportNo,String strSCheckNo,
			String strLabelNo,String strWorkerNo,String strFixPalFlag)throws Exception;
	
	/**
	 * 根据验收汇总单写标签数据、定位指示和库存
	 * @return
	 * @throws Exception
	 */
	public List tscIdataSCheckLocateInstock(String strEnterpriseNo,String strWareHouseNo,String strScheckNo,String strUserId,String strCheckTool,
			String strspecifyCellNo,String strLabelNo)throws Exception;
	
	/**
	 * 进货定位程序入口
	 * @return
	 * @throws Exception
	 */
	public List tscLocateMain(String strEnterpriseNo,String strWareHouseNo,String strOwnerNo,String strLocateNo,
			String strPrintFlag,String strWorkNo)throws Exception;
	
	/**
	 * 上架发单
	 * @return
	 * @throws Exception
	 */
	public List tscInsertInstock(
			String strEnterpriseNo,
			String strWareHouseNo,
			String strWorkerNo,
			String strLocateNo,
			String strDockNo,
			String strPrintType)throws Exception;
	
	/**
	 * 
	 * @param strArticleNo
	 * @param strLotNo
	 * @return
	 */
	public List<IdataGetLotAnswerModel> queryBdefArticleLotManage(String strArticleNo,String strLotNo)throws Exception;
	
	
	/**
	 * 获以验收确认单明细
	 * @return
	 * @throws Exception
	 */
	public MsgRes queryRFCheckD(String strRecvData)throws Exception;

	/**
	 * 存储验收码头检验
	 * @param strWarehouseNo
	 * @param strDockNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes IdataISDockValidate(String strRecvData)throws Exception;
	/**
	 * 直通验收码头检验
	 * @param strWarehouseNo
	 * @param strDockNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes IdataDockValidate(String strRecvData)throws Exception;
	
	/**
	 * RF封板及写标签数据、定位指示和库存
	 * @param strWareHouseNo
	 * @param strOwnerNo
	 * @param strsImportNo
	 * @param strSCheckNo
	 * @param strLabelNo
	 * @param strWorkerNo
	 * @param strFixPalFlag
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscRfClosePalWriteLocate(String strRecvData)throws Exception;
	
	/**
	 * 直通保存验收数据
	 * @return
	 * @throws Exception
	 */
	public List tscSaveCheckDataID(String strEnterpriseNo,
			String strWareHouseNo,
			String strOwnerNo,
			String strsImportNo,
			String strArticleNo,
			String strBarcode,
			Double nPackingQty,
			Double nCheckQty,
			String strPrinterGroupNo,
			String strDockNo,
			String strWorkerNo,
			String strCheckTools,
			Integer nIsAdd,
			String strQuality,
			Date dtProduceDate,
			Date dtExpireDate,
			String strLotNo,
			String strRSV_BATCH1,
			String strRSV_BATCH2,
			String strRSV_BATCH3,
			String strRSV_BATCH4,
			String strRSV_BATCH5,
			String strRSV_BATCH6,
			String strRSV_BATCH7,
			String strRSV_BATCH8,
			String strLabelNo,
			String strSubLabelNo,
			String strFixPalFlag,
			String strBusinessType
			)throws Exception;
	/**
	 * 直通验收确认,一单一验，可直接对进货单做结案
	 * @return
	 * @throws Exception
	 */
	public List tscDataComfireCheck(String strEnterpriseNo, 
			String strWareHouseNo,
			String strsImportNo,
			String strSCheckNo,
			String strWorkerNo,
			String strDockNo)throws Exception;

	/**
	 * 直通分播发单
	 * @return
	 * @throws Exception
	 */
	public List tscOdataDivideBegin(String strEnterpriseNo,String strWareHouseNo,
			String strOwnerNo,
			String strDockNo,
			String strSourceNo,
			String strSContainerNo,
			String strWorkerNo,//预定分播人员
			String strUserId,
			String strPrintFlag //--是否打印 0=否 1=是
			)throws Exception;
	/**
	 * 直通封板业务处理
	 * @return
	 * @throws Exception
	 */
	public MsgRes IdataCloseLabelID(String strEnterpriseNo,
			String strWareHouseNo,
			String strsImportNo,
			String strSCheckNo,
			String strWorkerNo,
			String strDockNo) throws Exception;
	
	/**
	 * 校验是否超量
	 * @return
	 * @throws Exception
	 * huangb 20160711 新增strOverFlag传参 是否超品
	 */
	public MsgRes CheckExcess(
			String strEnterpriseNo,
			String strWareHouseNo,
			String strOwnerNo,
			String strSimportNo,
			String strArticleNo,
			Double strPackingQty,
			Date dtProduceDate,
			Date dtExpireDate,
			Double ncheckQty,
			String strOverFlag) throws Exception;

	/**
	 * 进货定位程序
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscPLocateMain(String strEnterpriseNo,
			String strWareHouseNo,
			String strOwnerNo,
			String strLocateNo,
			String strPrintFlag,
			String strWorkNo) throws Exception;	
	

	
	/**
	 * 扫物流码过程
	 */
	public MsgRes tscInsert_ScanLabelNoLog(
			String strEnterpriseNo,
			String strWarehouseNo,
			String strOwnerNo,
			String strScanBarcode,
			String strSourceNo,
			String strArticleNo,
			String strCellNo,
			String strCheckType,
			String strWorkerNo,
			String strLabelNo,
			String strType) throws Exception;
	
	/**
	 * 删除bdef_scan_log表
	 */
	public MsgRes tscDeleteBdefScanLog(String strRecvData)throws Exception;
	
	
	/**
	 * 普通保存验收数据自動調用封板程序
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscISSaveAndColse(String strRecvData)throws Exception;	
	
	/**
	 * 直通保存天天惠
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscRfIdataCheckIDTTH(String strRecvData)throws Exception;	
	
	
	/**
	 * 校验临时表中是否还有数据
	 * @param strWarehouseNo
	 * @param strSImportNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes checkPalTmp(String enterpriseNo,String strWarehouseNo,String strSImportNo)throws Exception;

	/**
	 * 获取验收确认未封板明细
	 * @param sImportNo 
	 */
	public ExtListDataBo<Idata_CheckPalModel> queryUnCheckPal(String enterpriseNo,String warehouseNo,String sImportNo) throws Exception;

	//获取批次
	public List<ComboxBo> getlotNo(String enterpriseNo,String warehouseNo,String strQuery,String strWheresql)throws Exception;
	
	
	public MsgRes tscIdataCheckIDTTH(String strEnterpriseNo,
			String strWareHouseNo, 
			String strOwnerNo,
			String strsImportNo, 
			String strArticleNo, 
			String strBarcode,
			Double nPackingQty, 
			Double nCheckQty,
			String strPrinterGroupNo,
			String strDockNo,
			String strWorkerNo,
			String strCheckTools,
			Integer nIsAdd,
			String strQuality,
			Date dtProduceDate,
			Date dtExpireDate,
			String strLotNo,
			String strRSV_BATCH1,
			String strRSV_BATCH2,
			String strRSV_BATCH3,
			String strRSV_BATCH4,
			String strRSV_BATCH5,
			String strRSV_BATCH6,
			String strRSV_BATCH7,
			String strRSV_BATCH8,
			String strLabelNo,
			String strSubLabelNo,
			String strFixPalFlag,
			String strBusinessType) throws Exception;
	
	/**
	 * 直通保存验收数据(天天惠)
	 * @return
	 * @throws Exception
	 */
	public List tscSaveCheckDataIDTTH(String strEnterpriseNo,String strWareHouseNo,
			String strOwnerNo,
			String strsImportNo,
			String strArticleNo,
			String strBarcode,
			Double nPackingQty,
			Double nCheckQty,
			String strPrinterGroupNo,
			String strDockNo,
			String strWorkerNo,
			String strCheckTools,
			Integer nIsAdd,
			String strQuality,
			Date dtProduceDate,
			Date dtExpireDate,
			String strLotNo,
			String strRSV_BATCH1,
			String strRSV_BATCH2,
			String strRSV_BATCH3,
			String strRSV_BATCH4,
			String strRSV_BATCH5,
			String strRSV_BATCH6,
			String strRSV_BATCH7,
			String strRSV_BATCH8,
			String strLabelNo,
			String strSubLabelNo,
			String strFixPalFlag,
			String strBusinessType
			)throws Exception;	
		
	/**
	 * rf扫物流码
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscRfInsert_ScanLabelNoLog(String strRecvData)throws Exception;
	
	/**
	 * rf校验是否能验收
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscRfCheckExists(String strRecvData)throws Exception;	
	
	/**
	 * Rf校验板号
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscRfCheckLabelNo(String strRecvData)throws Exception;
	
	/**
	 * Rf直通存储验收事件
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscRfIdataCheckID(String strRecvData)throws Exception;
	
	/**
	 * Rf直通封板事件
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscRfIdataCloseLabelID(String strRecvData)throws Exception;

	/**
	 * RF直通验收获取商品信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */	
	public MsgRes IDCheckBarcodeForSave(String strRecvData)throws Exception;
	
	/**
	 * RF直通验确定商品是否可以验收
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */	
	public MsgRes IDGetArticleNoInfoByBarcodeAndPoNo(String strRecvData)throws Exception;
	
	//未封箱验收数据取消
	public MsgRes delcheckPalTmp(String strEnterpriseNo,String strWareHouseNo,
			String strOwnerNo,String strCheckNo,String strsImportNo) throws Exception;

	
	//打印验收单
	public MsgRes tscPrintCheckNo(String strEnterpriseNo,String strWareHouseNo,
			String workSpaceNo,String workerNo,
			String strSCheckNo) throws Exception;
	/**
	 * 存储验收扫描商品条码
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes IdataISCheckBarcode(String strRecvData)throws Exception;
	/**
	 * 直通验收保存并封板
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscIDCheckSaveAndClosePal(String strRecvData)throws Exception;

	/**
	 * 获取商品包装信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscGetArticlePacking(String strRecvData) throws Exception;
	
	//超量验收--新增进货单以及汇总单明细 huangbin 20160712
	public MsgRes tscImportDetailByOver(String strRecvData) throws Exception;
	
	/** 获取品质码表数据
	 * huangb 20160712
	 */
	public MsgRes getDeffieldvalInfo(String strRecvData) throws Exception;
	
	/**获取验收结果 用来导出
	 * huangb 20160716
	 */
	public List<Idata_Check_ResulModel> tscGetCheckResult(
			String strEnterpriseNo, String strOwnerNo, String strWarehouseNo,
			String strPoNo) throws Exception;
}
