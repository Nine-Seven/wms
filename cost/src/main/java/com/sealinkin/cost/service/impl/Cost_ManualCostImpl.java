package com.sealinkin.cost.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_CostListModel;
import com.sealinkin.cost.model.Cost_ExpensesListModel;
import com.sealinkin.cost.service.ICost_ManualCostService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Cost_ManualCostImpl implements ICost_ManualCostService{

	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	//获取UI界面的科目
	@Override
	public List<ComboxBo> getAccountNoForUI(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String str)
			throws Exception {
		String strSql="select distinct a.account_no value,b.account_name text," +
				 "'['|| ltrim(a.account_no)||']'||b.account_name dropValue " +
				"from cost_account_d a,cost_account_set b " +
				"where 1=1  " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.account_no=b.account_no " +
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
			String strWorkerOwner, String strQuery, String buildDate,
			String beginDate, String endDate, PageBo poPageBo,
			Integer intRequestFlag) throws Exception {
		String strSql="select a.*,  "+
				"'[' || e.account_no || ']' || e.account_name accountNoText, "+
				"'[' || a.owner_no || ']' || c.owner_name ownerNoText, "+
                "'[' || a.billing_project || ']' || b.project_name billingProjectText, "+
				" '['|| ltrim(a.billing_type)||']'||f.billing_type_name billingTypeText, "+
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
				") e, " +
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
				"and a.billing_type = f.billing_type "+
			    "and a.enterprise_no = '"+strEnterpriseNo+"' " +
			    "and a.warehouse_no = '"+strWarehouseNo+"' " +
			    "and a.owner_no in("+strWorkerOwner+") " +
			    "and a.status='10' " ;
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
				" '['|| ltrim(a.billing_type)||']'||f_get_fieldtext('COST_FORMULASET','BILLING_TYPE',a.billing_type)billingTypeText, "+
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
				") e " +
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
			    "and a.enterprise_no='"+strEnterpriseNo+"' " +
			    "and a.warehouse_no='"+strWarehouseNo+"' " +
			    "and a.owner_no in("+strWorkerOwner+") " +
			    "and a.status='10' " ;
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
	//手工生成费用
	@Override
	public MsgRes saveManualCost(String strEnterpriseNo,
			String strWarehouseNo, String str,String strJoin) throws Exception {
		List<Cost_ExpensesListModel> str1=JSON.parseArray(str, Cost_ExpensesListModel.class);
		List<Cost_CostListModel> str2=JSON.parseArray(strJoin, Cost_CostListModel.class);

		for (int i=0; i<str1.size();i++) {
			String sql="update cost_expenses_list t1 set t1.status='11' " +
					   "where t1.warehouse_no ='"+strWarehouseNo+"' " +
			             "and t1.enterprise_no= '"+strEnterpriseNo+"' " +
			             "and t1.owner_no='"+str1.get(i).getOwnerNo()+"' " +
			             "and t1.billing_project='"+str1.get(i).getBillingProject()+"' " +
			             "and t1.build_date=to_date('"+str1.get(i).getBuildDateText()+"','yyyy/mm/dd') " +
			             "and t1.source_no='"+str1.get(i).getSourceNo()+"' " +
			             "and t1.status='10' ";
			this.genDao.updateBySql(sql);
		}
		
		List  inList=new ArrayList();
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(str2.get(0).getOwnerNo());//strOwnerNo
		inList.add(str2.get(0).getBuildDate());//dtDate          
		inList.add(str2.get(0).getBeginDate());//dtEnd           
		inList.add(str2.get(0).getEndDate());//dtEnd           
		inList.add(str2.get(0).getBillingProject());//strBillingProject 
		inList.add(str2.get(0).getBillingType());//strBillingType 
		inList.add(str2.get(0).getFlag());//strFlag         
		inList.add(str2.get(0).getDiscountFlag());//strDiscountFlag 
		inList.add(str2.get(0).getRgstName());//strWorkerNo  
//		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKOBJ_COST.P_COST_DETAILS_BYHAND");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"费用生成成功！","");
	}
	
}
