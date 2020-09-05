package com.sealinkin.process;

import com.sealinkin.comm.model.socket.BizResponseModel;


public abstract class IWebServiceProcess implements IProcessFactory
{

	public abstract BizResponseModel callBizService(Object object,String className) throws Exception;

}
