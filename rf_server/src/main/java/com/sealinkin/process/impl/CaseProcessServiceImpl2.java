package com.sealinkin.process.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sealinkin.auth.service.ILogin;
import com.sealinkin.bdef.model.Bdef_DefWorkerModel;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.socket.CBizAnswerModel;
import com.sealinkin.comm.model.socket.CBizRequestModel;
import com.sealinkin.comm.model.socket.biz.role.StuPermission;
import com.sealinkin.comm.model.socket.biz.user.StuLoginRequest;
import com.sealinkin.listener.SpringContextUtil;
import com.sealinkin.process.ICaseProcessService2;
import com.sealinkin.protocolExchange.LoginDataChange.LoginAnswerModel;
import com.sealinkin.util.algorithm.MysqlPasswd;

@SuppressWarnings("unused")
public class CaseProcessServiceImpl2 extends ICaseProcessService2
{

	@Override
	public String caseMethod(String bizStrData)
			throws Exception
	{
		//生成请求业务对象JSON
		JSON jsonCBizReq = (JSON) JSONObject.parse(bizStrData);
		CBizRequestModel bizReqProModel = (CBizRequestModel)JSONObject.toJavaObject(jsonCBizReq , CBizRequestModel.class);
		
		//根据请求类型进入switch处理具体业务
		CBizAnswerModel cBizAnswerModel = null;
		Integer code = bizReqProModel.getNRequestType();
		if(code >=0 && code <=10 ){
			cBizAnswerModel = basicCaseMethod(bizReqProModel);
		}else if(code >=0 && code <=10 ){
			stockCaseMethod(bizReqProModel);
		}else if(11 >=0 && code <=199 ){
			shipmentCaseMethod(bizReqProModel);
		}else if(code >=200 && code <=699 ){
			reverseDistCaseMethod(bizReqProModel);
		}else if(code >=700 && code <=799 ){
			checkCaseMethod(bizReqProModel);
		}else if(code >=800 && code <=899 ){
			moveCaseMethod(bizReqProModel);
		}else if(code >=900 && code <=1200 ){
			labelRemoveCaseMethod(bizReqProModel);
		}else if(code >=1300 && code <=1399 ){
			storeCheckCaseMethod(bizReqProModel);
		}
		
		//封装返回业务对象为JSON
		String retStrData = JSON.toJSONString(cBizAnswerModel);
		return retStrData;
	}

	@Override
	public CBizAnswerModel realCaseMethod(CBizRequestModel bizReqProModel)
			throws Exception
	{
		return null;
	}

	@Override
	public CBizAnswerModel basicCaseMethod(CBizRequestModel bizReqProModel)
			throws Exception
	{
		CBizAnswerModel cBizAnswerModel = null;
		Integer code = bizReqProModel.getNRequestType();
		
		switch(code){
		
		case 0:
			
			
			break;
		//登陆
		case 1:
			
			ILogin login = (ILogin) SpringContextUtil.getBean("loginImpl");
			JSON jsonStuLoginRequest = (JSON) JSONObject.parse(bizReqProModel.getStrData());
			StuLoginRequest stuLoginRequest = (StuLoginRequest)JSONObject.toJavaObject(jsonStuLoginRequest , StuLoginRequest.class);
			MsgRes msgRes = login.login(stuLoginRequest.getStrUsername(),MysqlPasswd.MySQLPassword(stuLoginRequest.getStrPassword()),stuLoginRequest.getEnterpriseNo());
			Bdef_DefWorkerModel user = (Bdef_DefWorkerModel) msgRes.getObj();
			
			//封装返回数据对象
			
			List<StuPermission> arrStuCpermissionList = new ArrayList<StuPermission>();
			StuPermission stuPermission = new StuPermission();

			stuPermission.setnFunID(0);
			stuPermission.setnPurview_type(2);
			stuPermission.setStrFunname("盘点");
			stuPermission.setStrFunno("FI000");
			arrStuCpermissionList.add(stuPermission);
			
			stuPermission.setnFunID(0);
			stuPermission.setnPurview_type(2);
			stuPermission.setStrFunname("存储验收");
			stuPermission.setStrFunno("F2000");
			arrStuCpermissionList.add(stuPermission);
			
            //应答结构明细字典
			HashMap<String, StuPermission>  mapPermission =new HashMap<String,StuPermission>();
			mapPermission.put("FI0000", arrStuCpermissionList.get(0));
			mapPermission.put("F2000", arrStuCpermissionList.get(1));
			
			LoginAnswerModel loginAnsMod = new LoginAnswerModel();
			loginAnsMod.setStrSessionID("127.0.0.1-*F56315AFA2CC47C02D84FECE4D8C01BD198006C3");
			loginAnsMod.setArrStuCpermission(mapPermission);
			//String m_data = JSONArray.toJSONString(loginAnsMod);
			String m_data = JSONObject.toJSONString(loginAnsMod);
			cBizAnswerModel = new CBizAnswerModel();
			cBizAnswerModel.setNRequestType(code);
			cBizAnswerModel.setStrData(m_data);
			cBizAnswerModel.setStrResult("Y");
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			break;
		case 9:
			break;
		case 10:
			break;
		}
		return cBizAnswerModel;
	}

	@Override
	public CBizAnswerModel stockCaseMethod(CBizRequestModel bizReqProModel)
			throws Exception
	{
		return null;
	}

	@Override
	public CBizAnswerModel shipmentCaseMethod(CBizRequestModel bizReqProModel)
			throws Exception
	{
		return null;
	}

	@Override
	public CBizAnswerModel reverseDistCaseMethod(
			CBizRequestModel bizReqProModel) throws Exception
	{
		return null;
	}

	@Override
	public CBizAnswerModel checkCaseMethod(CBizRequestModel bizReqProModel)
			throws Exception
	{
		return null;
	}

	@Override
	public CBizAnswerModel moveCaseMethod(CBizRequestModel bizReqProModel)
			throws Exception
	{
		return null;
	}

	@Override
	public CBizAnswerModel labelRemoveCaseMethod(
			CBizRequestModel bizReqProModel) throws Exception
	{
		return null;
	}

	@Override
	public CBizAnswerModel storeCheckCaseMethod(
			CBizRequestModel bizReqProModel) throws Exception
	{
		return null;
	}
	public static void main(String[] args)
	{
		JSON jsonCBizReq = (JSON) JSONObject.parse("{'nRequestType':1,strData:{'abc':'123'}}");
		CBizRequestModel cBizRequestModel = (CBizRequestModel)JSONObject.toJavaObject(jsonCBizReq , CBizRequestModel.class);
		System.out.println(cBizRequestModel.getNRequestType());
	}
}
