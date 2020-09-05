package com.sealinkin.protocolExchange.print;

import java.io.Serializable;
import java.util.Map;


/**
 * 获取字段信息请求 应答  StuAnsFieldDesc
 * @author lich
 *
 */
public class AnsFieldDescModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, CProgramDescModel> ArrStuField;       //字段信息

	public Map<String, CProgramDescModel> getArrStuField() {
		return ArrStuField;
	}

	public void setArrStuField(Map<String, CProgramDescModel> arrStuField) {
		ArrStuField = arrStuField;
	}
}
