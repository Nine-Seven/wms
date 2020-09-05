package com.sealinkin.bdef.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import com.sealinkin.bdef.model.Bdef_WarehouseBaseModel;
import com.sealinkin.bdef.po.Bdef_WmsWarehouseBase;
import com.sealinkin.bdef.service.IBdef_WmsWarehouseBaseService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;

@SuppressWarnings({"rawtypes","unchecked"})
public class Bdef_WmsWarehouseBaseImpl implements IBdef_WmsWarehouseBaseService{
	
	private IGenericManager genDao;
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	@Override
	public ExtListDataBo<Bdef_WarehouseBaseModel> getWmsWarehouseBaseList(String enterpriseNo,
			String strWarehouseNo,String strGroupNo, String strSubGroupNo, PageBo poPageBo)
			throws Exception {
		String strSql=" select t1.*,'['||c1.warehouse_no||']'||c1.warehouse_name wareHouseText," +
				"'['|| ltrim(d1.sub_group_no)||']'||owner_get_fieldtext('wms_defbase',d1.sub_group_no)subGroupNoText," +
				"f_get_fieldtext('wms_defbase',d1.colname,d1.sdefine)sdefineText," +
				"owner_get_fieldtext('wms_defbase',d1.group_no)groupNoText " +
				" from wms_warehouse_base t1 left join  wms_defbase d1" +
				" on d1.colname=t1.colname  and d1.enterprise_no=t1.enterprise_no " +
				"  left join bdef_defloc c1 on c1.enterprise_no=t1.enterprise_no and c1.warehouse_no=t1.warehouse_no "+
				" where 1=1 and t1.warehouse_no='"+strWarehouseNo+
				"' and  t1.enterprise_no='"+enterpriseNo+"' "+
				" order by groupNoText,subGroupNoText,t1.colname";
		String strTotsql="select count(1)  from   wms_warehouse_base t1 left join  wms_defbase d1 " +
				"on d1.colname=t1.colname and d1.enterprise_no=t1.enterprise_no " +
				"where 1=1 and warehouse_no='"+strWarehouseNo+
				"' and  t1.enterprise_no='"+enterpriseNo+"' ";
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
		
		List<Bdef_WarehouseBaseModel> list=null;
		Integer intCount = 0;
		ExtListDataBo<Bdef_WarehouseBaseModel> extListBo=null;
		list = genDao.getListByNativeSql(strSql,Bdef_WarehouseBaseModel.class,poPageBo.getStart(),poPageBo.getPagesize());
		intCount = genDao.getCountByNativeSql(strTotsql);
		extListBo= new ExtListDataBo<Bdef_WarehouseBaseModel>(list, intCount);
        return extListBo;
	}

	@Override
	public MsgRes saveWmsWarehouseBase(String strWmsWarehouseBase) throws Exception {
		Bdef_WmsWarehouseBase poDd = (Bdef_WmsWarehouseBase)JSONObject.toBean(
				JSONObject.fromObject(strWmsWarehouseBase),Bdef_WmsWarehouseBase.class);
				genDao.saveOrUpdateObj(poDd);
			return new MsgRes(true,"保存成功","");
		}

	@Override
	public List<ComboxBo> getGroupNoCombo() {
		 String varSql = "select distinct t1.group_no value,f_get_fieldtext('WMS_DEFBASE','GROUP_NO',t1.group_no) text," +
			 		" '['|| ltrim(t1.group_no)||']'||owner_get_fieldtext('wms_defbase',t1.group_no) dropValue" +
			 		" from wms_warehouse_base t1 where 1=1 ";
				List list= genDao.getListByNativeSql(varSql,ComboxBo.class,0,10);	
				return  (List<ComboxBo>)list;
		}

	@Override
	public List<ComboxBo> getSubGroupNoCombo(String enterpriseNo,
			String warehouseNo,String strGroupNo) {
		String strSql="select distinct t1.sub_group_no value," +
				"'['|| ltrim(t1.sub_group_no)||']'||owner_get_fieldtext('wms_defbase',t1.sub_group_no) dropValue " +
				"from wms_warehouse_base t1 where 1=1 " +
				" and t1.group_no='"+strGroupNo+
				"' and t1.warehouse_no='"+warehouseNo+
			    "' and t1.enterprise_no='"+enterpriseNo+"'";
			List list= genDao.getListByNativeSql(strSql,ComboxBo.class,0,10);	
			return  (List<ComboxBo>)list;
	}
	
	
	@Override
	public List<ComboxBo> getAllGroupNoCombo() {
		 String varSql = "select distinct t1.group_no value,f_get_fieldtext('WMS_DEFBASE','GROUP_NO',t1.group_no) text," +
			 		" '['|| ltrim(t1.group_no)||']'||owner_get_fieldtext('wms_defbase',t1.group_no) dropValue" +
			 		" from wms_defbase t1 where 1=1 ";
				List list= genDao.getListByNativeSql(varSql,ComboxBo.class,0,10);	
				return  (List<ComboxBo>)list;
		}

	@Override
	public List<ComboxBo> getAllSubGroupNoCombo(String strGroupNo) {
		String strSql="select distinct t1.sub_group_no value," +
				"'['|| ltrim(t1.sub_group_no)||']'||owner_get_fieldtext('wms_defbase',t1.sub_group_no) dropValue " +
				"from wms_defbase t1 where 1=1 " +
				"and Group_No='"+strGroupNo+"'";
			List list= genDao.getListByNativeSql(strSql,ComboxBo.class,0,10);	
			return  (List<ComboxBo>)list;
	}
	
	
	@Override
	public List<ComboxBo> getSdefineCombo(String strColname) {
		String strSql="select distinct  d.value value," +
				"'['|| ltrim(d.value)||']'||d.text dropValue " +
				"from wms_deffieldval d " +
				"where d.table_name='WMS_DEFBASE'  and d.colname=upper('"+strColname+"')";
			List list= genDao.getListByNativeSql(strSql,ComboxBo.class,0,10);	
			return  (List<ComboxBo>)list;
	}

	@Override
	public List<ComboxBo> getAllFromGroupNoCombo(String enterpriseNo,
			String warehouseNo) {
		
		
		 String varSql = "select 'ALL' as value ,'ALL' text ,'ALL' dropvalue from wms_defbase a union " +
				   "select distinct t1.group_no value,f_get_fieldtext('WMS_DEFBASE','GROUP_NO',t1.group_no) text," +
			 		" '['|| ltrim(t1.group_no)||']'||owner_get_fieldtext('wms_defbase',t1.group_no) dropValue" +
			 		" from wms_warehouse_base t1 where 1=1 " +
			 		" and t1.warehouse_no='"+warehouseNo+
			 		"' and t1.enterprise_no='"+enterpriseNo+"'";
				List list= genDao.getListByNativeSql(varSql,ComboxBo.class,0,10);	
				return  (List<ComboxBo>)list;
		}
	
	@Override
	public List<ComboxBo> getColnameCombo(String strGroupNo) {
		 String strSql = " select distinct t1.colname value,t1.colname text," +
		 		"ltrim(t1.colname) dropValue " +
		 		"from wms_defbase t1 where 1=1 ";
		 if(strGroupNo!=null && !strGroupNo.equals(""))
			{
				strSql=strSql+" and t1.group_no in('"+strGroupNo+"') ";
			}		
				List list= genDao.getListByNativeSql(strSql,ComboxBo.class,0,100);	
				return  (List<ComboxBo>)list;
	}
	@Override
	public List<String> getMemoCombo(String strColname) {
		String strSql="select distinct  d.memo " +
				"from wms_defbase d " +
				"where d.colname='"+strColname+"'";
		List list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}

}
