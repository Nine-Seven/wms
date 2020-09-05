package com.sealinkin.process.impl;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sealinkin.comm.model.socket.CBizAnswerModel;
import com.sealinkin.comm.model.socket.CBizRequestModel;
import com.sealinkin.mina.util.LogFilterUtil;
import com.sealinkin.process.IBaseRfService;
import com.sealinkin.process.ICaseProcessService;

public class CaseProcessServiceImpl extends ICaseProcessService
{

	@Override
	public String caseMethod(String bizStrData,String ip)
			throws Exception
	{
//		Date newDate = new Date();
		//生成请求业务对象JSON
		JSON jsonCBizReq = (JSON) JSONObject.parse(bizStrData);
		CBizRequestModel bizReqProModel = (CBizRequestModel)JSONObject.toJavaObject(jsonCBizReq , CBizRequestModel.class);		
		CBizAnswerModel cBizAnswerModel = null;
		Integer code = bizReqProModel.getNRequestType();				
		IBaseRfService rfServiceImpl=null;
		if(code!=3001)//临时去掉 以便排查
		{
			LogFilterUtil.outputLog("enter",code, ip);
		}
		/**
		 * 根据通信类型处理对应逻辑
		 * 系统方面操作 0-10
		 * 进货         11-199
		 * 出货         200-699
		 * 反配         700-799
		 * 盘点         800-899
	     * 移库         900-999
	     * 退厂	1000-1099
		 * 标签销毁     1201-1299
	     * 储位检查     1300-1399
	     * 基础信息采集  1400-1499
	     * 退货供应商分播 2100-2199
	     * 分播出库2200-2299
	     * DPS拣货2300-2399
	     * 打印程序    3000-3099
		 */
		if (code>=0 && code<=10)
		{
			rfServiceImpl=new SystemRfImpl();
		}else if (code>=11 && code<=199)
		{
			rfServiceImpl=new IdataRfImpl();
		}else if (code>=200 && code<=699)
		{
			rfServiceImpl=new OdataRfImpl();
		}else if (code>=700 && code<=799)
		{
			rfServiceImpl=new RIdataRfImpl();
		}else if (code>=800 && code<=899)
		{
			rfServiceImpl=new FcdataRfImpl();
		}else if (code>=900 && code<=999)
		{
			rfServiceImpl=new MdataRfImpl();
		}else if (code>=1100 && code<=1199)
		{
			rfServiceImpl = new RODataRfImpl();
		}else if (code>=1201 && code<=1299)
		{
			rfServiceImpl=new IdataRfImpl();
		}else if (code>=1300 && code<=1399)
		{
			rfServiceImpl=new IdataRfImpl();
		}else if (code>=1400 && code<=1499)
		{
			rfServiceImpl=new BdefRfImpl();
		}else if (code>=2000 && code<=2099)
		{
			rfServiceImpl=new DpsSystemImpl();
		}else if (code>=2100 && code<=2199)
		{
			rfServiceImpl=new RIDivideImpl();
		}else if (code>=2200 && code<=2299)
		{
			rfServiceImpl=new ODivideRfImpl();
		}else if (code>=2300 && code<=2399)
		{
			rfServiceImpl=new DpsImpl();
		}else if(code>=3000 && code<=3099)
		{
			rfServiceImpl=new PrintServerImpl();
		}
		
		
		cBizAnswerModel=rfServiceImpl.doRfApplication(code,bizReqProModel.getStrData());		
		
		//封装返回业务对象为JSON
		String retStrData = JSON.toJSONString(cBizAnswerModel);
		if(code!=3001)//临时去掉 以便排查
		{
			LogFilterUtil.outputLog("leval",code, ip);
		}
//		Date oldDate = new Date();
	
//		LogFilterUtil.logWithOverTime(newDate,oldDate, ip,code);
		
		return retStrData;
	}
	public static void main(String[] args)
	{
		JSON jsonCBizReq = (JSON) JSONObject.parse("{'nRequestType':1,strData:{'abc':'123'}}");
		CBizRequestModel cBizRequestModel = (CBizRequestModel)JSONObject.toJavaObject(jsonCBizReq , CBizRequestModel.class);
		System.out.println(cBizRequestModel.getNRequestType());
	}
}
