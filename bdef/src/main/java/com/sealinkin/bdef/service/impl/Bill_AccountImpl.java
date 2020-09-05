/**
 * 模块名称：科目和计费公式的关系维护
 * 模块编码：B301
 * 创建：chensr 
 */
package com.sealinkin.bdef.service.impl;

import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.service.IBill_AccountService;
import com.sealinkin.bset.model.Bill_Account_DModel;
import com.sealinkin.bset.model.Bill_Account_MModel;
import com.sealinkin.bset.model.Bill_Account_Model;
import com.sealinkin.bset.model.Bill_FormulasetModel;
import com.sealinkin.bset.po.Bill_Account;
import com.sealinkin.bset.po.Bill_Account_D;
import com.sealinkin.bset.po.Bill_Account_M;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Bill_AccountImpl implements IBill_AccountService {
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	//获取科目信息
	@Override
	public ExtListDataBo<Bill_Account_MModel> getAccountList(
			String enterpriseNo,String warehouseNo, String str, PageBo pageBo,String workerOwner) throws Exception {
		String sql="select t1.*, " +
				"f_get_fieldtext('BILL_ACCOUNT_M','ACCOUNT_TYPE',t1.account_type)accountTypeText, "+
				"f_get_fieldtext('BILL_ACCOUNT_M','DISCOUNT_FLAG',t1.discount_flag)discountFlagText "+
				"from bill_account_m t1 where 1=1 "+
				"and t1.warehouse_no ='"+warehouseNo+"' " +
				"and t1.enterprise_no = '"+enterpriseNo+"' ";
		String strTotsql = "select count(1) from bill_account_m t1 where 1=1 "+
				"and t1.warehouse_no ='"+warehouseNo+"' "+
				"and t1.enterprise_no = '"+enterpriseNo+"' ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
			strTotsql=strTotsql+ws;
		}	
		
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			sql=sql+" and t1.owner_no in("+workerOwner+") ";
			strTotsql=strTotsql+" and t1.owner_no in("+workerOwner+") ";
		}else
		{
			sql=sql+" and 1=2";
			strTotsql=strTotsql+" and 1=2";
		}
		
		List<Bill_Account_MModel> list = genDao.getListByNativeSql(sql,Bill_Account_MModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Bill_Account_MModel> extListBo = new ExtListDataBo<Bill_Account_MModel>(list,intCount);
		return extListBo;
	}

	//判断科目代码是否唯一
	@Override
	public List<String> accountNoCheck(String enterpriseNo,String str, String warehouseNo,String ownerNo) throws Exception {
		String strSql="select t1.account_no "+
				"from bill_account_m t1 where 1=1  "+
				"and t1.account_no='"+str+
				"' and t1.warehouse_no='"+warehouseNo+
				"' and t1.owner_no='"+ownerNo+
				"' and t1.enterprise_no='"+enterpriseNo+"' ";		
		List list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}

	//保存科目信息
	public void saveAccount_m(String str) {
		Bill_Account_M account_m=(Bill_Account_M)JSON.parseObject(str,Bill_Account_M.class);
		genDao.saveOrUpdateObj(account_m);
		
	}

	//获取优惠项目代码
	@Override
	public List<ComboxBo> getDiscountAccountNoComboList(String enterpriseNo,String warehouseNo) throws Exception {
		String strSql="	select distinct t1.billing_project value,t1.billing_project text," +
				 "'['|| ltrim(t1.billing_project)||']'||t2.project_name dropValue " +
				 "from bill_account_d t1 ,bill_formulaset t2 "+
				 " where 1=1  " +
				 " and t1.enterprise_no =t2.enterprise_no " +
				 " and t1.warehouse_no=t2.warehouse_no " +
				 " and t1.billing_project=t2.billing_project " +
				 " and t1.enterprise_no='"+enterpriseNo+"' " +
				 " and t1.warehouse_no='"+warehouseNo+"' " ;
			    	
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}

	//获取用于查找的货主下拉
	@Override
	public List<ComboxBo> getOwnerNoForQuery(String enterpriseNo,String warehouseNo,String workerOwner)
			throws Exception {
		String strSql="select 'ALL' as value, 'ALL' as text, 'ALL' as dropValue from wms_defbase "+
				"union "+
				"select t1.owner_no value,(select distinct a.owner_name from bdef_defowner a where a.owner_no =t1.owner_no and a.enterprise_no = t1.enterprise_no) text," +	
				"'['|| ltrim(t1.owner_no)||']'||(select distinct a.owner_name from bdef_defowner a where a.owner_no =t1.owner_no and a.enterprise_no = t1.enterprise_no) dropValue " +
				"from bill_account_m t1 where 1=1  "+
				"and t1.warehouse_no ='"+warehouseNo+"' " +
				"and t1.enterprise_no= '"+enterpriseNo+"' ";
				
				
				if(workerOwner!=null && !workerOwner.equals(""))
				{
					strSql=strSql+" and t1.owner_no in("+workerOwner+") ";
				}else
				{
					strSql=strSql+" and 1=2";
				}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;	
	}
	//根据货主和仓别获取科目的下拉
	@Override
	public List<ComboxBo> getAccountForUI(String enterpriseNo,String warehouseNo,String str) throws Exception {
		String strSql="select distinct t1.account_no value, t1.account_name  text," +
				 "'['|| ltrim(t1.account_no)||']'||t1.account_name dropValue " +
				"from bill_account_m t1 where 1=1  " +
				"and t1.enterprise_no='"+enterpriseNo+"' " +
				"and t1.warehouse_no ='"+warehouseNo+"' ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}

	//根据货主和苍别，获取尚未维护的计费项目
	@Override
	public ExtListDataBo<Bill_FormulasetModel> getFormulasetList(String enterpriseNo,String str,
			PageBo pageBo, String accountNo) throws Exception {
		
		if(str!=null && !str.equals("") && accountNo!=null && !accountNo.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			
			String sql="select t1.billing_project , t1.project_Name " +
					"from bill_formulaset t1 where 1=1 "+
					"and t1.billing_project not in " +
					"(select billing_project from bill_account_d "+
					" where 1=1 and account_no ='"+accountNo+"' " +
					"and enterprise_no="+enterpriseNo+") " +
					"and t1.enterprise_no='"+enterpriseNo+"' ";
			
			String strTotsql = "select count(1) from bill_formulaset t1 where 1=1 "+
					"and t1.billing_project not in (select billing_project from bill_account_d "+
					" where 1=1 and account_no ='"+accountNo+"' " +
					"and enterprise_no="+enterpriseNo+") " +
					"and t1.enterprise_no='"+enterpriseNo+"' ";
			
			sql=sql+ws;
			strTotsql=strTotsql+ws;
			

			List<Bill_FormulasetModel> list = genDao.getListByNativeSql(sql,Bill_FormulasetModel.class,pageBo.getStart(), pageBo.getPagesize());
			Integer intCount = genDao.getCountByNativeSql(strTotsql);
			ExtListDataBo<Bill_FormulasetModel> extListBo = new ExtListDataBo<Bill_FormulasetModel>(list,intCount);
			return extListBo;
		}			
		return null;
	}

	//根据货主和仓别，获取科目和计费项目的关系
	@Override
	public ExtListDataBo<Bill_Account_DModel> getAccount_DListWithCondition(
			String enterpriseNo,String str, PageBo pageBo) {
		String sql="select t1.*, " +
				"(select a.project_name from bill_formulaset a where a.billing_project = t1.billing_project and a.warehouse_no = t1.warehouse_no and a.owner_no= t1.owner_no and a.enterprise_no= t1.enterprise_no) projectName, "+
				"'['|| ltrim(t1.account_no)||']'||(select b.account_name from bill_account_m b where b.account_no = t1.account_no and b.warehouse_no=t1.warehouse_no and b.owner_no=t1.owner_no and b.enterprise_no=t1.enterprise_no) accountName "+
				"from bill_account_d t1 where 1=1 " +
				"and t1.enterprise_no='"+enterpriseNo+"' ";
		String strTotsql = "select count(1) from bill_account_d t1 where 1=1 " +
				           "and t1.enterprise_no='"+enterpriseNo+"' ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
			strTotsql=strTotsql+ws;
		}		
		List<Bill_Account_DModel> list = genDao.getListByNativeSql(sql,Bill_Account_DModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Bill_Account_DModel> extListBo = new ExtListDataBo<Bill_Account_DModel>(list,intCount);
		return extListBo;
	}

	//保存科目和计费项目的关系
	@Override
	public void saveAccount_D(String str) {
		Collection<Bill_Account_D> account_D=JSONArray.toCollection(JSONArray.fromObject(str),Bill_Account_D.class);
		List<Bill_Account_D> account_DGroup=(List)account_D;
		this.genDao.saveList(account_DGroup);	
	}

	//删除科目和计费项目的关系
	@Override
	public void deleteAccount_D(String enterpriseNo,String warehouseNo, String ownerNo,
			String accountNo, String billingProject) {
		
		String wheresql1[]=warehouseNo.split(",");
		String wheresql2[]=ownerNo.split(",");
		String wheresql3[]=accountNo.split(",");
		String wheresql4[]=billingProject.split(",");
		
		for(int i=0;i<wheresql1.length;i++){
			String sql="delete bill_account_d a " +
			"where a.warehouse_no='"+wheresql1[i].trim()+"' and " +
			"a.owner_no='"+wheresql2[i].trim()+"' and " +
			"a.account_no='"+wheresql3[i].trim()+"' and "+
			"a.billing_project='"+wheresql4[i].trim()+"' and " +
			"a.enterprise_no='"+enterpriseNo+"' ";
			genDao.updateBySql(sql);
		}		
	}
	//获取科目阶梯信息列表
	@Override
	public ExtListDataBo<Bill_Account_Model> getBillAccountList(
			String enterpriseNo, String warehouseNo, String str, PageBo pageBo,
			String workerOwner) throws Exception {
		String sql="select t1.*, " +
				"f_get_fieldtext('BILL_ACCOUNT','DISCOUNT_FLAG',t1.discount_flag)discountFlagText "+
				"from bill_account t1 where 1=1 "+
				"and t1.warehouse_no ='"+warehouseNo+"' " +
				"and t1.enterprise_no = '"+enterpriseNo+"' ";
		String strTotsql = "select count(1) from bill_account t1 where 1=1 "+
				"and t1.warehouse_no ='"+warehouseNo+"' "+
				"and t1.enterprise_no = '"+enterpriseNo+"' ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
			strTotsql=strTotsql+ws;
		}	
		
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			sql=sql+" and t1.owner_no in("+workerOwner+") ";
			strTotsql=strTotsql+" and t1.owner_no in("+workerOwner+") ";
		}else
		{
			sql=sql+" and 1=2";
			strTotsql=strTotsql+" and 1=2";
		}
		
		List<Bill_Account_Model> list = genDao.getListByNativeSql(sql,Bill_Account_Model.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Bill_Account_Model> extListBo = new ExtListDataBo<Bill_Account_Model>(list,intCount);
		return extListBo;
	}
	//保存阶梯信息
	@Override
	public void saveAccount(String str) throws Exception {
		Bill_Account account=(Bill_Account)JSON.parseObject(str,Bill_Account.class);
		genDao.saveOrUpdateObj(account);
	}

	@Override
	public List<ComboxBo> getDiscountFlagList() throws Exception {
		String strSql="select t1.value value, '['|| ltrim(t1.value)||']'||t1.text text," +	
				"'['|| ltrim(t1.value)||']'||t1.text dropValue " +
				"from wms_deffieldval t1 where t1.table_name='BILL_ACCOUNT'  " +
				"and t1.colname='DISCOUNT_FLAG' ";
				
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;	
	}
}
