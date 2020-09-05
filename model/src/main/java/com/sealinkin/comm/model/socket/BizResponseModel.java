package com.sealinkin.comm.model.socket;

import java.io.Serializable;
/**
*@文件名:业务类
*@创建人:王翔
*@日期:2014-4-28
*@修改人:王翔
*@日期:2014-4-28
*@描述:
 */
public class BizResponseModel implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//业务层属性
	private Boolean isSucc;
	
	private int    intErrror;

	private int    nDataLength;

	private String strSessionID;

	private String strData;

	public Boolean getIsSucc() {
		return isSucc;
	}

	public void setIsSucc(Boolean isSucc) {
		this.isSucc = isSucc;
	}

	public int getIntErrror() {
		return intErrror;
	}

	public void setIntErrror(int intErrror) {
		this.intErrror = intErrror;
	}

	public int getnDataLength() {
		return nDataLength;
	}

	public void setnDataLength(int nDataLength) {
		this.nDataLength = nDataLength;
	}

	public String getStrSessionID() {
		return strSessionID;
	}

	public void setStrSessionID(String strSessionID) {
		this.strSessionID = strSessionID;
	}

	public String getStrData() {
		return strData;
	}

	public void setStrData(String strData) {
		this.strData = strData;
	}
	
		
}
