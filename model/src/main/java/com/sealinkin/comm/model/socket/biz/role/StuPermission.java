package com.sealinkin.comm.model.socket.biz.role;

import java.io.Serializable;

public class StuPermission implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strFunno;
	private int nFunID;
	private String strFunname;
	private int nPurview_type;
	
	
	public String getStrFunno()
	{
		return strFunno;
	}
	public void setStrFunno(String strFunno)
	{
		this.strFunno = strFunno;
	}
	public int getnFunID()
	{
		return nFunID;
	}
	public void setnFunID(int nFunID)
	{
		this.nFunID = nFunID;
	}
	public String getStrFunname()
	{
		return strFunname;
	}
	public void setStrFunname(String strFunname)
	{
		this.strFunname = strFunname;
	}
	public int getnPurview_type()
	{
		return nPurview_type;
	}
	public void setnPurview_type(int nPurview_type)
	{
		this.nPurview_type = nPurview_type;
	}

}
