package com.sealinkin.comm.service;

import java.util.List;

import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.wms.model.Wms_DeffielddescModel;

public interface IExportI18nService {

	public List<Wms_DeffielddescModel> exportI18n()throws Exception;
	
	public MsgRes insertI18n(String strSql) throws Exception;
}
