package com.sealinkin.process.impl;

import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.socket.CBizAnswerModel;
import com.sealinkin.listener.SpringContextUtil;
import com.sealinkin.process.IBaseRfService;
import com.sealinkin.protocolExchange.SessionDefine;
import com.sealinkin.rodata.service.IRodata_OutstockMService;
import com.sealinkin.util.ExceptionUtil;

public class RODataRfImpl implements IBaseRfService{

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
				case SessionDefine.GC_SESSION_TYPE_RO_GetOutStockDateInfo://获取拣货信息
					msgRes=ROGetOutStockDateInfo(strRecvData);	
					break;	
				case SessionDefine.GC_SESSION_TYPE_RO_GetArticleInfo://拣货回单  获取商品信息	
					msgRes=ROGetArticleInfo(strRecvData);	
					break;	
				case SessionDefine.GC_SESSION_TYPE_RO_PickReceipt://拣货回单
					msgRes=ROPickReceipt(strRecvData);	
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
	 * 退厂扫描标签号 获取拣货回单信息	
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes ROGetOutStockDateInfo(String strRecvData) throws Exception {
		IRodata_OutstockMService RoData_OutstockReceipt= (IRodata_OutstockMService) SpringContextUtil.getBean("rodata_OutstockMImpl");		
		return RoData_OutstockReceipt.ROdataCheckLabelNo(strRecvData);
	}
	/***
	 * 退厂拣货回单  获取商品信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes ROGetArticleInfo(String strRecvData) throws Exception {
		IRodata_OutstockMService RoData_OutstockReceipt= (IRodata_OutstockMService) SpringContextUtil.getBean("rodata_OutstockMImpl");		
		return RoData_OutstockReceipt.ROdataCheckBarcode(strRecvData);
	}
	/***
	 * 退厂拣货回单确认	
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes ROPickReceipt(String strRecvData) throws Exception {		
		IRodata_OutstockMService RoData_OutstockReceipt= (IRodata_OutstockMService) SpringContextUtil.getBean("rodata_OutstockMImpl");		
		return (MsgRes) RoData_OutstockReceipt.tscROPickReceipt(strRecvData);
	}
}
