/**
 * 模块名称：杂项费用维护
 * 模块编码：B203
 * 创建：hcx 
 */
package com.sealinkin.cost.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_OtherListModel;
import com.sealinkin.cost.model.Cost_OtherSetModel;
import com.sealinkin.cost.po.Cost_OtherList;
import com.sealinkin.cost.po.Cost_OtherSet;
import com.sealinkin.cost.service.ICost_OtherService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Cost_OtherImpl implements ICost_OtherService {
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	//获取杂项费用信息列表
	@Override
	public ExtListDataBo<Cost_OtherListModel> getCostOtherList(
			String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner, String str, PageBo pageBo) throws Exception {
		String sql="select t1.*,t2.cost_name, " +
				"'['|| ltrim(t1.cost_flag)||']'||f_get_fieldtext('COST_OTHER_LIST','COST_FLAG',t1.cost_flag)costFlagText, " +
				"'['|| ltrim(t1.status)||']'||f_get_fieldtext('COST_OTHER_LIST','STATUS',t1.status)statusText, " +
                "to_char(t1.cost_date,'yyyy-mm-dd')costDateText, " +
                "'['|| ltrim(t1.cost_no)||']'||t2.cost_name costNoText, " +
                "'['|| ltrim(t1.owner_no)||']'||t3.owner_name ownerNoText " +
           "from " +
                "cost_other_list t1, " +
                "cost_other_set t2, " +
                "bdef_defowner t3 " +
          "where 1=1   " +
            "and t1.enterprise_no=t2.enterprise_no  " +
            "and t1.cost_no=t2.cost_no  " +
            "and t1.enterprise_no=t2.enterprise_no " +
            "and t1.owner_no=t3.owner_no " +
			"and t1.warehouse_no ='"+strWarehouseNo+"' " +
			"and t1.enterprise_no= '"+strEnterpriseNo+"' "+
			"and t1.owner_no in("+strWorkerOwner+") ";

		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}	
		sql += "order by t1.owner_no,t1.cost_no,t1.cost_date desc ";
		String strTotsql="select count(*) from (" + sql + ") " ;	
		List<Cost_OtherListModel> list = genDao.getListByNativeSql(sql,Cost_OtherListModel.class/*,pageBo.getStart(), pageBo.getPagesize()*/);
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Cost_OtherListModel> extListBo = new ExtListDataBo<Cost_OtherListModel>(list,intCount);
		return extListBo;
	}
	//导出杂项费用信息
	@Override
	public List getCostOtherList2(
			String strEnterpriseNo, String strWarehouseNo,
			String strWorkerOwner, String strQuery, Integer intRequestFlag)
			throws Exception {
		String sql="select  "+
				"t1.warehouse_no," +
                "'['|| ltrim(t1.owner_no)||']'||t3.owner_name ownerNoText, " +
                "'['|| ltrim(t1.cost_no)||']'||t2.cost_name costNoText, " +
                "t1.cost_value, " +
                "to_char(t1.cost_date,'yyyy-mm-dd')costDateText, " +
                "'['|| ltrim(t1.cost_flag)||']'||f_get_fieldtext('COST_OTHER_LIST','COST_FLAG',t1.cost_flag)costFlagText," +
				"'['|| ltrim(t1.status)||']'||f_get_fieldtext('COST_OTHER_LIST','STATUS',t1.status)statusText, " +
                "t1.source_no, " +
                "t1.check_no," +
                "t1.remark " +
           "from " +
                "cost_other_list t1, " +
                "cost_other_set t2, " +
                "bdef_defowner t3 " +
          "where 1=1   " +
            "and t1.enterprise_no=t2.enterprise_no  " +
            "and t1.cost_no=t2.cost_no  " +
            "and t1.enterprise_no=t2.enterprise_no " +
            "and t1.owner_no=t3.owner_no " +
			"and t1.warehouse_no ='"+strWarehouseNo+"' " +
			"and t1.enterprise_no= '"+strEnterpriseNo+"' "+
			"and t1.owner_no in("+strWorkerOwner+") ";

		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
		}	
		sql += "order by t1.owner_no,t1.cost_no,t1.cost_date desc ";
		List list=genDao.getListByNativeSql(sql);
		return list;
	}
	//获取用于查找的货主下拉
	@Override
	public List<ComboxBo> getOwnerNoForQuery(String strEnterpriseNo,String strWarehouseNo, String strWorkerOwner)
			throws Exception {
		String strSql="select 'ALL' as value, 'ALL' as text, 'ALL' as dropValue from wms_defbase "+
				"union "+
				"select t1.owner_no value,(select distinct a.owner_name from bdef_defowner a where a.owner_no =t1.owner_no and a.enterprise_no = t1.enterprise_no) text," +	
				"'['|| ltrim(t1.owner_no)||']'||(select distinct a.owner_name from bdef_defowner a where a.owner_no =t1.owner_no and a.enterprise_no = t1.enterprise_no) dropValue " +
				"from cost_other_list t1 where 1=1  "+
				"and t1.warehouse_no ='"+strWarehouseNo+"' " +
				"and t1.enterprise_no= '"+strEnterpriseNo+"' " +
				"and t1.owner_no in("+strWorkerOwner+") ";

		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;	
	}
	//获取费用代码的下拉
	@Override
	public List<ComboxBo> getCostNoForUI(String strEnterpriseNo,String strWarehouseNo, String strWorkerOwner,String str) throws Exception {
		String strSql="select distinct t1.cost_no value, t2.cost_name  text," +
				"'['|| ltrim(t1.cost_no)||']'||t2.cost_name dropValue " +
				"from cost_other_list t1,cost_other_set t2" +
				" where 1=1  " +
				"and t1.enterprise_no=t2.enterprise_no  " +
		        "and t1.cost_no=t2.cost_no  " +
				"and t1.enterprise_no='"+strEnterpriseNo+"' " +
				"and t1.warehouse_no ='"+strWarehouseNo+"' ";

		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}

		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	//获取费用代码的下拉
	@Override
	public List<ComboxBo> getCostNoForWindown(String strEnterpriseNo, String str) throws Exception {
		String strSql="select distinct t1.cost_no value, t1.cost_name  text," +
					"'['|| ltrim(t1.cost_no)||']'||t1.cost_name dropValue " +
					"from cost_other_set t1 " +
					" where 1=1  " +
					"and t1.enterprise_no='"+strEnterpriseNo+"' ";

		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}

		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	//获取其他费用信息列表
	@Override
	public ExtListDataBo<Cost_OtherSetModel> getCostOtherSetList(
			String strEnterpriseNo,String queryStr, PageBo pageBo)
			throws Exception {
		String sql="select t1.enterprise_no,t1.cost_no, " +
                          "t1.cost_name,t1.remark,t1.rgst_name, " +
                          "t1.rgst_date,t1.updt_name,t1.updt_date,t1.create_flag, " +
          				"'['|| ltrim(t1.cost_no)||']'||t1.cost_name costNoText " +
                    "from cost_other_set t1 " +
			       "where t1.enterprise_no= '"+strEnterpriseNo+"' ";

		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
		}
		sql += "order by t1.cost_no ";
		String strTotsql="select count(*) from (" + sql + ") " ;	
		List<Cost_OtherSetModel> list = genDao.getListByNativeSql(sql,Cost_OtherSetModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Cost_OtherSetModel> extListBo = new ExtListDataBo<Cost_OtherSetModel>(list,intCount);
		return extListBo;
	}
	//保存杂项费用清单
	@Override
	public void saveCostOtherList(String str) throws Exception {
		Cost_OtherList costOtherList=(Cost_OtherList)JSON.parseObject(str,Cost_OtherList.class);
		genDao.saveOrUpdateObj(costOtherList);	
	}
	//删除杂项费用清单
	@Override
	public MsgRes deleteCostOtherList(String str, String strCostDate)
			throws Exception {
		String deleteSql="delete from cost_other_list t1  " +
				"where to_date('"+strCostDate+"','yyyy/mm/dd') =t1.cost_date  ";
		String ws=CommUtil.covtCollectionToWhereSql(str);
		deleteSql=deleteSql+ws;
		genDao.updateBySql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}
	//保存其他费用
	@Override
	public void saveCostOtherSet(String str) throws Exception {
		Cost_OtherSet costOtherSet=(Cost_OtherSet)JSON.parseObject(str,Cost_OtherSet.class);
		genDao.saveOrUpdateObj(costOtherSet);	
		
	}
	@Override
	public String checkCostNo(String strEnterpriseNo, String strCostNo)
			throws Exception {
		String sql="select a.cost_no from cost_other_set a " +
				"where a.cost_no='"+strCostNo+
			    "' and a.enterprise_no='"+strEnterpriseNo+"'";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
	//删除其他费用
	@Override
	public MsgRes deleteCostOtherSet(String strEnterpriseNo,String strCostNo,String str) throws Exception {
		String sql1="select a.cost_no from cost_other_list a " +
					"where a.enterprise_no='"+strEnterpriseNo+"' " +
					"and a.cost_no='"+strCostNo+"' ";
		
		List list = genDao.getListByNativeSql(sql1);
		
		if(list.size()>0){
			return new MsgRes(false,"该费用编码已有消费清单，不能删除","");
		}
		String deleteSql="delete from cost_other_set a " +
				         "where a.enterprise_no='"+strEnterpriseNo+"' " +
					     "and a.cost_no='"+strCostNo+"' ";
		genDao.updateBySql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}	
}
