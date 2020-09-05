/**
 * 模块名称：手工录入消费清单
 * 模块编码：B905
 * 创建：hcx 20160707
 */
package com.sealinkin.cost.service.impl;


import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_HandleListModel;
import com.sealinkin.cost.po.Cost_HandleList;
import com.sealinkin.cost.service.ICost_HandleListService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Cost_HandleListImpl implements ICost_HandleListService {
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	//取流水号
	@Override
	public MsgRes getSerialNo(String enterpriseNo, String warehouseNo)
			throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		inList.add(enterpriseNo);
		inList.add(warehouseNo);
		inList.add("F");
		outList.add("S");
		outList.add("S");
		resultList=genDao.execProc(inList, outList, "PKLG_WMS_BASE.p_getsheetno");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception();
		}
		return new MsgRes(true, "", resultList.get(0).toString());
	}	
	//保存手工录入消费清单
	@Override
	public void saveCostHandleList(String str) throws Exception {
		Cost_HandleList costHandleList=(Cost_HandleList)JSON.parseObject(str,Cost_HandleList.class);
		genDao.saveOrUpdateObj(costHandleList);
	}
	//获取手工录入消费清单信息
	@Override
	public ExtListDataBo<Cost_HandleListModel> getCostHandleList(
			String enterpriseNo, String warehouseNo, String str, PageBo pageBo,
			String workerOwner) throws Exception {
		String sql="select  a.*, "+
		            "b.billing_type, "+
		            "b.unit_price, " +
		            "to_char(a.amount_date,'yyyy-mm-dd')amountDateText, "+
		            "f_get_fieldtext('COST_OTHER_LIST','COST_FLAG',a.cost_flag)costFlagText, "+
		            "'['|| ltrim(a.owner_no)||']'||f.owner_name ownerNoText,   "+
		            "'[' || e.account_no || ']' || e.account_name accountNoText, "+
		            "'['|| ltrim(b.billing_type)||']'||c.billing_type_name billingTypeText,   "+
		            "'['|| ltrim(a.billing_project)||']'||b.project_name projectText,   "+
					"'['|| ltrim(a.status)||']'|| f_get_fieldtext('COST_EXPENSES_LIST','STATUS',a.status) statusText "+

		        "from "+
		            "cost_handle_list a , "+
		            "cost_formulaset b, "+
		            "cost_billing_type c,  "+ 
		            "(select distinct caf.enterprise_no,caf.warehouse_no," +
					 "caf.owner_no,caf.account_no,caf.billing_project,cad.account_name " +
					 "from cost_account_formula caf,cost_account_set cad " +
					 "where caf.enterprise_no=cad.enterprise_no " +
					 "and caf.account_no=cad.account_no " +
					") e, " +
					"bdef_defowner f "+ 
		        "where "+
		            "a.enterprise_no=b.enterprise_no  "+ 
		            "and a.warehouse_no=b.warehouse_no   "+
		            "and a.owner_no=b.owner_no   "+
		            "and a.billing_project=b.billing_project  "+ 
		            "and a.enterprise_no=c.enterprise_no "+
		            "and b.billing_type=c.billing_type "+
		            "and a.enterprise_no = e.enterprise_no(+) "+
		            "and a.warehouse_no = e.warehouse_no(+) " +
		            "and a.owner_no = e.owner_no(+) " +
		            "and a.billing_project = e.billing_project(+) " +
		            "and a.enterprise_no = f.enterprise_no " +
		            "and a.owner_no = f.owner_no  " +
					"and a.warehouse_no ='"+warehouseNo+"' " +
					"and a.enterprise_no = '"+enterpriseNo+"' ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}	
		
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			sql=sql+" and a.owner_no in("+workerOwner+") ";
		}else
		{
			sql=sql+" and 1=2";
		}
		String strTotsql = "select count(*) from (" + sql + ")";

		List<Cost_HandleListModel> list = genDao.getListByNativeSql(sql,Cost_HandleListModel.class/*,pageBo.getStart(), pageBo.getPagesize()*/);
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Cost_HandleListModel> extListBo = new ExtListDataBo<Cost_HandleListModel>(list,intCount);
		return extListBo;
	}
	//获取手工录入消费清单信息
	@Override
	public List getCostHandleList2(
			String enterpriseNo, String warehouseNo, String str,
			String workerOwner) throws Exception {
		String sql="select  "+
					"a.warehouse_no," +
		            "'['|| ltrim(a.owner_no)||']'||f.owner_name ownerNoText,   "+
                    "a.serial_no, "+
		            "'[' || e.account_no || ']' || e.account_name accountNoText, "+
		            "'['|| ltrim(a.billing_project)||']'||b.project_name projectText,   "+
		            "to_char(a.amount_date,'yyyy-mm-dd')amountDateText, "+
		            "b.unit_price, " +
		            "a.value, "+
		            "a.source_no, "+
		            "f_get_fieldtext('COST_OTHER_LIST','COST_FLAG',a.cost_flag)costFlagText, "+
		            "'['|| ltrim(a.status)||']'|| f_get_fieldtext('COST_EXPENSES_LIST','STATUS',a.status) statusText "+

		        "from "+
		            "cost_handle_list a , "+
		            "cost_formulaset b, "+
		            "cost_billing_type c,  "+ 
		            "(select distinct caf.enterprise_no,caf.warehouse_no," +
					 "caf.owner_no,caf.account_no,caf.billing_project,cad.account_name " +
					 "from cost_account_formula caf,cost_account_set cad " +
					 "where caf.enterprise_no=cad.enterprise_no " +
					 "and caf.account_no=cad.account_no " +
					") e, " +
					"bdef_defowner f "+ 
		        "where "+
		            "a.enterprise_no=b.enterprise_no  "+ 
		            "and a.warehouse_no=b.warehouse_no   "+
		            "and a.owner_no=b.owner_no   "+
		            "and a.billing_project=b.billing_project  "+ 
		            "and a.enterprise_no=c.enterprise_no "+
		            "and b.billing_type=c.billing_type "+
		            "and a.enterprise_no = e.enterprise_no(+) "+
		            "and a.warehouse_no = e.warehouse_no(+) " +
		            "and a.owner_no = e.owner_no(+) " +
		            "and a.billing_project = e.billing_project(+) " +
		            "and a.enterprise_no = f.enterprise_no " +
		            "and a.owner_no = f.owner_no  " +
					"and a.warehouse_no ='"+warehouseNo+"' " +
					"and a.enterprise_no = '"+enterpriseNo+"' ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}	
		
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			sql=sql+" and a.owner_no in("+workerOwner+") ";
		}else
		{
			sql=sql+" and 1=2";
		}
		List list = genDao.getListByNativeSql(sql);
		if(list.size()>0){
			List listtot = genDao.getListByNativeSql("select '总合计：'as warehouse_no,'','','','','',''," +
					"sum(value) value " +
					"from("+sql+")");
			list.add(listtot.get(0));
		}
		return list;
	}
	//获取新增窗口货主下拉
	@Override
	public List<ComboxBo> getOwnerNoForWindow(String enterpriseNo,
			String warehouseNo, String workerOwner) throws Exception {
		String strSql="select distinct t1.owner_no value,(select distinct a.owner_name from bdef_defowner a where a.owner_no =t1.owner_no and a.enterprise_no=t1.enterprise_no) text," +	
				"'['|| ltrim(t1.owner_no)||']'||(select distinct a.owner_name from bdef_defowner a where a.owner_no =t1.owner_no and a.enterprise_no=t1.enterprise_no) dropValue " +
				"from cost_formulaset t1 where 1=1  "+
				"and t1.warehouse_no ='"+warehouseNo+"' " +
				"and t1.enterprise_no ='"+enterpriseNo+"' ";
			
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			strSql=strSql+" and t1.owner_no in("+workerOwner+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		strSql +=" order by value "	;
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	//获取新增窗口计费项目下拉
	@Override
	public List<ComboxBo> getBillingProjectForWindow(String enterpriseNo,
			String warehouseNo, String workerOwner, String str)
			throws Exception {
		String strSql="select distinct t1.billing_project value,t1.project_name text," +
				 "'['|| ltrim(t1.billing_project)||']'||t1.project_name dropValue " +
				"from cost_formulaset t1 where 1=1  " +
				"and t1.billing_flag='2' " +
				"and t1.billing_unit='0' " +
				"and t1.enterprise_no='"+enterpriseNo+"' " +
				"and t1.warehouse_no='"+warehouseNo+"' " +
				"and t1.owner_no in("+workerOwner+") ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	//根据计费项目获取默认单价和费用标识
	@Override
	public List<Cost_HandleListModel> getUnitPriceAndcostFlag(
			String enterpriseNo, String warehouseNo, String workerOwner,
			String str) throws Exception {
		String strSql="select a.cost_flag, a.unit_price " +
				"from cost_formulaset a " +
				"where a.enterprise_no='"+enterpriseNo+"' " +
				 " and a.warehouse_no ='"+warehouseNo+"' " +
				 " and a.owner_no in("+workerOwner+") " ;
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}
		List<Cost_HandleListModel> list=genDao.getListByNativeSql(strSql,Cost_HandleListModel.class,0,100);
		return (List<Cost_HandleListModel>)list;
	}

	@Override
	public MsgRes deleteHandleList(String strEnterpriseNo,
			String strWarehouseNo, String strOwnerNo, String strSerialNo)
			throws Exception {

	    String deleteSql="delete from cost_handle_list  a  " +
	    		  "  where a.enterprise_no='"+strEnterpriseNo+"' " +
					" and a.warehouse_no='"+strWarehouseNo+"' " +
					" and a.owner_no='"+strOwnerNo+"' " +
				    " and a.serial_no='"+strSerialNo+"'  ";
	
		genDao.updateBySql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}

	@Override
	public List<String> billingProjectCheck(String strEnterpriseNo,
			String strWarehouseNo, String strOwnerNo, String strBillingProject,String strAmountDate)
			throws Exception {
		String strSql="select a.billing_project "+
				"from cost_handle_list a where 1=1  "+
				"and a.warehouse_no='"+strWarehouseNo+"' "+
				"and a.owner_no='"+strOwnerNo+"' "+
				"and a.enterprise_no='"+strEnterpriseNo+"' " +
				"and a.billing_project='"+strBillingProject+"' " +
				"and to_date('"+strAmountDate+"','yyyy/mm/dd') = a.amount_date";		
		List list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}
	//获取UI界面的查询条件下拉
	@Override
	public List<ComboxBo> getSelectForUI(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String strSelectFlag,
			String strQuery, String strWheresql) throws Exception {
		String strSql="";
		if(strSelectFlag != null && !strSelectFlag.equals("")){
			if(strSelectFlag.endsWith("1")){//货主下拉
				strSql="select 'ALL' as value, 'ALL' as text, 'ALL' as dropValue from wms_defbase "+
				       "union "+
				       "select distinct a.owner_no value,b.owner_name text," +	
						"'['|| ltrim(a.owner_no)||']'||b.owner_name dropValue " +
						"from cost_handle_list a,bdef_defowner b " +
						"where 1=1 " +
						"and a.enterprise_no=b.enterprise_no " +
						"and a.owner_no=b.owner_no ";
			}else if(strSelectFlag.endsWith("2")){//费用标识下拉
				strSql="select distinct a.cost_flag value,f_get_fieldtext('COST_OTHER_LIST','COST_FLAG',a.cost_flag) text," +
						"'['|| ltrim(a.cost_flag)||']'||f_get_fieldtext('COST_OTHER_LIST','COST_FLAG',a.cost_flag) dropValue " +
						" from cost_handle_list a "+
						" where 1=1 ";
			}else if(strSelectFlag.endsWith("3")){//状态下拉
				strSql="select distinct a.status value,f_get_fieldtext('COST_EXPENSES_LIST','STATUS',a.cost_flag) text," +
						"'['|| ltrim(a.status)||']'||f_get_fieldtext('COST_EXPENSES_LIST','STATUS',a.status) dropValue " +
						" from cost_handle_list a "+
						" where 1=1 ";
			}else if(strSelectFlag.endsWith("4")){//计费项目下拉
				strSql="select distinct a.billing_project value,b.project_name text," +
						"'['|| ltrim(a.billing_project)||']'||b.project_name dropValue " +
						" from cost_handle_list a ,cost_formulaset b "+
						" where 1=1" +
						" and a.enterprise_no=b.enterprise_no "+
						" and a.warehouse_no=b.warehouse_no "+
						" and a.owner_no=b.owner_no "+
						" and a.billing_project=b.billing_project ";
			}else if(strSelectFlag.endsWith("6")){//来源单号下拉
				 strSql="select distinct a.source_no value,source_no text," +
						"source_no dropValue " +
						" from cost_handle_list a "+
						" where 1=1 ";
			}
		}
		strSql += "and a.warehouse_no ='"+strWarehouseNo+"' " +
				  "and a.enterprise_no = '"+strEnterpriseNo+"' ";
		if (strWheresql != null && !strWheresql.equals("")) {
			strSql = strSql + "and a.source_no like '%" + strWheresql + "%' ";
		}
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+ws;
		}
		if(strWorkerOwner!=null && !strWorkerOwner.equals(""))
		{
			strSql=strSql+" and a.owner_no in("+strWorkerOwner+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}	
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
}
