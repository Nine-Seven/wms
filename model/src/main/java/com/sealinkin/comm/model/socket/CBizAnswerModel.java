package com.sealinkin.comm.model.socket;

import java.io.Serializable;
/**
*@文件名:业务应答对象
*@创建人:王翔
*@日期:2014-6-10
*@修改人:王翔
*@日期:2014-6-10
*@描述:
 */
public class CBizAnswerModel implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer nRequestType;
	private String  strData;
	private String  strResult;	//Y|N
	public Integer getNRequestType()
	{
		return nRequestType;
	}
	public void setNRequestType(Integer nRequestType)
	{
		this.nRequestType = nRequestType;
	}
	public String getStrData()
	{
		return strData;
	}
	public void setStrData(String strData)
	{
		this.strData = strData;
	}
	public String getStrResult()
	{
		return strResult;
	}
	public void setStrResult(String strResult)
	{
		this.strResult = strResult;
	}
	
	
}
