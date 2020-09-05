package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;
import java.util.List;


/**
 * 返回当前箱最少分播区域及条码列表 对应stuAnsBoxInfo
 * @author lich
 *
 */
public class AnsBoxInfoModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<BoxInfoModel> lstBarcode;
	
	public List<BoxInfoModel> getlstBarcode() {
		return lstBarcode;
	}

	public void setlstBarcode(List<BoxInfoModel> lstBarcode) {
		this.lstBarcode = lstBarcode;
	}	
}
