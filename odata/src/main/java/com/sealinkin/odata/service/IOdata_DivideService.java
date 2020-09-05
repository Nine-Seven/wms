/**
 * 模块名称：分播回单
 * 模块编码：900006
 * 创建：zhouhuan
 */

package com.sealinkin.odata.service;

import java.util.Date;
import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_DivideDModel;
import com.sealinkin.odata.model.Odata_DivideMModel;

@SuppressWarnings("rawtypes")
public interface IOdata_DivideService {
	//取商品条码下拉列表
	public List<ComboxBo> getBarcodeCombo(String enterpriseNo,String workerOwner, String warehouseNo,String str, String strWheresql)throws Exception;

	//获取分播明细
	List<ComboxBo> getOdata_DivideCombo(String strEnterpriseNo,String strWarehouseNo, String strWorkerOwner,
			String strFlag, String strDivide_No, String strWheresql,
			String strCheckFlag, int start, int pagesize) throws Exception;

	/**
	 * 获取分播头档
	 * @param strWarehouseNo
	 * @param strWorkerOwner
	 * @param str
	 * @param start
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	ExtListDataBo<Odata_DivideMModel> getOdata_DivideM(String strEnterpriseNo,String strWorkerOwner,String strWarehouseNo,
			String str,String strFlag, int start, int pageSize) throws Exception;

	/*
	 * 获得分播回单明细
	 */
	ExtListDataBo<Odata_DivideDModel> getOdata_DivideD(String strEnterpriseNo,String workerOwner,
			String str,String strFlag, int start, int pageSize) throws Exception;
	/*
	 * 保存分播回单
	 */
	MsgRes save(String str, String string) throws Exception;

	MsgRes check( String warehouseNo,String labelNo) throws Exception;
	
	/**
	 * 获取分播明细集合RF
	 * @param strWarehouseNo
	 * @param strSContainerNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes queryOdataDivideD(String strRecvData)throws Exception;
	
	/**
	 * 表单拣货回单,写分播单
	 * @return
	 * @throws Exception
	 */
	public List tscSaveOdataDivide(String strEnterPriseNo,String strWarehouseNo,
			String strOwnerNo,
			String strDivideNo,
			String strArticleNo,
			String strDcontainerNo,
			Double articleQty,
			Double nRealQty,
			String strDivideName,
			String strBatchNo,
			String strQuality,
			Date dtProduceDate,
			Date dtExpireDate,
			String strLotNo,
			String strRsvBatch1,
			String strRsvBatch2,
			String strRsvBatch3,
			String strRsvBatch4,
			String strRsvBatch5,
			String strRsvBatch6,
			String strRsvBatch7,
			String strRsvBatch8,
			String strSCellNo,
			String strSContainerNo,
			String strCustNo,
			String strDelvierObj,
			String strUpdtName)throws Exception;
	/**
	 * 获取分播箱信息或明细RF
	 * @return
	 * @throws Exception
	 */
	public MsgRes CheckFlag(String strRecvData)throws Exception;
	/**
	 * 读取箱分播明细RF
	 * @return
	 * @throws Exception
	 */
	public MsgRes getDivideDetail(String strRecvData)throws Exception;
	
	/**
	 * 分播数据保存RF
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscSaveDivideData(
			String strEnterpriseNo,
			String strWareHouseNo,
			String strDivideNo,
			String strArticleNo,
			String strDpsCellNo,
			String strBarcode,
			Double nRealQty,
			String strDivideName,
			String strCustNo,
			String strLabelNo,
			Integer strAddFlag)throws Exception;
	
	
	/**
	 * 储位换箱RF
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscCutBox(String strEnterpriseNo,
			String strWarehouseNo,
			String cellNo,
			String userId,
			String paperUserId,
			Integer  operateTools,
			String pntName)throws Exception;
	
	
	MsgRes tscCancelDivide(String str) throws Exception;
	
	/**
	 * RF写分播单
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscSaveDivide(String strRecvData)throws Exception;

	public MsgRes tscSaveDivide_dps(String strRecvData) throws Exception;
	
	

	/**
	 * RF分播取号
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscDivideGetNO(String strRecvData) throws Exception;
	/**
	 * 读取用户当前区域任务
	 * @param enterpriseNo
	 * @param wareHouseNo
	 * @param userId
	 * @param areaNo
	 * @param stockNo
	 * @param ctrlNo
	 * @param useType
	 * @return
	 * @throws Exception
	 */
	public MsgRes GetUserTask(String enterpriseNo, String wareHouseNo, String userId,
			Integer areaNo, Integer stockNo, Integer ctrlNo, Integer useType) throws Exception;			
	/**
	 * 读取物流箱已分播用户
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes GetBoxUserList(String enterpriseNo, String wareHouseNo, Integer useType,String labelNo) throws Exception;
	/**
	 * 换线
	 * @param strRecvData
	 * @return
	 * @throws Exception 
	 */
	public MsgRes ChangeGroup(String strRecvData) throws Exception;			

}
