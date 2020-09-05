package com.sealinkin.cost.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_ExpensesListModel;
import com.sealinkin.cost.service.ICost_ExpensesListService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.DateUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Cost_ExpensesListImpl implements ICost_ExpensesListService{

	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	//获取用于查找的货主下拉
	@Override
	public List<ComboxBo> getOwnerNoForQuery(String strEnterpriseNo,String strWarehouseNo,String strWorkerOwner) {
		String strSql="select 'ALL' as value, 'ALL' as text, 'ALL' as dropValue from wms_defbase "+
					"union "+
					"select t1.owner_no value,(select distinct a.owner_name from bdef_defowner a where a.owner_no =t1.owner_no and a.enterprise_no=t1.enterprise_no) text," +	
					"'['|| ltrim(t1.owner_no)||']'||(select distinct a.owner_name from bdef_defowner a where a.owner_no =t1.owner_no and a.enterprise_no=t1.enterprise_no) dropValue " +
					"from cost_expenses_list t1 where 1=1  "+
					"and t1.warehouse_no ='"+strWarehouseNo+"' " +
					"and t1.enterprise_no ='"+strEnterpriseNo+"' " +
					"and t1.owner_no in("+strWorkerOwner+") ";
			
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	//获取UI界面的计费项目
	@Override
	public List<ComboxBo> getBillingProjectForUI(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String str)
			throws Exception {
		String strSql="select distinct a.billing_project value,a.project_name text," +
				 "'['|| ltrim(a.billing_project)||']'||a.project_name dropValue " +
				"from cost_formulaset a, " +
				"(select distinct caf.enterprise_no,caf.warehouse_no," +
				 "caf.owner_no,caf.account_no,caf.billing_project,cad.account_name " +
				 "from cost_account_formula caf,cost_account_set cad " +
				 "where caf.enterprise_no=cad.enterprise_no " +
				 "and caf.account_no=cad.account_no " +
				") e " +
			   "where 1=1  " +
				 "and a.enterprise_no = e.enterprise_no(+) "+
		         "and a.warehouse_no = e.warehouse_no(+) " +
		         "and a.owner_no = e.owner_no(+) " +
		         "and a.billing_project = e.billing_project(+) " +
				 "and a.enterprise_no='"+strEnterpriseNo+"' " +
				 "and a.warehouse_no='"+strWarehouseNo+"' " +
				 "and a.owner_no in("+strWorkerOwner+") ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
		}
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	//取消费清单信息列表
	@Override
	public ExtListDataBo<Cost_ExpensesListModel> getExpList(
			String strEnterpriseNo, String strWarehouseNo,
			String strWorkerOwner, String strQuery,
			String buildDate,String beginDate,String endDate,
			PageBo poPageBo,Integer intRequestFlag) throws Exception {
		String strSql="select a.*,  "+
				"'[' || e.account_no || ']' || e.account_name accountNoText, "+
				"'[' || a.owner_no || ']' || c.owner_name ownerNoText, "+
                "'[' || a.billing_project || ']' || b.project_name billingProjectText, "+
				" '['|| ltrim(a.billing_type)||']'||billing_type_name billingTypeText, "+
                "'[' || a.family_no || ']' || d.family_name familyNoText, "+
				"to_char(a.build_date,'yyyy-mm-dd')buildDateText, "+
				"'['|| ltrim(a.status)||']'|| f_get_fieldtext('COST_EXPENSES_LIST','STATUS',a.status) statusText "+
				"from " +
				"cost_expenses_list a," +
				"cost_formulaset b , " +
				"bdef_defowner c, " +
				"bdef_article_family_m d, " +
				"(select distinct caf.enterprise_no,caf.warehouse_no," +
				 "caf.owner_no,caf.account_no,caf.billing_project,cad.account_name " +
				 "from cost_account_formula caf,cost_account_set cad " +
				 "where caf.enterprise_no=cad.enterprise_no " +
				 "and caf.account_no=cad.account_no " +
				") e," +
				"cost_billing_type f " +
				"where " +
				"a.warehouse_no = b.warehouse_no " +
				"and a.enterprise_no = b.enterprise_no " +
				"and a.owner_no = b.owner_no " +
				"and a.billing_project = b.billing_project " +
				"and a.enterprise_no = c.enterprise_no "+
	            "and a.owner_no = c.owner_no " +
	            "and a.enterprise_no = d.enterprise_no(+) "+
	            "and a.owner_no = d.owner_no(+) " +
	            "and a.family_no = d.family_no(+) " +
	            "and a.enterprise_no = e.enterprise_no(+) "+
	            "and a.warehouse_no = e.warehouse_no(+) " +
	            "and a.owner_no = e.owner_no(+) " +
	            "and a.billing_project = e.billing_project(+) " +
	            "and a.enterprise_no = f.enterprise_no " +
	            "and a.billing_type = f.billing_type " +
			    "and a.enterprise_no = '"+strEnterpriseNo+"' " +
			    "and a.warehouse_no = '"+strWarehouseNo+"' " +
			    "and a.owner_no in("+strWorkerOwner+") " ;
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}
		if(beginDate!=null && !beginDate.equals(""))
		{
			strSql += " and to_date('"+beginDate+"','yyyy/mm/dd') <= a.build_date ";
		}

		if(endDate!=null && !endDate.equals(""))
		{
			strSql += " and to_date('"+endDate+"','yyyy/mm/dd') >= a.build_date ";
		}
		if(buildDate!=null && !buildDate.equals(""))
		{
			strSql += " and to_date('"+buildDate+"','yyyy/mm/dd') = a.build_date ";
		}
		strSql+=" order by a.build_date desc,a.family_no,a.source_no ";
		List<Cost_ExpensesListModel> list = genDao.getListByNativeSql(strSql,Cost_ExpensesListModel.class/*,poPageBo.getStart(),poPageBo.getPagesize()*/);
		Integer intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");
		ExtListDataBo<Cost_ExpensesListModel> extListBo= new ExtListDataBo<Cost_ExpensesListModel>(list, intCount);
		return extListBo;
	}
	//取导出的消费清单信息
	@Override
	public List getExpList2(String strEnterpriseNo, String strWarehouseNo,
			String strWorkerOwner, String strQuery, String buildDate,
			String beginDate, String endDate, Integer intRequestFlag)
			throws Exception {
		String strSql="select  "+
				"a.warehouse_no," +
				"'[' || a.owner_no || ']' || c.owner_name ownerNoText, "+
				"'[' || e.account_no || ']' || e.account_name accountNoText, "+
                "'[' || a.billing_project || ']' || b.project_name billingProjectText, "+
				" '['|| ltrim(a.billing_type)||']'||f.billing_type_name billingTypeText, "+
                "'[' || a.family_no || ']' || d.family_name familyNoText," +
                "a.source_no, "+
                "to_char(a.build_date,'yyyy-mm-dd')buildDateText, "+
                "a.area," +
				"a.tray," +
				"a.qty," +
				"a.volume," +
				"a.weight, " +
				"a.reserved1, " +
				"a.reserved2, " +
				"'['|| ltrim(a.status)||']'|| f_get_fieldtext('COST_EXPENSES_LIST','STATUS',a.status) statusText "+
				"from " +
				"cost_expenses_list a," +
				"cost_formulaset b , " +
				"bdef_defowner c, " +
				"bdef_article_family_m d, " +
				"(select distinct caf.enterprise_no,caf.warehouse_no," +
				 "caf.owner_no,caf.account_no,caf.billing_project,cad.account_name " +
				 "from cost_account_formula caf,cost_account_set cad " +
				 "where caf.enterprise_no=cad.enterprise_no " +
				 "and caf.account_no=cad.account_no " +
				") e, " +
				"cost_billing_type f " +
				"where " +
				"a.warehouse_no=b.warehouse_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.owner_no=b.owner_no " +
				"and a.billing_project=b.billing_project " +
				"and a.enterprise_no = c.enterprise_no "+
	            "and a.owner_no = c.owner_no " +
	            "and a.enterprise_no = d.enterprise_no(+) "+
	            "and a.owner_no = d.owner_no(+) " +
	            "and a.family_no = d.family_no(+) " +
	            "and a.enterprise_no = e.enterprise_no(+) "+
	            "and a.warehouse_no = e.warehouse_no(+) " +
	            "and a.owner_no = e.owner_no(+) " +
	            "and a.billing_project = e.billing_project(+) " +
	            "and a.enterprise_no = f.enterprise_no " +
	            "and a.billing_type = f.billing_type "+
			    "and a.enterprise_no='"+strEnterpriseNo+"' " +
			    "and a.warehouse_no='"+strWarehouseNo+"' " +
			    "and a.owner_no in("+strWorkerOwner+") " ;
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}
		if(beginDate!=null && !beginDate.equals(""))
		{
			strSql += " and to_date('"+beginDate+"','yyyy/mm/dd') <= a.build_date ";
		}

		if(endDate!=null && !endDate.equals(""))
		{
			strSql += " and to_date('"+endDate+"','yyyy/mm/dd') >= a.build_date ";
		}
		if(buildDate!=null && !buildDate.equals(""))
		{
			strSql += " and to_date('"+buildDate+"','yyyy/mm/dd') = a.build_date ";
		}
		strSql+=" order by a.build_date desc,a.family_no,a.source_no ";
		
		List list=genDao.getListByNativeSql(strSql);

		if(list.size()>0){
			List listtot = genDao.getListByNativeSql("select '总合计：'as warehouse_no,'','','','','','','', " +
					"sum(area) area," +
					"sum(tray) tray," +
					"sum(qty) qty," +
					"sum(volume) volume," +
					"sum(weight) weight, " +
					"sum(reserved1) reserved1, " +
					"sum(reserved2) reserved2 " +
					"from("+strSql+")");
			list.add(listtot.get(0));
		}
		return list;
	}
	//填充状态下拉
	@Override
	public List<ComboxBo> getStatusList(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String str)
			throws Exception {
		String strSql="select distinct a.status value,a.status text,"+
				" f_get_fieldtext('COST_EXPENSES_LIST','STATUS',a.status) dropValue "+
				" from cost_expenses_list a " +
				"where 1=1 " +
				 "and a.enterprise_no='"+strEnterpriseNo+"' " +
				 "and a.warehouse_no='"+strWarehouseNo+"' " +
			     "and a.owner_no in("+strWorkerOwner+") ";
			
			if (str != null && !str.equals("")) {
				String ws = CommUtil.covtCollectionToWhereSql(str);
				strSql = strSql + ws;
			}
			
			List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
			return (List<ComboxBo>) list;
	}
	//取来源单号下拉列表
	@Override
	public List<ComboxBo> getSourceNoList(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String strWheresql,
			String str,String buildDate,String beginDate,String endDate) throws Exception {
		String sql = "select a.source_no value,a.source_no text,a.source_no dropValue  "+ 
				"from cost_expenses_list a, "+ 
				"(select distinct caf.enterprise_no,caf.warehouse_no," +
				 "caf.owner_no,caf.account_no,caf.billing_project,cad.account_name " +
				 "from cost_account_formula caf,cost_account_set cad " +
				 "where caf.enterprise_no=cad.enterprise_no " +
				 "and caf.account_no=cad.account_no " +
				 ") e "+
			 "where a.enterprise_no = e.enterprise_no(+) "+
   	           "and a.warehouse_no = e.warehouse_no(+) " +
   	           "and a.owner_no = e.owner_no(+) " +
   	           "and a.billing_project = e.billing_project(+) " +
			   "and a.enterprise_no='" + strEnterpriseNo + "' "+
			   "and a.warehouse_No='" + strWarehouseNo + "' "+
			   "and a.owner_no in(" + strWorkerOwner + ") ";
				
		if (str != null && !str.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql + ws;
		}
		if (strWheresql != null && !strWheresql.equals("")) {
			sql = sql + "and a.source_no like '%" + strWheresql + "%' ";
		}
		if(beginDate!=null && !beginDate.equals(""))
		{
			sql += " and to_date('"+beginDate+"','yyyy/mm/dd') <= a.build_date ";
		}

		if(endDate!=null && !endDate.equals(""))
		{
			sql += " and to_date('"+endDate+"','yyyy/mm/dd') >= a.build_date ";
		}
		if(buildDate!=null && !buildDate.equals(""))
		{
			sql += " and to_date('"+buildDate+"','yyyy/mm/dd') = a.build_date ";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	//重算消费清单
	@Override
	public MsgRes tscResetExp(String strEnterpriseNo,String strWarehouseNo,
			String strOwnerNo,String billingProject,String beginDate,
			String endDate,String strWorkerNo) throws Exception {
		Date beD=DateUtil.GetDate(beginDate, "yyyy-MM-dd");
		Date enD=DateUtil.GetDate(endDate, "yyyy-MM-dd");
		if(beD.compareTo(enD)>0)
		{
			return new MsgRes(false,"开始日期不能大于结束日期！","");
		}
		while (beD.compareTo(enD)<=0) {
			List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();

			outList.add("S");

			inList.add(strEnterpriseNo);
			inList.add(strWarehouseNo);//strWareHouseNo
			inList.add(strOwnerNo);//strOwnerNo
			inList.add(billingProject);//strBilling_project
			inList.add("");//strBillingType
			inList.add(beD);//重算日期
			inList.add("1");//0:代表正常日结生成，1：代表重算                                     
			inList.add(strWorkerNo);
			resultList = genDao.execProc(inList, outList, "PKOBJ_COST.P_EXPENSES_LIST");
			System.out.println(inList);
			if(resultList.get(0).toString().indexOf("N|")!=-1)
			{
				throw new Exception(resultList.get(0).toString());
			}
			beD=DateUtil.addDateTime(beD, 1);
		}			
		return new MsgRes(true,"重算成功！","");
	}
	//删除消费清单
	@Override
	public MsgRes deleteExpensesList(String strQuery, String strBuildDate)
			throws Exception {
		String deleteSql="delete from cost_expenses_list t1  " +
				"where to_date('"+strBuildDate+"','yyyy/mm/dd') =t1.build_date  ";
		String ws=CommUtil.covtCollectionToWhereSql(strQuery);
		deleteSql=deleteSql+ws;
		genDao.updateBySql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}	
}
