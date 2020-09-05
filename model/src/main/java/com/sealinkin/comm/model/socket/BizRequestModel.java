package com.sealinkin.comm.model.socket;

import java.io.Serializable;

/**
*@文件名:业务请求对象
*@创建人:王翔
*@日期:2014-4-28
*@修改人:王翔
*@日期:2014-4-28
*@描述:
 */
public class BizRequestModel implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//业务层属性
	private int     m_nBeanName;		  //数据对应类名称
	private String  m_strSessionID;       //通信令牌
	private int     m_nDataLength ;       //数据长度
	private String  m_strData;            //数据
	
	
	
	public int getM_nBeanName() {
		return m_nBeanName;
	}
	public void setM_nBeanName(int m_nBeanName) {
		this.m_nBeanName = m_nBeanName;
	}
	public String getM_strSessionID() {
		return m_strSessionID;
	}
	public void setM_strSessionID(String m_strSessionID) {
		this.m_strSessionID = m_strSessionID;
	}
	public int getM_nDataLength() {
		return m_nDataLength;
	}
	public void setM_nDataLength(int m_nDataLength) {
		this.m_nDataLength = m_nDataLength;
	}
	public String getM_strData() {
		return m_strData;
	}
	public void setM_strData(String m_strData) {
		this.m_strData = m_strData;
	}
}
