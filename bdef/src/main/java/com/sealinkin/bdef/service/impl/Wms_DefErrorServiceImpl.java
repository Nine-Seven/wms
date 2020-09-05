package com.sealinkin.bdef.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sealinkin.bdef.model.Wms_DefErrorModel;
import com.sealinkin.bdef.service.IWms_DefErrorService;
import com.sealinkin.cons.SystemConstant;
import com.sealinkin.dao.service.IGenericManager;

@SuppressWarnings({"rawtypes","unchecked"})
public class Wms_DefErrorServiceImpl implements IWms_DefErrorService
{
	
	private IGenericManager genDao;
	public IGenericManager getGenDao() 
	{
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) 
	{
		this.genDao = genDao;
	}

	@Override
	public List<Wms_DefErrorModel> queryWmsDefErrorList()
	{
		String sql = "select * from WMS_DEFERROR";
		List<Wms_DefErrorModel> list = genDao.getListByNativeSql(sql, Wms_DefErrorModel.class);
		return list;
	}
	
	
	@Override
	public void loadWmsDefError()
	{
		Map<String,String> tips = new HashMap<String,String>();
		List<Wms_DefErrorModel> list = queryWmsDefErrorList();
		for (int i = 0; i < list.size(); i++)
		{
			tips.put(list.get(i).getErrorid(), list.get(i).getErrordesc());
		}
		SystemConstant.tips = tips;
	}

}
