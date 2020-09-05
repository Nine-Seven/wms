/**
 * 货主仓别参数配置service
 * @author chensr
 */
package com.sealinkin.bdef.service.impl;


import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.service.IBdef_WmsWarehouseOwnerBaseService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;
import com.sealinkin.wms.model.Wms_WarehouseOwnerBaseModel;
import com.sealinkin.wms.po.Wms_WarehouseOwnerBase;



@SuppressWarnings({"rawtypes","unchecked"})
public class Bdef_WmsWarehouseOwnerBaseImpl implements
		IBdef_WmsWarehouseOwnerBaseService {
	
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	/*
	 * @func 获得货主仓别参数配置信息
	 * @param strWheresql 查询条件， pageBo 显示条数的限制
	 * @return list  查询获得的货主仓别参数配置信息
	 */
	@Override
	public ExtListDataBo<Wms_WarehouseOwnerBaseModel> getWarehouseOwnerBaselist(
			String enterpriseNo,String warehouseNo, String strWheresql, PageBo pageBo) throws Exception {
	
		String sql="select t1.*,"+
				"'['||t1.owner_no||']'||b.owner_name ownerNameText,"+
				"'['||t1.warehouse_no||']'||c.warehouse_name wareHouseNameText,"+
				"f_get_fieldtext('WMS_DEFBASE',t1.colname, to_number(t1.sdefine))sdefineText, " +
				"owner_get_fieldtext('wms_defbase',t1.group_no)groupNoText ,"+
				"owner_get_fieldtext('wms_defbase',t1.sub_group_no)subGroupNoText "+
				"from wms_warehouse_owner_base t1 " +
				"inner join bdef_defowner b on b.owner_no=t1.owner_no and b.enterprise_no=t1.enterprise_no  "+
				"inner join bdef_defloc c on c.warehouse_no=t1.warehouse_no and c.enterprise_no=t1.enterprise_no    "+
				" where 1=1 "+
				"and t1.warehouse_no ='"+warehouseNo+
				"' and t1.enterprise_no='"+enterpriseNo+"' "+
				" order by groupNoText,subGroupNoText,t1.colname";
		String strTotsql="select count(1) from wms_warehouse_owner_base t1 where 1=1 "+
				"and t1.warehouse_no ='"+warehouseNo+
				"' and t1.enterprise_no='"+enterpriseNo+"' ";
		
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strWheresql);
			sql=sql+ws;
			strTotsql=strTotsql+ws;
		}		
		List<Wms_WarehouseOwnerBaseModel> list = genDao.getListByNativeSql(sql,Wms_WarehouseOwnerBaseModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Wms_WarehouseOwnerBaseModel> extListBo = new ExtListDataBo<Wms_WarehouseOwnerBaseModel>(list,intCount);
		return extListBo;
	}

	
	/*
	 * @func 保存货主仓别 参数信息
	 * @param str 存放货主仓别信息的json数据 
	 */
	@Override
	public void saveWarehouseOwnerBase(String str) throws Exception {
		Wms_WarehouseOwnerBase warehouseOwnerBase=(Wms_WarehouseOwnerBase )JSON.parseObject(str,Wms_WarehouseOwnerBase .class);
		genDao.saveOrUpdateObj(warehouseOwnerBase);
	}

	/*
	 * @func 根据group_no和sub_group_no获取子colname下拉按钮
	 * @return list colName列表
	 */
	@Override
	public List<ComboxBo> getColNameComboList(String str) throws Exception {
		String strSql="select distinct t1.colname value,t1.colname text," +
				"ltrim(t1.colname) dropValue " +
				"from wms_defbase t1 where 1=1  "+
				"and mod(TO_NUMBER(t1.use_level),10)=1  ";
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}else
		{
			strSql=strSql+"and t1.group_no=''";
		}		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}

	/*
	 * @func 获取groupNo下拉列表
	 * @return groupNo列表
	 */
	@Override
	public List<ComboxBo> getGroupNoComboList() {
		String strSql="select distinct t1.group_no value," +
				"f_get_fieldtext('WMS_DEFBASE','GROUP_NO',t1.group_no) text, " +
				"'['|| ltrim(t1.group_no)||']'||owner_get_fieldtext('wms_defbase',t1.group_no) dropValue " +
				"from wms_defbase t1 where 1=1  "+
				"and mod(TO_NUMBER(t1.use_level),10)=1  ";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}

	/*
	 * @func 根据groupNo获取subGroupNo下拉列表（用于窗口）
	 * @param strQuery 查询条件(groupNo)
	 * @return list subGroupNo列表
	 */
	@Override
	public List<ComboxBo> getSubGroupNoComboList(String strQuery) {
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
	 * @func 根据colname查找Memo
	 * @param strQuery 查询条件（colname）
	 * @return list  memo字符串
	 */
	@Override
	public List<String> getMemo(String strQuery) {
		String strSql="select t1.memo "+
				"from wms_defbase t1 where 1=1  "+
				"and colname='"+
				strQuery+"'";
		
		List list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}

	/*
	 * @func 获取sdefine下拉列表
	 * @param strQuery 查询条件
	 * @return list sdefine列表
	 */
	@Override
	public List<ComboxBo> getSdefineComboList(String strQuery) {
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
	 * 获取业务下拉按钮(用于UI界面的业务下拉)
	 */
	@Override
	public List<ComboxBo> getGroupNoComboListForUI(String enterpriseNo,String warehouseNo) throws Exception {
		String strSql="select 'ALL' as value, 'ALL' as text, 'ALL' as dropValue from wms_defbase "+
				"union "+
				"select distinct t1.group_no value," +
				"f_get_fieldtext('WMS_DEFBASE','GROUP_NO',t1.group_no) text, " +
				"'['|| ltrim(t1.group_no)||']'||owner_get_fieldtext('wms_defbase',t1.group_no) dropValue " +
				"from wms_defbase t1 where 1=1  and t1.group_no in(select distinct group_no from wms_warehouse_owner_base" +
				" where warehouse_no ='"+warehouseNo+
				"' and enterprise_no='"+enterpriseNo+"' )  and t1.enterprise_no='"+enterpriseNo+"' ";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}

	/*
	 * @func 根据groupNo获取subGroupNo下拉列表（用于UI）
	 * @param strQuery 查询条件(groupNo)
	 * @return list subGroupNo列表
	 */
	@Override
	public List<ComboxBo> getSubGroupNoComboListForUI(String enterpriseNo,String warehouseNo,
			String strQuery) throws Exception {
		String strSql="select distinct t1.sub_group_no value,"+
				"f_get_fieldtext('WMS_DEFBASE','SUB_GROUP_NO', t1.sub_group_no) text, " +
				"'['|| ltrim(t1.sub_group_no)||']'||owner_get_fieldtext('wms_defbase',t1.sub_group_no) dropValue " +
				"from wms_defbase t1 where 1=1  " +
				"and group_no='"+
				strQuery+
				"' and t1.sub_group_no in(select distinct sub_group_no from wms_warehouse_owner_base where warehouse_no='"+warehouseNo+
				"' and enterprise_no='"+enterpriseNo+"' )";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
}
