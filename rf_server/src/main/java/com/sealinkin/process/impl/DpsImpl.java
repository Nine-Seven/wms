package com.sealinkin.process.impl;


import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.socket.CBizAnswerModel;
import com.sealinkin.listener.SpringContextUtil;
import com.sealinkin.odata.service.IDPS_SystemService;
import com.sealinkin.process.IBaseRfService;
import com.sealinkin.protocolExchange.SessionDefine;
import com.sealinkin.util.ExceptionUtil;

public class DpsImpl implements IBaseRfService {

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
			case SessionDefine.GC_Session_Type_DPS_GET_MIN_AREA: //获取当前任务号所在的巷道，区域，最小箱码
				msgRes=getMinArea(strRecvData);
				break;				
			case SessionDefine.GC_Session_Type_DPS_GET_ALL_TAST: //DPS获取当前任务所在巷道的拣货任务。
				msgRes=getAllTast(strRecvData);
				break;
			
			case SessionDefine.GC_Session_Type_DPS_SAVE_LABELNO: //DPS拣货回单
				msgRes=tscReceipt(strRecvData);	
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
	
	 //获取当前任务号所在的巷道，区域，最小箱码
		private MsgRes getMinArea(String strRecvData) throws Exception{
			IDPS_SystemService dps_SystemImpl = (IDPS_SystemService) SpringContextUtil.getBean("dps_SystemImpl");
			return (MsgRes) dps_SystemImpl.getMinArea(strRecvData);
		}
		
		//DPS获取当前任务所在巷道的拣货任务。
		private MsgRes getAllTast(String strRecvData)throws Exception{
			IDPS_SystemService dps_SystemImpl = (IDPS_SystemService) SpringContextUtil.getBean("dps_SystemImpl");
			return (MsgRes) dps_SystemImpl.getAllTast(strRecvData);
		}
		
		//PDS拣货回单
		private MsgRes tscReceipt(String strRecvData) throws Exception{
			IDPS_SystemService dps_SystemImpl = (IDPS_SystemService) SpringContextUtil.getBean("dps_SystemImpl");
			return (MsgRes) dps_SystemImpl.tscReceipt(strRecvData);
		}
}
