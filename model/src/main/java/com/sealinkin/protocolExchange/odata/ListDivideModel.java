package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;
import java.util.Map;
/**
 * 分播明细集合（读分播标签返回结构）
 * @author lich
 *
 */
public class ListDivideModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer, DivideDateAnswerModel> ArrStuDivide;      // map<序号,分播信息>

	public Map<Integer, DivideDateAnswerModel> getArrStuDivide() {
		return ArrStuDivide;
	}

	public void setArrStuDivide(Map<Integer, DivideDateAnswerModel> arrStuDivide) {
		ArrStuDivide = arrStuDivide;
	}
}
