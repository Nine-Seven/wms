package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;
import java.util.List;
/**
 * 上架扫标签或流水号 对应StuIMInstockLableOrSerialNoAnswer
 * @author lich
 *
 */
public class IdataInstockLableOrSerialNoAnswerModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String flag;//1:扫标签号：2：扫流水
	private List<IdataInStockLabelAnswerModel>  listInStLaAns;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public List<IdataInStockLabelAnswerModel> getListInStLaAns() {
		return listInStLaAns;
	}
	public void setListInStLaAns(List<IdataInStockLabelAnswerModel> listInStLaAns) {
		this.listInStLaAns = listInStLaAns;
	}
}
