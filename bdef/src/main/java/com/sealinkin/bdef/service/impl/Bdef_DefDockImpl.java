package com.sealinkin.bdef.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import com.sealinkin.bdef.service.IBdef_DefDockService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.bdef.model.Bdef_DefdockModel;
import com.sealinkin.bdef.po.Bdef_Defdock;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Bdef_DefDockImpl implements IBdef_DefDockService{
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	/**
	 * 获取码头下拉
	 */
	@Override
	public List<ComboxBo> queryBdefDefDock(String strWarehouseNo,
			String strFlag, String strWheresql,String enterpriseNo) throws Exception {
		String strSql="select t1.dock_no value,t1.dock_name text," +
				"'['|| ltrim(t1.dock_no)||']'||t1.dock_name dropValue " +
				"from bdef_defdock t1 where t1.warehouse_no='"+strWarehouseNo+"' " +
				"and t1.enterprise_no='"+enterpriseNo+"' ";
		
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+=" and t1.dock_no like '%"+strWheresql+"%' ";
		}
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	
	/**
	 * 保存或增加码头
	 */
	public Boolean add(String str) {
		
		Bdef_Defdock bdef_defDock=(Bdef_Defdock) JSONObject.toBean(JSONObject.fromObject(str),Bdef_Defdock.class);
		String sql="select * from bdef_defDock a where a.Dock_no='"+bdef_defDock.getId().getDockNo()+"' " +
				" and a.warehouse_no='"+bdef_defDock.getId().getWarehouseNo()+"' " +
				" and a.enterprise_no='"+bdef_defDock.getId().getEnterpriseNo()+"' ";
		List<Bdef_DefdockModel> list = genDao.getListByNativeSql(sql,Bdef_DefdockModel.class,0, 2000);
		
		if(list.size()==0){
			bdef_defDock.setUpdtDate(null);
			bdef_defDock.setUpdtName(null);
		}else{
			bdef_defDock.setRgstDate(list.get(0).getRgstDate());
			bdef_defDock.setRgstName(list.get(0).getRgstName());
		}
		this.genDao.saveOrUpdateObj(bdef_defDock);
		return true;		
	}

	/*
	 * 获得码头资料
	 * 
	 */
	@Override
	public ExtListDataBo<Bdef_DefdockModel> getBdef_DefDockList(String enterpriseNo,
			String warehouseNo,String queryStr, PageBo pageBo,Integer requestFlag) {
	String sql="select distinct a.*, f_get_fieldtext('N','DOCK_TYPE',A.DOCK_TYPE) as dockTypeText," +
			"f_get_fieldtext('BDEF_DEFDOCK','ADJUST_BOARD',A.ADJUST_BOARD) as adjustBoardText," +
			"f_get_fieldtext('BDEF_DEFDOCK','LOCATE_TYPE',A.LOCATE_TYPE) as locateTypeText" +
			" from bdef_defdock a where warehouse_no='"+warehouseNo+"' " +
			" and enterprise_no='"+enterpriseNo+"' ";
        String totsql = "select count(1) " 
				        +"from bdef_defdock a where warehouse_no='"+warehouseNo+"' " +
				        " and enterprise_no='"+enterpriseNo+"' ";
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
			totsql=totsql+ws;
		}
		sql=sql+" Order By dock_no";
		List<Bdef_DefdockModel> list = null;
		Integer count = 0;
		ExtListDataBo<Bdef_DefdockModel> extListBo= new ExtListDataBo<Bdef_DefdockModel>(list, count);
		if(requestFlag==1){
			list = genDao.getListByNativeSql(sql,Bdef_DefdockModel.class,pageBo.getStart(), pageBo.getPagesize());
			count = genDao.getCountByNativeSql(totsql);
		}else if(requestFlag==2){
			list = genDao.getListByNativeSql(sql,Bdef_DefdockModel.class);
			count = list.size();
		}
		extListBo= new ExtListDataBo<Bdef_DefdockModel>(list, count);
        return extListBo;
	}
	/*
	 * 删除供应商
	 * zhouhuan
	 */
	@Override
	public void delete(String enterpriseNo,String warehouseNo,String dockNo) {
		 String sql="delete from bdef_defDock where dock_no='" + dockNo + 
				    "' and warehouse_no='" + warehouseNo + "'" +
				    "' and enterprise_no='" + enterpriseNo + "'";
         genDao.updateBySql(sql);
	}

	@Override
	public List<ComboxBo> getDockComboList(String enterpriseNo,String warehouseNo) {
		String sql="select a.Dock_no as value,a.Dock_name as text,'['|| ltrim(a.Dock_no)||']'||a.Dock_name dropValue from bdef_defDock a where 1=1 " +
				   " and a.warehouse_no in ("+warehouseNo+") " +
				   " and a.enterprise_no ='"+enterpriseNo+"' ";
		List list= (List<Object>) genDao.getListByNativeSql(sql, ComboxBo.class);		 
		return  (List<ComboxBo>)list;
	}
	
	@Override
	public List<String> existsDockNo(String str, String warehouseNo,String enterpriseNo)
			throws Exception {
		String strSql="select t1.dock_no "+
				"from bdef_defdock t1 where 1=1  "+
				"and t1.dock_no='"+ str +"' "+
				"and t1.warehouse_no='"+warehouseNo+"' " +
				"and t1.enterprise_no='"+enterpriseNo+"' ";	
		List list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}
}
