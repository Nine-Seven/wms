package com.sealinkin.process;

import com.sealinkin.comm.model.socket.CBizAnswerModel;
import com.sealinkin.comm.model.socket.CBizRequestModel;

public abstract class ICaseProcessService2
{
	/**
	 * 调用业务逻辑入口方法
	 * @param jsonObject 传入JSONObejct对象
	 * @param className  传入对象要解析的Class名称
	 * @return Object    返回Object超类
	 */
	public abstract String caseMethod(String bizStrData) throws Exception;
	
	public abstract CBizAnswerModel basicCaseMethod(CBizRequestModel bizReqProModel)  throws Exception ;
	public abstract CBizAnswerModel stockCaseMethod(CBizRequestModel bizReqProModel)  throws Exception ;
	public abstract CBizAnswerModel shipmentCaseMethod(CBizRequestModel bizReqProModel)  throws Exception ;
	public abstract CBizAnswerModel reverseDistCaseMethod(CBizRequestModel bizReqProModel)  throws Exception ;
	public abstract CBizAnswerModel checkCaseMethod(CBizRequestModel bizReqProModel)  throws Exception ;
	public abstract CBizAnswerModel moveCaseMethod(CBizRequestModel bizReqProModel)  throws Exception ;
	public abstract CBizAnswerModel labelRemoveCaseMethod(CBizRequestModel bizReqProModel)  throws Exception ;
	public abstract CBizAnswerModel storeCheckCaseMethod(CBizRequestModel bizReqProModel)  throws Exception ;
	
	public abstract CBizAnswerModel realCaseMethod(CBizRequestModel bizReqProModel)  throws Exception ;
}
