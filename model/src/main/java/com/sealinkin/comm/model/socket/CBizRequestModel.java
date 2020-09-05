package com.sealinkin.comm.model.socket;

import java.io.Serializable;
/**
*@文件名:业务请求对象
*@创建人:王翔
*@日期:2014-6-10
*@修改人:王翔
*@日期:2014-6-10
*@描述:
 */
public class CBizRequestModel implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer nRequestType;
	private String  strData;
	

	public String getStrData()
	{
		return strData;
	}

	public void setStrData(String strData)
	{
		this.strData = strData;
	}

	public Integer getNRequestType()
	{
		return nRequestType;
	}

	public void setNRequestType(Integer nRequestType)
	{
		this.nRequestType = nRequestType;
	}
	
	public static void main(String[] args)
	{
		CBizRequestModel s = new CBizRequestModel();
		s.setNRequestType(1);
		System.out.println(s.getNRequestType());
	}
	
}
