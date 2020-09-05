package com.sealinkin.bdef.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import com.sealinkin.bdef.model.Bdef_WmsDefbaseModel;
import com.sealinkin.bdef.po.Bdef_WmsDefbase;
import com.sealinkin.bdef.service.IBdef_WmsDefbaseService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;

@SuppressWarnings({"rawtypes","unchecked"})
public class Bdef_WmsDefbaseImpl implements IBdef_WmsDefbaseService{
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	@Override
	public ExtListDataBo<Bdef_WmsDefbaseModel> getWmsDefbaseList(
			String enterpriseNo,String strGroupNo,String strSubGroupNo, PageBo poPageBo) throws Exception {
		String strSql=" select t1.*," +                                                    
				"'['|| ltrim(t1.sub_group_no)||']'||owner_get_fieldtext('wms_defbase',t1.sub_group_no)subGroupNoText," +
				"f_get_fieldtext('WMS_DEFBASE',t1.colname,t1.sdefine)sdefineText," +
				"owner_get_fieldtext('wms_defbase',t1.group_no)groupNoText" +
				" from wms_defbase t1" +
				" where 1=1 and t1.enterprise_no='"+enterpriseNo+"' " +
				" order by groupNoText,subGroupNoText,t1.colname ";
		String strTotsql="select count(1) from wms_defbase t1 where 1=1  and t1.enterprise_no='"+enterpriseNo+"' ";
		if(strGroupNo!=null && !strGroupNo.equals("")&& !strGroupNo.equals("ALL"))
		{
			strSql=strSql+" and t1.group_no in('"+strGroupNo+"') ";
			strTotsql=strTotsql+" and t1.group_no in('"+strGroupNo+"')";
		}
		if(strSubGroupNo!=null && !strSubGroupNo.equals(""))
		{
			strSql=strSql+" and t1.sub_group_no in('"+strSubGroupNo+"') ";
			strTotsql=strTotsql+" and t1.sub_group_no in('"+strSubGroupNo+"')";
		}
		
		List<Bdef_WmsDefbaseModel> list=null;
		Integer intCount = 0;
		ExtListDataBo<Bdef_WmsDefbaseModel> extListBo=null;
		list = genDao.getListByNativeSql(strSql,Bdef_WmsDefbaseModel.class,poPageBo.getStart(),poPageBo.getPagesize());
		intCount = genDao.getCountByNativeSql(strTotsql);
		extListBo= new ExtListDataBo<Bdef_WmsDefbaseModel>(list, intCount);
        return extListBo;
	}
	
	@Override
	public MsgRes saveWmsDefbase(String strColnameNo)
			throws Exception {
		Bdef_WmsDefbase poDd = (Bdef_WmsDefbase)JSONObject.toBean(
				JSONObject.fromObject(strColnameNo),Bdef_WmsDefbase.class);
				genDao.saveOrUpdateObj(poDd);
			return new MsgRes(true,"保存成功","");
		}
	
//	@Override
//	public List<ComboxBo> getGroupNoCombo(String enterpiseNo) {
//		 String varSql = "select distinct t1.group_no value,f_get_fieldtext('WMS_DEFBASE','GROUP_NO',t1.group_no) text," +
//		 		" '['|| ltrim(t1.group_no)||']'||owner_get_fieldtext('wms_defbase',t1.group_no) dropValue" +
//		 		" from wms_defbase t1 where 1=1 and t1.enterprise_no='"+enterpiseNo+"' ";
//			List list= genDao.getListByNativeSql(varSql,ComboxBo.class,0,10);	
//			return  (List<ComboxBo>)list;
//	}
	
	@Override
	public List<ComboxBo> getSubGroupNoCombo(String enterpiseNo,String strGroupNo) {
		String strSql="select distinct t1.sub_group_no value," +
				"'['|| ltrim(t1.sub_group_no)||']'||owner_get_fieldtext('wms_defbase',t1.sub_group_no) dropValue " +
				"from wms_defbase t1 where 1=1 and t1.enterprise_no='"+enterpiseNo+"' "+
				"and Group_No='"+strGroupNo+"'";
			List list= genDao.getListByNativeSql(strSql,ComboxBo.class,0,10);	
			return  (List<ComboxBo>)list;
	}
	/**
	 * 通过模块加载全部数据
	 * */
	@Override
	public List<ComboxBo> getAllFromGroupNoCombo(String enterpiseNo) {
		 String varSql = "select 'ALL' as value ,'ALL' text ,'ALL' dropvalue from wms_defbase a union " +
				   "select distinct t1.group_no value,f_get_fieldtext('WMS_DEFBASE','GROUP_NO',t1.group_no) text," +
			 		" '['|| ltrim(t1.group_no)||']'||owner_get_fieldtext('wms_defbase',t1.group_no) dropValue" +
			 		" from wms_defbase t1 where 1=1 and t1.enterprise_no='"+enterpiseNo+"' ";
				List list= genDao.getListByNativeSql(varSql,ComboxBo.class,0,10);	
				return  (List<ComboxBo>)list;
		}
	
	
	@Override
	public List<ComboxBo> getSdefineCombo(String colname) {
		String strSql="select distinct  d.value value," +
				"'['|| ltrim(d.value)||']'||d.text dropValue " +
				"from wms_deffieldval d " +
				"where d.table_name='WMS_DEFBASE'  and d.colname=upper('"+colname+"')";
			List list= genDao.getListByNativeSql(strSql,ComboxBo.class,0,10);	
			return  (List<ComboxBo>)list;
	}
	
}

