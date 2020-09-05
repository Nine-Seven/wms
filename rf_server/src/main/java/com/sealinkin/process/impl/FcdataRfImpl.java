package com.sealinkin.process.impl;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.socket.CBizAnswerModel;
import com.sealinkin.fcdata.service.IFcdata_RequestService;
import com.sealinkin.listener.SpringContextUtil;
import com.sealinkin.process.IBaseRfService;
import com.sealinkin.protocolExchange.SessionDefine;
import com.sealinkin.protocolExchange.fcdata.ReqFCScanBarcodeModel;
import com.sealinkin.util.ExceptionUtil;

public class FcdataRfImpl implements IBaseRfService {

	@Override
	public CBizAnswerModel doRfApplication(Integer nReqType, String strRecvData) {
		CBizAnswerModel bizAnsMod=new CBizAnswerModel();
		MsgRes msgRes=new MsgRes();
		System.out.println("******传入******");
		System.out.println(nReqType);
		System.out.println("***************");
		try {
			switch(nReqType)
			{
				case SessionDefine.GC_SESSION_TYPE_CH_CheckSerialNo://扫描盘点流水号
					msgRes=FcdataCheckSerialNo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_CH_CheckCellNo://扫描储位
					msgRes=FcdataCheckCellNo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_CH_GetCheckDateByBarcode://扫描储位
					msgRes=FcdataCheckBarcode(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_CH_CheckSave://盘点保存
					msgRes=saveFcdateD(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_CH_CheckReceipt://盘点保存
					msgRes=saveFcdataSure(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_CH_GetPackingInfoByBarcode://扫描商品条码获取包装信息
					msgRes=FcdataScanBarcode(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_CH_InputBarcode://扫描商品条码
					msgRes=GetArticleInfoByBarcode(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_CH_GetPackingInfoByArticleNo://扫描商品条码
					msgRes=GetPackingInfoByArticleNo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_CH_StockContentQuery://库存查询
					msgRes=StockContentQuery(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_DPSCELL_SCANLABEL://扫描标签获取商品
					msgRes=DpsScanLabelNo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_DPSCELL_DPSCELLSEL://获取商品电子标签分播储位
					msgRes=DpsGetCell(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_DPSCELL_GETDPSCELLINFO://获取商品分播信息
					msgRes=DpsGetCellInfo(strRecvData);	
					break;
					
				case SessionDefine.GC_SESSION_TYPE_CH_GetProductDateByArticleNO://根据商品获取生产日期  add by sunl 2016年8月4日
					msgRes=GetProductDateByArticleNO(strRecvData);	
					break;
				default:
					msgRes.setIsSucc(false);
					msgRes.setMsg("功能未定义！");
			}				
			bizAnsMod.setStrResult(msgRes.getIsSucc()?"Y":"N");
			bizAnsMod.setNRequestType(nReqType+SessionDefine.PACK_ACK_RADIX);
			bizAnsMod.setStrData(msgRes.getIsSucc()?(msgRes.getObj()!=null?msgRes.getObj().toString():""):msgRes.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			bizAnsMod.setStrResult("N");
			bizAnsMod.setNRequestType(nReqType+SessionDefine.PACK_ACK_RADIX);
			/*bizAnsMod.setStrData(e.getMessage());*/
			bizAnsMod.setStrData(ExceptionUtil.getCacseMessage(e));
			
		}
		return bizAnsMod;
	}
	
	/***
	 * 扫描盘点流水号
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes FcdataCheckSerialNo(String strRecvData) throws Exception {
		IFcdata_RequestService fcdata_RequestImpl = (IFcdata_RequestService) SpringContextUtil.getBean("fcdata_RequestImpl");
		return (MsgRes)fcdata_RequestImpl.fcdataCheckSerialNo(strRecvData);
	} 
	
	/***
	 * 扫描储位
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes FcdataCheckCellNo(String strRecvData) throws Exception {
		IFcdata_RequestService fcdata_RequestImpl = (IFcdata_RequestService) SpringContextUtil.getBean("fcdata_RequestImpl");
		return (MsgRes)fcdata_RequestImpl.fcdataCheckCellNo(strRecvData);
	} 
	
	/***
	 * 扫描商品条码
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes FcdataCheckBarcode(String strRecvData) throws Exception {
		ReqFCScanBarcodeModel resMod=JSON.parseObject(strRecvData, ReqFCScanBarcodeModel.class);
		IFcdata_RequestService fcdata_RequestImpl = (IFcdata_RequestService) SpringContextUtil.getBean("fcdata_RequestImpl");
		return (MsgRes)fcdata_RequestImpl.fcdataCheckBarcode(resMod);
	} 
	
	/***
	 * 盘点保存
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes saveFcdateD(String strRecvData) throws Exception {
		ReqFCScanBarcodeModel resMod=JSON.parseObject(strRecvData, ReqFCScanBarcodeModel.class);
		Double Qty=0.0;
		if(resMod.getCheckType().equals("1"))
		{
			Qty=resMod.getFirstQty();
		}else if(resMod.getCheckType().equals("2"))
		{
			Qty=resMod.getSecondQty();
		}else if(resMod.getCheckType().equals("3"))
		{
			Qty=resMod.getThirdQty();
		}
		IFcdata_RequestService fcdata_RequestImpl = (IFcdata_RequestService) SpringContextUtil.getBean("fcdata_RequestImpl");
		return (MsgRes)fcdata_RequestImpl.saveFcdateD(resMod.getEnterpriseNo(),
				resMod.getCheckNo(),
				resMod.getCheckType(),Qty,resMod.getCellNo(),
				resMod.getOwnerNo(),resMod.getArticleNo(),
				resMod.getExpireDate(),resMod.getLotNo(),
				resMod.getProduceDate(),resMod.getQuality(),resMod.getRsvBatch1(),
				resMod.getRsvBatch2(),resMod.getRsvBatch3(),resMod.getRsvBatch4(),
				resMod.getRsvBatch5(),resMod.getRsvBatch6(),resMod.getRsvBatch7(),
				resMod.getRsvBatch8(),resMod.getWarehouseNo(),resMod.getPackQty(),
				resMod.getUserID(),resMod.getLabelNo(),resMod.getSubLabelNo(),
				"1","N",resMod.getAddFlag());
	} 
	
	//盘点确认
	private MsgRes saveFcdataSure(String strRecvData) throws Exception {
		ReqFCScanBarcodeModel resMod=JSON.parseObject(strRecvData, ReqFCScanBarcodeModel.class);
		IFcdata_RequestService fcdata_RequestImpl = (IFcdata_RequestService) SpringContextUtil.getBean("fcdata_RequestImpl");
		return (MsgRes)fcdata_RequestImpl.saveFcdataSure(resMod.getEnterpriseNo(),resMod.getCheckNo(),
				resMod.getCheckType(),
				resMod.getWarehouseNo(),
				resMod.getOwnerNo(),
				resMod.getUserID(),
				resMod.getLabelNo(),
				resMod.getFlag());
	} 
	
	//扫描商品条码获取包装信息
	private MsgRes FcdataScanBarcode(String strRecvData) throws Exception {
		IFcdata_RequestService fcdata_RequestImpl = (IFcdata_RequestService) SpringContextUtil.getBean("fcdata_RequestImpl");
		return (MsgRes)fcdata_RequestImpl.fcdataScanBarcode(strRecvData);		
	} 	
	
	private MsgRes GetArticleInfoByBarcode(String strRecvData) throws Exception {
		IFcdata_RequestService fcdata_RequestImpl = (IFcdata_RequestService) SpringContextUtil.getBean("fcdata_RequestImpl");
		return (MsgRes)fcdata_RequestImpl.GetArticleInfoByBarcode(strRecvData);		
	} 	
	private MsgRes GetPackingInfoByArticleNo(String strRecvData) throws Exception {
		IFcdata_RequestService fcdata_RequestImpl = (IFcdata_RequestService) SpringContextUtil.getBean("fcdata_RequestImpl");
		return fcdata_RequestImpl.GetArticlePacking(strRecvData);
	} 		
	private MsgRes StockContentQuery(String strRecvData) throws Exception {
		IFcdata_RequestService fcdata_RequestImpl = (IFcdata_RequestService) SpringContextUtil.getBean("fcdata_RequestImpl");
		return fcdata_RequestImpl.StockContentQuery(strRecvData);
	}


	private MsgRes GetProductDateByArticleNO(String strRecvData) throws Exception {
		IFcdata_RequestService fcdata_RequestImpl = (IFcdata_RequestService) SpringContextUtil.getBean("fcdata_RequestImpl");
		return (MsgRes)fcdata_RequestImpl.GetProductDateByArticleNO(strRecvData);		
	} 
	
	/**
	 * 扫描标签获取商品
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes DpsScanLabelNo(String strRecvData) throws Exception {
		IFcdata_RequestService fcdata_RequestImpl = (IFcdata_RequestService) SpringContextUtil.getBean("fcdata_RequestImpl");
		return fcdata_RequestImpl.DpsScanLabelNo(strRecvData);
	}
	/**
	 * 获取商品电子标签分播储位
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes DpsGetCell(String strRecvData) throws Exception {
		IFcdata_RequestService fcdata_RequestImpl = (IFcdata_RequestService) SpringContextUtil.getBean("fcdata_RequestImpl");
		return fcdata_RequestImpl.DpsGetCell(strRecvData);
	}
	/**
	 * 获取商品分播信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes DpsGetCellInfo(String strRecvData) throws Exception {
		IFcdata_RequestService fcdata_RequestImpl = (IFcdata_RequestService) SpringContextUtil.getBean("fcdata_RequestImpl");
		return fcdata_RequestImpl.DpsGetCellInfo(strRecvData);
	}
}
