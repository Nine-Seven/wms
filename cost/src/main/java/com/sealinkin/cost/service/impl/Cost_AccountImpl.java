/**
 * 模块名称：科目和计费公式的关系维护
 * 模块编码：B301
 * 创建：chensr 
 */
package com.sealinkin.cost.service.impl;

import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_AccountDModel;
import com.sealinkin.cost.model.Cost_AccountDiscountModel;
import com.sealinkin.cost.model.Cost_AccountFormulaModel;
import com.sealinkin.cost.model.Cost_AccountMModel;
import com.sealinkin.cost.model.Cost_AccountSetModel;
import com.sealinkin.cost.model.Cost_FormulasetModel;
import com.sealinkin.cost.po.Cost_AccountD;
import com.sealinkin.cost.po.Cost_AccountDiscount;
import com.sealinkin.cost.po.Cost_AccountFormula;
import com.sealinkin.cost.po.Cost_AccountM;
import com.sealinkin.cost.po.Cost_AccountSet;
import com.sealinkin.cost.service.ICost_AccountService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Cost_AccountImpl implements ICost_AccountService {
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	//获取科目代码信息列表
	@Override
	public ExtListDataBo<Cost_AccountSetModel> getCostAccountSetList(
			String strEnterpriseNo, String strQuery, PageBo pageBo)
			throws Exception {
		String sql="select a.* " +
				"from cost_account_set a " +
				"where " +
				"1=1  " +
				"and a.enterprise_no = '"+strEnterpriseNo+"' ";

		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
		}	
		sql += " order by a.account_no ";

		String strTotsql="select count(*) from (" + sql + ") " ;	
		List<Cost_AccountSetModel> list = genDao.getListByNativeSql(sql,Cost_AccountSetModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Cost_AccountSetModel> extListBo = new ExtListDataBo<Cost_AccountSetModel>(list,intCount);
		return extListBo;
	}
	//科目代码维护-判断科目代码是否唯一
	@Override
	public List<String> checkAccountNo(String strEnterpriseNo, String str)
			throws Exception {
		String strSql="select a.account_no "+
				"from cost_account_set a where 1=1  "+
				"and a.account_no='"+str+
				"' and a.enterprise_no='"+strEnterpriseNo+"' ";		
		List list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}
	//科目代码维护-保存科目代码
	@Override
	public void saveCostAccountSet(String str) throws Exception {
		Cost_AccountSet costAccountSet=(Cost_AccountSet)JSON.parseObject(str,Cost_AccountSet.class);
		genDao.saveOrUpdateObj(costAccountSet);	
	}
	//科目代码维护-删除科目代码
	@Override
	public MsgRes deleteCostAccountSet(String strEnterpriseNo,String str)
			throws Exception {
		String sql=" select a.account_no from cost_account_d a " +
				   "  where a.enterprise_no='"+strEnterpriseNo+"' " +
				   "    and a.account_no='"+str+"' ";
		
		List list = genDao.getListByNativeSql(sql);
		
		if(list.size()>0){
			return new MsgRes(false,"该科目代码已使用，不能删除","");
		}
		String deleteSql="delete from cost_account_set a  " +
				"where a.account_no='"+str+"' "+
				"and a.enterprise_no='"+strEnterpriseNo+"' ";
		genDao.updateBySql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}
	//获取科目信息
	@Override
	public ExtListDataBo<Cost_AccountDModel> getAccountList(
			String enterpriseNo,String warehouseNo, String str, PageBo pageBo,String workerOwner) throws Exception {
		String sql="select t1.*,t1.remark remark_m, " +
				" '['|| ltrim(t1.owner_no)||']'||t3.owner_name ownerNoText, " +
				"f_get_fieldtext('COST_ACCOUNT_D','ACCOUNT_TYPE',t2.account_type)accountTypeText, " +
				" f_get_fieldtext('COST_ACCOUNT_M','ACCOUNT_CYCLE',t1.account_cycle)accountCycleText, "+
				"f_get_fieldtext('COST_ACCOUNT_M','STATUS',t1.status)statusText, " +
				"case when t1.account_cycle in('3') then t1.balance_day else "+
				"f_get_fieldtext('COST_ACCOUNT_M','BALANCE_DAY',t1.balance_day) end balanceDayText," +
				"t2.account_no,t2.account_type,t4.account_name,t2.account_id,t2.other_cost1,t2.other_cost2,t2.other_cost3," +
				"t2.other_cost4,t2.other_cost5 " +
//				"to_char(t2.next_account_date,'yyyy-mm-dd')nextAccountDateText " +
				" from cost_account_m t1 " +
				" left join cost_account_d t2  " +
				" on t1.enterprise_no=t2.enterprise_no  " +
				" and t1.warehouse_no=t2.warehouse_no  " +
				" and t1.owner_no=t2.owner_no  " +
				" and t1.account_group_no=t2.account_group_no  " +
				" inner join bdef_defowner t3  " +
				" on t1.enterprise_no=t3.enterprise_no  " +
				" and t1.owner_no=t3.owner_no " +
				" left join cost_account_set t4 " +
				" on t1.enterprise_no=t4.enterprise_no  " +
				" and t2.account_no=t4.account_no " +
				"where " +
				"1=1  " +
				"and t1.warehouse_no ='"+warehouseNo+"' " +
				"and t1.enterprise_no = '"+enterpriseNo+"' " +
				"and t1.owner_no in("+workerOwner+") ";

		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}	
		sql += " order by t1.owner_no,t2.account_no ";

		String strTotsql="select count(*) from (" + sql + ") " ;	
		List<Cost_AccountDModel> list = genDao.getListByNativeSql(sql,Cost_AccountDModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Cost_AccountDModel> extListBo = new ExtListDataBo<Cost_AccountDModel>(list,intCount);
		return extListBo;
	}
	//获取科目明细信息列表
	@Override
	public ExtListDataBo<Cost_AccountDModel> getAccountDList(
			String enterpriseNo, String warehouseNo, String str, PageBo pageBo,
			String workerOwner) throws Exception {
		String sql="select t1.*,t4.account_name," +
				" '['|| ltrim(t1.account_type)||']'||f_get_fieldtext('COST_ACCOUNT_D','ACCOUNT_TYPE',t1.account_type)accountTypeText, " +
				" '['|| ltrim(t1.owner_no)||']'||t3.owner_name ownerNoText " +
				" from cost_account_d t1 " +
				" inner join bdef_defowner t3  " +
				" on t1.enterprise_no=t3.enterprise_no  " +
				" and t1.owner_no=t3.owner_no " +
				" inner join cost_account_set t4 " +
				" on t1.enterprise_no=t4.enterprise_no  " +
				" and t1.account_no=t4.account_no " +
				"where " +
				"1=1  " +
				"and t1.warehouse_no ='"+warehouseNo+"' " +
				"and t1.enterprise_no = '"+enterpriseNo+"' " +
				"and t1.owner_no in("+workerOwner+") ";

		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}	
		sql += " order by t1.owner_no,t1.account_no ";

		String strTotsql="select count(*) from (" + sql + ") " ;	
		List<Cost_AccountDModel> list = genDao.getListByNativeSql(sql,Cost_AccountDModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Cost_AccountDModel> extListBo = new ExtListDataBo<Cost_AccountDModel>(list,intCount);
		return extListBo;
	}
	//判断科目代码是否唯一
	@Override
	public List<String> accountNoCheck(String enterpriseNo,String str, String warehouseNo,String ownerNo) throws Exception {
		String strSql="select t1.account_no "+
				"from cost_account_d t1 where 1=1  "+
				"and t1.account_no='"+str+
				"' and t1.warehouse_no='"+warehouseNo+
				"' and t1.owner_no='"+ownerNo+
				"' and t1.enterprise_no='"+enterpriseNo+"' ";		
		List list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}

	//保存科目信息
	public void saveAccount(String jsonMaster,String jsonDetail) {
		Cost_AccountM account_m=(Cost_AccountM)JSON.parseObject(jsonMaster,Cost_AccountM.class);
		genDao.saveOrUpdateObj(account_m);
		if(jsonDetail!=null && !jsonDetail.equals("")){
			Cost_AccountD account_d=(Cost_AccountD)JSON.parseObject(jsonDetail,Cost_AccountD.class);
			genDao.saveOrUpdateObj(account_d);
		}
	}

	//获取优惠项目代码
	@Override
	public List<ComboxBo> getBillingProjectComboList(String enterpriseNo,String warehouseNo,String str) throws Exception {
		String strSql="	select distinct t1.billing_project value,t1.billing_project text," +
				"'['|| ltrim(t1.billing_project)||']'||t2.project_name dropValue " +
				"from cost_account_formula t1 ,cost_formulaset t2 "+
				" where 1=1  " +
				" and t1.enterprise_no =t2.enterprise_no " +
				" and t1.warehouse_no=t2.warehouse_no " +
				" and t1.owner_no=t2.owner_no " +
				" and t1.billing_project=t2.billing_project " +
				" and t1.enterprise_no='"+enterpriseNo+"' " +
				" and t1.warehouse_no='"+warehouseNo+"' " ;
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
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
				"from cost_account_m t1 where 1=1  "+
				"and t1.warehouse_no ='"+warehouseNo+"' " +
				"and t1.enterprise_no= '"+enterpriseNo+"' ";


		if(workerOwner!=null && !workerOwner.equals(""))
		{
			strSql=strSql+" and t1.owner_no in("+workerOwner+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;	
	}
	//根据货主和仓别获取科目的下拉
	@Override
	public List<ComboxBo> getAccountForUI(String enterpriseNo,String warehouseNo,String str) throws Exception {
		String strSql="select distinct t1.account_no value, t2.account_name  text," +
				"'['|| ltrim(t1.account_no)||']'||t2.account_name dropValue " +
				"from cost_account_d t1,cost_account_set t2 where 1=1  " +
				"and t1.enterprise_no=t2.enterprise_no " +
				"and t1.account_no=t2.account_name " +
				"and t1.enterprise_no='"+enterpriseNo+"' " +
				"and t1.warehouse_no ='"+warehouseNo+"' ";

		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}

		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}

	//获取尚未维护的计费项目
	@Override
	public ExtListDataBo<Cost_FormulasetModel> getFormulasetList(
			String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner, 
			String falg, String str,String strWhereSql,  PageBo pageBo) throws Exception {
		String sql="select t1.* " +
				" from cost_formulaset t1 " +
				" where t1.enterprise_no = '"+strEnterpriseNo+"' " +
				" and t1.owner_no in("+strWorkerOwner+") " +
				" and t1.billing_project not in(select t2.billing_project from cost_account_formula t2 " +
				" where t2.warehouse_no ='"+strWarehouseNo+"' " +
				" and t2.enterprise_no = '"+strEnterpriseNo+"' " +
				" and t2.owner_no in("+strWorkerOwner+") ";
		if(strWhereSql!=null && !strWhereSql.equals(""))
		{
			String ws1=CommUtil.covtCollectionToWhereSql(strWhereSql);
			sql=sql+ws1;
		}	
		sql += " )" ;
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}	
		String strTotsql ="select count(*) from (" + sql + ")";

		List<Cost_FormulasetModel> list = genDao.getListByNativeSql(sql,Cost_FormulasetModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Cost_FormulasetModel> extListBo = new ExtListDataBo<Cost_FormulasetModel>(list,intCount);
		return extListBo;
	}

	//获取科目和计费项目的关系
	@Override
	public ExtListDataBo<Cost_AccountFormulaModel> getAccountFormulaList(
			String enterpriseNo,String warehouseNo, String ownerNo,String str, PageBo pageBo) {
		String sql="select t1.*, t3.project_name, "+
				" '['|| ltrim(t4.billing_type)||']'||t4.billing_type_name billingTypeText, "+
				"'['|| ltrim(t1.account_no)||']'||t2.account_name accountName "+
				"from cost_account_formula t1,cost_account_set t2,cost_formulaset t3,cost_billing_type t4 " +
				"where 1=1 " +
				"and t2.enterprise_no=t1.enterprise_no " +
				"and t2.account_no=t1.account_no " +
				"and t3.warehouse_no=t1.warehouse_no " +
				"and t3.owner_no=t1.owner_no " +
				"and t3.enterprise_no=t1.enterprise_no " +
				"and t3.billing_project=t1.billing_project " +
				"and t4.enterprise_no=t1.enterprise_no " +
				"and t4.billing_type=t1.billing_type " +
				"and t1.enterprise_no='"+enterpriseNo+"' " +
				"and t1.warehouse_no='"+warehouseNo+"' " +
				"and t1.owner_no in("+ownerNo+") " ;


		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}		
		String strTotsql="select count(*) from ("+sql+")";

		List<Cost_AccountFormulaModel> list = genDao.getListByNativeSql(sql,Cost_AccountFormulaModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Cost_AccountFormulaModel> extListBo = new ExtListDataBo<Cost_AccountFormulaModel>(list,intCount);
		return extListBo;
	}

	//保存科目和计费项目的关系
	@Override
	public void saveAccountFormula(String str) {
		Collection<Cost_AccountFormula> accountFormula=JSONArray.toCollection(JSONArray.fromObject(str),Cost_AccountFormula.class);
		List<Cost_AccountFormula> accountFormulaGroup=(List)accountFormula;
		this.genDao.saveList(accountFormulaGroup);	
	}

	//删除科目和计费项目的关系
	@Override
	public MsgRes deleteAccountFormula(String enterpriseNo,String warehouseNo, String ownerNo,
			String accountNo, String billingProject)throws Exception  {

		String wheresql1[]=warehouseNo.split(",");
		String wheresql2[]=ownerNo.split(",");
		String wheresql3[]=accountNo.split(",");
		String wheresql4[]=billingProject.split(",");

		for(int i=0;i<wheresql1.length;i++){
			String sql=" select a.billing_project from cost_account_discount a " +
					"where a.warehouse_no='"+wheresql1[i].trim()+"' and " +
					"a.owner_no='"+wheresql2[i].trim()+"' and " +
					"a.account_no='"+wheresql3[i].trim()+"' and "+
					"a.billing_project='"+wheresql4[i].trim()+"' and " +
					"a.enterprise_no='"+enterpriseNo+"' ";
			
			List list = genDao.getListByNativeSql(sql);
			
			if(list.size()>0){
				return new MsgRes(false,wheresql4[i].trim()+"项目有优惠阶梯占用，不能移除","");
			}
			String deletesql="delete cost_account_formula a " +
					"where a.warehouse_no='"+wheresql1[i].trim()+"' and " +
					"a.owner_no='"+wheresql2[i].trim()+"' and " +
					"a.account_no='"+wheresql3[i].trim()+"' and "+
					"a.billing_project='"+wheresql4[i].trim()+"' and " +
					"a.enterprise_no='"+enterpriseNo+"' ";
			genDao.updateBySql(deletesql);
		}
		return new MsgRes(true,"","");
	}
	//获取科目优惠策咯信息列表
	@Override
	public ExtListDataBo<Cost_AccountDiscountModel> getAccountDiscountList(
			String enterpriseNo, String warehouseNo, String str, PageBo pageBo,
			String workerOwner) throws Exception {
		String sql="select t1.*, " +
				"f_get_fieldtext('COST_ACCOUNT_DISCOUNT','DISCOUNT_FLAG',t1.discount_flag)discountFlagText "+
				"from cost_account_discount t1 where 1=1 "+
				"and t1.warehouse_no ='"+warehouseNo+"' " +
				"and t1.enterprise_no = '"+enterpriseNo+"'" +
				"and t1.owner_no in("+workerOwner+") ";


		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}	

		String strTotsql="select count(*) from ("+sql+")";
		List<Cost_AccountDiscountModel> list = genDao.getListByNativeSql(sql,Cost_AccountDiscountModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Cost_AccountDiscountModel> extListBo = new ExtListDataBo<Cost_AccountDiscountModel>(list,intCount);
		return extListBo;
	}
	//保存阶梯信息
	@Override
	public void saveAccountDiscount(String str) throws Exception {
		Cost_AccountDiscount account=(Cost_AccountDiscount)JSON.parseObject(str,Cost_AccountDiscount.class);
		genDao.saveOrUpdateObj(account);
	}

	@Override
	public List<ComboxBo> getDiscountFlagList() throws Exception {
		String strSql="select t1.value value, '['|| ltrim(t1.value)||']'||t1.text text," +	
				"'['|| ltrim(t1.value)||']'||t1.text dropValue " +
				"from wms_deffieldval t1 where t1.table_name='BILL_ACCOUNT'  " +
				"and t1.colname='DISCOUNT_FLAG' ";

		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;	
	}

	@Override
	public List<Cost_AccountMModel> getAccountCycle(String enterpriseNo,
			String warehouseNo, String ownerNo, String str) throws Exception {
		String strSql="select t1.*,t1.account_cycle," +
				" to_char(t1.next_account_date,'yyyy-mm-dd')nextAccountDateText " +
				"from cost_account_m t1 " +
				"where  t1.warehouse_no ='"+warehouseNo+"' " +
				"and t1.enterprise_no = '"+enterpriseNo+"'" +
				"and t1.owner_no in("+ownerNo+") ";


		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}	

		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<Cost_AccountMModel>) list;	
	}
	//校验科目周期 
	@Override
	public List<Cost_AccountMModel> getAccountMList(
			String enterpriseNo, String warehouseNo, String str, 
			String workerOwner) throws Exception {
		String sql="select t1.enterprise_no,t1.warehouse_no,t1.owner_no, " +
				"t1.account_group_no,t1.account_cycle,t1.balance_day,t1.status, " +
				"t1.remark,t1.rgst_name,t1.rgst_date,t1.updt_name,t1.updt_date, " +
				"to_char(t1.rgst_date,'yyyymmddHH24MISS') rgstDateText " +
				"from cost_account_m t1 where 1=1 "+
				"and t1.warehouse_no ='"+warehouseNo+"' " +
				"and t1.enterprise_no = '"+enterpriseNo+"'" +
				"and t1.owner_no in("+workerOwner+") ";

		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}	

		List list = genDao.getListByNativeSql(sql);
		return (List<Cost_AccountMModel>) list;
	}

	@Override
	public String getAccountId(String enterpriseNo, String warehouseNo,
			String ownerNo, String str) throws Exception {
		String strSql="select nvl(max(to_number(t1.account_id)),'0')  " +
				"from cost_account_d t1 " +
				"where  t1.warehouse_no ='"+warehouseNo+"' " +
				"and t1.enterprise_no = '"+enterpriseNo+"'" +
				"and t1.owner_no in("+ownerNo+") ";
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}	
		List list=genDao.getListByNativeSql(strSql);
		return (String) list.get(0).toString();
	}
	@Override
	public String getMaxAccountGroupNo(String enterpriseNo, String warehouseNo,
			String ownerNo, String str) throws Exception {
		String strSql="select nvl(max(to_number(t1.account_group_no)),'0')  " +
				"from cost_account_m t1 " +
				"where  t1.warehouse_no ='"+warehouseNo+"' " +
				"and t1.enterprise_no = '"+enterpriseNo+"'" +
				"and t1.owner_no in("+ownerNo+") ";
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}	
		List list=genDao.getListByNativeSql(strSql);
		return (String) list.get(0).toString();
	}
	//获取科目组编码下拉
	@Override
	public List<ComboxBo> getAccountGroupNoCombo(String enterpriseNo,
			String warehouseNo, String workerOwner, String str)
			throws Exception {
		String strSql="select a.account_group_no value,a.account_group_no text," +	
				"a.account_group_no dropValue " +
				"from cost_account_m a where 1=1  "+
				"and a.warehouse_no ='"+warehouseNo+"' " +
				"and a.owner_no in("+workerOwner+") ";

		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}	
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;	
	}

	@Override
	public List<ComboxBo> getAccountForWind(String enterpriseNo, String str)
			throws Exception {
		String strSql="select distinct a.account_no value, a.account_name  text," +
				"'['|| ltrim(a.account_no)||']'||a.account_name dropValue " +
				"from cost_account_set a where 1=1  " +
				"and a.enterprise_no='"+enterpriseNo+"' ";
		String strSql2=" and a.account_no not in( ";
		String strSql3=" select distinct a.account_no " +
		         "from cost_account_d a where a.enterprise_no='"+enterpriseNo+"' ";

		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql3=strSql3+ws;
		}
		strSql +=strSql2+strSql3+" ) ";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}

	@Override
	public MsgRes deleteCostAccount(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,
			String strDeleteType, String str) throws Exception {
		String deleteSql="";
		if(strDeleteType.equals("1")){
			deleteSql="delete from cost_account_m a  " +
					  "where a.account_group_no='"+str+"' "+
					  "and a.enterprise_no='"+strEnterpriseNo+"' ";
		}else{
			String sql1=" select a.account_no from cost_financial a " +
					   "  where a.enterprise_no='"+strEnterpriseNo+"' " +
					   	" and a.warehouse_no='"+strWarehouseNo+"' " +
					   	" and a.owner_no='"+strOwnerNo+"' " +
					   "  and a.account_no='"+str+"' ";
			
			List list = genDao.getListByNativeSql(sql1);
			
			if(list.size()>0){
				return new MsgRes(false,"该科目已产生账单，不能删除","");
			}
			String sql2=" select a.account_no from cost_account_formula a " +
					   "  where a.enterprise_no='"+strEnterpriseNo+"' " +
					   	" and a.warehouse_no='"+strWarehouseNo+"' " +
					   	" and a.owner_no='"+strOwnerNo+"' " +
					   "  and a.account_no='"+str+"' ";
			
			List list2 = genDao.getListByNativeSql(sql2);
			
			if(list2.size()>0){
				return new MsgRes(false,"该科目已包含项目，不能删除","");
			}
			
			String sql3=" select a.account_no from cost_account_discount a " +
					   "  where a.enterprise_no='"+strEnterpriseNo+"' " +
						" and a.warehouse_no='"+strWarehouseNo+"' " +
					   	" and a.owner_no='"+strOwnerNo+"' " +
						" and a.account_no='"+str+"' ";
			
			List list3 = genDao.getListByNativeSql(sql3);
			
			if(list3.size()>0){
				return new MsgRes(false,"该科目代码已维护优惠策略，不能删除","");
			}
		    deleteSql="delete from cost_account_d a  " +
					"where a.account_no='"+str+"' "+
					"and a.enterprise_no='"+strEnterpriseNo+"' ";
		}
		genDao.updateBySql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}

}
