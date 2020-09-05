package com.sealinkin.protocolExchange.dpsdata;

import java.io.Serializable;
import java.util.List;

/**
        电子标签数据   StuAnsGetDivideSuppBox
 * @author lich
 *
 */
public class AnsGetDivideSuppBoxModel implements Serializable{    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<DivideSuppBoxDetailModel> lstDetail;

	public List<DivideSuppBoxDetailModel> getLstDetail() {
		return lstDetail;
	}

	public void setLstDetail(List<DivideSuppBoxDetailModel> lstDetail) {
		this.lstDetail = lstDetail;
	}
	
}
