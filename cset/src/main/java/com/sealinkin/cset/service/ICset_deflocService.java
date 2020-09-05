package com.sealinkin.cset.service;

import com.sealinkin.bdef.model.Bdef_DeflocModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;


public interface ICset_deflocService {

	ExtListDataBo<Bdef_DeflocModel> getLoc(String currEnterpriseNo,
			PageBo pageBo, String strQuery) throws Exception;

	MsgRes saveOrUpdateDefloc(String str) throws Exception;

}
