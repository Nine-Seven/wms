package com.sealinkin.process.impl;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.socket.CBizAnswerModel;
import com.sealinkin.listener.SpringContextUtil;
import com.sealinkin.odata.service.IDPS_SystemService;
import com.sealinkin.process.IBaseRfService;
import com.sealinkin.protocolExchange.SessionDefine;
import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
import com.sealinkin.protocolExchange.dpsdata.ReqDpsAddrModel;
import com.sealinkin.util.ExceptionUtil;

public class DpsSystemImpl implements IBaseRfService{

	@Override
	public CBizAnswerModel doRfApplication(Integer nReqType, String strRecvData) {
		CBizAnswerModel bizAnsMod=new CBizAnswerModel();
		MsgRes msgRes=new MsgRes();
		try {
			switch(nReqType)
			{
				case SessionDefine.GC_Session_Type_Get_All_DpsAddr://读取所有标签地址	
					msgRes=GetAllDPSAddress(strRecvData);	
					break;
				case SessionDefine.GC_Session_Type_Verify_User://校验用户	
					msgRes=CheckWorkerNo(strRecvData);	
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
			bizAnsMod.setStrData(ExceptionUtil.getCacseMessage(e));
		}
		return bizAnsMod;
	}
	
	/***
	 * 读取所有标签地址
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes GetAllDPSAddress(String strRecvData) throws Exception {
		ReqDpsAddrModel resMod=JSON.parseObject(strRecvData, ReqDpsAddrModel.class);
		IDPS_SystemService dps_SystemImpl = (IDPS_SystemService) SpringContextUtil.getBean("dps_SystemImpl");		
		return (MsgRes) dps_SystemImpl.getDPSAddress(resMod.getEnterpriseNo(),
				resMod.getWarehouseNo(), resMod.getUseType(), resMod.getDeviceGroupNo());
	}	
	
	private MsgRes CheckWorkerNo(String strRecvData) throws Exception {
		CommSingleDataRequestModel resMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		IDPS_SystemService dps_SystemImpl = (IDPS_SystemService) SpringContextUtil.getBean("dps_SystemImpl");		
		return (MsgRes) dps_SystemImpl.CheckWorkerNo(resMod.getEnterpriseNo(), resMod.getReqObj());
	}	

   
}
