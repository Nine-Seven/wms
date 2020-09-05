package com.sealinkin.process.impl;

import com.sealinkin.auth.service.IAuthService;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.socket.CBizAnswerModel;
import com.sealinkin.listener.SpringContextUtil;
import com.sealinkin.process.IBaseRfService;
import com.sealinkin.protocolExchange.SessionDefine;
import com.sealinkin.util.ExceptionUtil;
/**
 * 移库RF
 * @author lich
 *
 */
public class AutoUpdateRfImpl implements IBaseRfService{

	@Override
	public CBizAnswerModel doRfApplication(Integer nReqType, String strRecvData) {
		CBizAnswerModel bizAnsMod=new CBizAnswerModel();
		MsgRes msgRes=new MsgRes();
		try {
			switch(nReqType)
			{
				case SessionDefine.GC_Session_Type_GetProg://获取更新列表
					msgRes=GetProgUpdateList(strRecvData);	
					break;	
				case SessionDefine.GC_Session_Type_GetDBConfig://获取db、ftp配置
					msgRes=GetDbConfig(strRecvData);	
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
	private MsgRes GetProgUpdateList(String strRecvData) throws Exception {
		IAuthService authImpl = (IAuthService) SpringContextUtil.getBean("authImpl");
		return authImpl.getProcUpdateList();
	}
	private MsgRes GetDbConfig(String strRecvData) throws Exception {
		IAuthService authImpl = (IAuthService) SpringContextUtil.getBean("authImpl");
		return authImpl.GetDbConfig();
	}	
}
