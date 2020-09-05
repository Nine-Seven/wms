package com.sealinkin.process;

public abstract class ICaseProcessService {
	/**
	 * 调用业务逻辑入口方法
	 * @param jsonObject 传入JSONObejct对象
	 * @param className  传入对象要解析的Class名称
	 * @return Object    返回Object超类
	 */
	public abstract String caseMethod(String bizStrData,String ip) throws Exception;	
}
