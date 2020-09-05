package com.sealinkin.protocolExchange.odata;

import java.io.Serializable;
import java.util.List;
/**
 *  对应StuAnsGetDivideBoxDetail
 * @author lich
 *
 */
public class AnsGetDivideBoxDetailModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AnsDivideBoxDetailModel> lstDetail;

	public List<AnsDivideBoxDetailModel> getLstDetail() {
		return lstDetail;
	}

	public void setLstDetail(List<AnsDivideBoxDetailModel> lstDetail) {
		this.lstDetail = lstDetail;
	}	
}
