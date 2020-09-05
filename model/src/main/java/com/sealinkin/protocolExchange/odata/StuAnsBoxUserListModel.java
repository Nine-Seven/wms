package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;


// <summary>
// 读取物流箱已分播用户列表接收model
// </summary>
public class StuAnsBoxUserListModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 public String WorkNo;
	 
	public String getWorkNo() {
		return WorkNo;
	}
	public void setWorkNo(String workNo) {
		WorkNo = workNo;
	}

	

}
