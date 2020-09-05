package com.sealinkin.comm.service.impl;

import java.util.List;

import com.sealinkin.comm.service.IWms_DefModuleQueryColumnm;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.wms.model.Wms_DefModuleQueryColumnmModel;

@SuppressWarnings({"unchecked","rawtypes"})
public class Wms_DefModuleQueryColumnmImpl implements IWms_DefModuleQueryColumnm{
	
	private IGenericManager genDao;
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	public List<Wms_DefModuleQueryColumnmModel> getModuleQueryColumn(String enterpriseNo,String moduleId) {
		String sql="select moduleid, " +
			       "case " +
			         "when xtype='datetimefield' then " +
			         "'trunc(' || columnid ||',''mi'')' " +
			       "else columnid " +
			       "end columnid, " +
			       "columnname, " +
			       "xtype, " +
			       "fieldtable, " +
			       "fieldcolumn, " +
			       "orderno, " +
			       "enterprise_no " +
				   " from WMS_DefModuleQueryColumn " +
				   " where 1=1 and moduleId='"+moduleId+"' " +
				   " and enterprise_no='" +enterpriseNo+"' "+
				   " order by orderNo";
		List<Wms_DefModuleQueryColumnmModel> list=genDao.getListByNativeSql(sql, Wms_DefModuleQueryColumnmModel.class, 0, 100);				
		return list;
	}
}
