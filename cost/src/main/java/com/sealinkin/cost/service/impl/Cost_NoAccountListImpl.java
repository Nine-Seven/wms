package com.sealinkin.cost.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_CostListModel;
import com.sealinkin.cost.service.ICost_NoAccountListService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.DateUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Cost_NoAccountListImpl implements ICost_NoAccountListService{

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
					"from cost_cost_list t1 where 1=1  "+
					"and t1.warehouse_no ='"+strWarehouseNo+"' " +
					"and t1.enterprise_no ='"+strEnterpriseNo+"' " +
					"and t1.owner_no in("+strWorkerOwner+") " +
					"and t1.status='10' ";
			
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	//获取UI界面的计费项目
	@Override
	public List<ComboxBo> getBillingProjectForUI(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String str)
			throws Exception {
		String strSql="select distinct a.billing_project value,b.project_name text," +
				 "'['|| ltrim(a.billing_project)||']'||b.project_name dropValue " +
				"from cost_cost_list a ,cost_formulaset b, " +
				"(select distinct caf.enterprise_no,caf.warehouse_no," +
				 "caf.owner_no,caf.account_no,caf.billing_project,cad.account_name " +
				 "from cost_account_formula caf,cost_account_set cad " +
				 "where caf.enterprise_no=cad.enterprise_no " +
				 "and caf.account_no=cad.account_no " +
				") e, " +
				"cost_financial f " +  
				"where 1=1  " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.warehouse_no=b.warehouse_no " +
				"and a.owner_no=b.owner_no " +
				"and a.billing_project=b.billing_project " +
				"and a.enterprise_no = e.enterprise_no(+) "+
		        "and a.warehouse_no = e.warehouse_no(+) " +
		        "and a.owner_no = e.owner_no(+) " +
		        "and a.billing_project = e.billing_project(+) " +
		        "and a.enterprise_no = f.enterprise_no(+) " +
		        "and a.warehouse_no = f.warehouse_no(+) " +
		        "and a.owner_no = f.owner_no(+) " +
		        "and a.check_no = f.check_no(+) " +
		        "and a.account_no = f.account_no(+) " +
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
	//取未出账清单信息列表
	@Override
	public ExtListDataBo<Cost_CostListModel> getNoAccountList(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String strQuery,String strMenuType,
			PageBo poPageBo, Integer intRequestFlag) throws Exception {
		String strSql="select a.*,b.unit_price, "+
				      "'[' || e.account_no || ']' || e.account_name accountNoText, "+
                      "'[' || a.billing_project || ']' || b.project_name billingProjectText, "+
      				  " '['|| ltrim(a.cost_flag)||']'||f_get_fieldtext('COST_OTHER_LIST','COST_FLAG',a.cost_flag)costFlagText, " +
                      "'[' || a.owner_no || ']' || c.owner_name ownerNoText, "+
                      "to_char(a.build_date,'yyyy-mm-dd')buildDateText, "+
                      "to_char(a.begin_date,'yyyy-mm-dd')beginDateText, "+
                      "to_char(a.end_date,'yyyy-mm-dd')endDateText, "+
                      "case when b.billing_unit ='1' then a.qty else 0 end days, "+
                      "case when b.billing_unit ='2' then a.qty else 0 end area, "+  
                      "case when b.billing_unit ='3' then a.qty else 0 end tray, "+ 
                      "case when b.billing_unit in('0','4') or b.billing_unit is null then a.qty else 0 end qty2, "+
                      "case when b.billing_unit ='5' then a.qty else 0 end volume, "+
                      "case when b.billing_unit ='6' then a.qty else 0 end weigth, "+
                      "case when b.billing_unit in('7','8') then a.qty else 0 end costPrice, "+
                      "case when b.billing_unit ='9' then a.qty else 0 end cell, "+
                      "b.other_cost1,b.other_cost2,b.other_cost3,b.other_cost4,b.other_cost5, "+
                      "(a.amount+b.other_cost1+b.other_cost2+b.other_cost3+b.other_cost4+b.other_cost5-a.favourable_amount) as total "+     
                  "from cost_cost_list a, cost_formulaset b, bdef_defowner c , " +
				"(select distinct caf.enterprise_no,caf.warehouse_no," +
				 "caf.owner_no,caf.account_no,caf.billing_project,cad.account_name " +
				 "from cost_account_formula caf,cost_account_set cad " +
				 "where caf.enterprise_no=cad.enterprise_no " +
				 "and caf.account_no=cad.account_no " +
				 ") e, "+
				 "cost_financial f  "+
                 "where a.warehouse_no = b.warehouse_no "+
                   "and a.enterprise_no = b.enterprise_no "+
                   "and a.owner_no = b.owner_no "+
                   "and a.billing_project = b.billing_project "+
                   "and a.enterprise_no = c.enterprise_no "+
                   "and a.owner_no = c.owner_no " +
                   "and a.enterprise_no = e.enterprise_no(+) "+
   	               "and a.warehouse_no = e.warehouse_no(+) " +
   	               "and a.owner_no = e.owner_no(+) " +
   	               "and a.billing_project = e.billing_project(+) " +
   	               "and a.enterprise_no = f.enterprise_no(+) " +
		           "and a.warehouse_no = f.warehouse_no(+) " +
		           "and a.owner_no = f.owner_no(+) " +
		           "and a.check_no = f.check_no(+) " +
		           "and a.account_no = f.account_no(+) " +
                   "and a.enterprise_no='"+strEnterpriseNo+"' " +
   				   "and a.warehouse_no='"+strWarehouseNo+"' " +
   				   "and a.owner_no in("+strWorkerOwner+") ";
		
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}
		if(strMenuType.equals("1")){
			strSql+=" and a.billing_project in(select distinct billing_project from cost_account_formula ) ";
		}
		strSql+=" order by a.owner_no,a.billing_project,a.build_date desc ";
		List<Cost_CostListModel> list = genDao.getListByNativeSql(strSql,Cost_CostListModel.class/*,poPageBo.getStart(),poPageBo.getPagesize()*/);
		Integer intCount = genDao.getCountByNativeSql("select count(*) from ("+strSql+" )");
		ExtListDataBo<Cost_CostListModel> extListBo= new ExtListDataBo<Cost_CostListModel>(list, intCount);
		return extListBo;
	}
	//取导出的未出账清单信息
	@Override
	public List getNoAccountList2(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String strQuery,
			Integer intRequestFlag) throws Exception {
		String strSql="select "+
				"a.warehouse_no," +
                "'[' || a.owner_no || ']' || c.owner_name ownerNoText, "+
				"'[' || e.account_no || ']' || e.account_name accountNoText, "+
                "'[' || a.billing_project || ']' || b.project_name billingProjectText, "+
				" '['|| ltrim(a.cost_flag)||']'||f_get_fieldtext('COST_OTHER_LIST','COST_FLAG',a.cost_flag)costFlagText, " +
                "to_char(a.build_date,'yyyy-mm-dd')buildDateText, "+
                "to_char(a.begin_date,'yyyy-mm-dd')beginDateText, "+
                "to_char(a.end_date,'yyyy-mm-dd')endDateText, " +
                "case when b.billing_unit ='2' then a.qty else 0 end area, "+  
                "case when b.billing_unit ='3' then a.qty else 0 end tray, "+ 
                "case when b.billing_unit in('0','4') then a.qty else 0 end qty2, "+
                "case when b.billing_unit ='5' then a.qty else 0 end volume, "+
                "case when b.billing_unit ='6' then a.qty else 0 end weigth, " +
                "case when b.billing_unit in('7','8') then a.qty else 0 end costPrice, "+
                "case when b.billing_unit ='9' then a.qty else 0 end cell, "+
                "b.unit_price, "+
                "a.amount, "+              
                "b.other_cost1,b.other_cost2,b.other_cost3,b.other_cost4,b.other_cost5, "+
                "a.favourable_amount, "+
                "(a.amount+b.other_cost1+b.other_cost2+b.other_cost3+b.other_cost4+b.other_cost5-a.favourable_amount) as total "+     
            "from cost_cost_list a, cost_formulaset b, bdef_defowner c, " +
				"(select distinct caf.enterprise_no,caf.warehouse_no," +
				 "caf.owner_no,caf.account_no,caf.billing_project,cad.account_name " +
				 "from cost_account_formula caf,cost_account_set cad " +
				 "where caf.enterprise_no=cad.enterprise_no " +
				 "and caf.account_no=cad.account_no " +
				 ") e, "+
				 "cost_financial f  "+
           "where a.warehouse_no = b.warehouse_no "+
             "and a.enterprise_no = b.enterprise_no "+
             "and a.owner_no = b.owner_no "+
             "and a.billing_project = b.billing_project "+
             "and a.enterprise_no = c.enterprise_no "+
             "and a.owner_no = c.owner_no " +
             "and a.enterprise_no = e.enterprise_no(+) "+
	         "and a.warehouse_no = e.warehouse_no(+) " +
	         "and a.owner_no = e.owner_no(+) " +
	         "and a.billing_project = e.billing_project(+) " +
	         "and a.enterprise_no = f.enterprise_no(+) " +
		     "and a.warehouse_no = f.warehouse_no(+) " +
		     "and a.owner_no = f.owner_no(+) " +
		     "and a.check_no = f.check_no(+) " +
		     "and a.account_no = f.account_no(+) " +
             "and a.enterprise_no='"+strEnterpriseNo+"' " +
		     "and a.warehouse_no='"+strWarehouseNo+"' " +
		     "and a.owner_no in("+strWorkerOwner+") ";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+strWs;
		}		
		strSql+=" order by a.begin_date ";
		List list=genDao.getListByNativeSql(strSql);

		if(list.size()>0){
			List listtot = genDao.getListByNativeSql(
			"select '总合计：'as warehouse_no," +
			        "'' as ownerNoText," +
			        "'' as accountNoText," +
			        "'' as billingProjectText," +
			        "'' as costFlagText," +
			        "'' as buildDateText," +
			        "'' as beginDateText," +
			        "'' as endDateText," +			        
					"sum(area) area," +
					"sum(tray) tray," +
					"sum(qty2) qty2," +
					"sum(volume) volume," +
					"sum(weigth) weigth, " +
					"sum(costPrice) costPrice, " +
					"sum(cell) cell, " +
					"'' as unitPrice, "+
					"sum(amount) amount, " +				
					"sum(other_cost1) otherCost1, " +
					"sum(other_cost2) otherCost2, " +
					"sum(other_cost3) otherCost3, " +
					"sum(other_cost4) otherCost4, " +
					"sum(other_cost5) otherCost5, " +	
					"sum(favourable_amount) favourableAmount, " +
					"sum(total) total " +
					"from("+strSql+")");
			list.add(listtot.get(0));
		}
		return list;
	}

	//重算费用明细
	@Override
	public MsgRes tscResetCost(String strEnterpriseNo,String strWarehouseNo,
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
			resultList = genDao.execProc(inList, outList, "PKOBJ_COST.P_COST_DETAILS");
			System.out.println(inList);
			if(resultList.get(0).toString().indexOf("N|")!=-1)
			{
				throw new Exception(resultList.get(0).toString());
			}
			beD=DateUtil.addDateTime(beD, 1);
		}			
		return new MsgRes(true,"重算成功！","");
	}
	//费用明细回退
	@Override
	public MsgRes tscUndoCost(String strEnterpriseNo, String strWarehouseNo,
			String strWorkerOwner, String str) throws Exception {
		List<Cost_CostListModel> str1=JSON.parseArray(str, Cost_CostListModel.class);
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		
		for (int i=0; i<str1.size();i++) {
			List  inList=new ArrayList();
			
			inList.add(strEnterpriseNo);
			inList.add(strWarehouseNo);
			inList.add(str1.get(i).getOwnerNo());//strOwnerNo
			inList.add(str1.get(i).getBillingProject());//strBilling_project 
			inList.add(str1.get(i).getBillingType());//strBillingType     
			inList.add(str1.get(i).getBuildDate());//dtDate 
			inList.add(str1.get(i).getSerialNo());//dtDate 
			System.out.println(inList);
			resultList=genDao.execProc(inList, outList, "PKOBJ_COST.P_REBACK_COST_DETAILS");
			System.out.println(resultList.get(0).toString());

			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
		}
		return new MsgRes(true,"回退成功！","");
	}
	
}
