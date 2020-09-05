package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;
import java.util.Map;
/**
 * 下架明细集合（读拣货任务标签返回结构）
 * @author lich
 *
 */
public class ListOutStockAnswerModel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Map<Integer, OutStockDateAnswerModel> ArrStuOutStock;      // Map<序号,下架信息>

	public Map<Integer, OutStockDateAnswerModel> getArrStuOutStock() {
		return ArrStuOutStock;
	}

	public void setArrStuOutStock(
			Map<Integer, OutStockDateAnswerModel> arrStuOutStock) {
		ArrStuOutStock = arrStuOutStock;
	}
}
