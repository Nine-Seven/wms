package com.sealinkin.ridata.service;

import java.util.Date;
import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.ridata.model.Ridata_CheckDModel;
import com.sealinkin.ridata.model.Ridata_CheckPalTmpModel;

public interface IRidata_CheckService {
	
	
	/**
	 * 返配扫描验收列
	 * @param str
	 * @param pageBo
	 * @return
	 */
	public ExtListDataBo<Ridata_CheckDModel> getRidata_CheckDList(
			String enterpriseNO,String warehouseNo,String strWhereSql);
	

	/*
	 * 获取货主...下拉
	 */
	public List<ComboxBo> getComboList(String enterpriseNO,String warehouseNo, String workerOwner,
			String strFlag);

	//获得返配汇总单号
	/*public List<ComboxBo> getUntreadNoList(String workerOwner,String enterpriseNO,
			String warehouseNo, String strPageSql, String strWhereSql) throws Exception;
*/
    //保存
	public MsgRes save(String enterpriseNO,String strWarehouseNo,String jsonDetail) throws Exception;
	
	
	/**
	 * 获取码头下拉
	 * @param strWarehouseNo
	 * @param strWhereSql
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> queryDockCombo(String enterpriseNO,String strWarehouseNo,String strWhereSql)throws Exception;
	
	/**
	 * 校验条码
	 * @param strWarehouseNo
	 * @param strBarcode
	 * @param strOwnerNo
	 * @param strSImportNo
	 * @param strSUntreadNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes queryBarcode(
			String enterpriseNO,
			String strWarehouseNo,
			String strBarcode,
			String strOwnerNo,
			String strSImportNo,
			String strSUntreadNo)throws Exception;
	
	
	/**
	 * 获取临时表数据
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Ridata_CheckPalTmpModel> queryCheckPalTmp(String enterpriseNO,
			String strWarehouseNo,String strDeviceNo,String strLabelId,String strDockNo)throws Exception;
	
	/**
	 * 获取单号的状态
	 * @param strWarehouseNo
	 * @param SUntreadNo
	 * @return
	 * @throws Exception
	 */
/*	public List<String> getSUntreadNoStatus(String enterpriseNO,String strWarehouseNo,String SUntreadNo)throws Exception;
*/	
	/**
	 * 封板
	 * @param strDetail
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscClosePal(String strDetail)throws Exception;
	
	/**
	 * 获取标签号
	 * @param strWarehouseNo
	 * @param strSupplierNo
	 * @param strWorkerNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscGetLabelNo(String enterpriseNO,String strWarehouseNo,String strSupplierNo,String strWorkerNo,String strDeviceNo,String SUntreadNo,String strOwnerNo,String strArticleNo)throws Exception;
	/**
	 * 获取预备箱数
	 * @param strWarehouseNo
	 * @param strSupplierNo
	 * @param strWorkerNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes getSupperAllotCell(String enterpriseNO,String strWarehouseNo,String strUntreadNo2,String strDockNo,String strUser_Id,String strDeviceNo,String strOwnerNo)throws Exception;
	
	/**
	 * 获取临时表标签
	 * @param strWarehouseNo
	 * @param strDockNo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Ridata_CheckPalTmpModel> queryTmpLabelList(
			String enterpriseNo,
			String strWarehouseNo,String strDeviceNo)throws Exception;
	
	/**
	 * 获取临时表标签明细
	 * @param strWarehouseNo
	 * @param strDockNo
	 * @param strLabelNo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Ridata_CheckPalTmpModel> queryTmpLabelDetail(
			String enterpriseNo,
			String strWarehouseNo,String strDockNo,String strLabelNo)throws Exception;
	
	/**
	 * 返配RF验收 输入流水号
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes InPutSerialNo(String strRecvData)throws Exception;
	
	/**
	 * 返配RF验收 输入条码
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes InPutBarcode(String strRecvData)throws Exception;	

	/**
	 * 验收 保存
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscCheckSave(String strEnterpriseNo,String strWareHouseNo,
			String strOwnerNo,
			String strsUntreadNo,
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
			String strSupplierNo,
			String strFixPalFlag,
			String strBusinessType)throws Exception;	
	/**
	 * 返配RF验收 保存
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscRfCheckSave(String strRecvData)throws Exception;	
	
	/**
	 * 返配RF验收 输入板号
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes CheckLabel(String strRecvData)throws Exception;


	/**
	 * 返配RF验收 扫描码头号
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes CheckDock(String strRecvData)throws Exception;
	
	/***
	 * 资源试算，并返回相应的单据信息（天天惠）
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscResourceCalculation(String strRecvData)throws Exception;

	/***
	 * 扫描条码确认（天天惠）
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscScanBarcodeNo(String strRecvData)throws Exception;

	/***
	 * 返配封箱（天天惠）
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscClosBox(String strRecvData)throws Exception;

	/***
	 * RF获取过季品预上储位（天天惠）
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes getCellNo(String strRecvData)throws Exception;

	/***
	 * RF根据分播墙获取商品的类型（天天惠）
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes getqualityByWallNo(String strRecvData)throws Exception;

	/***
	 * 获取po_no
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes GetPoNo(String strRecvData)throws Exception;


	/***
	 * 铁越扫描条码
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */           
	public MsgRes GetMixRIScanBarcodeNo(String strRecvData)throws Exception;
	/***
	 * 铁越RF反配验收取批次信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */           
	public MsgRes GetMixRILotInfo(String strRecvData)throws Exception;
	/***
	 * 铁越RF反配验收
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */           
	public MsgRes tscMixRICheckSave(String strRecvData)throws Exception;
	/***
	 * 铁越获取RF反配验收信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */           
	public MsgRes GetMixRICheckInfo(String strRecvData)throws Exception;
	/***
	 * 铁越获取RF反配封板
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */           
	public MsgRes tscMixRIClosePal(String strRecvData)throws Exception;
}
