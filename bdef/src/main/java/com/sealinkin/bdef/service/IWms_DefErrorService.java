package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bdef.model.Wms_DefErrorModel;

/**
*@文件名:操作错误提示信息类
*@创建人:王翔
*@日期:2014-4-22
*@修改人:王翔
*@日期:2014-4-22
*@描述:
 */
public interface IWms_DefErrorService
{
	/**
	 * 查询全部错误信息
	 * @return
	 */
	public List<Wms_DefErrorModel> queryWmsDefErrorList();
	
	/**
	 * 加载全部错误信息入内存
	 * 服务器启动时候去数据库加载
	 */
	public void loadWmsDefError();
	
}
