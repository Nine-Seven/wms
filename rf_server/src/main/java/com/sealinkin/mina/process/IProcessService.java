package com.sealinkin.mina.process;

import com.alibaba.fastjson.JSONObject;

/**
*@文件名:统一调用业务逻辑接口
*@创建人:王翔
*@日期:2014-4-28
*@修改人:王翔
*@日期:2014-4-28
*@描述:
 */
public interface IProcessService
{

	public Object callBizService(JSONObject jsonObject);
}
