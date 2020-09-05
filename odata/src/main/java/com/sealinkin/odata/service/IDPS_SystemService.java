package com.sealinkin.odata.service;

import com.sealinkin.comm.model.MsgRes;

public interface IDPS_SystemService {

	
	/**
	 * 电子标签数据
	 * @param strWarehouseNo
	 * @param strCtrlStatusNo 控制机站号
	 * @param nUseType 作业类型
	 * @return
	 * @throws Exception
	 */
	public MsgRes getDPSAddress(String strEnterpriseNo,String strWarehouseNo,
			int nUseType, String strDeviceGroupNo )throws Exception;
	
	/**
	 *  获取分播箱明细
	 * @param strWarehouseNo
	 * @param nUseType
	 * @param nCtrlNo
	 * @param nAreaNo
	 * @param nStockNo
	 * @param strBoxNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes getDivideDetail(String strEnterpriseNo,String strWarehouseNo,
			Integer nUseType,Integer nCtrlNo,Integer nAreaNo,
			Integer nStockNo,String strBoxNo )throws Exception;
	
	/**
	 * 分播数据保存
	 * @param strWarehouseNo
	 * @param instockNo
	 * @param labelNo
	 * @param articleNo
	 * @param barcode
	 * @param packingQty
	 * @param realQty
	 * @param userId
	 * @param paperUserId
	 * @param operateTools
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscSaveDivideData(String strEnterpriseNo,
			String strWarehouseNo,
			String instockNo,
			String labelNo,
			String articleNo,
			String barcode,
			float packingQty,
			double realQty,
			String  userId,
			String  paperUserId,
			Integer  operateTools
			)throws Exception;
	
	/**
	 * 封箱 
	 * @param strWarehouseNo
	 * @param cellNo
	 * @param userId
	 * @param paperUserId
	 * @param operateTools
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscCutBOX(String strEnterpriseNo, 
			String strWarehouseNo,
			String cellNo,
			String userId,
			String paperUserId,
			Integer  operateTools,
			String pntName
			)throws Exception;

	public MsgRes CheckWorkerNo(String enterpriseNo, String reqObj)throws Exception;

	//获取当前任务号所在的巷道，区域，最小箱码
	public MsgRes getMinArea(String strRecvData)throws Exception;
	
	//DPS获取当前任务所在巷道的拣货任务。
	public MsgRes getAllTast(String strRecvData)throws Exception;
	
	//pds拣货回单
	public MsgRes tscReceipt(String strRecvData)throws Exception;

}
