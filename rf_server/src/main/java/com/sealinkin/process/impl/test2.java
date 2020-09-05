package com.sealinkin.process.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sealinkin.comm.model.socket.biz.role.StuPermission;
import com.sealinkin.protocolExchange.LoginDataChange.LoginAnswerModel;

public class test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
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
		//mapPermission.put("F2000", arrStuCpermissionList.get(1));
		
		System.out.println(JSONObject.toJSONString(mapPermission));
		
		LoginAnswerModel loginAnsMod = new LoginAnswerModel();
		loginAnsMod.setStrSessionID("127.0.0.1-*F56315AFA2CC47C02D84FECE4D8C01BD198006C3");
		loginAnsMod.setArrStuCpermission(mapPermission);
		
		//String m_data = JSONArray.toJSONString(loginAnsMod);
		String m_data = JSON.toJSONString(loginAnsMod);
		System.out.println(m_data);
	}

}
