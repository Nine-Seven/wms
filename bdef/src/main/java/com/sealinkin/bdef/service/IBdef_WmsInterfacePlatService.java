package com.sealinkin.bdef.service;

import com.sealinkin.bdef.model.Bdef_WmsInterfacePlatModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;

public interface IBdef_WmsInterfacePlatService {

	public ExtListDataBo<Bdef_WmsInterfacePlatModel> getWmsInterfacePlatList(
			String enterpriseNo,
			String str, PageBo poPageBo)throws Exception;
}
