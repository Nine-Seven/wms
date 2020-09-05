package com.sealinkin.idata.service;

import com.sealinkin.comm.model.MsgRes;

public interface Iidata_StraightCheckWorkService {

	
	public MsgRes save(String strJsonMaster, String strJsonDetail1) throws Exception;

}
