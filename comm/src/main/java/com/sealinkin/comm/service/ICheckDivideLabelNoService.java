package com.sealinkin.comm.service;

import com.sealinkin.comm.model.MsgRes;

public interface ICheckDivideLabelNoService {
    
	/**
	 * 获取分播回单目的标签号
	 * @param strArticleNo 
	 * @param strCustNo2 
	 * @param strLabelNo2 
	 * @throws Exception 
	 */
	MsgRes checkDivideLabelNo(String strEnterpriseNo,String strWarehouseNo, String ownerNo,
			String strSContainerNo,String strLabelNo,
			String strCustNo, String strArticleNo,String deliverObj) throws Exception;
}
