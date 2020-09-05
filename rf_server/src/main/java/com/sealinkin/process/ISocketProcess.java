package com.sealinkin.process;

import com.sealinkin.comm.model.TransferDataModel;
import com.sealinkin.comm.model.socket.BizResponseModel;
import com.sealinkin.comm.model.socket.CBizAnswerModel;
import com.sealinkin.comm.model.socket.CBizRequestModel;
import com.sealinkin.cons.SystemConstant;
import com.sealinkin.util.arithmetic.Base64Md5Util;


public abstract class ISocketProcess implements IProcessFactory
{

	/**
	 * 调用业务逻辑入口方法
	 * @param jsonObject 传入JSONObejct对象
	 * @param className  传入对象要解析的Class名称
	 * @return Object    返回Object超类
	 */
	public abstract CBizAnswerModel callBizService(CBizRequestModel cBizRequestModel) throws Exception;
	
	/**
	 * 封装返回对象
	 * @return
	 */
	public TransferDataModel packData(BizResponseModel bizModel) throws Exception
	{
		//获取秘钥
		String token = SystemConstant.token;
		token = Base64Md5Util.encodeBase64AndMd5(token);
		
		//封装数据
		TransferDataModel commReturnBo = new TransferDataModel(token, bizModel);
		return commReturnBo;
	}

}
