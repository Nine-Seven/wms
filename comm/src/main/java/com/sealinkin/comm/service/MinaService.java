package com.sealinkin.comm.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sealinkin.comm.model.TransferDataModel;
import com.sealinkin.comm.model.socket.BizRequestModel;
import com.sealinkin.comm.model.socket.BizResponseModel;
import com.sealinkin.cons.SystemConstant;
import com.sealinkin.util.arithmetic.Base64Md5Util;

@SuppressWarnings("unused")
public class MinaService
{

	public static TransferDataModel parseRequestData(String message){
		JSON json = (JSON) JSONObject.parse(message.toString());
		TransferDataModel commReturnBo = (TransferDataModel)JSONObject.toJavaObject(json , TransferDataModel.class);
		return commReturnBo;
	}
	
	
	/**
	 * 封装请求数据 + 私钥
	 * @param bizReqMdl
	 * @return
	 * @throws Exception
	 */
	public static TransferDataModel parseDispathData(BizRequestModel bizReqMdl) throws Exception{
		TransferDataModel commReturnBo = new TransferDataModel();
		commReturnBo.setBizReqMdl(bizReqMdl);
		
		//获取秘钥
		String token = SystemConstant.token;
		token = Base64Md5Util.encodeBase64AndMd5(token);
		commReturnBo.setToken(token);
		
		return commReturnBo;
	}
	
	/**
	 * 返回异常对象
	 * @param e
	 * @return
	 */
	public static BizResponseModel getCatchModel(Exception e){
		BizResponseModel responseModel = new BizResponseModel();
		responseModel.setIsSucc(false);
		//responseModel.setMsg(ExceptionUtil.getCacseMessage(e));
		String writeStr = JSONObject.toJSONString(responseModel);
		return responseModel;
	}
}
