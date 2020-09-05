package com.sealinkin.comm.service.impl;

import java.util.List;

import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.IExportI18nService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.wms.model.Wms_DeffielddescModel;

@SuppressWarnings({"rawtypes","unchecked"})
public class ExportI18nImpl implements IExportI18nService{

	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	@Override
	public List<Wms_DeffielddescModel> exportI18n() throws Exception {
		String strSql="select * from wms_deffielddesc order by field_id";
		List<Wms_DeffielddescModel> list = genDao.getListByNativeSql(strSql,Wms_DeffielddescModel.class);
		return list;
	}
	
	@Override
	public MsgRes insertI18n(String strSql) throws Exception {
		genDao.updateBySql(strSql);
		return new MsgRes(true,"","");
	}

}
