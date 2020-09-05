package com.sealinkin.bdef.service.impl;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_WmsInterfacePlatModel;
import com.sealinkin.bdef.service.IBdef_WmsInterfacePlatService;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Bdef_WmsInterfacePlatImpl implements IBdef_WmsInterfacePlatService {

	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	@Override
	public ExtListDataBo<Bdef_WmsInterfacePlatModel> getWmsInterfacePlatList(
			String enterpriseNo, String str, PageBo poPageBo) throws Exception {
		// TODO Auto-generated method stub
		Integer count = 0;
		List<Bdef_WmsInterfacePlatModel> list = null;
		ExtListDataBo<Bdef_WmsInterfacePlatModel> extListBo= new ExtListDataBo<Bdef_WmsInterfacePlatModel>(list, count);
		String sql="select a.enterprise_no,a.plat_no,a.plat_desc,a.warehource_no,a.api_no,a.api_desc,           a.api_methmod,a.url_address,a.data_format,a.cust_code,a.app_key,a.app_secret,a.access_tokey,a.callback_address," +
		           "f_get_fieldtext('PLAT_TYPE','WMS_INTERFACE_LOG',a.plat_type) plat_type "+
				   " from wms_interface_plat a " +
				   " where 1=1 and a.enterprise_no='"+enterpriseNo+"' " ;
		String totsql="select count(*) " +
				   " from wms_interface_plat " +
				   " where 1=1 and enterprise_no='"+enterpriseNo+"' " ;
		list=genDao.getListByNativeSql(sql, Bdef_WmsInterfacePlatModel.class, poPageBo.getStart(), poPageBo.getPagesize());
		count = genDao.getCountByNativeSql(totsql);
		extListBo= new ExtListDataBo<Bdef_WmsInterfacePlatModel>(list, count);
        return extListBo;
	}

}
