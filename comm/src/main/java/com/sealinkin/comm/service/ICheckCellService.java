package com.sealinkin.comm.service;

import com.sealinkin.comm.model.MsgRes;

/**
 * 储位校验接口
 * @author zhouhuan
 *
 */
public interface ICheckCellService {
	
	//储位校验
	public MsgRes checkCell(String strOwnerNo, String strCellNo,String strWarehouseNo,String strEnterpriseNo);

}
