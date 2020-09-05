package com.sealinkin.bdef.service.impl;

import java.util.List;

import com.sealinkin.bdef.service.IBdef_ControlLogService;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.wms.model.Wms_InterfaceLogModel;

@SuppressWarnings({"rawtypes","unchecked"})
public class Bdef_ControlLogImpl implements IBdef_ControlLogService{
	
	 private IGenericManager genDao;
	 public IGenericManager getGenDao() {
		 return genDao;
	 }
	 public void setGenDao(IGenericManager genDao) {
		 this.genDao = genDao;
	 }
	 
	 //获取接口监控日志列表
	@Override
	public ExtListDataBo<Wms_InterfaceLogModel> getInterfaceLogList(String enterpriseNo,
			PageBo pageBo)
			throws Exception {
		String sql="select f_get_fieldtext('PLAT_TYPE','WMS_INTERFACE_LOG',a.plat_type) plat_type," +
				"a.operate_date,a.plat_type,a.plat_no,a.enterprise_no,a.warehource_no," +
				"a.api_no,a.api_desc,a.seq_no,a.exec_desc,a.exec_time " +
				" from wms_interface_log a  where a.exec_status<>'0' ";
		String totsql="select count(1) from wms_interface_log a " ;
		
	
		sql += "order by a.enterprise_no,a.warehource_no,a.operate_date,a.seq_no ";
		List<Wms_InterfaceLogModel> list = null;
		Integer count = 0;
		ExtListDataBo<Wms_InterfaceLogModel> extListBo=null;
	    list = genDao.getListByNativeSql(sql,Wms_InterfaceLogModel.class);
		count = genDao.getCountByNativeSql(totsql);
		
		extListBo= new ExtListDataBo<Wms_InterfaceLogModel>(list, count);
        return extListBo;
	}
	
	//获取接口监控日志汇总列表
	@Override
	public ExtListDataBo<Wms_InterfaceLogModel> getInterfaceLogSumList(
			String enterpriseNo, PageBo pageBo) throws Exception {
		String sql="select count(*) as execTimeQty, "+
				"(select count(*) from wms_interface_log t " +
				"  where t.exec_status='0' " +
				"  group by a.operate_date,a.plat_type,a.plat_no,a.enterprise_no,a.warehource_no,a.api_no,a.api_desc ) as yesTimeQty, "+
				"(select count(*) from wms_interface_log t " +
				"  where t.exec_status<>'0' " +
				"  group by a.operate_date,a.plat_type,a.plat_no,a.enterprise_no,a.warehource_no,a.api_no,a.api_desc ) as noTimeQty, "+
				"max(a.exec_time) as lastTime,a.operate_date," +
				"f_get_fieldtext('PLAT_TYPE','WMS_INTERFACE_LOG',a.plat_type) plat_type," +
				"a.plat_no,a.enterprise_no,a.warehource_no,a.api_no,a.api_desc "+
				"from wms_interface_log a " +
				"group by a.operate_date,a.plat_type,a.plat_no,a.enterprise_no,a.warehource_no,a.api_no,a.api_desc" ;
		String totsql="select count(1) from ("+sql+") " ;
		
	
		List<Wms_InterfaceLogModel> list = null;
		Integer count = 0;
		ExtListDataBo<Wms_InterfaceLogModel> extListBo=null;
	    list = genDao.getListByNativeSql(sql,Wms_InterfaceLogModel.class);
		count = genDao.getCountByNativeSql(totsql);
		
		extListBo= new ExtListDataBo<Wms_InterfaceLogModel>(list, count);
        return extListBo;
	}
	
}
