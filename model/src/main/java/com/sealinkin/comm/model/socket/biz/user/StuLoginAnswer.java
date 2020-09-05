package com.sealinkin.comm.model.socket.biz.user;

import java.io.Serializable;
import java.util.List;

import com.sealinkin.comm.model.socket.biz.role.StuPermission;
/**
*@文件名:登陆请求数据对象
*@创建人:王翔
*@日期:2014-6-10
*@修改人:王翔
*@日期:2014-6-10
*@描述:
 */
public class StuLoginAnswer implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String strSessionID;      					//对话ID 
    public List<StuPermission> ArrStuCpermission;       //用户名 


	public String getStrSessionID()
	{
		return strSessionID;
	}
	public void setStrSessionID(String strSessionID)
	{
		this.strSessionID = strSessionID;
	}
	public List<StuPermission> getArrStuCpermission()
	{
		return ArrStuCpermission;
	}
	public void setArrStuCpermission(List<StuPermission> arrStuCpermission)
	{
		ArrStuCpermission = arrStuCpermission;
	}
    

}
