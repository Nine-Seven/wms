package com.sealinkin.process;

import com.sealinkin.comm.model.socket.BizResponseModel;

/**
*@文件名:
*@创建人:王翔
*@日期:2014-4-28
*@修改人:王翔
*@日期:2014-4-28
*@描述:
 */
public interface IProcessFactory
{

	public BizResponseModel callBizService(String beanName,String methodName,String args) throws Exception;
}
