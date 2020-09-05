package com.sealinkin.process.impl;

import com.alibaba.fastjson.JSON;
import com.sealinkin.auth.service.IAuthService;
import com.sealinkin.auth.service.ILogin;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.socket.CBizAnswerModel;
import com.sealinkin.listener.SpringContextUtil;
import com.sealinkin.process.IBaseRfService;
import com.sealinkin.protocolExchange.SessionDefine;
import com.sealinkin.protocolExchange.LoginDataChange.LoginRequestModel;
import com.sealinkin.protocolExchange.LoginDataChange.UpdatePwdModel;
import com.sealinkin.util.algorithm.MysqlPasswd;

public class SystemRfImpl implements IBaseRfService {

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
				case SessionDefine.GC_Session_Type_Login:	
					msgRes=Login(strRecvData);	
					break;
				case SessionDefine.GC_Session_Type_UpdatePassword://修改密码
					msgRes=UpdatePwd(strRecvData);	
					break;	
				case SessionDefine.GC_Session_Type_GetProg://获取更新列表
					msgRes=GetProgUpdateList(strRecvData);	
					break;	
				case SessionDefine.GC_Session_Type_GetDBConfig://获取db、ftp配置
					msgRes=GetDbConfig(strRecvData);	
					break;	
				case SessionDefine.GC_Session_Type_GetRFProg://获取RF更新列表
					msgRes=GetProgUpdateListForRF(strRecvData);	
					break;
				case SessionDefine.GC_Session_Type_GetProgVer://获取更新列表版本
					msgRes=GetProgVerList(strRecvData);	
					break;	
				case SessionDefine.GC_Session_Type_GetRFProgVer://获取RF更新列表版本号
					msgRes=GetProgUpdateVerForRF(strRecvData);	
					break;
				case SessionDefine.GC_Session_Type_QtyShow://获取RF系统允许显示的数量信息
					msgRes=GetQtyShow(strRecvData);	
					break;	
			}
			bizAnsMod.setStrResult(msgRes.getIsSucc()?"Y":"N");
			bizAnsMod.setNRequestType(nReqType+SessionDefine.PACK_ACK_RADIX);
			bizAnsMod.setStrData(msgRes.getIsSucc()?(msgRes.getObj()!=null?msgRes.getObj().toString():""):msgRes.getMsg());
			//bizAnsMod.setStrData("{'arrStuCpermission':{'F2000':{'strFunname':'存储验收','strFunno':'F2000'},'FI0000':{'strFunname':'盘点','strFunno':'FI000'}},'strSessionID':'127.0.0.1-*F56315AFA2CC47C02D84FECE4D8C01BD198006C3'}");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bizAnsMod;
	}
	
	private MsgRes Login(String strRecvData) throws Exception {
		LoginRequestModel loginReq=JSON.parseObject(strRecvData, LoginRequestModel.class);
		ILogin loginImpl = (ILogin) SpringContextUtil.getBean("loginImpl");
		return (MsgRes)loginImpl.rfLogin(loginReq.getStrUsername(), MysqlPasswd.MySQLPassword(loginReq.getStrPassword()),loginReq.getEnterpriseNo());
	}
	/**
	 * 修改密码
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes UpdatePwd(String strRecvData) throws Exception {
		
		UpdatePwdModel mod=JSON.parseObject(strRecvData, UpdatePwdModel.class);
		ILogin loginImpl = (ILogin) SpringContextUtil.getBean("loginImpl");
		System.out.println(mod.getWorkerNo()+"---"+mod.getPassword()+"----"+mod.getNewPwd());
		return (MsgRes)loginImpl.updatePWD(mod.getWorkerNo(),
				MysqlPasswd.MySQLPassword(mod.getPassword()),
				MysqlPasswd.MySQLPassword(mod.getNewPwd()));
	}
	private MsgRes GetProgUpdateList(String strRecvData) throws Exception {
		IAuthService authImpl = (IAuthService) SpringContextUtil.getBean("authImpl");
		return authImpl.getProcUpdateList();
	}
	private MsgRes GetDbConfig(String strRecvData) throws Exception {
		IAuthService authImpl = (IAuthService) SpringContextUtil.getBean("authImpl");
		return authImpl.GetDbConfig();		
	}

	private MsgRes GetProgUpdateListForRF(String strRecvData) throws Exception {
		IAuthService authImpl = (IAuthService) SpringContextUtil.getBean("authImpl");
		return authImpl.getProcUpdateListForRF();
	}
	private MsgRes GetProgVerList(String strRecvData) throws Exception {
		IAuthService authImpl = (IAuthService) SpringContextUtil.getBean("authImpl");
		return authImpl.getProgVerList();
	}
	private MsgRes GetProgUpdateVerForRF(String strRecvData) throws Exception {
		IAuthService authImpl = (IAuthService) SpringContextUtil.getBean("authImpl");
		return authImpl.getProgVerListForRF();
	}
	
	//获取RF系统允许显示的数量信息 Add by sunl
	private MsgRes GetQtyShow(String strRecvData) throws Exception {
		IAuthService authImpl = (IAuthService) SpringContextUtil.getBean("authImpl");
		return authImpl.GetQtyShow(strRecvData);
	}
}
