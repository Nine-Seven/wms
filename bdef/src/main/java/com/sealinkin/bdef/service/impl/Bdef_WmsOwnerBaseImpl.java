/**
 * 货主参数配置service
 * @author chensr
 */
package com.sealinkin.bdef.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.service.IBdef_WmsOwnerBaseService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;
import com.sealinkin.wms.model.Wms_OwnerBaseModel;
import com.sealinkin.wms.po.Wms_OwnerBase;

@SuppressWarnings({"rawtypes","unchecked"})
public class Bdef_WmsOwnerBaseImpl implements IBdef_WmsOwnerBaseService {
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	/*
	 * @func 获得货主参数配置信息
	 * @param strQuery 查询条件， pageBo 显示条数
	 * @return list  查询获得的货主参数配置信息
	 */
	@Override
	public ExtListDataBo<Wms_OwnerBaseModel> getWmsOwnerBaseList(
			String enterpriseNo,String str, PageBo pageBo) {
		String sql="select wob.*," +
				" '['||b.owner_no||']'||b.owner_name ownerNameText,"+
				"f_get_fieldtext('WMS_DEFBASE',wob.colname,wob.sdefine)sdefineText, " +
				"owner_get_fieldtext('wms_defbase',wob.group_no)groupNoText ,"+
				"owner_get_fieldtext('wms_defbase',wob.sub_group_no)subGroupNoText "+
				"from wms_owner_base wob ,bdef_defowner b where 1=1 and wob.owner_no=b.owner_no " +
				"order by groupNoText,subGroupNoText,wob.colname";
		String strTotsql = "select count(1) from wms_owner_base wob where 1=1 ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
			strTotsql=strTotsql+ws;
		}		
		List<Wms_OwnerBaseModel> list = genDao.getListByNativeSql(sql,Wms_OwnerBaseModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Wms_OwnerBaseModel> extListBo = new ExtListDataBo<Wms_OwnerBaseModel>(list,intCount);
		return extListBo;
	}

	/*
	 * @func 保存货主参数信息
	 * @param str 存放货主信息的json数据 
	 */
	@Override
	public void saveWmsOwnerBase(String str) throws Exception {
		Wms_OwnerBase ownerBase=(Wms_OwnerBase)JSON.parseObject(str,Wms_OwnerBase.class);
		genDao.saveOrUpdateObj(ownerBase);
	}

	/*
	 * @func 获取colName下拉列表
	 * @return list colName列表
	 */
	@Override
	public List<ComboxBo> getColNameComboList(String str) throws Exception {
		String strSql="select distinct t1.colname value,t1.colname text," +
				"ltrim(t1.colname) dropValue " +
				"from wms_defbase t1 where 1=1  "+
				"and mod(TO_NUMBER(t1.use_level)/10,10)>=1  "+
				"and t1.group_no = '"+str+"'";
						
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}

	/*
	 * @func 获取groupNo下拉列表
	 * @return list groupNo列表
	 */
	@Override
	public List<ComboxBo> getGroupNoComboList() throws Exception {
		String strSql="select distinct t1.group_no value," +
				"f_get_fieldtext('WMS_DEFBASE','GROUP_NO',t1.group_no) text, " +
				"'['|| ltrim(t1.group_no)||']'||owner_get_fieldtext('wms_defbase',t1.group_no) dropValue " +
				"from wms_defbase t1 where 1=1  "+
				"and mod(TO_NUMBER(t1.use_level)/10,10)>=1  ";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}

	/*
	 * @func 根据groupNo获取subGroupNo下拉列表(用于窗口)
	 * @param strQuery 查询条件(groupNo)
	 * @return list subGroupNo列表
	 */
	@Override
	public List<ComboxBo> getSubGroupNoComboList(String strQuery) throws Exception {
		String strSql="select distinct t1.sub_group_no value,"+
				"f_get_fieldtext('WMS_DEFBASE','SUB_GROUP_NO', t1.sub_group_no) text, " +
				"'['|| ltrim(t1.sub_group_no)||']'||owner_get_fieldtext('wms_defbase',t1.sub_group_no) dropValue " +
				"from wms_defbase t1 where 1=1  " +
				"and group_no='"+
				strQuery+
				"'";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}

	/*
	 * @func 获取sdefine下拉列表
	 * @param strQuery 查询条件
	 * @return list sdefine列表
	 */
	@Override
	public List<ComboxBo> getSdefineComboList(String strQuery) throws Exception {
		String strSql="	select t1.value value,t1.text text," +
				 "'['|| ltrim(t1.value)||']'||t1.text dropValue " +
				 "from wms_deffieldval t1 where 1=1  " +
			     "and colname=upper('"+
			     strQuery+
			     "')";	
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	/*
	 * @func 根据colname查找Memo
	 * @param strQuery 查询条件（colname）
	 * @return list  memo字符串
	 */
	@Override
	public List<String> getMemo(String strQuery) throws Exception {
		String strSql="select t1.memo "+
				"from wms_defbase t1 where 1=1  "+
				"and colname='"+
				strQuery+"'";
		
		List list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}

	/*
	 * 获取业务下拉按钮(用于UI界面的业务下拉)
	 */
	@Override
	public List<ComboxBo> getGroupNoComboListForUI(String enterpriseNo) {
		String strSql="select 'ALL' as value, 'ALL' as text, 'ALL' as dropValue from wms_defbase "+
				"union "+
				"select distinct t1.group_no value," +
				"f_get_fieldtext('WMS_DEFBASE','GROUP_NO',t1.group_no) text, " +
				"'['|| ltrim(t1.group_no)||']'||owner_get_fieldtext('wms_defbase',t1.group_no) dropValue " +
				"from wms_defbase t1 where 1=1  and t1.group_no in(select distinct group_no from wms_owner_base) ";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}

	/*
	 * @func 根据groupNo获取subGroupNo下拉列表（用于UI）
	 * @param strQuery 查询条件(groupNo)
	 * @return list subGroupNo列表
	 */
	@Override
	public List<ComboxBo> getSubGroupNoComboListForUI(String enterpriseNo,String strQuery)
			throws Exception {
		String strSql="select distinct t1.sub_group_no value,"+
				"f_get_fieldtext('WMS_DEFBASE','SUB_GROUP_NO', t1.sub_group_no) text, " +
				"'['|| ltrim(t1.sub_group_no)||']'||owner_get_fieldtext('wms_defbase',t1.sub_group_no) dropValue " +
				"from wms_defbase t1 where 1=1  " +
				"and group_no='"+
				strQuery+
				"' and t1.sub_group_no in(select distinct sub_group_no from wms_owner_base)";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
}
